package TestCases.W4Calculator;

import business_actions.MyblockActions;
import utility.Config;

public class TC_924087_ValidateW4CalcForRetailUserFromPOD {
	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Browser navigation
			mblock.NavigateToURL();
			mblock.actLogin.login();
			// Create new account

			mblock.actW4Calc.Calculator_Click();
			mblock.actW4Calc.w4Click_Dashboard();						
			mblock.actW4Calc.payFrequency();
			mblock.actW4Calc.lastCheckDate();
			mblock.actW4Calc.grossPayPerCheck();
			mblock.actW4Calc.nxtBtn();
			mblock.actW4Calc.fedIncomeTaxWithheldPerCheck();
			mblock.actW4Calc.fedWithHoldingToDate();
			mblock.actW4Calc.otherPayrollDed();
			mblock.actW4Calc.nxtBtn();
			mblock.actW4Calc.allowances();
			mblock.actW4Calc.nxtBtn();
			mblock.actW4Calc.filingStatus();
			mblock.actW4Calc.estimatedFedLiability();
			mblock.actW4Calc.nxtBtn();
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
