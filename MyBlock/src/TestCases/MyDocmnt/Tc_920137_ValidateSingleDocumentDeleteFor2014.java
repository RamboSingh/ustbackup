package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_920137_ValidateSingleDocumentDeleteFor2014 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with retail user
			mblock.actCtracnt.crtacnt();

			// Click My document from global navigation
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();

			// Verify the default Drop down tax year
			mblock.actMydoc.vrfyDfltMydocYr2016();

			// Choose tax year from dropdown
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2014");

			// Click More button in uploaded document
			mblock.actMydoc.clkMoreBtn();

			// Click delete option
			mblock.actMydoc.clkDeleteOptn();

			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
