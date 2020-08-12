package com.cdk.eleads.pages;

import org.openqa.selenium.By;
public class DashBoardPage {

	/**
	 * Author : Ramesh K
	 * Page Object Properties
	 */
	
	public static By logo = By.id("imageProduct");

	public static By menuItem = By.xpath("//*[contains(@id,'MenuSections_SectionLabel')]");
	public static By submenuitem=By.xpath("//ul[@class='menulist ui-menu ui-widget ui-widget-content ui-corner-all']/li");
	public static By addplusbutton=By.id("tdAddProspectImage");

	
	public static By firstname = By.xpath("//*[@name='szFirstName']");
	public static By lastname = By.xpath("//*[@name='szLastName']");
	public static By middlename = By.xpath("//*[@name='szMiddleName']");
	
	public static By street1 = By.xpath("//*[@name='szAddress1']");
	public static By stree2 = By.xpath("//*[@name='szAddress2']");
	public static By statedrp = By.xpath("//*[@id='lStateId']");
	public static By zip = By.xpath("//*[@name='szZip']");

	
	public static By housephone = By.xpath("//*[@name='szHomeNumber']");
	public static By housephoneimg = By.xpath("//*[@id='imgReversePhoneLookupHome']");
	public static By houseimg = By.xpath("//*[@class='perfectAddress']/img");

	
	//Name of the Objects
	public static String menuItemnm = "menu Item";
	public static String submenuitemnm = "Sub Menu Item";
	public static String logonm = "ELeads Logo";

}
