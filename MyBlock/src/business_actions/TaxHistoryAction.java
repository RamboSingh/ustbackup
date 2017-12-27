package business_actions;

import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import pageObject.ObjDashboard;
import pageObject.ObjTaxHistory;
import utility.Constant_Class;

public class TaxHistoryAction {

	MyblockActions mblock;

	public TaxHistoryAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	// Verify the tax history Header text
	public void vrfyTaxheader() throws Exception {

		boolean txt = mblock.ElementExists(ObjTaxHistory.txt_taxhstry);
		if (txt = true) {
			String val = mblock.Element(ObjTaxHistory.txt_taxhstry).getText();
			if (val.equalsIgnoreCase("Tax History")) {
				mblock.ValidateTest(true, true, "Header Text is displayed correctly");
			} else {
				mblock.ValidateTest(false, true, "Header Text is displayed not correctly");
				Assert.assertFalse(true);
			}
		}

		else {
			mblock.ValidateTest(false, true, "Header is not displayed");
			Assert.assertFalse(true);
		}

	}

	// Verify the tax history image(book and pen) text
	public void vrfyTaxHstryBckImg() throws Exception {

		WebElement fd_tax =  mblock.objWebDriver.findElement(By.xpath("//*[@id='refundTHAmt']/span"));
		WebElement state_tax =  mblock.objWebDriver.findElement(By.xpath("/*[@id='taxHistoryTopSection']/div/div[1]/div[2]/div/div"));
		Point point = fd_tax.getLocation();
		int xfd = point.getX();
		int yfd = point.getY();
		
		Point point1 = state_tax.getLocation();
		int x1fd = point1.getX();
		int y1fd = point1.getY();
		

	}

	// Verify the tax history Header text for new user
	public void vrfyTaxhdrfrNewuser() throws Exception {

		boolean txt = mblock.ElementExists(ObjTaxHistory.txt_hdrnewuser);
		if (txt = true) {
			String val = mblock.Element(ObjTaxHistory.txt_hdrnewuser).getText();
			String val1 = mblock.Element(ObjTaxHistory.txt_hdrsb1newuser).getText();
			String val2 = mblock.Element(ObjTaxHistory.txt_hdrsb2newuser).getText();

			if (val.equalsIgnoreCase(Constant_Class.tax_hdrnewusr)
					&& val1.equalsIgnoreCase(Constant_Class.tax_hdrsb1newusr)
					&& val2.equalsIgnoreCase(Constant_Class.tax_hdrsb2newusr)) {
				mblock.ValidateTest(true, true, "Header Text is displayed correctly for non IDP user");
			} else {
				mblock.ValidateTest(false, true, "Header Text is not displayed correctly for non IDP user");
				Assert.assertFalse(true);
			}
		}

		else {
			mblock.ValidateTest(false, true, "Header is not displayed");
			Assert.assertFalse(true);
		}

	}

	// Verify the tax return fields

	public void vrfyTaxRtnfield() throws Exception {
		mblock.ElementExists(ObjDashboard.answr_col1, 5000);
		boolean fed_rtrn = mblock.ElementExists(ObjTaxHistory.fed_rtrn, 5000);
		boolean state_rtrn = mblock.ElementExists(ObjTaxHistory.state_rtrn, 5000);
		boolean filing_status = mblock.ElementExists(ObjTaxHistory.fed_fillngSts, 5000);
		boolean filing_date = mblock.ElementExists(ObjTaxHistory.fed_fillngDte, 5000);

		if (fed_rtrn && state_rtrn && filing_status && filing_date) {
			String val = mblock.Element(ObjTaxHistory.txt_fed_rtrn_val).getText();
			String val1 = mblock.Element(ObjTaxHistory.txt_fed_fillngSts_val).getText();
			String val2 = mblock.Element(ObjTaxHistory.txt_fed_fillngDte_val).getText();
			String val3 = mblock.Element(ObjTaxHistory.txt_state_rtrn_val).getText();

			if (!val.isEmpty() && !val1.isEmpty() && !val2.isEmpty() && !val3.isEmpty()) {
				mblock.ValidateTest(true, true, "User can able to view Tax return fields");
			} else {
				mblock.ValidateTest(false, true, "User can not able to view some Tax return fields");
				Assert.assertFalse(true);
			}
		}

		else if (fed_rtrn || state_rtrn && filing_date && filing_status) {

			String val = mblock.Element(ObjTaxHistory.txt_fed_rtrn_val).getText();
			String val1 = mblock.Element(ObjTaxHistory.txt_fed_fillngSts_val).getText();
			String val2 = mblock.Element(ObjTaxHistory.txt_fed_fillngDte_val).getText();

			if (!val.isEmpty() && !val1.isEmpty() && !val2.isEmpty()) {
				mblock.ValidateTest(true, true, "User can able to view Tax return fields");
			} else {
				mblock.ValidateTest(false, true, "User can not able to view some Tax return fields");
				Assert.assertFalse(true);
			}

		}

		else {
			mblock.ValidateTest(true, "Tax return field is not available for this tax year");

		}

	}

