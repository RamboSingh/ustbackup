package TestCases.TaxEstimator;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_971942_ValidateTaxYearChangesHelpTextIncomeTab {

	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with retail user
			mblock.actLogin.login();

			// Click Tax Estimator under Calculators and Taxes
			mblock.actTaxEstimator.clkTaxEstmrLnkFrmStusBar();

			// Entering basic detail and click next
			mblock.actTaxEstimator.entrAboutDetailandClkNext();
			
			//Verify the help text under income tab for tax year 2016
			mblock.actTaxEstimator.vrfyHelpTextIncometabforTY2016();
			
			//close the browser
			mblock.Finalize();
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
