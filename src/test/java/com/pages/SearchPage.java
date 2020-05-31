package com.pages;

import org.openqa.selenium.By;

import com.utils.DriverActions;
import com.utils.ExcelUtils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;

/**
 * Page object model Class for search screen. Contains methods for searching the
 * hotels in the app.
 * 
 * @author Pandu
 * @since 30/05/2020
 * 
 */
public class SearchPage extends DriverActions {

	By icnHotel = By.xpath(" //*[@resource-id='com.makemytrip:id/title' and @text='Hotels']");
	By lblHotelPageTitle = By.id("com.makemytrip:id/tv_title_light'");
	By lblLocation = By.id("com.makemytrip:id/location");
	By txtCityName = By.xpath("//*[@class='android.widget.EditText']");
	By lblSelectCity = By.xpath("//*[@resource-id='com.makemytrip:id/primary_text' and @text='Delhi']");
	By lblCityName = By.id("com.makemytrip:id/city");
	By pnlCheckInDate = By.id("checkIn_layout");
	By pnlCalendarMonth = By.id("rvCalendarMonth");
	By lblCheckInDate = By.id("checkInDate");
	By lblCheckInMMYY = By.id("checkInMonthYear");
	By lblCheckInDay = By.id("checkInWeek");
	By lblCheckOutDate = By.id("checkInDate");
	By lblCheckOutMMYY = By.id("checkInMonthYear");
	By lblCheckOutDay = By.id("checkInWeek");
	By btnCalendarDone = By.id("rlDone");
	By lblGuestsCount = By.id("guest_count");
	By lblRoomsCount = By.id("room_count");


	By pnlRooms = By.id("room_layout");
	By pnlRoom1Detail = By.xpath("//*[contains(@id,'rlAddRoomContainer')]/*[contains(@id,'rl_container_expanded')]");
	By pnlRoom2Detail = By.xpath("//*[@id='rlAddRoomContainer']/*[@id='rl_container_expanded']");
	By lblAdultCount = By.id("tvadultcount");
	By imgAdultSub = By.id("ivadultsubtract");
	By imgAdultAdd = By.id("ivadultadd");
	By lblChildCount = By.id("tvchildcount");
	By imgChildSub = By.id("ivchildsubtract");
	By imgChildAdd = By.id("ivchildadd");
	By btnAddRoom = By.id("btn_add_room");
	By btnDone = By.id("btn_done");
	By lblTripTypeFamily = By.id("rb_travel_type_family");
	By btnSearch = By.id("search_button");


	public static String strSelectedCityName;
	public static String strCheckInDate;
	public static String strCheckInDay;
	public static String strCheckOutDate;
	public static String strCheckOutDay;
	public static int intGuestCount;
	public static int intRoomsCount;


	public SearchPage(AppiumDriver driver) throws Exception {
		super(driver);

		// Setting the test-data file used for data and the sheet name.
		ExcelUtils.setExcelFile("datasheet.xlsx", "search_hotel");
	}

	/**
	 * Method to select City and enter guest details
	 * @throws Exception
	 */
	public void searchCityAndRoom() throws Exception {
		click(icnHotel);
		click(lblLocation);
		click(lblSelectCity);
		strSelectedCityName = getText(lblCityName);
		selectRoomStayDates();
		EnterGuestDetails();
		click(lblTripTypeFamily);
		click(btnSearch);
	}

	/**
	 * Method to select room check in and out details
	 * @throws InterruptedException
	 */
	public void selectRoomStayDates() throws InterruptedException {
		click(pnlCheckInDate);
		scrollDown();
		Dimension size = driver.findElement(pnlCalendarMonth).getSize();
		clickByCoordinates((size.width /3),(size.height / 2)) ;
		clickByCoordinates((size.width /2),(size.height / 2)) ;
		click(btnCalendarDone);
		strCheckInDate = getText(lblCheckInDate)+" "+getText(lblCheckInMMYY);
		strCheckInDay = getText(lblCheckInDay);
		strCheckOutDate = getText(lblCheckOutDate)+" "+getText(lblCheckOutMMYY);
		strCheckOutDay = getText(lblCheckOutDay);

	}

	/**
	 * Method to enter guest details
	 * @throws Exception
	 */
	public void EnterGuestDetails() throws Exception {
		click(pnlRooms);
		selectGuests(2,lblAdultCount,imgAdultAdd,imgAdultSub);
		selectGuests(2,lblChildCount,imgChildAdd,imgChildSub);
		click(btnAddRoom);
		selectGuests(2,lblAdultCount,imgAdultAdd,imgAdultSub);
		selectGuests(2,lblChildCount,imgChildAdd,imgChildSub);
		click(btnDone);
		intGuestCount = Integer.parseInt(getText(lblGuestsCount));
		intRoomsCount = Integer.parseInt(getText(lblRoomsCount));
	}

	public void selectGuests(int howMany, By addedCount, By add, By sub) throws Exception {
		int count=0;
		while(!(count==howMany)){
			count = Integer.parseInt(getText(addedCount));
			if(count<howMany){
				click(add);
			}else if(count>howMany) {
				click(sub);
			}
		}

	}
}
