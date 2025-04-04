package POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Config.ConfigReader;

public class loginPage {
	private WebDriver driver;

	@FindBy(xpath = "//input[@id='user_email_login']")
	private WebElement userId;
	@FindBy(xpath = "//input[@id='user_password_login']")
	private WebElement password;
	@FindBy(xpath = "//input[@id='captcha_input_login']")
	private WebElement captcha;
	@FindBy(xpath = "//button[@id='submit_login_btn']")
	private WebElement submit;
	@FindBy(xpath = "//a[text()='user_forgot_password_btn']")
	private WebElement forgot_password;
	@FindBy(xpath = "//a[text()=\"Don't have an account? Signup now!\"]")
	private WebElement signup;
	@FindBy(xpath = "//div[@role='alert']//div[contains(@class,'el-notification__content')]")
	private WebElement getError;

	public loginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enteruserId(String user) {
		userId.sendKeys(user);
	}

	public void enterpassword(String pass) {
		password.sendKeys(pass);
	}

	public void enterCaptcha() {
		String captchaKey = ConfigReader.getProperty("captcha");
		captcha.sendKeys(captchaKey);
	}

	public void ClickOnSubmit() {
		submit.click();
	}

	public void ClickOnforgot() {
		forgot_password.click();
	}

	public void ClickOnSignup() {
		signup.click();
	}

	public String getErrorText() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(getError));

			String errorMessage = getError.getText();
			return errorMessage;
		} catch (Exception e) {
			return "No error message displayed";
		}
	}

}
