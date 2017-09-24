package pageObject;

import org.openqa.selenium.By;

public class objProfile {

	//Profile launch link is in ObjDashboard
	//Privacy Notice
	public static By Profile_PrivacyNotice = By.id("");
	//Get Profile Page
	public static By Edit = By.id("editsec");
	
	//Edit Page
	public static By PreFirstName = By.id("prefFirstName");
	public static By PreLastName = By.id("prefLastName");
	//Home Address
	public static By Home_StreetAddress = By.id("homeLineOne");
	public static By Home_Apt = By.id("homeLineTwo");
	public static By Home_City = By.id("homeCity");
	public static By Home_State = By.id("stateIdHome");
	public static By Home_Zip = By.id("homePostalCode");
	//Mailing Address
	public static By CheckBox_SameAsHome = By.id("Mailingaddress");
	public static By Mailing_StreetAddress = By.id("mailingLineOne");
	public static By Mailing_Apt = By.id("mailingLineTwo");
	public static By Mailing_City = By.id("mailingLineCity");
	public static By Mailing_State = By.id("stateIdMailing");
	public static By Mailing_Zip = By.id("mailingPostalCode");
	//Phone
	public static By Ph_Home = By.id("homephoneNumber");
	public static By Ph_Mobile = By.id("cellphoneNumber");
	public static By Ph_Business = By.id("businessphoneNumber");
	//Email Preferred
	public static By Pref_Email = By.id("preferredEmail");
	public static By CheckBox_receiveAlerts = By.id("agreeToShare1");
	
	//Submit
	public static By Btn_Submit = By.cssSelector(".mhrbbuttonrt.dtmtrack-click");
	//Success Status
	public static By Sucess_Status = By.id("successStatus");
	
	//Error
	public static By Error_FN = By.id("errorprefFirstName");
	public static By Error_LN = By.id("errorprefLastName");
	public static By Error_HomeZip = By.id("errorhomePostalCode");
	public static By Error_MailingZip = By.id("errormailingPostalCode");
	public static By Error_HomePh = By.id("errorhomephoneNumber");
	public static By Error_MobilePh = By.id("errorcellphoneNumber");
	public static By Error_BusinessPh = By.id("errorbusinessphoneNumber");
	public static By Error_Email = By.id("errorpreferredEmail");
	
}