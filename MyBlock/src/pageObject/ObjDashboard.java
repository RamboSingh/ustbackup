package pageObject;

import org.openqa.selenium.By;

public class ObjDashboard {

	public static By txt_msg_h1 = By.xpath("//div[@id='skiptoMainContent']/div[1]/div/div/div[1]/h1/strong");
	public static By txt_msg_h2 = By.xpath("//div[@id='skiptoMainContent']/div[1]/div/div/div[1]/h2/strong");
	public static By txt_msg_P = By.xpath("//div[@id='skiptoMainContent']/div[1]/div/div/div[1]/p");
	public static By lnk_RvwTax = By.xpath("//div[@id='skiptoMainContent']/div[1]/div/div/div[2]/a[1]");
	public static By lnk_myblock = By.linkText("MyBlock");
	
	//upload doc button
	public static By lnk_updoc = By.xpath("//*[@id='skiptoMainContent']/div[1]/div/div/div[2]/a[1]");
	public static By lnk_updoc1 = By.xpath("//*[@id='skiptoMainContent']/div[1]/div/div/div[2]/a[2]");
	//All Global navigation
	public static By glb_nav = By.xpath("//*[@id='myb_contentNav']/div/div/div");
	
	//My taxes menu and sub menu 
	public static By lnk_mytax = By.id("mtlLi");
	public static By lnk_mytaxpro = By.id("mytaxpromenu");
	public static By lnk_mydonlinetax = By.id("myonlinetaxesmenu");
	public static By lnk_mydoc = By.id("documentTopLi");
	public static By lnk_taxhstry = By.id("historyTopLi");
	
	//Calculator&Tools menu and sub menu
	public static By lnk_calctr = By.id("tcLi");
	public static By lnk_taxestmr = By.id("estimatorTopLi");
	public static By lnk_w4calctr = By.id("w4plannerTopLi");
	public static By lnk_taxorgnzr = By.id("organizerTopLi");
	
	public static By btn_additem = By.id("organizer_additem");
	public static By wedit_itemtitle = By.id("addtextItemTitle");
	public static By lnk_category = By.id("hierarchybreadcrumb");
	public static By lnk_income = By.xpath("//div[@class='positionHelper']/div[1]/ul[2]/li[1]/a");//("//div[@class='four columns mbV']/div[2]/ul[2]/li[1]/a");
	public static By lnk_income1 = By.linkText("Interest income (Form 1099-INT)");//("//ul[@class='myb_fgContent fg-menu-scroll fg-menu-current']/li");
	public static By lnk_dede = By.linkText("Deductions and Expenses");
	public static By lnk_dede1 = By.linkText("Cash contributions");
	
	
	//Product and service menu and sub menu
	public static By lnk_prdctsrvce = By.id("pslLi");
	public static By lnk_makeappnmt = By.id("makeanappointmentmenu");
	public static By lnk_filngonline = By.id("filingonlinemenu");
	public static By lnk_pomlink = By.id("pommenulink");
	public static By lnk_taxidshield = By.id("tismenulink");
	public static By lnk_emrldcrd = By.className("mb_eclist");
	
	
	//My account menu and sub menu
	public static By lnk_profile = By.id("maLi");
	public static By lnk_mngacnt = By.xpath("//a[@title='Manage Account']");
	public static By lnk_prfle = By.xpath("//a[@title='Profile']");
	public static By lnk_help = By.xpath("//a[@title='Help']");
	public static By lnk_signout = By.xpath("//a[@title='Sign Out']");
	public static By userName = By.xpath(".//*[@id='userNameTN']");
	
	//Confirm my identity button 
	public static By btn_cnfmid = By.id("verifyIdp");
	public static By txt_frstname = By.id("firstName");
	public static By txt_lstname = By.id("lastName");
	public static By txt_dob = By.id("dob");
	public static By txt_ssn1 = By.id("ssn1");
	public static By txt_ssn2 = By.id("ssn2");
	public static By txt_ssn3 = By.id("ssn3");
	public static By btn_next = By.id("submitbtn");
	public static By iframe_idp = By.id("basic-modal-iframe-idp");
	public static By iframe_idp1 = By.id("basic-modal-idp");
	public static By MDiframe_idp1 = By.id("basic-modal-iframe");
	public static By MDiframe_idp2 = By.id("basic-modal-iframe");
	public static By THiframe_idp1 = By.id("basic-modal-iframe1");
	public static By THiframe_idp2 = By.id("basic-modal-iframe1");
	
	
	
