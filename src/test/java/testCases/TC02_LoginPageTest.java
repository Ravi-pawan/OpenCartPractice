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
		boolean msg=mp.verifyLogin();
		Assert.assertEquals(msg, true);
		logger.info("****TC02_LoginTest finished....****");
	}
}
