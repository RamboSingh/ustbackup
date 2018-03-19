package pageObject;

import org.openqa.selenium.By;

public class ObjDashboard {

	public static By txt_msg_h1 = By.xpath("//*[@id='skiptoMainContent']/div[4]/div/div/div[1]/h1");
	public static By txt_msg_h2 = By.xpath("//div[@id='skiptoMainContent']/div[4]/div/div/div[1]/h2/strong");
	public static By txt_msg_P = By.xpath("//div[@id='skiptoMainContent']/div[4]/div/div/div[1]/p");
	public static By lnk_RvwTax = By.xpath("//div[@id='skiptoMainContent']/div[4]/div/div/div[2]/a[1]");
	
	public static By lnk_myblock = By.linkText("MyBlock");
	
	//upload doc button
	public static By lnk_updoc = By.xpath("//*[@id='skiptoMainContent']/div[4]/div/div/div[2]/a[1]");
	public static By lnk_updoc1 = By.xpath("//*[@id='skiptoMainContent']/div[4]/div/div/div[2]/a[2]");
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
	
	//Category
	public static By lnk_category = By.id("hierarchybreadcrumb");
	
	//income category
	public static By lnk_income = By.linkText("Income");
	
	//income sub category
	public static By lnk_incmesub1 = By.linkText("Employer wages (Form W-2)");
	public static By lnk_incmesub2 = By.linkText("Self-employment or business income or loss (Schedule C)");
	public static By lnk_incmesub3 = By.linkText("Interest income (Form 1099-INT)");
	public static By lnk_incmesub4 = By.linkText("Dividend income (Form 1099-DIV)");
	public static By lnk_incmesub5 = By.linkText("Retirement income (Form 1099-R)");
	public static By lnk_incmesub6 = By.linkText("More");
	
	//More sub category in income
	//Default header and sub
	public static By dflt_sub1 = By.linkText("Other - Income");
	
	//employement income
	
	public static By empincm_sub1 = By.linkText("Employer wages (Form W-2)");
	public static By empincm_sub2 = By.linkText("State or local refund (Form 1099-G)");
	public static By empincm_sub3 = By.linkText("Unemployment income (Form 1099-G)");
	public static By empincm_sub4 = By.linkText("Foreign earned income");
	public static By empincm_sub5 = By.linkText("Canada Wages (Form T-4)");
	public static By empincm_sub6 = By.linkText("India Wages (Form 16)");
	
	//interest and invesment income
	public static By instmt_sub1 = By.linkText("Interest income (Form 1099-INT)");
	public static By instmt_sub2 = By.linkText("Dividend income (Form 1099-DIV)");
	public static By instmt_sub3 = By.linkText("Sale of stock and other assets (Form 1099-B)");
	public static By instmt_sub4 = By.linkText("Child's investment income");
	public static By instmt_sub5 = By.linkText("Trust or estate income or loss (Schedule K-1)");
	public static By instmt_sub6 = By.linkText("Undistributed capital gains");
	
	//Retirement And Social Security Income
	public static By rtmnt_sub1 = By.linkText("Retirement income (Form 1099-R)");
	public static By rtmnt_sub2 = By.linkText("Rail road retirement benefits (Form RRB-1099)");
	public static By rtmnt_sub3 = By.linkText("Social Security Income (Form SSA-1099)");
	
	//Business, Rental, Partnership, Farm and Royalties
	public static By bnsns_sub1 = By.linkText("Self-employment or business income or loss (Schedule C)");
	public static By bnsns_sub2 = By.linkText("Rental and royalty income or loss (Schedule E)");
	public static By bnsns_sub3 = By.linkText("Partnership income or loss (Schedule K-1)");
	public static By bnsns_sub4 = By.linkText("S corporation income or loss (Schedule K-1)");
	public static By bnsns_sub5 = By.linkText("Farming income or loss (Schedule F)");
	public static By bnsns_sub6 = By.linkText("Farm rental income or loss");
	public static By bnsns_sub7 = By.linkText("REMIC income or loss");
	
	
	//Miscellaneous Income
	
