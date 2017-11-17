package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_999763_ValidateDefaultTaxYearMyDocuments {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with retail user
			mblock.actLogin.login();

			// Click My document from global navigation
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();
			
			//Verify the my document header
			mblock.actMydoc.vrfyMydocheader();

			// Verify the default Drop down tax year
			mblock.actMydoc.vrfyDfltMydocYr2016();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
