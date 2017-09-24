package business_actions;

import pageObject.ObjDashboard;
import pageObject.ObjLogin;
import pageObject.ObjTaxHistory;
import pageObject.ObjTaxOrganizer;
import pageObject.ObjTaxestimator;
import pageObject.ObjUploaddoc;
import pageObject.Objcommon;
import pageObject.objProfile;
import pageObject.objW4Calc;
import utility.Constant_Class;

public class GeneralnavigationAction {

	MyblockActions mblock;

	public GeneralnavigationAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	// Verify the My block logo
	public void vrfyMyblockLogo() throws Exception {

		boolean txt = mblock.ElementExists(ObjDashboard.img_logo);
		if (txt = true) {
			mblock.Element(ObjDashboard.img_logo).ufxScrollElementToView();
			String text = mblock.Element(ObjDashboard.img_logo).getAttribute("src");
			if (text.contains(Constant_Class.src_logo)) {
				mblock.ValidateTest(true, true, "Logo image is displayed");
			} else {
				mblock.ValidateTest(false, true, "Logo image is not displayed");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Logo image is not displayed");
		}

	}

	// Verify the H&R block logo in footer

	public void vrfyHRblockLogofooter() throws Exception {

		boolean txt = mblock.ElementExists(ObjDashboard.img_logofooter);
		if (txt = true) {
			mblock.Element(ObjDashboard.img_logofooter).ufxScrollElementToView();
			String text = mblock.Element(ObjDashboard.img_logofooter).getAttribute("src");
			if (text.contains(Constant_Class.src_logofooter)) {
				mblock.ValidateTest(true, true, "H&R Block Logo image is displayed in footer");
			} else {
				mblock.ValidateTest(false, true, "H&R Block Logo image is not displayed in footer");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Logo image is not displayed");
		}

	}

	// Verify the copy right year for 2016
	public void vrfyCopyRightDate2016() throws Exception {

		boolean txt = mblock.ElementExists(ObjDashboard.txt_copyright);
		if (txt = true) {
			String text = mblock.Element(ObjDashboard.txt_copyright).getText();
			if (text.equalsIgnoreCase(Constant_Class.txt_copyright)) {
				mblock.ValidateTest(true, true, "Copy right date to be displayed for 2016-2017");
			} else {
				mblock.ValidateTest(false, true, "Copy right date not to be displayed for 2016-2017");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Copy right date is not displayed in footer");
		}

	}

	// Verify My taxes link is present in the gobal navigation
	public void VrfyMyTaxFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_mytax);
		if (icon_lnk = true) {
			mblock.ValidateTest(true, true, "My Tax link is present in the Golbal navigation");
		}

		else {
			mblock.ValidateTest(false, true, "My Taxes link is not present in the Golbal navigation");
		}

	}

	// Verify the My document shield link under My Taxes
	public void vrfyMydocFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_mytax);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_mytax).click();

			} else {
				mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_mydoc);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_mydoc).ufxFocus();

				}
				mblock.ValidateTest(true, true, "My document link is present");

			} else {
				mblock.ValidateTest(false, true, "My document link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My Taxes link is not present in the status bar");
		}

	}

	// click the MY document link under My taxes
	public void clkMydocFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_mytax);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_mytax).click();

			} else {
				mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_mydoc);
			if (txt_lnk = true) {

				mblock.Element(ObjDashboard.lnk_mydoc).ufxClick();
				mblock.ElementExists(ObjUploaddoc.btn_adddoc);
				mblock.ValidateTest(true, true, "User lands to My document page");

			} else {
				mblock.ValidateTest(false, true, "My document link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My Taxes link is not present in the status bar");
		}

	}

	// Verify the Tax History shield link under My Taxes (global navigation)
	public void vrfyTaxHstryFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_mytax);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_mytax).click();

			} else {
				mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_taxhstry);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_taxhstry).ufxFocus();

				}
				mblock.ValidateTest(true, true, "Tax history link is present");

			} else {
				mblock.ValidateTest(false, true, "Tax History link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My Taxes link is not present in the status bar");
		}

	}

	// Verify the Tax history in Footer
	public void vrfyTaxHstryFrmFooter() throws Exception {

		boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_footertaxhsty);
		if (txt_lnk = true) {
			 mblock.Element(ObjDashboard.lnk_footertaxhsty).ufxScrollElementToView();
			mblock.ValidateTest(true, true, "Tax History link is present in footer");
		}

		else

		{
			mblock.ValidateTest(false, true, "My Taxes link is not present in the footer");
		}

	}

	// click the Tax history link under My taxes
	public void clkTaxHstryFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_mytax);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_mytax).click();

			} else {
				mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_taxhstry);
			if (txt_lnk = true) {
				mblock.Element(ObjDashboard.lnk_taxhstry).ufxClick();
				mblock.ElementExists(ObjTaxHistory.txt_taxhstry);
				mblock.ValidateTest(true, true, "User lands to Tax history page");

			} else {
				mblock.ValidateTest(false, true, "Tax History link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My Taxes link is not present in the status bar");
		}

	}
	
	
	// Click the Tax history in Footer
		public void clkTaxHstryFrmFooter() throws Exception {

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_footertaxhsty);
			if (txt_lnk = true) {
				 mblock.Element(ObjDashboard.lnk_footertaxhsty).ufxScrollElementToView();
				 mblock.Element(ObjDashboard.lnk_footertaxhsty).ufxClick();
				 mblock.ElementExists(ObjTaxHistory.txt_taxhstry);
				mblock.ValidateTest(true, true, "User lands to Tax history page");
			}

			else

			{
				mblock.ValidateTest(false, true, "My Taxes link is not present in the footer");
			}

		}

	// Verify the My taxpro shield link under My Taxes
	public void vrfyTaxproFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_mytax);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_mytax).click();

			} else {
				mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_mytaxpro);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_mytaxpro).ufxFocus();

				}
				mblock.ValidateTest(true, true, "My tax pro link is present");

			} else {
				mblock.ValidateTest(false, true, "My tax pro link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My Taxes link is not present in the status bar");
		}

	}

	// click the MY taxpro link under My taxes
	public void clkMyTaxProFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_mytax);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_mytax).click();

			} else {
				mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_mytaxpro);
			if (txt_lnk = true) {
				mblock.Element(ObjDashboard.lnk_mytaxpro).ufxClick();
				mblock.ElementExists(ObjDashboard.lnk_makeapnmt);
				mblock.ValidateTest(true, true, "User lands in Tax Pro Page");

			} else {
				mblock.ValidateTest(false, true, "My tax pro link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My Taxes link is not present in the status bar");
		}

	}

	// Verify the My Online tax shield link under My Taxes
	public void vrfyMyOnlneTaxFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_mytax);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_mytax).click();

			} else {
				mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_mydonlinetax);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_mydonlinetax).ufxFocus();

				}
				mblock.ValidateTest(true, true, "My online tax link is present");

			} else {
				mblock.ValidateTest(false, true, "My online tax link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My Taxes link is not present in the status bar");
		}

	}

	// click the MY online taxes link under My taxes
	public void clkMyOnlneTaxFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_mytax);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_mytax).click();

			} else {
				mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_mydonlinetax);
			if (txt_lnk = true) {

				mblock.Element(ObjDashboard.lnk_mydonlinetax).ufxClick();
				mblock.ElementExists(ObjDashboard.lnk_myblock);
				mblock.ValidateTest(true, true, "User lands to TCX page");

			} else {
				mblock.ValidateTest(false, true, "My online tax link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My Taxes link is not present in the status bar");
		}

	}

	// Tcx page to Dashboard
	public void clkMyblockbtnTCX() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_myblock);
		if (icon_lnk = true) {

			mblock.Element(ObjDashboard.lnk_myblock).ufxClick();
			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_mytax);
			if (txt_lnk = true) {
				mblock.ValidateTest(true, true, "Successfully back to Dashboard");

			} else {
				mblock.ValidateTest(false, true, "Page is not back to Dashboard");
			}
		}

	}

	// Verify Calculator&tools link is present in the gobal navigation
	public void VrfyCalcToolFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_calctr);
		if (icon_lnk = true) {
			mblock.ValidateTest(true, true, "Calculator&tools link is present in the Golbal navigation");
		}

		else {
			mblock.ValidateTest(false, true, "Calculator&tools link is not present in the Golbal navigation");
		}

	}

	// click the Tax estimator link under Calculator&tools
	public void clkTaxEstmtrFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_calctr);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_calctr).click();

			} else {
				mblock.Element(ObjDashboard.lnk_calctr).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_taxestmr);
			if (txt_lnk = true) {
				mblock.Element(ObjDashboard.lnk_taxestmr).ufxClick();
				mblock.ElementExists(ObjTaxestimator.txt_aboutyouheader);
				mblock.ElementExists(ObjTaxestimator.txt_expenseheader);
				mblock.ValidateTest(true, true, "User lands to Tax estimator page");

			} else {
				mblock.ValidateTest(false, true, "Tax Estimator link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Calculator&tools link is not present in the status bar");
		}

	}

	// click the W4calculator link under Calculator&tools
	public void clkW4CaltrFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_calctr);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_calctr).click();

			} else {
				mblock.Element(ObjDashboard.lnk_calctr).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_w4calctr);
			if (txt_lnk = true) {
				mblock.Element(ObjDashboard.lnk_w4calctr).ufxClick();
				mblock.ElementExists(objW4Calc.payFreq);
				mblock.ValidateTest(true, true, "User lands to W4calculator page");

			} else {
				mblock.ValidateTest(false, true, "W4Calculator link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Calculator&tools link is not present in the status bar");
		}

	}

	// click the Tax organizer link under Calculator&tools
	public void clkTaxOrgnzrFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_calctr);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_calctr).click();

			} else {
				mblock.Element(ObjDashboard.lnk_calctr).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_taxorgnzr);
			if (txt_lnk = true) {
				mblock.Element(ObjDashboard.lnk_taxorgnzr).ufxClick();
				mblock.ElementExists(ObjTaxOrganizer.btn_additem);
				mblock.ValidateTest(true, true, "User lands to Tax Organizer page");

			} else {
				mblock.ValidateTest(false, true, "Tax Organaizer link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Calculator&tools link is not present in the status bar");
		}

	}

	// Verify Product&Service link is present in the gobal navigation
	public void VrfyProductServiceFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
		if (icon_lnk = true) {
			mblock.ValidateTest(true, true, "Product&Service link is present in the Golbal navigation");
		}

		else {
			mblock.ValidateTest(false, true, "Product&Service link is not present in the Golbal navigation");
		}

	}

	// Verify the Make appointment link under Product&Service
	public void vrfyMakeAppnmtFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).click();

			} else {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_makeappnmt);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_makeappnmt).ufxFocus();

				}
				mblock.ValidateTest(true, true, "Make appoinment link is present");

			} else {
				mblock.ValidateTest(false, true, "Make appoinment link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Product and service link is not present in the status bar");
		}

	}

	// click the Make appointment link under Product&Service
	public void clkMakeAppnmtFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).click();

			} else {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_makeappnmt);
			if (txt_lnk = true) {
				mblock.Element(ObjDashboard.lnk_makeappnmt).ufxClick();
				mblock.ElementExists(Objcommon.txt_search);
				mblock.ValidateTest(true, true, "User lands to Make appointment page");

			} else {
				mblock.ValidateTest(false, true, "Make appointment link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Product&Service link is not present in the status bar");
		}

	}

	// Verify the Filling Online link under Product&Service
	public void vrfyFillingOnlneFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).click();

			} else {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_filngonline);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_filngonline).ufxFocus();

				}
				mblock.ValidateTest(true, true, "Filling Online link is present");

			} else {
				mblock.ValidateTest(false, true, "Filling Online link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Product and service link is not present in the status bar");
		}

	}

	// click the Filling online link under Product&Service
	public void clkFillingOnlneFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).click();

			} else {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_filngonline);
			if (txt_lnk = true) {
				mblock.Element(ObjDashboard.lnk_filngonline).ufxClick();
				mblock.ElementExists(ObjDashboard.lnk_myblock);
				mblock.ValidateTest(true, true, "User lands to TCX page");

			} else {
				mblock.ValidateTest(false, true, "Filling Online link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Product&Service link is not present in the status bar");
		}

	}

	// Verify the Tax id shield link under Product&Service
	public void vrfyTaxIdShldFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).click();

			} else {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_taxidshield);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_taxidshield).ufxFocus();

				}
				mblock.ValidateTest(true, true, "Tax id shield link is present");

			} else {
				mblock.ValidateTest(false, true, "Tax id shield link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Product and service link is not present in the status bar");
		}

	}

	// click the Tax ID shield link under Product&Service
	public void clkTaxIdShldFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).click();

			} else {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_taxidshield);
			if (txt_lnk = true) {
				mblock.ValidateTest(true, true, "Tax id shield link is present");
				mblock.Element(ObjDashboard.lnk_taxidshield).ufxClick();

			} else {
				mblock.ValidateTest(false, true, "Tax id shield link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Product&Service link is not present in the status bar");
		}

	}

	// Verify the EmerlandCard link under Product&Service
	public void vrfyEmrldCardFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).click();

			} else {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_emrldcrd);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_emrldcrd).ufxFocus();

				}
				mblock.ValidateTest(true, true, "EmerlandCard link is present");

			} else {
				mblock.ValidateTest(false, true, "EmerlandCard link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Product and service link is not present in the status bar");
		}

	}

	// click the Emerlandcard shield link under Product&Service
	public void clkEmrldCardFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_prdctsrvce);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).click();

			} else {
				mblock.Element(ObjDashboard.lnk_prdctsrvce).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_emrldcrd);
			if (txt_lnk = true) {
				mblock.ValidateTest(true, true, "EmerlandCard link is present");
				mblock.Element(ObjDashboard.lnk_emrldcrd).ufxClick();

			} else {
				mblock.ValidateTest(false, true, "EmerlandCard link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Product&Service link is not present in the status bar");
		}

	}

	// Verify My Account link is present in the gobal navigation
	public void VrfyMyAccountFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_profile);
		if (icon_lnk = true) {
			mblock.ValidateTest(true, true, "My Account link is present in the Golbal navigation");
		}

		else {
			mblock.ValidateTest(false, true, "My Account link is not present in the Golbal navigation");
		}

	}

	// Verify My Account link is present in the global navigation
	public void VrfyMngeAccntFrmGlobalNav() throws Exception {
		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_profile);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_profile).click();

			} else {
				mblock.Element(ObjDashboard.lnk_profile).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_mngacnt);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_mngacnt).ufxFocus();

				}
				mblock.ValidateTest(true, true, "Manage account link is present");

			} else {
				mblock.ValidateTest(false, true, "Manage account link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My account link is not present in the status bar");
		}

	}

	// Verify My Account link is present in the footer
	public void VrfyMngeAccntFrmfooter() throws Exception {

		boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_footermngeacnt);
		if (txt_lnk = true) {
			mblock.Element(ObjDashboard.lnk_footermngeacnt).ufxScrollElementToView();
			mblock.ValidateTest(true, true, "Manage account link is present in footer");

		} else {
			mblock.ValidateTest(false, true, "Manage account link is not present in footer");
		}
	}

	// click the ManageAccount link under My Account
	public void clkMngeAccntFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_profile);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_profile).click();

			} else {
				mblock.Element(ObjDashboard.lnk_profile).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_mngacnt);
			if (txt_lnk = true) {
				mblock.ValidateTest(true, true, "Manage account link is present");
				mblock.Element(ObjDashboard.lnk_mngacnt).ufxClick();

			} else {
				mblock.ValidateTest(false, true, "Manage account link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My account link is not present in the status bar");
		}

	}

	// verify the profile link under My Account in global navigation
	public void vrfyProfileFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_profile);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_profile).click();

			} else {
				mblock.Element(ObjDashboard.lnk_profile).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_prfle);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_prfle).ufxFocus();

				}
				mblock.ValidateTest(true, true, "profile link is present");

			} else {
				mblock.ValidateTest(false, true, "profile link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My account link is not present in the status bar");
		}

	}

	// verify the profile link under My Account in footer
	public void vrfyProfileFrmfooter() throws Exception {

		boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_footerprfle);
		if (txt_lnk = true) {
			mblock.Element(ObjDashboard.lnk_footerprfle).ufxScrollElementToView();
			mblock.ValidateTest(true, true, "profile link is present in footer");

		} else {
			mblock.ValidateTest(false, true, "profile link is not present in footer");
		}
	}

	// click the profile link under My Account
	public void clkProfileFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_profile);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_profile).click();

			} else {
				mblock.Element(ObjDashboard.lnk_profile).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_prfle);
			if (txt_lnk = true) {
				mblock.Element(ObjDashboard.lnk_prfle).ufxClick();
				mblock.ElementExists(objProfile.Edit);
				mblock.ValidateTest(true, true, "User lands in MDM page");

			} else {
				mblock.ValidateTest(false, true, "profile link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My account link is not present in the status bar");
		}

	}

	// click the get assistant link in POD
	public void clkGetasstFrmPOD() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_getassistant);

		if (icon_lnk = true) {

			mblock.Element(ObjDashboard.lnk_getassistant).ufxClick();

			mblock.ElementExists(ObjDashboard.btn_contactus);
			mblock.ValidateTest(true, true, "Get assistant linl is present in the POD");

		}

		else {
			mblock.ValidateTest(false, true, "Get assistant linl is not present in the POD");
		}

	}

	// Verify the Help shield link under My Account
	public void vrfyHelpFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_profile);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_profile).click();

			} else {
				mblock.Element(ObjDashboard.lnk_profile).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_help);
			if (txt_lnk = true) {
				if (mblock.strBrowserType.equals("CHROME")) {
					mblock.Element(ObjDashboard.lnk_help).ufxFocus();

				}
				mblock.ValidateTest(true, true, "Help link is present");

			} else {
				mblock.ValidateTest(false, true, "Help link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My account link is not present in the status bar");
		}

	}

	// Verify the Help shield link under My Account in footer
	public void vrfyHelpFrmfooter() throws Exception {

		boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_footerhelp);
		if (txt_lnk = true) {
			mblock.Element(ObjDashboard.lnk_footerhelp).ufxScrollElementToView();
			mblock.ValidateTest(true, true, "Help link is present in footer");

		} else {
			mblock.ValidateTest(false, true, "Help link is not present footer");
		}
	}

	// click the Help shield link under My Account
	public void clkHelpFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_profile);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_profile).click();

			} else {
				mblock.Element(ObjDashboard.lnk_profile).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_help);
			if (txt_lnk = true) {
				mblock.Element(ObjDashboard.lnk_help).ufxClick();
				mblock.ElementExists(ObjDashboard.btn_help);
				mblock.ValidateTest(true, true, "User lands to Helps page");

			} else {
				mblock.ValidateTest(false, true, "Help link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My account link is not present in the status bar");
		}

	}

	// Verify the Singout shield link under My Account
	public void vrfySignOutFrmGlobalNav() throws Exception {

		boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_signout);
		if (txt_lnk = true) {
			if (mblock.strBrowserType.equals("CHROME")) {
				mblock.Element(ObjDashboard.lnk_signout).ufxFocus();

			}
			mblock.ValidateTest(true, true, "Singout link is present");

		} else {
			mblock.ValidateTest(false, true, "Singout link is not present");
		}
	}

	// click the Singout shield link under My Account
	public void clkSignOutFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_profile);
		if (icon_lnk = true) {
			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_profile).click();

			} else {
				mblock.Element(ObjDashboard.lnk_profile).ufxFocus();

			}

			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_signout);
			if (txt_lnk = true) {
				mblock.Element(ObjDashboard.lnk_signout).ufxClick();
				mblock.ElementExists(ObjLogin.txt_username);
				mblock.ValidateTest(true, true, "Successfully signout");

			} else {
				mblock.ValidateTest(false, true, "Singout link is not present");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My account link is not present in the status bar");
		}

	}

	// Verify the Global navigation background colour
	public void vrfyGlobalNavbckrndclr() throws Exception {

		mblock.ElementExists(ObjDashboard.glb_nav);
		String clr = mblock.Element(ObjDashboard.glb_nav).getCssValue("background-color");
		if (clr.equalsIgnoreCase("rgba(0, 0, 0, 0)")) {
			mblock.ValidateTest(true, true, "Gloabal navigation backgroud colour is white");
		}

		else {
			mblock.ValidateTest(false, true, "Gloabal navigation backgroud colour is not white");
		}

	}

	// Verify the TIS POD is displayed for user
	public void vrfyTISpodDsplyd() throws Exception {

		boolean val = mblock.ElementExists(ObjDashboard.pod_tis);
		boolean val1 = mblock.ElementExists(ObjDashboard.pod_tis1);

		if (val) {
			mblock.Element(ObjDashboard.pod_tis).ufxScrollElementToView();
			mblock.ValidateTest(true, true, "TIS POD is displayed to the User");
		}

		else if (val1) {
			mblock.Element(ObjDashboard.pod_tis1).ufxScrollElementToView();
			mblock.ValidateTest(true, true, "TIS POD is displayed to the User");
		}

		else {
			mblock.ValidateTest(false, true, "TIS POD is not displayed to the User");
		}
	}

	// Verify the TIS POD is not displayed for user
	public void vrfyTISpodNotDsplyd() throws Exception {

		boolean val = mblock.ElementDoesNotExists(ObjDashboard.pod_tis);
		boolean val1 = mblock.ElementExists(ObjDashboard.pod_tis1);

		if (val) {
			mblock.ValidateTest(true, true, "TIS POD is not displayed to the User");
		}

		else if (val1) {
			mblock.ValidateTest(true, true, "TIS POD is not displayed to the User");
		}

		else {
			mblock.ValidateTest(false, true, "TIS POD is displayed to the User");
		}
	}

}
