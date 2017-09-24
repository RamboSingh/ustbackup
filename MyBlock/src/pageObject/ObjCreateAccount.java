package pageObject;

import org.openqa.selenium.By;

public class ObjCreateAccount {

	public static By txt_email = By.id("email");
	public static By txt_usrnme = By.id("userName");
	public static By txt_pswrd = By.id("password");
	public static By txt_cnfrmpswrd = By.id("confirmpassword");
	public static By btn_next = By.id("showhide");
	public static By drpdwn_qstn1 = By.id("securityQuestion1");
	public static By drpdwn_qstn2 = By.id("securityQuestion2");
	public static By drpdwn_qstn3 = By.id("securityQuestion3");
	public static By txt_ans1 = By.id("securityAnswer1");
	public static By txt_ans2 = By.id("securityAnswer2");
	public static By txt_ans3 = By.id("securityAnswer3");
	public static By input_rmbrme = By.xpath("//input[@id='rememberme']");
	public static By box_rmbrme = By.xpath("//span[@id='rememberbox']");
	public static By box_iagree = By.xpath("//span[@id='termsbox']");
	public static By input_iagree = By.xpath("//input[@id='termsconditions']");
	public static By btn_crtacnt = By.id("submitButton");
	public static By lnk_iveacnt = By.partialLinkText("I already have an account.");
	public static By txt_errormsg = By.id("userNameAjaxError");
	
}
