package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginPageTest extends BaseClass{

	@Test(groups = {"Regression","Master"})
	public void verify_LoginAccount()
	{
		logger.info("****TC02_LoginTest Started....****");
		HomePage hm=new HomePage(driver);
		logger.info("Click MyAccount");
		hm.clickMyaccount();
		logger.info("Click Login");
		hm.clickLogin();
		
		logger.info("Enter Customer Details");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLoginButton();
		
		logger.info("Validating pageTest");
		MyAccountPage mp=new MyAccountPage(driver);
		boolean ret=mp.verifyLogin();
		if(ret==true)
		{
			mp.clickLogout();
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("test failed");
			logger.debug("test debug");
			Assert.fail();
		}
		
		logger.info("****TC02_LoginTest finished....****");
	}
}
