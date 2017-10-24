package utility;

import com.ustglobal.common.Utility;

import business_actions.MyblockActions;

public class Constant_Class {
	 
	 public static final String URL = "https://myaccountqa.hrblock.net/mytax/";
	 public static final String Digital_URL = "https://tcxqa.hrblock.net/hrblock/default_TPS.aspx";
	 
     public static final String Username = "winner1717";

     public static final String Password = "Welcome@1";
     
     public static final String txt_wlcm = "Welcome,";
     
     public static final String txt_filing = "FILING DONE, TAXES WON.";
     public static String Testcase_name = "";
     
     
     public static final String txt_msg = "Get a head start on next year -- upload your 2017 tax docs as you get them!";
     
     public static final String Path_TestData = System.getProperty("user.dir")+"/resources/TestData.xlsx";
     public static final String doc_uplScript="wscript" + " "+ System.getProperty("user.dir") + "\\resources\\script.vbs";
     public static final String doc_uplScript_dob="wscript" + " "+ System.getProperty("user.dir") + "\\resources\\scriptdob.vbs";
     public static final String doc_upldfile1 = "D://Ktb//Desert.jpg";
     
     public static final String URL1 = "http://devguaweb01.hrbinc.hrblock.net:8080/examples/QAADMINTOOL.jsp"; 
     public static final String tax_aboutyouheader = "Please select your filing status for 2016.";
     public static final String tax_incomeheader = "Please select all sources of income you had in 2016.";
     public static final String tax_expenseheader = "Please select all expenses you had in 2016.";
     public static final String tax_year = "2016";
     
     public static final String txt_copyright = "© 2016-2017 HRB Digital LLC. All Rights Reserved.";
     public static final String src_logo= "/mytax/images/fy17/mb_logo_myb_b.png";
     public static final String src_logofooter= "/mytax/images/fy17/mb_logo_hrb.png";
     
     public static final String tax_hdrnewusr = "LOOKS LIKE YOU HAVEN'T CONFIRMED YOUR IDENTITY YET.";
     public static final String tax_hdrsb1newusr = "If you previously worked with an H&R Block tax pro in a retail office, confirm your identity to view prior-year returns and your tax history.";
     public static final String tax_hdrsb2newusr = "(If you prepared your taxes online, sign in with your online account to get started.)";
     
     public static final String img_taxyear = "mytax/images/fy17/myb_banner_th.jpg";
     public static final String img_mydoc = "mytax/images/fy17/myb_banner_td.jpg";
     
     //Emerland card Qstn popup
     
     public static final String txt_popup1 = "If you have questions about your Emerald Card:";
     public static final String txt_popup2 = "Call 1-866-353-1266";
     public static final String txt_popup3 = "Or";
     public static final String txt_popup4 = "For Frequently Asked Questions, select the "+"\"View\"" +" link beside your Emerald Card Number to launch your Emerald Online Account. Then, select the "+"\"Questions?\""+" link from the top of your Emerald Online Account.";
     
     
     public static final String brsr = MyblockActions.browser;
     
     //My document delet popup text
     public static final String txt_line1 = "Are you sure you want to delete test?";
     public static final String txt_line2 = "This action is not recoverable.";
     
     //My document verbiage content
     public static final String txt_hdr ="UPLOAD AND MANAGE YOUR TAX DOCUMENTS";
     public static final String txt_lne1 = "Drag and drop your receipts, donations, and other tax-related items into your account. We'll upload them for you, then share them with your tax pro automatically.";
     public static final String txt_lne2 = "When you add documents to your MyBlock account, H&R Block might access them to assist you with tax preparation or enhance its services. Please see our";

     
}

