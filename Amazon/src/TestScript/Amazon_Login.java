/*
	 * 1.Launch “www.amazon.com” page and validate page header
	   2.Click on login link and Provide incorrect username and validate error.
	   3.Provide incorrect password and validate error.
	   4.Provide correct username/password and validate home page header.
	   5.Search for iphone x 64 gb and click on 1st product and move to product page.
	   6.Capture product name and price and add 1 quantity to basket
	   7.Validate product is added into basket on product page
	   8.Vaidate product price and name on basket page.
	   9.Logout and login again validate product in basket is present or not.
	 */
/* Created By: Priyanka zope*/

package TestScript;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Library.CommanMethods;
import Library.driverUtils;
import PropertyFile.LoadProperties;

public class Amazon_Login extends CommanMethods{
	public WebDriver driver;
	 @BeforeClass
	    public void setUp(){
	        driver = driverUtils.getDriver();

	    }
	
	  @Test()
	  public void addToCart()
	  
	  {  
		  CommanMethods cm=new CommanMethods();
	  	  //get value from property file
		  String usernameWrong = LoadProperties.user.getProperty("usernameWrong");
		  String username = LoadProperties.user.getProperty("username");
	      String passwordW = LoadProperties.user.getProperty("pwdwrong");
	      String password = LoadProperties.user.getProperty("password");
	      
	      cm.navigateURL();
		  cm.navigateToSignInPage();
		  
		  //Login with incorrect and then correct details
		  cm.loginWithinCorrectDetails(usernameWrong, passwordW,username,password);
		  
		  //Search product and add to basket
		  cm.filterSearch();
		  
		  //log out
		 
		  cm.signOut();
		  
		  //Login with correct details
		  cm.loginWithCorrectDetails(username,password);
		  
		  //Check product is present in basket
		  cm.checkBasket();
		  
		  //logout
		  cm.signOut();
		  
	  }

	  @AfterClass
	    public void tearDown(){
	        driver.quit();
	    }
	}

