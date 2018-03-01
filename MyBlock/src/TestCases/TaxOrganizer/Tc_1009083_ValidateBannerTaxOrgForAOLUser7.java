package TestCases.TaxOrganizer;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1009083_ValidateBannerTaxOrgForAOLUser7 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// create new user
			mblock.actLogin.login();

			// click Tax organizer under My tax life in global navigation
			mblock.actGnrlNvgtn.clkTaxOrgnzrFrmGlobalNav();

			//Verify the history banner
			mblock.actTaxorg.vrfyTaxorgzrBckImg();
			
			// Verify the Tax History header
			mblock.actTaxorg.vrfyTaxheader();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
