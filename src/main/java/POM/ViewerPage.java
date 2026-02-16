package POM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Contains web elements and actions for the viewer-related page.
public class ViewerPage {

	 private WebDriver driver;
	    private WebDriverWait wait;

	    public ViewerPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 🔥 FIX
	        PageFactory.initElements(driver, this);
	    }


	@FindBy(xpath = "//*[@id=\"nav-list-holder\"]/ul/li[1]/div")
	WebElement menuPage;

	@FindBy(xpath = "/html/body/div[2]/section/main/section/div/aside/div/div/ul/li[2]/div")
	WebElement profileManagement;

	@FindBy(xpath = "/html[1]/body[1]/div[2]/section[1]/main[1]/section[1]/div[1]/div[1]/div[2]/ul[1]/li[2]")
	WebElement addeditviewer;

	@FindBy(xpath = "/html[1]/body[1]/div[2]/section[1]/main[1]/section[1]/div[1]/main[1]/div[1]/div[1]/section[1]/main[1]/div[1]/div[2]/section[1]/main[1]/p[1]")
	WebElement addViewer;

	@FindBy(xpath = "/html/body/div[2]/section/main/section/div/main/div[1]/div/section/div[2]/div/div/section/div/div/section/main/div/div/form/div[1]/div/div/input")
	WebElement firstnameField;

	@FindBy(xpath = "/html/body/div[2]/section/main/section/div/main/div[1]/div/section/div[2]/div/div/section/div/div/section/main/div/div/form/div[2]/div/div/input")
	WebElement lastnameField;

	@FindBy(xpath = "/html/body/div[2]/section/main/section/div/main/div[1]/div/section/div[2]/div/div/section/div/div/section/main/div/div/form/div[3]/div/div/input")
	WebElement emailFieldEntry;

	@FindBy(xpath = "/html/body/div[2]/section/main/section/div/main/div[1]/div/section/div[2]/div/div/section/div/div/section/main/div/div/form/div[4]/div/div/input")
	WebElement contactField;

	@FindBy(xpath = "/html/body/div[2]/section/main/section/div/main/div[1]/div/section/div[2]/div/div/section/div/div/section/main/div/div/form/div[5]/div/div/div/div[2]/div[1]/input")
	WebElement roleField;

	By roleSelect = By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[1]");

	@FindBy(xpath = "/html/body/div[2]/section/main/section/div/main/div[1]/div/section/div[2]/div/div/section/div/div/section/main/div/div/form/div[6]/div/div/label[1]/span[1]/span")
	WebElement AccessRights;

	@FindBy(xpath = "/html/body/div[2]/section/main/section/div/main/div[1]/div/section/div[2]/div/div/section/div/div/section/footer/div/div[1]/button/span/span")
	WebElement submitButtonForm;

	@FindBy(xpath = "/html/body/div[2]/section/main/section/div/main/div[1]/div/section/div[2]/div/div/header/button/i")
	WebElement closeAddViewerPage;

	@FindBy(xpath = "//div[contains(@class,'el-notification__content')]//p")
	WebElement errorMessage;

	By successMessage = By.xpath("//p[contains(text(),'Viewers')]");


	//-------------------------------------------------------------------------------------------------------------------------------//


	private void waitForElementToAppearWebElement(WebElement element) {
	    new WebDriverWait(driver, Duration.ofSeconds(10))
	        .until(ExpectedConditions.visibilityOf(element));
	}

	private void clickField(WebElement element) {
	    new WebDriverWait(driver, Duration.ofSeconds(10))
	        .until(ExpectedConditions.elementToBeClickable(element)).click();

	}

	private void waitForElementToInvisibleWeb(WebElement element)
	{
		new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(element));
	}

	private void clearField(WebElement element)
	{
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOf(element));
		element.clear();
	}


	public void waitForElementToAppearBy(By findBy) {

		WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToInvisibleBy(By findBy) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}



	public void clickFieldBy(By web) {
		wait.until(ExpectedConditions.elementToBeClickable(web)).click();
	}



//-------------------------------------------------------------------------------------------------------------------------------//


	public void gotoAddViewer() throws InterruptedException {
		waitForElementToAppearWebElement(menuPage);
		clickField(profileManagement);
		clickField(addeditviewer);
		clickField(addViewer);

	}

	public String submitEmptyForm() throws InterruptedException {
		// System.out.println("Test: Submitting form without entering data");
		gotoAddViewer();
		clickField(submitButtonForm);
		waitForElementToAppearWebElement(errorMessage);
		String message = errorMessage.getText();
		waitForElementToInvisibleWeb(errorMessage);
		clickField(closeAddViewerPage);
		return message;
	}

	public String testInvalidEmailFormat(String firstname, String lastname, String email, String contact)
			throws InterruptedException {
		// System.out.println("Test: Invalid email format check");

		clickField(addViewer);
		clearField(firstnameField);
		clearField(lastnameField);
		clearField(emailFieldEntry);
		clearField(contactField);

		firstnameField.sendKeys(firstname);
		lastnameField.sendKeys(lastname);
		emailFieldEntry.sendKeys(email); // invalid format
		contactField.sendKeys(contact);

		clickField(submitButtonForm);
	//	waitForTwoSeconds(); // allow toast to appear

		// ✅ Get and print error message immediately
		String errorMsg = getErrorValidationMessage();

		// ✅ Wait until message disappears
		waitForElementToInvisibleWeb(errorMessage);

		waitForElementToAppearWebElement(closeAddViewerPage);
		clickField(closeAddViewerPage);

		return errorMsg;
	}

	public void addViewer(String firstname, String lastname, String email, String Contact) {
		// System.out.println("Inside addViewer method ");

		clickField(addViewer);
		clearField(firstnameField);
		clearField(lastnameField);
		clearField(emailFieldEntry);
		clearField(contactField);

		// Fill fields with data
		firstnameField.sendKeys(firstname);
		lastnameField.sendKeys(lastname);
		emailFieldEntry.sendKeys(email);
		contactField.sendKeys(Contact);

		clickField(roleField);

		waitForElementToAppearBy(roleSelect);
		clickFieldBy(roleSelect);

		clickField(AccessRights);
		clickField(submitButtonForm);
		waitForElementToAppearBy(successMessage);

	}

	public String getErrorValidationMessage() {
		waitForElementToAppearWebElement(errorMessage);
		String errorValidationText = errorMessage.getText();
		waitForElementToInvisibleWeb(errorMessage);
		return errorValidationText;

	}

	public String testInvalidContactFormat(String string, String string2, String string3, String string4)
			throws InterruptedException {
		// System.out.println("Test: testInvalidContactFormat ");

		clickField(addViewer);
		clearField(firstnameField);
		clearField(lastnameField);
		clearField(emailFieldEntry);
		clearField(contactField);

		firstnameField.sendKeys(string);
		lastnameField.sendKeys(string2);
		emailFieldEntry.sendKeys(string3); // invalid format
		contactField.sendKeys(string4);

		clickField(submitButtonForm);
//		waitForTwoSeconds(); // allow toast to appear

		// ✅ Get and print error message immediately
		String contactErrorMsg = getErrorValidationMessage();
		// System.out.println("❌ Invalid Email Error: " + contactErrorMsg);

		// ✅ Wait until message disappears
		waitForElementToInvisibleWeb(errorMessage);

		waitForElementToAppearWebElement(closeAddViewerPage);
		clickField(closeAddViewerPage);

		return contactErrorMsg;

	}

}
