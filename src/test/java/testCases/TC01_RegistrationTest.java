package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC01_RegistrationTest extends BaseClass {
	
	@Test
	public void verify_acc_registration()
	{
		
		logger.info("**** TC01_RestrationTest Started....****");
		HomePage hm=new HomePage(driver);
		hm.clickMyaccount();
		logger.info("MyAccount link Click");
		hm.clickRegisters();
		logger.info("Register link Click");
		
		logger.info("customer details enter");
		AccountRegistrationPage account=new AccountRegistrationPage(driver);
		account.setFirstName(genrateRandomAlphabet());
		account.setLastName(genrateRandomAlphabet());
		account.setEmail(genrateRandomAlphabet()+"@gmail.com");
		account.setTelephone(genrateRandomNumeric());
		String password=genrateRandomAlphaNumeric();
		account.setPassword(password);
		account.setConfirmPassword(password);
		account.setPrivacyPolicy();
		account.clickBtnContinue();
		logger.info("validation started");
		String cnfmmsg=account.getConfirmMessage();
		try {
		if(cnfmmsg.equals("Your Account Has Been Created!"))
		{
		logger.info("test passed");
		Assert.assertTrue(true);
		}
		else
		{
			logger.error("test failed");
			logger.debug("test debug");
			Assert.fail();
		}
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("**** TC01_RestrationTest finished....****");
	}
}
