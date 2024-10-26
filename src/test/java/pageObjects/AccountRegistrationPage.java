package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtCnfpassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chkdPolicy;
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation ;
	
	public void setFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephone)
	{
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String cnfmpwd)
	{
		txtCnfpassword.sendKeys(cnfmpwd);
	}
	
	public void setPrivacyPolicy()
	{
		chkdPolicy.click();
	}
	
	public void clickBtnContinue()
	{
		btnContinue.click();
	}
	
	public String getConfirmMessage()
	{
		try {
		return msgConfirmation.getText();
		}catch(Exception e)
		{
			return e.getMessage();
		}
	}
}