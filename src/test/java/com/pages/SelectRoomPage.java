package com.pages;


import com.utils.DriverActions;
import com.utils.ExcelUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Page object model Class for search screen. Contains methods for select hotel and room in the app.
 * 
 * @author Pandu
 * @email pandurangang.gangatharan.2@team.telstra.com
 * 
 */
public class SelectRoomPage extends DriverActions {

	By itemTitle = By.id("");
	By itemSubtitle = By.id("");
	By itemPrice = By.id("");

	public static String itemTitle_str;
	public static String itemPrice_str;

	@SuppressWarnings("rawtypes")
	public SelectRoomPage(AppiumDriver driver) throws Exception {
		super(driver);

		// Setting the test-data file used for data and the sheet name.
		ExcelUtils.setExcelFile("datasheet.xlsx", "search_hotel");
	}

	/*
	 * Java method to automate to select hotel and room
	 */
	public void verifyHotelAndRoom() throws Exception {

		//validateResult(itemPrice_str,SearchPage.productPrice_str);
	}

}
