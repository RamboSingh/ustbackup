package TestCases.Dashboard;

import business_actions.MyblockActions;
import pageObject.ObjDashboard;
import utility.Config;

public class Tc_972745_ValidateColorGlobalNavigationBackground {

	public static void main(String[] args) {

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with retail user
			mblock.actLogin.login();

			// Verify global navigation backround colour
			mblock.actGnrlNvgtn.vrfyGlobalNavbckrndclr();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
