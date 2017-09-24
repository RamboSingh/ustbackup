package TestCases.TIS;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_918123_ValidateTISpodForDigitalUserPurchasedTISfor2015and2016 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with digital user
			mblock.actLogin.login();

			// Verify TIS POD is displayed
			mblock.actGnrlNvgtn.vrfyTISpodDsplyd();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
