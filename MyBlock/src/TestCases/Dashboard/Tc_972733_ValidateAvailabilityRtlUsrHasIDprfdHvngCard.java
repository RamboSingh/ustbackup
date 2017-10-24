package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_972733_ValidateAvailabilityRtlUsrHasIDprfdHvngCard {

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

			// Verify the Emerland card POD is displayed and position
			mblock.actDashboard.vrfyECpodForRtlUsr();

			// Verify the points in Emerland card POD
			mblock.actDashboard.vrfyTxtInEcPod();

			// Verify the buttons in Emerland card
			mblock.actDashboard.vrfyECpodBtns();

			// Click qstn button and verify the popup
			mblock.actDashboard.clkQstnBtnEcPod();

			// Click the Access my card and verify the popup
			mblock.actDashboard.clkAccsmyCardEcPod();
			
			//Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
