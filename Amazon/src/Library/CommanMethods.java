package Library;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class CommanMethods {
	//Xpath
	By email_By=By.xpath("//input[@id='ap_email']");
	By coninue_By=By.xpath("//input[@id='continue']");
	By pwd_By=By.xpath("//input[@id='ap_password']");
	By unameErrorMsg_By=By.xpath("//div[@id='auth-error-message-box']/div/div/ul/li/span");
	By pwdErrorMsg_By=By.xpath("//div[@id='auth-error-message-box']/div/div/ul/li/span");
	By signinBtn=By.xpath("//input[@id='signInSubmit']");
	By filterSearch_By=By.xpath("//input[@id='twotabsearchtextbox']");
	By clickOnGo_By=By.xpath("//div[@id='nav-search']/form/div[2]/div/input");
	By productTitle_By=By.xpath("//span[@id='productTitle']");
	By productPrice_By=By.xpath("//span[@id='priceblock_ourprice']");
	By searchedProduct_By=By.xpath("//li[@id='result_0']/div/div/div/div[2]/div[1]/div[1]/a/h2");
	By productAddedProductpage_By=By.xpath("//div[@id='huc-v2-order-row-confirm-text']/h1");
	By productNameonBasket_By=By.xpath("//span[contains(text(), '      Apple iPhone X, GSM Unlocked 5.8')]");
	By productPriceonBasket_By=By.xpath("//form[@id='activeCartViewForm']/div[2]/div/div[4]/div/div[2]/p/span");
	By clickOnAccount_By=By.xpath("//a[@id='nav-link-accountList']/span[2]");
	By clickSignOut_By=By.xpath("//a[@id='nav-item-signout-sa']/span");

	WebDriver d;

	//Below method is used to navigate to amazon url
	public void navigateURL()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\geckodriver.exe");
		/*DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.firefox();
		capabilities.setBrowserName("firefox");
		capabilities.setVersion("your firefox version");
		capabilities.setPlatform(Platform.WINDOWS);
		capabilities.setCapability("marionette", false);
		d = new FirefoxDriver(capabilities);*/
		d= new FirefoxDriver();
		//WebDriver d=new FirefoxDriver();
		//1.Launch “www.amazon.com” page and validate page header
		d.get("http://www.amazon.com");
		String pageHeader=d.getTitle();
		if(pageHeader.contains("Amazon.com"))
		{
			System.out.println("validated page header");
		}
	}

	//Below method is used to go to sign in page
	public void navigateToSignInPage()
	{
		d.findElement(By.xpath("//span[contains(text(), 'Hello')]")).click();
	}

	//Below method is used to first login with incorrect uname and password and then correct uname and password
	public void loginWithinCorrectDetails(String usernameW, String password,String usernameR,String passwordR){

		WebElement email=d.findElement(email_By);
		email.sendKeys(usernameW);
		WebElement continued=d.findElement(coninue_By);
		continued.click();
		try
		{
			String errorMsgForEmail=d.findElement(unameErrorMsg_By).getText();
			System.out.println(errorMsgForEmail);
			if(errorMsgForEmail.contains("We cannot find an account with that email address"))
			{
				System.out.println("Incorrect Email ID");
			}
			String emailcontents=d.findElement(email_By).getAttribute("value");
			if(!emailcontents.isEmpty())
			{
				d.findElement(email_By).clear();
				d.findElement(email_By).sendKeys(usernameR);
				d.findElement(coninue_By).click();

			}
		}

		catch(Exception e)
		{
		}

		WebElement pwd=d.findElement(pwd_By);
		pwd.sendKeys(password);
		WebElement signIn=d.findElement(signinBtn);
		signIn.click();
		try
		{
			String errorMsgForPwd=d.findElement(pwdErrorMsg_By).getText();
			System.out.println(errorMsgForPwd);
			if(errorMsgForPwd.contains("Your password is incorrect"))
			{
				System.out.println("Incorrect Password");
			}
		}
		catch(Exception a)
		{

		}

		d.findElement(pwd_By).sendKeys(passwordR);
		d.findElement(signinBtn).click();
		String header=d.getTitle();
		if(header.contains("Amazon.com")) 
		{
			System.out.println("Home page header validated");
		}

	}

	//Below method is used to login with correct username and password
	public void loginWithCorrectDetails(String usernameR,String passwordR){


		WebElement email=d.findElement(email_By);
		email.sendKeys(usernameR);
		WebElement continued=d.findElement(coninue_By);
		continued.click();


		WebElement pwd=d.findElement(pwd_By);
		pwd.sendKeys(passwordR);
		WebElement signIn=d.findElement(signinBtn);
		signIn.click();

		String header=d.getTitle();
		if(header.contains("Amazon.com")) 
		{
			System.out.println("Home page header validated");
		}

	}

	//Below method is used to check basket products
	public void checkBasket()
	{
		//Click on basket
		d.findElement(By.xpath("//span[@id='nav-cart-count']")).click();
		WebElement productInBasket=d.findElement(productNameonBasket_By);
		if(productInBasket.isDisplayed())
		{
			System.out.println("Product in basket is present");
		}
		else
		{
			System.out.println("Product in basket is not present");
		}
	}

	//Below method is used to sign out
	public void signOut()
	{
		try{
			//d.findElement(clickOnAccount_By).click();
			Actions a= new Actions(d);
	          WebElement ele=d.findElement(clickOnAccount_By);
	          a.moveToElement(ele).build().perform();
			Thread.sleep(500);
			d.findElement(clickSignOut_By).click();
			System.out.println("Sign out successfully");
		}
		catch(Exception b)
		{
			System.out.println("There is issue in sign out");
		}


	}

	// Below method is used to Search for iphone x 64 gb and click on 1st product and move to product page.
	public void filterSearch()
	{

		WebElement searchBox=d.findElement(filterSearch_By);
		searchBox.sendKeys("iphone x 64 gb");
		d.findElement(clickOnGo_By).click();
		d.findElement(searchedProduct_By).click();

		// 6.Capture product name and price and add 1 quantity to basket
		String productName=d.findElement(productTitle_By).getText();
		System.out.println(productName);
		String productPrice=d.findElement(productPrice_By).getText();
		System.out.println(productPrice);
		Select slt=new Select(d.findElement(By.id("quantity")));
		slt.selectByValue("1");
		d.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();

		// 7.Validate product is added into basket on product page
		String productadded=d.findElement(productAddedProductpage_By).getText();
		if(productadded.contains("Added to Cart"))
		{
			System.out.println("Product added successfully");
		}

		//8.Vaidate product price and name on basket page.
		//Click on basket
		d.findElement(By.xpath("//span[@id='nav-cart-count']")).click();
		//get name from basket
		String productNameonBasket=d.findElement(productNameonBasket_By).getText();
		System.out.println(productNameonBasket);
		String productPriceonBasket=d.findElement(productPriceonBasket_By).getText();
		System.out.println(productPriceonBasket);
		if(productName.equals(productNameonBasket) && productPrice.equals(productPriceonBasket))
		{
			System.out.println("Product Name and Price are same on both page");
		}
	}



}	



