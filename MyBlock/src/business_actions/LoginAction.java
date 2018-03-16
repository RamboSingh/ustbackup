package business_actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.By;

import pageObject.ObjDashboard;
import pageObject.ObjLogin;
import utility.Config;
import utility.Constant_Class;

public class LoginAction {

	MyblockActions mblock;
	

	public LoginAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	// login function ** this is direct method
	public void loginCrdtl(String Susername, String Spassword) throws Exception {

		try {
			mblock.ElementExists(ObjLogin.txt_username);
			mblock.Element(ObjLogin.txt_username).sendKeys(Susername);
			mblock.Element(ObjLogin.txt_password).sendKeys(Spassword);
			mblock.Element(ObjLogin.btn_singin).click();
			boolean err_msg = mblock.ElementExists(ObjLogin.SecurityAnswerLink,3000);
			boolean err_msg1 = mblock.ElementExists(ObjDashboard.lnk_profile,5000);
			if(err_msg==false&&err_msg1==false){
			
			//if(val){
				boolean msg = false; 
				while(msg==false){
					mblock.objWebDriver.manage().deleteAllCookies();
					mblock.NavigateToURL();
					//mblock.objWebDriver.navigate().to("https://qaidp.hrblock.net/idp/profile/SAML2/Redirect/SSO?execution=e1s1");
					mblock.ElementExists(ObjLogin.txt_username);
					mblock.Element(ObjLogin.txt_username).sendKeys(Susername);
					mblock.Element(ObjLogin.txt_password).sendKeys(Spassword);
					mblock.Element(ObjLogin.btn_singin).click();
					boolean val  = mblock.ElementExists(ObjLogin.SecurityAnswerLink,3000);
					boolean val1 = mblock.ElementExists(ObjDashboard.lnk_profile,2000);
					
					if(val||val1){
						msg=true;
					}
					
					
				//}
			}
			}
			
			
			boolean val = mblock.ElementExists(ObjLogin.SecurityAnswerLink,3000);
			if(val){
			mblock.Element(ObjLogin.SecurityAnswerLink).click();

			mblock.ElementExists(ObjLogin.SecurityAnswer_Label, 5000);
			String SecAns = mblock.Element(ObjLogin.SecurityAnswer_Label).getText();
			Pattern p = Pattern.compile("(\\w+)\\?");
			Matcher m = p.matcher(SecAns);

			if (m.find()) {
				String str = m.group(m.groupCount());
				System.out.println(str);
				mblock.Element(ObjLogin.SecurityAnswer_TextField).sendKeys(str);
			
			}		
			mblock.Element(ObjLogin.SecurityAnswer_TextField).getText();
			mblock.ElementExists(ObjLogin.SecurityPage_Next);
			mblock.Element(ObjLogin.SecurityPage_Next).click();
			boolean license_value = mblock.ElementExists(ObjLogin.lcnse_agrmnt,5000);
			if(license_value){
				
				mblock.Element(ObjLogin.lcnse_agrmnt).click();
				boolean bnk_agrmnt = mblock.ElementExists(ObjLogin.bnkng_agrmnt,3000);
				if(bnk_agrmnt){
					mblock.Element(ObjLogin.bnkng_agrmnt).click();
				}
				mblock.Element(ObjLogin.btn_next).click();
				
			}
			
			}
		
			boolean dhs_brd = mblock.ElementExists(ObjDashboard.lnk_profile,5000);
			if(dhs_brd){
				mblock.ValidateTest(true, true, "successfully login");
			}
			else {
				mblock.ValidateTest(false, true, "Login issue");
				Assert.assertFalse(true);
			}
			
		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.assertFalse(true);
			
		}

	}

	// getting login credentials from excel and login the application ** this is
	// main login function
	public void login() throws Exception {
		try {
			mblock.actExcel.setExcelfile();
			mblock.actExcel.getCredentials();
			loginCrdtl(ExcelAction.crtnls[1], ExcelAction.crtnls[2]);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Verifying Welcome text message
	public void VerifyWelcomeText() throws Exception {
		try {

			boolean wlcmsg = mblock.ElementExists(ObjDashboard.txt_msg_h1, 5000);
			if (wlcmsg) {
				String txt1 = mblock.Element(ObjDashboard.txt_msg_h1).getText();
				String txt2 = mblock.Element(ObjDashboard.txt_msg_h2).getText();
				String txt3 = mblock.Element(ObjDashboard.txt_msg_P).getText();

				if (txt1.contains(Constant_Class.txt_wlcm.trim())
						&& txt2.equalsIgnoreCase(Constant_Class.txt_filing.trim())
						&& txt3.equalsIgnoreCase(Constant_Class.txt_msg.trim())) {

					mblock.Log("message is displayed");

				} else {

					mblock.Log("Message not displayed correctly");
					mblock.TakeSnapShot();
				}

			}

			else {
				System.out.println("Welcome message doesn't displayed");
				mblock.TakeSnapShot();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Click My Review Tax link button
	public void ClickReviewTaxbtn() throws Exception {
		try {

			boolean rvw_btn = mblock.ElementExists(ObjDashboard.lnk_RvwTax);
			if (rvw_btn) {
				mblock.Element(ObjDashboard.lnk_RvwTax).click();
				mblock.ElementExists(ObjDashboard.lnk_myblock);
				mblock.Element(ObjDashboard.lnk_myblock).click();
				mblock.ValidateTest(true, true, "Review button is displayed");
				
			} else {
				mblock.ValidateTest(false, true, "Review button is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
