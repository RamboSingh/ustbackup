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
			mblock.actProfile.profile_Click();
		
			mblock.actProfile.profile_Edit();
			mblock.actProfile.PreFirstName();
			mblock.actProfile.PreLastName();
			mblock.actProfile.NegHomeAddress();
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
