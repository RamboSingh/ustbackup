package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_918075_ValidateReturningUserHeroForOnly2014Filed {

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

			// Verify Upload my document button is not displayed
			mblock.actDashboard.vrfyMydocBtnNotDsply();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
