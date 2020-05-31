package com.pages;

import com.utils.DriverActions;
import com.utils.ExcelUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

/**
 * Page object model Class for search screen. Contains methods for searching the
 * hotels in the app.
 * 
 * @author Pandu
 * @email pandurangan.gangatharan.2@team.telstra.com
 * @since 30/05/2020
 * 
 */
public class SearchByFilterPage extends DriverActions {

	By btnSortAndFilter= By.xpath("//*[contains(@resource-id,'tv_sort_filter')]");
	By lblUserRatingHeading=By.xpath("//android.widget.TextView[contains(@text,'User Rating')]");
	By imgseekBar= By.id("makemytrip:id/price_seek_bar");
	By chkUserRating= By.xpath("//android.widget.TextView[contains(@text,'4.0 & Above')]");
	By applyFilter = By.id("com.makemytrip:id/tv_apply");


	public SearchByFilterPage(AppiumDriver driver) throws Exception {
		super(driver);

		// Setting the test-data file used for data and the sheet name.
		ExcelUtils.setExcelFile("datasheet.xlsx", "search_hotel");
	}

	/**
	 * Method to sort room by adding price filter
	 * @throws Exception
	 */
	public void searchRoomByFilter() throws Exception {
		click(btnSortAndFilter);
		swipeSeekBar();
		scrollDown();
		validateResult(getText(lblUserRatingHeading),"User Rating");
		click(chkUserRating);
		click(applyFilter);
	}

	/**
	 * Method to drag price value in bar element
	 * @throws Exception
	 */
	public void swipeSeekBar() throws Exception {
		driver.context("NATIVE_APP");
		MobileElement seek_bar = (MobileElement) driver.findElement(imgseekBar);
		int start=seek_bar.getLocation().getX();
		int end=seek_bar.getSize().getWidth();
		int y=seek_bar.getLocation().getY();
		TouchAction action=new TouchAction(driver);
		int moveTo=(int)(end*(0.075));
		action.press(PointOption.point(start,y)).moveTo(PointOption.point(moveTo,y)).release().perform();
	}
}
