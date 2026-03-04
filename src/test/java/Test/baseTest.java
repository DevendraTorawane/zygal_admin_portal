package Test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import Utility.browserLaunch;
import Utility.parameterization;

// Base class for setup and teardown
public class baseTest {

    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = browserLaunch.openBrowser();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();   // Ensures browser closes properly
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws Exception {

        return new Object[][]{
                {
                        parameterization.getData("Sheet1", 0, 0),
                        parameterization.getData("Sheet1", 0, 1)
                }
        };
    }
}