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
		HomePage hm=new HomePage(driver);
		hm.clickMyaccount();
		hm.clickRegisters();
		
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
		String cnfmmsg=account.getConfirmMessage();
		try {
		Assert.assertEquals(cnfmmsg, "Your Account Has Been Created!");
		}catch(Exception e)
		{
			Assert.fail();
		}
		
	}
}