	// Verify the tax return colour for positive and negative

	public void vrfyTaxRtnclr() throws Exception {
		mblock.ElementExists(ObjDashboard.answr_col1, 5000);
		boolean fed_rtrn = mblock.ElementExists(ObjTaxHistory.fed_rtrn, 5000);
		boolean state_rtrn = mblock.ElementExists(ObjTaxHistory.state_rtrn, 5000);
		boolean filing_status = mblock.ElementExists(ObjTaxHistory.fed_fillngSts, 5000);
		boolean filing_date = mblock.ElementExists(ObjTaxHistory.fed_fillngDte, 5000);

		if (fed_rtrn && state_rtrn && filing_status && filing_date) {

			String txt1 = mblock.Element(ObjTaxHistory.txt_fed_rtrn_val).getText();
			String txt2 = mblock.Element(ObjTaxHistory.txt_state_rtrn_val).getText();
			if (txt1.contains("-") && txt2.contains("-")) {
				String val = mblock.Element(ObjTaxHistory.txt_fed_rtrn_val).getCssValue("color");
				String val1 = mblock.Element(ObjTaxHistory.txt_state_rtrn_val).getCssValue("color");
				
				if ((val.equalsIgnoreCase("rgb(225, 180, 22)")||val.equalsIgnoreCase("rgba(225, 180, 22, 1)")) && (val1.equalsIgnoreCase("rgb(225, 180, 22)")||val1.equalsIgnoreCase("rgba(225, 180, 22, 1)"))) {
					mblock.ValidateTest(true, true, "Values are negative and color is orange");
				}

				else {
					mblock.ValidateTest(false, true, "Values are negative and color is not orange");
				}
			}

			else if (txt1.contains("-") && !txt2.isEmpty()) {
				String val = mblock.Element(ObjTaxHistory.txt_fed_rtrn_val).getCssValue("color");
				String val1 = mblock.Element(ObjTaxHistory.txt_state_rtrn_val).getCssValue("color");
				if ((val.equalsIgnoreCase("rgb(225, 180, 22)")||val.equalsIgnoreCase("rgba(225, 180, 22, 1)")) && (val1.equalsIgnoreCase("rgb(23, 163, 178)")||val1.equalsIgnoreCase("rgba(23, 163, 178, 1)"))) {
					mblock.ValidateTest(true, true, "Orange color for negavite and teal color for positive");
				}

				else {
					mblock.ValidateTest(false, true, "color is mismatched");
				}
			}

			else if (!txt1.isEmpty() && txt2.contains("-")) {
				String val = mblock.Element(ObjTaxHistory.txt_fed_rtrn_val).getCssValue("color");
				String val1 = mblock.Element(ObjTaxHistory.txt_state_rtrn_val).getCssValue("color");
				System.out.println(val+" "+val1);
				if ((val.equalsIgnoreCase("rgb(23, 163, 178)")||val.equalsIgnoreCase("rgba(23, 163, 178, 1)")) && (val1.equalsIgnoreCase("rgb(225, 180, 22)")||val1.equalsIgnoreCase("rgba(225, 180, 22, 1)"))) {
					mblock.ValidateTest(true, true, "Orange color for negavite and teal color for positive");
				}

				else {
					mblock.ValidateTest(false, true, "color is mismatched");
				}
			} else if (!txt1.isEmpty() && !txt2.isEmpty()) {

				String val = mblock.Element(ObjTaxHistory.txt_fed_rtrn_val).getCssValue("color");
				String val1 = mblock.Element(ObjTaxHistory.txt_state_rtrn_val).getCssValue("color");
				if ((val.equalsIgnoreCase("rgb(23, 163, 178)")||val.equalsIgnoreCase("rgba(23, 163, 178, 1)")) && (val1.equalsIgnoreCase("rgb(23, 163, 178)")||val1.equalsIgnoreCase("rgba(23, 163, 178, 1)"))) {
					mblock.ValidateTest(true, true, "Values are positive and color is Teal");
				}

				else {
					mblock.ValidateTest(false, true, "Values are negative and color is not Teal");
				}
			}

			else {
				mblock.ValidateTest(false, true, "value is not displayed");

			}
		}

		else if (fed_rtrn && filing_date && filing_status) {

			String txt1 = mblock.Element(ObjTaxHistory.txt_fed_rtrn_val).getText();
			if (txt1.contains("-")) {
				String val = mblock.Element(ObjTaxHistory.txt_fed_rtrn_val).getCssValue("color");

				if (val.equalsIgnoreCase("rgb(225, 180, 22)")||val.equalsIgnoreCase("rgba(225, 180, 22, 1)")) {
					mblock.ValidateTest(true, true, "Values are negative and color is orange");
				}

				else {
					mblock.ValidateTest(false, true, "Values are negative and color is not orange");
				}
			}

			else if (!txt1.isEmpty()) {

				String val = mblock.Element(ObjTaxHistory.txt_fed_rtrn_val).getCssValue("color");

				if (val.equalsIgnoreCase("rgb(23, 163, 178)")||val.equalsIgnoreCase("rgba(23, 163, 178, 1)")) {
					mblock.ValidateTest(true, true, "Values are positive and color is Teal");
				}

				else {
					mblock.ValidateTest(false, true, "Values are negative and color is not Teal");
				}
			}

			else {
				mblock.ValidateTest(false, true, "value is not displayed");

			}

		}

		else {
			mblock.ValidateTest(true, "Tax return field is not available for this tax year");

		}

	}

	
	//Verify the position of the return data
	public void vrfyPositionTaxRtrn() throws Exception {

		mblock.ElementExists(ObjDashboard.answr_col1, 5000);
		boolean fed_rtrn = mblock.ElementExists(ObjTaxHistory.fed_rtrn, 5000);
		boolean state_rtrn = mblock.ElementExists(ObjTaxHistory.state_rtrn, 5000);
		boolean filing_status = mblock.ElementExists(ObjTaxHistory.fed_fillngSts, 5000);
		boolean filing_date = mblock.ElementExists(ObjTaxHistory.fed_fillngDte, 5000);
		boolean txt = mblock.ElementExists(ObjTaxHistory.drp_taxyear);
		
		if (txt = true) {
			// WebElement element =
			// mblock.Element(ObjTaxHistory.drp_taxyear).ufxGetSelectedItemInDropdown();
			String val = mblock.Element(ObjTaxHistory.txt_year).getAttribute("value");

			if (val.contains(Constant_Class.tax_year1)) {
				mblock.ValidateTest(true, true, "Tax History with 2016 as default year");
			} else {
				mblock.ValidateTest(false, true, "Tax History not with 2016 as default year");
				Assert.assertFalse(true);
			}
		}

		else {
			mblock.ValidateTest(false, true, "Tax year dropdown is not displayed");
			Assert.assertFalse(true);
		}

	}
	
	
	// Verify the tax history year history for 2016 as default
	public void vrfyDfltTaxhstryYr2016() throws Exception {

		boolean txt = mblock.ElementExists(ObjTaxHistory.drp_taxyear);
		if (txt = true) {
			// WebElement element =
			// mblock.Element(ObjTaxHistory.drp_taxyear).ufxGetSelectedItemInDropdown();
			String val = mblock.Element(ObjTaxHistory.txt_year).getAttribute("value");

			if (val.contains(Constant_Class.tax_year1)) {
				mblock.ValidateTest(true, true, "Tax History with 2016 as default year");
			} else {
				mblock.ValidateTest(false, true, "Tax History not with 2016 as default year");
				Assert.assertFalse(true);
			}
		}

		else {
			mblock.ValidateTest(false, true, "Tax year dropdown is not displayed");
			Assert.assertFalse(true);
		}

	}

