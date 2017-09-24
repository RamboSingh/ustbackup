package business_actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;
import pageObject.ObjDashboard;
import pageObject.ObjIntegratedServices;
import pageObject.ObjLogin;
import pageObject.ObjUploaddoc;
import pageObject.objPOM;
import utility.Constant_Class;

public class DashboardActions {

	MyblockActions mblock;

	public DashboardActions(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	// UserNameValidation

	public void userNameDisplayed() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.userName);
			if (element1) {
				mblock.Log("User Name field is present in Global Navigation Bar");
				mblock.ValidateTest(true, true, "User Name field is displayed");
				String userName = mblock.Element(ObjDashboard.userName).getText();
				int totalchar = userName.length();
				int visiblechar = 12;

				if (totalchar <= visiblechar) {
					if (userName == ExcelAction.crtnls[1]) {
						mblock.Log("User Name is less than or Eqaul to 12 characters and is displayed appropriately");
						mblock.ValidateTest(true, true, "User Name is displayed");
					}
				}
				if (totalchar > visiblechar) {
					String uNameEllipsed1 = userName.substring(13, 15);
					String after12 = "...";
					String uNameEllipsed = userName.substring(0, 12);
					String ExcelEllipsed = ExcelAction.crtnls[1].substring(0, 12);
					if (uNameEllipsed.equalsIgnoreCase(ExcelEllipsed)) {
						mblock.Log(
								"User Name is greater than or Eqaul to 12 characters and is displayed appropriately followed by ellipses");
						mblock.ValidateTest(true, true, "User Name is displayed");
						if (uNameEllipsed1.equalsIgnoreCase(after12)) {
							mblock.Log("User Name has ellipses after 12th character");
							mblock.ValidateTest(true, true, "User Name is displayed");

						}

					}

				}

			} else {
				mblock.ValidateTest(false, true, "User Name is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void Product_Services_Launch() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
			if (element1) {
				mblock.Log("Products and Services is present in Global Navigation Bar");
				mblock.ValidateTest(true, true, "Product and Services field is displayed");
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

			}

			else {
				mblock.ValidateTest(false, true, "Products and Services is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void POM_Launch() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_pomlink);
			if (element1) {
				mblock.Log("POM is present in Global Navigation Bar");
				mblock.ValidateTest(true, true, "POM is displayed");
				mblock.Element(ObjDashboard.lnk_pomlink).click();

			}

			else {
				mblock.ValidateTest(false, true, "POM is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void POM_LaunchFooter() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.footer_POM);
			if (element1) {
				mblock.Log("POM is present in Footer");
				mblock.ValidateTest(true, true, "POM is displayed");
				mblock.Element(ObjDashboard.footer_POM).click();

			}

			else {
				mblock.ValidateTest(false, true, "POM is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void POM_PageHeader() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(objPOM.POM_PageHeader);
			if (element1) {
				mblock.Log("POM page header is present");
				mblock.ValidateTest(true, true, "POM is displayed");
				String text = mblock.Element(objPOM.POM_PageHeader).getText();
				System.out.println(text);
				if (text.equalsIgnoreCase("Peace of Mind Extended Service Plan")) {
					mblock.Log("Page header is displayed");
					mblock.ValidateTest(true, true, "Page Header is displayed");
				}

			}

			else {
				mblock.ValidateTest(false, true, "Page header is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void POM_FAQ() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(objPOM.FreqAskedQuestions);
			if (element1) {
				mblock.Log("FAQ is present in POM Page");
				mblock.ValidateTest(true, true, "FAQ is displayed");
				mblock.Element(objPOM.FreqAskedQuestions).click();

				String FAQ_URL = mblock.GetCurrentURL();
				String Actual_URL = "https://hrbcomqa.hrblock.net/tax-offices/peace-of-mind-claims.html";
				if (Actual_URL.equalsIgnoreCase(FAQ_URL)) {
					mblock.Log("User is landed in FAQ Page");
					mblock.ValidateTest(true, true, "User lands in FAQ Page");
				} else {
					mblock.Log("User is not landed in FAQ Page");
					mblock.ValidateTest(true, true, "User is not landed in FAQ Page");
				}

			}

			else {
				mblock.ValidateTest(false, true, "FAQ is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void POM_Content() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(objPOM.POM_Content);
			if (element1) {
				mblock.Log("FAQ is present in POM Page");
				mblock.ValidateTest(true, true, "FAQ is displayed");
				String Act_Content = mblock.Element(objPOM.POM_Content).getText();
				String Exp_ContentTP = "CONGRATULATIONS!,\n"
						+ "You made a great decision to add our Peace of Mind® plan to your 2016 tax return. As a result, we at H&R Block have your back and will provide:\n"
						+ "Tax Notice Services - for any IRS letter or tax notice you receive, just bring it to us - we will review, research and respond to the tax authority.\n"
						+ "Audit Representation95 - an H&R Block Enrolled Agent will be available to represent you in the event of an audit. This can cost up to $1,000 without Peace of Mind®.\n"
						+ "Additional tax reimbursement - if we make a mistake, we'll reimburse you for up to $6,000 in additional taxes owed.\n"
						+ "IF YOU RECEIVED A LETTER FROM A TAX AUTHORITY\n"
						+ "Contact Linda Franklin or call 1-800-HRBLOCK.\n"
						+ "WHAT HAPPENS NEXT? HOW LONG WILL MY CLAIM TAKE?\n"
						+ "See our Frequently Asked Questions for answers.";
				String Exp_ContentAM = "CONGRATULATIONS!,\n"
						+ "You made a great decision to add our Peace of Mind® plan to your 2016 tax return. As a result, we at H&R Block have your back and will provide:\n"
						+ "Tax Notice Services - for any IRS letter or tax notice you receive, just bring it to us - we will review, research and respond to the tax authority.\n"
						+ "Audit Representation95 - an H&R Block Enrolled Agent will be available to represent you in the event of an audit. This can cost up to $1,000 without Peace of Mind®.\n"
						+ "Additional tax reimbursement - if we make a mistake, we'll reimburse you for up to $6,000 in additional taxes owed.\n"
						+ "IF YOU RECEIVED A LETTER FROM A TAX AUTHORITY\n"
						+ "Contact Linda Franklin or call 1-800-HRBLOCK.\n"
						+ "WHAT HAPPENS NEXT? HOW LONG WILL MY CLAIM TAKE?\n"
						+ "See our Frequently Asked Questions for answers.";
				if (Exp_ContentTP.equalsIgnoreCase(Act_Content)) {
					mblock.Log("POM Content is displayed");
					mblock.ValidateTest(true, true, "POM Content is displayed");
				} else if (Exp_ContentAM.equalsIgnoreCase(Act_Content)) {
					mblock.Log("POM Content is displayed");
					mblock.ValidateTest(true, true, "POM Content is displayed");
				}

			}

			else {
				mblock.ValidateTest(false, true, "Content is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void POM_Lnk95() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(objPOM.lnk_95);
			if (element1) {
				mblock.Log("95 is present in POM Page");
				mblock.ValidateTest(true, true, "95 is displayed");
				mblock.Element(objPOM.lnk_95).click();

				String FAQ_URL = mblock.GetCurrentURL();
				String Actual_URL = "https://hrbcomqa.hrblock.net/?odisc=95#/en/";
				if (Actual_URL.equalsIgnoreCase(FAQ_URL)) {
					mblock.Log("User is landed in 95 Page");
					mblock.ValidateTest(true, true, "User lands in 95 Page");
				} else {
					mblock.Log("User is not landed in FAQ Page");
					mblock.ValidateTest(true, true, "User is not landed in 95 Page");
				}

			}

			else {
				mblock.ValidateTest(false, true, "95 is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void POM_LetterFromAuthority() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(objPOM.LetterFromAuthority);
			if (element1) {
				mblock.Log("Link is present in POM Page");
				mblock.ValidateTest(true, true, "Link is displayed");
				mblock.Element(objPOM.LetterFromAuthority).isDisplayed();

			} else {
				mblock.ValidateTest(false, true, "Link is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void POM_AMLink() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(objPOM.BtnPOM);
			if (element1) {
				mblock.Log("Link is present in POM Page");
				mblock.ValidateTest(true, true, "Link is displayed");
				mblock.Element(objPOM.BtnPOM).isDisplayed();
				mblock.Element(objPOM.BtnPOM).click();

				String URL = "https://myaccountqa.hrblock.net/mytax/tax-offices/local-offices/index.html#!/en/office-locator/";
				if (URL.equalsIgnoreCase(mblock.GetCurrentURL())) {
					mblock.Log("User landed in AM Page");
					mblock.ValidateTest(true, true, "User lands in AM page");
				}
			} else {
				mblock.ValidateTest(false, true, "Link is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void POM_ApptLink() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(objPOM.AppointmentLink);
			if (element1) {
				mblock.Log("Link is present in POM Page");
				mblock.ValidateTest(true, true, "Link is displayed");
				mblock.Element(objPOM.AppointmentLink).isDisplayed();

			} else {
				mblock.ValidateTest(false, true, "Link is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void MD_AddDoc() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjUploaddoc.btn_adddoc);
			if (element1) {
				mblock.Log("Add Document is present");
				mblock.ValidateTest(true, true, "Add Document is present");
				mblock.Element(ObjUploaddoc.btn_adddoc).click();

			} else {
				mblock.ValidateTest(false, true, "Add Document is not present");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void Click_MD_IDP() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjUploaddoc.btn_cnfrmidnty);
			if (element1) {
				mblock.Log("Confirm Identity is present");
				mblock.ValidateTest(true, true, "Confirm Identity is present");
				mblock.Element(ObjUploaddoc.btn_cnfrmidnty).click();
				mblock.ValidateTest(true, true, "IDP Frame is displayed");

			} else {
				mblock.ValidateTest(false, true, "Confirm ID is not present");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void TH_Lauch() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_mytax);
			if (element1) {
				mblock.Log("My Taxes is present");
				mblock.ValidateTest(true, true, "My Taxes is present");
				mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();
				mblock.Element(ObjDashboard.lnk_taxhstry).click();

			} else {
				mblock.ValidateTest(false, true, "Add Document is not present");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void DashBoard_GoToMyTaxes() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.goToMyTaxes);
			if (element1) {
				mblock.Log("Go to My Taxes Button is present");
				mblock.ValidateTest(true, true, " Go to My Taxes is present");
				mblock.Element(ObjDashboard.goToMyTaxes).click();

			} else {
				mblock.ValidateTest(false, true, "Go to My Taxes Btn is not present");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void DashBoard_ContinueToMyTaxes() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.continueMyTaxes);
			if (element1) {
				mblock.Log("Continue My Taxes Button is present");
				mblock.ValidateTest(true, true, " Continue My Taxes is present");
				mblock.Element(ObjDashboard.continueMyTaxes).click();

			} else {
				mblock.ValidateTest(false, true, "Go to My Taxes Btn is not present");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void Footer_GoOnline() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.footer_FilingOnline);
			if (element1) {
				mblock.Log("Go to My Taxes Button is present");
				mblock.ValidateTest(true, true, " Go to My Taxes is present");
				mblock.Element(ObjDashboard.footer_FilingOnline).click();

			} else {
				mblock.ValidateTest(false, true, "Go to My Taxes Btn is not present");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void PAS_GoToMyTaxes() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
			if (element1) {
				mblock.Log("Product and Services is present");

				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();
				mblock.Element(ObjDashboard.lnk_filngonline).click();
				mblock.ValidateTest(true, true, "Filing Online is Clicked");

			} else {
				mblock.ValidateTest(false, true, "Go to My Taxes Btn is not present");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void TCX_CYValidation() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjIntegratedServices.tcxCurrentYear);
			if (element1) {
				mblock.Log("TCX Current Year is present");
				mblock.ValidateTest(true, true, " TCX Current Year is present");
				String CurrentYear = "My 2016 Taxes";
				if (CurrentYear.equalsIgnoreCase(mblock.Element(ObjIntegratedServices.tcxCurrentYear).getText())) {
					mblock.Log("Current Year is displayed as" + mblock.Element(ObjIntegratedServices.tcxCurrentYear));
					mblock.ValidateTest(true, true,
							"Current Year is displayed as" + mblock.Element(ObjIntegratedServices.tcxCurrentYear));
				}

			} else {
				mblock.ValidateTest(false, true, "TCX Current Year is not valid");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
// Click My Review Tax link button
/*
 * public void ClickReviewTaxbtn() throws Exception { try {
 * 
 * boolean rvw_btn = mblock.ElementExists(ObjDashboard.lnk_RvwTax); if (rvw_btn)
 * { mblock.Element(ObjDashboard.lnk_RvwTax).click();
 * mblock.ElementExists(ObjDashboard.lnk_myblock);
 * mblock.Element(ObjDashboard.lnk_myblock).click(); mblock.ValidateTest(true,
 * true, "Review button is displayed");
 * 
 * } else { mblock.ValidateTest(false, true, "Review button is not displayed");
 * }
 * 
 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); } }
 */
