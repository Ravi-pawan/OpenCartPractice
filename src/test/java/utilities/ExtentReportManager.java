package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

       public ExtentSparkReporter sparkReporter;
       public ExtentReports extent;
       public ExtentTest test;
       String repName;
public void onStart(ITestContext testContext) {
  /*SimpleDateFormat df-new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    *  Date dt=new Date();O
   String currentdatetimestamp=df.format(dt);
  */
  String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time st repName = "Test-Report-" + timeStamp + ".html";
   repName="Test-Report-"+timeStamp+".html"; 
   sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report

   sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
   sparkReporter.config().setReportName("Opencart Functional Testing");
   sparkReporter.config().setTheme (Theme.DARK);

   extent=new ExtentReports();
   extent.attachReporter (sparkReporter);
   extent.setSystemInfo("Application", "opencart"); 
   extent.setSystemInfo("Module", "Admin");
   extent.setSystemInfo("Sub Module", "Customers");
   extent.setSystemInfo("User Name", System.getProperty("user.name"));
   extent.setSystemInfo("Environement", "QA");

   String os=testContext.getCurrentXmlTest().getParameter("os");
   extent.setSystemInfo("Operating System", os);

   String browser=testContext.getCurrentXmlTest().getParameter("browser");
   extent.setSystemInfo("Operating System", browser);

   List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
   if(!includedGroups.isEmpty()) {
   extent.setSystemInfo("Groups", includedGroups.toString());
}
}
     public void  onTestSuccess(ITestResult result) {
    	   test=extent.createTest(result.getTestClass().getName());
    	   test.assignCategory(result.getMethod().getGroups()); //to display groups in report
    	   test.log(Status.PASS, result.getName()+"got successfully executed");
     }
     
     public void onTestFailure(ITestResult result) {
    	 test=extent.createTest(result.getTestClass().getName());
  	     test.assignCategory(result.getMethod().getGroups()); //to display groups in report
  	     
  	     test.log(Status.FAIL, result.getName()+"got failed");
  	     test.log(Status.INFO,result.getThrowable().getMessage());
  	     
  	     try {
  	    	 String imgPath=new BaseClass().captureScreen(result.getName());
  	    	 test.addScreenCaptureFromPath(imgPath);
  	     }catch(Exception e1)
  	     {
  	    	 e1.printStackTrace();
  	     }
  	     
     }
     
     public void onTestSkipped(ITestResult result) {
    	 test=extent.createTest(result.getTestClass().getName());
  	     test.assignCategory(result.getMethod().getGroups()); //to display groups in report
  	     
  	     test.log(Status.SKIP, result.getName()+"got skipped");
  	     test.log(Status.INFO,result.getThrowable().getMessage());
    	  }
     
     public void onFinish(ITestContext context) {
    	   extent.flush();
    	   
    	   String pathofExtentReprt=System.getProperty("user.dir")+"\\reports\\"+repName;
    	   File extentReport=new File(pathofExtentReprt);
    	   try {
    		   Desktop.getDesktop().browse(extentReport.toURI());
    	   }catch(Exception e)
    	   {
    		   e.printStackTrace();
    	   }
    	  
    	   /*
    	   try {URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
    	   
    	   //create the email message
    	   ImageHtmlEmail email=new ImageHtmlEmail();
    	   email.setDataSourceResolver(new DataSourceUrlResolver(url));
    	   email.setHostName("smtp.googleemail.com");
    	   email.setSmtpPort(465);
    	   email.setAuthenticator(new DefaultAuthenticator("useremail","password"));
    	   email.setSSLOnConnect(true);
    	   email.setFrom("useremail");//sender
    	   email.setSubject("test results");
    	   email.setMsg("please find attached report");
    	   email.addTo("recevier email");//recevier
    	   email.attach(url,"extent report","please check report...");
    	   email.send();//send the email
    	   }catch(Exception e)
    	   {
    		   e.printStackTrace();
    	   }
    	  */ 
    }
}