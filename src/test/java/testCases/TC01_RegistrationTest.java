package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC01_RegistrationTest {
	public WebDriver driver;
	
	@BeforeClass
	public void setUp()
	{
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();;
		
	}
	
	@Test
	public void verify_acc_registration()
	{
		HomePage hm=new HomePage(driver);
		hm.clickMyaccount();
		hm.clickRegisters();
		
		AccountRegistrationPage account=new AccountRegistrationPage(driver);
		account.setFirstName("Rsutuv");
		account.setLastName("khrity");
		account.setEmail("myactct@gmail.com");
		account.setTelephone("5467342812");
		account.setPassword("81881379@rat");
		account.setConfirmPassword("81881379@rat");
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

	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
}
