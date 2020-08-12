package com.cdk.eleads.step_definitions;

import org.testng.Assert;

import com.cdk.eleads.utilities.GlobalUtil;
import com.cdk.eleads.utilities.KeywordUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import mobileutil.AmazonAppFunctions;

public class MobileTestsAmazon {
	
	@Given("^open the Amazon app$")
	public void open_the_Amazon_app() throws Throwable {
		try{
			KeywordUtil.cucumberTagName = "MobileTestsAmazon";
		}
		catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
		
	}

	@Given("^click on skip sign in$")
	public void click_on_skip_sign_in() throws Throwable {
		try{
			AmazonAppFunctions.skipSignIn();
		}
		catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^select for an item by category$")
	public void search_for_an_item() throws Throwable {
		try{
			AmazonAppFunctions.clickShopByCategory();
			AmazonAppFunctions.clickFirstCategoryExpandButton();
			AmazonAppFunctions.selectProduct();
		}
		catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	    
	}

	@When("^add the item to cart$")
	public void add_the_item_to_cart() throws Throwable {
		try{
			AmazonAppFunctions.addProductToCart();
		}
		catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@Then("^verify item is added to cart$")
	public void verify_item_is_added_to_cart() throws Throwable {
		try{
			AmazonAppFunctions.verifyItemAddedToCart();;
		}
		catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@Given("^login to the app with username \"([^\"]*)\"$")
	public void login_to_the_app_with_username_and_password(String username) throws Throwable {
		try{
			AmazonAppFunctions.loginToAmazonApp(username);;
		}
		catch (Exception e) {
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

}
