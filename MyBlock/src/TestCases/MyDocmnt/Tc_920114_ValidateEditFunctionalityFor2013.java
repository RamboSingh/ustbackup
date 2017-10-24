package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_920114_ValidateEditFunctionalityFor2013 {

	public static void main(String[] args) {
	
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with retail user
			mblock.actLogin.login();

			// Click My document from global navigation
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();
			
			//Choose tax year from dropdown
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2013");
			
			//Click More button in uploaded document
			mblock.actMydoc.clkMoreBtn();
			
			//Click Edit option
			mblock.actMydoc.clkEditOptn();
			
			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
