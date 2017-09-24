package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_968122_ValidateAvailabilityProfileUnderMyAccountForRetailUser {

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

			// Verify the manage account under my account in Global
			mblock.actGnrlNvgtn.VrfyMngeAccntFrmGlobalNav();

			// Verify the profile under my account in Global
			mblock.actGnrlNvgtn.vrfyProfileFrmGlobalNav();

			// Verify the help under my account in Global
			mblock.actGnrlNvgtn.vrfyHelpFrmGlobalNav();

			// Verify the sigout under my account in Global
			mblock.actGnrlNvgtn.vrfySignOutFrmGlobalNav();

			// Verify the profile under my account in footer
			mblock.actGnrlNvgtn.vrfyProfileFrmfooter();

			// click the profile under my account form global navigation
			mblock.actGnrlNvgtn.clkProfileFrmGlobalNav();

			// close the browser
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
