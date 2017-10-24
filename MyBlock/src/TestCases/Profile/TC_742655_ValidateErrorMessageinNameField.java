package TestCases.Profile;

import business_actions.MyblockActions;
import utility.Config;

public class TC_742655_ValidateErrorMessageinNameField {
	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Browser navigation
			mblock.NavigateToURL();
			mblock.actLogin.login();
		
			mblock.actProfile.profile_Launch();
			mblock.actGnrlNvgtn.clkProfileFrmGlobalNav();
		
			mblock.actProfile.profile_Edit();
			mblock.actProfile.NegFirstName();
			
			mblock.actProfile.NegLastName();
			
			
			mblock.Finalize();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
