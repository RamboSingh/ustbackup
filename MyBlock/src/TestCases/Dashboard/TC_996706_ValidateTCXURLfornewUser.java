package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_996706_ValidateTCXURLfornewUser {

	public static void main(String[] args) {
		
		try {
			
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Step:1 Navigate to the browser
			mblock.NavigateToURL();

			// step:2 Run login action
			mblock.actCtracnt.crtacnt();
			mblock.actDashboard.DashBoard_PrepareTaxesMyself();
			mblock.actDashboard.TCX_CYValidation();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
