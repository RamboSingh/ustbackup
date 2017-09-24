package TestCases.TaxHistory;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_997701_ValidateUITaxReturnDataInTaxHistoryNewUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// Create new user
			mblock.actCtracnt.crtacnt();
			
			//click Tax history under My tax life
			mblock.actGnrlNvgtn.clkTaxHstryFrmGlobalNav();
			
			//Verify the tax history year with 2016 as default
			mblock.actTaxhstry.vrfyDfltTaxhstryYr2016();
			
			//Choose Tax History year as 2015 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2015");
			
			//Validate the tax history header text for new user
			mblock.actTaxhstry.vrfyTaxhdrfrNewuser();
			
			//verify the confirm my identity button is displayed
			mblock.actCardIdnty.vrfyCnfrmIdbtn();
			
			//Choose Tax History year as 2014 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2014");
			
			//Validate the tax history header text for new user
			mblock.actTaxhstry.vrfyTaxhdrfrNewuser();
			
			//verify the confirm my identity button is displayed
			mblock.actCardIdnty.vrfyCnfrmIdbtn();
			
			//Choose Tax History year as 2013 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2013");
			
			//Validate the tax history header text for new user
			mblock.actTaxhstry.vrfyTaxhdrfrNewuser();
			
			//verify the confirm my identity button is displayed
			mblock.actCardIdnty.vrfyCnfrmIdbtn();
			
			//Choose Tax History year as 2012 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2012");
			
			//Validate the tax history header text for new user
			mblock.actTaxhstry.vrfyTaxhdrfrNewuser();
			
			//verify the confirm my identity button is displayed
			mblock.actCardIdnty.vrfyCnfrmIdbtn();
			
			//Choose Tax History year as 2011 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2011");
			
			//Validate the tax history header text for new user
			mblock.actTaxhstry.vrfyTaxhdrfrNewuser();
			
			//verify the confirm my identity button is displayed
			mblock.actCardIdnty.vrfyCnfrmIdbtn();
			
			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
