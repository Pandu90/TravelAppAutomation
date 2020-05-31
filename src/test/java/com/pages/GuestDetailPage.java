package com.pages;

import com.utils.DriverActions;
import com.utils.ExcelUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Page object model Class for search screen. Contains methods for searching the
 * hotels in the app.
 * 
 * @author Pandu
 * @email pandurangan.gangatharan.2@team.telstra.com
 * @since 30/05/2020
 * 
 */
public class GuestDetailPage extends DriverActions {

	By searchBox = By.id("");



	public GuestDetailPage(AppiumDriver driver) throws Exception {
		super(driver);

		// Setting the test-data file used for data and the sheet name.
		ExcelUtils.setExcelFile("datasheet.xlsx", "search_product");
	}
}
