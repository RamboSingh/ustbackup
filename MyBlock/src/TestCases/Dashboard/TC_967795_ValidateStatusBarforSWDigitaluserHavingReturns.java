package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_967795_ValidateStatusBarforSWDigitaluserHavingReturns {
	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Browser navigation
			mblock.NavigateToURL();
			mblock.actLogin.login();
			mblock.actDashboard.statusBarVisible();
			mblock.actDashboard.statusBarIconsVisible();
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}