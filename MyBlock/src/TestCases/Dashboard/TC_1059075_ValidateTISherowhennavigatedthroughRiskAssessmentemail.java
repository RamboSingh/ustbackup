package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_1059075_ValidateTISherowhennavigatedthroughRiskAssessmentemail {

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

			//News Letter Navigation
			mblock.actDashboard.NavigatetoRiskAssmnt();
			
			//Verify the News Letter TIS content
			mblock.actDashboard.TIS_RiskAssessment();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
