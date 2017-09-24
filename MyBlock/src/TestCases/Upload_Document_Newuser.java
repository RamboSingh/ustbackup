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

			// Create new account
			mblock.actLogin.login();
			
			mblock.actUplddoc.clkMydoclnk();
			
		//	mblock.actUplddoc.upldDocTaxclient();
			mblock.actUplddoc.upldDocument(); // need to mentioned the file name in constat.java file
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
