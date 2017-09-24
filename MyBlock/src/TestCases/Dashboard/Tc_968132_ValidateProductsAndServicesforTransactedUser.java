package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_968132_ValidateProductsAndServicesforTransactedUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with digital transacted user
			mblock.actLogin.login();
			
			//verify make appointment link
			mblock.actGnrlNvgtn.vrfyMakeAppnmtFrmGlobalNav();
			
			//verify Filling online link
			mblock.actGnrlNvgtn.vrfyFillingOnlneFrmGlobalNav();
			
			//verify emerland card link
			mblock.actGnrlNvgtn.vrfyEmrldCardFrmGlobalNav();
			
			//click Filling online link
			mblock.actGnrlNvgtn.clkFillingOnlneFrmGlobalNav();
			
			//Tcx to dashboar
			mblock.actGnrlNvgtn.clkMyblockbtnTCX();
			
			//click make appointment link 
			mblock.actGnrlNvgtn.clkMakeAppnmtFrmGlobalNav();
			
			//click emerland card link 
			mblock.actGnrlNvgtn.clkEmrldCardFrmGlobalNav();
			
			
			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
