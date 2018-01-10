package commonlibs;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;










import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



	public class CommonDriver {

			private WebDriver oDriver;
			
			private long elementDetectionTimeout;
			
			private long pageLoadTimeout;
			
		    ExcelDriver oxExcel = new ExcelDriver();
			
			HashMap<String, HashMap<String, String>> GetData = new HashMap<String, HashMap<String, String>>();
			
			public CommonDriver(){
				elementDetectionTimeout = 601;
				pageLoadTimeout = 60l;
				
			}
			

			
			//----------------------------------------------------------------------------------------------------------------
			
			public void setElementDetectionTimeout(long elementDetectionTimeout){
				try {
					
					this.elementDetectionTimeout = elementDetectionTimeout;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
//			public void MakeDatabaseConnection(String url, String username, String password){
//				Connection con = oDriver.getConnection(url,username, password);
//			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void setPageLoadTimeout(long pageLoadTimeout){
				try {
					
					this.pageLoadTimeout = pageLoadTimeout;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void openBrowser(String sBrowserType, String sUrl ){
				
				try {
					
					sBrowserType = sBrowserType.trim();
					sUrl = sUrl.trim();
					switch (getBrowserType(sBrowserType)) {
					case 1:
						
						System.setProperty("webdriver.chrome.driver", "C:\\Users\\Varun\\chromedriver_win32\\chromedriver.exe");
						
						oDriver = new ChromeDriver();
						break;
						
					case 2:
						
						System.setProperty("webdriver.ie.driver", "");
						
						oDriver = new InternetExplorerDriver();
						break;
						
					case 3:
			
						oDriver = new FirefoxDriver();
						break;
					default:
						throw new Exception("Invalid Browser Type");
					}
					
					
					oDriver.manage().window().maximize();
					
					oDriver.manage().deleteAllCookies();
					
					oDriver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
					
					oDriver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
					
					if(sUrl.isEmpty()){
						oDriver.get("about:blank");
					} else {
						oDriver.get(sUrl);
					}
					
							
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			private int getBrowserType(String sBrowserType){
				
			try {
				
				if(sBrowserType.equalsIgnoreCase("chrome") || sBrowserType.equalsIgnoreCase("google chrome") 
						|| sBrowserType.equalsIgnoreCase("google")) {
					return 1;
				}
				
				if(sBrowserType.equalsIgnoreCase("ie") || sBrowserType.equalsIgnoreCase("internet explorer") 
						|| sBrowserType.equalsIgnoreCase("internet")) {
					return 2;
				}
				
				if(sBrowserType.equalsIgnoreCase("mozilla") || sBrowserType.equalsIgnoreCase("mozilla firefox") 
						|| sBrowserType.equalsIgnoreCase("firefox") ||  sBrowserType.equalsIgnoreCase("ff")) {
					return 3;
				}
				
				return -1;
				
			} catch (Exception e) {
				e.printStackTrace();
				
				return -1;
			}
				
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public WebDriver getDtriver(){
				return oDriver;
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			
			public String getTitle(){
				try {
					
				return	oDriver.getTitle();
					
				} catch (Exception e) {
					e.printStackTrace();
					
					return "";
				}
			}
			//----------------------------------------------------------------------------------------------------------------
			
			public String getCurrentUrl(){
				try {
					
				return	oDriver.getCurrentUrl();
					
				} catch (Exception e) {
					e.printStackTrace();
					
					return "";
				}
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void RefreshPage(){
				try {
					
					oDriver.navigate().refresh();
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			//----------------------------------------------------------------------------------------------------------------
			
			public void clickElement(By oBy){
				try {
					
				oDriver.findElement(oBy).click();
					
				} catch (Exception e) {
					e.printStackTrace();
					
					
				}
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void findelement(By oby){
				try{
					oDriver.findElement(oby).click();
					
				}catch(Exception e){
					e.printStackTrace();
					
				
				}
			}
			
			//-----------------------------------------------------------------------------------------------------------------
			
			public void AcceptAlert(){
			try{
				Alert alert = oDriver.switchTo().alert();
				alert.accept();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			}
			
			//-----------------------------------------------------------------------------------------------------------------
			
			public void DismissAlert(){
				try{
					Alert alert = oDriver.switchTo().alert();
					alert.dismiss();
					
				}catch(Exception e){
				    e.printStackTrace();
				}
			}
			
			//-----------------------------------------------------------------------------------------------------------------
			
			public boolean IsElementEnabled(By oby){
				try{
					boolean b = oDriver.findElement(oby).isEnabled();
					System.out.println(b);
				    return b;
				}catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			
			//------------------------------------------------------------------------------------------------------------------
			public void clear(By oBy){
				try {
					
				oDriver.findElement(oBy).clear();
					
				} catch (Exception e) {
					e.printStackTrace();
					
					
				}
			}
			//----------------------------------------------------------------------------------------------------------------
			
			public void setText(By oBy, String sValue){
				try {
					
				oDriver.findElement(oBy).sendKeys(sValue);
					
				} catch (Exception e) {
					e.printStackTrace();
					
					
				}
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public String gettext(By oBy){
				try {
					
				String str=oDriver.findElement(oBy).getText();
				
				return str;
				} catch (Exception e) {
					e.printStackTrace();
					
					return "";
				}
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void navigateTo(String sUrl){
				
				try {
					
					oDriver.navigate().to(sUrl);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void navigateBack(){
					
					try {
						
						oDriver.navigate().back();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}

			//----------------------------------------------------------------------------------------------------------------
			
			public void navigateForward(){
				
				try {
					
					oDriver.navigate().forward();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			//----------------------------------------------------------------------------------------------------------------
			
			public void reloadPage(){
				
				try {
					
					oDriver.navigate().refresh();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			//----------------------------------------------------------------------------------------------------------------
			
			public void Enter(By oBy){
				try {
				    
				   oDriver.findElement(oBy).sendKeys(Keys.ENTER);
				    
				   } catch (Exception e) {
				    e.printStackTrace();
				    
				    
				   }
				  }	
			
			//---------------------------------------------------------------------------------------------------------------
			
			public void selectVisibleItemFromDropdown(By oBy, String sItem){
				
				try {
					
					Select oDropdown = new Select(oDriver.findElement(oBy));
					
					oDropdown.selectByVisibleText(sItem);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//------------------------------------------------------------------------------------------------------------------------
			
           public void ExplicitWait(By oby){
				
				try {
					
					WebDriverWait wait=new WebDriverWait(oDriver, 200);
					wait.until(ExpectedConditions.visibilityOfElementLocated(oby));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
           //--------------------------------------------------------------------------------------------------------------------------
           public void ExplicitWait_click(By oby){
				
				try {
					
					WebDriverWait wait=new WebDriverWait(oDriver, 200);
					wait.until(ExpectedConditions.elementToBeClickable(oby));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void selectByValueFromDropdown(By oBy, String sValue){
					
					try {
						
						Select oDropdown = new Select(oDriver.findElement(oBy));
						
						oDropdown.selectByValue(sValue);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			//----------------------------------------------------------------------------------------------------------------
			
			public boolean IsElementVisible(By oBy){
				try {
					
				   return oDriver.findElement(oBy).isDisplayed();
				
					
				} catch (Exception e) {
					e.printStackTrace();
					
					return false;
				}
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public boolean isElementSelected(By oBy){
				try {
					
					return oDriver.findElement(oBy).isSelected();
					
				} catch (Exception e) {
					e.printStackTrace();
					
					return false;
				}
			}
			
			//----------------------------------------------------------------------------------------------------------------

			public void SelectElement(By oBy){
				try {
					
					oDriver.findElement(oBy).isSelected();
					
				} catch (Exception e) {
					e.printStackTrace();
					

				}
			}
			
		   //-----------------------------------------------------------------------------------------------------------------
			
			public boolean isDropDownMultiple(By oBy){
				try {
					
					Select oDropdwon = new Select(oDriver.findElement(oBy));
					return oDropdwon.isMultiple();
					
				} catch (Exception e) {
					e.printStackTrace();
					
					return false;
				}
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public boolean isElementEnabled(By oBy){
				try {
					
					return oDriver.findElement(oBy).isEnabled();
					
				} catch (Exception e) {
					e.printStackTrace();
					
					return false;
				}
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void close(){
				try {
					
					oDriver.close();
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void closeAllBrowser(){
				try {
					
					oDriver.quit();
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
			
            //----------------------------------------------------------------------------------------------------------------
			
			public void CloseBrTab(){
				try {
					oDriver.getWindowHandle();
					oDriver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"w");
				}
				catch (Exception e) {
					e.printStackTrace();	
				}
			}
			
            //----------------------------------------------------------------------------------------------------------------
			
			public void ClearData(){
				try {
					List<WebElement> Input = oDriver.findElements(By.tagName("input"));
					List<WebElement> Span = oDriver.findElements(By.tagName("span"));
					for (WebElement element: Input) {
						element.clear();
					}
					for (WebElement element: Span){
						 element.clear();
					}
				}
				catch (Exception e) {
					e.printStackTrace();	
				}
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void EnableCheckbox(By oBy){
				WebElement checkBoxElement = oDriver.findElement(oBy);
				checkBoxElement.click();
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void waitTillElementVisible(By oBy, long timeoutInSeconds){
				
				WebDriverWait oWait = new WebDriverWait(oDriver, timeoutInSeconds);
				
				oWait.until(ExpectedConditions.visibilityOfElementLocated(oBy));
			}
			
			//----------------------------------------------------------------------------------------------------------------
			
			public void mousehover1(By oby1,By oby2){
				try
				{
				 WebElement oelement = oDriver.findElement(oby1);
				 Actions a = new Actions(oDriver);
				 a.moveToElement(oelement).build().perform();
				 oDriver.findElement(oby2).click();
				}catch(Exception e){
					e.printStackTrace();
				}
			}

			//----------------------------------------------------------------------------------------------------------------
			
			public void toolTipCase1(By oby1,By oby2)
			{
				try
				{
					WebElement element = oDriver.findElement(oby1);
					Actions action = new Actions(oDriver);
					action.moveToElement(element).build().perform();
					WebElement toolTipElement = oDriver.findElement(oby2);
				
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		   //-----------------------------------------------------------------------------------------------------------------
			
			public String GetDynamicData(By oby,String Attribute)
			{
			try{
				WebElement element = oDriver.findElement(oby);
				String labelForValue = element.getAttribute(Attribute);
				return labelForValue;
				
			}catch(Exception e){
		    e.printStackTrace();
				return "";
			}
	}
			
		   //-----------------------------------------------------------------------------------------------------------------
			public boolean toolTipCase2(String tooltiptext,By oby,String Attribute)
			{
				try
				{
					String tooltipTextToVerify = oDriver.findElement(oby).getAttribute(Attribute);
					if(tooltiptext.equals(tooltipTextToVerify)){
						return true;
					}else{
						return false;
					}
				}catch(Exception e){
					 return false;
				}
			}
			
		   //-----------------------------------------------------------------------------------------------------------------
			
			public String toolTipCase3(By oby1,By oby2){
				try{
					WebElement element = oDriver.findElement(oby1);

					// Use action class to mouse hover on Text box field
					Actions action = new Actions(oDriver);
					action.moveToElement(element).build().perform();
					WebElement toolTipElement = oDriver.findElement(oby2);

					// To get the tool tip text and assert
					String toolTipText = toolTipElement.getText();

					return toolTipText;
				}catch(Exception e){
					e.printStackTrace();
					
					return "";
				}
			}
		
			
		   //-----------------------------------------------------------------------------------------------------------------
			
			public String HtmlToolTipText(By oby)
			{
				try{
					JavascriptExecutor js = (JavascriptExecutor)oDriver;
					WebElement field = oDriver.findElement(oby);
					Boolean is_valid = (Boolean)js.executeScript("return arguments[0].checkValidity();", field);
					String message = (String)js.executeScript("return arguments[0].validationMessage;", field);
					return message;
					
				}catch(Exception e){
					e.printStackTrace();
					
					return "";
				}
			}
			
		   //-----------------------------------------------------------------------------------------------------------------
			
			public void mousehover2(By oby){

				try
				{
				 WebElement oelement = oDriver.findElement(oby);
				 Actions a = new Actions(oDriver);
				 a.moveToElement(oelement).build().perform();
				 }
				catch(Exception e){
					e.printStackTrace();
		            
				}
			}
		//----------------------------------------------------------------------------------------------------------------------------
			
			//it  counts the elements of same xpath on the page
			
			public int SameElementCount(By oby){
				try{
					List<WebElement> optionCount = oDriver.findElements(oby);
					return optionCount.size();
				}catch(Exception e){
					return 0;
				}
			}
			
		//---------------------------------------------------------------------------------------------------------------------------	
			
			public int ElementCount(By oby){
				try{
		            WebElement selectElement = oDriver.findElement(oby);
		            Select listBox = new Select(selectElement);
		            return listBox.getOptions().size();

				}catch(Exception e){
					
				  return 0;
				}
			}
		//---------------------------------------------------------------------------------------------------------------------------
			
			public boolean ElementExistInDropdown(String str,By oby,String element){
				try{
					        oDriver.findElement(oby).getAttribute(element);
							if(str.contains("element")){
							 return true;
							}else{
							 System.out.println("Element not present");
							 return false;
							}
				}catch(Exception e){
					return false;
				}
			}
			
			
		//----------------------------------------------------------------------------------------------------------------------------

			public void mousehover3(By oby){
				try
				{
				 WebElement oelement = oDriver.findElement(oby);
				 Actions a = new Actions(oDriver);
				 a.moveToElement(oelement).build().perform();
				 oDriver.findElement(oby).click();
				}catch(Exception e){
					e.printStackTrace();
				}
			}


		   //---------------------------------------------------------------------------------------------------------------
			
		   public String GetAlertText(){
		   try{
			   Alert alert=oDriver.switchTo().alert();
			   return alert.getText();
		   
		   }catch(Exception e){
			   e.printStackTrace();
			   return "";
		}


		}
		   //---------------------------------------------------------------------------------------------------------------
			
		   public void CountULElements(By oby){
			   try{
				   List<WebElement> allUL = oDriver.findElements(oby);
//				   return allUL.size();
				   for (WebElement element: allUL) {
				        System.out.println(element.getText());
				        
			   }
			   }catch(Exception e){
				   e.printStackTrace();
				 
			   }
		   }
		   		   
		//-------------------------------------------------------------------------------------------------------------
		   
		   public void CompareElements(By oby){
			   String[] myStrings = new String[] {"ICICI Bank","BAJAJ Finserv","HDFC BANK","Induslnd Bank","Standard Chartered","TATA Capital","Citi Bank","Kotak","Axis Bank","Fullerton India","RBL Bank","Yes Bank","FAIRCENT","CAPITAL FIRST","IDFC"};
			   List<WebElement> allUL = oDriver.findElements(oby);
			   List mylist = Arrays.asList(myStrings );
			   for (WebElement element: allUL) {
				   for(int i=0; i<= mylist.size();i++){
					   
					   if(element.getText()==mylist.get(i)){
						   System.out.println(element.getText());
				   }else{
					    System.out.println("Element not there");   
			        
			        }
			        
		   }
		}
	}
				   
	    //-------------------------------------------------------------------------------------------------------------
		   
		   public String GetAttribute(By oby,String str){
		   try{
			   
			   String attribute=oDriver.findElement(oby).getAttribute(str);
			   return attribute;
		   }
		   catch(Exception e){
			   e.printStackTrace();
			   
			   return "";
		   }
		   }
		   
		//------------------------------------------------------------------------------------------------------------
		   
		   public void savePageSnapshot(String sImagePath){
				try {
					
					TakesScreenshot oCamera;
					File oTmpFile, oImageFile;
					oImageFile = new File(sImagePath);
					
					if(new File(sImagePath).exists()){
						throw new Exception("Image File already Exists");
					}
					
					oCamera = (TakesScreenshot)oDriver;
					oTmpFile = oCamera.getScreenshotAs(OutputType.FILE);
					//oCamera.getScreenshotAs(OutputType.FILE);
					
					FileUtils.copyFile(oTmpFile, oImageFile);
					
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}

		 //--------------------------------------------------------------------------------------------------------------
		   
		   public static String getDateTimeStamp(){

				Date oDate;
				String[] sDatePart;
				String sDateStamp;
				
				
				oDate = new Date();
				System.out.println(oDate.toString());
				//Mon Sep 07 17:28:42 IST 2015

				sDatePart = oDate.toString().split(" ");
				
				sDateStamp = sDatePart[5] + "_" +
						sDatePart[1] + "_" +
						sDatePart[2] + "_" +
						sDatePart[3] ;
				
				sDateStamp = sDateStamp.replace(":",  "_");
				System.out.println(sDateStamp);
				return sDateStamp;
			}
		   
		//---------------------------------------------------------------------------------------------------------------
		   
		   /*
		   public String switchwindow(String object, String data){
		        try {

		        String winHandleBefore = oDriver.getWindowHandle();

		        for(String winHandle : oDriver.getWindowHandles()){
		            oDriver.switchTo().window(winHandle);
		        }
		        }catch(Exception e){
		        return Constants.KEYWORD_FAIL+ "Unable to Switch Window" + e.getMessage();
		        }
		        return Constants.KEYWORD_PASS;
		        } 

		

	    //----------------------------------------------------------------------------------------------------------------

	       public String switchwindowback(String object, String data){
	          try {
	        String winHandleBefore = oDriver.getWindowHandle();
	        oDriver.close(); 
	        //Switch back to original browser (first window)
	        oDriver.switchTo().window(winHandleBefore);
	        //continue with original browser (first window)
	    }catch(Exception e){
	    return Constants.KEYWORD_FAIL+ "Unable to Switch to main window" + e.getMessage();
	    }
	    return Constants.KEYWORD_PASS;
	}

	*/
		   
		 
	   //-----------------------------------------------------------------------------------------------------------------
		/*   
		public void WindowHandles(String sBrowserType,String sUrl){
			try{
				this.openBrowser(sBrowserType, sUrl);
				String handle = oDriver.getWindowHandle();
			Set<String> handles = oDriver.getWindowHandles();
			
			for(String hnd:handles){
				
				if(!hnd.equals(handle)){
					oDriver.switchTo().window(hnd);
				}
			}
				
			}
		}
		*/

	   //------------------------------------------------------------------------------------------------------------------	   
	//
//		   public static int getResponseCode(String urlString) throws MalformedURLException, IOException{
//			    URL url = new URL(urlString);
//			    HttpURLConnection huc = (HttpURLConnection)url.openConnection();
//			    huc.setRequestMethod("GET");
//			    huc.connect();
//			    return huc.getResponseCode();
//			}
		   
		//------------------------------------------------------------------------------------------------------------------   
		   
		    public void LiUlTable(By oby){
		    	List<WebElement> allLi = oDriver.findElements(oby);
		    	for(WebElement eachLi:allLi){
		    		String tmp = eachLi.getText();
		    		System.out.println("Text in ul: "+tmp);//You may match for your required text, before printing/using it.
		    	}
		    }
		   
		    
		  //------------------------------------------------------------------------------------------------------------------   
			   
		    public void LiUlValue(By oby1){
		    	List<WebElement> bankNames = oDriver.findElements(oby1);
		    	int count = bankNames.size();
		    	System.out.println(count);
		    	
		    	for(int i=3; i<=count; i++)
		    	{
		    		for(WebElement bank : bankNames) {
			    		String bankN = bank.getAttribute("title");
			    		System.out.print("  "+bankN);
		    		WebElement IntRate = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[2]//span"));
		    		System.out.print(IntRate.getText());
		    		
		    		WebElement ProFees = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[3]//span"));
		    		System.out.print(ProFees.getText());
		    		
		    		WebElement EMI = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[4]//span"));
		    		System.out.print(EMI.getText());
		    		
		    		System.out.println("");
		    		}
		    	}
		    	}
		    	
//		    	for(WebElement bank : bankNames) {
//		    		String bankName = bank.getAttribute("title");
//		    		System.out.println(bankName);
//		    		String interestRate = bank.findElement(By.xpath("//span")).getAttribute("innterText");
//		    		String ProcessingFees = bank.findElement(arg0)
		    //---------------------------------------------------------------------------------	
		    
		    public void ExtractElements(By oby){
		    	List<WebElement> Elements = oDriver.findElements(oby);
//		    	JavascriptExecutor executor = (JavascriptExecutor)oDriver;
//		    	executor.executeScript("arguments[0].click();", element);
//		    	List<WebElement> Elements = oDriver.findElements(oby);
		    	int count = Elements.size();
		    	System.out.println(count);
//		    	List<WebElement> element = oDriver.findElements(oby);	    	
		    	if(count == 0){
		    	   System.out.println("No such element available");	
		    	}else{
		    		
		    	int i=1;
		    	while(i<=count){
		    	List <WebElement> elements = oDriver.findElements(oby);
		    	   for(WebElement element : elements){
	                   element.click();
		    	       this.ExplicitWait(oby);
		    	       this.IsElementVisible(oby);
		    	       System.out.println("varun");
//		    		   ((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		    		   element.click();
		    		   i++;
//		    	   }
//		    	while(i<=count){	
//		    	System.out.println("sharma1");
//		    	WebElement element = oDriver.findElement(By.xpath("//quote[@class='cat_toggle'])["+i+"]"));
//		    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element);
//		    	System.out.println("sharma3");
//		    	oDriver.findElement(By.xpath("//a[contains(@href,\"javascript:void[0]\")]")).click();
		    	// [conatins(text(), 'more')]
		    	//(//quote[@class='cat_toggle'])["+i+"]
//		    	System.out.println("sharma2");
//		    	element.click();
//		    	System.out.println("sharma3");
//		    	i++;
		    	}
		    	}
		    	}
		    }
		    
		//------------------------------------------------------------------------------------------------------------
		        
		    public void ExtractElements1(){
		    	try {
		    		List <WebElement> elements = oDriver.findElements(By.xpath("//quote[@class='cat_toggle'][contains(text(),'more')]"));
		    		int count = elements.size();
		    		System.out.println("Elements Size" +count);
		    		if(count == 0){
				    	   System.out.println("No such element available");	
				    	}else{
				    		
				    	int i=1;
				    	while(i<=count){
				    		WebElement element = oDriver.findElement(By.xpath("(//quote[@class='cat_toggle'])["+i+"]"));
				    		            int x = element.getLocation().x;
				    		            int y = element.getLocation().y;
				    		            System.out.println(x+" "+y);
				    		            JavascriptExecutor jse = (JavascriptExecutor)oDriver;
				    		            System.out.println("varun1");
				    		            jse.executeScript("window.scrollBy(x,y)", "");
				    		            System.out.println("varun2");
				    		element.click();
				    		i++;
				    	}
				    	}
				    	
				} catch (Exception e) {
					e.printStackTrace();
				}
		    } 	
		 //-----------------------------------------------------------------------------------------------------------
		    
		    public void ExtractElements2(){
		    	try {
		    		List <WebElement> elements = oDriver.findElements(By.xpath("//quote[@class='cat_toggle']"));
//		    		[contains(text(),'more')]
		    		int count = elements.size();
		    		System.out.println("Elements Size " +count);
		    		if(count == 0){
			    	   System.out.println("No such element available");	
			    	}
		    		if(count == 1){
		   			WebElement element1 = oDriver.findElement(By.xpath("//a[@parent_bank_id='246']"));
		    		element1.click();	
		    		}
		    		if(count == 2){
		    		boolean b = oDriver.findElement(By.xpath("//a[@parent_bank_id='246']")).isDisplayed();		    		System.out.println(b);
		    		JavascriptExecutor je = (JavascriptExecutor) oDriver;
		    		WebElement element1 = oDriver.findElement(By.xpath("//a[@parent_bank_id='246']"));
//		    		je.executeScript("arguments[0].scrollIntoView(true);",element1);	    	
		    		Actions actions = new Actions(oDriver);
		    		actions.moveToElement(element1);
		    		actions.perform();
		    		Thread.sleep(3000);
//			    	element1.click();
//		    		boolean c = oDriver.findElement(By.xpath("//a[@parent_bank_id='249']")).isDisplayed();
//			    	WebElement element2 = oDriver.findElement(By.xpath("//a[@parent_bank_id='249']"));
//			    	System.out.println(c);
//		    		Thread.sleep(3000);
//			    	element2.click();
		    		}
//		    		else{
//			    		int i=1;
//			    		while(i<=count){
//				    		Thread.sleep(5000);
  //				    	oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])["+i+"]")).sendKeys(Keys.PAGE_DOWN);
//				    		WebElement element = oDriver.findElement(By.xpath("(//quote[@class='cat_toggle'])["+i+"]"));
//				    		if(oDriver.findElement(By.xpath("(//quote[@class='cat_toggle'])["+i+"]")).isDisplayed()){
//				    			System.out.println("varun1");
//				    			Actions actions = new Actions(oDriver);
//				    			actions.moveToElement(element);
//				    			actions.perform();
//				    			element.click();
//				    			System.out.println("value "+i);
//				    		}
//				    		else {
//				    			System.out.println("varun3");
//				    			WebElement e=oDriver.findElement(By.xpath("(//quote[@class='cat_toggle'])["+i+"]"));
//				    			Coordinates cor=((Locatable)e).getCoordinates();
//				    			cor.inViewPort();
//				    			Thread.sleep(1000);
//				    			e.click();
//				    		     ((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element);
//				    		}
		//				    			}
		//								}
		//				    		element.click();
		//		    		i++;
	//				    		element.sendKeys(Keys.PAGE_DOWN);
	//				    		element.click();
	//				    		int x = element.getLocation().x;
	//	    		            int y = element.getLocation().y;
	//	    		            System.out.println(x+" "+y);
	//				    		 JavascriptExecutor js = (JavascriptExecutor)oDriver;
	//				    	      for(int sl=0;;sl++)
	//				    	        {
	//				    	            if(sl>=60)
	//				    	            {
	//				    	                break;
	//				    	            }
	//				    	            js.executeScript("window.scrollBy(0,500)","");
	//				    	            Thread.sleep(1000);
	//				    	            element.click();
	//		    	        		}
			    	
	//				    		Actions action = new Actions(oDriver);
	//				    		action.sendKeys(Keys.PAGE_DOWN);
	//				    		System.out.println("VARUN1");
	//				    		action.click(oDriver.findElement(By.xpath("(//quote[@class='cat_toggle'])["+i+"]"))).perform();
	//				    		System.out.println("VARUN2");
	//				    		oDriver.quit();
		    
//				    		}
//				    	}
//			    	}
				    	
				} catch (Exception e) {
					e.printStackTrace();
				}
		    } 	
		    
//------------------------------------------------------------------------------------------------------------	    
		    
		    public void Ullitabledata(By oby1){
		    List<WebElement> bankNames = oDriver.findElements(oby1);	
		    int count = bankNames.size();
		    int i=2;
		    while(i<=count)
	    	{
		    	try{
		    	WebElement Bank = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[1]"));
	    		String title = Bank.getAttribute("title");
	    		System.out.println(title);
	    		i++;
		    	}
		    	catch(Exception e)
		    	{
		    		e.printStackTrace();
		    	}
//		    	WebElement Bank = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[1]//div//img"));
//	    		String source = Bank.getAttribute("src");
//	    		System.out.println(source);
//	    		
//			    	if(oDriver.findElement(By.xpath("//quote[@class='cat_toggle']")) != Bank)
//			    	{
//			    	
//	//		    		WebElement Bank = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[1]//div//img"));
//			    		System.out.print(Bank.getAttribute("title"));
//			    		
//				    	WebElement IntRate = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[2]//span"));
//			    		System.out.print(IntRate.getText());
//			    		
//			    		WebElement ProFees = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[3]//span"));
//			    		System.out.print(ProFees.getText());
//			    		
//			    		WebElement EMI = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[4]//span"));
//			    		System.out.print(EMI.getText());
//			    	} 
//		    	}
	    	}
		    }
		    
//----------------------------------------------------------------------------------------------------------------------------------------
		    
		    public void Ullitabledata1(String locator1, String locator2, String sSheetName, String sFileName, ExcelDriver Excel, int k){
		    	    
		    	String[] Bank_Array = {"HDFC Bank", "ICICI Bank", "Kotak Bank", "YES Bank", "TATA CAPITAL"};
		    	try {
					int RowCount = Excel.getRowCountOfSheet(sSheetName);
					System.out.println("Row count is :" +RowCount);
					int count = Excel.getCellCount(sSheetName, 1, sFileName);
	               	System.out.println(count);
	         
		    		List<WebElement> bankNames1 = oDriver.findElements(By.xpath(locator1 + "/ancestor::ul/li[1]//img"));
		         
//		    		List<WebElement> bankNames2 = oDriver.findElements(By.xpath(locator2 + "/ancestor::ul/li[1]//img"));
		   
				    List<WebElement> interestRates = oDriver.findElements(By.xpath(locator1 + "/ancestor::ul/li[2]//span[1]"));
			       
				    List<WebElement> ProcessingFees = oDriver.findElements(By.xpath(locator1 + "/ancestor::ul/li[3]//span[1]"));
				    
				    List<WebElement> Emi = oDriver.findElements(By.xpath(locator1 + "/ancestor::ul/li[4]//span[1]"));
			
				    List<WebElement> ChildQuote = oDriver.findElements(By.xpath("(//a[@class='view_child_quote'])"));
				    
					try {
						Thread.sleep(3000);
					  } catch (Exception e) {
					    e.printStackTrace();
					  }
				    
//				    System.out.println("varun");
//				    int count = Excel.getCellCount(sSheetName, 2, sFileName);
//               	System.out.println(count);               
//				                     try {
//									
//				                    	 Excel.setCellCData(sSheetName, 1, 2, "Varun1", sFileName);
//				                    	 System.out.println("Varun Sharma");
//									} catch (Exception e) {
//										e.printStackTrace();
//									}
//				                 
//				    
				                     int count3 = bankNames1.size();
				                     System.out.println("Banks size is :"+count3);
                                                                             
				                     int InterestRates = interestRates.size();
				                     System.out.println("Interest Rates size is :"+InterestRates);
				                     
				                     int count1 = Excel.getCellCount(sSheetName, 2, sFileName);
//				                     int count2 = (count1 + bankNames1.size());
//				                     System.out.println("Column and bankname size combined :"+count2);
//				                     if(bankNames1.size() > 0)
//				                     {
				                     for(int i = 0; i < bankNames1.size() ; i++) {
				                    	 
				                    
//				                     //for(WebElement bank : bankNames) {
				                    	 String bankN = bankNames1.get(i).getAttribute("title");
				                    	 String interestRate = interestRates.get(i).getText().substring(1);
				                    	 String PFees = ProcessingFees.get(i).getText().trim().substring(1);
				                    	 String Emis = Emi.get(i).getText().trim().substring(1);
				 			    	    // String bankN = bankN.getAttribute("title");
				 			           
//     			                        if(Arrays.asList(Bank_Array).contains(bankN)){
				                   				                         	
				 			            Excel.setCellCData(sSheetName, k, count1+1, bankN, sFileName);
                                        Excel.setCellCData(sSheetName, k, count1+2, 1+interestRate, sFileName);
				 			    		Excel.setCellCData(sSheetName, k, count1+3, PFees, sFileName);
				 			    		Excel.setCellCData(sSheetName, k, count1+4, Emis, sFileName);
				 			    		k = k+1;
//				                     }else{
//				                    	 System.out.println("Moving");
//				                     }
				                        
//				                     }
				                     
//				                     if(bankNames2.size() > 0)
//				                     {
//				                     for(int i = 0; i < bankNames2.size() ; i++) {
//					                     //for(WebElement bank : bankNames) {
//					                    	 String bankN = bankNames1.get(i).getAttribute("title");
//					                    	 String interestRate = interestRates.get(i).getText();
//					                    	 String PFees = ProcessingFees.get(i).getText().trim();
//					                    	 String Emis = Emi.get(i).getText().trim();
//					 			    	   //String bankN = bankN.getAttribute("title");
//					 			    
//					 			            Excel.setCellCData(sSheetName, k, ++count2, bankN, sFileName);
//	                                        Excel.setCellCData(sSheetName, k, ++count2, interestRate, sFileName);
//					 			    		Excel.setCellCData(sSheetName, k, ++count2, PFees, sFileName);
//					 			    		Excel.setCellCData(sSheetName, k, ++count2, Emis, sFileName);
//					                     }
//				                     }
//				 			    		System.out.print("    "+bankN);
//				 			    		System.out.print("    "+interestRate);
//				 			    		System.out.print("    "+PFees);
//				 			    		System.out.print("    "+Emis);
//	       		 			    		System.out.println();
//				 		    		WebElement IntRate = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[2]//span"));
//				 		    		System.out.print(IntRate.getText());
//				 		    		
//				 		    		WebElement ProFees = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[3]//span"));
//				 		    		System.out.print(ProFees.getText());
//				 		    		
//				 		    		WebElement EMI = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[4]//span"));
//				 		    		System.out.print(EMI.getText());
//				 		    		
//				 		    		System.out.println("");
//				    
//				    	WebElement Bank = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[1]//div//img"));
//			    		String source = Bank.getAttribute("src");
//			    		System.out.println(source);
//			    		
//					    	if(oDriver.findElement(By.xpath("//quote[@class='cat_toggle']")) != Bank)
//					    	{
//					    	
//			//		    		WebElement Bank = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[1]//div//img"));
//					    		System.out.print(Bank.getAttribute("title"));
//					    		
//						    	WebElement IntRate = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[2]//span"));
//					    		System.out.print(IntRate.getText());
//					    		
//					    		WebElement ProFees = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[3]//span"));
//					    		System.out.print(ProFees.getText());
//					    		
//					    		WebElement EMI = oDriver.findElement(By.xpath(".//div[@id='accordion']/ul["+i+"]/li[4]//span"));
//					    		System.out.print(EMI.getText());
//					    	}
//				    	}
				                     
		    	
				                     }
		    	}
		    	
				 catch (Exception e) {
					e.printStackTrace();
				}
			    	
			    }
	
		    	
		//-------------------------------------------------------------------------------------------------------------
		    
		    public void ChildQuoteSize1(){
		    	try{

		    		List<WebElement> ChildQuote = oDriver.findElements(By.xpath("(//a[@class='view_child_quote'])"));
                    int ChildQuoteSize = ChildQuote.size();
                    System.out.println("Child Quote Size in method 1 :"+ChildQuoteSize);
                    
                    if(ChildQuoteSize == 0){
                      System.out.println("No Such element present");
                    }
                    if(ChildQuoteSize == 2){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[1]"));
                    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element1);
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[1]")).click();
                      
                    }
                    if(ChildQuoteSize == 4){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[1]"));
                    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element1);
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[1]")).click();
                        
                        WebElement element2 = oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[3]"));
                    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element2);
                    	Thread.sleep(500);
                    	oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[3]")).click();
                    }
                    if(ChildQuoteSize == 6){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[1]"));
                    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element1);
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[1]")).click();
                        
                        WebElement element2 = oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[3]"));
                    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element2);
                    	Thread.sleep(500);
                    	oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[3]")).click();
                    	
                    	WebElement element3 = oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[5]"));
                    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element3);
                    	Thread.sleep(500);
                    	oDriver.findElement(By.xpath("(//a[@class='view_child_quote'])[5]")).click();
                    }
                    
		    	}catch(Exception e){
		    		e.printStackTrace();
		    	
		    	}
		    }
		    
		    
		 //-------------------------------------------------------------------------------------------------------------
		    
		    public void ChildQuoteSize2(){
		    	try{

		    		List<WebElement> ChildQuote = oDriver.findElements(By.xpath("//ul[@class='show-info nested_code']//li//span//a[@class='view_child_quote']"));
                    int ChildQuoteSize = ChildQuote.size();
                    System.out.println("Child Quote Size in method 2 :"+ChildQuoteSize);
                    
                    if(ChildQuoteSize == 0){
                      System.out.println("No Such element present");
                    }
                    
                    if(ChildQuoteSize == 1){
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//span//a[@class='view_child_quote'])[1]")).click();  
                    }
                    
                    if(ChildQuoteSize == 2){
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//span//a[@class='view_child_quote'])[1]")).click();  
                   
                	    Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//span//a[@class='view_child_quote'])[2]")).click();  
            
                    }
                    if(ChildQuoteSize == 3){
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//span//a[@class='view_child_quote'])[1]")).click();  
                   
                	    Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//span//a[@class='view_child_quote'])[2]")).click();
                    	
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//span//a[@class='view_child_quote'])[3]")).click();  
                    }
                    
		    	}catch(Exception e){
		    		e.printStackTrace();
		    	
		    	}
		    }

		//--------------------------------------------------------------------------------------------------------------
		    
		    public void ChildQuoteSize3(){
		    	try{

		    		List<WebElement> ChildQuote = oDriver.findElements(By.xpath("//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')]"));
                    int ChildQuoteSize = ChildQuote.size();
                    System.out.println("Child Quote Size in method 3 :"+ChildQuoteSize);
                    
                    if(ChildQuoteSize == 0){
                      System.out.println("No Such element present");
                    }
                    
                    if(ChildQuoteSize == 1){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]"));
                    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element1);
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();  
                    }
                    
                    if(ChildQuoteSize == 2){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]"));
                    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element1);
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();  
                   
                	    WebElement element2 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]"));
                	    ((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element2);
                	    Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]")).click();  
                    }
                    
                    if(ChildQuoteSize == 3){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]"));
                    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element1);
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();  
                   
                	    WebElement element2 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]"));
                	    ((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element2);
                	    Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]")).click();
                    	
                        WebElement element3 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[3]"));
                    	((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element3);
                    	Thread.sleep(500);
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[3]")).click();  
                    }
                    
		    	}catch(Exception e){
		    		e.printStackTrace();
		    	
		    	}
		    }
		    
		//--------------------------------------------------------------------------------------------------------------
		    
		    public void ChildQuoteSize4(){
		    	try{

		    		List<WebElement> ChildQuote = oDriver.findElements(By.xpath("//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')]"));
                    int ChildQuoteSize = ChildQuote.size();
                    System.out.println("Child Quote Size in method 4 :"+ChildQuoteSize);
                    
                    if(ChildQuoteSize == 0){
                      System.out.println("No Such element present");
                    }
                    
                    if(ChildQuoteSize == 1){
                    	Point hoverItem1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).getLocation();
                    	((JavascriptExecutor)oDriver).executeScript("return window.title;");    
                    	Thread.sleep(6000);
                    	((JavascriptExecutor)oDriver).executeScript("window.scrollBy(0,"+(hoverItem1.getY())+");"); 
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();  
                    }
                    
                    if(ChildQuoteSize == 2){
                    	Point hoverItem1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).getLocation();
                    	((JavascriptExecutor)oDriver).executeScript("return window.title;");    
                    	Thread.sleep(6000);
                    	((JavascriptExecutor)oDriver).executeScript("window.scrollBy(0,"+(hoverItem1.getY())+");"); 
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();  
                   
                        Point hoverItem2 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]")).getLocation();
                    	((JavascriptExecutor)oDriver).executeScript("return window.title;");    
                    	Thread.sleep(6000);
                    	((JavascriptExecutor)oDriver).executeScript("window.scrollBy(0,"+(hoverItem1.getY())+");"); 
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]")).click();  
                    }
                    
                    if(ChildQuoteSize == 3){
                    	Point hoverItem1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).getLocation();
                    	((JavascriptExecutor)oDriver).executeScript("return window.title;");    
                    	Thread.sleep(6000);
                    	((JavascriptExecutor)oDriver).executeScript("window.scrollBy(0,"+(hoverItem1.getY())+");"); 
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();  
                   
                        Point hoverItem2 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]")).getLocation();
                    	((JavascriptExecutor)oDriver).executeScript("return window.title;");    
                    	Thread.sleep(6000);
                    	((JavascriptExecutor)oDriver).executeScript("window.scrollBy(0,"+(hoverItem1.getY())+");"); 
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]")).click();
                        
                        Point hoverItem3 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[3]")).getLocation();
                    	((JavascriptExecutor)oDriver).executeScript("return window.title;");    
                    	Thread.sleep(6000);
                    	((JavascriptExecutor)oDriver).executeScript("window.scrollBy(0,"+(hoverItem3.getY())+");"); 
                        oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[3]")).click();  
                    }
                    
		    	}catch(Exception e){
		    		e.printStackTrace();
		    	
		    	}
		    }
		    
		//--------------------------------------------------------------------------------------------------------------   
		   
		    public void ChildQuoteSize5(){
		    	try{

		    		List<WebElement> ChildQuote = oDriver.findElements(By.xpath("//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')]"));
                    int ChildQuoteSize = ChildQuote.size();
                    System.out.println("Child Quote Size in method 5 :"+ChildQuoteSize);
                    
                    if(ChildQuoteSize == 0){
                      System.out.println("No Such element present");
                    }
                    
                    if(ChildQuoteSize == 1){
                    	 WebElement element1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code'])[1]"));
        				 Actions a = new Actions(oDriver);
        				 a.moveToElement(element1).build().perform();
        				 oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();  
                    }
                    
                    if(ChildQuoteSize == 2){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code'])[1]"));
       				    Actions a = new Actions(oDriver);
       				    a.moveToElement(element1).build().perform();
       				    oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();  
                   
       				    WebElement element2 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code'])[2]"));
    				    Actions b = new Actions(oDriver);
    				    b.moveToElement(element2).build().perform();
    				    oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]")).click();  
                    }
                    
                    if(ChildQuoteSize == 3){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code'])[1]"));
       				    Actions a = new Actions(oDriver);
       				    a.moveToElement(element1).build().perform();
       				    oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();  
                   
       				    WebElement element2 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code'])[2]"));
    				    Actions b = new Actions(oDriver);
    				    b.moveToElement(element2).build().perform();
    				    oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]")).click();
                        
    				    WebElement element3 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code'])[3]"));
    				    Actions c = new Actions(oDriver);
    				    c.moveToElement(element3).build().perform();
    				    oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[3]")).click();  
                    }
                    
		    	}catch(Exception e){
		    		e.printStackTrace();
		    	
		    	}
		    }
		    
		//--------------------------------------------------------------------------------------------------------------
		    
		    public void ChildQuoteSize6(){
		    	try{

		    		List<WebElement> ChildQuote = oDriver.findElements(By.xpath("//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')]"));
                    int ChildQuoteSize = ChildQuote.size();
                    System.out.println("Child Quote Size in method 6 :"+ChildQuoteSize);
                    
                    if(ChildQuoteSize == 0){
                      System.out.println("No Such element present");
                    }
                    
                    if(ChildQuoteSize == 1){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]"));

                    	String scrollElementIntoMiddle1 = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    	                                            + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    	                                            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

                    	((JavascriptExecutor) oDriver).executeScript(scrollElementIntoMiddle1, element1);  
                    	oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();
                    }
                    
                    if(ChildQuoteSize == 2){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]"));

                    	String scrollElementIntoMiddle1 = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    	                                            + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    	                                            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

                    	((JavascriptExecutor) oDriver).executeScript(scrollElementIntoMiddle1, element1);  
                    	oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();
  
                   
                    	WebElement element2 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]"));

                    	String scrollElementIntoMiddle2 = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    	                                            + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    	                                            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

                    	((JavascriptExecutor) oDriver).executeScript(scrollElementIntoMiddle2, element2);  
                    	oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]")).click();
  
                    }
                    
                    if(ChildQuoteSize == 3){
                    	WebElement element1 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]"));

                    	String scrollElementIntoMiddle1 = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    	                                            + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    	                                            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

                    	((JavascriptExecutor) oDriver).executeScript(scrollElementIntoMiddle1, element1);  
                    	oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[1]")).click();
  
                   
                    	WebElement element2 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]"));

                    	String scrollElementIntoMiddle2 = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    	                                            + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    	                                            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

                    	((JavascriptExecutor) oDriver).executeScript(scrollElementIntoMiddle2, element2);  
                    	oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[2]")).click();
                        
                    	WebElement element3 = oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[3]"));

                    	String scrollElementIntoMiddle3 = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    	                                            + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    	                                            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

                    	((JavascriptExecutor) oDriver).executeScript(scrollElementIntoMiddle3, element3);  
                    	oDriver.findElement(By.xpath("(//ul[@class='show-info nested_code']//li//a[contains(text(),'View more offers')])[3]")).click();
  
                    }
                    
		    	}catch(Exception e){
		    		e.printStackTrace();
		    	
		    	}
		    } 
		    
	    //--------------------------------------------------------------------------------------------------------------
	       
		   public void getHtmlTableData(By oby){
				
				String[] rowTxt;
				WebElement table = oDriver.findElement(oby);
				for (WebElement rowElmt : table.findElements(By.tagName("tr")))
				{
				    List<WebElement> cols = rowElmt.findElements(By.tagName("td"));
				    rowTxt = new String[cols.size()];
				    for (int i = 0; i < rowTxt.length; i++)
				    {
				        rowTxt[i] = cols.get(i).getText();
				        System.out.println(rowTxt[i]);
				    }
				}
				}
		   
//--------------------------------------------------------------------------------------------------------------   
		   
		   public void ScrollPageUp(){
				try {
				
					JavascriptExecutor jse = (JavascriptExecutor) oDriver;
				    jse.executeScript("window.scrollBy(0,250)");
				} catch (Exception e) {
				  e.printStackTrace();
				}
			   
				    }
				
//--------------------------------------------------------------------------------------------------------------   
		   
		   public void ScrollPageUp2(){
				try {
				
					Actions actions = new Actions(oDriver); 
					actions.keyUp(Keys.CONTROL).sendKeys(Keys.UP).perform();
				} catch (Exception e) {
				  e.printStackTrace();
				}
			   
				    }

		   
		//-----------------------------------------------------------------------------------------   
		   private static int statusCode;
		   
		   public void httpResponse(String url) throws IOException{
		     
		       oDriver.get(url);
		       List<WebElement> links = oDriver.findElements(By.tagName("a"));
		       for(int i = 0; i < links.size(); i++){
		           if(!(links.get(i).getAttribute("href") == null) && !(links.get(i).getAttribute("href").equals(""))){
		               if(links.get(i).getAttribute("href").contains("http")){
//		                   statusCode= getResponseCode(links.get(i).getAttribute("href").trim());
		                   if(statusCode == 403){
		                       System.out.println("HTTP 403 Forbidden # " + i + " " + links.get(i).getAttribute("href"));
		                   }
		               }
		           }   
		       }   
		   }
	}


