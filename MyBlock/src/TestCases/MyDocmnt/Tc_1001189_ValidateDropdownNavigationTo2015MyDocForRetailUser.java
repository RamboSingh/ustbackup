package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1001189_ValidateDropdownNavigationTo2015MyDocForRetailUser {

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

			//Click My document from global navigation
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();
			
			//Verify the default Drop down tax year
			mblock.actMydoc.vrfyDfltMydocYr2016();
			
			//select 2015 from the dropdown
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2015");
			
			//Click on Uploaded document and verify new window is opened
			mblock.actMydoc.clkUpldDocVrfyNewWndwOpnd();
			
			
			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