	// Verify the TY in Tax history is not present in Dropdown
	public void vrfyTaxhstryYrNotDsply(String year) throws Exception {

		boolean txt = mblock.ElementExists(ObjTaxHistory.drp_taxyear);
		if (txt = true) {

			boolean val = mblock.Element(ObjTaxHistory.drp_taxyear).ufxIsItemExistInDropdown(year);

			if (val == true) {
				mblock.ValidateTest(false, true, year + " is displayed");
				Assert.assertFalse(true);
			} else {
				mblock.ValidateTest(true, true, year + " is not displayed");

			}
		}

		else {
			mblock.ValidateTest(false, true, "Tax year dropdown is not displayed");
			Assert.assertFalse(true);
		}

	}

	// Click Tax year history from Dropdown
	public void clkTaxhstryYrFrmDrpdwn(String year) throws Exception {

		boolean txt = mblock.ElementExists(ObjTaxHistory.drp_taxyear);
		if (txt = true) {
			mblock.Element(ObjTaxHistory.drp_taxyear).ufxSelectFromDropdown("MY " + year + " TAXES");
			mblock.ElementExists(ObjTaxHistory.txt_year);
			String val = mblock.Element(ObjTaxHistory.txt_year).getAttribute("value");

			if (val.contains(year)) {
				mblock.ValidateTest(true, true, "User is successfully lands in TY" + year);
			} else {
				mblock.ValidateTest(false, true, "User is not successfully lands in TY" + year);
				Assert.assertFalse(true);
			}
		}

		else {
			mblock.ValidateTest(false, true, "Tax year dropdown is not displayed");
			Assert.assertFalse(true);
		}

	}