	public static By mscls_sub1 = By.linkText("Foreign account");
	public static By mscls_sub2 = By.linkText("Depreciable asset");
	public static By mscls_sub3 = By.linkText("Jury duty pay");
	public static By mscls_sub4 = By.linkText("Gambling income (Form W-2)");
	public static By mscls_sub5 = By.linkText("Alimony received");
	public static By mscls_sub6 = By.linkText("Qualified Education Programs (Form 1099Q)");
	public static By mscls_sub7 = By.linkText("Miscellaneous Income (Form 1099MISC)");
	public static By mscls_sub8 = By.linkText("Check");
	public static By mscls_sub9 = By.linkText("Cancellation of Debt (Form 1099C)");
	public static By mscls_sub10 = By.linkText("Foreclosure of Property (Form 1099A)");
	public static By mscls_sub11 = By.linkText("Payment Card and Third Party Transactions (Form 1099K)");
	public static By mscls_sub12 = By.linkText("Changes in Corporate Control and Capital Structure (Form 1099CAP)");
	
	
	//Deduction and expenses category
	public static By lnk_dede = By.linkText("Deductions and Expenses");
	
	//Deduction and expenses sub category
	public static By lnk_dedsub1 = By.linkText("Mortgage interest (Form 1098)");
	public static By lnk_dedsub2 = By.linkText("Tuition and expenses (Form 1098-T)");
	public static By lnk_dedsub3 = By.linkText("Cash contributions");
	public static By lnk_dedsub4 = By.linkText("Child and dependent care expenses");
	public static By lnk_dedsub5 = By.linkText("Real estate tax");
	public static By lnk_dedsub6 = By.linkText("Sales tax paid");
	public static By lnk_dedsub7 = By.linkText("More");
	
	//More sub category in Deduction and expenses category
	//Default header and sub
	
	public static By dfult_sub1 = By.linkText("Other - Adj. and Ded.");
	
	//Property
	public static By prpty_sub1 = By.linkText("Mortgage interest (Form 1098)");
	public static By prpty_sub2 = By.linkText("Real estate tax");
	public static By prpty_sub3 = By.linkText("Personal property tax");
	
	//Education
	public static By edutn_sub1 = By.linkText("Student loan interest (Form 1098-E)");
	public static By edutn_sub2 = By.linkText("Tuition and expenses (Form 1098-T)");
	 
	
	
	//Charitable Donations
	
	public static By chrt_sub1 = By.linkText("Cash contributions");
	
	//Health and Medical
	public static By hlth_sub1 = By.linkText("Medical expenses");
	public static By hlth_sub2 = By.linkText("Distributions from HSA or MSA (Form 1099-SA)");
	public static By hlth_sub3 = By.linkText("Archer MSA long-term care contract (Form 1099-LTC)");
	public static By hlth_sub4 = By.linkText("Health insurance premiums (Form 1095A)");
	public static By hlth_sub5 = By.linkText("Health insurance premiums (Form 1095C)");
	public static By hlth_sub6 = By.linkText("Health insurance premiums (Form 1095B)");
	
	//State and Local Taxes
	public static By stelcl_sub1 = By.linkText("Sales tax paid");
	
	//IRAs
	public static By irs_sub1 = By.linkText("IRA contributions and distributions");
	public static By irs_sub2 = By.linkText("Non-deductible IRA contributions");
	
	//Job Related Expenses
	public static By job_sub1 = By.linkText("Employee expenses");
	public static By job_sub2 = By.linkText("Job expenses");
	
	//Misc. Adj. and Ded.
	public static By misc_sub1 = By.linkText("Mortgage points");
	public static By misc_sub2 = By.linkText("Private mortgage insurance");
	public static By misc_sub3 = By.linkText("Health insurance premiums");
	public static By misc_sub4 = By.linkText("Education savings accounts");
	public static By misc_sub5 = By.linkText("Other deductible expenses");
	public static By misc_sub6 = By.linkText("Miscellaneous deductions");
	public static By misc_sub7 = By.linkText("Investment interest deduction");
	public static By misc_sub8 = By.linkText("Hurricane disaster retirement deduction");
	public static By misc_sub9 = By.linkText("Charitable contributions");
	public static By misc_sub10 = By.linkText("Alimony paid");
	public static By misc_sub11 = By.linkText("Charitable Contribution of Motor Vehicles, etc. (Form 1098C)");
	public static By misc_sub12 = By.linkText("Original Issue Discount (Form 1099OID)");
	
