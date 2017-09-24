package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_968121_ValidateAvailabilityMyTaxProForKnownUser {

	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Browser navigation
			mblock.NavigateToURL();
			
			// login with known user who has tax pro option
			mblock.actLogin.login();
			
			//Verify and click My tax pro 
			mblock.actGnrlNvgtn.clkMyTaxProFrmGlobalNav();
			
			//close the browser
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
