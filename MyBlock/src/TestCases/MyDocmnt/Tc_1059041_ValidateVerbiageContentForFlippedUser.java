package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1059041_ValidateVerbiageContentForFlippedUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// create new user
			mblock.actCtracnt.crtacnt();

			// Click Filling online to change user from retail to digital
			mblock.actGnrlNvgtn.clkFillingOnlneFrmGlobalNav();

			// Click my block button in TCX page
			mblock.actGnrlNvgtn.clkMyblockbtnTCX();

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
