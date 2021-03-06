package TestCases.TaxHistory;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_993514_ValidateAvailability2010InTaxHistoryForRetailUser {

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

			// click Tax history under My tax life
			mblock.actGnrlNvgtn.clkTaxHstryFrmGlobalNav();

			// Verify the tax history year with 2016 as default
			mblock.actTaxhstry.vrfyDfltTaxhstryYr2016();

			// Verify tax year 2010 is not present in Dropdown
			mblock.actTaxhstry.vrfyTaxhstryYrNotDsply("2011");

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
