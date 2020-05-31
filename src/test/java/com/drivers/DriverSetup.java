package com.drivers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.utils.PropertiesClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

/**
 * Class to setup and initialize android driver for appium. Reading and adding
 * the desired capabilities from the properties file.
 * 
 * @author Pandu
 * @email pandurangang.gangatharan.2@team.telstra.com
 */

@SuppressWarnings("rawtypes")
public class DriverSetup {

	protected AppiumDriver driver;
	AppiumDriverLocalService appiumService;
	String appiumServiceUrl;

	/*
	 * A Method which will start the appium server
	 */
	@BeforeSuite
	public void beforeTestSuite() {
		//startAppium();
	}

	/*
	 * A Method which is initializing the android driver before starts test
	 */
	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		// Calling initializing driver method for android testing.
		initializeAppiumDriver();
	}

	/*
	 * Java method to start appium with the default configurations using AppiumDriverLocalService class
	 */
	protected void startAppium() {
		appiumService = AppiumDriverLocalService.buildDefaultService();
		appiumService.start();
		appiumServiceUrl = appiumService.getUrl().toString();
		System.out.println("Appium Service Address : - " + appiumServiceUrl);
	}

	/*
	 * Java method to initialize Appium driver and set the appium capabilities
	 */
	protected void initializeAppiumDriver() throws MalformedURLException {
		PropertiesClass appiumProperties = new PropertiesClass();
		File app = null;

		// Desired capabilities initialization for test execution.
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", appiumProperties.getInstance().getProperty("Device_Name"));
		capabilities.setCapability("platformVersion", appiumProperties.getInstance().getProperty("Platform_Version"));
		capabilities.setCapability("platformName", appiumProperties.getInstance().getProperty("platformName"));

		capabilities.setCapability("appPackage",
				appiumProperties.getInstance().getProperty("Application_Package_Name"));
		capabilities.setCapability("appActivity",
				appiumProperties.getInstance().getProperty("Application_MainActivity_Name"));
		capabilities.setCapability("automationName", appiumProperties.getInstance().getProperty("automationName"));
		capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		capabilities.setCapability("autoAcceptAlerts", true);

		try {
			// initialize appium server.
			driver = new AndroidDriver(new URL("http://localhost:4724/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * A Method to quit the Android driver
	 */
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	/*
	 * A Method which will stop the appium server
	 */
	@AfterSuite
	public void AfterTestSuite() {
		stopAppium();
	}

	/*
	 * Java method to stop the appium server
	 */
	protected void stopAppium() {
		appiumService.stop();
	}

}
