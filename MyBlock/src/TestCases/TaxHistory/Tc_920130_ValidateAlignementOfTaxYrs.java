package TestCases.TaxHistory;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_920130_ValidateAlignementOfTaxYrs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with AOL user
			mblock.actLogin.login();

			// click Tax history under My tax life
			mblock.actGnrlNvgtn.clkTaxHstryFrmGlobalNav();

			// Verify the tax history year with 2016 as default
			mblock.actTaxhstry.vrfyDfltTaxhstryYr2016();

			//Verify the TY drop down Tax year is present
			mblock.actTaxhstry.vrfyTaxhstryYrDsply("2017");

			//Verify the TY drop down Tax year is present
			mblock.actTaxhstry.vrfyTaxhstryYrDsply("2016");

			//Verify the TY drop down Tax year is present
			mblock.actTaxhstry.vrfyTaxhstryYrDsply("2015");

			//Verify the TY drop down Tax year is present
			mblock.actTaxhstry.vrfyTaxhstryYrDsply("2014");

			//Verify the TY drop down Tax year is present
			mblock.actTaxhstry.vrfyTaxhstryYrDsply("2013");
			
			//Verify the TY drop down Tax year is present
			mblock.actTaxhstry.vrfyTaxhstryYrDsply("2012");

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
