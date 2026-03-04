package POM;

import java.time.Duration;
import java.util.List;

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

/*
 * ============================================================
 *  GroupsAddGroups POM Class
 *  Handles:
 *      - Add Group
 *      - Add Device to Group
 *      - Remove Device from Group
 * ============================================================
 */

public class GroupsAddGroups {

    // ============================================================
    // DRIVER & WAIT
    // ============================================================

    private WebDriver driver;
    private WebDriverWait wait;

    private By successNotification =
            By.xpath("//div[contains(@class,'el-notification')]");

    public GroupsAddGroups(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ============================================================
    // NAVIGATION ELEMENTS
    // ============================================================

    @FindBy(id = "ic3")
    private WebElement deviceManagement;

    @FindBy(xpath = "//li[normalize-space()='Groups']")
    private WebElement groupsMenu;

    // ============================================================
    // ADD GROUP ELEMENTS
    // ============================================================

    @FindBy(xpath = "//div[@class='el-input el-input--suffix']//input[@class='el-input__inner']")
    private WebElement groupName;

    @FindBy(xpath = "//div[@class='el-textarea el-input--suffix']//textarea")
    private WebElement groupDescription;

    @FindBy(id = "addGroup_saveBtn")
    private WebElement saveGroupButton;

    private By groupAddedToast =
            By.xpath("//*[contains(text(),'Group added successfully')]");
    // ============================================================
    // ADD / REMOVE DEVICE ELEMENTS
    // ============================================================

    @FindBy(xpath = "//*[@id='addRemoveDevice_id']")
    private WebElement addRemoveDeviceButton;

    @FindBy(xpath = "//input[@placeholder='Select Device']")
    private WebElement selectDeviceDropdown;

    @FindBy(xpath = "//label[text()='Select Groups']/following::div[contains(@class,'el-select')][1]")
    private WebElement selectGroupDropdown;

    
  
    
    @FindBy(xpath = "//*[@id='add_device_Btnid']")
    private WebElement addDeviceButton;

 
    @FindBy(xpath = " //*[@id='remove_btnDeviceid']")
    private WebElement removeDeviceButton;

    @FindBy(xpath = "//p[text()='Devices assigned successfully']")
    private WebElement deviceAssignSuccessMessage;

    @FindBy(xpath = "//p[text()='Devices removed successfully']")
    private WebElement deviceRemoveSuccessMessage;

    // Scoped dropdown options (IMPORTANT FIX)
    private By firstDeviceOption = By.xpath(
            "(//input[@placeholder='Select Device']/ancestor::div[contains(@class,'el-select')]//following-sibling::div//li[contains(@class,'el-select-dropdown__item') and not(contains(@class,'is-disabled'))])[1]"
    );

    private By firstGroupOption = By.xpath(
            "(//label[text()='Select Groups']/following::div[contains(@class,'el-select')][1]//following-sibling::div//li[contains(@class,'el-select-dropdown__item') and not(contains(@class,'is-disabled'))])[1]"
    );

    private By firstDeviceCheckbox = By.xpath(
            "//main[@id='addRemoveDeviceMainTableHolder']//tbody/tr[1]/td[3]//span[contains(@class,'el-checkbox__inner')]"
    );
    // ============================================================
    // For Delete Groups METHODS
    // ===========================================================
  /*  private By deleteIcons =
            By.xpath("//button[contains(@id,'btn_grpDelete_id_')]");
    
    
    
    @FindBy(xpath="//*[@id=\"deleteGroup_deleteBtn\"]")
    private WebElement deleteButton;
    
    @FindBy(xpath="//*[@id=\"el-popover-636\"]/div[1]/div/button[2]")
    private WebElement yesButton;
    @FindBy(xpath="//*[@id=\"mainBodyHolder\"]/div[13]")
    private WebElement deleteGroupSuccessMessage;
    
    */
    
    
 // Delete icon on home page
    private By deleteIcon =
            By.xpath("//button[contains(@id,'btn_grpDelete_id_')]");

    // Delete button in popup
    private By deleteButton =
            By.id("deleteGroup_deleteBtn");

    // Success message (better than absolute div[15])
    private By deleteGroupSuccessMessage =
            By.xpath("//*[contains(text(),'successfully')]");
    // ============================================================
    // NAVIGATION METHODS
    // ============================================================

    public void navigateToGroupsPage() {
        wait.until(ExpectedConditions.visibilityOf(deviceManagement)).click();
        wait.until(ExpectedConditions.visibilityOf(groupsMenu)).click();
    }

    // ============================================================
    // ADD GROUP METHODS
    // ============================================================

    private void clickAddGroupButton() {
        waitForNotificationToDisappear();
        By addGroupBtn = By.id("addGroup_id");
        wait.until(ExpectedConditions.elementToBeClickable(addGroupBtn)).click();
    }

    public void addGroup(String name, String description) {

        clickAddGroupButton();

        wait.until(ExpectedConditions.visibilityOf(groupName)).clear();
        groupName.sendKeys(name);

        wait.until(ExpectedConditions.visibilityOf(groupDescription)).clear();
        groupDescription.sendKeys(description);

        wait.until(ExpectedConditions.elementToBeClickable(saveGroupButton)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(groupAddedToast));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(groupAddedToast));
    }

