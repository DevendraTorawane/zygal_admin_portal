package Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Config.ConfigReader;
import POM.loginPage;
import Utility.parameterization;

public class loginPageTest extends baseTest {

	@BeforeMethod
	public void browserLaunch() {
		driver = Utility.browserLaunch.openBrowser();
	}

	@Test(description = "User Login with Valid Credentials")
	public void loginWithValidCredentialsTest() throws EncryptedDocumentException, IOException, InterruptedException {
		//Fetch Captch bypass key from config file
		String captchaBypassToken = ConfigReader.getProperty("captcha_token");
		
		loginPage zygalLoginPage = new loginPage(driver);
		Thread.sleep(1000);		
		// Get the user email and password from Excel sheet
		String userName = parameterization.getData("loginData", 1, 0);
		String password = parameterization.getData("loginData", 1, 1);
		
		//Split email to insert bypass key in the email
		String[] userEmailSplit = userName.split("@");
		String userEmail = userEmailSplit[0] + captchaBypassToken + "@" + userEmailSplit[1];
		
		//Send email and password to browser
		zygalLoginPage.enteruserId(userEmail);
		zygalLoginPage.enterpassword(password);
		zygalLoginPage.enterCaptcha();
		zygalLoginPage.ClickOnSubmit();
	}

}
