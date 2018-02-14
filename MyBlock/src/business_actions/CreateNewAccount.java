package business_actions;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import pageObject.ObjCreateAccount;
import pageObject.ObjDashboard;
import pageObject.ObjLogin;

public class CreateNewAccount {

	MyblockActions mblock;

	public CreateNewAccount(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	// Click Create account button in login page
	public void clkCrtAcntBtn() throws Exception {

		try {
			boolean element1 = mblock.ElementExists(ObjLogin.btn_crtacnt);
			if (element1) {
				mblock.ValidateTest(true, true,"Create account link is displayed.");
				mblock.Element(ObjLogin.btn_crtacnt).click();
				
			} else {

				mblock.ValidateTest(false,true, "Create account link is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Entering the User details
	public void entrAcntDtlAndClkNext(String Susrnme, String Spswrd, String Semail) throws Exception {
		try {

			mblock.Element(ObjCreateAccount.txt_email).sendKeys(Semail);
			mblock.Element(ObjCreateAccount.txt_usrnme).sendKeys(Susrnme + Keys.TAB);
			// mblock.Element(ObjCreateAccount.txt_usrnme).sendKeys(Keys.TAB);
			boolean txt_ermsg = mblock.ElementExists(ObjCreateAccount.txt_errormsg);
			if (txt_ermsg) {
				String ousrnme = Susrnme.substring(0, 4);
				Date date=new Date(); // your date
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(date);
			    int year = cal.get(Calendar.YEAR);
			    int month = cal.get(Calendar.MONTH)+1;
			    int day = cal.get(Calendar.DAY_OF_MONTH);  //HOUR_OF_DAY=16,MINUTE=5,SECOND=40
			    int hour = cal.get(Calendar.HOUR_OF_DAY);
			    int minute = cal.get(Calendar.MINUTE);
			    int seconds = cal.get(Calendar.SECOND);
			    String usrnme = ousrnme+month+""+day+""+year+"_"+hour+""+minute+""+seconds;
				mblock.Element(ObjCreateAccount.txt_usrnme).clear();
				mblock.Element(ObjCreateAccount.txt_usrnme).sendKeys(usrnme);
			}
			mblock.Element(ObjCreateAccount.txt_pswrd).sendKeys(Spswrd);
			mblock.Element(ObjCreateAccount.txt_cnfrmpswrd).sendKeys(Spswrd);
			mblock.Element(ObjCreateAccount.btn_next).click();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.assertFalse(true);
			
		}
	}

	// 1st Security qstn and ans
	public void slctScrtyQnsAndAns1() throws Exception {
		try {
			Select oselect = new Select(mblock.Element(ObjCreateAccount.drpdwn_qstn1));
			oselect.selectByIndex(1);
			String val = mblock.Element(ObjCreateAccount.drpdwn_qstn1).getText();
			Pattern p = Pattern.compile("(\\w+)\\?");
			Matcher m = p.matcher(val);

			if (m.find()) {
				String str = m.group(m.groupCount());
				System.out.println(str);
				mblock.Element(ObjCreateAccount.txt_ans1).sendKeys(str);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 2nd Security qstn and ans
	public void slctScrtyQnsAndAns2() throws Exception {
		try {
			Select oselect = new Select(mblock.Element(ObjCreateAccount.drpdwn_qstn2));
			oselect.selectByIndex(1);
			String val = mblock.Element(ObjCreateAccount.drpdwn_qstn2).getText();
			Pattern p = Pattern.compile("(\\w+)\\?");
			Matcher m = p.matcher(val);

			if (m.find()) {
				String str = m.group(m.groupCount());
				System.out.println(str);
				mblock.Element(ObjCreateAccount.txt_ans2).sendKeys(str);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 3rd Security qstn and ans
	public void slctScrtyQnsAndAns3() throws Exception {
		try {
			Select oselect = new Select(mblock.Element(ObjCreateAccount.drpdwn_qstn3));
			oselect.selectByIndex(1);
			String val = mblock.Element(ObjCreateAccount.drpdwn_qstn3).getText();
			Pattern p = Pattern.compile("(\\w+)\\?");
			Matcher m = p.matcher(val);

			if (m.find()) {
				String str = m.group(m.groupCount());
				System.out.println(str);
				mblock.Element(ObjCreateAccount.txt_ans3).sendKeys(str);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Handle all the Security qnsts and ans in single method
	public void slctScrtyQnsAndAns() throws Exception {
		try {

			
			slctScrtyQnsAndAns1();
			slctScrtyQnsAndAns2();
			slctScrtyQnsAndAns3();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Uncheck if Remember check box is selected and Click I agree check box
	public void clkAgreeAndCrtacnt() throws Exception {
		try {

			boolean val = mblock.Element(ObjCreateAccount.input_rmbrme).isSelected();
			if (val) {
				mblock.Element(ObjCreateAccount.box_rmbrme).click();
			}
			boolean val1 = mblock.Element(ObjCreateAccount.input_iagree).isSelected();
			if (val1 == false) {
				mblock.Element(ObjCreateAccount.box_iagree).click();
			}

			mblock.Element(ObjCreateAccount.btn_crtacnt).click();
			mblock.WaitForPageLoad();
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void Verifyhomepage() throws Exception{
		try {
			boolean home_page = mblock.ElementExists(ObjDashboard.lnk_profile);
			if(home_page){
				mblock.ValidateTest(true, true,"New user Account has been Created");
			}
			else{
				mblock.ValidateTest(false, true,"New user Account is not Created");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Main method of Creating new Account Retail user
	public void crtacnt() throws Exception {
		try {
			clkCrtAcntBtn();
			mblock.actExcel.setExcelfile();
			mblock.actExcel.getCredentials();
			entrAcntDtlAndClkNext(ExcelAction.crtnls[1], ExcelAction.crtnls[2], ExcelAction.crtnls[3]);
			slctScrtyQnsAndAns();
			clkAgreeAndCrtacnt();
			Verifyhomepage();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	//Create Digital user method
	public void crtDigitalUsrAcnt() throws Exception {
		try {
			clkCrtAcntBtn();
			mblock.actExcel.setExcelfile();
			mblock.actExcel.getCredentials();
			entrAcntDtlAndClkNext(ExcelAction.crtnls[1], ExcelAction.crtnls[2], ExcelAction.crtnls[3]);
			slctScrtyQnsAndAns();
			clkAgreeAndCrtacnt();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
