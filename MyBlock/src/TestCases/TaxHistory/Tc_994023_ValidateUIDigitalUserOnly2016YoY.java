package TestCases.TaxHistory;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_994023_ValidateUIDigitalUserOnly2016YoY {

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

			// click Tax history under My tax life in global navigation
			mblock.actGnrlNvgtn.clkTaxHstryFrmGlobalNav();

			// Verify the Fedaral tax summary coloumn
			mblock.actTaxhstry.vrfyYoyNotDsply();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
