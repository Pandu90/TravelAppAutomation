package com.tests;

import com.pages.SearchByFilterPage;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.drivers.DriverSetup;
import com.pages.SearchPage;
import com.pages.SignInPage;

/**
 * TestNg class where actual test executes. "validateProductCheckout" method
 * executes the complete flow of the testing from sign,searching the product and
 * buying it.
 * 
 */

public class AppiumTest extends DriverSetup {

	/*
	 * TestNG Test method to execute the automation flow of eBay app verify
	 * product details scenario
	 */
	@Test
	public void validateProductCheckout() {
		try {

			new SignInPage(driver).loginIn();
			Reporter.log("Successfully completted singing into the app");
			new SearchPage(driver).searchCityAndRoom();
			Reporter.log("Successfully entered city, Room and Guest details in | ");
			new SearchByFilterPage(driver).searchRoomByFilter();

		} catch (Exception e) {
			System.out.println(e);
			Reporter.log("Application Testing Failed with following error : " + e);
		}
	}

	

}
