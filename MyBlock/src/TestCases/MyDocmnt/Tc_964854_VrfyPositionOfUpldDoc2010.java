package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_964854_VrfyPositionOfUpldDoc2010 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with retail user
			mblock.actCtracnt.crtacnt();

			// Click My document from global navigation
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();

			// Verify the default Drop down tax year
			mblock.actMydoc.vrfyDfltMydocYr2016();

			// verify the uploaded document is on top of the list
			mblock.actMydoc.vrfyUpldDocPstn("2010");

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
