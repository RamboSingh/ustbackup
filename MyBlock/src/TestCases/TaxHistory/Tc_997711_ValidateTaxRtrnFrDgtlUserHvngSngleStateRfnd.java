package TestCases.TaxHistory;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_997711_ValidateTaxRtrnFrDgtlUserHvngSngleStateRfnd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login user
			mblock.actLogin.login();

			// click Tax history under My tax life
			mblock.actGnrlNvgtn.clkTaxHstryFrmGlobalNav();

			// Verify the tax history year with 2016 as default
			mblock.actTaxhstry.vrfyDfltTaxhstryYr2016();

			// Verify State name is fully displayed.
			mblock.actTaxhstry.vrfyTaxRtrnStateName();

			// Choose Tax History year as 2015 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2015");

			// Verify State name is fully displayed.
			mblock.actTaxhstry.vrfyTaxRtrnStateName();

			// Choose Tax History year as 2015 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2014");

			// Verify State name is fully displayed.
			mblock.actTaxhstry.vrfyTaxRtrnStateName();

			// Choose Tax History year as 2015 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2013");

			// Verify State name is fully displayed.
			mblock.actTaxhstry.vrfyTaxRtrnStateName();

			// Choose Tax History year as 2015 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2012");

			// Verify State name is fully displayed.
			mblock.actTaxhstry.vrfyTaxRtrnStateName();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
