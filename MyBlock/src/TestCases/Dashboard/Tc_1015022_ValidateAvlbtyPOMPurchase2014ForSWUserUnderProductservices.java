package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1015022_ValidateAvlbtyPOMPurchase2014ForSWUserUnderProductservices {

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
