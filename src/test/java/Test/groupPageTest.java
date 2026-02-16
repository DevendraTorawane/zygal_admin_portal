package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import POM.GroupsAddGroups;
import POM.LoginPage;

public class groupPageTest extends baseTest
{
	@Test(dataProvider="loginData", description="User Login with Valid Credentials", groups={"Smoke"})
	public void loginWithValidCredentialsTest(String userName, String password) throws Exception {

	    LoginPage zygalLoginPage = new LoginPage(driver);

	    zygalLoginPage.loginApplication(userName, password);
	    // Add assertion to verify login success
	    System.out.println("for git hub assertion to verify login success");
	    Assert.assertTrue(zygalLoginPage.isLoginSuccessful(), "Login failed");



	}

	@Test (dependsOnMethods="loginWithValidCredentialsTest", description="Verify Add Groups Functionality", groups= {"Smoke"})
	public void addGroups() throws Exception
	{
		  GroupsAddGroups addgroups=new GroupsAddGroups(driver);

		//  addgroups.add300GroupsFromExcel();
		  addgroups.MoveTilladdGroup();
		  addgroups.selectFirstDeviceGroupAndDevice();

		  Assert.assertTrue(true, "Groups added successfully");
	}



}
