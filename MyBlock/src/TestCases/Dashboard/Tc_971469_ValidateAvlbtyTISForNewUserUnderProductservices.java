package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_971469_ValidateAvlbtyTISForNewUserUnderProductservices {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// create account
			mblock.actCtracnt.crtacnt();

			// Verify taxid shield is displayed in global nav
			mblock.actGnrlNvgtn.vrfyTaxIdShldFrmGlobalNav();
			
			// Verify taxid shield is displayed in footer
			mblock.actGnrlNvgtn.vrfyTaxIdShldFrmfooter();
			

			// close the browser
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
