package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1059033_ValidateVerbiageContentForProspectUser {

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

			// Click My document from global navigation
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();
			
			//Verify the veribage content text
			mblock.actMydoc.vrfyVerbiageTxt();
			
			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
