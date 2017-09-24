package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_968120_ValidateTopLevelNavigationUIforNewUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Browser navigation
			mblock.NavigateToURL();
			
			// Create new account
			mblock.actCtracnt.crtacnt();
			
			//Verify Logo,My taxes,Calculator&tools,Product&servie and MY account is present
			mblock.actGnrlNvgtn.vrfyMyblockLogo();
			mblock.actGnrlNvgtn.VrfyMyTaxFrmGlobalNav();
			mblock.actGnrlNvgtn.VrfyCalcToolFrmGlobalNav();
			mblock.actGnrlNvgtn.VrfyProductServiceFrmGlobalNav();
			mblock.actGnrlNvgtn.VrfyMyAccountFrmGlobalNav();
			
			//close the browser
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