	// Verify the Return date is displayed
	public void vrfyTaxRtrnData() throws Exception {

		boolean txt = mblock.ElementExists(ObjTaxHistory.txt_rtrnyr);
		if (txt = true) {

			mblock.ValidateTest(true, true, "Tax data for the respective year is displayed.");
		}

		else {
			mblock.ValidateTest(false, true, "Tax data for the respective year is not displayed.");
			Assert.assertFalse(true);
		}
	}

	// click Tax retrun document for digital user
	public void clkRtrnDocDgtlUsr() throws Exception {

		String window1 = mblock.objWebDriver.getWindowHandle();
		boolean txt = mblock.ElementExists(ObjTaxHistory.rtn_doc_fld);
		if (txt = true) {
			mblock.Element(ObjTaxHistory.rtn_doc_fld).ufxScrollElementToView();

			mblock.Element(ObjTaxHistory.rtn_doc, "Online Tax Return").ufxClick();
			boolean val = mblock.ElementExists(ObjDashboard.iframe_idp);

			Set<String> window2 = mblock.objWebDriver.getWindowHandles();
			System.out.println(window2);
			if (window2.size() == 2) {
				for (String window : window2) {
					mblock.objWebDriver.switchTo().window(window);
				}
				mblock.ValidateTest(true, true, "New window is opened");
			} else {
				mblock.ValidateTest(false, true, "New window is not opened");
				Assert.assertFalse(true);
			}

		}

		else {
			mblock.ValidateTest(false, true, "Tax return document is not displayed for Tax year");
			Assert.assertFalse(true);
		}
	}

	// click Tax return document for retail user
	public void clkRtrnDocRtlUsr() throws Exception {

		String window1 = mblock.objWebDriver.getWindowHandle();
		boolean txt = mblock.ElementExists(ObjTaxHistory.rtn_doc_fld);
		if (txt = true) {
			mblock.Element(ObjTaxHistory.rtn_doc_fld).ufxScrollElementToView();

			mblock.Element(ObjTaxHistory.rtn_doc, "Tax Return").ufxClick();
			boolean val = mblock.ElementExists(ObjDashboard.iframe_idp);

			Set<String> window2 = mblock.objWebDriver.getWindowHandles();
			System.out.println(window2);
			if (window2.size() == 2) {
				for (String window : window2) {
					mblock.objWebDriver.switchTo().window(window);
				}
				mblock.ValidateTest(true, true, "New window is opened");
			} else {
				mblock.ValidateTest(false, true, "New window is not opened");
				Assert.assertFalse(true);
			}

		}

		else {
			mblock.ValidateTest(false, true, "Tax return document is not displayed for Tax year");
			Assert.assertFalse(true);
		}
	}

	// Verify the YOY coloum for two years comparing
	public void vrfyYoyClmn() throws Exception {
		mblock.ElementExists(ObjDashboard.answr_col1, 5000); // it is for time
																// waiting
		boolean txt = mblock.ElementExists(ObjTaxHistory.tbl_yoy);
		String txt1 = mblock.Element(ObjTaxHistory.tbl_yoy).getAttribute("style");
		if (txt1.equalsIgnoreCase("")) {
			mblock.ElementExists(ObjTaxHistory.txt_yoy);
			mblock.Element(ObjTaxHistory.txt_yoy).ufxScrollElementToView();
			String Yoy = mblock.Element(ObjTaxHistory.txt_yoy).getText();
			String yr1 = mblock.Element(ObjTaxHistory.txt_yoy2016).getText();
			String yr2 = mblock.Element(ObjTaxHistory.txt_yoy2015).getText();
			String var = mblock.Element(ObjTaxHistory.txt_yoyvar).getText();

			if (Yoy.equalsIgnoreCase("Year to Year") && yr1.equalsIgnoreCase("2016") && yr2.equalsIgnoreCase("2015")
					&& var.equalsIgnoreCase("Variations")) {

				mblock.ValidateTest(true, true, "YOY Coloums are displayed correctly");
			} else {
				mblock.ValidateTest(false, true, "YOY Coloums are not display correctly");
				Assert.assertFalse(true);
			}

		}

		else {
			mblock.ValidateTest(false, true, "YOY is not displayed");
			Assert.assertFalse(true);
		}
	}

