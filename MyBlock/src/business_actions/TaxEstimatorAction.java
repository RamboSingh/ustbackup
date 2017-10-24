package business_actions;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;



import pageObject.ObjDashboard;
import pageObject.ObjTaxestimator;
import utility.Constant_Class;

public class TaxEstimatorAction {

	MyblockActions mblock;

	public TaxEstimatorAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	// Verify the taxestimator link in global navigation (status bar)
	public void vrfyTaxEstmrLnkFrmGlobalNav() throws Exception {

		boolean icon_lnk = mblock.ElementExists(ObjDashboard.lnk_calctr);
		if (icon_lnk = true) {

			if (mblock.strBrowserType.equals("FIREFOX")) {
				mblock.Element(ObjDashboard.lnk_calctr).click();

			} else {
				mblock.Element(ObjDashboard.lnk_calctr).ufxFocus();

			}
			
			boolean txt_lnk = mblock.ElementExists(ObjDashboard.lnk_taxestmr);
			if (txt_lnk = true) {

				mblock.ValidateTest(true, true, "Tax Estimator link is present");
			} else {
				mblock.ValidateTest(false, true, "Tax Estimator link is not present");
				Assert.assertFalse(true);
			}
		}

		else {
			mblock.ValidateTest(true, true, "Calculato and tax icon is not present in the status bar");
			Assert.assertFalse(true);
		}

	}

	// click tax estimator link from Status bar
	public void clkTaxEstmrLnkFrmStusBar() throws Exception {
		vrfyTaxEstmrLnkFrmGlobalNav();

		mblock.Element(ObjDashboard.lnk_taxestmr).ufxClick();
		Thread.sleep(5000);
		boolean txt_lnk = mblock.ElementExists(ObjTaxestimator.lnk_aboutyou);

		if (txt_lnk = true) {
			mblock.ValidateTest(true, true, "Tax Estimator page is loaded");
		} else {
			mblock.ValidateTest(false, true, "Tax Estimator page is not loaded");
			Assert.assertFalse(true);
		}
	}

	// Verify the header text in About you
	public void vrfyAbtyouHeadingText() throws Exception {

		String lnk_aboutyou = mblock.Element(ObjTaxestimator.lnk_aboutyou).getAttribute("aria-selected");
		if (lnk_aboutyou.equalsIgnoreCase("false")) {
			mblock.Element(ObjTaxestimator.lnk_aboutyou).click();
		}
		boolean txt_header = mblock.ElementExists(ObjTaxestimator.txt_aboutyouheader);
		if (txt_header = true) {
			String text = mblock.Element(ObjTaxestimator.txt_aboutyouheader).getText();
			if (text.equalsIgnoreCase(Constant_Class.tax_aboutyouheader)) {
				mblock.ValidateTest(true, true, "About you tab heading text is displayed for year 2016");
			} else {
				mblock.ValidateTest(false, true, "About you tab heading text is not displayed for year 2016");
				Assert.assertFalse(true);
			}
		}
	}

	// Verify the TY in help text under About you tab
	public void vrfyHelpTextforTY2016() throws Exception {
		mblock.ElementExists(ObjTaxestimator.lnk_help);
		mblock.Element(ObjTaxestimator.lnk_help).click();
		boolean help_popup = mblock.ElementExists(ObjTaxestimator.txt_helpstep1);
		if (help_popup = true) {
			String step1 = mblock.Element(ObjTaxestimator.txt_helpstep1).getText();
			String step2 = mblock.Element(ObjTaxestimator.txt_helpstep2).getText();
			String step3 = mblock.Element(ObjTaxestimator.txt_helpstep3).getText();
			String step4 = mblock.Element(ObjTaxestimator.txt_helpstep4).getText();
			String step4_1 = mblock.Element(ObjTaxestimator.txt_helpstep4_1).getText();
			String step4_2 = mblock.Element(ObjTaxestimator.txt_helpstep4_2).getText();
			String step5 = mblock.Element(ObjTaxestimator.txt_helpstep5).getText();
			String step5_1 = mblock.Element(ObjTaxestimator.txt_helpstep5_1).getText();
			String step5_3 = mblock.Element(ObjTaxestimator.txt_helpstep5_3).getText();

			int step2_count = 0;
			int step4_count = 0;
			int step4_2_count = 0;

			Pattern p = Pattern.compile(Constant_Class.tax_year);
			Matcher m = p.matcher(step2);
			Matcher m1 = p.matcher(step4);
			Matcher m2 = p.matcher(step4_2);
			while (m.find()) {
				step2_count++;
			}

			while (m1.find()) {
				step4_count++;
			}

			while (m2.find()) {
				step4_2_count++;
			}

			if (step1.contains(Constant_Class.tax_year) && step3.contains(Constant_Class.tax_year)
					&& step4.contains(Constant_Class.tax_year) && step4_1.contains(Constant_Class.tax_year)
					&& step5_1.contains(Constant_Class.tax_year) && step5_3.contains(Constant_Class.tax_year)
					&& step2_count == 2 && step4_count == 2 && step4_2_count == 3) {
				mblock.ValidateTest(true, true, "All the Tax year content is with TY 2016.");
			} else {
				mblock.ValidateTest(false, true, "some Tax year content is not with TY 2016.");
				Assert.assertFalse(true);
			}

		}

	}

