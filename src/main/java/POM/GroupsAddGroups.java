package POM;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utility.parameterization;

// Contains web elements and actions for the viewer-related page.
public class GroupsAddGroups {

	    	 WebDriver driver;
	    	 WebDriverWait wait;
	    	 private By successNotification =
	    		        By.xpath("//div[contains(@class,'el-notification')]");

	    		public GroupsAddGroups(WebDriver driver)
	    		{
	    			this.driver=driver;
	    			this.wait=new WebDriverWait(driver,Duration.ofSeconds(15));
	    			PageFactory.initElements(driver, this);
	    		}

	    		@FindBy(xpath="//img[@id=\"ic3\"]")
	    		WebElement Device_Managent;

	    		@FindBy(xpath="//li[normalize-space()='Groups']")
	    		WebElement Groups;

	    	//	@FindBy(xpath="//p [@id=\"addGroup_id\"]")
	    //		WebElement Add_Groups;

	    		@FindBy(xpath="//div[@class=\"el-input el-input--suffix\"]//input [@class=\"el-input__inner\"]")
	    		WebElement GroupName;

	    		@FindBy(xpath="//div[@class=\"el-textarea el-input--suffix\"]//textarea")
	    		WebElement GroupDescription;


	    		@FindBy(xpath="//button[@id=\"addGroup_saveBtn\"]")
	    		WebElement SaveGroups;


	    		@FindBy(xpath="//p[normalize-space()='Group added successfully']")
	    		WebElement GroupAddedSuccessfully;

	    		//----------------------------------------------------------------------
	    		@FindBy(xpath="// p [@id=\"addRemoveDevice_id\"]")
	    		WebElement addRemoveDevice;

	    		/*		@FindBy(xpath = "//input[@placeholder='Select Device']")
	    		WebElement selectDeviceDropDown;

	    		By firstDeviceOption =
	    			    By.xpath("(//div[contains(@class,'el-select-dropdown') and not(contains(@style,'display: none'))]//li[contains(@class,'el-select-dropdown__item')])[1]");


	    		@FindBy(xpath="//input[@placeholder='Select Group']")
	    		WebElement selectGroup;

	    		@FindBy(xpath="(//div[contains(@class,'el-select-dropdown') and not(contains(@style,'display: none'))]\r\n"+ " //li[contains(@class,'el-select-dropdown__item')])[1]")
	    		WebElement selectedGroup;
	    		*/


	    		// ===== Drawer =====



	    		// ===== Select Device =====
	    		@FindBy(xpath = "//input[@placeholder='Select Device']")
	    		WebElement selectDeviceDropdown;

	    		By firstDeviceOption = By.xpath(
	    		        "(//div[contains(@class,'el-popper') and contains(@x-placement,'bottom')]//li[contains(@class,'el-select-dropdown__item') and not(contains(@class,'is-disabled'))])[1]"
	    		);


	    		// ===== Select Group (container, NOT input) =====
	    		@FindBy(xpath = "//label[text()='Select Groups']/following::div[contains(@class,'el-select')][1]")
	    		WebElement selectGroupDropdown;

	    		By firstGroupOption = By.xpath(
	    		        "(//div[contains(@class,'el-popper') and contains(@x-placement,'bottom')]//li[contains(@class,'el-select-dropdown__item') and not(contains(@class,'is-disabled'))])[1]"
	    		);


	    		// ===== Device Checkbox =====
	    		By firstDeviceCheckbox = By.xpath(
	    		        "(//div[@id='addRemoveDeviceMainTableHolder']//tbody//label[contains(@class,'el-checkbox')])[1]"
	    		);


	    		public void selectFirstDeviceGroupAndDevice() {

	    		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    		    Actions actions = new Actions(driver);

	    		    // Open Add / Remove Device popup
	    		    wait.until(ExpectedConditions.elementToBeClickable(addRemoveDevice)).click();
	    		    System.out.println("addRemoveDevice clicked");

	    		    // ===== Select first Device =====
	    		    wait.until(ExpectedConditions.elementToBeClickable(selectDeviceDropdown)).click();
	    		    System.out.println("selectDeviceDropdown clicked");

	    		    WebElement firstDevice = wait.until(
	    		            ExpectedConditions.visibilityOfElementLocated(firstDeviceOption)
	    		    );
	    		    System.out.println("firstDeviceOption visible");

	    		    actions.moveToElement(firstDevice)
	    		           .pause(Duration.ofMillis(200))
	    		           .click()
	    		           .perform();
	    		    System.out.println("firstDeviceOption clicked");

	    		    // ===== Select first Group =====
	    		    wait.until(ExpectedConditions.elementToBeClickable(selectGroupDropdown)).click();
	    		    System.out.println("selectGroupDropdown clicked");

	    		    WebElement firstGroup = wait.until(
	    		            ExpectedConditions.visibilityOfElementLocated(firstGroupOption)
	    		    );

	    		    actions.moveToElement(firstGroup)
	    		           .pause(Duration.ofMillis(200))
	    		           .click()
	    		           .perform();
	    		    System.out.println("firstGroupOption clicked");

	    		    // ===== Select first Device checkbox =====
	    		    wait.until(ExpectedConditions.elementToBeClickable(firstDeviceCheckbox)).click();
	    		    System.out.println("firstDeviceCheckbox clicked");
	    		}



