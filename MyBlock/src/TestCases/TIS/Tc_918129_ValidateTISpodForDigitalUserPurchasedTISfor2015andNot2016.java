package TestCases.TIS;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_918129_ValidateTISpodForDigitalUserPurchasedTISfor2015andNot2016 {

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

			// Verify TIS POD is not displayed
			mblock.actGnrlNvgtn.vrfyTISpodNotDsplyd();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