	// Enter About you detail and click next
	public void entrAboutDetailandClkNext() throws Exception {
		boolean txt_header1 = mblock.ElementExists(ObjTaxestimator.txt_incomeheader,5000);
		boolean txt_header2 = mblock.ElementExists(ObjTaxestimator.txt_expenseheader,5000);
		boolean txt_header3 = mblock.ElementExists(ObjTaxestimator.btn_online,5000);
		String lnk_aboutyou = mblock.Element(ObjTaxestimator.lnk_aboutyou).getAttribute("aria-selected");
		if (lnk_aboutyou.equalsIgnoreCase("false")
				&& (txt_header1 == true || txt_header2 == true || txt_header3 == true)) {
			mblock.Element(ObjTaxestimator.lnk_aboutyou).click();
		}

		String status = mblock.Element(ObjTaxestimator.lnk_continue).getAttribute("aria-disabled");
		if (status.equalsIgnoreCase("true")) {
			mblock.ElementExists(ObjTaxestimator.lnk_single);
			mblock.Element(ObjTaxestimator.lnk_single).ufxClick();
			mblock.Element(ObjTaxestimator.lnk_dependent0).ufxClick();
			mblock.Element(ObjTaxestimator.lnk_continue).ufxClick();
		} else {
			mblock.Element(ObjTaxestimator.lnk_continue).ufxClick();

		}

		boolean val = mblock.ElementExists(ObjTaxestimator.inpt_dob);
		mblock.Element(ObjTaxestimator.inpt_dob).ufxSetAttribute("value", "1980-07-25");
		// Runtime.getRuntime().exec(Constant_Class.doc_uplScript_dob);
		mblock.Element(ObjTaxestimator.lnk_next).ufxClick();
		mblock.ElementExists(ObjTaxestimator.txt_incomeheader);
		mblock.ValidateTest(true, true, "income tab is landed");

	}

	// Verify the header text in income tab
	public void vrfyIncomeHeadingText() throws Exception {
		String lnk_aboutyou = mblock.Element(ObjTaxestimator.lnk_income).getAttribute("aria-selected");
		if (lnk_aboutyou.equalsIgnoreCase("false")) {
			mblock.Element(ObjTaxestimator.lnk_income).click();
		}
		boolean txt_header = mblock.ElementExists(ObjTaxestimator.txt_incomeheader);
		if (txt_header = true) {
			String text = mblock.Element(ObjTaxestimator.txt_incomeheader).getText();
			if (text.equalsIgnoreCase(Constant_Class.tax_incomeheader)) {
				mblock.ValidateTest(true, true, "Income tab heading text is displayed for year 2016");
			} else {
				mblock.ValidateTest(false, true, "Income tab heading text is not displayed for year 2016");
				Assert.assertFalse(true);
			}
		}
	}

	// Verify the TY in help text under About you tab
	public void vrfyHelpTextIncometabforTY2016() throws Exception {
		mblock.ElementExists(ObjTaxestimator.lnk_help);
		mblock.Element(ObjTaxestimator.lnk_help).click();
		boolean help_popup = mblock.ElementExists(ObjTaxestimator.txt_helpstep1);
		if (help_popup = true) {
			String step1 = mblock.Element(ObjTaxestimator.txt_step1).getText();
			String step2 = mblock.Element(ObjTaxestimator.txt_step2).getText();
			String step3 = mblock.Element(ObjTaxestimator.txt_step3).getText();
			String step4 = mblock.Element(ObjTaxestimator.txt_step4).getText();
			String step5 = mblock.Element(ObjTaxestimator.txt_step5).getText();

			if (step1.contains(Constant_Class.tax_year) && step2.contains(Constant_Class.tax_year)
					&& step3.contains(Constant_Class.tax_year) && step4.contains(Constant_Class.tax_year)
					&& step5.contains(Constant_Class.tax_year)) {
				mblock.ValidateTest(true, true, "All the Tax year content is with TY 2016.");
			} else {
				mblock.ValidateTest(false, true, "some Tax year content is not with TY 2016.");
				Assert.assertFalse(true);
				
			}

		}

	}

