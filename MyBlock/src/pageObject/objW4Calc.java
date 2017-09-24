package pageObject;

import org.openqa.selenium.By;

public class objW4Calc {

	//Page launch link is in ObjDashboard
		//Page1
		public static By payFreq = By.id("payFrequency");
		public static By lastCheckDate = By.id("lastCheckDate");
		public static By grossPayPerCheck = By.id("grossPayPerCheck");
		
		//Page2
		public static By fedIncomeTaxWithHeld = By.id("federalIncomeTaxWithheld");
		public static By fedWithHolding = By.id("federalWithholdingToDate");
		public static By otherPayrollDed = By.id("otherPayrollDeductions");
		
		//Page3
		public static By allowancesValue = By.id("allowancesValue");
		
		//Page4
		public static By filingStatus = By.id("filingStatus");
		public static By fedTaxLiability = By.id("federalTaxLiability");
		
		//Common btns (next button)
		public static By nxtBtn = By.cssSelector("input.btn.btn-green");
		
		//From Pod
		public static By w4Pod = By.linkText("View My Planner");
}
