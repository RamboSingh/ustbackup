package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_1013806_POM_Navigation_Footer {
	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Browser navigation
			mblock.NavigateToURL();
			mblock.actLogin.login();
			
		
			mblock.actDashboard.POM_LaunchFooter();		
			
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
