package business_actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObject.ObjMyDocmnt;
import pageObject.ObjTaxHistory;
import utility.Constant_Class;

public class MydocumentAction {

	MyblockActions mblock;

	public MydocumentAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	// Verify the My document Header text
	public void vrfyMydocheader() throws Exception {

		boolean txt = mblock.ElementExists(ObjMyDocmnt.txt_header);
		if (txt = true) {
			String val = mblock.Element(ObjMyDocmnt.txt_header).getText();
			if (val.equalsIgnoreCase("My Documents")) {
				mblock.ValidateTest(true, true, "My document Header Text is displayed correctly");
			} else {
				mblock.ValidateTest(false, true, "My document Header Text is displayed not correctly");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My document Header is not displayed");
		}

	}

	// Verify the My document image(book and pen) text
	public void vrfyMydocBckImg() throws Exception {

		boolean txt = mblock.ElementExists(ObjMyDocmnt.img_mydoc);
		if (txt = true) {
			String val = mblock.Element(ObjMyDocmnt.img_mydoc).getCssValue("background-image");
			if (val.contains(Constant_Class.img_mydoc)) {
				mblock.ValidateTest(true, true, "My document Backround image(book and pen) is displayed");
			} else {
				mblock.ValidateTest(false, true, "My document Backround image(book and pen) is not displayed");
			}
		}

		else {
			mblock.ValidateTest(false, true, "My doc Header is not displayed");
		}

	}

	// Verify the My document year for 2016 as default
	public void vrfyDfltMydocYr2016() throws Exception {

		boolean txt = mblock.ElementExists(ObjMyDocmnt.drp_taxyear);
		if (txt = true) {
			boolean val1 = mblock.ElementExists(ObjMyDocmnt.doc_year);
			
			String val = mblock.Element(ObjMyDocmnt.doc_year).getAttribute("innerText");
			
			if (val.contains(Constant_Class.tax_year)) {
				mblock.ValidateTest(true, true, "My document with 2016 as default year");
			} else {
				mblock.ValidateTest(false, true, "My document not with 2016 as default year");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Tax year dropdown is not displayed");
		}

	}

	// Verify the TY in My document is not present in Dropdown
	public void vrfyMydocYrNotDsply(String year) throws Exception {

		boolean txt = mblock.ElementExists(ObjMyDocmnt.drp_taxyear);
		if (txt = true) {

			boolean val = mblock.Element(ObjMyDocmnt.drp_taxyear).ufxIsItemExistInDropdown(year);

			if (val == true) {
				mblock.ValidateTest(false, true, year + " is displayed");
			} else {
				mblock.ValidateTest(true, true, year + " is not displayed");
			}
		}

		else {
			mblock.ValidateTest(false, true, "Tax year dropdown is not displayed");
		}

	}

	// Click Tax year in my document from Dropdown
	public void clkMydocYrFrmDrpdwn(String year) throws Exception {

		boolean txt = mblock.ElementExists(ObjMyDocmnt.drp_taxyear);
		if (txt = true) {
			mblock.Element(ObjMyDocmnt.drp_taxyear).ufxSelectFromDropdown("MY " + year + " DOCS");
			mblock.ElementExists(ObjMyDocmnt.img_ldng);
			String ldng = mblock.Element(ObjMyDocmnt.img_ldng).getCssValue("display");
			
			String val = mblock.Element(ObjMyDocmnt.doc_year).getAttribute("innerText");

			if (val.contains(year)) {
				mblock.ValidateTest(true, true, "User is successfully lands in TY" + year);
			} else {
				mblock.ValidateTest(false, true, "User is not successfully lands in TY" + year);
			}
		}

		else {
			mblock.ValidateTest(false, true, "Tax year dropdown is not displayed");
		}

	}
}
