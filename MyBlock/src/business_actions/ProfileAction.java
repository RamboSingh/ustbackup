package business_actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import pageObject.ObjDashboard;
import pageObject.ObjLogin;
import pageObject.objProfile;
import pageObject.objW4Calc;
import utility.Constant_Class;

public class ProfileAction {

	MyblockActions mblock;

	public ProfileAction(MyblockActions myblockActions) {
		// TODO Auto-generated constructor stub
		this.mblock = myblockActions;
	}

	// Profile from dashboard

	public void profile_Launch() throws Exception {

		try {
			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_profile);
			if (element1) {
				mblock.ValidateTest(true, true, "Profile is displayed");
				mblock.Log("Clicking on Profile");
				mblock.Element(ObjDashboard.lnk_profile).ufxFocus();

			} else {

				mblock.ValidateTest(false, true, "Profile is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void profile_Click() throws Exception {

		try {
			boolean element1 = mblock.ElementExists(ObjDashboard.lnk_prfle);
			if (element1) {
				mblock.ValidateTest(true, true, "Profile link is displayed");
				mblock.Log("Clicking on Profile Link");
				mblock.Element(ObjDashboard.lnk_prfle).click();

			} else {

				mblock.ValidateTest(false, true, "Profile link is not displayed. Please try with IDP/CP user.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void prospective_profile_Click() throws Exception {

		try {
			boolean element1 = mblock.ElementDoesNotExists(ObjDashboard.lnk_prfle);
			if (element1) {
				mblock.ValidateTest(true, true, "Profile link is not displayed");
				mblock.Log("Clicking on Profile Link");

			} else {

				mblock.ValidateTest(false, true, "Profile link is displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Click Edit Link
	public void profile_Edit() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Edit);
			if (element1) {
				mblock.ValidateTest(true, true, "Edit link is displayed");
				mblock.Log("Clicking on Edit Link");
				mblock.Element(objProfile.Edit).click();

			} else {

				mblock.ValidateTest(false, true, "Edit link is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Changes in Preferred Name
	// First Name
	public void PreFirstName() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.PreFirstName);
			if (element1) {
				mblock.ValidateTest(true, true, "First Name field is displayed");
				mblock.Log("Clicking on First Name Field");

				mblock.Element(objProfile.PreFirstName).clear();
				mblock.Element(objProfile.PreFirstName).sendKeys(ExcelAction.crtnls[40]);

			} else {

				mblock.ValidateTest(false, true, "First Name field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Negative First Name
	public void NegFirstName() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.PreFirstName);
			if (element1) {
				mblock.ValidateTest(true, true, "First Name field is displayed");
				mblock.Log("Clicking on First Name Field");

				mblock.Element(objProfile.PreFirstName).clear();
				String MaxCharFN = "qwertyuiopasdfghjklzxcvbnmop";
				mblock.Element(objProfile.PreFirstName).sendKeys(MaxCharFN);
				String EnteredText = mblock.Element(objProfile.PreFirstName).getText();
				if (MaxCharFN != EnteredText) {
					mblock.Log("FN field accepts only first 25 characters");

				}
				mblock.Element(objProfile.PreFirstName).clear();
				mblock.Element(objProfile.PreFirstName).sendKeys("Mr.Automation");
				boolean element2 = mblock.ElementExists(objProfile.Error_FN);
				if (element2 == true) {
					mblock.Log("FN field Does not accept special characters");
					mblock.ValidateTest(true, true, "FN field Does not accept special characters");
				}
				mblock.Element(objProfile.PreFirstName).clear();
				mblock.Element(objProfile.PreFirstName).sendKeys("123");
				if (element2 == true) {
					mblock.Log("FN field Does not accept Numerics");
					mblock.ValidateTest(true, true, "FN field Does not accept Numerics");
				}
				PreFirstName();

			} else {

				mblock.ValidateTest(false, true, "First Name field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Last Name
	public void PreLastName() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.PreLastName);
			if (element1) {
				mblock.ValidateTest(true, true, "Last Name field is displayed");
				mblock.Log("Clicking on Last Name Field");

				mblock.Element(objProfile.PreLastName).clear();
				mblock.Element(objProfile.PreLastName).sendKeys(ExcelAction.crtnls[41]);

			} else {

				mblock.ValidateTest(false, true, "Last Name field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Negative Last Name
	public void NegLastName() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.PreLastName);
			if (element1) {
				mblock.ValidateTest(true, true, "Last Name field is displayed");
				mblock.Log("Clicking on Last Name Field");

				mblock.Element(objProfile.PreLastName).clear();
				String MaxCharLN = "qwertyuioplkjhgfdsamnbvcxzqwerty";
				mblock.Element(objProfile.PreLastName).sendKeys(MaxCharLN);
				String EnteredText = mblock.Element(objProfile.PreLastName).getText();
				if (MaxCharLN != EnteredText) {
					mblock.Log("LN field accepts only first 30 characters");

				}
				mblock.Element(objProfile.PreLastName).clear();
				mblock.Element(objProfile.PreLastName).sendKeys("Mr.Automation");
				boolean element2 = mblock.ElementExists(objProfile.Error_LN);
				if (element2 == true) {
					mblock.Log("LN field Does not accept special characters");
					mblock.ValidateTest(true, true, "LN field Does not accept special characters");
				}
				mblock.Element(objProfile.PreLastName).clear();
				mblock.Element(objProfile.PreLastName).sendKeys("123");
				if (element2 == true) {
					mblock.Log("LN field Does not accept Numerics");
					mblock.ValidateTest(true, true, "LN field Does not accept Numerics");
				}
				PreLastName();

			} else {

				mblock.ValidateTest(false, true, "First Name field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Home Address
	// Street Address
	public void Home_Street() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Home_StreetAddress);
			if (element1) {
				mblock.ValidateTest(true, true, "Street Address field is displayed");
				mblock.Log("Clicking on Street Address Field");

				mblock.Element(objProfile.Home_StreetAddress).clear();
				mblock.Element(objProfile.Home_StreetAddress).sendKeys(ExcelAction.crtnls[42]);

			} else {

				mblock.ValidateTest(false, true, "Street Address field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Apt/Suite Address
	public void Home_Apt() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Home_Apt);
			if (element1) {
				mblock.ValidateTest(true, true, "Apt field is displayed");
				mblock.Log("Clicking on Apt Field");

				mblock.Element(objProfile.Home_Apt).clear();
				mblock.Element(objProfile.Home_Apt).sendKeys(ExcelAction.crtnls[43]);

			} else {

				mblock.ValidateTest(false, true, "Apt field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// City
	public void Home_City() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Home_City);
			if (element1) {
				mblock.ValidateTest(true, true, "City field is displayed");
				mblock.Log("Clicking on City Field");

				mblock.Element(objProfile.Home_City).clear();
				mblock.Element(objProfile.Home_City).sendKeys(ExcelAction.crtnls[44]);

			} else {

				mblock.ValidateTest(false, true, "City field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// State
	public void Home_State() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Home_State);
			if (element1) {
				mblock.ValidateTest(true, true, "State field is displayed");
				mblock.Log("Clicking on State Field");
				new Select(mblock.Element(objProfile.Home_State)).selectByVisibleText(ExcelAction.crtnls[45]);

			} else {

				mblock.ValidateTest(false, true, "State field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Zip
	public void Home_Zip() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Home_Zip);
			if (element1) {
				mblock.ValidateTest(true, true, "Zip field is displayed");
				mblock.Log("Clicking on State Field");
				mblock.Element(objProfile.Home_Zip).clear();
				mblock.Element(objProfile.Home_Zip).sendKeys(ExcelAction.crtnls[46]);

			} else {

				mblock.ValidateTest(false, true, "Zip field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Negative Home Address
	public void NegHomeAddress() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Home_StreetAddress);
			if (element1) {
				mblock.ValidateTest(true, true, "Street Address field is displayed");
				mblock.Log("Clicking on Street Address Field");
				String maxChar = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuioppoiuytrewqpoiuytrewqpoiuytrewqpoiuytrewq";
				mblock.Element(objProfile.Home_StreetAddress).clear();
				mblock.Element(objProfile.Home_StreetAddress).sendKeys(maxChar);
				String enteredVal = mblock.Element(objProfile.Home_StreetAddress).getText();
				if (maxChar != enteredVal) {
					mblock.Log("Street Address accepts only 100 characters");
				}

				Home_Street();
				boolean element2 = mblock.ElementExists(objProfile.Home_Apt);
				if (element2) {
					mblock.ValidateTest(true, true, "Apt field is displayed");
					mblock.Log("Clicking on Apt Field");
					String maxCharApt = "qwertyuiopqwertyuiopqwertyuiop";
					mblock.Element(objProfile.Home_Apt).clear();
					mblock.Element(objProfile.Home_Apt).sendKeys(maxCharApt);
					String enteredValApt = mblock.Element(objProfile.Home_Apt).getText();
					if (maxCharApt != enteredValApt) {
						mblock.Log("Apt/Suite accepts only 25 characters");
					}

					Home_Apt();
				}
				boolean element3 = mblock.ElementExists(objProfile.Home_City);
				if (element3) {
					mblock.ValidateTest(true, true, "Apt field is displayed");
					mblock.Log("Clicking on Apt Field");
					String maxCharCity = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
					mblock.Element(objProfile.Home_City).clear();
					mblock.Element(objProfile.Home_City).sendKeys(maxCharCity);
					String enteredValCity = mblock.Element(objProfile.Home_City).getText();
					if (maxCharCity != enteredValCity) {
						mblock.Log("City accepts only 50 characters");
					}

					Home_City();
				}
				boolean element4 = mblock.ElementExists(objProfile.Home_Zip);
				if (element4) {
					mblock.ValidateTest(true, true, "Apt field is displayed");
					mblock.Log("Clicking on Apt Field");
					String maxCharzip = "qwertyuiopqwertyuiopdfsdf";
					mblock.Element(objProfile.Home_Zip).clear();
					mblock.Element(objProfile.Home_Zip).sendKeys(maxCharzip);
					String enteredValZip = mblock.Element(objProfile.Home_Zip).getText();
					if (maxCharzip != enteredValZip) {
						mblock.Log("City accepts only 50 characters");
					} else if (mblock.ElementExists(objProfile.Error_HomeZip) == true) {
						mblock.Log("ZipCode is not valid");
						mblock.ValidateTest(true, true, "ZipCode is not valid");
					}
					mblock.Element(objProfile.Home_Zip).clear();
					mblock.Element(objProfile.Home_Zip).sendKeys("641056");
					if (mblock.ElementExists(objProfile.Error_HomeZip) == true) {
						mblock.Log("ZipCode is not valid");
						mblock.ValidateTest(true, true, "ZipCode is not valid");
					}

					Home_Zip();
				}

			} else {

				mblock.ValidateTest(false, true, "Negative TC failing for Home Field");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// MAILING ADDRESS
	// CHECKBOX SAME AS

	public void chkBox_SameAs() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.CheckBox_SameAsHome);
			if (element1) {
				mblock.ValidateTest(true, true, "Check Box Same as Home is displayed");

				mblock.Log("Clicking on Check Box Same as Home");
				if (ExcelAction.crtnls[47].equalsIgnoreCase("YES")) {
					boolean val = mblock.ElementExists(objProfile.CheckBox_SameAsHome);
					mblock.Element(objProfile.CheckBox_SameAsHome).click();

				} else {
					mailing_Street();
					Mailing_Apt();
					Mailing_City();
					Mailing_State();
					Mailing_Zip();

				}

			} else {

				mblock.ValidateTest(false, true, "Check Box Same as Home is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Street Address
	public void mailing_Street() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Mailing_StreetAddress);
			if (element1) {
				mblock.ValidateTest(true, true, "Street Address field is displayed");
				mblock.Log("Clicking on Street Address Field");

				mblock.Element(objProfile.Mailing_StreetAddress).clear();
				mblock.Element(objProfile.Mailing_StreetAddress).sendKeys(ExcelAction.crtnls[48]);

			} else {

				mblock.ValidateTest(false, true, "Street Address field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Apt/Suite Address
	public void Mailing_Apt() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Mailing_Apt);
			if (element1) {
				mblock.ValidateTest(true, true, "Apt field is displayed");
				mblock.Log("Clicking on Apt Field");

				mblock.Element(objProfile.Mailing_Apt).clear();
				mblock.Element(objProfile.Mailing_Apt).sendKeys(ExcelAction.crtnls[49]);

			} else {

				mblock.ValidateTest(false, true, "Apt field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// City
	public void Mailing_City() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Mailing_City);
			if (element1) {
				mblock.ValidateTest(true, true, "City field is displayed");
				mblock.Log("Clicking on City Field");

				mblock.Element(objProfile.Mailing_City).clear();
				mblock.Element(objProfile.Mailing_City).sendKeys(ExcelAction.crtnls[50]);

			} else {

				mblock.ValidateTest(false, true, "City field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// State
	public void Mailing_State() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Mailing_State);
			if (element1) {
				mblock.ValidateTest(true, true, "State field is displayed");
				mblock.Log("Clicking on State Field");
				new Select(mblock.Element(objProfile.Mailing_State)).selectByVisibleText(ExcelAction.crtnls[51]);

			} else {

				mblock.ValidateTest(false, true, "State field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Zip
	public void Mailing_Zip() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Mailing_Zip);
			if (element1) {
				mblock.ValidateTest(true, true, "Zip field is displayed");
				mblock.Log("Clicking on State Field");
				mblock.Element(objProfile.Mailing_Zip).sendKeys(ExcelAction.crtnls[52]);

			} else {

				mblock.ValidateTest(false, true, "Zip field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Negative Mailing Address
	public void NegMailingAddress() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Mailing_StreetAddress);
			if (element1) {
				mblock.ValidateTest(true, true, "Street Address field is displayed");
				mblock.Log("Clicking on Street Address Field");
				String maxChar = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuioppoiuytrewqpoiuytrewqpoiuytrewqpoiuytrewq";
				mblock.Element(objProfile.Mailing_StreetAddress).clear();
				mblock.Element(objProfile.Mailing_StreetAddress).sendKeys(maxChar);
				String enteredVal = mblock.Element(objProfile.Mailing_StreetAddress).getText();
				if (maxChar != enteredVal) {
					mblock.Log("Street Address accepts only 100 characters");
				}

				mailing_Street();
				boolean element2 = mblock.ElementExists(objProfile.Mailing_Apt);
				if (element2) {
					mblock.ValidateTest(true, true, "Apt field is displayed");
					mblock.Log("Clicking on Apt Field");
					String maxCharApt = "qwertyuiopqwertyuiopqwertyuiop";
					mblock.Element(objProfile.Mailing_Apt).clear();
					mblock.Element(objProfile.Mailing_Apt).sendKeys(maxCharApt);
					String enteredValApt = mblock.Element(objProfile.Mailing_Apt).getText();
					if (maxCharApt != enteredValApt) {
						mblock.Log("Apt/Suite accepts only 25 characters");
					}

					Mailing_Apt();
				}
				boolean element3 = mblock.ElementExists(objProfile.Mailing_City);
				if (element3) {
					mblock.ValidateTest(true, true, "Apt field is displayed");
					mblock.Log("Clicking on Apt Field");
					String maxCharCity = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
					mblock.Element(objProfile.Mailing_City).clear();
					mblock.Element(objProfile.Mailing_City).sendKeys(maxCharCity);
					String enteredValCity = mblock.Element(objProfile.Mailing_City).getText();
					if (maxCharCity != enteredValCity) {
						mblock.Log("City accepts only 50 characters");
					}

					Mailing_City();
				}
				boolean element4 = mblock.ElementExists(objProfile.Mailing_Zip);
				if (element4) {
					mblock.ValidateTest(true, true, "Apt field is displayed");
					mblock.Log("Clicking on Apt Field");
					String maxCharzip = "qwertyuiopqwertyuiopdfsdf";
					mblock.Element(objProfile.Mailing_Zip).clear();
					mblock.Element(objProfile.Mailing_Zip).sendKeys(maxCharzip);
					String enteredValZip = mblock.Element(objProfile.Mailing_Zip).getText();
					if (maxCharzip != enteredValZip) {
						mblock.Log("City accepts only 50 characters");
					} else if (mblock.ElementExists(objProfile.Error_MailingZip) == true) {
						mblock.Log("ZipCode is not valid");
						mblock.ValidateTest(true, true, "ZipCode is not valid");
					}
					mblock.Element(objProfile.Mailing_Zip).clear();
					mblock.Element(objProfile.Mailing_Zip).sendKeys("641056");
					if (mblock.ElementExists(objProfile.Error_MailingZip) == true) {
						mblock.Log("ZipCode is not valid");
						mblock.ValidateTest(true, true, "ZipCode is not valid");
					}

					Mailing_Zip();
				}

			} else {

				mblock.ValidateTest(false, true, "Negative TC failing for Home Field");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// PHONE
	// Home
	public void phone_Home() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Ph_Home);
			if (element1) {
				mblock.ValidateTest(true, true, "Home Phone field is displayed");
				mblock.Log("Clicking on Home Phone Field");
				mblock.Element(objProfile.Ph_Home).clear();
				mblock.Element(objProfile.Ph_Home).sendKeys(ExcelAction.crtnls[53]);

			} else {

				mblock.ValidateTest(false, true, "Home Phone field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Mobile
	public void phone_Mobile() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Ph_Mobile);
			if (element1) {
				mblock.ValidateTest(true, true, "Mobile Phone field is displayed");
				mblock.Log("Clicking on Mobile Phone Field");
				mblock.Element(objProfile.Ph_Mobile).clear();
				mblock.Element(objProfile.Ph_Mobile).sendKeys(ExcelAction.crtnls[54]);

			} else {

				mblock.ValidateTest(false, true, "Mobile Phone field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Business
	public void phone_Business() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Ph_Business);
			if (element1) {
				mblock.ValidateTest(true, true, "business Phone field is displayed");
				mblock.Log("Clicking on business Phone Field");
				mblock.Element(objProfile.Ph_Business).clear();
				mblock.Element(objProfile.Ph_Business).sendKeys(ExcelAction.crtnls[55]);

			} else {

				mblock.ValidateTest(false, true, "business Phone field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Negative Phone
	public void NegPhone() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Ph_Home);
			if (element1) {
				mblock.ValidateTest(true, true, "Home Phone field is displayed");
				mblock.Log("Clicking on Home Phone Field");
				String maxChar = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuioppoiuytrewqpoiuytrewqpoiuytrewqpoiuytrewq";
				mblock.Element(objProfile.Ph_Home).clear();
				mblock.Element(objProfile.Ph_Home).sendKeys(maxChar);
				String enteredVal = mblock.Element(objProfile.Ph_Home).getText();
				if (maxChar != enteredVal) {
					mblock.Log("Phone number accepts only 50 numbers");
				} else if (mblock.ElementExists(objProfile.Error_HomePh) == true) {
					mblock.Log("Enter a number of 10 digits");
					mblock.ValidateTest(true, true, "Entered number is not of 10 digits");

				}
				mblock.Element(objProfile.Ph_Home).clear();
				mblock.Element(objProfile.Ph_Home).sendKeys("1234");
				if (mblock.ElementExists(objProfile.Error_HomePh) == true) {
					mblock.Log("Enter a number of 10 digits");
					mblock.ValidateTest(true, true, "Entered number is less than 10 digits");
				}
				phone_Home();
			}
			boolean element2 = mblock.ElementExists(objProfile.Ph_Mobile);
			if (element1) {
				mblock.ValidateTest(true, true, "Home Phone field is displayed");
				mblock.Log("Clicking on Home Phone Field");
				String maxCharMob = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuioppoiuytrewqpoiuytrewqpoiuytrewqpoiuytrewq";
				mblock.Element(objProfile.Ph_Mobile).clear();
				mblock.Element(objProfile.Ph_Mobile).sendKeys(maxCharMob);
				String enteredValMob = mblock.Element(objProfile.Ph_Mobile).getText();
				if (maxCharMob != enteredValMob) {
					mblock.Log("Phone number accepts only 50 numbers");
				} else if (mblock.ElementExists(objProfile.Error_MobilePh) == true) {
					mblock.Log("Enter a number of 10 digits");
					mblock.ValidateTest(true, true, "Entered number is not of 10 digits");

				}
				mblock.Element(objProfile.Ph_Mobile).clear();
				mblock.Element(objProfile.Ph_Mobile).sendKeys("1234");
				if (mblock.ElementExists(objProfile.Error_MobilePh) == true) {
					mblock.Log("Enter a number of 10 digits");
					mblock.ValidateTest(true, true, "Entered number is less than 10 digits");
				}
				phone_Mobile();
			}
			boolean element3 = mblock.ElementExists(objProfile.Ph_Business);
			if (element1) {
				mblock.ValidateTest(true, true, "Home Phone field is displayed");
				mblock.Log("Clicking on Home Phone Field");
				String maxCharBus = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuioppoiuytrewqpoiuytrewqpoiuytrewqpoiuytrewq";
				mblock.Element(objProfile.Ph_Business).clear();
				mblock.Element(objProfile.Ph_Business).sendKeys(maxCharBus);
				String enteredValBus = mblock.Element(objProfile.Ph_Business).getText();
				if (maxCharBus != enteredValBus) {
					mblock.Log("Phone number accepts only 50 numbers");
				} else if (mblock.ElementExists(objProfile.Error_BusinessPh) == true) {
					mblock.Log("Enter a number of 10 digits");
					mblock.ValidateTest(true, true, "Entered number is not of 10 digits");

				}
				mblock.Element(objProfile.Ph_Business).clear();
				mblock.Element(objProfile.Ph_Business).sendKeys("1234");
				if (mblock.ElementExists(objProfile.Error_BusinessPh) == true) {
					mblock.Log("Enter a number of 10 digits");
					mblock.ValidateTest(true, true, "Entered number is less than 10 digits");
				}
				phone_Business();

			} else {

				mblock.ValidateTest(false, true, "Negative TC failing for Home Field");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Email Preferred
	public void email_Preferred() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Pref_Email);
			if (element1) {
				mblock.ValidateTest(true, true, "Preferred Email field is displayed");
				mblock.Log("Clicking on Preferred Email Field");
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(ExcelAction.crtnls[3]);

			} else {

				mblock.ValidateTest(false, true, "Preferred Email field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Negative Email
	public void negEmail() throws Exception {
		try {
			String maxLength = "asJr8GymgUtcd6GE0pH0O5qS7qX8JyK6hKkLP4REesf1xaqWyzp5M9ZsOMbc4UF50b7zWINJN2Wm2TUdNC1wuE7fETrAjY610FPvSLBzVbr902rChFLm5BdKiGdsKzaaFKauZx4q8OyjR0RMV9P73BBPG1pEJ9sy8a87nE0vYutC621G0sicNt9yLRhB81zb7aD7jcgX0IvESl14yXN66jiPzDoG2gKOIl7hkr1Ksd";
			String invalidemail1 = "@me@email.com";
			String invalidemail2 = "me automation@email.com";
			String invalidemail3 = "meemail.com";
			String invalidemail4 = "me@.email.com";
			String invalidemail5 = "me@email.c";
			String invalidemail6 = "me,@mail.com";
			String invalidemail7 = "me.mail@email..com";
			String invalidemail8 = ".me@email.com";
			String invalidemail9 = "me@email.com.";
			boolean element1 = mblock.ElementExists(objProfile.Pref_Email);
			if (element1) {
				mblock.ValidateTest(true, true, "Preferred Email field is displayed");
				mblock.Log("Clicking on Preferred Email Field");
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(maxLength);
				if (mblock.ElementExists(objProfile.Error_Email) == true) {
					mblock.Log("Enter a valid email id");
					mblock.ValidateTest(true, true, "Enter a valid email id");
				}
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(invalidemail1);
				if (mblock.ElementExists(objProfile.Error_Email) == true) {
					mblock.Log("Enter a valid email id");
					mblock.ValidateTest(true, true, "Enter a valid email id");
				}
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(invalidemail2);
				if (mblock.ElementExists(objProfile.Error_Email) == true) {
					mblock.Log("Enter a valid email id");
					mblock.ValidateTest(true, true, "Enter a valid email id");
				}
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(invalidemail3);
				if (mblock.ElementExists(objProfile.Error_Email) == true) {
					mblock.Log("Enter a valid email id");
					mblock.ValidateTest(true, true, "Enter a valid email id");
				}
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(invalidemail4);
				if (mblock.ElementExists(objProfile.Error_Email) == true) {
					mblock.Log("Enter a valid email id");
					mblock.ValidateTest(true, true, "Enter a valid email id");
				}
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(invalidemail5);
				if (mblock.ElementExists(objProfile.Error_Email) == true) {
					mblock.Log("Enter a valid email id");
					mblock.ValidateTest(true, true, "Enter a valid email id");
				}
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(invalidemail6);
				if (mblock.ElementExists(objProfile.Error_Email) == true) {
					mblock.Log("Enter a valid email id");
					mblock.ValidateTest(true, true, "Enter a valid email id");
				}
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(invalidemail7);
				if (mblock.ElementExists(objProfile.Error_Email) == true) {
					mblock.Log("Enter a valid email id");
					mblock.ValidateTest(true, true, "Enter a valid email id");
				}
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(invalidemail8);
				if (mblock.ElementExists(objProfile.Error_Email) == true) {
					mblock.Log("Enter a valid email id");
					mblock.ValidateTest(true, true, "Enter a valid email id");
				}
				mblock.Element(objProfile.Pref_Email).clear();
				mblock.Element(objProfile.Pref_Email).sendKeys(invalidemail9);
				if (mblock.ElementExists(objProfile.Error_Email) == true) {
					mblock.Log("Enter a valid email id");
					mblock.ValidateTest(true, true, "Enter a valid email id");
				}
			} else {

				mblock.ValidateTest(false, true, "Preferred Email field is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Subscribe
	/*public void chkBox_Subscribe() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.CheckBox_receiveAlerts);
			if (element1) {
				mblock.ValidateTest(true, true, "Check Box is displayed");
				mblock.Log("Clicking on Check Box");
				boolean chckbox = mblock.Element(objProfile.CheckBox_receiveAlerts).isSelected();
				if ((chckbox == false) && (ExcelAction.crtnls[56].equalsIgnoreCase("YES"))) {
					mblock.Element(objProfile.CheckBox_receiveAlerts).click();

				} else {
					mblock.Element(objProfile.CheckBox_receiveAlerts).click();
				}

			} else {

				mblock.ValidateTest(false, true, "Check Box is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/

	// Submit

	public void Btn_submit() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Btn_Submit);
			if (element1) {
				mblock.ValidateTest(true, true, "Submit Button is displayed");
				mblock.Log("Clicking on Submit Button");
				mblock.Element(objProfile.Btn_Submit).click();
				mblock.GetSynchWaitTimeInMillis();

			} else {

				mblock.ValidateTest(false, true, "Submit Button is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Success Message
	public void Message_Success() throws Exception {
		try {
			boolean element1 = mblock.ElementExists(objProfile.Sucess_Status);
			if (element1 == true) {
				mblock.ValidateTest(true, true, "Sucess Status Message is displayed");
				mblock.Log("Sucess Status Message is displayed");

			} else {

				mblock.ValidateTest(false, true, "Sucess Status Message is not displayed");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}