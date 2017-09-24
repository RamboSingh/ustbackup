package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_939368_IDPSucessMessageforRetailUserthroughDashboard {
	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			

			// Browser navigation
			mblock.NavigateToURL();
			mblock.actCtracnt.crtacnt();
			mblock.actCardIdnty.clrSsdob();
			mblock.actCardIdnty.clkCnfrmIdbtn();
			mblock.actCardIdnty.cnfrmMyIdenty();
			
			
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
