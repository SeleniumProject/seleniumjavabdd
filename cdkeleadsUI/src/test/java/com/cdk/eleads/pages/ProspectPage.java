package com.cdk.eleads.pages;

import org.openqa.selenium.By;
public class ProspectPage {

	
	public static By firstNametxt =By.cssSelector("span#tdAddProspectImage.buttons");
	public static By lastNametxt =By.cssSelector("input#szLastName.textBoxText.TextBoxColor.custname");
	public static By searchbtn =By.id("KioskSubmital");
	public static By sendEmailbtn =By.id("btnSend");
	
	public static By noresultslbl = By.xpath("//*[@id=\"maindata\"]/tbody/tr/td/font/b");
	public static By nomatch = By.id("NoMatch");
	
	public static By firstname = By.id("szFirstName");
	public static By lastname = By.id("szLastName");
	public static By middlename = By.xpath("//*[@name='szMiddleName']");
	public static By phonenumber = By.id("szHomeNumber");
	
	public static By typedrp = By.id("ddlType");
	public static By sourcedrp = By.id("Select1");
	public static By savebtn = By.name("btnSave");
	public static By zip = By.id("szZip");
	
	public static By ziphome = By.xpath("//*[@class='perfectAddress']/img");
	
	
	public static By countryselectionpopup = By.xpath("//*[@class='LinkButton10']");
	public static By typeofcalls = By.xpath("//*[@id='gvScheduled_Task_']");
	public static By comments = By.id("Comments");
	public static By changecurrentactivity = By.id("btnChangeCurrentTask");
	public static By submitbtn = By.id("Submit1");
	public static By schedulemenu= By.id("MenuBar_imgSchedule");
	public static By taskdropdown = By.xpath("//*[@name='listNextTasks']");
	public static By switchtocurrenttaskdrp = By.xpath("//*[@id='szSwitch']");
	public static By schedulcontactcomments = By.id("textNextTaskComments");

	public static By schedulebtn = By.id("textNextTaskComments");
	public static By toAddress = By.id("szTo");
	public static By sendEmailMenu = By.xpath("//*[@class='menubarlink x_email']");
	public static By emailpopupcomment = By.xpath("//*[@name='szComments']");
	public static By sendEmailProcessActivity = By.xpath("//*[@id='btnSendEmail']");
	public static By subjectemail = By.xpath("//*[@id='szSubject']");
	public static By checkspelling = By.xpath("//*[text()='Check Spelling']");
	
	public static By bodytext = By.xpath("//*[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']");
	public static By primaryemail = By.xpath("//*[@name='szAddress' and @class='textBoxText textBoxColor custemail']");
	
	
	
	//Name of the Objects
	public static String menuItemnm = "menu Item";
	public static String submenuitemnm = "Sub Menu Item";
	

}
