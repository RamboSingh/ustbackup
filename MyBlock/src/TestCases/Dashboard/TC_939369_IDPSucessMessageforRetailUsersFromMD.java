package TestCases.Dashboard;

import business_actions.MyblockActions;
import utility.Config;

public class TC_939369_IDPSucessMessageforRetailUsersFromMD {
	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();
			mblock.actCtracnt.crtacnt();
			mblock.actCardIdnty.clrSsdob();
			mblock.actUplddoc.clkMydoclnk();
			mblock.actDashboard.MD_AddDoc();
			mblock.actDashboard.Click_MD_IDP();
			mblock.actCardIdnty.MDcnfrmMyIdenty();
			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
