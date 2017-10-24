package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1008308_ValidateButtonColorTISPod {

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

			//Verify the TIS POD
			mblock.actDashboard.vrfyTISPOD();
			
			//Verify the TIS button clr
			mblock.actDashboard.vrfyClrOfTIsBtn();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
