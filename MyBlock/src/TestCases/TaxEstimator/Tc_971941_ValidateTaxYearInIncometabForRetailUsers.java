package TestCases.TaxEstimator;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_971941_ValidateTaxYearInIncometabForRetailUsers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
			
			//Entering basic detail and click next
			mblock.actTaxEstimator.entrAboutDetailandClkNext();
			
			//verify the header text of Income tab
			mblock.actTaxEstimator.vrfyIncomeHeadingText();
			
			//close the browser
			mblock.Finalize();
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
