package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC02_LoginTestDDT extends BaseClass{

	@Test(dataProvider  = "LoginData",dataProviderClass =DataProviders.class,groups = {"DataDriven"})
	public void verify_LoginAccount(String email,String pwd,String exp)
	{
		logger.info("****TC02_LoginTest Started....****");
		try {
		HomePage hm=new HomePage(driver);
		logger.info("Click MyAccount");
		hm.clickMyaccount();
		logger.info("Click Login");
		hm.clickLogin();
		
		logger.info("Enter Customer Details");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLoginButton();
		
		logger.info("Validating pageTest");
		MyAccountPage mp=new MyAccountPage(driver);
		boolean targetPage=mp.verifyLogin();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				mp.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				mp.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****TC02_LoginTest finished....****");
	}
}
