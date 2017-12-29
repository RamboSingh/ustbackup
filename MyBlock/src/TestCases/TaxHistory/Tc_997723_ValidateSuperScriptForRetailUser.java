package TestCases.TaxHistory;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_997723_ValidateSuperScriptForRetailUser {

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

			// Verify the super script($) of the tax return
			mblock.actTaxhstry.vrfySuperScript();

			// Choose Tax History year as 2015 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2015");

			// Verify the super script($) of the tax return
			mblock.actTaxhstry.vrfySuperScript();

			// Choose Tax History year as 2015 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2014");

			// Verify the super script($) of the tax return
			mblock.actTaxhstry.vrfySuperScript();

			// Choose Tax History year as 2015 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2013");

			// Verify the super script($) of the tax return
			mblock.actTaxhstry.vrfySuperScript();

			// Choose Tax History year as 2015 from dropdown
			mblock.actTaxhstry.clkTaxhstryYrFrmDrpdwn("2012");

			// Verify the super script($) of the tax return
			mblock.actTaxhstry.vrfySuperScript();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
