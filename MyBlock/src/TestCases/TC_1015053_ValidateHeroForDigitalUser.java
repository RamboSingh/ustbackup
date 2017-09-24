package TestCases;

import business_actions.MyblockActions;
import utility.Config;


public class TC_1015053_ValidateHeroForDigitalUser {

	public static void main(String[] args) {
		try {

			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Step:1 Navigate to the browser
			mblock.NavigateToURL();

			// step:2 Run login action
			mblock.actLogin.login();

			// Step:3 verifying welcome text
			mblock.actLogin.VerifyWelcomeText();

			// Step:4 Click Review tax button
			mblock.actLogin.ClickReviewTaxbtn();
			
			//Logout from the application
			mblock.actLogout.LgoutFrmPrfle();
			
		
			// Step:5
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
