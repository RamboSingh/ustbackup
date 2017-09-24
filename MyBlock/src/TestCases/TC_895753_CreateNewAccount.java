package TestCases;

import business_actions.MyblockActions;
import utility.Config;


public class TC_895753_CreateNewAccount {

	public static void main(String[] args) throws Exception {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			

			// Browser navigation
			mblock.NavigateToURL();

			// Create new account
			mblock.actCtracnt.crtacnt();
			
			mblock.actLogout.LgoutFrmPrfle();

			mblock.Finalize(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
