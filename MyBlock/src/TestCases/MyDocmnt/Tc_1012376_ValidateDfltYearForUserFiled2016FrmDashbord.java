package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1012376_ValidateDfltYearForUserFiled2016FrmDashbord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// Login with user
			mblock.actLogin.login();

			// Click my document link from POD
			mblock.actDashboard.clkMydocFrmDashbord();

			// Verify default year as 2017
			mblock.actMydoc.vrfyDfltMydocYr2016();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
