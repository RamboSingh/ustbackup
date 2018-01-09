package business_actions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "Product and Services field is displayed");
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

			}

			else {
				mblock.ValidateTest(false, true, "Products and Services is not displayed");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "POM is displayed");
				mblock.Element(ObjDashboard.lnk_pomlink).click();

			}

			else {
				mblock.ValidateTest(false, true, "POM is not displayed");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "POM is displayed");
				mblock.Element(ObjDashboard.footer_POM).click();

			}

			else {
				mblock.ValidateTest(false, true, "POM is not displayed");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "POM is displayed");
				String text = mblock.Element(objPOM.POM_PageHeader).getText();
				System.out.println(text);
				if (text.equalsIgnoreCase("Peace of Mind Extended Service Plan")) {
					mblock.ValidateTest(true, true, "Page Header is displayed");
				}

			}

			else {
				mblock.ValidateTest(false, true, "Page header is not displayed");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "FAQ is displayed");
				mblock.Element(objPOM.FreqAskedQuestions).click();

				String FAQ_URL = mblock.GetCurrentURL();
				String Actual_URL = "https://hrbcomqa.hrblock.net/tax-offices/peace-of-mind-claims.html";
				if (Actual_URL.equalsIgnoreCase(FAQ_URL)) {
					mblock.ValidateTest(true, true, "User lands in FAQ Page");
				} else {
					mblock.ValidateTest(true, true, "User is not landed in FAQ Page");
				}

			}

			else {
				mblock.ValidateTest(false, true, "FAQ is not displayed");
				Assert.assertFalse(true);
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
					mblock.ValidateTest(true, true, "POM Content is displayed");
				} else if (Exp_ContentAM.equalsIgnoreCase(Act_Content)) {
					mblock.ValidateTest(true, true, "POM Content is displayed");
				}

			}

			else {
				mblock.ValidateTest(false, true, "Content is not displayed");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "95 is displayed");
				mblock.Element(objPOM.lnk_95).click();

				String FAQ_URL = mblock.GetCurrentURL();
				String Actual_URL = "https://hrbcomqa.hrblock.net/?odisc=95#/en/";
				if (Actual_URL.equalsIgnoreCase(FAQ_URL)) {
					mblock.ValidateTest(true, true, "User lands in 95 Page");
				} else {
					mblock.ValidateTest(true, true, "User is not landed in 95 Page");
				}

			}

			else {
				mblock.ValidateTest(false, true, "95 is not displayed");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "Link is displayed");
				mblock.Element(objPOM.LetterFromAuthority).isDisplayed();

			} else {
				mblock.ValidateTest(false, true, "Link is not displayed");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "Link is displayed");
				mblock.Element(objPOM.BtnPOM).isDisplayed();
				mblock.Element(objPOM.BtnPOM).click();

				String URL = "https://myaccountqa.hrblock.net/mytax/tax-offices/local-offices/index.html#!/en/office-locator/";
				if (URL.equalsIgnoreCase(mblock.GetCurrentURL())) {
					mblock.ValidateTest(true, true, "User lands in AM page");
				}
			} else {
				mblock.ValidateTest(false, true, "Link is not displayed");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "Link is displayed");
				mblock.Element(objPOM.AppointmentLink).isDisplayed();

			} else {
				mblock.ValidateTest(false, true, "Link is not displayed");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "Add Document is present");
				mblock.Element(ObjUploaddoc.btn_adddoc).click();

			} else {
				mblock.ValidateTest(false, true, "Add Document is not present");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "Confirm Identity is present");
				mblock.Element(ObjUploaddoc.btn_cnfrmidnty).click();
				mblock.ValidateTest(true, true, "IDP Frame is displayed");

			} else {
				mblock.ValidateTest(false, true, "Confirm ID is not present");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, "My Taxes is present");
				mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();
				mblock.Element(ObjDashboard.lnk_taxhstry).click();

			} else {
				mblock.ValidateTest(false, true, "Add Document is not present");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, " Go to My Taxes is present");
				mblock.Element(ObjDashboard.goToMyTaxes).click();

			} else {
				mblock.ValidateTest(false, true, "Go to My Taxes Btn is not present");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, " Continue My Taxes is present");
				mblock.Element(ObjDashboard.continueMyTaxes).click();

			} else {
				mblock.ValidateTest(false, true, "Go to My Taxes Btn is not present");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, " Go to My Taxes is present");
				mblock.Element(ObjDashboard.footer_FilingOnline).click();

			} else {
				mblock.ValidateTest(false, true, "Go to My Taxes Btn is not present");
				Assert.assertFalse(true);
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
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();
				mblock.Element(ObjDashboard.lnk_filngonline).click();
				mblock.ValidateTest(true, true, "Filing Online is Clicked");

			} else {
				mblock.ValidateTest(false, true, "Go to My Taxes Btn is not present");
				Assert.assertFalse(true);
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
				mblock.ValidateTest(true, true, " TCX Current Year is present");
				String CurrentYear = "My 2016 Taxes";
				if (CurrentYear.equalsIgnoreCase(mblock.Element(ObjIntegratedServices.tcxCurrentYear).getText())) {
					mblock.ValidateTest(true, true,
							"Current Year is displayed as" + mblock.Element(ObjIntegratedServices.tcxCurrentYear));
				}

			} else {
				mblock.ValidateTest(false, true, "TCX Current Year is not valid");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the My document button is displayed
	public void vrfyMydocBtnDsply() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_updoc, 8000);
			boolean element2 = mblock.ElementExists(ObjDashboard.lnk_updoc1, 8000);

			String val = mblock.Element(ObjDashboard.lnk_updoc).getText();
			String val1 = mblock.Element(ObjDashboard.lnk_updoc1).getText();
			if (val.contains("Document") || val1.contains("Document")) {
				mblock.Element(ObjDashboard.lnk_updoc).ufxScrollElementToView();
				mblock.ValidateTest(true, true, "Upload My Documents is displayed");

			} else {
				mblock.ValidateTest(false, true, "Upload My Documents is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// click the My document button is displayed
	public void clkMydocFrmDashbord() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_updoc, 8000);
			boolean element2 = mblock.ElementExists(ObjDashboard.lnk_updoc1, 8000);

			String val = mblock.Element(ObjDashboard.lnk_updoc).getText();
			String val1 = mblock.Element(ObjDashboard.lnk_updoc1).getText();
			if (val.contains("Document")) {
				mblock.Element(ObjDashboard.lnk_updoc).ufxClick();

			}

			else if (val1.contains("Document")) {
				mblock.Element(ObjDashboard.lnk_updoc1).ufxClick();
			} else {
				mblock.ValidateTest(false, true, "Upload My Documents is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the My document button is not displayed
	public void vrfyMydocBtnNotDsply() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_updoc, 8000);
			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_updoc1, 8000);
			if (element && element1) {
				String val = mblock.Element(ObjDashboard.lnk_updoc).getAttribute("title");
				String val1 = mblock.Element(ObjDashboard.lnk_updoc1).getAttribute("title");
				if (val.contains("Document") || val1.contains("Document")) {
					mblock.ValidateTest(false, true, "Upload My Documents is displayed");
					Assert.assertFalse(true);
				} else {
					mblock.ValidateTest(true, true, "Upload My Documents is not displayed");
				}
			}

			else if (element) {
				String val = mblock.Element(ObjDashboard.lnk_updoc).getAttribute("title");
				if (val.contains("Document")) {
					mblock.ValidateTest(false, true, "Upload My Documents is displayed");
					Assert.assertFalse(true);
				} else {
					mblock.ValidateTest(true, true, "Upload My Documents is not displayed");
				}
			}

			else {
				mblock.ValidateTest(false, true, "Upload My Documents is displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// POD verification

	// Emerland card pod for retail user
	public void vrfyECpodForRtlUsr() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.img_emrcard, 5000);
			boolean element01 = mblock.ElementExists(ObjDashboard.img_emrcard01, 5000);
			if (element1) {
				mblock.Element(ObjDashboard.img_emrcard).ufxScrollElementToView();
				String clsnme = mblock.Element(ObjDashboard.img_emrcard).getAttribute("class");
				if (clsnme.contains("Emerald")) {
					mblock.ValidateTest(true, true, "Emerland card is displayed and position at 3rd");
				}

				else {
					mblock.ValidateTest(false, true, "Emerland card is not display/position at 3rd");
					Assert.assertFalse(true);
				}

			}

			else if (element01) {
				mblock.Element(ObjDashboard.img_emrcard01).ufxScrollElementToView();
				String clsnme = mblock.Element(ObjDashboard.img_emrcard01).getAttribute("class");
				if (clsnme.contains("Emerald")) {
					mblock.ValidateTest(true, true, "Emerland card is displayed and position at 3rd");
				}

				else {
					mblock.ValidateTest(false, true, "Emerland card is not display/position at 3rd");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true, "Emerland card is not displayed for this user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify Emerland card is not displayed
	public void vrfyECpodNotdsply() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.img_emrcard, 5000);
			boolean element01 = mblock.ElementExists(ObjDashboard.img_emrcard01, 5000);

			if (element1) {
				mblock.Element(ObjDashboard.img_emrcard).ufxScrollElementToView();
				String clsnme = mblock.Element(ObjDashboard.img_emrcard).getAttribute("class");
				if (clsnme.contains("Emerald")) {
					mblock.ValidateTest(false, true, "Emerland card is displayed for this user");
					Assert.assertFalse(true);
				}

				else {
					mblock.ValidateTest(true, true, "Emerland card is not display for this user");
				}

			}

			else if (element01) {
				mblock.Element(ObjDashboard.img_emrcard01).ufxScrollElementToView();
				String clsnme = mblock.Element(ObjDashboard.img_emrcard01).getAttribute("class");
				if (clsnme.contains("Emerald")) {
					mblock.ValidateTest(false, true, "Emerland card is displayed for this user");
					Assert.assertFalse(true);
				}

				else {
					mblock.ValidateTest(true, true, "Emerland card is not display for this user");

				}

			}

			else {
				mblock.ValidateTest(false, true, "POD is not displayed for this user");
				Assert.assertFalse(true);
			}
		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Verify the point in Emerland card

	public void vrfyTxtInEcPod() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.txt_opt1, 5000);
			boolean element01 = mblock.ElementExists(ObjDashboard.txt_opt01, 5000);
			if (element1) {
				String opt1 = mblock.Element(ObjDashboard.txt_opt1).getText();
				String opt2 = mblock.Element(ObjDashboard.txt_opt2).getText();
				String opt3 = mblock.Element(ObjDashboard.txt_opt3).getText();
				if (opt1.equalsIgnoreCase("Check your balance") && opt2.equalsIgnoreCase("Pay monthly bills")
						&& opt3.equalsIgnoreCase("View Emerald Cash Reward offers")) {
					String algn1 = mblock.Element(ObjDashboard.txt_opt1).getCssValue("text-align");
					String algn2 = mblock.Element(ObjDashboard.txt_opt2).getCssValue("text-align");
					String algn3 = mblock.Element(ObjDashboard.txt_opt3).getCssValue("text-align");
					if (algn1.equalsIgnoreCase("center") && algn2.equalsIgnoreCase("center")
							&& algn3.equalsIgnoreCase("center")) {
						mblock.ValidateTest(true, true, "Points in EC are displayed and align in center");
					}

					else {
						mblock.ValidateTest(false, true, "Points are not align into center");
						Assert.assertFalse(true);
					}

				}

				else {
					mblock.ValidateTest(false, true, "Some point is missing/misspelled in Emerland card ");
					Assert.assertFalse(true);
				}

			}

			else if (element01) {

				String opt1 = mblock.Element(ObjDashboard.txt_opt01).getText();
				String opt2 = mblock.Element(ObjDashboard.txt_opt02).getText();
				String opt3 = mblock.Element(ObjDashboard.txt_opt03).getText();
				if (opt1.equalsIgnoreCase("Check your balance") && opt2.equalsIgnoreCase("Pay monthly bills")
						&& opt3.equalsIgnoreCase("View Emerald Cash Reward offers")) {
					String algn1 = mblock.Element(ObjDashboard.txt_opt01).getCssValue("text-align");
					String algn2 = mblock.Element(ObjDashboard.txt_opt02).getCssValue("text-align");
					String algn3 = mblock.Element(ObjDashboard.txt_opt03).getCssValue("text-align");
					if (algn1.equalsIgnoreCase("center") && algn2.equalsIgnoreCase("center")
							&& algn3.equalsIgnoreCase("center")) {
						mblock.ValidateTest(true, true, "Points in EC are displayed and align in center");
					}

					else {
						mblock.ValidateTest(false, true, "Points are not align into center");
						Assert.assertFalse(true);
					}

				}

				else {
					mblock.ValidateTest(false, true, "Some point is missing/misspelled in Emerland card ");
					Assert.assertFalse(true);
				}

			}

			else {
				mblock.ValidateTest(false, true, "Emerland card points are not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Emerland card pod buttons
	public void vrfyECpodBtns() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_qstn);
			boolean element2 = mblock.ElementExists(ObjDashboard.lnk_emracssmycard);
			if (element1 && element2) {

				mblock.ValidateTest(true, true, "Qstn and Access my card Buttons are displayed");
			} else {
				mblock.ValidateTest(false, true,
						"Qstn and Access my card Button are/is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Emerland card proofed pod buttons
	public void vrfyClrECPrfdpodBtns() throws Exception {
		try {

			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_qstn);
			String clr = mblock.Element(ObjDashboard.lnk_qstn).getCssValue("color");
			String clr1 = mblock.Element(ObjDashboard.lnk_crdntlst).getCssValue("color");
			if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))
					&& (clr1.contains("rgba(78, 133, 34, 1)") || clr1.contains("rgba(91, 155, 28, 1)"))) {
				mblock.ValidateTest(true, true, "Emerland card button clr is green");
			} else {
				mblock.ValidateTest(false, true, "Emerland card button clr is not green");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Click Question Button and verify the text
	public void clkQstnBtnEcPod() throws Exception {
		try {

			mblock.Element(ObjDashboard.lnk_qstn).ufxClick();
			boolean element = mblock.ElementExists(ObjDashboard.txt_popup1);
			mblock.ElementExists(ObjDashboard.txt_popup2, 2000);
			mblock.ElementExists(ObjDashboard.txt_popup3, 2000);
			mblock.ElementExists(ObjDashboard.answr_col1, 3000); // this is line
																	// used for
																	// just
																	// waiting
			if (element) {
				String popup1 = mblock.Element(ObjDashboard.txt_popup1).getText();
				String popup2 = mblock.Element(ObjDashboard.txt_popup2).getText();
				String popup3 = mblock.Element(ObjDashboard.txt_popup3).getText();
				String popup4 = mblock.Element(ObjDashboard.txt_popup4).getText();
				if (popup1.equalsIgnoreCase(Constant_Class.txt_popup1)
						&& popup2.equalsIgnoreCase(Constant_Class.txt_popup2)
						&& popup3.equalsIgnoreCase(Constant_Class.txt_popup3)
						&& popup4.equalsIgnoreCase(Constant_Class.txt_popup4)) {
					mblock.ValidateTest(true, true, "Pop up message is displayed correctly");
				} else {
					mblock.ValidateTest(false, true, "Pop up message is not displayed correctly");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true, "pop up is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// click Access my card and verify pop up is displayed
	public void clkAccsmyCardEcPod() throws Exception {
		try {

			mblock.Element(ObjDashboard.lnk_emracssmycard).ufxClick();
			boolean element = mblock.ElementExists(ObjDashboard.iframe_card);
			if (element) {
				mblock.ValidateTest(true, true, "Card proof model is displayed");
			} else {
				mblock.ValidateTest(false, true, "Card proof model is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Schdule appointment POD
	public void vrfyShdlApmntPOD() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_shdlapnmt);

			if (element) {
				mblock.Element(ObjDashboard.lnk_shdlapnmt).ufxScrollElementToView();
				mblock.ValidateTest(true, true, "Schdule appointment POD is displayed");

			} else {
				mblock.ValidateTest(false, true,
						"Schdule appointment POD is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Schdule appointment button color is green in POD
	public void vrfyClrOfShdlApmntBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_shdlapnmt, 5000);
			if (element) {
				String clr = mblock.Element(ObjDashboard.lnk_shdlapnmt).getCssValue("color");
				if (clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)")) {
					mblock.ValidateTest(true, true, "Schdule appointment button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Schdule appointment button clr is not green");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true,
						"Schdule appointment button is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Go to My tax POD
	public void vrfyGoMyTaxPOD() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_gomytax);
			if (element) {
				mblock.Element(ObjDashboard.lnk_gomytax).ufxScrollElementToView();
				mblock.ValidateTest(true, true, "Go TO My Taxes POD is Displayed");

			} else {
				mblock.ValidateTest(false, true,
						"Go TO My Taxes POD is not Displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Go TO MY Taxes button color is green in POD
	public void vrfyClrOfGoMyTaxBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_gomytax, 5000);
			if (element) {
				String clr = mblock.Element(ObjDashboard.lnk_gomytax).getCssValue("color");
				if (clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)")) {
					mblock.ValidateTest(true, true, "Go My Taxes button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Go My Taxes button clr is not green");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true,
						"Go My Taxes button is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the TIS POD
	public void vrfyTISPOD() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_tis);
			if (element) {
				mblock.Element(ObjDashboard.lnk_tis).ufxScrollElementToView();
				mblock.ValidateTest(true, true, "TIS POD is Displayed");

			} else {
				mblock.ValidateTest(false, true, "TIS POD is not Displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the TIS button color is green in POD
	public void vrfyClrOfTIsBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_tis, 5000);
			if (element) {

				String clr = mblock.Element(ObjDashboard.lnk_tis).getCssValue("color");
				if (clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)")) {
					mblock.ValidateTest(true, true, "TIS button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "TIS button clr is not green");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true, "TIS button is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the W4 Planner POD
	public void vrfyw4PlnrPOD() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_w4plnr);
			if (element) {
				mblock.Element(ObjDashboard.lnk_w4plnr).ufxScrollElementToView();
				mblock.ValidateTest(true, true, "W4 planner POD is Displayed");

			} else {
				mblock.ValidateTest(false, true, "W4 planner POD is not Displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the W4 palnner button color is green in POD
	public void vrfyClrOfW4plnrBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_w4plnr, 5000);
			if (element) {
				String clr = mblock.Element(ObjDashboard.lnk_w4plnr).getCssValue("color");
				if (clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)")) {
					mblock.ValidateTest(true, true, "W4 planner button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "W4 planner button clr is not green");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true, "W4 planner button is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Tax history POD
	public void vrfyTaxHstryPOD() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_viewtaxrtn);

			if (element) {
				mblock.Element(ObjDashboard.lnk_viewtaxrtn).ufxScrollElementToView();
				mblock.ValidateTest(true, true, "Taxhistory POD is Displayed");

			} else {
				mblock.ValidateTest(false, true, "Taxhistory POD is not Displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Taxhistory button color is green in POD
	public void vrfyClrOfTaxHstryBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_viewtaxrtn, 5000);
			if (element) {
				String clr = mblock.Element(ObjDashboard.lnk_viewtaxrtn).getCssValue("color");
				String clr1 = mblock.Element(ObjDashboard.lnk_viewhstry).getCssValue("color");
				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))
						&& (clr1.contains("rgba(78, 133, 34, 1)") || clr1.contains("rgba(91, 155, 28, 1)"))) {
					mblock.ValidateTest(true, true, "Tax history button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Tax history button clr is not green");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true,
						"Tax history button is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Tax Organizer POD
	public void vrfyTaxOrgPOD() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_taxorg);

			if (element) {
				mblock.Element(ObjDashboard.lnk_taxorg).ufxScrollElementToView();
				mblock.ValidateTest(true, true, "TaxOrganizer POD is Displayed");

			} else {
				mblock.ValidateTest(false, true, "TaxOrganizer POD is not Displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Taxhistory button color is green in POD
	public void vrfyClrOfTaxOrgnBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_taxorg, 5000);
			if (element) {
				String clr = mblock.Element(ObjDashboard.lnk_taxorg).getCssValue("color");

				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.ValidateTest(true, true, "Tax history button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Tax Organizer button clr is not green");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true,
						"Tax Organizer button is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Mydocument POD
	public void vrfyMydocPOD() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_mydocmnt);

			if (element) {
				mblock.Element(ObjDashboard.lnk_mydocmnt).ufxScrollElementToView();
				mblock.ValidateTest(true, true, "Mydocument POD is Displayed");

			} else {
				mblock.ValidateTest(false, true, "Mydocument POD is not Displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// click the Mydocument from POD
	public void clkMydocFrmPOD() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_mydocmnt);

			if (element) {

				mblock.Element(ObjDashboard.lnk_mydocmnt).ufxClick();

			} else {
				mblock.ValidateTest(false, true, "Mydocument POD is not Displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Mydocument button color is green in POD
	public void vrfyClrOfMydocBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_mydocmnt, 5000);
			if (element) {
				String clr = mblock.Element(ObjDashboard.lnk_mydocmnt).getCssValue("color");

				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.ValidateTest(true, true, "Mydocument button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Mydocument button clr is not green");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true, "Mydocument button is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Tax Estimator POD
	public void vrfyTaxEstmrPOD() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_taxestmor);

			if (element) {
				mblock.Element(ObjDashboard.lnk_taxestmor).ufxScrollElementToView();
				mblock.ValidateTest(true, true, "Tax Estimator POD is Displayed");

			} else {
				mblock.ValidateTest(false, true, "Tax Estimator POD is not Displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Tax Estimator button color is green in POD
	public void vrfyClrOfTaxEstmrBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_taxestmor, 5000);
			if (element) {
				String clr = mblock.Element(ObjDashboard.lnk_taxestmor).getCssValue("color");

				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.ValidateTest(true, true, "Tax Estimator button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Tax Estimator button clr is not green");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true,
						"Tax Estimator button is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Get assistant POD
	public void vrfyGetAsstntPOD() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_assistant);

			if (element) {
				mblock.Element(ObjDashboard.lnk_assistant).ufxScrollElementToView();
				mblock.ValidateTest(true, true, "Get assistant POD is Displayed");

			} else {
				mblock.ValidateTest(false, true, "Get assistant POD is not Displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the Get assistant button color is green in POD
	public void vrfyClrOfGetAsstntBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_assistant, 5000);
			if (element) {
				String clr = mblock.Element(ObjDashboard.lnk_assistant).getCssValue("color");

				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.ValidateTest(true, true, "Get assistant button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Get assistant button clr is not green");
					Assert.assertFalse(true);
				}

			} else {
				mblock.ValidateTest(false, true,
						"Get assistant button is not displayed for this user, Please check user");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Verify the all POD buttons color is green

	public void vrfyClrOfAllPodBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.lnk_qstn, 2000);
			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_shdlapnmt, 2000);
			boolean element2 = mblock.ElementExists(ObjDashboard.lnk_gomytax, 2000);
			boolean element3 = mblock.ElementExists(ObjDashboard.lnk_tis, 2000);
			boolean element4 = mblock.ElementExists(ObjDashboard.lnk_w4plnr, 2000);
			boolean element5 = mblock.ElementExists(ObjDashboard.lnk_viewhstry, 2000);
			boolean element6 = mblock.ElementExists(ObjDashboard.lnk_taxorg, 2000);
			boolean element7 = mblock.ElementExists(ObjDashboard.lnk_mydocmnt, 2000);
			boolean element8 = mblock.ElementExists(ObjDashboard.lnk_taxestmor, 2000);
			boolean element9 = mblock.ElementExists(ObjDashboard.lnk_assistant, 2000);
			if (element) {
				String clr = mblock.Element(ObjDashboard.lnk_assistant).getCssValue("color");
				String clr1 = mblock.Element(ObjDashboard.lnk_emracssmycard).getCssValue("color");

				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))
						&& (clr1.contains("rgba(78, 133, 34, 1)") || clr1.contains("rgba(91, 155, 28, 1)"))) {
					mblock.ValidateTest(true, true, "Emerland card button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Emerland card button clr is not green");
					Assert.assertFalse(true);
				}

			}

			else if (element1) {
				String clr = mblock.Element(ObjDashboard.lnk_shdlapnmt).getCssValue("color");
				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.Element(ObjDashboard.lnk_shdlapnmt).ufxScrollElementToView();
					mblock.ValidateTest(true, true, "Schedule appointment button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Schedule appointment button clr is not green");
					Assert.assertFalse(true);
				}

			}

			else if (element2) {
				String clr = mblock.Element(ObjDashboard.lnk_gomytax).getCssValue("color");
				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.Element(ObjDashboard.lnk_gomytax).ufxScrollElementToView();
					mblock.ValidateTest(true, true, "Go to my tax button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Go to my tax button clr is not green");
					Assert.assertFalse(true);
				}

			}

			else if (element3) {
				String clr = mblock.Element(ObjDashboard.lnk_tis).getCssValue("color");
				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.Element(ObjDashboard.lnk_tis).ufxScrollElementToView();
					mblock.ValidateTest(true, true, "TIS button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "TIS button clr is not green");
					Assert.assertFalse(true);
				}

			}

			else if (element4) {
				String clr = mblock.Element(ObjDashboard.lnk_w4plnr).getCssValue("color");
				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.Element(ObjDashboard.lnk_w4plnr).ufxScrollElementToView();
					mblock.ValidateTest(true, true, "W4 planner button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "W4 planner button clr is not green");
					Assert.assertFalse(true);
				}

			}

			else if (element5) {
				String clr = mblock.Element(ObjDashboard.lnk_viewhstry).getCssValue("color");
				String clr1 = mblock.Element(ObjDashboard.lnk_viewtaxrtn).getCssValue("color");
				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))
						&& (clr1.contains("rgba(78, 133, 34, 1)") || clr1.contains("rgba(91, 155, 28, 1)"))) {
					mblock.ValidateTest(true, true, "Tax history button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Tax history button clr is not green");
					Assert.assertFalse(true);
				}

			}

			else if (element6) {
				String clr = mblock.Element(ObjDashboard.lnk_taxorg).getCssValue("color");
				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.Element(ObjDashboard.lnk_taxorg).ufxScrollElementToView();
					mblock.ValidateTest(true, true, "Tax organizer button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Tax organizer button clr is not green");
					Assert.assertFalse(true);
				}

			}

			else if (element7) {
				String clr = mblock.Element(ObjDashboard.lnk_mydocmnt).getCssValue("color");
				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.Element(ObjDashboard.lnk_mydocmnt).ufxScrollElementToView();
					mblock.ValidateTest(true, true, "My Document button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "My Document button clr is not green");
					Assert.assertFalse(true);
				}

			}

			else if (element8) {
				String clr = mblock.Element(ObjDashboard.lnk_taxestmor).getCssValue("color");
				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.Element(ObjDashboard.lnk_taxestmor).ufxScrollElementToView();
					mblock.ValidateTest(true, true, "Tax Estimator button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Tax Estimator button clr is not green");
					Assert.assertFalse(true);
				}

			}

			else if (element9) {
				String clr = mblock.Element(ObjDashboard.lnk_assistant).getCssValue("color");
				if ((clr.contains("rgba(78, 133, 34, 1)") || clr.contains("rgba(91, 155, 28, 1)"))) {
					mblock.Element(ObjDashboard.lnk_assistant).ufxScrollElementToView();
					mblock.ValidateTest(true, true, "Get assistant button clr is green");
				}

				else {
					mblock.ValidateTest(false, true, "Get assistant button clr is not green");
					Assert.assertFalse(true);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void statusBarNotVisible() throws Exception {
		try {

			boolean element = mblock.ElementDoesNotExists(ObjDashboard.StatusBar);
			if (element) {
				mblock.ValidateTest(true, true, "Status Bar is Not Present");
			}

			else {
				mblock.ValidateTest(false, true, "Status Bar is Present");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void statusBarVisible() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.StatusBar);
			if (element) {
				mblock.ValidateTest(true, true, "Status Bar is Present");
				String SB_Width = mblock.Element(ObjDashboard.StatusBar).getCssValue("width");
				System.out.println(SB_Width);
				String SB_Height = mblock.Element(ObjDashboard.StatusBar).getCssValue("height");
				System.out.println(SB_Height);
				if ((SB_Width.contains("1263px") && (SB_Height.contains("171px")))) {
					mblock.ValidateTest(true, true, "Status Bar is in Proper Alignment");
				} else {
					mblock.ValidateTest(false, true, "Status Bar is Not Aligned Properly");
					Assert.assertFalse(true);
				}
			}

			else {
				mblock.ValidateTest(false, true, "Status Bar is Not Present");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void statusBarNewUser() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.btn_cnfmid);
			if (element) {
				mblock.ValidateTest(true, true, "Prospective Status Bar is Present");

			}

			else {
				mblock.ValidateTest(false, true, "Prospective Status Bar is Not Present");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void statusBarIconsVisible() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.SB_THBtn);
			if (element) {
				mblock.ValidateTest(true, true, "Status Bar is Present");
				boolean FedRefund = mblock.ElementExists(ObjDashboard.SB_FedRefund);
				boolean FilingStatus = mblock.ElementExists(ObjDashboard.SB_FilingStatus);
				boolean FiledOn = mblock.ElementExists(ObjDashboard.SB_FiledOn);
				boolean FiledWith = mblock.ElementExists(ObjDashboard.SB_FiledWith);
				if (FedRefund && FilingStatus && FiledOn && FiledWith) {
					mblock.ValidateTest(true, true, "Four Icons in About Me is Present");
				} else {
					mblock.ValidateTest(false, true, "Four Icons in About Me is not Present");
				}
			}

			else {
				mblock.ValidateTest(false, true, "Prospective Status Bar is Not Present");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void statusBarViewHistoryBtn() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.SB_THBtn);
			if (element) {

				mblock.ValidateTest(true, true, "Tax History Button is present");
				// mblock.Element(ObjDashboard.SB_THBtn).click();

				String statusBar = mblock.Element(ObjDashboard.SB_THBtn).getText();
				Pattern pattern = Pattern.compile("(\\d+)");
				Matcher matcher = pattern.matcher(statusBar);

				if (matcher.find()) {
					String str = matcher.group(matcher.groupCount());
					System.out.println(str);

					mblock.Element(ObjDashboard.SB_THBtn).click();
					mblock.WaitForPageLoad();
					String TY = mblock.GetCurrentURL();
					Pattern pattern1 = Pattern.compile("(\\d+)");
					Matcher matcher1 = pattern1.matcher(statusBar);

					if (matcher1.find()) {
						String ty = matcher1.group(matcher1.groupCount());
						if (str.equalsIgnoreCase(ty)) {
							mblock.ValidateTest(true, true, "User lands in TH with TY " + ty);
						} else {
							mblock.Log("TY displayed in Status Bar and Tax History Navigation Year Mismatches");
						}

					}

				}

			}

			else {
				mblock.ValidateTest(false, true, "Status Bar is Not Present");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// validate product and service

	public void vldPrdctSrvce() throws Exception {
		try {

			boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
			if (icon_lnk = true) {
				if (mblock.strBrowserType.equals("FIREFOX")) {
					mblock.Element(ObjDashboard.lnk_prdctsrvce).click();

				} else {
					mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

				}
			}

			boolean card = mblock.ElementExists(ObjDashboard.lnk_card);
			if (card) {

				mblock.Element(ObjDashboard.lnk_card).click();
				boolean emrld_page = mblock.ElementExists(ObjDashboard.lnk_myblock);
				if (emrld_page) {
					mblock.ValidateTest(true, true, "User is directed to Emerald Online");
				}

				else {
					mblock.ValidateTest(false, true, "User is not directed to Emerald Online");
					Assert.assertFalse(true);
				}
			}

			else {
				mblock.ValidateTest(true, true, "Emerland card detail is not present");
				mblock.Element(ObjDashboard.lnk_emrldcrd).ufxClick();
				boolean iframe = mblock.ElementExists(ObjDashboard.iframe_card);
				boolean iframe1 = mblock.ElementExists(ObjDashboard.iframe_card1);
				if(iframe||iframe1){
					mblock.ValidateTest(true, true, "User is able to view card proof modal");
				}
				
				else{
					mblock.ValidateTest(false, true, "User is not able to view card proof modal");
					Assert.assertFalse(true);
				}
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void TIS_RiskAssessment() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.tis_newsletter);
			if (element) {
				String EH_TIS_riskAssesment = "Tax Identity Shield";
				String H_TIS_riskAssessment = mblock.Element(ObjDashboard.tis_newsletter_Header).getText();
				String Text_TIS_riskAssessment = mblock.Element(ObjDashboard.tis_newsletter_Header).getCssValue("font-weight");
				String Color_TIS_riskAssessment = mblock.Element(ObjDashboard.tis_newsletter_Header).getCssValue("color");
				if((H_TIS_riskAssessment.equalsIgnoreCase(EH_TIS_riskAssesment))&&(Text_TIS_riskAssessment.equalsIgnoreCase("bold"))&&(Color_TIS_riskAssessment.equalsIgnoreCase("#30d8e7"))){
					mblock.ValidateTest(true, true, "HeaderText is displayed");
				}
				else{
					mblock.ValidateTest(false, true, "HeaderText is not displayed");

				}
				String Header_TIS_riskAssessment = "WHAT IS YOUR RISK LEVEL?";
				String Head_TIS_riskAssessment = mblock.Element(ObjDashboard.tis_newsletter_content).getText();
				String HeadF_TIS_riskAssessment = mblock.Element(ObjDashboard.tis_newsletter_content).getCssValue("font-weight");
				String HeaderColor_TIS_riskAssessment = mblock.Element(ObjDashboard.tis_newsletter_content).getCssValue("color");
				if((Head_TIS_riskAssessment.equalsIgnoreCase(Header_TIS_riskAssessment))&&(HeadF_TIS_riskAssessment.equalsIgnoreCase("bold"))&&(HeaderColor_TIS_riskAssessment.equalsIgnoreCase("#fff"))){
					mblock.ValidateTest(true, true, "Content is displayed");
				}
				else{
					mblock.ValidateTest(false, true, "Content Text is not displayed");

				}
				String Explain_Text = "Your tax identity theft Risk Assessment results are now available.";
				String AExplain_Text = mblock.Element(ObjDashboard.tis_newsletter_expl).getText();
				if((AExplain_Text.equalsIgnoreCase(Explain_Text))){
					mblock.ValidateTest(true, true, "Explanatory is displayed");
					}
				else{
					mblock.ValidateTest(false, true, "Explanatory Text is not displayed");

				}
				}
				
				

			 else {
				mblock.ValidateTest(false, true, "Newsletter Hero is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public void TIS_Newsletter() throws Exception {
		try {

			boolean element = mblock.ElementExists(ObjDashboard.tis_newsletter);
			if (element) {
				String EH_TIS_newsletter = "Tax Identity Shield";
				String H_TIS_newsletter = mblock.Element(ObjDashboard.tis_newsletter_Header).getText();
				String Text_TIS_newsletter = mblock.Element(ObjDashboard.tis_newsletter_Header).getCssValue("font-weight");
				String Color_TIS_newsletter = mblock.Element(ObjDashboard.tis_newsletter_Header).getCssValue("color");
				if((H_TIS_newsletter.equalsIgnoreCase(EH_TIS_newsletter))&&(Text_TIS_newsletter.equalsIgnoreCase("bold"))&&(Color_TIS_newsletter.equalsIgnoreCase("#30d8e7"))){
					mblock.ValidateTest(true, true, "HeaderText is displayed");
				}
				else{
					mblock.ValidateTest(false, true, "HeaderText is not displayed");

				}
				String Header_TIS_Newsletter = "ACCESS YOUR ACCOUNT";
				String Head_TIS_Newsletter = mblock.Element(ObjDashboard.tis_newsletter_content).getText();
				String HeadF_TIS_Newsletter = mblock.Element(ObjDashboard.tis_newsletter_content).getCssValue("font-weight");
				String HeaderColor_TIS_newsletter = mblock.Element(ObjDashboard.tis_newsletter_content).getCssValue("color");
				if((Head_TIS_Newsletter.equalsIgnoreCase(Header_TIS_Newsletter))&&(HeadF_TIS_Newsletter.equalsIgnoreCase("bold"))&&(HeaderColor_TIS_newsletter.equalsIgnoreCase("#fff"))){
					mblock.ValidateTest(true, true, "Content is displayed");
				}
				else{
					mblock.ValidateTest(false, true, "Content Text is not displayed");

				}
				String Explain_Text = "Your credit report is now available!";
				String AExplain_Text = mblock.Element(ObjDashboard.tis_newsletter_expl).getText();
				if((AExplain_Text.equalsIgnoreCase(Explain_Text))){
					mblock.ValidateTest(true, true, "Explanatory is displayed");
					}
				else{
					mblock.ValidateTest(false, true, "Explanatory Text is not displayed");

				}
				}
				
				

			 else {
				mblock.ValidateTest(false, true, "Newsletter Hero is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	public void TIS_RiskAssessmentHeroBtn() throws Exception {
		try {
			
			boolean element = mblock.ElementExists(ObjDashboard.tis_newsletter);
			if (element) {
				String A_Text = mblock.Element(ObjDashboard.tis_newsletter).getText();
				String E_Text = "View My Risk Assessment";
				if(E_Text.equalsIgnoreCase(A_Text)){
					mblock.ValidateTest(true, true, "Risk Assessment Hero Text is displayed");
							
				}else{
					mblock.ValidateTest(false, true, "Risk Assessment Hero Text is not displayed");
					Assert.assertFalse(true);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	public void TIS_RiskAssessmentHeroBtnBehavior() throws Exception {
		try {
			
			boolean element = mblock.ElementExists(ObjDashboard.tis_newsletter);
			if (element) {
				
					mblock.Element(ObjDashboard.tis_newsletter).click();
					boolean element1 = mblock.ElementExists(ObjDashboard.TISiframe);
					if(element1){
						mblock.ValidateTest(true, true, "IDP modal frame is displayed");
							
				}
					else{
					mblock.ValidateTest(false, true, "IDP modal frame is not displayed");
					Assert.assertFalse(true);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	public void TIS_NewsLetterHeroBtnBehavior() throws Exception {
		try {
			
			boolean element = mblock.ElementExists(ObjDashboard.tis_newsletter);
			if (element) {
				
					mblock.Element(ObjDashboard.tis_newsletter).click();
					boolean element1 = mblock.ElementExists(ObjDashboard.TISiframe);
					if(element1){
						mblock.ValidateTest(true, true, "IDP modal frame is displayed");
							
				}
					else{
					mblock.ValidateTest(false, true, "IDP modal frame is not displayed");
					Assert.assertFalse(true);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	public void TIS_NewsLetterHeroBtn() throws Exception {
		try {
			
			boolean element = mblock.ElementExists(ObjDashboard.tis_newsletter);
			if (element) {
				String A_Text = mblock.Element(ObjDashboard.tis_newsletter).getText();
				String E_Text = "View My Credit Report";
				if(E_Text.equalsIgnoreCase(A_Text)){
					mblock.ValidateTest(true, true, "News Letter Hero Text is displayed");
							
				}else{
					mblock.ValidateTest(false, true, "News Letter Hero Text is not displayed");
					Assert.assertFalse(true);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	//Personalized Dashboard
	public void Federal_RefundCheck() throws Exception {
		try {
			
			boolean element = mblock.ElementExists(ObjDashboard.StatusBarTHBtn);
			if (element) {
				
				String A_Text = mblock.Element(ObjDashboard.RefundAmt).getText();
				mblock.Element(ObjDashboard.StatusBarTHBtn).click();
				String E_Text = mblock.Element(ObjDashboard.RefundID).getText();
				if(E_Text.equalsIgnoreCase(A_Text)){
					mblock.ValidateTest(true, true, "Refund Amount Matches in Status Bar and TH");
							
				}else{
					mblock.ValidateTest(false, true, "Refund Amount is not matched in Status Bar and TH");
					Assert.assertFalse(true);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public void Federal_FilingStatus() throws Exception {
		try {
			
			boolean element = mblock.ElementExists(ObjDashboard.StatusBarTHBtn);
			if (element) {
				
				String A_Text = mblock.Element(ObjDashboard.filingStatusSts).getText();
				mblock.Element(ObjDashboard.StatusBarTHBtn).click();
				String E_Text = mblock.Element(ObjDashboard.filingStatusSts).getText();
				if(E_Text.equalsIgnoreCase(A_Text)){
					mblock.ValidateTest(true, true, "Filing Status Matches in Status Bar and TH");
							
				}else{
					mblock.ValidateTest(false, true, "Filing Status is not matched in Status Bar and TH");
					Assert.assertFalse(true);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	public void Federal_TH() throws Exception {
		try {
			
			boolean element = mblock.ElementExists(ObjDashboard.StatusBarTHBtn);
			if (element) {
				
				String A_Text = mblock.Element(ObjDashboard.StatusBarTHBtn).getText();
				mblock.Element(ObjDashboard.StatusBarTHBtn).click();
				String E_Text = mblock.Element(ObjDashboard.TH).getText();
				if(E_Text.equalsIgnoreCase(A_Text)){
					mblock.ValidateTest(true, true, "Filing Year Matches in Status Bar and TH");
							
				}else{
					mblock.ValidateTest(false, true, "Filing Year is not matched in Status Bar and TH");
					Assert.assertFalse(true);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}


	
}
