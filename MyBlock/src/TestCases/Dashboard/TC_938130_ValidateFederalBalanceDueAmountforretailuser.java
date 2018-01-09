package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_938130_ValidateFederalBalanceDueAmountforretailuser {

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

			//News Letter Navigation
			mblock.actDashboard.Federal_RefundCheck();
			
			
			

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
