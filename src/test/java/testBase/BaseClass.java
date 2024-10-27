package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties prop;
	
	@BeforeClass
	@Parameters({"os","browser"})
	public void setUp(String os,String browser) throws IOException
	{
		FileReader file=new FileReader("C:\\Users\\Ravi Yadav\\eclipse-workspace1\\OpenCartPractice\\src\\test\\resources\\opj.properties");
		prop=new Properties();
		prop.load(file);
		logger=LogManager.getLogger(this.getClass());
		driver=new ChromeDriver();
		switch(browser.toLowerCase())
		{
		case "chrome":driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default:System.out.println("Invalid browser name");return;
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("appurl"));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
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