	//Confirm identity _ your basic questions
	public static By qstn_col1 = By.xpath("//div[@class='col colQuestionnaire span_1_of_1']/p[1]");
	public static By answr_col1 = By.xpath("//ul[@class='gua_questionnaire'][1]/li");
	
	public static By qstn_col2 = By.xpath("//div[@class='col colQuestionnaire span_1_of_1']/p[2]");
	public static By answr_col2 = By.xpath("//ul[@class='gua_questionnaire'][2]/li");
	
	public static By qstn_col3 = By.xpath("//div[@class='col colQuestionnaire span_1_of_1']/p[3]");
	public static By answr_col3 = By.xpath("//ul[@class='gua_questionnaire'][3]/li");
	
	public static By qstn_col4 = By.xpath("//div[@class='col colQuestionnaire span_1_of_1']/p[4]");
	public static By answr_col4 = By.xpath("//ul[@class='gua_questionnaire'][4]/li");
	public static By btn_submit = By.id("submitbtn");
	public static By idp_Success = By.id("rdIDPStatusDiv1");
	public static By idp_Success_Content = By.className("emerview");
	public static By btn_ok = By.id("rdIDPStatusBtn1");
	
	//Emerland card pod
	
	public static By img_emrcard = By.xpath("//div[@id='skiptoMainContent']/div[9]/div/div[3]/div[1]/div[1]");
	public static By img_emrcard01 = By.xpath("//div[@id='skiptoMainContent']/div[10]/div/div[3]/div[1]/div[1]");
											
	
	public static By txt_opt1 = By.xpath("//div[@id='skiptoMainContent']/div[9]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[1]");
	public static By txt_opt01 = By.xpath("//div[@id='skiptoMainContent']/div[10]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[1]");
	public static By txt_opt2 = By.xpath("//div[@id='skiptoMainContent']/div[9]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[2]");
	public static By txt_opt02 = By.xpath("//div[@id='skiptoMainContent']/div[10]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[2]");
	public static By txt_opt3 = By.xpath("//div[@id='skiptoMainContent']/div[9]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[3]");
	public static By txt_opt03 = By.xpath("//div[@id='skiptoMainContent']/div[10]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[3]");
	public static By txt_popup1 = By.xpath("//div[@id='emeraldquestions']/div[2]/p[1]");
	public static By txt_popup2 = By.xpath("//div[@id='emeraldquestions']/div[2]/p[2]");
	public static By txt_popup3 = By.xpath("//div[@id='emeraldquestions']/div[2]/p[3]");
	public static By txt_popup4 = By.xpath("//div[@id='emeraldquestions']/div[2]/p[4]");
	
	
	public static By lnk_emracssmycard = By.id("hCDP");
	public static By lnk_qstn = By.id("ecard_questions");
	public static By lnk_crdntlst  = By.linkText("Card not listed");
	public static By txt_cardnumbr = By.id("block4");
	public static By txt_cvv = By.id("cvv");
	public static By txt_pin = By.id("pin");
	public static By box_iagree = By.xpath("//span[@id='box']");
	public static By btn_nxt = By.id("nxtBtn");
	public static By btn_okey = By.id("rdCardStatusBtn1");
	public static By iframe_card = By.id("basic-modal-iframe-cardproof");
	
	//POD position
	public static By img_pod1 = By.xpath("//*[@class='mb_container']/div[1]/div[1]/div[1]");
	public static By img_pod2 = By.xpath("//*[@class='mb_container']/div[2]/div[1]/div[1]");
	public static By img_pod3 = By.xpath("//*[@class='mb_container']/div[3]/div[1]/div[1]");
	
