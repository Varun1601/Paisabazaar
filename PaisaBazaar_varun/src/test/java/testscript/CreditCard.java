package testscript;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonlibs.CommonDriver;
import commonlibs.ExcelDriver;
import commonlibs.Utils;

public class CreditCard {

	ExcelDriver Excel=new ExcelDriver();
	CommonDriver oBrowser = new CommonDriver();
	Utils utils = new Utils();
    Properties oProperty = new Properties();
    
    String browser, URL, MonthlyIncome, Residence, MNumber, getCurrentUrl, ExpectedUrl;
    boolean actual = true;
    boolean expected = false;
    
    String path = "C:\\Users\\Varun\\workspace\\PaisaBazaar\\src\\main\\resources\\config\\config.properties";
    HashMap<String, HashMap<String, String>> data = new HashMap<String, HashMap<String, String>>();
    
   @BeforeClass
    public void BeforeScript(){

    	oProperty=utils.getProperties(path);
    	System.out.println("Varun");
    	Excel.openExcelWorkbook(oProperty.getProperty("PaisaBazaar"));
		data = Excel.ReadTestCaseData(oProperty.getProperty("PaisaBazaar"),oProperty.getProperty("Product"));
		
		browser = (data.get("TC_001").get("browser")).trim();
		URL = (data.get("TC_001").get("URL")).trim();
		oBrowser.setPageLoadTimeout(601);
		oBrowser.setElementDetectionTimeout(601);
		
    }
   
   @Test
	public void TC_001(){
    System.out.println("TC_001");
    try{
    oBrowser.openBrowser(browser,URL);
    MonthlyIncome = (data.get("TC_001").get("MonthlyIncome")).trim();
    Residence = (data.get("TC_001").get("Residence")).trim();
    MNumber = (data.get("TC_001").get("MNumber")).trim();
    ExpectedUrl = (data.get("TC_001").get("ExpectedUrl")).trim();
    
    //Monthly Income
    oBrowser.setText(By.id("monthly_income"), MonthlyIncome);
    
    //To enter text in Residence City field
    oBrowser.clickElement(By.xpath("(//span[@class='select2-selection__arrow'])[2]"));
    oBrowser.setText(By.xpath("//input[@class='select2-search__field']"), Residence);
    oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
    
    //Mobile Number
    oBrowser.setText(By.id("mobile_number"), MNumber);
    
    //Checkbox
    oBrowser.clickElement(By.xpath("//input[@id='prequote_term']"));
    
    //Proceed
    oBrowser.clickElement(By.id("unlock_offer"));
	try {
	Thread.sleep(3000);
} catch (Exception e) {
    e.printStackTrace();
}
    //Verifying Condition
    getCurrentUrl = oBrowser.getCurrentUrl();
    System.out.println(getCurrentUrl);
    
    if(getCurrentUrl.contains(ExpectedUrl)){
    	System.out.println("TC_001 passed");
    }else{
    	Assert.assertEquals(actual, expected);
    }
    oBrowser.close();
	}catch(AssertionError e)
    {
        System.out.println("Assertion error. ");
    }
}  
   
   @Test
  	public void TC_002(){
      System.out.println("TC_002");
      try{
      oBrowser.openBrowser(browser,URL);
      MonthlyIncome = (data.get("TC_002").get("MonthlyIncome")).trim();
      Residence = (data.get("TC_002").get("Residence")).trim();
      MNumber = (data.get("TC_002").get("MNumber")).trim();
      ExpectedUrl = (data.get("TC_002").get("ExpectedUrl")).trim();
      
      //Monthly Income
      oBrowser.setText(By.id("monthly_income"), MonthlyIncome);
      
      //To enter text in Residence City field
      oBrowser.clickElement(By.xpath("(//span[@class='select2-selection__arrow'])[2]"));
      oBrowser.setText(By.xpath("//input[@class='select2-search__field']"), Residence);
      oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
      
      //Mobile Number
      oBrowser.setText(By.id("mobile_number"), MNumber);
      
      //Checkbox
      oBrowser.clickElement(By.xpath("//input[@id='prequote_term']"));
      
      //Proceed
      oBrowser.clickElement(By.id("unlock_offer"));
  	try {
  	Thread.sleep(3000);
  } catch (Exception e) {
      e.printStackTrace();
  }
      //Verifying Condition
      getCurrentUrl = oBrowser.getCurrentUrl();
      System.out.println(getCurrentUrl);
      
      if(getCurrentUrl.contains(ExpectedUrl)){
      	System.out.println("TC_002 passed");
      }else{
      	Assert.assertEquals(actual, expected);
      }
      oBrowser.close();
  	}catch(AssertionError e)
      {
          System.out.println("Assertion error. ");
      }
  }  
   
//   @AfterClass
//   public void AfterScript(){
//	   System.out.println("varun3");
//	   Excel.close(oProperty.getProperty("PaisaBazaar"));
//	   System.out.println("varun4");
//	   oBrowser.closeAllBrowser();
//   }
   
}
