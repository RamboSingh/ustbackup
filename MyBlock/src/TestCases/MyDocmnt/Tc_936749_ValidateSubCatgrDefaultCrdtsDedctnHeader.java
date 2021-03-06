package TestCases.MyDocmnt;

import business_actions.MyblockActions;
import utility.Config;

public class Tc_936749_ValidateSubCatgrDefaultCrdtsDedctnHeader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// Configuration settings
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			// Browser navigation
			mblock.NavigateToURL();

			// login with user
			mblock.actLogin.login();

			// click my document under My tax life
			mblock.actGnrlNvgtn.clkMydocFrmGlobalNav();

			// Verify the tax history year with 2016 as default
			mblock.actMydoc.vrfyDfltMydocYr2016();

			// click category link
			mblock.actMydoc.clkCategoryinMydoc();

			// Click deduction category and verify the sub category
			mblock.actMydoc.clkDeductionCatgry();

			// Click More option in deduction
			mblock.actMydoc.clkMoreDeduction();

			// Verify Default credits header.
			mblock.actMydoc.vrfyDfltCrdtsDedHdr();

			// close the browser
			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
