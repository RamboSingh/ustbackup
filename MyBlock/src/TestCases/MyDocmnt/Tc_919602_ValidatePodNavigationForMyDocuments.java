package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_919602_ValidatePodNavigationForMyDocuments {

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

			// Verify the Tax Estimator POD
			mblock.actDashboard.vrfyMydocPOD();

			// click view my document button
			mblock.actDashboard.clkMydocFrmPOD();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
