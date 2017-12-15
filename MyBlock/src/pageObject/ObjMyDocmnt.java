package pageObject;

import org.openqa.selenium.By;

public class ObjMyDocmnt {

	// My document year view and header
	public static By drp_taxyear = By.id("taxYears");
	public static By txt_header = By.xpath("//*[@id='skiptoMainContent']/div/div/div[4]/div/div[1]/div[1]/h1/strong");


	public static By img_mydoc = By.xpath("//*[@id='skiptoMainContent']/div/div/div[4]");
	
	public static By doc_year = By.id("yearbreadcrumb");
	public static By img_ldng = By.id("loadingResult");
	
	public static By lnk_more = By.xpath("//*[@id='myb_udListDoc']/li/a");
	public static By lnk_view = By.xpath("//*[@id='myb_udListDoc']/li/span/a[1]");
	public static By lnk_edit = By.xpath("//*[@id='myb_udListDoc']/li/span/a[2]");
	public static By lnk_delete = By.xpath("//*[@id='myb_udListDoc']/li/span/a[3]");
	public static By txt_filename = By.xpath("//*[@id='myb_udListDoc']/li/p[1]/a");
	public static By lnk_category = By.id("hierarchybreadcrumb0");
	
	//Edit pop up field
	public static By lnk_trnsfyear = By.xpath("//a[@id='yearbreadcrumb']");
	public static By txt_flenme = By.id("filenm");
	public static By drp_catcry = By.xpath("//div[@id='catergoryEdit']/a");
	public static By btn_save = By.xpath("//*[@id='myb_docpreview']/div/div/fieldset/input[3]");
	public static By btn_cancel = By.id("btnCancel");
	
	public static By tble_cnt = By.id("docListTable");
	
	//Delete pop up filed
	
	public static By popup_del = By.id("mydocument_delete");
	public static By txt_line1 = By.xpath("//*[@id='delMsg']/p[1]");
	public static By txt_line2 = By.xpath("//*[@id='delMsg']/p[2]");
	public static By btn_ok = By.name("YES");
	public static By btn_cncel = By.name("NO");
	
	
	//Verbiage content
	
	public static By txt_hdr = By.xpath("//*[@id='skiptoMainContent']/div/div/div[5]/div/div[1]/h2");
	public static By txt_lne1 = By.xpath("//*[@id='skiptoMainContent']/div/div/div[5]/div/div[1]/p[1]");
	public static By txt_lne2 = By.xpath("//*[@id='skiptoMainContent']/div/div/div[5]/div/div[1]/p[2]");
	
	
	//Error messge while upload above 10mb file
	public static By err_msg = By.xpath("//*[@id='filelistingcontainer']/div/span[2]");	
}
