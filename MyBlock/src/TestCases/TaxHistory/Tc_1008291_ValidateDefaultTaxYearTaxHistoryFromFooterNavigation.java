package TestCases.TaxHistory;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1008291_ValidateDefaultTaxYearTaxHistoryFromFooterNavigation {

	public static void main(String[] args) {

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with any user
			mblock.actLogin.login();
			
			// click Tax history under My tax life in footer
			mblock.actGnrlNvgtn.clkTaxHstryFrmFooter();

			// Verify the Tax History header
			mblock.actTaxhstry.vrfyTaxheader();

			// Verify the tax history year with 2016 as default
			mblock.actTaxhstry.vrfyDfltTaxhstryYr2016();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
