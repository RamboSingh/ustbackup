package pageObject;

import org.openqa.selenium.By;

public class ObjMyDocmnt {

	// My document year view and header
	public static By drp_taxyear = By.id("taxYears");
	public static By txt_header = By.xpath("//*[@id='skiptoMainContent']/div/div/div[3]/div/div[1]/div[1]/h1/strong");

	public static By img_mydoc = By.xpath("//*[@id='skiptoMainContent']/div/div/div[3]");
	
	public static By doc_year = By.id("yearbreadcrumbedit");
	public static By img_ldng = By.id("loadingResult");
	
	
	
	
}