	    		public void clickAddGroupButton() {

	    		    // ✅ wait for toast / notification to go away
	    		    waitForNotificationToDisappear();

	    		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    		    By addGroupBtn = By.xpath("//p[@id='addGroup_id']");

	    		    wait.until(ExpectedConditions.elementToBeClickable(addGroupBtn));
	    		    driver.findElement(addGroupBtn).click();
	    		}

				public void MoveTilladdGroup() {


					wait.until(ExpectedConditions.visibilityOf(Device_Managent));
				    Device_Managent.click();

				    wait.until(ExpectedConditions.visibilityOf(Groups));
				    Groups.click();

			//	    wait.until(ExpectedConditions.visibilityOf(Add_Groups));
			//	    Add_Groups.click(); replaced this with    clickAddGroupButton();

	/*			    clickAddGroupButton();

				    wait.until(ExpectedConditions.visibilityOf(GroupName));
				    GroupName.clear();
				    GroupName.sendKeys(groupName);

				    wait.until(ExpectedConditions.visibilityOf(GroupDescription));
				    GroupDescription.clear();
				    GroupDescription.sendKeys(description);

				    wait.until(ExpectedConditions.visibilityOf(SaveGroups));
				    SaveGroups.click();

				    wait.until(ExpectedConditions.visibilityOf(GroupAddedSuccessfully));
				    */
				}

				public void addGroup(String groupName, String description)
				{
					clickAddGroupButton();

					 	wait.until(ExpectedConditions.visibilityOf(GroupName));
					    GroupName.clear();
					    GroupName.sendKeys(groupName);

					    wait.until(ExpectedConditions.visibilityOf(GroupDescription));
					    GroupDescription.clear();
					    GroupDescription.sendKeys(description);

					    wait.until(ExpectedConditions.visibilityOf(SaveGroups));
					    SaveGroups.click();

					    wait.until(ExpectedConditions.visibilityOf(GroupAddedSuccessfully));

				}

				public void waitForNotificationToDisappear() {
				    try {
				        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				        wait.until(ExpectedConditions.invisibilityOfElementLocated(successNotification));
				        System.out.println("Notification disappeared");
				    } catch (Exception e) {
				        System.out.println("No notification present");
				    }
				}

				public void add300GroupsFromExcel() throws Exception
				{

					System.out.println(new File("path_here").getAbsolutePath());

				    GroupsAddGroups groups = new GroupsAddGroups(driver);
				    groups.MoveTilladdGroup();

				    int rows = parameterization.getRowCount("AddGroupData"); // Sheet2 = Groups

				    for (int i = 1; i <= rows; i++)
				    {

				        String groupName = parameterization.getData("AddGroupData", i, 0);
				        String description = parameterization.getData("AddGroupData", i, 1);

				        groups.addGroup(groupName, description);

				        Assert.assertTrue(groups.GroupAddedSuccessfully.isDisplayed(),"Group not added: " + groupName);
				        System.out.println("add300GroupsFromExcel() executed successfully");
				    }
				}


		/*		public void addRemoveDevice() {

				    wait.until(ExpectedConditions.elementToBeClickable(addRemoveDevice)).click();

				    wait.until(ExpectedConditions.elementToBeClickable(selectDeviceDropDown)).click();
				    System.out.println("=============> 1");

				    WebElement firstOption = wait.until(
				        ExpectedConditions.visibilityOfElementLocated(firstDeviceOption)
				    );

				    // Extra safety for Element-UI
				    wait.until(ExpectedConditions.elementToBeClickable(firstOption)).click();
				    System.out.println("2");

				    wait.until(ExpectedConditions.elementToBeClickable(selectGroup)).click();
				    System.out.println("3");

				    wait.until(ExpectedConditions.elementToBeClickable(selectedGroup)).click();
				    System.out.println("4");
				}
*/





}
