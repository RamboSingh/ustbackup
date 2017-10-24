package TestCases.Profile;

import business_actions.MyblockActions;
import utility.Config;

public class TC_742654_ValidateErrorMessageinHomeAddressField {
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
			mblock.actProfile.PreFirstName();
			mblock.actProfile.PreLastName();
			mblock.actProfile.NegHomeAddress();
					
			mblock.Finalize(true);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
