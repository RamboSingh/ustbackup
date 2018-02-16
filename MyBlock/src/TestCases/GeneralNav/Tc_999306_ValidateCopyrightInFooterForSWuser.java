package TestCases.GeneralNav;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_999306_ValidateCopyrightInFooterForSWuser {

	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with soft user
			mblock.actLogin.login();

			// Validate the copyright dates in footer
			mblock.actGnrlNvgtn.vrfyCopyRightDate2016();

			// Click My Documents under My Taxes
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();

			// Validate the copyright dates in footer
			mblock.actGnrlNvgtn.vrfyCopyRightDate2016();

			// Click Tax History under My Taxes
			mblock.actGnrlNvgtn.clkTaxHstryFrmGlobalNav();

			// Validate the copyright dates in footer
			mblock.actGnrlNvgtn.vrfyCopyRightDate2016();

			// Click Tax Organizer under Calculators & Tools
			mblock.actGnrlNvgtn.clkTaxOrgnzrFrmGlobalNav();

			// Validate the copyright dates in footer
			mblock.actGnrlNvgtn.vrfyCopyRightDate2016();

			// Click Tax Estimator under Calculators & Tools
			mblock.actGnrlNvgtn.clkTaxEstmtrFrmGlobalNav();

			// Validate the copyright dates in footer
			mblock.actGnrlNvgtn.vrfyCopyRightDate2016();

			// Click W4 Calculator under Calculators & Tools
			mblock.actGnrlNvgtn.clkW4CaltrFrmGlobalNav();

			// Validate the copyright dates in footer
			mblock.actGnrlNvgtn.vrfyCopyRightDate2016();

			// Click Schedule Appointment under Products & Services
			mblock.actGnrlNvgtn.clkMakeAppnmtFrmGlobalNav();

			// Validate the copyright dates in footer
			mblock.actGnrlNvgtn.vrfyCopyRightDate2016();

			// Click profile under My account
			mblock.actGnrlNvgtn.clkProfileFrmGlobalNav();

			// Validate the copyright dates in footer
			mblock.actGnrlNvgtn.vrfyCopyRightDate2016();

			// Click Help under My Account
			mblock.actGnrlNvgtn.clkHelpFrmGlobalNav();

			// Validate the copyright dates in footer
			mblock.actGnrlNvgtn.vrfyCopyRightDate2016();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
