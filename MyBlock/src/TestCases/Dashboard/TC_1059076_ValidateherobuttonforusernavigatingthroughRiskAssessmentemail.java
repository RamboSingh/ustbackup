package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_1059076_ValidateherobuttonforusernavigatingthroughRiskAssessmentemail {

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
			mblock.RiskAssessment_TIS();
			
			
			//Verify the News Letter TIS content
			mblock.actDashboard.TIS_RiskAssessmentHeroBtn();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
