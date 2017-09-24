package TestCases.TaxHistory;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_937715_ValidateTaxHistoryUIforTY2014SwUser {

	public static void main(String[] args) {
		
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// Login with software user
			mblock.actLogin.login();
			
			//click Tax history under My tax life
			mblock.actGnrlNvgtn.clkTaxHstryFrmGlobalNav();
			
			//Verify the tax history year with 2016 as default
			mblock.actTaxhstry.vrfyDfltTaxhstryYr2016();
			
			//Choose Tax History year as 2014 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2014");
			
			//Validate the tax history header text for new user
			mblock.actTaxhstry.vrfyTaxhdrfrNewuser();
			
			//verify the confirm my identity button is displayed
			mblock.actCardIdnty.vrfyCnfrmIdbtn();
			
			//click confirm my identity button
			mblock.actCardIdnty.clkCnfrmIdbtn();
			
			//Verify the confirm identity pop up in Tax histry 
			mblock.actCardIdnty.vrfyCnfrmIdPopupinTaxhstry();
			
			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
