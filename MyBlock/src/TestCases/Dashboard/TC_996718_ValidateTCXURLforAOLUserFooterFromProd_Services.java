package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_996718_ValidateTCXURLforAOLUserFooterFromProd_Services {

	public static void main(String[] args) {
		
		try {
			
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Step:1 Navigate to the browser
			mblock.NavigateToURL();

			// step:2 Run login action
			mblock.actLogin.login();
			mblock.actDashboard.Footer_GoOnline();
			mblock.actDashboard.TCX_CYValidation();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
