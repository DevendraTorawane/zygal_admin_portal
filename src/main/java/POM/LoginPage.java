package POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


 // Contains web elements and methods related to the login page.
public class LoginPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this. wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//input[@id='user_email_login']")
	private WebElement userId;
	@FindBy(xpath = "//input[@id='user_password_login']")
	private WebElement passwordField;
	@FindBy(xpath = "//input[@id='captcha_input_login']")
	private WebElement captchaField;
	@FindBy(xpath = "//button[@id='submit_login_btn']")
	private WebElement submit;
	@FindBy(xpath = "//a[text()='user_forgot_password_btn']")
	private WebElement forgot_password;
	@FindBy(xpath = "//a[text()=\"Don't have an account? Signup now!\"]")
	private WebElement signup;
	@FindBy(xpath = "//div[@role='alert']//div[contains(@class,'el-notification__content')]")
	private WebElement getError;
	@FindBy(id="ic1")
	WebElement HomeIcon;


	// Popup container (dialog / notification)
	@FindBy(xpath = "//div[contains(@class,'el-dialog') or contains(@class,'el-notification')]")
	private WebElement postLoginPopup;

	// OK / Close / Cancel button
	@FindBy(xpath = "//button//span[text()='OK' or text()='Close' or text()='Cancel']/ancestor::button")
	private WebElement popupOkBtn;


	public void handlePostLoginPopupIfPresent() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	        wait.until(ExpectedConditions.visibilityOf(postLoginPopup));

	        System.out.println("Post login popup detected");

	        popupOkBtn.click();

	        wait.until(ExpectedConditions.invisibilityOf(postLoginPopup));

	        System.out.println("Popup closed");

	    } catch (Exception e) {
	        System.out.println("No popup after login");
	    }
	}

	public void handleBrowserAlertIfPresent() {
	    try {
	        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        alertWait.until(ExpectedConditions.alertIsPresent());

	        String alertText = driver.switchTo().alert().getText();
	        System.out.println("Browser alert detected: " + alertText);

	        driver.switchTo().alert().accept();
	        System.out.println("Browser alert accepted");

	    } catch (Exception e) {
	        System.out.println("No browser alert present");
	    }
	}


	public void enteruserId(String user) {
		userId.sendKeys(user);
	}

	public void enterpassword(String pass) {
		passwordField.sendKeys(pass);
	}

	/*public void enterCaptcha() {
		String captchaKey = ConfigReader.getProperty("captcha");
		captcha.sendKeys(captchaKey);
	} */

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

			wait.until(ExpectedConditions.visibilityOf(getError));

			String errorMessage = getError.getText();
			return errorMessage;
		} catch (Exception e) {
			return "No error message displayed";
		}
	}

	// Below are Actions methods
		public void loginApplication(String email, String password) throws InterruptedException {

			System.out.println("*********** POM LoginPage loginApplication method ************");
			userId.sendKeys(email);
			passwordField.sendKeys(password);

			captchaField.click();
			System.out.println("Solve CAPTCHA manually within 20 seconds");
			Thread.sleep(15000); // human solves captcha


			submit.click();
			System.out.println("LoginPage.java submit button click ");

			// ✅ FIRST: handle browser JS alert (33333)
			handleBrowserAlertIfPresent();

			// ✅ SECOND: handle DOM popup (if any)
			handlePostLoginPopupIfPresent();

			// ✅ THEN: wait for home page
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.urlContains("adminV2"));
		}



		public boolean isLoginSuccessful() {
		    try {
		        wait.until(ExpectedConditions.visibilityOf(HomeIcon));
		        return true;
		    } catch (Exception e) {
		        System.out.println("Login failed: " + getErrorText());
		        return false;
		    }
		}






}