    public void addSingleGroup() {
        addGroup("Devendra", "Testing Team in Testing Group");
       
    }

    // ============================================================
    // ADD / REMOVE DEVICE FLOW
    // ============================================================

    private void openAddRemovePopupIfClosed()
    {
 /*       if (driver.findElements(By.xpath("//input[@placeholder='Select Device']")).isEmpty()) 
        {
            wait.until(ExpectedConditions.elementToBeClickable(addRemoveDeviceButton)).click();
        } */
    }

    private void selectFirstDeviceGroupAndCheckbox() {

    	System.out.println("selectFirstDeviceGroupAndCheckbox()");
        openAddRemovePopupIfClosed();
     
        Actions actions = new Actions(driver);

        
        wait.until(ExpectedConditions.elementToBeClickable(addRemoveDeviceButton)).click();
        // Select Device
        wait.until(ExpectedConditions.elementToBeClickable(selectDeviceDropdown)).click();
        WebElement device = wait.until(ExpectedConditions.visibilityOfElementLocated(firstDeviceOption));
        actions.moveToElement(device).click().perform();

        // Select Group
        wait.until(ExpectedConditions.elementToBeClickable(selectGroupDropdown)).click();
        WebElement group = wait.until(ExpectedConditions.visibilityOfElementLocated(firstGroupOption));
        actions.moveToElement(group).click().perform();

        // Select Checkbox
        wait.until(ExpectedConditions.elementToBeClickable(firstDeviceCheckbox)).click();
    }

    public void addDevice() {

    	System.out.println("**********1************");
        selectFirstDeviceGroupAndCheckbox();
        System.out.println("***********2***********");
        wait.until(ExpectedConditions.elementToBeClickable(addDeviceButton)).click();
        System.out.println("**********3************");
        wait.until(ExpectedConditions.visibilityOf(deviceAssignSuccessMessage));
        System.out.println("************4**********");
        wait.until(ExpectedConditions.invisibilityOf(deviceAssignSuccessMessage));
        System.out.println("**********5************");
    }

    public void removeDevice() {

        selectFirstDeviceGroupAndCheckbox();

        wait.until(ExpectedConditions.elementToBeClickable(removeDeviceButton)).click();

        wait.until(ExpectedConditions.visibilityOf(deviceRemoveSuccessMessage));
        wait.until(ExpectedConditions.invisibilityOf(deviceRemoveSuccessMessage));
    }

    // ============================================================
    // EXCEL BULK GROUP ADD
    // ============================================================

    public void add300GroupsFromExcel() throws Exception {

        navigateToGroupsPage();

        int rows = parameterization.getRowCount("AddGroupData");

        for (int i = 1; i <= rows; i++) {

            String name = parameterization.getData("AddGroupData", i, 0);
            String desc = parameterization.getData("AddGroupData", i, 1);

            addGroup(name, desc);

            Assert.assertTrue(((WebElement) groupAddedToast).isDisplayed(),
                    "Group not added: " + name);
        }
    }

 // Validate Group Created
    public boolean isGroupCreated(String groupName) {
        return driver.findElements(
                By.xpath("//td[normalize-space()='" + groupName + "']")
        ).size() > 0;
    }
    
    
    
    
    // Validate Device Added
    public boolean isDeviceAdded() {
        try {
            return deviceAssignSuccessMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Validate Device Removed
    public boolean isDeviceRemoved() {
        try {
            return deviceRemoveSuccessMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
    
    // ============================================================
    // UTILITY
    // ============================================================

    private void waitForNotificationToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(successNotification));
        } catch (Exception e) {
            System.out.println("No notification present.");
        }
    }
    
    
    
    public void deleteAllGroups() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        while (driver.findElements(deleteIcon).size() > 0) {

            // 1️⃣ Click first delete icon
            wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();

            // 2️⃣ Click delete button
            wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();

            // 3️⃣ Wait for success message visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(deleteGroupSuccessMessage));

            // 4️⃣ Wait for success message invisible
            wait.until(ExpectedConditions.invisibilityOfElementLocated(deleteGroupSuccessMessage));

            // Small pause to allow DOM refresh (optional but stable)
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All groups deleted successfully");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
