package utility;

import business_actions.MyblockActions;

public class Constant_Class {

	 public static final String URL = "https://myaccountqa.hrblock.net/mytax/";
	 public static final String Digital_URL = "https://tcxqa.hrblock.net/hrblock/default_TPS.aspx";
	 
     public static final String Username = "winner1717";

     public static final String Password = "Welcome@1";
     
     public static final String txt_wlcm = "Welcome,";
     
     public static final String txt_filing = "FILING DONE, TAXES WON.";
     public static String Testcase_name = "";
     public static String browserName = null;   
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
}

