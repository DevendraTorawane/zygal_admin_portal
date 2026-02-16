package Test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import Utility.browserLaunch;
import Utility.parameterization;

  //Base class for setting up and tearing down tests (e.g., launching and closing browser).
public class baseTest
{
	public static WebDriver driver;

	@BeforeClass (alwaysRun=true)
	public void setUp()
	{
 driver = browserLaunch.openBrowser();
	}

	@DataProvider(name="loginData")
	public Object[][] loginData() throws Exception
	{
	    return new Object[][]
	    {
	        {
	        	parameterization.getData("Sheet1", 0, 0),
	        	parameterization.getData("Sheet1", 0, 1)
	        }
	    };
	}

/*
	public LoginPage launchApplication() throws IOException {
		LoginPage loginPage = new LoginPage(driver);
		driver = setUp();
		return loginPage;
	}
*/
/*	@AfterClass
    public void tearDown()
	{
        if (driver != null) {
            driver.quit();  // ✅ ensures browser closes after each test
        }
    } */

}
