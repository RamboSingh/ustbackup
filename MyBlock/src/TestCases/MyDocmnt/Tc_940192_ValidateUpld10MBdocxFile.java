package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_940192_ValidateUpld10MBdocxFile {

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

			// Click my document link from Global nav
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();
			
			//Verify default year as 2017
			mblock.actMydoc.vrfyDfltMydocYr2016();
			
			//verifying 10mb file is uploaded
			mblock.actMydoc.clkMydocYrUpld0MB();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
