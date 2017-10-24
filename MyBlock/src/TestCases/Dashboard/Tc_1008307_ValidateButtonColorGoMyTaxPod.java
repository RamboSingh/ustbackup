package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;
import utility.Constant_Class;

public class Tc_1008307_ValidateButtonColorGoMyTaxPod {

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

			// Verify the Go to My Tax POD
			mblock.actDashboard.vrfyGoMyTaxPOD();

			// Verify the Go to My Tax button clr
			mblock.actDashboard.vrfyClrOfGoMyTaxBtn();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
