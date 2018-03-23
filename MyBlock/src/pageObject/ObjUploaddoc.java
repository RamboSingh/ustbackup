package pageObject;

import org.openqa.selenium.By;

public class ObjUploaddoc {

	//Online tax client 
	public static By txt_frstname = By.id("fname");
	public static By txt_lastname = By.id("lname");
	public static By txt_ssn = By.id("last4ssn");
	public static By txt_dob = By.id("clientmatchDOB");
	
	public static By btn_adddoc = By.linkText("Add a Document");
	public static By lnk_upldfle = By.linkText("Upload a file");
	public static By btn_cnfrmidnty = By.name("verifyid");
	public static By btn_submit= By.id("clk_submitidp");
	public static By btn_upld= By.id("docListTable");
	
	public static By count_doc= By.xpath("//ul[@id='myb_udListDoc']");
	public static By count_doc1= By.xpath("//ul[@id='myb_udListDoc']/li");
	
	public static By msg_box= By.id("filelistingcontainer");
	public static By msg_upldoc= By.xpath("//div['filelistingcontainer']/div");
	
}


