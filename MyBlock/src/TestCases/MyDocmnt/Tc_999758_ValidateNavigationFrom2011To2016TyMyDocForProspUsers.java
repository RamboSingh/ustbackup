package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_999758_ValidateNavigationFrom2011To2016TyMyDocForProspUsers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// Create new user
			mblock.actCtracnt.crtacnt();
			
			//click Tax history under My tax life
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();
			
			//verify the My document header
			mblock.actMydoc.vrfyMydocheader();
			
			//Verify the My document TY with 2016 as default
			mblock.actMydoc.vrfyDfltMydocYr2016();
			
			//Click TY 2015
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2015");
			
			//Click TY 2014
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2014");

			//Click TY 2013
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2013");

			//Click TY 2012
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2012");

			//Click TY 2011
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2011");
			
			//Click TY 2012
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2012");
			
			//Click TY 2013
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2013");
			
			//Click TY 2014
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2014");
			
			//Click TY 2015
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2015");
			
			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
