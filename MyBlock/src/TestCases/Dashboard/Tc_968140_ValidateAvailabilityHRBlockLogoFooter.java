package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_968140_ValidateAvailabilityHRBlockLogoFooter {

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
			
			//Verify the H&R block logo in footer
			mblock.actGnrlNvgtn.vrfyHRblockLogofooter();

			
			// close the browser
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
