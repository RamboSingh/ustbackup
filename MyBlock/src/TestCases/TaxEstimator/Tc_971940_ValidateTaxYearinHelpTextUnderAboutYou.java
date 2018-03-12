package TestCases.TaxEstimator;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_971940_ValidateTaxYearinHelpTextUnderAboutYou {

	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			//login with retail user
			mblock.actLogin.login();
			
			//Click Tax Estimator under Calculators and Taxes 
			mblock.actTaxEstimator.clkTaxEstmrLnkFrmStusBar();
			
			//Validate help icon near About you tab heading text
			mblock.actTaxEstimator.vrfyHelpTextforTY2016();
			
			//close the browser
			mblock.Finalize();
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
