package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1059066_ValidateAddDocumentButtonForBOBUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with BOB user
			mblock.actLogin.login();

			// Click My document from global navigation
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();

			// Verify the default Drop down tax year
			mblock.actMydoc.vrfyDfltMydocYr2016();
			
			// Click Add a document button
			mblock.actMydoc.clkAddDocmntBtn();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
