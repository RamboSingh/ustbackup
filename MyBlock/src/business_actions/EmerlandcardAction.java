package business_actions;

import org.junit.Assert;

import pageObject.ObjDashboard;

public class EmerlandcardAction {

	MyblockActions mblock;

	public EmerlandcardAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	

	public void entrBasicInfo() throws Exception {
		try {
			
			String ssn1 = ExcelAction.crtnls[6].substring(0, 3);
			String ssn2 = ExcelAction.crtnls[6].substring(3, 5);
			String ssn3 = ExcelAction.crtnls[6].substring(5, 9);

			/*mblock.actExcel.setExcelfile();
			val = mblock.actExcel.getCredentials();
			//String ssn1 = val[6].substring(0, 3);
			String ssn2 = val[6].substring(3, 5);
			String ssn3 = val[6].substring(5, 9);*/
			mblock.Element(ObjDashboard.lnk_emracssmycard).ufxScrollElementToView();
			boolean lnk = mblock.ElementExists(ObjDashboard.lnk_emracssmycard);
			if (lnk) {
				mblock.ValidateTest(true,true,"Emerald Card is present");
				mblock.Element(ObjDashboard.lnk_emracssmycard).click();
				mblock.ElementExists(ObjDashboard.iframe_card);
				mblock.ActivateFrame(ObjDashboard.iframe_card);
				mblock.ElementExists(ObjDashboard.txt_frstname);
				mblock.Element(ObjDashboard.txt_frstname).sendKeys(ExcelAction.crtnls[4]);
				mblock.Element(ObjDashboard.txt_lstname).sendKeys(ExcelAction.crtnls[5]);
				mblock.Element(ObjDashboard.txt_ssn1).sendKeys(ssn1);
				mblock.Element(ObjDashboard.txt_ssn2).sendKeys(ssn2);
				mblock.Element(ObjDashboard.txt_ssn3).sendKeys(ssn3);
				mblock.Element(ObjDashboard.txt_dob).sendKeys(ExcelAction.crtnls[7]);
				mblock.Element(ObjDashboard.btn_next).click();
				mblock.ValidateTest(true,true,"Emerald Card link is present");
			} else {
				mblock.ValidateTest(false,true,"Emerald Card link is not present");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void entrCardInfo() throws Exception {
		try {

			boolean txt = mblock.ElementExists(ObjDashboard.txt_cardnumbr);
			if (txt) {
				mblock.ValidateTest(true,true,"Card information is displayed");
				mblock.Element(ObjDashboard.txt_cardnumbr).sendKeys(ExcelAction.crtnls[36]);
				mblock.Element(ObjDashboard.txt_cvv).sendKeys(ExcelAction.crtnls[37]);
				mblock.Element(ObjDashboard.txt_pin).sendKeys(ExcelAction.crtnls[38]);
				mblock.Element(ObjDashboard.box_iagree).click();
				mblock.Element(ObjDashboard.btn_nxt).click();
				mblock.ElementExists(ObjDashboard.btn_okey);
				mblock.Element(ObjDashboard.btn_okey).click();
				mblock.ValidateTest(true,true,"Card proofing is successful");
				

			} else {
				mblock.ValidateTest(false,true,"Card information is not displayed");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void cardPrfActn() throws Exception{
		try {
			entrBasicInfo();
			entrCardInfo();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	

}
