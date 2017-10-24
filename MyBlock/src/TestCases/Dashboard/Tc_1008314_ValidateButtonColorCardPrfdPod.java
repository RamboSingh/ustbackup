package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1008314_ValidateButtonColorCardPrfdPod {

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

			//Verify the EC POD
			mblock.actDashboard.vrfyECpodForRtlUsr();
			
			//Verify the EC POD button clr
			mblock.actDashboard.vrfyClrECPrfdpodBtns();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
