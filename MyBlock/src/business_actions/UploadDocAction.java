package business_actions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import pageObject.ObjDashboard;
import pageObject.ObjUploaddoc;
import utility.Constant_Class;

public class UploadDocAction {

	MyblockActions mblock;

	public UploadDocAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	
	public void clkMydoclnk() throws Exception {
		try {

			mblock.Element(ObjDashboard.lnk_mytax).ufxFocus();
			mblock.Element(ObjDashboard.lnk_mydoc).ufxFocus();
			mblock.Element(ObjDashboard.lnk_mydoc).ufxClick();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void upldDocTaxclient() throws Exception {
		try {
			/*mblock.actExcel.setExcelfile();
			val = mblock.actExcel.getCredentials();*/
			boolean elmnt = mblock.ElementExists(ObjUploaddoc.btn_adddoc);
			if (elmnt) {
				System.out.println("element is present");
				mblock.Element(ObjUploaddoc.btn_adddoc).click();
				mblock.Element(ObjUploaddoc.txt_frstname).sendKeys(ExcelAction.crtnls[4]);
				mblock.Element(ObjUploaddoc.txt_lastname).sendKeys(ExcelAction.crtnls[5]);
				mblock.Element(ObjUploaddoc.txt_ssn).sendKeys(ExcelAction.crtnls[6]);
				mblock.Element(ObjUploaddoc.txt_dob).click();
				mblock.Element(ObjUploaddoc.txt_dob).sendKeys(ExcelAction.crtnls[7]);
				mblock.Element(ObjUploaddoc.btn_submit).click();
				
			}

			else {
				System.out.println("element not present");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public void upldDocument() throws Exception{
		try {
			boolean elmnt = mblock.ElementExists(ObjUploaddoc.btn_adddoc);
			if (elmnt) {
				mblock.ElementExists(ObjUploaddoc.count_doc);
				List<WebElement> val1 = mblock.Element(ObjUploaddoc.count_doc).findElements(ObjUploaddoc.count_doc1);
				int val01 =val1.size();
				mblock.Element(ObjUploaddoc.btn_adddoc).click();
				Thread.sleep(3000);
				System.out.println(Constant_Class.doc_uplScript);
				Runtime.getRuntime().exec(Constant_Class.doc_uplScript);
				
				Thread.sleep(10000);
				mblock.ElementExists(ObjUploaddoc.count_doc);
				List<WebElement> val2 = mblock.Element(ObjUploaddoc.count_doc).findElements(ObjUploaddoc.count_doc1);
				int val02 = val2.size();
						if(val02>val01){
							System.out.println(val02+" > "+val01);
							System.out.println("document uploaded");
						}
						
				
			}
			


 

 } catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			
		}
	}

}
