package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1008309_ValidateButtonColorW4PlnrPod {

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

			//Verify the W4 planner POD
			mblock.actDashboard.vrfyw4PlnrPOD();
			
			//Verify the W4 planner button clr
			mblock.actDashboard.vrfyClrOfW4plnrBtn();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
