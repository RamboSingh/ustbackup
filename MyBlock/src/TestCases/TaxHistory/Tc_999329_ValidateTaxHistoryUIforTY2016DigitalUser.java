package TestCases.TaxHistory;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_999329_ValidateTaxHistoryUIforTY2016DigitalUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation digital url
			mblock.NavigateToDigitalUsrURL();

			// Create new digital user
			mblock.actCtracnt.crtDigitalUsrAcnt();
			
			//Click my block button in TCX page
			mblock.actGnrlNvgtn.clkMyblockbtnTCX();

			// click Tax history under My tax life
			mblock.actGnrlNvgtn.clkTaxHstryFrmGlobalNav();

			// Verify the tax history year with 2016 as default
			mblock.actTaxhstry.vrfyDfltTaxhstryYr2016();

			// Validate the tax history header text for new user
			mblock.actTaxhstry.vrfyTaxhdrfrNewuser();

			// verify the confirm my identity button is displayed
			mblock.actCardIdnty.vrfyCnfrmIdbtn();

			// click confirm my identity button
			mblock.actCardIdnty.clkCnfrmIdbtn();

			// Verify the confirm identity pop up in Tax histry
			mblock.actCardIdnty.vrfyCnfrmIdPopupinTaxhstry();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
