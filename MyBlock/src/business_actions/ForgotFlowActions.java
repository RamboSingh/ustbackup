package business_actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.boon.core.Sys;
import org.junit.Assert;

import pageObject.ObjForgotFlow;
import pageObject.ObjLogin;

public class ForgotFlowActions {
	MyblockActions mblock;
	public ForgotFlowActions(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	public void clkfflnk() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(ObjLogin.Lnk_forgotPass);
			if (element1){
				mblock.ValidateTest(true, true, "Forgot Username Password link is displayed");
				mblock.Element(ObjLogin.Lnk_forgotPass).click();
			}
			else {
				mblock.ValidateTest(false, true, "Forgot Username Password link is not displayed");
				Assert.assertFalse(true);
			}
				
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	// Enter the Email ID
	public void entremailandclknext (String Semail) throws Exception {
		try {
			boolean txt_emailid = mblock.ElementExists(ObjForgotFlow.txt_ffEmailid);
			if (txt_emailid){
			mblock.ValidateTest(true, true, "Let's find your account page is displayed");
			mblock.Element(ObjForgotFlow.txt_ffEmailid).sendKeys(Semail);
			mblock.Element(ObjForgotFlow.btn_ffEmailid_Next).click();
			}
			else {
				mblock.ValidateTest(false, true, "Let's find your account page is not displayed");
				Assert.assertFalse(true);
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.assertFalse(true);
		}
	
	}

	// click next button in Check your email page.
	
	public void chkuremailpgclknext () throws Exception {
		try {
			
			
			boolean btn_Nxt = mblock.ElementExists(ObjForgotFlow.btn_ffChkEmail_Next);
			if (btn_Nxt) {
				mblock.ValidateTest(true, true, "Check your email page is displayed");
				mblock.Element(ObjForgotFlow.btn_ffChkEmail_Next).click();						
			}
			else{
				mblock.ValidateTest(false, true, "Check your email page is not displayed");
				Assert.assertFalse(true);
			}
			}catch (Exception e) {
			
			// TODO: handle exception
			Assert.assertFalse(true);
		}
					}
	
	// Enter the Security code and click next button.
	
		public void entSecCodandclknext (String Semail) throws Exception {
			try {		
				boolean txt_secCod = mblock.ElementExists(ObjForgotFlow.txt_ffSecCode);
				if (txt_secCod) {
					mblock.ValidateTest(true, true, "Tell us your security code page is displayed");
					Semail = ExcelAction.crtnls[3];
					Connection conn1 = utility.DataBase_Connection.getConnection(); 
				    Statement st = conn1.createStatement();
			          String selectquery = "select FFResetCode from [FY18_QA_System_GUA].[dbo].[UsersEmailID_FF_ResetCode] where emailid='"+Semail+"'";
			           //Executing the SQL Query and store the results in ResultSet
			          ResultSet rs = st.executeQuery(selectquery);
			          //While loop to iterate through all data and print results
			                   
			          while (rs.next()) {
			                 //System.out.println(rs.getString("FFResetCode"));
			                 String SecCod = rs.getString("FFResetCode");
			                 mblock.Element(ObjForgotFlow.txt_ffSecCode).sendKeys(SecCod);
						     mblock.Element(ObjForgotFlow.btn_ffSecCode_Next).click();	
			          }
			           //Closing DB Connection
			        
			        conn1.close();
				}
				else{
					mblock.ValidateTest(false, true, "Tell us your security code is not displayed");
					//Assert.assertFalse(true);
				}
				}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				//Assert.assertFalse(true);
			}
						}
		
// Enter the details in PI search Page

	public void entrPISearchInfo(String FName, String LName, String DOB) throws Exception {
		try {

			String ssn1 = ExcelAction.crtnls[6].substring(0, 3);
			String ssn2 = ExcelAction.crtnls[6].substring(3, 5);
			String ssn3 = ExcelAction.crtnls[6].substring(5, 9);

			boolean txt_PISearchfld = mblock.ElementExists(ObjForgotFlow.txt_ffFName);

			if (txt_PISearchfld) {
				mblock.ValidateTest(true, true, "PI Search page is displayed");
				mblock.ElementExists(ObjForgotFlow.txt_ffFName);
				mblock.Element(ObjForgotFlow.txt_ffFName).sendKeys(FName);
				mblock.Element(ObjForgotFlow.txt_ffLName).sendKeys(LName);
				mblock.Element(ObjForgotFlow.txt_ffSSN1).sendKeys(ssn1);
				mblock.Element(ObjForgotFlow.txt_ffSSN2).sendKeys(ssn2);
				mblock.Element(ObjForgotFlow.txt_ffSSN3).sendKeys(ssn3);
				mblock.Element(ObjForgotFlow.txt_ffDOB).sendKeys(DOB);
				mblock.Element(ObjForgotFlow.btn_ffPISearch_Next).click();
				boolean err_msg = mblock.ElementExists(ObjForgotFlow.msg_ffPISearch_ErrMsg);
				if (err_msg){
					mblock.Element(ObjForgotFlow.txt_ffSSN1).sendKeys(ssn1);
					mblock.Element(ObjForgotFlow.txt_ffSSN2).sendKeys(ssn2);
					mblock.Element(ObjForgotFlow.txt_ffSSN3).sendKeys(ssn3);
					mblock.Element(ObjForgotFlow.btn_ffPISearch_Next).click();
				}
			}
			else{
				mblock.ValidateTest(false, true, "PI Search Page is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// Let's create an account Page

		public void crtacc() throws Exception {
			try {

				boolean btn_crtacc = mblock.ElementExists(ObjForgotFlow.btn_ffCrtAcc);

				if (btn_crtacc) {
					mblock.ValidateTest(true, true, " Let's create an account Page is displayed");
				mblock.Element(ObjForgotFlow.btn_ffCrtAcc).click();				
				}
				else{
					mblock.ValidateTest(false, true, " Let's create an account Page is not displayed");
					Assert.assertFalse(true);
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	// User selection.
	public void seluserandclkpwdreset() throws Exception{
	try {
		boolean rbtn_userlst = mblock.ElementExists(ObjForgotFlow.rbtn_UserList);
		if (rbtn_userlst) {
			mblock.ValidateTest(true, true, "Here are the accounts we found for you Page is displayed");
			mblock.ElementExists(ObjForgotFlow.rbtn_UserList);
			mblock.Element(ObjForgotFlow.rbtn_UserList).click();
			mblock.Element(ObjForgotFlow.btn_ffPUserList_Resetpwd).click();
		}
		else{
			mblock.ValidateTest(false, true, "Here are the accounts we found for you Page is not displayed");
			Assert.assertFalse(true);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		Assert.assertFalse(true);
	}

}
	
	public void clkpwdreset() throws Exception{
		try {

			String txt_userlst = mblock.Element(ObjForgotFlow.lbl_ffUserList_HdrTxt).getText();
			System.out.println(txt_userlst);
			String act_txt	= "Here's the account we found for you.";
	if (txt_userlst.equalsIgnoreCase(act_txt)) {
		mblock.Element(ObjForgotFlow.btn_ffPUserList_Resetpwd).click();
			
		}
	else{
		mblock.ValidateTest(false, true, "Here is the account we found for you Page is not displayed");
		Assert.assertFalse(true);
	}
} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	Assert.assertFalse(true);
}

}
	// Enter the Card Detail
		public void entrcrddtlandclknext () throws Exception {
			try {
				boolean txt_cardfld = mblock.ElementExists(ObjForgotFlow.txt_ffLast4Dig);
				if (txt_cardfld) {
					mblock.ValidateTest(true, true, "Verify Your Identity (Emerald Card) Page is displayed");
					mblock.ElementExists(ObjForgotFlow.txt_ffLast4Dig);
					mblock.Element(ObjForgotFlow.txt_ffLast4Dig).sendKeys(ExcelAction.crtnls[36]);
					mblock.Element(ObjForgotFlow.txt_ffPin).sendKeys(ExcelAction.crtnls[38]);
					mblock.Element(ObjForgotFlow.btn_ffCard_Next).click();
				}
				else{
					mblock.ValidateTest(false, true, "Verify Your Identity (Emerald Card) Page is not displayed");
					Assert.assertFalse(true);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Assert.assertFalse(true);
			}
		
		}

		// Enter the Security Answer
				public void entrseqansdtlandclknext () throws Exception {
					try {
						
						
						boolean txt_Seqfld = mblock.ElementExists(ObjForgotFlow.txt_ffSecAns);
						if (txt_Seqfld) {
							mblock.ValidateTest(true, true, "Verify Your Identity (Security Answer) Page is displayed");
							mblock.ElementExists(ObjForgotFlow.txt_ffSecAns, 5000);
							String SecAns = mblock.Element(ObjForgotFlow.lbl_ffSeqQuest).getText();
							Pattern p = Pattern.compile("(\\w+)\\?");
							Matcher m = p.matcher(SecAns);

							if (m.find()) {
								String str = m.group(m.groupCount());
								//System.out.println(str);
								mblock.Element(ObjForgotFlow.txt_ffSecAns).sendKeys(str);
							}
							mblock.Element(ObjForgotFlow.txt_ffSecAns).getText();
							mblock.Element(ObjForgotFlow.btn_ffSeqAns_Next).click();
						}
						else{
							mblock.ValidateTest(false, true, "Verify Your Identity (Security Answer) Page is not displayed");
							Assert.assertFalse(true);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						Assert.assertFalse(true);
					}
				
				}
	
				// Enter the New password and Confirm Password
				public void entrnewcnfpwdandclknext (String pwd) throws Exception {
					try {
						
						
						boolean txt_newfld = mblock.ElementExists(ObjForgotFlow.txt_ffNewPwd);
						if (txt_newfld) {
							mblock.ValidateTest(true, true, "Enter the New password and Confirm Password page is displayed");
							mblock.ElementExists(ObjForgotFlow.txt_ffNewPwd, 5000);
							mblock.Element(ObjForgotFlow.txt_ffNewPwd).sendKeys(pwd);
							mblock.Element(ObjForgotFlow.txt_ffCnfPwd).sendKeys(pwd);
							mblock.Element(ObjForgotFlow.chkbx_ffRemember).click();
							mblock.Element(ObjForgotFlow.btn_ffSave).click();
							boolean Err_msg = mblock.ElementExists(ObjForgotFlow.msg_ffErrMsg);
							if (Err_msg) {
								mblock.Element(ObjForgotFlow.txt_ffNewPwd).sendKeys("Testing@2");
								mblock.Element(ObjForgotFlow.txt_ffCnfPwd).sendKeys("Testing@2");
								mblock.Element(ObjForgotFlow.chkbx_ffRemember).click();
								mblock.Element(ObjForgotFlow.btn_ffSave).click();
							}
							
						}
						else{
							mblock.ValidateTest(false, true, "Password Changed successfully");
							Assert.assertFalse(true);
						}
						}catch (Exception e) {
						
						// TODO: handle exception
						e.printStackTrace();
						Assert.assertFalse(true);
					}
						
				
				}
	
				// click next in Password reset confirmation page.
				
				public void pwdcnfpgclknext () throws Exception {
					try {
						
						
						boolean btn_Nxt = mblock.ElementExists(ObjForgotFlow.btn_ffPwdCnf_Next);
						if (btn_Nxt) {
							mblock.ValidateTest(true, true, "Password reset confirmation page is displayed");
							mblock.Element(ObjForgotFlow.btn_ffPwdCnf_Next).click();						
						}
						else{
							mblock.ValidateTest(false, true, "Password reset confirmation page is not displayed");
							Assert.assertFalse(true);
						}
						}catch (Exception e) {
						
						// TODO: handle exception
						Assert.assertFalse(true);
					}
								}
				
				// Forgot Flow Email field validation
				public void fldValEmail() throws Exception {
					try {
						boolean element1 = mblock.ElementExists(ObjForgotFlow.txt_ffEmailid);
						if (element1) {
							mblock.ValidateTest(true, true, "Email field is displayed");
							String ActErrMsg1 = "Enter an e-mail address.";
							mblock.Element(ObjForgotFlow.btn_ffEmailid_Next).click();
							String AppErrMsg1 = mblock.Element(ObjForgotFlow.msg_ffEmailid_ErrMsg).getText();
							//System.out.println(AppErrMsg1);
						if (ActErrMsg1.equalsIgnoreCase(AppErrMsg1)) {
							mblock.ValidateTest(true, true, "Error Message is displayed as: "+ActErrMsg1);
							}
						String invalidemail[] = {"asJr8GymgUtcd6GE0pH0O5qS7qX8JyK6hKkLP4REesf1xaqWyzp5M9ZsOMbc4UF50b7zWINJN2Wm2TUdNC1wuE7fETrAjY610FPvSLBzVbr902rChFLm5BdKiGdsKzaaFKauZx4q8OyjR0RMV9P73BBPG1pEJ9sy8a87nE0vYutC621G0sicNt9yLRhB81zb7aD7jcgX0IvESl14yXN66jiPzDoG2gKOIl7hkr1Ksd", "@cjoncdfsaaes@hrblock.com", "cjeeones @hrblock.com", "cjontreteshrblock.com", "cjoncxves@.hrblock.com", "cjonxcvxes@hrblock.c", "me,@mail.com", "me.mail@email..com", "me@email.com."};
						int i = invalidemail.length;
						String ActErrMsg = "Enter a valid e-mail address.";
						for (i=0; i<=8; i++)
						{
						//System.out.println(invalidemail[i]);
						mblock.Element(ObjForgotFlow.txt_ffEmailid).clear();
						mblock.Element(ObjForgotFlow.txt_ffEmailid).sendKeys(invalidemail[i]);
						mblock.Element(ObjForgotFlow.btn_ffEmailid_Next).click();
						String AppErrMsg = mblock.Element(ObjForgotFlow.msg_ffEmailid_ErrMsg).getText();
						if(ActErrMsg.equalsIgnoreCase(AppErrMsg)){
							mblock.ValidateTest(true, true, "Error Message is displayed as: "+AppErrMsg);
							
						}
															
						}
						}
						else {

							mblock.ValidateTest(false, true, "Preferred Email field is not displayed");
							Assert.assertFalse(true);
						}

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

				// Main method for Forgot Flow with Security Code (Prospective/ID Proofed User) 
				public void SecCodProU() throws Exception {
					try {
						
						clkfflnk();
						mblock.actExcel.setExcelfile();
						mblock.actExcel.getCredentials();
						entremailandclknext(ExcelAction.crtnls[3]);
						chkuremailpgclknext();
						entSecCodandclknext(ExcelAction.crtnls[3]);
						clkpwdreset();
						entrseqansdtlandclknext();
						entrnewcnfpwdandclknext (ExcelAction.crtnls[2]);
						pwdcnfpgclknext();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				// Main method for Forgot Flow with Security Code (Card Proofed User) 
				public void SecCodCDPU() throws Exception {
					try {
						mblock.actExcel.setExcelfile();
						mblock.actExcel.getCredentials();
						clkfflnk();
						entremailandclknext(ExcelAction.crtnls[3]);
						chkuremailpgclknext();
						entSecCodandclknext(ExcelAction.crtnls[3]);
						clkpwdreset();
						entrcrddtlandclknext();
						entrnewcnfpwdandclknext (ExcelAction.crtnls[2]);
						pwdcnfpgclknext();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				// Main method for Forgot Flows PI search - Card Proofed Users 
				public void PISrchECU() throws Exception {
					try {
						mblock.actExcel.setExcelfile();
						mblock.actExcel.getCredentials();
						clkfflnk();
						entremailandclknext(ExcelAction.crtnls[3]);
						entrPISearchInfo(ExcelAction.crtnls[4], ExcelAction.crtnls[5], ExcelAction.crtnls[7]);
						seluserandclkpwdreset();
						entrcrddtlandclknext();
						entrnewcnfpwdandclknext (ExcelAction.crtnls[2]);
						pwdcnfpgclknext();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

				// Main method for Forgot Flows PI search - ID Proofed Users 
				public void PISrchIDPU() throws Exception {
					try {
						mblock.actExcel.setExcelfile();
						mblock.actExcel.getCredentials();
						clkfflnk();
						entremailandclknext(ExcelAction.crtnls[3]);
						entrPISearchInfo(ExcelAction.crtnls[4], ExcelAction.crtnls[5], ExcelAction.crtnls[7]);
						seluserandclkpwdreset();
						entrseqansdtlandclknext();
						entrnewcnfpwdandclknext (ExcelAction.crtnls[2]);
						pwdcnfpgclknext();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				// Main method for Forgot Flows PI search - Create Account 
				public void PISrchCtracnt() throws Exception {
					try {
						mblock.actExcel.setExcelfile();
						mblock.actExcel.getCredentials();
						clkfflnk();
						entremailandclknext(ExcelAction.crtnls[3]);
						entrPISearchInfo(ExcelAction.crtnls[4], ExcelAction.crtnls[5], ExcelAction.crtnls[7]);
						crtacc();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				// Field Validation in Let's find your account page. 
				public void fldandErrvalEmail() throws Exception {
					try {
						mblock.actExcel.setExcelfile();
						mblock.actExcel.getCredentials();
						clkfflnk();
						fldValEmail();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
}
