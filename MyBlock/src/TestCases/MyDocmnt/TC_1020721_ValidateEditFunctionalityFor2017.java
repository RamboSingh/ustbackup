package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class TC_1020721_ValidateEditFunctionalityFor2017 {

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
			
			//Verify default year as 2016
			mblock.actMydoc.vrfyDfltMydocYr2016();
			
			//Upload the documnet for the mentioned year
			mblock.actMydoc.clkMydocYrUpldDoc("2012");
			
			//click edit option
			mblock.actMydoc.clkEditOptn();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