	// Verify the fedaral tax summary coloum for 2016
	public void vrfyFedarlTaxClmn2016() throws Exception {
		mblock.ElementExists(ObjDashboard.answr_col1, 5000); // it is for time
																// waiting
		boolean txt = mblock.ElementExists(ObjTaxHistory.tbl_smry);
		String txt1 = mblock.Element(ObjTaxHistory.tbl_smry).getAttribute("style");
		if (txt1.equalsIgnoreCase("")) {
			mblock.ElementExists(ObjTaxHistory.txt_smry);
			mblock.Element(ObjTaxHistory.txt_smry).ufxScrollElementToView();
			String Yoy = mblock.Element(ObjTaxHistory.txt_smry).getText();
			String yr1 = mblock.Element(ObjTaxHistory.txt_smry2016).getText();

			if (Yoy.equalsIgnoreCase("Federal Tax Summary") && yr1.equalsIgnoreCase("2016")) {

				mblock.ValidateTest(true, true, "Fedaral Tax summary Coloums are displayed correctly");
			} else {
				mblock.ValidateTest(false, true, "Fedaral Tax summary Coloums are not display correctly");
				Assert.assertFalse(true);
			}

		}

		else {
			mblock.ValidateTest(false, true, "Fedaral Tax summary is not displayed");
			Assert.assertFalse(true);
		}
	}

	// Verify the fedaral tax summary coloum for 2015
	public void vrfyFedarlTaxClmn2015() throws Exception {
		mblock.ElementExists(ObjDashboard.answr_col1, 5000); // it is for time
																// waiting
		boolean txt = mblock.ElementExists(ObjTaxHistory.tbl_smry);
		String txt1 = mblock.Element(ObjTaxHistory.tbl_smry).getAttribute("style");
		if (txt1.equalsIgnoreCase("")) {
			mblock.ElementExists(ObjTaxHistory.txt_smry);
			mblock.Element(ObjTaxHistory.txt_smry).ufxScrollElementToView();
			String Yoy = mblock.Element(ObjTaxHistory.txt_smry).getText();
			String yr1 = mblock.Element(ObjTaxHistory.txt_smry2015).getText();

			if (Yoy.equalsIgnoreCase("Federal Tax Summary") && yr1.equalsIgnoreCase("2015")) {

				mblock.ValidateTest(true, true, "Fedaral Tax summary Coloums are displayed correctly");
			} else {
				mblock.ValidateTest(false, true, "Fedaral Tax summary Coloums are not display correctly");
				Assert.assertFalse(true);
			}

		}

		else {
			mblock.ValidateTest(false, true, "Fedaral Tax summary is not displayed");
			Assert.assertFalse(true);
		}
	}

	// Verify the YOY coloum is not displayed
	public void vrfyYoyNotDsply() throws Exception {
		mblock.ElementExists(ObjDashboard.answr_col1, 5000); // it is for time
																// waiting
		String txt = mblock.Element(ObjTaxHistory.tbl_yoy).getAttribute("style");
		String txt1 = mblock.Element(ObjTaxHistory.tbl_smry).getAttribute("style");
		if (txt.equalsIgnoreCase("display: none;") && txt1.equalsIgnoreCase("display: none;")) {
			mblock.ValidateTest(true, true, "YOY data is not displayed");
		} else {
			mblock.ValidateTest(false, true, "YOY data is  displayed");
			Assert.assertFalse(true);
		}
	}

	// Verify the purchase my Return button
	public void vrfyPrchsRtnBtn() throws Exception {
		boolean val = mblock.ElementExists(ObjTaxHistory.lnk_prchsrtn);
		if (val == true) {
			mblock.ValidateTest(true, true, "Purchase my return button is displayed");
		} else {
			mblock.ValidateTest(false, true, "Purchase my return button is not displayed");
			Assert.assertFalse(true);
		}
	}

}