	// Enter income detail and click next
	public void entrincomeDetailandClkNext() throws Exception {

		String lnk_aboutyou = mblock.Element(ObjTaxestimator.lnk_income).getAttribute("aria-selected");
		if (lnk_aboutyou.equalsIgnoreCase("false")) {
			mblock.Element(ObjTaxestimator.lnk_income).click();
		}
		boolean status = mblock.ElementExists(ObjTaxestimator.lnk_interest);
		if (status = true) {
			mblock.ElementExists(ObjDashboard.answr_col1,5000);
			mblock.Element(ObjTaxestimator.lnk_interest).ufxClick();
			mblock.Element(ObjTaxestimator.lnk_continue).ufxClick();
			/*
			 * boolean val =
			 * mblock.ElementExists(ObjTaxestimator.inpt_incomeamnt);
			 * mblock.Element(ObjTaxestimator.inpt_incomeamnt).sendKeys("100");
			 */
			mblock.Element(ObjTaxestimator.lnk_next).ufxClick();
			mblock.ElementExists(ObjTaxestimator.txt_expenseheader);
			mblock.ValidateTest(true, true, "Expenses tab is landed");

		}

	}

	// Verify the header text in expenses tab
	public void vrfyExpensesHeadingText() throws Exception {
		String lnk_aboutyou = mblock.Element(ObjTaxestimator.lnk_expenses).getAttribute("aria-selected");
		if (lnk_aboutyou.equalsIgnoreCase("false")) {
			mblock.Element(ObjTaxestimator.lnk_expenses).click();
		}
		boolean txt_header = mblock.ElementExists(ObjTaxestimator.txt_expenseheader);
		if (txt_header = true) {
			String text = mblock.Element(ObjTaxestimator.txt_expenseheader).getText();
			if (text.equalsIgnoreCase(Constant_Class.tax_expenseheader)) {
				mblock.ValidateTest(true, true, "Expenses tab heading text is displayed for year 2016");
			} else {
				mblock.ValidateTest(false, true, "Expenses tab heading text is not displayed for year 2016");
				Assert.assertFalse(true);
			}
		}
	}

	public void vrfyHelpTextExpensTabforTY2016() throws Exception {
		mblock.ElementExists(ObjTaxestimator.lnk_help);
		mblock.Element(ObjTaxestimator.lnk_help).click();
		boolean help_popup = mblock.ElementExists(ObjTaxestimator.txt_step01);
		if (help_popup = true) {
			String step1 = mblock.Element(ObjTaxestimator.txt_step01).getText();
			String step2 = mblock.Element(ObjTaxestimator.txt_step02).getText();
			String step3 = mblock.Element(ObjTaxestimator.txt_step03).getText();
			String step4 = mblock.Element(ObjTaxestimator.txt_step04).getText();
			String step5 = mblock.Element(ObjTaxestimator.txt_step05).getText();
			String step6 = mblock.Element(ObjTaxestimator.txt_step06).getText();
			String step7 = mblock.Element(ObjTaxestimator.txt_step07).getText();
			String step8 = mblock.Element(ObjTaxestimator.txt_step08).getText();
			String step9 = mblock.Element(ObjTaxestimator.txt_step09).getText();
			String step10 = mblock.Element(ObjTaxestimator.txt_step10).getText();

			int step4_count = 0;
			int step5_count = 0;

			Pattern p = Pattern.compile(Constant_Class.tax_year);
			Matcher m1 = p.matcher(step4);
			Matcher m2 = p.matcher(step5);

			while (m1.find()) {
				step4_count++;
			}

			while (m2.find()) {
				step5_count++;
			}

			if (step1.contains(Constant_Class.tax_year) && step2.contains(Constant_Class.tax_year)
					&& step3.contains(Constant_Class.tax_year) && step6.contains(Constant_Class.tax_year)
					&& step7.contains(Constant_Class.tax_year) && step8.contains(Constant_Class.tax_year)
					&& step9.contains(Constant_Class.tax_year) && step10.contains(Constant_Class.tax_year)
					&& step4_count == 2 && step4_count == 2 && step5_count == 2) {
				mblock.ValidateTest(true, true, "All the Tax year content is with TY 2016.");
			} else {
				mblock.ValidateTest(false, true, "some Tax year content is not with TY 2016.");
				Assert.assertFalse(true);
			}

		}

	}

}
