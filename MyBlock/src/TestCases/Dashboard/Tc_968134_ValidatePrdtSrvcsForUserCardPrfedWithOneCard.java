package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_968134_ValidatePrdtSrvcsForUserCardPrfedWithOneCard {

	public static void main(String[] args) {

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with digital transacted user
			mblock.actLogin.login();

			// validate product and service
			mblock.actDashboard.vldPrdctSrvce();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
