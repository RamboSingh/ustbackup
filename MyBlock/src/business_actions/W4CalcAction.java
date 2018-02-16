package business_actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import pageObject.ObjDashboard;
import pageObject.ObjLogin;
import pageObject.objW4Calc;
import utility.Constant_Class;

public class W4CalcAction {

	MyblockActions mblock;
	

	public W4CalcAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	// W4 Click from dashboard
	
	public void Calculator_Click() throws Exception {

		try {
			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_calctr);
			if(element1){
				mblock.ValidateTest(true,true, "W4 Calculator link is displayed");
				mblock.Log("Clicking on W4 Calculator Link");
				mblock.Element(ObjDashboard.lnk_calctr).ufxFocus();
			
			}else {

				mblock.ValidateTest(false, true, "W4 Calculator link is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void w4Click_Dashboard() throws Exception {

		try {
			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_w4calctr);
			if(element1){
				mblock.ValidateTest(true,true, "W4 Calculator link is displayed");
				mblock.Log("Clicking on W4 Calculator Link");
				mblock.Element(ObjDashboard.lnk_w4calctr).click();
			
			}else {

				mblock.ValidateTest(false, true, "W4 Calculator link is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

			
	// Entering values in Page 1:
		//Pay Frequency Function
	public void payFrequency() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.payFreq);
			if(element1){
				mblock.ValidateTest(true,true, "Pay frequency field is displayed");
				mblock.Log("Clicking on Pay Frequency field");
				new Select (mblock.Element(objW4Calc.payFreq)).selectByIndex(1);
				
				
			}else {

				mblock.ValidateTest(false,true, "Pay Frequency field is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
		// Federal With holding to Date
	public void lastCheckDate() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.lastCheckDate);
			if(element1){
				mblock.ValidateTest(true,true, "Last Check Date field is displayed");
				mblock.Log("Clicking on Last check Date");
				
				mblock.Element(objW4Calc.lastCheckDate).click();
				mblock.Element(objW4Calc.lastCheckDate).ufxSetAttribute("value", "2018-10-10");
				
				
				
				
			}else {

				mblock.ValidateTest(false,true, "Last Check Date field is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

		// Gross Pay Per Check
	public void grossPayPerCheck() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.grossPayPerCheck);
			if(element1){
				mblock.ValidateTest(true,true, "Gross Pay Per Check field is displayed");
				mblock.Log("Clicking on Gross Pay Per Check field");
				mblock.Element(objW4Calc.grossPayPerCheck).click();
				mblock.Element(objW4Calc.grossPayPerCheck).sendKeys("1500");
				
			}else {

				mblock.ValidateTest(false,true, "Gross Pay Per Check field is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//Page 2
		//Fed Income Tax Withheld Per Paycheck
	public void fedIncomeTaxWithheldPerCheck() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.fedIncomeTaxWithHeld);
			if(element1){
				mblock.ValidateTest(true,true, "Fed Income Tax Withheld Per Paycheck field is displayed");
				mblock.Log("Clicking on Fed Income Tax Withheld Per Paycheck field");
				mblock.Element(objW4Calc.fedIncomeTaxWithHeld).click();
				mblock.Element(objW4Calc.fedIncomeTaxWithHeld).sendKeys("200");
				
			}else {

				mblock.ValidateTest(false,true, "Fed Income Tax Withheld Per Paycheck field is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
		//Fed WithHolding To Date
	public void fedWithHoldingToDate() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.fedWithHolding);
			if(element1){
				mblock.ValidateTest(true,true, "Fed WithHolding To Date field is displayed");
				mblock.Log("Clicking on Fed WithHolding To Date field");
				mblock.Element(objW4Calc.fedWithHolding).click();
				mblock.Element(objW4Calc.fedWithHolding).sendKeys("1800");
				
			}else {

				mblock.ValidateTest(false,true, "Fed WithHolding To Date field is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
		//Other Payroll Deductions
	public void otherPayrollDed() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.otherPayrollDed);
			if(element1){
				mblock.ValidateTest(true,true, "Other Payroll Deductions field is displayed");
				mblock.Log("Clicking on Other Payroll Deductions field");
				mblock.Element(objW4Calc.otherPayrollDed).click();
				mblock.Element(objW4Calc.otherPayrollDed).sendKeys("1500");
			}else {

				mblock.ValidateTest(false,true, "Other Payroll Deductions field is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//Page 3
		//Allowances
	public void allowances() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.allowancesValue);
			if(element1){
				mblock.ValidateTest(true,true, "Allowances field is displayed");
				mblock.Log("Clicking on Allowances field");
				mblock.Element(objW4Calc.allowancesValue).clear();
				mblock.Element(objW4Calc.allowancesValue).sendKeys("5");
				
			}else {

				mblock.ValidateTest(false,true, "Allowances field is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//Page4
		//Filing Status
	public void filingStatus() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.filingStatus);
			if(element1){
				mblock.ValidateTest(true,true, "Filing Status field is displayed");
				mblock.Log("Clicking on Filing Status field");
				new Select(mblock.Element(objW4Calc.filingStatus)).selectByIndex(1);
				
			}else {

				mblock.ValidateTest(false,true, "Filing Status field is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
		//Estimated Fed Liability
	public void estimatedFedLiability() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.fedTaxLiability);
			if(element1){
				mblock.ValidateTest(true,true, "Estimated Fed Liability field is displayed");
				mblock.Log("Clicking on Estimated Fed Liability field");
				
				mblock.Element(objW4Calc.fedTaxLiability).click();
				mblock.Element(objW4Calc.fedTaxLiability).sendKeys("5");
			}else {
				
				mblock.ValidateTest(false,true, "Estimated Fed Liability field is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//Next Button
	public void nxtBtn() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.nxtBtn);
			if(element1){
				mblock.ValidateTest(true,true, "Next Button is present");
				mblock.Log("Clicking on Next Button");
				
				mblock.Element(objW4Calc.nxtBtn).click();
				
			}else {
				
				mblock.ValidateTest(false,true, "Next Button is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//from POD

	public void w4Pod() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objW4Calc.w4Pod);
			if(element1){
				mblock.ValidateTest(true,true, "W4 Pod Button is present");
				mblock.Log("Clicking on W4 Pod Button");
				
				
			}else {
				
				mblock.ValidateTest(false,true, "W4 Pod Button is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
