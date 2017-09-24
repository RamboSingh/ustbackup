package TestCases.Profile;

import business_actions.MyblockActions;
import utility.Config;

public class TC_708613_ValidateAvailabilityOfProfileforProspectiveUsers {
	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Browser navigation
			mblock.NavigateToURL();
			mblock.actCtracnt.crtacnt();
			mblock.actProfile.profile_Launch();
			mblock.actProfile.prospective_profile_Click();
		
			
			
			mblock.Finalize(true);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
