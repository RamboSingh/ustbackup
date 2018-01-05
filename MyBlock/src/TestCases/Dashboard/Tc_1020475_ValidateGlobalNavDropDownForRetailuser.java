package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_1020475_ValidateGlobalNavDropDownForRetailuser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Step:1 Navigate to the browser
			mblock.NavigateToURL();

			// step:2 Run login action
			mblock.actLogin.login();

			// verify My document link under My taxes
			mblock.actGnrlNvgtn.vrfyMydocFrmGlobalNav();

			// verify Tax history link under My taxes
			mblock.actGnrlNvgtn.vrfyTaxHstryFrmGlobalNav();

			// verify Tax estimator link under calculator and tools
			mblock.actGnrlNvgtn.vrfyTaxEstmrLnkFrmGlobalNa();

			// verify W4 calculator link under calculator and tools
			mblock.actGnrlNvgtn.vrfyW4CaltrFrmGlobalNav();

			// verify Tax orgn link under calculator and tools
			mblock.actGnrlNvgtn.vrfyTaxOrgnzrFrmGlobalNav();

			// verify Make an appointment link under Product and service
			mblock.actGnrlNvgtn.vrfyMakeAppnmtFrmGlobalNav();

			// verify Filling online link under Product and service
			mblock.actGnrlNvgtn.vrfyFillingOnlneFrmGlobalNav();

			// verify Manage account link under profile
			mblock.actGnrlNvgtn.VrfyMngeAccntFrmGlobalNav();

			// verify Help link under profile
			mblock.actGnrlNvgtn.vrfyHelpFrmGlobalNav();

			// verify signout link under profile
			mblock.actGnrlNvgtn.vrfySignOutFrmGlobalNav();

			// Step:5
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