	//Default - Credits
	public static By dfltcrdt_sub1 = By.linkText("Other - Credits");
	public static By dfltcrdt_sub2 = By.linkText("Health Coverage Tax Credit - Adv. Payment (Form 1099H)");
	
	//Family
	public static By fmly_sub1 = By.linkText("Child and dependent care expenses");
	public static By fmly_sub2 = By.linkText("Adoption expenses");
	
	//Employment
	public static By emp_sub1 = By.linkText("Saver's credit");
	
	//Home Ownership
	
	public static By res_sub1 = By.linkText("Residential energy credit");
	public static By res_sub2 = By.linkText("Mortgage interest credit");
	
	//Misc. Credits
	public static By plug_sub1 = By.linkText("Plug-in electric drive motor vehicle credit");
	public static By plug_sub2 = By.linkText("Credit for prior year minimum tax");
	public static By plug_sub3 = By.linkText("Credit for the elderly or the disabled");
	public static By plug_sub4 = By.linkText("Health insurance premiums");
	
	
	//Default - Taxes
	public static By dfltax_sub1 = By.linkText("Other - Taxes");
	
	
	//Federal Tax Payments
	public static By fedtaxpay_sub1 = By.linkText("Estimated tax payments (Form 1040-ES)");
	
	//Misc. Tax Forms
	
	public static By misctax_sub1 = By.linkText("Household employee tax (Schedule H)");
	public static By misctax_sub2 = By.linkText("Children with unearned income");
	public static By misctax_sub3 = By.linkText("Prior Year Return (Form 1040A)");
	public static By misctax_sub4 = By.linkText("Prior Year Return (Form 1040EZ)");
	public static By misctax_sub5 = By.linkText("Prior Year Return (Form 1040NREZ)");
	public static By misctax_sub6 = By.linkText("Prior Year Return (Form 1040NR)");
	public static By misctax_sub7 = By.linkText("Prior Year Return (Form 1040)");
	public static By misctax_sub8 = By.linkText("Prior Year Return (Form 1120S)");
	public static By misctax_sub9 = By.linkText("Prior Year Return (Form 1120)");
	public static By misctax_sub10 = By.linkText("Brokerage Statement (Form 1099 Combination)");
	public static By misctax_sub11 = By.linkText("Taxable Distribution Received from Cooperatives (Form 1099PATR)");
	public static By misctax_sub12 = By.linkText("Receipt");
	
	//Product and service menu and sub menu
	public static By lnk_prdctsrvce = By.id("pslLi");
	public static By lnk_makeappnmt = By.id("makeanappointmentmenu");
	public static By lnk_filngonline = By.id("filingonlinemenu");
	public static By lnk_pomlink = By.id("pommenulink");
	public static By lnk_taxidshield = By.linkText("Tax Identity Shield");
	public static By lnk_emrldcrd = By.className("mb_eclist");
	public static By lnk_card = By.xpath("//*[@id='submenu-id']/li[1]/a");
	
	
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
	public static By TISiframe = By.id("hero-basic-modal-contentIdp");
	
	
	
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
	public static By idp_mdSuccess = By.id("rdDiv1");
	public static By idp_Success_Content = By.className("emerview");
	public static By btn_ok = By.id("rdIDPStatusBtn1");
	public static By btn_MDok = By.id("rdbtn1");
	
	//Emerland card pod
	
	public static By img_emrcard = By.xpath("//div[@id='skiptoMainContent']/div[9]/div/div[3]/div[1]/div[1]");
	public static By img_emrcard01 = By.xpath("//div[@id='skiptoMainContent']/div[10]/div/div[3]/div[1]/div[1]");
	public static By img_emrcard02 = By.xpath("//div[@id='skiptoMainContent']/div[8]/div/div[3]/div[1]/div[1]");
	public static By img_emrcard03 = By.xpath("//div[@id='skiptoMainContent']/div[7]/div/div[3]/div[1]/div[1]");
											
	
											
	
	public static By txt_opt1 = By.xpath("//div[@id='skiptoMainContent']/div[9]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[1]");
	public static By txt_opt2 = By.xpath("//div[@id='skiptoMainContent']/div[9]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[2]");
	public static By txt_opt3 = By.xpath("//div[@id='skiptoMainContent']/div[9]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[3]");
	
