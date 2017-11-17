package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_999762_ValidateAvailability2010TyInMyDocumentForDigitalUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with digital user
			mblock.actLogin.login();

			// Click My document from global navigation
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();

			// Verify the default Drop down tax year
			mblock.actMydoc.vrfyDfltMydocYr2016();

			// Verify the Tax year 2010 is not present in the dropdown
			mblock.actMydoc.vrfyTYyrNotDsply("2010");

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