	//Schedule appointment POD
	public static By img_shdlapnmt = By.xpath("//*[@id='skiptoMainContent']/div[8]/div/div[3]/div/div[1]");
	public static By lnk_shdlapnmt = By.xpath("//*[@id='Schedule Appointment']");
	

	//Go to mytax POD
	public static By lnk_gomytax = By.xpath("//*[@id='Go to My Taxes']");
	
	//TIS POD
	public static By lnk_tis = By.xpath("//*[@id='tisViewDetails']");
	
	//W4 palnner POD
	public static By lnk_w4plnr = By.xpath("//*[@id='View my Planner']");
	
	//Tax history POD
	public static By lnk_viewtaxrtn  = By.linkText("View 2016 Tax Return");
	public static By lnk_viewhstry  = By.xpath("//*[@id='View History']");
	
	//Tax organizer POD
	public static By lnk_taxorg = By.xpath("//*[@id='viewOrganizer']");
	
	//My Document POD
	public static By lnk_mydocmnt = By.xpath("//*[@id='Upload Receipts']");
	
	//Tax Estimator POD
	public static By lnk_taxestmor = By.name("Estimate My Taxes");
	
	//Get assistant POD
	public static By lnk_assistant  = By.linkText("Get Assistance");
	
	//SSOD page object
	public static By txt_ssod = By.id("ssndob");
	public static By drpdwn_reset = By.id("ClearXml");
	public static By btn_reset = By.className("gua_buttonrt2");
	

	//FooterNav
	public static By footer_POM = By.id("pommenufooter");
	public static By footer_FilingOnline = By.id("filingonlinemenufooter");
	
	//HeroButtons
	public static By goToMyTaxes  = By.linkText("Go to my 2016 Taxes");
	public static By continueMyTaxes  = By.linkText("Continue My 2016 Taxes");
	
	//footer - copy right
	public static By txt_copyright = By.xpath("//div[@id='sb-site']/div[4]/div/div/ul/li[1]");
	public static By lnk_footermydoc = By.id("documentFLi");
	public static By lnk_footertaxhsty= By.id("taxhistoryFLi");
	public static By lnk_footermyonlinetax = By.id("mytaxonlinemenufooter");
	public static By lnk_footertaxpro= By.id("mytaxpromenufooter");
	public static By lnk_footertaxEstmtr = By.id("estimatorFLi");
	public static By lnk_footerw4calc = By.id("w4plannerFLi");
	public static By lnk_footerTaxorgnr = By.id("organizerFLi");
	public static By lnk_footermakeapnt = By.id("makeanappointmentmenufooter");
	public static By lnk_footerflngonlie = By.id("filingonlinemenufooter");
	public static By lnk_footermngeacnt = By.className("maFooter");
	public static By lnk_footerhelp = By.id("helpFLi");
	public static By lnk_footerprfle = By.id("profileFLi");
	public static By btn_help = By.className("mhrbbutton");
	
	//POD
	public static By lnk_getassistant = By.xpath("//*[@id='skiptoMainContent']/div[8]/div/div[2]/div/div[2]/a");
	public static By btn_contactus = By.id("mb_chatcontact");
	
	//Myblock logo
	public static By img_logo = By.xpath("//div[@id='navRighthighR']/div[1]/a/img");
	public static By img_logofooter = By.xpath("//*[@id='sb-site']/div[4]/div/div/a/img");
	
	//My tax pro page 
	public static By lnk_makeapnmt = By.linkText("Make An Appointment");
	
	
	//TIS 

	
	public static By pod_tis = By.xpath("//*[@id='skiptoMainContent']/div[8]/div/div[2]/div[1]/div[1]");
	public static By pod_tis1 = By.xpath("//*[@id='skiptoMainContent']/div[9]/div/div[2]/div[1]/div[1]");
	
	//Status Bar
		public static By StatusBar = By.className("myb_infobanner");
		public static By SB_THBtn = By.cssSelector(".mybPbutton");
		public static By SB_FedRefund = By.id("addressDiv");
		public static By SB_FilingStatus = By.id("filingDiv");
		public static By SB_FiledOn = By.id("prBusTransDtDiv");
		public static By SB_FiledWith = By.id("tPNameDiv");
}
