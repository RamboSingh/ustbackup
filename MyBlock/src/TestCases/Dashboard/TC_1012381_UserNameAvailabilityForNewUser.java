package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_1012381_UserNameAvailabilityForNewUser {
	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Browser navigation
			mblock.NavigateToURL();
			mblock.actCtracnt.crtacnt();
			// Create new account
			mblock.actDashboard.userNameDisplayed();
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
