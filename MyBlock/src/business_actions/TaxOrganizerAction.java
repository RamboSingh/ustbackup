package business_actions;

import org.junit.Assert;

import pageObject.ObjTaxHistory;
import pageObject.ObjTaxOrganizer;
import utility.Constant_Class;

public class TaxOrganizerAction {
	
	MyblockActions mblock;

	public TaxOrganizerAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}


	
	// Verify the tax organizer image(notebook and pen) text
		public void vrfyTaxorgzrBckImg() throws Exception {

			boolean txt = mblock.ElementExists(ObjTaxOrganizer.img_taxorg);
			if (txt) {
				String val = mblock.Element(ObjTaxOrganizer.img_taxorg).getCssValue("background-image");
				if (val.contains(Constant_Class.img_taxorg)) {
					mblock.ValidateTest(true, true, "Tax organizer Backround image(notebook and pen) is displayed");
				} else {
					mblock.ValidateTest(false, true, "Tax organizer Backround image(notebook and pen) is not displayed");
					Assert.assertFalse(true);
				}
			}

			else {
				mblock.ValidateTest(false, true, "Header is not displayed");
				Assert.assertFalse(true);
			}

		}
		
		
		// Verify the tax organizer Header text
		public void vrfyTaxheader() throws Exception {

			boolean txt = mblock.ElementExists(ObjTaxOrganizer.txt_taxorgnzr);
			if (txt) {
				String val = mblock.Element(ObjTaxOrganizer.txt_taxorgnzr).getText();
				if (val.equalsIgnoreCase("Tax Organizer")) {
					mblock.ValidateTest(true, true, "Tax Organizer Header Text is displayed correctly");
				} else {
					mblock.ValidateTest(false, true, "Tax Organizer Header Text is displayed not correctly");
					Assert.assertFalse(true);
				}
			}

			else {
				mblock.ValidateTest(false, true, "Header is not displayed");
				Assert.assertFalse(true);
			}

		}
		
}
