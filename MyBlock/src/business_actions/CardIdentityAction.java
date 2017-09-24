package business_actions;

import pageObject.ObjDashboard;
import pageObject.objPOM;
import utility.Config;

public class CardIdentityAction {

	MyblockActions mblock;

	public CardIdentityAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	// verify the confirm my identity button is displayed
	public void vrfyCnfrmIdbtn() throws Exception {
		try {
			boolean cnfm_btn = mblock.ElementExists(ObjDashboard.btn_cnfmid);
			if (cnfm_btn) {
				mblock.ValidateTest(true, true, "Confirm my identity button is displayed");
			}
			else {
				mblock.ValidateTest(false, true, "Confirm my identity button is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void clkCnfrmIdbtn() throws Exception {
		try {
			boolean cnfm_btn = mblock.ElementExists(ObjDashboard.btn_cnfmid);
			if (cnfm_btn) {
				mblock.Element(ObjDashboard.btn_cnfmid).click();

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//Verify the confirm identity pop up in Tax histry
	public void vrfyCnfrmIdPopupinTaxhstry() throws Exception {
		try {
			boolean txt_fld = mblock.ElementExists(ObjDashboard.THiframe_idp1);
			if (txt_fld) {
				mblock.ActivateFrame(ObjDashboard.THiframe_idp1);
				boolean val = mblock.ElementExists(ObjDashboard.txt_frstname);
				if (val) {
					mblock.ValidateTest(true, true, "Confirm identity pop up is displayed");
					
				} else {
					mblock.ValidateTest(false, true, "Confirm identity pop up is not displayed");
				}

			}
			else{
				mblock.ValidateTest(false, true, "Confirm identity pop up is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void entrBasicInfo() throws Exception {
		try {

			String ssn1 = ExcelAction.crtnls[6].substring(0, 3);
			String ssn2 = ExcelAction.crtnls[6].substring(3, 5);
			String ssn3 = ExcelAction.crtnls[6].substring(5, 9);

			boolean txt_fld = mblock.ElementExists(ObjDashboard.iframe_idp);

			if (txt_fld) {
				mblock.ActivateFrame(ObjDashboard.iframe_idp);
				mblock.ElementExists(ObjDashboard.txt_frstname);
				mblock.Element(ObjDashboard.txt_frstname).sendKeys(ExcelAction.crtnls[4]);
				mblock.Element(ObjDashboard.txt_lstname).sendKeys(ExcelAction.crtnls[5]);
				mblock.Element(ObjDashboard.txt_ssn1).sendKeys(ssn1);
				mblock.Element(ObjDashboard.txt_ssn2).sendKeys(ssn2);
				mblock.Element(ObjDashboard.txt_ssn3).sendKeys(ssn3);
				mblock.Element(ObjDashboard.txt_dob).sendKeys(ExcelAction.crtnls[7]);
				mblock.Element(ObjDashboard.btn_next).click();

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void choosePrsnInfo1() throws Exception {
		try {
			mblock.ElementExists(ObjDashboard.iframe_idp1);
			String qstn = mblock.Element(ObjDashboard.qstn_col1).getText();
			int cnt = qstn.length();
			String qstn1 = qstn.substring(3, cnt);

			for (int j = 0; j < ExcelAction.crtnls.length; j++) {
				if (qstn1.equalsIgnoreCase(ExcelAction.crtnls[j])) {
					mblock.Element(ObjDashboard.answr_col1, Integer.parseInt(ExcelAction.crtnls[j + 1]) - 1).click();
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void choosePrsnInfo2() throws Exception {
		try {
			String qstn = mblock.Element(ObjDashboard.qstn_col2).getText();
			int cnt = qstn.length();
			String qstn1 = qstn.substring(3, cnt);

			for (int j = 0; j < ExcelAction.crtnls.length; j++) {
				if (qstn1.equalsIgnoreCase(ExcelAction.crtnls[j])) {
					mblock.Element(ObjDashboard.answr_col2, Integer.parseInt(ExcelAction.crtnls[j + 1]) - 1).click();
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void choosePrsnInfo3() throws Exception {
		try {

			String qstn = mblock.Element(ObjDashboard.qstn_col3).getText();
			int cnt = qstn.length();
			String qstn1 = qstn.substring(3, cnt);

			for (int j = 0; j < ExcelAction.crtnls.length; j++) {
				if (qstn1.equalsIgnoreCase(ExcelAction.crtnls[j])) {
					mblock.Element(ObjDashboard.answr_col3, Integer.parseInt(ExcelAction.crtnls[j + 1]) - 1).click();
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void choosePrsnInfo4() throws Exception {
		try {

			String qstn = mblock.Element(ObjDashboard.qstn_col4).getText();
			int cnt = qstn.length();
			String qstn1 = qstn.substring(3, cnt);

			for (int j = 0; j < ExcelAction.crtnls.length; j++) {
				if (qstn1.equalsIgnoreCase(ExcelAction.crtnls[j])) {
					mblock.Element(ObjDashboard.answr_col4, Integer.parseInt(ExcelAction.crtnls[j + 1]) - 1).click();
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void clrSsdob() throws Exception {
		try {
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());

			mblock.NavigateToSSDURL();
			mblock.ElementExists(ObjDashboard.txt_ssod);
			for (int i = 0; i < 1; i++) {
				mblock.Element(ObjDashboard.txt_ssod).sendKeys(ExcelAction.crtnls[39]);
				mblock.Element(ObjDashboard.drpdwn_reset).ufxSelectFromDropdown("Clear XML");
				mblock.Element(ObjDashboard.btn_reset).click();

			}

			mblock.Finalize();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void SuccessIDPHeaderMessage() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(ObjDashboard.idp_Success);
			if (element1) {
				String successHeader = mblock.Element(ObjDashboard.idp_Success).getText();
				if (successHeader.equalsIgnoreCase("Success!")) {
					mblock.Log("Sucess Header is present");
					mblock.ValidateTest(true, true, "Sucess header in IDP screen is present");
				}
			} else {
				mblock.Log("Sucess Header is not present");
				mblock.ValidateTest(false, true, "Sucess header in IDP screen is not present");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void SuccessIDPBodyMessage() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(ObjDashboard.idp_Success_Content);
			if (element1) {
				String successHeader = mblock.Element(ObjDashboard.idp_Success_Content).getText();
				System.out.println(successHeader);
				String content = "We have verified your identity and you now have access to your personal H&R Block tax information.\n"

						+ "You can access your prior year tax returns in the 'Tax History' section of the site.\n"
						+ "Your Tax Professional's information is also available throughout the site.";
				if (successHeader.equalsIgnoreCase("Success!")) {
					mblock.Log("Sucess Header is present");
					mblock.ValidateTest(true, true, "Sucess header in IDP screen is present");
				}
			} else {
				mblock.Log("Sucess Header is not present");
				mblock.ValidateTest(false, true, "Sucess header in IDP screen is not present");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void MD_entrBasicInfo() throws Exception {
		try {

			String ssn1 = ExcelAction.crtnls[6].substring(0, 3);
			String ssn2 = ExcelAction.crtnls[6].substring(3, 5);
			String ssn3 = ExcelAction.crtnls[6].substring(5, 9);

			boolean txt_fld = mblock.ElementExists(ObjDashboard.MDiframe_idp1);

			if (txt_fld) {
				mblock.ActivateFrame(ObjDashboard.MDiframe_idp1);
				mblock.ElementExists(ObjDashboard.txt_frstname);
				mblock.Element(ObjDashboard.txt_frstname).sendKeys(ExcelAction.crtnls[4]);
				mblock.Element(ObjDashboard.txt_lstname).sendKeys(ExcelAction.crtnls[5]);
				mblock.Element(ObjDashboard.txt_ssn1).sendKeys(ssn1);
				mblock.Element(ObjDashboard.txt_ssn2).sendKeys(ssn2);
				mblock.Element(ObjDashboard.txt_ssn3).sendKeys(ssn3);
				mblock.Element(ObjDashboard.txt_dob).sendKeys(ExcelAction.crtnls[7]);
				mblock.Element(ObjDashboard.btn_next).click();

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void MDchoosePrsnInfo1() throws Exception {
		try {
			mblock.ElementExists(ObjDashboard.MDiframe_idp2);
			String qstn = mblock.Element(ObjDashboard.qstn_col1).getText();
			int cnt = qstn.length();
			String qstn1 = qstn.substring(3, cnt);

			for (int j = 0; j < ExcelAction.crtnls.length; j++) {
				if (qstn1.equalsIgnoreCase(ExcelAction.crtnls[j])) {
					mblock.Element(ObjDashboard.answr_col1, Integer.parseInt(ExcelAction.crtnls[j + 1]) - 1).click();
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void MDcnfrmMyIdenty() throws Exception {
		try {

			MD_entrBasicInfo();
			MDchoosePrsnInfo1();
			choosePrsnInfo2();
			choosePrsnInfo3();
			choosePrsnInfo4();
			mblock.Element(ObjDashboard.btn_submit).click();
			SuccessIDPHeaderMessage();
			SuccessIDPBodyMessage();
			mblock.ElementExists(ObjDashboard.btn_ok);
			mblock.ValidateTest(true, "Identity Validation is success");
			mblock.Element(ObjDashboard.btn_ok).click();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void TH_entrBasicInfo() throws Exception {
		try {

			String ssn1 = ExcelAction.crtnls[6].substring(0, 3);
			String ssn2 = ExcelAction.crtnls[6].substring(3, 5);
			String ssn3 = ExcelAction.crtnls[6].substring(5, 9);

			boolean txt_fld = mblock.ElementExists(ObjDashboard.THiframe_idp1);

			if (txt_fld) {
				mblock.ActivateFrame(ObjDashboard.THiframe_idp1);
				mblock.ElementExists(ObjDashboard.txt_frstname);
				mblock.Element(ObjDashboard.txt_frstname).sendKeys(ExcelAction.crtnls[4]);
				mblock.Element(ObjDashboard.txt_lstname).sendKeys(ExcelAction.crtnls[5]);
				mblock.Element(ObjDashboard.txt_ssn1).sendKeys(ssn1);
				mblock.Element(ObjDashboard.txt_ssn2).sendKeys(ssn2);
				mblock.Element(ObjDashboard.txt_ssn3).sendKeys(ssn3);
				mblock.Element(ObjDashboard.txt_dob).sendKeys(ExcelAction.crtnls[7]);
				mblock.Element(ObjDashboard.btn_next).click();

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void THchoosePrsnInfo1() throws Exception {
		try {
			mblock.ElementExists(ObjDashboard.THiframe_idp2);
			String qstn = mblock.Element(ObjDashboard.qstn_col1).getText();
			int cnt = qstn.length();
			String qstn1 = qstn.substring(3, cnt);

			for (int j = 0; j < ExcelAction.crtnls.length; j++) {
				if (qstn1.equalsIgnoreCase(ExcelAction.crtnls[j])) {
					mblock.Element(ObjDashboard.answr_col1, Integer.parseInt(ExcelAction.crtnls[j + 1]) - 1).click();
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void THcnfrmMyIdenty() throws Exception {
		try {

			MD_entrBasicInfo();
			MDchoosePrsnInfo1();
			choosePrsnInfo2();
			choosePrsnInfo3();
			choosePrsnInfo4();
			mblock.Element(ObjDashboard.btn_submit).click();
			SuccessIDPHeaderMessage();
			SuccessIDPBodyMessage();
			mblock.ElementExists(ObjDashboard.btn_ok);
			mblock.ValidateTest(true, "Identity Validation is success");
			mblock.Element(ObjDashboard.btn_ok).click();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void cnfrmMyIdenty() throws Exception {
		try {

			entrBasicInfo();
			choosePrsnInfo1();
			choosePrsnInfo2();
			choosePrsnInfo3();
			choosePrsnInfo4();
			mblock.Element(ObjDashboard.btn_submit).click();
			SuccessIDPHeaderMessage();
			SuccessIDPBodyMessage();
			mblock.ElementExists(ObjDashboard.btn_ok);
			mblock.ValidateTest(true, "Identity Validation is success");
			mblock.Element(ObjDashboard.btn_ok).click();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
