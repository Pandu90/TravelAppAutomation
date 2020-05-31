package com.pages;



import org.openqa.selenium.By;

import com.utils.DriverActions;
import com.utils.ExcelUtils;

import io.appium.java_client.AppiumDriver;
import org.testng.Reporter;

/**
 * Page object model Class for sign in screen. Contains methods to navigate to
 * login in the app.
 * 
 * @author Pandu
 * @email pandurangang.gangatharan.2@team.telstra.com
 */

public class SignInPage extends DriverActions {

	By btnLogin = By.id("headerWidget");
	By btnSkip = By.xpath("//*[@text='SKIP']");
	By txtUsername = By.xpath("//*[contains(@text,'Enter Mobile No')]");
	By btnContinue = By.xpath("//*[contains(@text,'CONTINUE')]");
	By txtPassword = By.xpath("//android.widget.EditText[@text='Enter Password']");
	By btnSignIn = By.id("com.makemytrip:id/btn_continue");
	By btnClearUser = By.id("text_input_end_icon");
	By btnViaPassword = By.id("pwd_option");
	
	@SuppressWarnings("rawtypes")
	public SignInPage(AppiumDriver driver) throws Exception {
		super(driver);

		// Setting the test-data file used for data and the sheet name.
		ExcelUtils.setExcelFile("datasheet.xlsx", "sign_in");
	}

	/**
	 * Method to sign into an application
	 * @throws Exception
	 */
	public void loginIn() throws Exception {

		driver.navigate().back();
		String strUsername = ExcelUtils.getCellData(1, 1);
		String strPassword = ExcelUtils.getCellData(1, 2);
		Reporter.log("Signing in with: " + strUsername + " " + strPassword);
		if(driver.findElement(btnClearUser).isDisplayed()){
			click(btnClearUser);
		}
		sendKeysToElement(txtUsername, strUsername);
		click(btnContinue);
		click(btnViaPassword);
		sendKeysToElement(txtPassword, strPassword);
		driver.hideKeyboard();
		click(btnSignIn);
		Reporter.log("Logged in successfully");

	}
	
}