package Utility; // Changed from POJO to utilities

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
   //Handles WebDriver initialization, browser setup.
public class browserLaunch {
	public static WebDriver openBrowser() {

		// Disable browser notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		// Setup ChromeDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options); // Pass options here

		// Load URL --- 
		String baseUrl = ConfigReader.getProperty("base_url");
		driver.get(baseUrl);

		// Maximize window
		driver.manage().window().maximize();

		return driver;
	}
}
