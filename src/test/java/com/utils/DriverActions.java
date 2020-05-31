package com.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

import static java.lang.Thread.sleep;

/**
 * Class for all the performing actions in the device. Contains methods for
 * performing actions like sendkeys, click, wait, swiping, assert, rotation etc.
 * 
 * @author Pandu
 * @email pandurangang.gangatharan.2@team.telstra.com
 */


public class DriverActions {
	
	protected AppiumDriver driver;

	public DriverActions(AppiumDriver driver) {
		this.driver = driver;
	}

	protected void clickOnElement(By elementSelector) {
		driver.findElement(elementSelector).click();
	}


	public void click(By by) throws InterruptedException {
		boolean isElementFound = waitForElement(by,3);
		if (isElementFound) {
			driver.findElement(by).click();
		}
	}

	public void click(By parentBy, By childBy) throws InterruptedException {
		boolean isElementFound = waitForElement(parentBy,3);
		if (isElementFound) {
			driver.findElement(parentBy).findElement(childBy).click();
		}
	}

	public void clickByCoordinates(int x, int y) {
		TouchAction action = new TouchAction(driver);
		action.tap(new PointOption().withCoordinates(x,y));
		action.perform();
	}

	protected void sendKeysToElement(By elementSelector, String value) {
		driver.findElement(elementSelector).clear();
		driver.findElement(elementSelector).sendKeys(value);
	}


	@SuppressWarnings("unchecked")
	protected List<WebElement> getAllElements(By elementSelector) {
		List<WebElement> childElements = driver.findElements(By.className("android.view.View"));
		return childElements;
	}

	/**
	 * Scroll up half screen everytime when this method is called
	 */
	public void scrollUp() {
		Dimension size = driver.manage().window().getSize();
		int startx = size.width / 2;
		int starty = size.height / 2;
		int endx = size.width / 2;
		int endy = (size.height*3) / 4;
		TouchAction action = new TouchAction(driver).press(PointOption.point(startx, starty)).
				waitAction().
				moveTo(PointOption.point(endx, endy)).
				release().
				perform();
		driver.performTouchAction(action);
	}

	/**
	 * Scroll down half screen everytime when this method is called
	 */
	public void scrollDown() {
		Dimension size = driver.manage().window().getSize();
		int startx = size.width / 2;
		int starty = (size.height*3) / 4;
		int endx = size.width / 2;
		int endy = size.height / 4;
		TouchAction action = new TouchAction(driver).press(PointOption.point(startx, starty)).
				waitAction().
				moveTo(PointOption.point(endx, endy)).
				release().
				perform();
		driver.performTouchAction(action);
	}

	protected void rotateScreen(String orientation) {
		ScreenOrientation position;
		switch (orientation) {
		case "portrait":
			position = ScreenOrientation.PORTRAIT;
			break;
		case "landscape":
			position = ScreenOrientation.LANDSCAPE;
			break;
		default:
			position = ScreenOrientation.PORTRAIT;

		}
		driver.rotate(position);
	}

	protected String getOrientation() {
		return driver.getOrientation().value();
	}

	protected void waitForVisibilityOf(By locator, long timeOutSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void waitForClickabilityOf(By locator, long timeOutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	protected String getText(By elementSelector) {
		try {
			String text = driver.findElement(elementSelector).getText();
			return text;
		} catch (Exception e) {
			return null;
		}
	}

	public String getText(By parentElementBy, By childElementBy) {

		try{
			MobileElement parentElement = (MobileElement) driver.findElement(parentElementBy);
			MobileElement childElement = parentElement.findElement(childElementBy);
			return childElement.getText();
		} catch (Exception ex) {
			return null;
		}
	}

	protected void validateResult(String textOne, String textTwo) {
		try {
			Assert.assertEquals(textOne, textTwo);
		} catch (Exception e) {
			System.out.println("Exception occured");
		}

	}

	public boolean waitForElement(By by, int defaultWait) throws InterruptedException {
		boolean found = false;
		int count = 0;
		int elementFound = 0;

		while (count < defaultWait) {
			elementFound = driver.findElements(by).size();

			if (elementFound > 0) {
				found = true;
				break;
			} else {
				sleep(1000);
				count += 1;
			}
		}
		return found;
	}

	protected void implicitWait(int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

}