	public static By txt_opt01 = By.xpath("//div[@id='skiptoMainContent']/div[10]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[1]");
	public static By txt_opt02 = By.xpath("//div[@id='skiptoMainContent']/div[10]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[2]");
	public static By txt_opt03 = By.xpath("//div[@id='skiptoMainContent']/div[10]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[3]");
	
	public static By txt_opt001 = By.xpath("//div[@id='skiptoMainContent']/div[8]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[1]");
	public static By txt_opt002 = By.xpath("//div[@id='skiptoMainContent']/div[8]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[2]");
	public static By txt_opt003 = By.xpath("//div[@id='skiptoMainContent']/div[8]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[3]");
	
	public static By txt_opt0001 = By.xpath("//div[@id='skiptoMainContent']/div[7]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[1]");
	public static By txt_opt0002 = By.xpath("//div[@id='skiptoMainContent']/div[7]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[2]");
	public static By txt_opt0003 = By.xpath("//div[@id='skiptoMainContent']/div[7]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[3]");
	
	
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
	public static By iframe_card1 = By.id("basic-modal-cardproof");
	
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
	public static By lnk_mydocmnt = By.name("View My Documents");
	
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
	public static By prpreTaxMyslf  = By.linkText("Prepare Taxes Myself");
	public static By continueMyTaxes  = By.linkText("Continue My 2017 Taxes");
	
	//footer - copy right
	public static By txt_copyright = By.xpath("//div[@id='sb-site']/div[4]/div/div/div/ul/li[1]");
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
	public static By lnk_footertaxIdshiled = By.xpath("//*[@id='sb-site']/div[3]/div/div/div[3]/ul/li[3]/a");
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
		
		

		//TIS 

		public static By tis_newsletter = By.id("tisDisplay");
		public static By tis_newsletter_Header = By.xpath("//*[@id='skiptoMainContent']/div[4]/div/div[1]/div[1]/h1/strong");
		public static By tis_newsletter_content = By.xpath("//*[@id='skiptoMainContent']/div[4]/div/div[1]/div[1]/h2/strong");
		public static By tis_newsletter_expl = By.xpath("//*[@id='skiptoMainContent']/div[4]/div/div[1]/div[1]/p");
		public static By tis_RiskAssessment_Header = By.xpath("html/body/div[6]/div[2]/div[1]/div/div[1]/div[1]/h1/strong");
		public static By tis_RiskAssessmentr_content = By.xpath("html/body/div[6]/div[2]/div[1]/div/div[1]/div[1]/h2/strong");
		public static By tis_RiskAssessment_expl = By.xpath("html/body/div[6]/div[2]/div[1]/div/div[1]/div[1]/p");
		
		
		
		//Status Bar
		public static By RefundAmt = By.id("addressDiv");
		public static By StatusBarTHBtn = By.id("mybPbutton");
		public static By RefundID = By.id("refundid");
		public static By filingStatus = By.xpath("html/body/div[6]/div[2]/div/div[2]/div[5]/div/div[1]/div[1]/div[2]/span");
		public static By filingDate = By.xpath("html/body/div[6]/div[2]/div/div[2]/div[5]/div/div[1]/div[1]/div[3]/span");
		public static By filingStatusSts = By.id("filingDiv");
		public static By filingDateSts = By.id("prBusTransDtDiv");
		public static By TH = By.id("taxYears");
		
		
		//Dashboard Changes
		public static By Hero_WelcomeString = By.xpath("//*[@id='skiptoMainContent']/div[4]/div/div/div[1]/h1");
		public static By Hero_FNString = By.xpath("html/body/div[6]/div[2]/div[1]/div/div/div[1]/h1/span");
		public static By Hero_BoldText = By.xpath("//*[@id='skiptoMainContent']/div[4]/div/div/div[1]/h2/strong");
		public static By Hero_ExplanatoryText = By.xpath("//*[@id='skiptoMainContent']/div[4]/div/div/div[1]/p");
		public static By Hero_Btn = By.xpath("html/body/div[6]/div[2]/div[1]/div/div/div[2]/a");
		
		
		//Filling status
		public static By flng_sts = By.id("clFilingLi1");
}
