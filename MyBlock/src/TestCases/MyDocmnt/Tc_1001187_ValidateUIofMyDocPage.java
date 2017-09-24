package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1001187_ValidateUIofMyDocPage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with valid user
			mblock.actLogin.login();
			
			//click Tax history under My tax life
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();
			
			//verify the My document header
			mblock.actMydoc.vrfyMydocheader();
			
			//Verify the My document TY with 2016 as default
			mblock.actMydoc.vrfyDfltMydocYr2016();
			
			//Verify the backgorund image (Full of Drawers and one of the drawer with full of files)
			mblock.actMydoc.vrfyMydocBckImg();
			
			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
