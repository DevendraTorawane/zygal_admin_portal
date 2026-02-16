package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import POM.LoginPage;
import POM.ViewerPage;

// Tests related to the viewer functionality.
public class viewerPageTest extends baseTest
{
	@Test(dataProvider="loginData", description="User Login with Valid Credentials", groups={"positive"})
	public void loginWithValidCredentialsTest(String userName, String password) throws Exception {

	    LoginPage zygalLoginPage = new LoginPage(driver);

	    zygalLoginPage.loginApplication(userName, password);
	    // Add assertion to verify login success
	    Assert.assertTrue(zygalLoginPage.isLoginSuccessful(), "Login failed");

	    ViewerPage viewerPage = new ViewerPage(driver);
	    viewerPage.gotoAddViewer();


	}

}
