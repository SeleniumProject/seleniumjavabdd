package mobileutil;

import java.awt.Button;
import java.io.IOException;

import com.cdk.eleads.utilities.DriverUtil;
import com.cdk.eleads.utilities.GlobalUtil;

public class AmazonAppFunctions extends MobileKeywords {

	public static void skipSignIn() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.skip_sign_in_btn, AndriodConstants.Common.type_id,
				"Click on Skip Sign In Button");
	}

	public static void clickShopByCategory() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.shop_by_category_btn, AndriodConstants.Common.type_id,
				"Click on Shop By Category Button");
	}

	public static void clickFirstCategoryExpandButton() throws InterruptedException {
		executionDelay(20000);
		click(AndriodConstants.Amazon.first_category_expand_btn, AndriodConstants.Common.type_xpath,
				"Click on First Category Expand Button");
	}

	public static void selectProduct() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.select_product_btn, AndriodConstants.Common.type_xpath,
				"Click on the product to be selected");
	}

	public static void addProductToCart() throws IOException, InterruptedException {
		executionDelay(30000);
		MobileKeywords.scrollInMobile();
		executionDelay(2000);
		click(AndriodConstants.Amazon.add_to_cart_btn, AndriodConstants.Common.type_xpath,
				"Click on Add to Cart button");
	}

	public static void verifyItemAddedToCart() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.checkout_cart_img, AndriodConstants.Common.type_id,
				"Verify Product is added to Cart");
	}

	public static void loginToAmazonApp(String username) {
		executionDelay(5000);
		click(AndriodConstants.Amazon.sign_In_btn, AndriodConstants.Common.type_id, "Click on Sign In Button");
		executionDelay(5000);
		writeInInput(AndriodConstants.Amazon.email_Login_txtbx, AndriodConstants.Common.type_id, username,
				"Enter Invalid Username");
		executionDelay(5000);
		click(AndriodConstants.Amazon.continue_btn, AndriodConstants.Common.type_id, "Click on Continue Button");
	}

}
