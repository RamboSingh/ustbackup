package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class TC_940239_MoveDocmntFrmTY2015ToTY2014DigitalUsr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with digital user
			mblock.actLogin.login();

			// Click my document link from Global nav
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();
			
			//Verify default year as 2016
			mblock.actMydoc.vrfyDfltMydocYr2016();
			
			//Upload the documnet for the mentioned year
			mblock.actMydoc.clkMydocYrUpldDoc("2015");
			
			//Transfer the file which upload in previous step to mentioned below year
			mblock.actMydoc.clkEditBtnTrnsfrFile("2014");

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
