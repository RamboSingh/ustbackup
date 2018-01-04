package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_971468_ValidateAvlbtyTISForReturningDgtlUserUnderProductservices {

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

			// Verify taxid shield is not displayed
			mblock.actGnrlNvgtn.vrfyTaxIdShldNotDsplGlobalNav();

			// close the browser
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
