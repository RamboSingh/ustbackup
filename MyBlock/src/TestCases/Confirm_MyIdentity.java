package TestCases;

import business_actions.MyblockActions;
import utility.Config;

public class Confirm_MyIdentity {

	
	public static void main(String[] args) {
		
		try {
			
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();
			
			mblock.actCtracnt.crtacnt();
			mblock.actCardIdnty.cnfrmMyIdenty();
			mblock.actLogout.LgoutFrmPrfle();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
