package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1059040_ValidateVerbiageContentForDigitalUser {

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

			// Click My document from global navigation
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();

			// Verify the veribage content text
			mblock.actMydoc.vrfyVerbiageTxt();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
