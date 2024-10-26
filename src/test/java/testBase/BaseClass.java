package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

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
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
	
	public String genrateRandomAlphabet()
	{
		String randomAlphabet=RandomStringUtils.randomAlphabetic(5);
		return randomAlphabet;
	}
	
	public String genrateRandomNumeric()
	{
		String randomNumber=RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}
	
	public String genrateRandomAlphaNumeric()
	{
		String rA=RandomStringUtils.randomAlphabetic(2);
		String rN=RandomStringUtils.randomNumeric(6);
		return rA+"@"+rN;
	}
}
