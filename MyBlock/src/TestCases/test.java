package TestCases;

import business_actions.MyblockActions;
import utility.Config;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			// Configuration settings
						Config objConfig = new Config();
						MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

						// Browser navigation
						mblock.NavigateToURL();

						// login with retail user
						mblock.actLogin.login();

						//Verify the Tax orgn POD
						mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();
						mblock.actMydoc.clkMydocYrUpldDoc("2016");
						mblock.actMydoc.clkEditBtnTrnsfrFile("2012");
						
						
						// Close the browser
						mblock.Finalize();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
