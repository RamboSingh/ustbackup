package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_968131_ValidateMyTaxesForTransactedUser {

	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with retail user
			mblock.actLogin.login();

			// Verify My online tax under My taxes
			mblock.actGnrlNvgtn.vrfyMyOnlneTaxFrmGlobalNav();

			// Verify My document under My taxes
			mblock.actGnrlNvgtn.vrfyMydocFrmGlobalNav();

			// Verify tax history under My taxes
			mblock.actGnrlNvgtn.vrfyTaxHstryFrmGlobalNav();

			// click My online tax
			mblock.actGnrlNvgtn.clkMyOnlneTaxFrmGlobalNav();

			// Click My block button in TCX page to back to dashboard
			mblock.actGnrlNvgtn.clkMyblockbtnTCX();

			// Click My document under my taxes
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();

			// Click Tax history under my taxes
			mblock.actGnrlNvgtn.clkTaxHstryFrmGlobalNav();

			// close the browser
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
