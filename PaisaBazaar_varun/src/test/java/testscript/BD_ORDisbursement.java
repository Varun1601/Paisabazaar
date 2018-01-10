package testscript;




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

	public class BD_ORDisbursement {

		ExcelDriver Excel=new ExcelDriver();
		CommonDriver oBrowser = new CommonDriver();
		Utils utils = new Utils();
	    Properties oProperty = new Properties();

	    String Str1,MakerUsername,MakerPassword,browser,username,password,URL,PPA,InvoiceNumber,VendorRefNumber,PONumber,GRNNumber,BillMonth,BillYear,BillDate,BillAmount,DiscountFactor,DiscountMonth,DiscountYear,DiscountDate,DisbursementMonth,DisbursementYear,DisbursementDate,DueMonth,DueYear,DueDate,InterestStartMonth,InterestStartYear,InterestStartDate,RateOfDiscount,BankCharges,OtherCharges,OtherChargesRemarks,BasisAmount,ActualAmountToDisburse,ErrorMessage,Remarks;
	    String path = "D:\\automation\\PincapAutomation\\conf\\config.properties";
	    HashMap<String, HashMap<String, String>> loginData = new HashMap<String, HashMap<String, String>>();
	    HashMap<String, HashMap<String, String>> data = new HashMap<String, HashMap<String, String>>();
	    
	    @BeforeClass
	    public void login(){

	    	oProperty=utils.getProperties(path);
	    	Excel.openExcelWorkbook(oProperty.getProperty("BD_OR_Disbursement"));
			loginData = Excel.ReadLoginCredentials(oProperty.getProperty("BD_OR_Disbursement"),oProperty.getProperty("LoginDataSheet"));
			data = Excel.ReadTestCaseData(oProperty.getProperty("BD_OR_Disbursement"),oProperty.getProperty("DisbursementDataSheet"));
			
	        browser = loginData.get("1").get("browser");
	        URL = loginData.get("1").get("url");
	        username = loginData.get("1").get("username");
	        password = loginData.get("1").get("password");
	        
			oBrowser.setPageLoadTimeout(601);
			oBrowser.setElementDetectionTimeout(601);
			oBrowser.openBrowser(browser,URL);
			oBrowser.setText(By.id("username"), username);
			oBrowser.setText(By.name("_password"), password);
			oBrowser.clickElement(By.xpath("//input[@type='submit']"));

	    }
	    
	    
	    /*
		@AfterClass
		public void logout(){
			oBrowser.mousehover1(By.xpath("//a[@id='user-dropdown']"), By.xpath("//a[@id='logout']"));
			oBrowser.closeAllBrowser();
		}
	 */

		@BeforeMethod
		public void precondition(){
			Excel.openExcelWorkbook(oProperty.getProperty("BD_OR_Disbursement"));
			String LoanType = loginData.get("1").get("LoanType").trim();
			oBrowser.clickElement(By.xpath("(//i[@class='icon-plus icon-large'])[1]"));
			oBrowser.clickElement(By.xpath("//img[@class='search-expand-icon']"));
			oBrowser.setText(By.id("grid_2dff8539800272b5c2cb92ab0e197879__loan_product_type__query__from"),LoanType);
			oBrowser.clickElement(By.xpath("//button[@class='grid-search-submit pure-button']"));
		}
		
		/*
		@Test
		public void TC_D001(){
	    System.out.println("TC_D001");
	    String text = oBrowser.gettext(By.xpath("(//td[@class='grid-column-accountNumber'])[1]"));
	    System.out.println(text);
	    oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    Assert.assertTrue(oBrowser.IsElementVisible(By.xpath("//h2[contains(text(),text)]")));
		}
		*/
		
//		@Test
//		public void TC_D002(){
//		System.out.println("TC_D002");
//		String text = oBrowser.gettext(By.xpath("(//td[@class='grid-column-rate_of_interest_period'])[1]"));
//		System.out.println(text);
//		oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
//		Assert.assertTrue(oBrowser.IsElementVisible(By.xpath("(//div[@class='row'])[7]")));
//		}

		/*
		@Test
		public void TC_003(){
		System.out.println("TC_D003");
		oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
		oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
		PPA = data.get("TC_D002").get("PPA");
		InvoiceNumber = data.get("TC_D002").get("InvoiceNumber");
		VendorRefNumber = data.get("TC_D002").get("VendorRefNumber");
		PONumber = data.get("TC_D002").get("PONumber");
		GRNNumber = data.get("TC_D002").get("GRNNumber");
		BillDate = data.get("TC_D002").get("BillDate");
		BillAmount = data.get("TC_D002").get("BillAmount");
		DiscountFactor = data.get("TC_D002").get("DiscountFactor");
		DiscountDate = data.get("TC_D002").get("DiscountDate");
		DisbursementDate = data.get("TC_D002").get("DisbursementDate");
		DueDate = data.get("TC_D002").get("DueDate");
		InterestStartDate = data.get("TC_D002").get("InterestStartDate");
		RateOfDiscount = data.get("TC_D002").get("RateOfDiscount");
		BankCharges = data.get("TC_D002").get("BankCharges");
		OtherCharges = data.get("TC_D002").get("OtherCharges");
		OtherChargesRemarks = data.get("TC_D002").get("OtherChargesRemarks");
		BasisAmount = data.get("TC_D002").get("BasisAmount");
		
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnDate"),BillDate);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_rateOfGrn"),DiscountFactor);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_discountDate"),DiscountDate);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_disbursementDate"),DisbursementDate);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_dueDate"),DueDate);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_settlementStartDate"),InterestStartDate);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_rateOfDiscount"),RateOfDiscount);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_bankCharges"),BankCharges);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_otherCharges"),OtherCharges);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_otherChargesComment"),OtherChargesRemarks);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_basisAmount"),BasisAmount);
		}
		
		
	    @Test
		public void TC_D006(){
		System.out.println("TC_D006");
		oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
		oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
		PPA = data.get("TC_D006").get("PPA");
		InvoiceNumber = data.get("TC_D006").get("InvoiceNumber");
		VendorRefNumber = data.get("TC_D006").get("VendorRefNumber");
		PONumber = data.get("TC_D006").get("PONumber");
		GRNNumber = data.get("TC_D006").get("GRNNumber");
		BillAmount = data.get("TC_D006").get("BillAmount");
		DiscountFactor = data.get("TC_D006").get("DiscountFactor");
		DiscountMonth = data.get("TC_D006").get("DiscountMonth");
		DiscountYear = data.get("TC_D006").get("DiscountYear");
		DiscountDate = data.get("TC_D006").get("DiscountDate");
		ErrorMessage = data.get("TC_D006").get("ErrorMessage");
	    
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
		oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
		oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
		oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
		oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
		oBrowser.clickElement(By.linkText(DiscountDate));
		oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
		oBrowser.mousehover2(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"));
		String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"));
	    System.out.println(str);
	    Assert.assertEquals(str,ErrorMessage);
		
	 }
	    
	    @Test
	    public void TC_D007(){
	    	System.out.println("TC_D007");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D007").get("PPA");
	    	VendorRefNumber = data.get("TC_D007").get("VendorRefNumber");
	    	PONumber = data.get("TC_D007").get("PONumber");
	    	GRNNumber = data.get("TC_D007").get("GRNNumber");
	    	BillAmount = data.get("TC_D007").get("BillAmount");
	    	DiscountFactor = data.get("TC_D007").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D007").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D007").get("DiscountYear");
	    	DiscountDate = data.get("TC_D007").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D007").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"));
	        System.out.println(str);
	        Assert.assertEquals(str,ErrorMessage);
	    	
	    }

	    
	    @Test
	    public void TC_D009(){
	    	System.out.println("TC_D009");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D009").get("PPA");
	    	InvoiceNumber = data.get("TC_D009").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D009").get("VendorRefNumber");
	    	PONumber = data.get("TC_D009").get("PONumber");
	    	GRNNumber = data.get("TC_D009").get("GRNNumber");
	    	BillAmount = data.get("TC_D009").get("BillAmount");
	    	DiscountFactor = data.get("TC_D009").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D009").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D009").get("DiscountYear");
	    	DiscountDate = data.get("TC_D009").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D009").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"));
	        System.out.println(str);
	        Assert.assertEquals(str,ErrorMessage);	
	    }
	    	
		@Test
	    public void TC_D010(){
	    	System.out.println("TC_D010");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D010").get("PPA");
	    	InvoiceNumber = data.get("TC_D010").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D010").get("VendorRefNumber");
	    	PONumber = data.get("TC_D010").get("PONumber");
	    	GRNNumber = data.get("TC_D010").get("GRNNumber");
	    	BillAmount = data.get("TC_D010").get("BillAmount");
	    	DiscountFactor = data.get("TC_D010").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D010").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D010").get("DiscountYear");
	    	DiscountDate = data.get("TC_D010").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D010").get("ErrorMessage");
	    	
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.gettext(By.xpath("//div[contains(text(),'Invoice Number already exists.')]");
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
		}
		
		
	    
	    @Test
	    public void TC_D013(){
	    	System.out.println("TC_D013");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D013").get("PPA");
	    	InvoiceNumber = data.get("TC_D013").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D013").get("VendorRefNumber");
	    	PONumber = data.get("TC_D013").get("PONumber");
	    	GRNNumber = data.get("TC_D013").get("GRNNumber");
	    	BillAmount = data.get("TC_D013").get("BillAmount");
	    	DiscountFactor = data.get("TC_D013").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D013").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D013").get("DiscountYear");
	    	DiscountDate = data.get("TC_D013").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D013").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	    }
	 
	 
		@Test
	    public void TC_D014(){
	    	System.out.println("TC_D014");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D014").get("PPA");
	    	InvoiceNumber = data.get("TC_D014").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D014").get("VendorRefNumber");
	    	PONumber = data.get("TC_D014").get("PONumber");
	    	GRNNumber = data.get("TC_D014").get("GRNNumber");
	    	BillAmount = data.get("TC_D014").get("BillAmount");
	    	DiscountFactor = data.get("TC_D014").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D014").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D014").get("DiscountYear");
	    	DiscountDate = data.get("TC_D014").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D014").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.gettext(By.xpath("//div[contains(text(),'Vendor Invoice Number already exists.')]"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	    }
		
		@Test
	    public void TC_D018(){
	    	System.out.println("TC_D018");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D018").get("PPA");
	    	InvoiceNumber = data.get("TC_D018").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D018").get("VendorRefNumber");
	    	PONumber = data.get("TC_D018").get("PONumber");
	    	GRNNumber = data.get("TC_D018").get("GRNNumber");
	    	BillAmount = data.get("TC_D018").get("BillAmount");
	    	DiscountFactor = data.get("TC_D018").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D018").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D018").get("DiscountYear");
	    	DiscountDate = data.get("TC_D018").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D018").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	        
	    }
	    
	 
		@Test
	    public void TC_D022(){
	    	System.out.println("TC_D022");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D022").get("PPA");
	    	InvoiceNumber = data.get("TC_D022").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D022").get("VendorRefNumber");
	    	PONumber = data.get("TC_D022").get("PONumber");
	    	GRNNumber = data.get("TC_D022").get("GRNNumber");
	    	BillAmount = data.get("TC_D022").get("BillAmount");
	    	DiscountFactor = data.get("TC_D022").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D022").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D022").get("DiscountYear");
	    	DiscountDate = data.get("TC_D022").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D022").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	    
		}


		@Test
	    public void TC_D026(){
	    	System.out.println("TC_D026");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D026").get("PPA");
	    	InvoiceNumber = data.get("TC_D026").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D026").get("VendorRefNumber");
	    	PONumber = data.get("TC_D026").get("PONumber");
	    	GRNNumber = data.get("TC_D026").get("GRNNumber");
	    	BillAmount = data.get("TC_D026").get("BillAmount");
	    	DiscountFactor = data.get("TC_D026").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D026").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D026").get("DiscountYear");
	    	DiscountDate = data.get("TC_D026").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D026").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	  
		}


		@Test
	    public void TC_D027(){
	    	System.out.println("TC_D027");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D027").get("PPA");
	    	InvoiceNumber = data.get("TC_D027").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D027").get("VendorRefNumber");
	    	PONumber = data.get("TC_D027").get("PONumber");
	    	GRNNumber = data.get("TC_D027").get("GRNNumber");
	    	BillAmount = data.get("TC_D027").get("BillAmount");
	    	DiscountFactor = data.get("TC_D027").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D027").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D027").get("DiscountYear");
	    	DiscountDate = data.get("TC_D027").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D027").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	    
		}
		
		@Test
	    public void TC_D028(){
	    	System.out.println("TC_D028");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D028").get("PPA");
	    	InvoiceNumber = data.get("TC_D028").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D028").get("VendorRefNumber");
	    	PONumber = data.get("TC_D028").get("PONumber");
	    	GRNNumber = data.get("TC_D028").get("GRNNumber");
	    	BillAmount = data.get("TC_D028").get("BillAmount");
	    	DiscountFactor = data.get("TC_D028").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D028").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D028").get("DiscountYear");
	    	DiscountDate = data.get("TC_D028").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D028").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	        
		}

		
		@Test
	    public void TC_D030(){
	    	System.out.println("TC_D030");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D030").get("PPA");
	    	InvoiceNumber = data.get("TC_D030").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D030").get("VendorRefNumber");
	    	PONumber = data.get("TC_D030").get("PONumber");
	    	GRNNumber = data.get("TC_D030").get("GRNNumber");
	    	BillAmount = data.get("TC_D030").get("BillAmount");
	    	DiscountFactor = data.get("TC_D030").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D030").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D030").get("DiscountYear");
	    	DiscountDate = data.get("TC_D030").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D030").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	        
		}

		@Test
	    public void TC_D031(){
	    	System.out.println("TC_D031");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D031").get("PPA");
	    	InvoiceNumber = data.get("TC_D031").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D031").get("VendorRefNumber");
	    	PONumber = data.get("TC_D031").get("PONumber");
	    	GRNNumber = data.get("TC_D031").get("GRNNumber");
	    	BillAmount = data.get("TC_D031").get("BillAmount");
	    	DiscountFactor = data.get("TC_D031").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D031").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D031").get("DiscountYear");
	    	DiscountDate = data.get("TC_D031").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D031").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	        
		}

		
		@Test
	    public void TC_D032(){
	    	System.out.println("TC_D032");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D032").get("PPA");
	    	InvoiceNumber = data.get("TC_D032").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D032").get("VendorRefNumber");
	    	PONumber = data.get("TC_D032").get("PONumber");
	    	GRNNumber = data.get("TC_D032").get("GRNNumber");
	    	BillAmount = data.get("TC_D032").get("BillAmount");
	    	DiscountFactor = data.get("TC_D032").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D032").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D032").get("DiscountYear");
	    	DiscountDate = data.get("TC_D032").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D032").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	        
		}

		
		@Test
	    public void TC_D033(){
	    	System.out.println("TC_D033");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D033").get("PPA");
	    	InvoiceNumber = data.get("TC_D033").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D033").get("VendorRefNumber");
	    	PONumber = data.get("TC_D033").get("PONumber");
	    	GRNNumber = data.get("TC_D033").get("GRNNumber");
	    	BillAmount = data.get("TC_D033").get("BillAmount");
	    	DiscountFactor = data.get("TC_D033").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D033").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D033").get("DiscountYear");
	    	DiscountDate = data.get("TC_D033").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D033").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clear(By.id("pincap_loanbundle_account_disbursemententry_rateOfGrn"));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_rateOfGrn"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	        
		}
		
		@Test
	    public void TC_D035(){
	    	System.out.println("TC_D035");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D035").get("PPA");
	    	InvoiceNumber = data.get("TC_D035").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D035").get("VendorRefNumber");
	    	PONumber = data.get("TC_D035").get("PONumber");
	    	GRNNumber = data.get("TC_D035").get("GRNNumber");
	    	BillAmount = data.get("TC_D035").get("BillAmount");
	    	DiscountFactor = data.get("TC_D035").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D035").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D035").get("DiscountYear");
	    	DiscountDate = data.get("TC_D035").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D035").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clear(By.id("pincap_loanbundle_account_disbursemententry_rateOfGrn"));
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_rateOfGrn"),DiscountFactor);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_rateOfGrn"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	        
		}

		
		@Test
	    public void TC_D037(){
	    	System.out.println("TC_D037");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D037").get("PPA");
	    	InvoiceNumber = data.get("TC_D037").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D037").get("VendorRefNumber");
	    	PONumber = data.get("TC_D037").get("PONumber");
	    	GRNNumber = data.get("TC_D037").get("GRNNumber");
	    	BillAmount = data.get("TC_D037").get("BillAmount");
	    	DiscountFactor = data.get("TC_D037").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D037").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D037").get("DiscountYear");
	    	DiscountDate = data.get("TC_D037").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D037").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clear(By.id("pincap_loanbundle_account_disbursemententry_rateOfGrn"));
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_rateOfGrn"),DiscountFactor);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_rateOfGrn"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	        
		}
		
		@Test
	    public void TC_D039(){
	    	System.out.println("TC_D039");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D039").get("PPA");
	    	InvoiceNumber = data.get("TC_D039").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D039").get("VendorRefNumber");
	    	PONumber = data.get("TC_D039").get("PONumber");
	    	GRNNumber = data.get("TC_D039").get("GRNNumber");
	    	BillAmount = data.get("TC_D039").get("BillAmount");
	    	DiscountFactor = data.get("TC_D039").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D039").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D039").get("DiscountYear");
	    	DiscountDate = data.get("TC_D039").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D039").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	        
		}

		
		@Test
	    public void TC_D041(){
	    	System.out.println("TC_D041");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D041").get("PPA");
	    	InvoiceNumber = data.get("TC_D041").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D041").get("VendorRefNumber");
	    	PONumber = data.get("TC_D041").get("PONumber");
	    	GRNNumber = data.get("TC_D041").get("GRNNumber");
	    	BillAmount = data.get("TC_D041").get("BillAmount");
	    	DiscountFactor = data.get("TC_D041").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D041").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D041").get("DiscountYear");
	    	DiscountDate = data.get("TC_D041").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D041").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.gettext(By.xpath("//div[contains(text(),'Discount date must be between loan agreement period.')]"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);        
		}
		

		
//		@Test
//	    public void TC_D042(){
//	    	System.out.println("TC_D042");
//	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
//	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
//	    	PPA = data.get("TC_D042").get("PPA");
//	    	InvoiceNumber = data.get("TC_D042").get("InvoiceNumber");
//	    	VendorRefNumber = data.get("TC_D042").get("VendorRefNumber");
//	    	PONumber = data.get("TC_D042").get("PONumber");
//	    	GRNNumber = data.get("TC_D042").get("GRNNumber");
//	    	BillAmount = data.get("TC_D042").get("BillAmount");
//	    	DiscountFactor = data.get("TC_D042").get("DiscountFactor");
//	    	DiscountMonth = data.get("TC_D042").get("DiscountMonth");
//	    	DiscountYear = data.get("TC_D042").get("DiscountYear");
//	    	DiscountDate = data.get("TC_D042").get("DiscountDate");
//	    	ErrorMessage = data.get("TC_D042").get("ErrorMessage");
//	        
//	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
//	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
//	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
//	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
//	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
//	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
//	    	System.out.println(BillAmount);
//	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
//	    	oBrowser.selectVisibleItemFromDropdo	wn(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
//	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
//	    	oBrowser.clickElement(By.linkText(DiscountDate));
//	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
//	    	String str = oBrowser.gettext(By.xpath("//div[contains(text(),'Discount date must be between loan agreement period.')]"));
//	        System.out.println(str);
//	        Assert.assertEquals(str, ErrorMessage);        
//		}
		

		@Test
	    public void TC_D045(){
	    	System.out.println("TC_D045");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D045").get("PPA");
	    	InvoiceNumber = data.get("TC_D045").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D045").get("VendorRefNumber");
	    	PONumber = data.get("TC_D045").get("PONumber");
	    	GRNNumber = data.get("TC_D045").get("GRNNumber");
	    	BillAmount = data.get("TC_D045").get("BillAmount");
	    	DiscountFactor = data.get("TC_D045").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D045").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D045").get("DiscountYear");
	    	DiscountDate = data.get("TC_D045").get("DiscountDate");
	    	DisbursementMonth = data.get("TC_D045").get("DisbursementMonth");
	    	DisbursementYear = data.get("TC_D045").get("DisbursementYear");
	    	DisbursementDate = data.get("TC_D045").get("DisbursementDate");
	    	ErrorMessage = data.get("TC_D045").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_disbursementDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DisbursementMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DisbursementYear);
	    	oBrowser.clickElement(By.linkText(DisbursementDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.gettext(By.xpath("//div[contains(text(),'Disbursement date must be between loan agreement period.')]"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
		}
			
		
		@Test
	    public void TC_D050(){
	    	System.out.println("TC_D050");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D050").get("PPA");
	    	InvoiceNumber = data.get("TC_D050").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D050").get("VendorRefNumber");
	    	PONumber = data.get("TC_D050").get("PONumber");
	    	GRNNumber = data.get("TC_D050").get("GRNNumber");
	    	BillAmount = data.get("TC_D050").get("BillAmount");
	    	DiscountFactor = data.get("TC_D050").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D050").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D050").get("DiscountYear");
	    	DiscountDate = data.get("TC_D050").get("DiscountDate");
	    	DisbursementMonth = data.get("TC_D050").get("DisbursementMonth");
	    	DisbursementYear = data.get("TC_D050").get("DisbursementYear");
	    	DisbursementDate = data.get("TC_D050").get("DisbursementDate");
	    	DueMonth = data.get("TC_D050").get("DueMonth");
	    	DueYear = data.get("TC_D050").get("DueYear");
	    	DueDate = data.get("TC_D050").get("DueDate");
	    	ErrorMessage = data.get("TC_D050").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_disbursementDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DisbursementMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DisbursementYear);
	    	oBrowser.clickElement(By.linkText(DisbursementDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_dueDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DueMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DueYear);
	    	oBrowser.clickElement(By.linkText(DueDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.gettext(By.xpath("//div[contains(text(),'Due date must be after disbursement date.')]"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
		}
	*/
		
		/*
		@Test
	    public void TC_D054(){
	    	System.out.println("TC_D054");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D054").get("PPA");
	    	InvoiceNumber = data.get("TC_D054").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D054").get("VendorRefNumber");
	    	PONumber = data.get("TC_D054").get("PONumber");
	    	GRNNumber = data.get("TC_D054").get("GRNNumber");
	    	BillAmount = data.get("TC_D054").get("BillAmount");
	    	DiscountFactor = data.get("TC_D054").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D054").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D054").get("DiscountYear");
	    	DiscountDate = data.get("TC_D054").get("DiscountDate");
	    	InterestStartMonth = data.get("TC_D054").get(InterestStartMonth);
	    	InterestStartYear = data.get("TC_D054").get("InterestStartYear");
	    	InterestStartDate = data.get("TC_D054").get("InterestStartDate");
	    	ErrorMessage = data.get("TC_D054").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_settlementStartDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),InterestStartMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),InterestStartYear);
	    	oBrowser.clickElement(By.linkText(InterestStartDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.gettext(By.xpath("//div[contains(text(),'Settlment date can not be before disbursement date.')]"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
		}
		

		@Test
	    public void TC_D055(){
	    	System.out.println("TC_D055");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D055").get("PPA");
	    	InvoiceNumber = data.get("TC_D055").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D055").get("VendorRefNumber");
	    	PONumber = data.get("TC_D055").get("PONumber");
	    	GRNNumber = data.get("TC_D055").get("GRNNumber");
	    	BillAmount = data.get("TC_D055").get("BillAmount");
	    	DiscountFactor = data.get("TC_D055").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D055").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D055").get("DiscountYear");
	    	DiscountDate = data.get("TC_D055").get("DiscountDate");
	    	InterestStartMonth = data.get("TC_D055").get(InterestStartMonth);
	    	InterestStartYear = data.get("TC_D055").get("InterestStartYear");
	    	InterestStartDate = data.get("TC_D055").get("InterestStartDate");
	    	ErrorMessage = data.get("TC_D055").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	System.out.println(BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_settlementStartDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),InterestStartMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),InterestStartYear);
	    	oBrowser.clickElement(By.linkText(InterestStartDate));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.gettext(By.xpath("//div[contains(text(),'Settlment date can not be before disbursement date.')]"));
	        System.out.println(str);
	        Assert.assertEquals(str, ErrorMessage);
	        
		}
		
		
		@Test
	    public void TC_D057(){
	    	System.out.println("TC_D057");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D057").get("PPA");
	    	InvoiceNumber = data.get("TC_D057").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D057").get("VendorRefNumber");
	    	PONumber = data.get("TC_D057").get("PONumber");
	    	GRNNumber = data.get("TC_D057").get("GRNNumber");
	    	BillAmount = data.get("TC_D057").get("BillAmount");
	    	DiscountFactor = data.get("TC_D057").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D057").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D057").get("DiscountYear");
	    	DiscountDate = data.get("TC_D057").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D057").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	String str = oBrowser.GetDynamicData(By.id("pincap_loanbundle_account_disbursemententry_outstandingPrincipalAmount"),"value");
	    	System.out.println(str);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_plusOneDay"));
	    	String Str1 = oBrowser.GetDynamicData(By.id("pincap_loanbundle_account_disbursemententry_outstandingPrincipalAmount"), "value");
	    	System.out.println(Str1);
	      	Assert.assertNotEquals(str, Str1);
	      	
		}

		
		@Test
	    public void TC_D059(){
	    	System.out.println("TC_D059");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D059").get("PPA");
	    	InvoiceNumber = data.get("TC_D059").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D059").get("VendorRefNumber");
	    	PONumber = data.get("TC_D059").get("PONumber");
	    	GRNNumber = data.get("TC_D059").get("GRNNumber");
	    	BillAmount = data.get("TC_D059").get("BillAmount");
	    	DiscountFactor = data.get("TC_D059").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D059").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D059").get("DiscountYear");
	    	DiscountDate = data.get("TC_D059").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D059").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clear(By.id("pincap_loanbundle_account_disbursemententry_rateOfDiscount"));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_rateOfDiscount"));
	      	System.out.println(str);
	      	Assert.assertEquals(str, ErrorMessage);
		}

		
		@Test
	    public void TC_D061(){
	    	System.out.println("TC_D061");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D061").get("PPA");
	    	InvoiceNumber = data.get("TC_D061").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D061").get("VendorRefNumber");
	    	PONumber = data.get("TC_D061").get("PONumber");
	    	GRNNumber = data.get("TC_D061").get("GRNNumber");
	    	BillAmount = data.get("TC_D061").get("BillAmount");
	    	DiscountFactor = data.get("TC_D061").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D061").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D061").get("DiscountYear");
	    	DiscountDate = data.get("TC_D061").get("DiscountDate");
	    	ErrorMessage = data.get("TC_D061").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clear(By.id("pincap_loanbundle_account_disbursemententry_rateOfDiscount"));
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_rateOfDiscount"));
	      	System.out.println(str);
	      	Assert.assertEquals(str, ErrorMessage);
		}

		
		@Test
	    public void TC_D062(){
	    	System.out.println("TC_D062");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D062").get("PPA");
	    	InvoiceNumber = data.get("TC_D062").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D062").get("VendorRefNumber");
	    	PONumber = data.get("TC_D062").get("PONumber");
	    	GRNNumber = data.get("TC_D062").get("GRNNumber");
	    	BillAmount = data.get("TC_D062").get("BillAmount");
	    	DiscountFactor = data.get("TC_D062").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D062").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D062").get("DiscountYear");
	    	DiscountDate = data.get("TC_D062").get("DiscountDate");
	    	RateOfDiscount = data.get("TC_D062").get("RateOfDiscount");
	    	ErrorMessage = data.get("TC_D062").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	String str = oBrowser.GetDynamicData(By.id("pincap_loanbundle_account_disbursemententry_actualDisburseAmount"),"value");
	    	System.out.println(str);
	    	oBrowser.clear(By.id("pincap_loanbundle_account_disbursemententry_rateOfDiscount"));
	        oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_rateOfDiscount"), RateOfDiscount);
	        String Str1 = oBrowser.GetDynamicData(By.id("pincap_loanbundle_account_disbursemententry_actualDisburseAmount"),"value");
	        System.out.println(Str1);
	        Assert.assertNotEquals(str, Str1);
		}

		
		@Test
	    public void TC_D065(){
	    	System.out.println("TC_D065");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D065").get("PPA");
	    	InvoiceNumber = data.get("TC_D065").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D065").get("VendorRefNumber");
	    	PONumber = data.get("TC_D065").get("PONumber");
	    	GRNNumber = data.get("TC_D065").get("GRNNumber");
	    	BillAmount = data.get("TC_D065").get("BillAmount");
	    	DiscountFactor = data.get("TC_D065").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D065").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D065").get("DiscountYear");
	    	DiscountDate = data.get("TC_D065").get("DiscountDate");
	    	BankCharges = data.get("TC_D065").get("BankCharges");
	    	ErrorMessage = data.get("TC_D065").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_bankCharges"),BankCharges);
	    	System.out.println(BankCharges);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_bankCharges"));
	    	System.out.println(str);
	    	Assert.assertEquals(str, ErrorMessage);
		}

		
		@Test
	    public void TC_D067(){
	    	System.out.println("TC_D067");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D067").get("PPA");
	    	InvoiceNumber = data.get("TC_D067").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D067").get("VendorRefNumber");
	    	PONumber = data.get("TC_D067").get("PONumber");
	    	GRNNumber = data.get("TC_D067").get("GRNNumber");
	    	BillAmount = data.get("TC_D067").get("BillAmount");
	    	DiscountFactor = data.get("TC_D067").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D067").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D067").get("DiscountYear");
	    	DiscountDate = data.get("TC_D067").get("DiscountDate");
	    	BankCharges = data.get("TC_D067").get("BankCharges");
	    	ErrorMessage = data.get("TC_D067").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_bankCharges"),BankCharges);
	    	System.out.println(BankCharges);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_bankCharges"));
	    	System.out.println(str);
	    	Assert.assertEquals(str, ErrorMessage);
		}
		

		
		@Test
	    public void TC_D070(){
	    	System.out.println("TC_D070");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D070").get("PPA");
	    	InvoiceNumber = data.get("TC_D070").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D070").get("VendorRefNumber");
	    	PONumber = data.get("TC_D070").get("PONumber");
	    	GRNNumber = data.get("TC_D070").get("GRNNumber");
	    	BillAmount = data.get("TC_D070").get("BillAmount");
	    	DiscountFactor = data.get("TC_D070").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D070").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D070").get("DiscountYear");
	    	DiscountDate = data.get("TC_D070").get("DiscountDate");
	    	OtherCharges = data.get("TC_D070").get("OtherCharges");
	    	ErrorMessage = data.get("TC_D070").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_otherCharges"),OtherCharges);
	    	System.out.println(OtherCharges);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_otherCharges"));
	    	System.out.println(str);
	    	Assert.assertEquals(str, ErrorMessage);
		}
		
		
		@Test
	    public void TC_D072(){
	    	System.out.println("TC_D072");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D072").get("PPA");
	    	InvoiceNumber = data.get("TC_D072").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D072").get("VendorRefNumber");
	    	PONumber = data.get("TC_D072").get("PONumber");
	    	GRNNumber = data.get("TC_D072").get("GRNNumber");
	    	BillAmount = data.get("TC_D072").get("BillAmount");
	    	DiscountFactor = data.get("TC_D072").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D072").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D072").get("DiscountYear");
	    	DiscountDate = data.get("TC_D072").get("DiscountDate");
	    	OtherCharges = data.get("TC_D072").get("OtherCharges");
	    	ErrorMessage = data.get("TC_D072").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_otherCharges"),OtherCharges);
	    	System.out.println(OtherCharges);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_otherCharges"));
	    	System.out.println(str);
	    	Assert.assertEquals(str, ErrorMessage);
		}
		
		
		@Test
	    public void TC_D077(){
	    	System.out.println("TC_D077");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D077").get("PPA");
	    	InvoiceNumber = data.get("TC_D077").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D077").get("VendorRefNumber");
	    	PONumber = data.get("TC_D077").get("PONumber");
	    	GRNNumber = data.get("TC_D077").get("GRNNumber");
	    	BillAmount = data.get("TC_D077").get("BillAmount");
	    	DiscountFactor = data.get("TC_D077").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D077").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D077").get("DiscountYear");
	    	DiscountDate = data.get("TC_D077").get("DiscountDate");
	    	ActualAmountToDisburse = data.get("TC_D077").get("ActualAmountToDisburse");
	    	ErrorMessage = data.get("TC_D077").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clear(By.id("pincap_loanbundle_account_disbursemententry_actualDisburseAmount"));
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_actualDisburseAmount"),ActualAmountToDisburse);
	    	System.out.println(ActualAmountToDisburse);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	String str = oBrowser.HtmlToolTipText(By.id("pincap_loanbundle_account_disbursemententry_actualDisburseAmount"));
	    	System.out.println(str);
	    	Assert.assertEquals(str, ErrorMessage);
		}
		
		
		@Test
	    public void TC_D078(){
	    	System.out.println("TC_D078");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D078").get("PPA");
	    	InvoiceNumber = data.get("TC_D078").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D078").get("VendorRefNumber");
	    	PONumber = data.get("TC_D078").get("PONumber");
	    	GRNNumber = data.get("TC_D078").get("GRNNumber");
	    	BillAmount = data.get("TC_D078").get("BillAmount");
	    	DiscountFactor = data.get("TC_D078").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D078").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D078").get("DiscountYear");
	    	DiscountDate = data.get("TC_D078").get("DiscountDate");
	    	ActualAmountToDisburse = data.get("TC_D078").get("ActualAmountToDisburse");
	    	ErrorMessage = data.get("TC_D078").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	oBrowser.clear(By.id("pincap_loanbundle_account_disbursemententry_actualDisburseAmount"));
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_actualDisburseAmount"),ActualAmountToDisburse);
	    	System.out.println(ActualAmountToDisburse);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_submit"));
	    	boolean str = oBrowser.IsElementVisible(By.xpath("//div[contains(text(),'Amount to disburse should be less than disbursal limit')]"));
	    	System.out.println(str);
	    	
	    	Assert.assertEquals(str,true);
	    
		}

		
		@Test
	    public void TC_D079(){
	    	System.out.println("TC_D079");
	    	oBrowser.clickElement(By.xpath("(//a[@target='_self'])[1]"));
	    	oBrowser.clickElement(By.xpath("//input[@class='add_disbursement pure-button']"));
	    	PPA = data.get("TC_D079").get("PPA");
	    	InvoiceNumber = data.get("TC_D079").get("InvoiceNumber");
	    	VendorRefNumber = data.get("TC_D079").get("VendorRefNumber");
	    	PONumber = data.get("TC_D079").get("PONumber");
	    	GRNNumber = data.get("TC_D079").get("GRNNumber");
	    	BillAmount = data.get("TC_D079").get("BillAmount");
	    	DiscountFactor = data.get("TC_D079").get("DiscountFactor");
	    	DiscountMonth = data.get("TC_D079").get("DiscountMonth");
	    	DiscountYear = data.get("TC_D079").get("DiscountYear");
	    	DiscountDate = data.get("TC_D079").get("DiscountDate");
	    	ActualAmountToDisburse = data.get("TC_D079").get("ActualAmountToDisburse");
	    	Remarks = data.get("TC_D079").get("Remarks");
	    	ErrorMessage = data.get("TC_D079").get("ErrorMessage");
	        
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_pinnaclePaymentAdvice"),PPA);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_invoiceNumber"),InvoiceNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_vendorInvoiceNumber"),VendorRefNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_poNumber"),PONumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_grnNumber"),GRNNumber);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_originalDisbursementAmount"),BillAmount);
	    	oBrowser.clickElement(By.id("pincap_loanbundle_account_disbursemententry_discountDate"));
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-month']"),DiscountMonth);
	    	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@class='ui-datepicker-year']"),DiscountYear);
	    	oBrowser.clickElement(By.linkText(DiscountDate));
	    	String str = oBrowser.gettext(By.id("pincap_loanbundle_account_disbursemententry_basisAmount"));
	    	oBrowser.clear(By.id("pincap_loanbundle_account_disbursemententry_actualDisburseAmount"));
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_actualDisburseAmount"),ActualAmountToDisburse);
	    	System.out.println(ActualAmountToDisburse);
	    	oBrowser.setText(By.id("pincap_loanbundle_account_disbursemententry_remarks"),Remarks);
	    	Str1 = oBrowser.GetDynamicData(By.id("pincap_loanbundle_account_disbursemententry_basisAmount"),"value");
	    	System.out.println(Str1);
	    	Assert.assertNotEquals(str, Str1);      
		}
	*/
		
		
		
	}

