package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	
	@BeforeClass(groups = {"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setUp(String os,String browser) throws IOException
	{
		FileReader file=new FileReader("C:\\Users\\Ravi Yadav\\eclipse-workspace1\\OpenCartPractice\\src\\test\\resources\\opj.properties");
		prop=new Properties();
		prop.load(file);
		logger=LogManager.getLogger(this.getClass());
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities ds=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows"))
			{
				ds.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				ds.setPlatform(Platform.LINUX);
			}
			
			else if(os.equalsIgnoreCase("mac"))
			{
				ds.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("Invalid os");
			}
			
			switch(browser.toLowerCase())
			{
			case "chrome":ds.setBrowserName("chrome");break;
			case "edge":ds.setBrowserName("MicrosoftEdge");break;
			case "firefox":ds.setBrowserName("firefox");break;
			default:System.out.println("Invalid browser name");return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),ds);
		}
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
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
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master","DataDriven"})
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
	
	
	public String captureScreen(String tname)
	{
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.hhmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\Ss\\"+tname+" "+timeStamp+".png";
		File targetfile=new File(targetFilePath);
		source.renameTo(targetfile);
		return targetFilePath;
	}
}
