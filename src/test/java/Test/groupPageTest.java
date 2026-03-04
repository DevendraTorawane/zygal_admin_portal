package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import POM.GroupsAddGroups;
import POM.LoginPage;

public class groupPageTest extends baseTest {

	public   GroupsAddGroups groupPage;
	
    @Test(
            dataProvider = "loginData",
            description = "User Login with Valid Credentials",
            groups = {"Smoke"}
    )
    public void loginWithValidCredentialsTest(String userName, String password) throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginApplication(userName, password);

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login failed - User not redirected properly"
        );
    }
   @Test(
            dependsOnMethods = "loginWithValidCredentialsTest",
            description = "Verify Add Single Group",
            groups = {"Smoke"}
    )
    public void addSingleGroupTest() {

        groupPage = new GroupsAddGroups(driver);

        groupPage.navigateToGroupsPage();
        groupPage.addSingleGroup();

   //     Assert.assertTrue(groupPage.isGroupCreated(null),"Single Group was not created successfully");
    }
   
  
    
    
    @Test( dependsOnMethods = "addSingleGroupTest",   // ✅ Correct dependency
    	    description ="Verify Group Delete",
    	    groups= {"Smoke"})
    	public void deleteGroups() {
System.out.println("i am at deleteGroups()");
    	groupPage = new GroupsAddGroups(driver);
  //      groupPage.navigateToGroupsPage();   // ✅ Add this
        groupPage.deleteAllGroups();
        
      System.out.println("Group deleted Successfully");
    	}
/*    @Test(
            dependsOnMethods = "loginWithValidCredentialsTest",
            description = "Verify Add Single Group",
            groups = {"Smoke"}
    )
    public void addMultipleGroupTest() throws Exception {

        GroupsAddGroups groupPage = new GroupsAddGroups(driver);

        groupPage.navigateToGroupsPage();
        groupPage.add300GroupsFromExcel();

        Assert.assertTrue(groupPage.isGroupCreated(),"Multiple groups  created successfully");
    }
 */   
    
    

  /*  
    
    @Test(
            dependsOnMethods = "addSingleGroupTest",
            description = "Verify Add Device to Group",
            groups = {"Smoke"}
    )
    public void addDeviceTest() {

//        GroupsAddGroups groupPage = new GroupsAddGroups(driver);
//        groupPage.navigateToGroupsPage();
        groupPage.addDevice();

        Assert.assertTrue(groupPage.isDeviceAdded(),"Device was not added successfully");
    }

    @Test(
            dependsOnMethods = "addDeviceTest",
            description = "Verify Remove Device from Group",
            groups = {"Smoke"}
    )
    public void removeDeviceTest() {

 //       GroupsAddGroups groupPage = new GroupsAddGroups(driver);

 //       groupPage.navigateToGroupsPage();
        groupPage.removeDevice();

        Assert.assertTrue(groupPage.isDeviceRemoved(),"Device was not removed successfully");
    }
    
    
    */
}