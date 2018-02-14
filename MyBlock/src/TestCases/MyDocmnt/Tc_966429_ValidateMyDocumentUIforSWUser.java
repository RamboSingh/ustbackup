package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_966429_ValidateMyDocumentUIforSWUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// create new user
			mblock.actCtracnt.crtacnt();

			// Click My document from global navigation
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();
			
			//Verify the veribage content text
			mblock.actMydoc.vrfyVerbiageTxt();
			
			//Choose the year
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2016");
			
			//Verify the veribage content text
			mblock.actMydoc.vrfyVerbiageTxt();
			
			//Choose the year
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2015");
			
			//Verify the veribage content text
			mblock.actMydoc.vrfyVerbiageTxt();
			
			//Choose the year
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2014");
			
			//Verify the veribage content text
			mblock.actMydoc.vrfyVerbiageTxt();
			
			//Choose the year
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2013");
			
			//Verify the veribage content text
			mblock.actMydoc.vrfyVerbiageTxt();
			
			//Choose the year
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2012");
			
			//Verify the veribage content text
			mblock.actMydoc.vrfyVerbiageTxt();
			
			//Choose the year
			mblock.actMydoc.clkMydocYrFrmDrpdwn("2011");
			
			//Verify the veribage content text
			mblock.actMydoc.vrfyVerbiageTxt();
			
			
			// Close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
