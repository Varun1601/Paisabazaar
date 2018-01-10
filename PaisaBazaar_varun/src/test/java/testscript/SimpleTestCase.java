package testscript;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import commonlibs.CommonDriver;
import commonlibs.ExcelDriver;
import commonlibs.Utils;

public class SimpleTestCase {
	
private List list;
ExcelDriver Excel=new ExcelDriver();
CommonDriver oBrowser = new CommonDriver();
Utils utils = new Utils();
Properties oProperty = new Properties();

String Str1,browser,URL,LoanAmount,EmploymentType,MonthlyIncome,Residence,EmailAddress,MobileNumber,LoanPurpose,EmployerName,BankAccount,CurrentEMI,FullName,Date,Month,Year;
String path = "C:\\Users\\Varun\\workspace\\PaisaBazaar\\target\\classes\\config\\config.properties";
HashMap<String, HashMap<String, String>> data = new HashMap<String, HashMap<String, String>>();

boolean value;

@Test
public void testcase1() throws InterruptedException{
	oProperty=utils.getProperties(path);
	Excel.openExcelWorkbook(oProperty.getProperty("PaisaBazaar"));
	data = Excel.ReadTestCaseData(oProperty.getProperty("PaisaBazaar"),oProperty.getProperty("Product1"));
	System.out.println("hello");
	browser = data.get("TC_006").get("browser");
	System.out.println(browser);
	URL = data.get("TC_006").get("URL");
	System.out.println(URL);
	oBrowser.setPageLoadTimeout(601);
	oBrowser.setElementDetectionTimeout(601);
	oBrowser.openBrowser(browser,URL);
	oBrowser.clickElement(By.xpath("//a[@href='/personal-loan/'][1]"));
	
	LoanAmount = data.get("TC_006").get("LoanAmount");
	EmploymentType = data.get("TC_006").get("EmploymentType");
	MonthlyIncome = data.get("TC_006").get("MonthlyIncome");
	Residence = data.get("TC_006").get("Residence");
	EmailAddress = data.get("TC_006").get("EmailAddress");
	MobileNumber = data.get("TC_006").get("MobileNumber");
	LoanPurpose = data.get("TC_006").get("LoanPurpose").trim();
	EmployerName = data.get("TC_006").get("EmployerName");
	BankAccount = data.get("TC_006").get("BankAccount");
	CurrentEMI = data.get("TC_006").get("CurrentEMI");
	FullName = data.get("TC_006").get("FullName");
	Date = data.get("TC_006").get("Date").trim();
	Month = data.get("TC_006").get("Month");
	Year = data.get("TC_006").get("Year");
	
	//PQ1
	oBrowser.setText(By.id("loan_amount"), LoanAmount);
	oBrowser.clickElement(By.id("select2-employment_type_id-container"));
	oBrowser.setText(By.id("monthly_income"), MonthlyIncome);
	oBrowser.clickElement(By.id("select2-city-container"));
	oBrowser.setText(By.xpath("//input[@class='select2-search__field']"), Residence);
	oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
	oBrowser.setText(By.id("email"), EmailAddress);	
	oBrowser.setText(By.id("mobile_number"), MobileNumber);
	oBrowser.EnableCheckbox(By.xpath("//label[@for='terms']"));
	oBrowser.clickElement(By.id("submit_first_step"));
	
	//PQ2
	try {
	Thread.sleep(3000);
  } catch (Exception e) {
    e.printStackTrace();
  }
	oBrowser.ExplicitWait(By.xpath("//select[@id = 'loan_type']"));
	oBrowser.selectVisibleItemFromDropdown(By.xpath("//select[@id = 'loan_type']"),LoanPurpose );
	oBrowser.setText(By.id("company_name"), EmployerName);
	oBrowser.clickElement(By.id("select2-salary_mode-container"));
	oBrowser.setText(By.xpath("//input[@class='select2-search__field']"),BankAccount);
	oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
	oBrowser.setText(By.id("current_emi"), CurrentEMI);
	oBrowser.setText(By.xpath("//input[@class='form-control name required']"), FullName);
	oBrowser.selectVisibleItemFromDropdown(By.id("day_of_birth"), Date);
	oBrowser.selectVisibleItemFromDropdown(By.id("month_of_birth"), Month);
	oBrowser.selectVisibleItemFromDropdown(By.id("year_of_birth"), Year);
//	oBrowser.clickElement(By.id("view-best-offers"));  //live
	oBrowser.clickElement(By.className("proceed-fcr2"));
//	oBrowser.LiUlValue(By.xpath("//ul[contains(@class, 'ul-loop')]//img"));
//	try {
//	Thread.sleep(3000);
//} catch (Exception e) {
//    e.printStackTrace();
//}
	
	oBrowser.ExplicitWait_click(By.xpath("//ul[@class='show-info nested_code']"));
	oBrowser.ExtractElements2();
	oBrowser.IsElementEnabled(By.xpath("//quote[@class='cat_toggle'][contains(text(), 'View more offers')]"));
	//oBrowser.ExtractElements(By.xpath("//ul[@class='show-info nested_code']"));
	Excel.close(oProperty.getProperty("PaisaBazaar"));
	Excel.openExcelWorkbook(oProperty.getProperty("PaisaBazaar"));
	String Product1 = oProperty.getProperty("Product1");
	String PaisaBazaar = oProperty.getProperty("PaisaBazaar");
	oBrowser.Ullitabledata1("//ul[contains(@class, 'ul-loop')]//button[text() = 'Apply']", "//ul[contains(@class, 'ul-loop')]//button[text() = 'Enquire']", Product1, PaisaBazaar, Excel, 2);
//	oBrowser.clickElement(By.xpath("//a[@class='close']"));
	oBrowser.ScrollPageUp2();
//	try {
//		Thread.sleep(3000);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
	oBrowser.clickElement(By.xpath("//a[contains(text(),'About Myself')]"));
	oBrowser.CloseBrTab();
//	"//ul[contains(@class, 'ul-loop')]//button[text() = 'Apply']"
//	int count = Excel.getCellCount(Product1, 2, PaisaBazaar);
//	System.out.println(count);
}

//@Test
//public void testcase2(){
//	try {
//		Thread.sleep(3000);
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
//
//	LoanAmount = data.get("TC_002").get("LoanAmount");
//	EmploymentType = data.get("TC_002").get("EmploymentType");
//	MonthlyIncome = data.get("TC_002").get("MonthlyIncome");
//	Residence = data.get("TC_002").get("Residence");
//	EmailAddress = data.get("TC_002").get("EmailAddress");
//	MobileNumber = data.get("TC_002").get("MobileNumber");
//	LoanPurpose = data.get("TC_002").get("LoanPurpose").trim();
//	EmployerName = data.get("TC_002").get("EmployerName");
//	BankAccount = data.get("TC_002").get("BankAccount");
//	CurrentEMI = data.get("TC_002").get("CurrentEMI");
//	FullName = data.get("TC_002").get("FullName");
//	Date = data.get("TC_002").get("Date").trim();
//	Month = data.get("TC_002").get("Month");
//	Year = data.get("TC_002").get("Year");
//	
//	// PQ1
//	oBrowser.clear(By.id("loan_amount"));
//	oBrowser.setText(By.id("loan_amount"), LoanAmount);
//	oBrowser.clickElement(By.id("select2-employment_type_id-container"));
//	oBrowser.clear(By.id("monthly_income"));
//	oBrowser.setText(By.id("monthly_income"), MonthlyIncome);
//	oBrowser.clickElement(By.id("select2-city-container"));
//	oBrowser.setText(By.xpath("//input[@class='select2-search__field']"), Residence);
//	oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
//	oBrowser.clear(By.id("email"));
//	oBrowser.setText(By.id("email"), EmailAddress);	
//	oBrowser.clear(By.id("mobile_number"));
//	oBrowser.setText(By.id("mobile_number"), MobileNumber);
//	oBrowser.EnableCheckbox(By.xpath("//label[@for='terms']"));
//	oBrowser.clickElement(By.id("submit_first_step"));
//	
//    //PQ2
//	oBrowser.selectVisibleItemFromDropdown(By.id("loan_type"),LoanPurpose );
//	oBrowser.clear(By.id("company_name"));
//	oBrowser.setText(By.id("company_name"), EmployerName);
//	oBrowser.clickElement(By.id("select2-salary_mode-container"));
//	oBrowser.setText(By.xpath("//input[@class='select2-search__field']"),BankAccount);
//	oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
//	oBrowser.clear(By.id("current_emi"));
//	oBrowser.setText(By.id("current_emi"), CurrentEMI);
//	oBrowser.clear(By.xpath("//input[@class='form-control name required']"));
//	oBrowser.setText(By.xpath("//input[@class='form-control name required']"), FullName);
//	oBrowser.selectVisibleItemFromDropdown(By.id("day_of_birth"), Date);
//	oBrowser.selectVisibleItemFromDropdown(By.id("month_of_birth"), Month);
//	oBrowser.selectVisibleItemFromDropdown(By.id("year_of_birth"), Year);
////	oBrowser.clickElement(By.id("view-best-offers"));  //live
//	oBrowser.clickElement(By.className("proceed-fcr2"));
////  oBrowser.LiUlValue(By.xpath("//ul[contains(@class, 'ul-loop')]//img"));
//	try {
//	Thread.sleep(3000);
//} catch (Exception e) {
//    e.printStackTrace();
//}
//	oBrowser.ExtractElements(By.xpath("//ul[@class='show-info nested_code']"));
//	Excel.close(oProperty.getProperty("PaisaBazaar"));
//	Excel.openExcelWorkbook(oProperty.getProperty("PaisaBazaar"));
//	String Product1 = oProperty.getProperty("Product1");
//	String PaisaBazaar = oProperty.getProperty("PaisaBazaar");
//	oBrowser.Ullitabledata1("//ul[contains(@class, 'ul-loop')]//button[text() = 'Apply']", "//ul[contains(@class, 'ul-loop')]//button[text() = 'Enquire']", Product1, PaisaBazaar, Excel, 3);
////	oBrowser.clickElement(By.xpath("//a[@class='close']"));
//	oBrowser.ScrollPageUp2();
//	oBrowser.ScrollPageUp2();
//	try {
//		Thread.sleep(3000);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	oBrowser.clickElement(By.xpath("//a[contains(text(),'About Myself')]"));
//	oBrowser.CloseBrTab();
//	"//ul[contains(@class, 'ul-loop')]//button[text() = 'Apply']"
//	int count = Excel.getCellCount(Product1, 2, PaisaBazaar);
//	System.out.println(count);}
// }
//@Test
//public void testcase3(){
//	try {
//		Thread.sleep(3000);
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
//
//	LoanAmount = data.get("TC_003").get("LoanAmount");
//	EmploymentType = data.get("TC_003").get("EmploymentType");
//	MonthlyIncome = data.get("TC_003").get("MonthlyIncome");
//	Residence = data.get("TC_003").get("Residence");
//	EmailAddress = data.get("TC_003").get("EmailAddress");
//	MobileNumber = data.get("TC_003").get("MobileNumber");
//	LoanPurpose = data.get("TC_003").get("LoanPurpose").trim();
//	EmployerName = data.get("TC_003").get("EmployerName");
//	BankAccount = data.get("TC_003").get("BankAccount");
//	CurrentEMI = data.get("TC_003").get("CurrentEMI");
//	FullName = data.get("TC_003").get("FullName");
//	Date = data.get("TC_003").get("Date").trim();
//	Month = data.get("TC_003").get("Month");
//	Year = data.get("TC_003").get("Year");
//	
//	// PQ1
//	oBrowser.clear(By.id("loan_amount"));
//	oBrowser.setText(By.id("loan_amount"), LoanAmount);
//	oBrowser.clickElement(By.id("select2-employment_type_id-container"));
//	oBrowser.clear(By.id("monthly_income"));
//	oBrowser.setText(By.id("monthly_income"), MonthlyIncome);
//	oBrowser.clickElement(By.id("select2-city-container"));
//	oBrowser.setText(By.xpath("//input[@class='select2-search__field']"), Residence);
//	oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
//	oBrowser.clear(By.id("email"));
//	oBrowser.setText(By.id("email"), EmailAddress);	
//	oBrowser.clear(By.id("mobile_number"));
//	oBrowser.setText(By.id("mobile_number"), MobileNumber);
//	oBrowser.EnableCheckbox(By.xpath("//label[@for='terms']"));
//	oBrowser.clickElement(By.id("submit_first_step"));
//	
//    //PQ2
//	oBrowser.selectVisibleItemFromDropdown(By.id("loan_type"),LoanPurpose );
//	oBrowser.clear(By.id("company_name"));
//	oBrowser.setText(By.id("company_name"), EmployerName);
//	oBrowser.clickElement(By.id("select2-salary_mode-container"));
//	oBrowser.setText(By.xpath("//input[@class='select2-search__field']"),BankAccount);
//	oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
//	oBrowser.clear(By.id("current_emi"));
//	oBrowser.setText(By.id("current_emi"), CurrentEMI);
//	oBrowser.clear(By.xpath("//input[@class='form-control name required']"));
//	oBrowser.setText(By.xpath("//input[@class='form-control name required']"), FullName);
//	oBrowser.selectVisibleItemFromDropdown(By.id("day_of_birth"), Date);
//	oBrowser.selectVisibleItemFromDropdown(By.id("month_of_birth"), Month);
//	oBrowser.selectVisibleItemFromDropdown(By.id("year_of_birth"), Year);	
//	oBrowser.clickElement(By.className("proceed-fcr2"));
////  oBrowser.LiUlValue(By.xpath("//ul[contains(@class, 'ul-loop')]//img"));
//	oBrowser.ExtractElements(By.xpath("//ul[@class='show-info nested_code']"));
//	Excel.close(oProperty.getProperty("PaisaBazaar"));
//	Excel.openExcelWorkbook(oProperty.getProperty("PaisaBazaar"));
//	String Product1 = oProperty.getProperty("Product1");
//	String PaisaBazaar = oProperty.getProperty("PaisaBazaar");
//	oBrowser.Ullitabledata1("//ul[contains(@class, 'ul-loop')]//button[text() = 'Apply']", "//ul[contains(@class, 'ul-loop')]//button[text() = 'Enquire']", Product1, PaisaBazaar, Excel, 4);
////	oBrowser.clickElement(By.xpath("//a[@class='close']"));
//	oBrowser.ScrollPageUp2();
//	oBrowser.ScrollPageUp2();
//	try {
//		Thread.sleep(3000);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	oBrowser.clickElement(By.xpath("//a[contains(text(),'About Myself')]"));
//	oBrowser.CloseBrTab();
//}
//
//@Test
//public void testcase4(){
//	try {
//		Thread.sleep(3000);
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
//
//	LoanAmount = data.get("TC_004").get("LoanAmount");
//	EmploymentType = data.get("TC_004").get("EmploymentType");
//	MonthlyIncome = data.get("TC_004").get("MonthlyIncome");
//	Residence = data.get("TC_004").get("Residence");
//	EmailAddress = data.get("TC_004").get("EmailAddress");
//	MobileNumber = data.get("TC_004").get("MobileNumber");
//	LoanPurpose = data.get("TC_004").get("LoanPurpose").trim();
//	EmployerName = data.get("TC_004").get("EmployerName");
//	BankAccount = data.get("TC_004").get("BankAccount");
//	CurrentEMI = data.get("TC_004").get("CurrentEMI");
//	FullName = data.get("TC_004").get("FullName");
//	Date = data.get("TC_004").get("Date").trim();
//	Month = data.get("TC_004").get("Month");
//	Year = data.get("TC_004").get("Year");
//	
//	// PQ1
//	oBrowser.clear(By.id("loan_amount"));
//	oBrowser.setText(By.id("loan_amount"), LoanAmount);
//	oBrowser.clickElement(By.id("select2-employment_type_id-container"));
//	oBrowser.clear(By.id("monthly_income"));
//	oBrowser.setText(By.id("monthly_income"), MonthlyIncome);
//	oBrowser.clickElement(By.id("select2-city-container"));
//	oBrowser.setText(By.xpath("//input[@class='select2-search__field']"), Residence);
//	oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
//	oBrowser.clear(By.id("email"));
//	oBrowser.setText(By.id("email"), EmailAddress);	
//	oBrowser.clear(By.id("mobile_number"));
//	oBrowser.setText(By.id("mobile_number"), MobileNumber);
//	oBrowser.EnableCheckbox(By.xpath("//label[@for='terms']"));
//	oBrowser.clickElement(By.id("submit_first_step"));
//	
//    //PQ2
//	oBrowser.selectVisibleItemFromDropdown(By.id("loan_type"),LoanPurpose );
//	oBrowser.clear(By.id("company_name"));
//	oBrowser.setText(By.id("company_name"), EmployerName);
//	oBrowser.clickElement(By.id("select2-salary_mode-container"));
//	oBrowser.setText(By.xpath("//input[@class='select2-search__field']"),BankAccount);
//	oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
//	oBrowser.clear(By.id("current_emi"));
//	oBrowser.setText(By.id("current_emi"), CurrentEMI);
//	oBrowser.clear(By.xpath("//input[@class='form-control name required']"));
//	oBrowser.setText(By.xpath("//input[@class='form-control name required']"), FullName);
//	oBrowser.selectVisibleItemFromDropdown(By.id("day_of_birth"), Date);
//	oBrowser.selectVisibleItemFromDropdown(By.id("month_of_birth"), Month);
//	oBrowser.selectVisibleItemFromDropdown(By.id("year_of_birth"), Year);	
//	oBrowser.clickElement(By.className("proceed-fcr2"));
////  oBrowser.LiUlValue(By.xpath("//ul[contains(@class, 'ul-loop')]//img"));
//	oBrowser.ExtractElements(By.xpath("//ul[@class='show-info nested_code']"));
//	Excel.close(oProperty.getProperty("PaisaBazaar"));
//	Excel.openExcelWorkbook(oProperty.getProperty("PaisaBazaar"));
//	String Product1 = oProperty.getProperty("Product1");
//	String PaisaBazaar = oProperty.getProperty("PaisaBazaar");
//	oBrowser.Ullitabledata1("//ul[contains(@class, 'ul-loop')]//button[text() = 'Apply']", "//ul[contains(@class, 'ul-loop')]//button[text() = 'Enquire']", Product1, PaisaBazaar, Excel, 5);
////	oBrowser.clickElement(By.xpath("//a[@class='close']"));
//	oBrowser.ScrollPageUp2();
//	oBrowser.ScrollPageUp2();
//	try {
//		Thread.sleep(3000);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	oBrowser.clickElement(By.xpath("//a[contains(text(),'About Myself')]"));
//	oBrowser.CloseBrTab();
//}
//@Test
//public void testcase5(){
//	try {
//		Thread.sleep(3000);
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
//
//	LoanAmount = data.get("TC_005").get("LoanAmount");
//	EmploymentType = data.get("TC_005").get("EmploymentType");
//	MonthlyIncome = data.get("TC_005").get("MonthlyIncome");
//	Residence = data.get("TC_005").get("Residence");
//	EmailAddress = data.get("TC_005").get("EmailAddress");
//	MobileNumber = data.get("TC_005").get("MobileNumber");
//	LoanPurpose = data.get("TC_005").get("LoanPurpose").trim();
//	EmployerName = data.get("TC_005").get("EmployerName");
//	BankAccount = data.get("TC_005").get("BankAccount");
//	CurrentEMI = data.get("TC_005").get("CurrentEMI");
//	FullName = data.get("TC_005").get("FullName");
//	Date = data.get("TC_005").get("Date").trim();
//	Month = data.get("TC_005").get("Month");
//	Year = data.get("TC_005").get("Year");
//	
//	// PQ1
//	oBrowser.clear(By.id("loan_amount"));
//	oBrowser.setText(By.id("loan_amount"), LoanAmount);
//	oBrowser.clickElement(By.id("select2-employment_type_id-container"));
//	oBrowser.clear(By.id("monthly_income"));
//	oBrowser.setText(By.id("monthly_income"), MonthlyIncome);
//	oBrowser.clickElement(By.id("select2-city-container"));
//	oBrowser.setText(By.xpath("//input[@class='select2-search__field']"), Residence);
//	oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
//	oBrowser.clear(By.id("email"));
//	oBrowser.setText(By.id("email"), EmailAddress);	
//	oBrowser.clear(By.id("mobile_number"));
//	oBrowser.setText(By.id("mobile_number"), MobileNumber);
//	oBrowser.EnableCheckbox(By.xpath("//label[@for='terms']"));
//	oBrowser.clickElement(By.id("submit_first_step"));
//	
//    //PQ2
//	oBrowser.selectVisibleItemFromDropdown(By.id("loan_type"),LoanPurpose );
//	oBrowser.clear(By.id("company_name"));
//	oBrowser.setText(By.id("company_name"), EmployerName);
//	oBrowser.clickElement(By.id("select2-salary_mode-container"));
//	oBrowser.setText(By.xpath("//input[@class='select2-search__field']"),BankAccount);
//	oBrowser.Enter(By.xpath("//input[@class='select2-search__field']"));
//	oBrowser.clear(By.id("current_emi"));
//	oBrowser.setText(By.id("current_emi"), CurrentEMI);
//	oBrowser.clear(By.xpath("//input[@class='form-control name required']"));
//	oBrowser.setText(By.xpath("//input[@class='form-control name required']"), FullName);
//	oBrowser.selectVisibleItemFromDropdown(By.id("day_of_birth"), Date);
//	oBrowser.selectVisibleItemFromDropdown(By.id("month_of_birth"), Month);
//	oBrowser.selectVisibleItemFromDropdown(By.id("year_of_birth"), Year);	
//	oBrowser.clickElement(By.className("proceed-fcr2"));
////  oBrowser.LiUlValue(By.xpath("//ul[contains(@class, 'ul-loop')]//img"));
//	oBrowser.ExtractElements(By.xpath("//ul[@class='show-info nested_code']"));
//	Excel.close(oProperty.getProperty("PaisaBazaar"));
//	Excel.openExcelWorkbook(oProperty.getProperty("PaisaBazaar"));
//	String Product1 = oProperty.getProperty("Product1");
//	String PaisaBazaar = oProperty.getProperty("PaisaBazaar");
//	oBrowser.Ullitabledata1("//ul[contains(@class, 'ul-loop')]//button[text() = 'Apply']", "//ul[contains(@class, 'ul-loop')]//button[text() = 'Enquire']", Product1, PaisaBazaar, Excel, 6);
////	oBrowser.clickElement(By.xpath("//a[@class='close']"));
//	oBrowser.ScrollPageUp2();
//	oBrowser.ScrollPageUp2();
//	try {
//		Thread.sleep(3000);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	oBrowser.clickElement(By.xpath("//a[contains(text(),'About Myself')]"));
//	oBrowser.CloseBrTab();
//}
///*
//@Test
//public void testemptycollection(){
//	
//	list = new ArrayList();
//	Assert.assertTrue(list.isEmpty());
//	System.out.println("list is empty");
//}*/
}
