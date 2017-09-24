package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_939370_IDPSucessMessageforRetailUsersFromTH {
	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Browser navigation
			mblock.NavigateToURL();
			mblock.actCtracnt.crtacnt();
			mblock.actDashboard.TH_Lauch();
			mblock.actCardIdnty.clrSsdob();
			mblock.actCardIdnty.clkCnfrmIdbtn();
			mblock.actCardIdnty.THcnfrmMyIdenty();
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
