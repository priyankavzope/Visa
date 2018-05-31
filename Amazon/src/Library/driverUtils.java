package Library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
public class driverUtils {
	 public static  WebDriver _driver;

	    @BeforeSuite(alwaysRun = true)
	    public void setUp() {
	    	System.setProperty("webdriver.gecko.driver", "C:\\Users\\geckodriver.exe");
	        _driver = new FirefoxDriver();
	    }

	    public static WebDriver getDriver() {
	        if ( _driver == null) {
	        	System.setProperty("webdriver.gecko.driver", "C:\\Users\\geckodriver.exe");
	            _driver = new FirefoxDriver();
	        }
	        return _driver;
	    }

	    @AfterSuite(alwaysRun = true)
	    public void tearDown() throws Exception {
	        _driver.close();
	        _driver.quit();
	    }
	}

