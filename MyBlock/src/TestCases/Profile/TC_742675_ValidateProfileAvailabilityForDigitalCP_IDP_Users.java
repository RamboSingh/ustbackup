package TestCases.Profile;

import business_actions.MyblockActions;
import utility.Config;

public class TC_742675_ValidateProfileAvailabilityForDigitalCP_IDP_Users {
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
			mblock.actProfile.Home_Street();
			mblock.actProfile.Home_Apt();
			mblock.actProfile.Home_City();
			mblock.actProfile.Home_State();
			mblock.actProfile.Home_Zip();
			mblock.actProfile.chkBox_SameAs();
			mblock.actProfile.phone_Home();
			mblock.actProfile.phone_Mobile();
			mblock.actProfile.phone_Business();
			mblock.actProfile.email_Preferred();
			
			mblock.actProfile.Btn_submit();
			mblock.actProfile.Message_Success();
			
			mblock.Finalize(true);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
