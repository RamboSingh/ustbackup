package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_999985_ValidateFillingStatusIcon {

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

			//verify the filling status
			mblock.actDashboard.vrfyFilingStatus();
			

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
