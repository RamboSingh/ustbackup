package pageObject;

import org.openqa.selenium.By;

public class ObjTaxHistory {

	//header and image
	public static By txt_taxhstry = By.xpath("//*[@id='iTaxHistory']/div[3]/div/div/div[1]/h1/strong");
	public static By img_taxhstry = By.xpath("//div[@id='iTaxHistory']/div[3]");
	
	//Tax history header and sub header for new user
	public static By txt_hdrnewuser = By.xpath("//div[@id='errorNoEfileDataTH']/div/div[1]/div/h3");
	public static By txt_hdrsb1newuser = By.xpath("//div[@id='errorNoEfileDataTH']/div/div[1]/div/p[1]");
	public static By txt_hdrsb2newuser = By.xpath("//div[@id='errorNoEfileDataTH']/div/div[1]/div/p[2]");
	
	//My tax year view
	public static By drp_taxyear = By.id("taxYears");
	
	//Return date and document 
	public static By txt_rtrnyr = By.xpath("//div[@id='viewMyReturnTHAvailable']/span");
	public static By rtn_doc_fld = By.xpath("//div[@id='taxyear']/span");
	public static By rtn_doc = By.xpath("//div[@id='taxyear']/span/a");

	//Tax return field
	public static By fed_rtrn_test = By.xpath("//*[@id='refundid']");
	public static By fed_rtrn = By.xpath("//*[@id='refundTHAmt']/h2");
	public static By txt_fed_rtrn_val = By.xpath("//*[@id='refundTHAmt']/span");
	public static By txt_fed_superscript = By.xpath("//*[@id='refundTHAmt']/span/sup");
	
	public static By fed_fillngSts = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[1]/div[2]/strong");
	public static By txt_fed_fillngSts_val = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[1]/div[2]/span");
	
	
	public static By fed_fillngDte= By.xpath("//*[@id='viewMyReturnTHAvailable']/strong");
	public static By txt_fed_fillngDte_val= By.xpath("//*[@id='viewMyReturnTHAvailable']/span");
	
	
	public static By state_rtrn = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/h2");
	public static By txt_state_rtrn_val = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/div");
	public static By txt_state_rtrn_superscript = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/div/sup");
	
	public static By btn_indicator = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/a[2]");
	
	public static By btn_navigation1 = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/p/a[1]");
	public static By btn_navigation2 = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/p/a[2]");
	
	public static By txt_state1 = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/div/ul/li[1]/div/div");
	public static By txt_state2 = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/div/ul/li[2]/div/div");
	
	public static By txt_state_rtrn_superscript1 = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/div/ul/li[1]/div/div[2]/sup");
	public static By txt_state_rtrn_superscript2 = By.xpath("//*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/div/ul/li[2]/div/div[2]/sup");
	
	//confirm my tax history
	
	public static By iframe_idp = By.id("basic-modal-iframe1");
	public static By txt_year = By.xpath("//div[@id='skiptoMainContent']/div/input");
	
	//Yoy
	public static By tbl_yoy = By.id("yeartoyeardetail");
	public static By txt_yoy = By.xpath("//*[@id='yeartoyeardetail']/div/div[1]/ul/li[1]/h2");
	public static By txt_yoy2016 = By.id("liYear1");
	public static By txt_yoy2015 = By.id("liYear2");
	public static By txt_yoyvar = By.xpath("//*[@id='yeartoyeardetail']/div/div[1]/ul/li[4]");
	
	//Tax year summary (YOY)
	public static By tbl_smry = By.id("yeartoyeartaxsummary");
	public static By txt_smry = By.xpath("//*[@id='yeartoyeartaxsummary']/div/div[1]/ul/li[1]/h2");
	public static By txt_smry2015 = By.id("liYearts1");
	public static By txt_smry2016 = By.id("liYearts1");
	public static By lnk_prchsrtn = By.linkText("Purchase My Return");
	
	
}
