package TestCases;

import business_actions.MyblockActions;
import utility.Config;

public class Upload_Document_Newuser {

	public static void main(String[] args) {
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			
			for(int i=0; i<15;i++){
			// Create new account
			mblock.actLogin.login();
			mblock.actLogout.LgoutFrmPrfle();
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
