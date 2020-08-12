package com.cdk.eleads.step_definitions;

import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.cdk.eleads.pages.DashBoardPage;
import com.cdk.eleads.pages.LoginPage;
import com.cdk.eleads.pages.ProspectPage;
import com.cdk.eleads.utilities.ExcelDataUtil;
import com.cdk.eleads.utilities.GlobalUtil;
import com.cdk.eleads.utilities.KeywordUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddProspects extends KeywordUtil {

	@SuppressWarnings("rawtypes")
	static Class thisClass = AddProspects.class;
	static String testCaseID = thisClass.getSimpleName();
	public WebDriver driver;

	public static HashMap<String, String> dataMap = new HashMap<String, String>();
	String newsletter = "testing" + KeywordUtil.getCurrentDateTime();

	@Given("^Read the test data  \"([^\"]*)\" from Excel file$")
	public void read_the_test_data_from_Excel_file(String arg1) {
		try {
			dataMap = ExcelDataUtil.getTestDataWithTestCaseID("login", arg1);

		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

	@When("^User navigate to site url$")
	public void user_navigate_to_site_url() throws Throwable {
		try {
			navigateToUrl(dataMap.get("URL"));

		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

	@When("^I Enter the user name$")
	public void i_Enter_the_user_name() throws Throwable {

		KeywordUtil.inputText(LoginPage.usernametxt, dataMap.get("username"),	LoginPage.usernm + " : " + dataMap.get("username"));
	}

	@When("^I Enter the Password$")
	public void i_Enter_the_Password() throws Throwable {

		KeywordUtil.inputText(LoginPage.passwordtxt, dataMap.get("password"), LoginPage.passwordnm + " : " + dataMap.get("password"));

	}

	@When("^I Click on Login button$")
	public void i_Click_on_Login_button() throws Throwable {

		KeywordUtil.click(LoginPage.loginbtn, "Click on Login button");
	}

	@Then("^Validate login successfull and check the dashboard title$")
	public void validate_login_successfull_and_check_the_dashboard_title() throws Throwable {

		KeywordUtil.isWebElementVisible(DashBoardPage.logo, "ELeads Present");

	}

	@When("^I Click on Prospects Menu item from left hand side$")
	public void i_Click_on_Prospects_Menu_item_from_left_hand_side() throws Throwable {

		KeywordUtil.clickElementFromList(DashBoardPage.menuItem, "Prospects", "Click on Prospect Menu");
	}

	@When("^I Click on Add Prospect submenu under Prospects$")
	public void i_Click_on_Add_Prospect_submenu_under_Prospects() throws Throwable {

		KeywordUtil.clickElementFromList(DashBoardPage.submenuitem, "Add Prospect", "Click on Add Prospects Menu");
	}

	@When("^I Click on Add plus button on the top$")
	public void i_Click_on_Add_plus_button_on_the_top() throws Throwable {

		KeywordUtil.click(DashBoardPage.addplusbutton, "Click on Add plus button");
	}

	@Then("^I Validate the title of the page$")
	public void i_Validate_the_title_of_the_page() throws Throwable {

	}

	@When("^I Enter the FirstName and Last name$")
	public void i_Enter_the_FirstName_and_Last_name() throws Throwable {
		KeywordUtil.switchToFrame(0);
		KeywordUtil.inputText(ProspectPage.firstname, "RockTest", "Enter the FirstName ");
		KeywordUtil.inputText(ProspectPage.lastname, "Hawking", "Enter the LastName ");
	}

	@When("^I Click on Search button$")
	public void i_Click_on_Search_button() throws Throwable {

		KeywordUtil.click(ProspectPage.searchbtn, "Click on Search button");

	}

	@Then("^I Validate the no search results lable$")
	public void i_Validate_the_no_search_results_lable() throws Throwable {

	}

	@When("^I Click on No Match - Add New Sales Customer link$")
	public void i_Click_on_No_Match_Add_New_Sales_Customer_link() throws Throwable {

		KeywordUtil.click(ProspectPage.nomatch, "Click on No Match button");
	}

	@Then("^Validate the Firstname and Last Name entered successfully$")
	public void validate_the_Firstname_and_Last_Name_entered_successfully() throws Throwable {

	}

	@When("^Enter the Address details Zip and click on house button select city$")
	public void enter_the_Address_details_Zip_and_click_on_house_button_select_city() throws Throwable {

	//	KeywordUtil.inputText(DashBoardPage.street1, "Florida", "Enter the Street 1 ");
	//	KeywordUtil.inputText(DashBoardPage.stree2, "Demonte Colony", "Enter the Street 2 ");
	//	KeywordUtil.inputText(DashBoardPage.zip, "10001", "Enter the Street 2 ");
	//	Thread.sleep(2500);
	//	KeywordUtil.click(ProspectPage.ziphome, "Click on House button for Pincode");
	//	Thread.sleep(1500);
	//	KeywordUtil.selectBasedOnIndex(ProspectPage.countryselectionpopup, 0, "Select the NewYork City");
	}

	@When("^Enter the Phone number along with primary email address$")
	public void enter_the_Phone_number_along_with_primary_email_address() throws Throwable {
		Thread.sleep(1000);
	//	KeywordUtil.inputText(ProspectPage.phonenumber, "212-2232", "Enter Phone number");
		Thread.sleep(2500);
//		KeywordUtil.acceptAlert();
		Thread.sleep(1000);
		KeywordUtil.inputText(ProspectPage.primaryemail, "ramesh.kudikala@cdk.com", "Enter the Primary Email Address");
		Thread.sleep(8000);
	}

	@When("^I select the Showroom up as type$")
	public void i_select_the_Showroom_up_as_type() throws Throwable {

		KeywordUtil.selectValueFromDropDown(ProspectPage.typedrp, "Showroom Up", "select the showroomup as a type");
	}

	@When("^I Select the google as a Source$")
	public void i_Select_the_google_as_a_Source() throws Throwable {
		Thread.sleep(2500);
		KeywordUtil.selectValueFromDropDown(ProspectPage.sourcedrp, "Google", "select the Source as Google");
	}

	@When("^I CLick on Save button$")
	public void i_CLick_on_Save_button() throws Throwable {

		KeywordUtil.click(ProspectPage.savebtn, "Click on Save button");
	}

	@When("^I Click on (\\d+)PhoneCall Activity by clicking on Complete$")
	public void i_Click_on_PhoneCall_Activity_by_clicking_on_Complete(int arg1) throws Throwable {

		KeywordUtil.switchToFrame(1);
		KeywordUtil.clickOnComplete(ProspectPage.typeofcalls, "24 phone call");
		KeywordUtil.defaultContent();
		KeywordUtil.getWindowsHandles();
	}

	@When("^I Enter the comments$")
	public void i_Enter_the_comments() throws Throwable {
		Thread.sleep(1200);
		KeywordUtil.inputText(ProspectPage.comments, "Testing Comments", "Enter the comments");
	}

	@When("^I CLick on Complete Activity$")
	public void i_CLick_on_Complete_Activity() throws Throwable {
		KeywordUtil.click(ProspectPage.changecurrentactivity, "Click on current activity");
		Thread.sleep(1500);
		KeywordUtil.getWindowsHandles();
	
		KeywordUtil.selectValueFromDropDown(ProspectPage.switchtocurrenttaskdrp, "Send Email", "Select Send Email /Letter value from drop down");
		KeywordUtil.click(ProspectPage.submitbtn, "Click on save button");
		Thread.sleep(2000);
		KeywordUtil.getWindowsHandles();
		KeywordUtil.inputText(ProspectPage.comments, "Testing Comments", "Enter the comments");
		Thread.sleep(2000);
		KeywordUtil.click(ProspectPage.sendEmailProcessActivity, "Click on Send Email button");
	}

	@When("^I Click on Schedule at the top of the Opportunity$")
	public void i_Click_on_Schedule_at_the_top_of_the_Opportunity() throws Throwable {
		
             //  KeywordUtil.click(ProspectPage.schedulemenu, "Click on Schedule button");
	}

	@When("^I Click on Schedule Task of Send Email /Letter  and click on Schedule$")
	public void i_Click_on_Schedule_Task_of_Send_Email_Letter_and_click_on_Schedule() throws Throwable {
		//KeywordUtil.getWindowsHandles();
	//	KeywordUtil.selectValueFromDropDown(ProspectPage.taskdropdown, "Send Email/Letter", "Select the send Email /Letter option");
	//	KeywordUtil.inputText(ProspectPage.schedulcontactcomments, "Testing Comments", "Enter the Comments in Schedule contacts");
		
	//	KeywordUtil.click(ProspectPage.schedulebtn, "Click on Scheduel menu");
		//KeywordUtil.getWindowsHandles();
	}

	@When("^Click on Complete on Send Email / Letter task$")
	public void click_on_Complete_on_Send_Email_Letter_task() throws Throwable {

	 //  KeywordUtil.click(ProspectPage.sendEmailMenu, "Click on Email Menu Item");
	}

	@When("^I CLick on Send Email in Process Activity Page$")
	public void i_CLick_on_Send_Email_in_Process_Activity_Page() throws Throwable {
		KeywordUtil.getWindowsHandles();
		KeywordUtil.inputText(ProspectPage.emailpopupcomment, "Testing Email Comments", "Enter the To comments");
	}

	@When("^I Add Email Address in To: Field$")
	public void i_Add_Email_Address_in_To_Field() throws Throwable {
		
		KeywordUtil.inputText(ProspectPage.toAddress, "rkudikala83@gmail.com", "Enter the To Email Address");
		
	}

	@When("^I Enter the comments, subject and Body of the message$")
	public void i_Enter_the_comments_subject_and_Body_of_the_message() throws Throwable {
		KeywordUtil.inputText(ProspectPage.subjectemail, "Testing Email Subject", "Enter the To comments");
		Thread.sleep(2000);
		KeywordUtil.sendKeysWithCommand(ProspectPage.checkspelling, Keys.TAB);
		Thread.sleep(2000);
		KeywordUtil.sendDataInHtml("Body Text with comments");
	}

	@When("^I Click on Send button$")
	public void i_Click_on_Send_button() throws Throwable {
		
		KeywordUtil.click(ProspectPage.sendEmailbtn, "Click on Send Email Button");
	}

	@When("^I CLick on DeskLog link on left side of the page$")
	public void i_CLick_on_DeskLog_link_on_left_side_of_the_page() throws Throwable {

	}

	@Then("^User verify the Customer is now on the desklog for today$")
	public void user_verify_the_Customer_is_now_on_the_desklog_for_today() throws Throwable {

	}

}
