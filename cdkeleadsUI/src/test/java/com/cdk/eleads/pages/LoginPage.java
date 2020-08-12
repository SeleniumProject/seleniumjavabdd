package com.cdk.eleads.pages;

import org.openqa.selenium.By;
public class LoginPage {

	public static By loginlbl = By.xpath("//*[@class='title']/h1");
	public static By usernametxt=By.id("user");
	public static  By passwordtxt = By.id("password");
	public static  By loginbtn = By.id("loginbtn");
	
	//Name of the Objects
	public static String loginlblnm = "Login Lable";
	public static String usernm = "Enter Email";
	public static String passwordnm = "Enter Password";
	public static String loginbtnm = "Click Login button";
	
}
