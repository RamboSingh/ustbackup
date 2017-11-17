package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_999748_ValidateDropdownNavigationTo2016MyDocForDigitalUser {

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

			// Click on Uploaded document and verify new window is opened
			mblock.actMydoc.clkUpldDocVrfyNewWndwOpnd();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
