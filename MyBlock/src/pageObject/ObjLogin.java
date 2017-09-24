package pageObject;

import org.openqa.selenium.By;

public class ObjLogin {

	public static By txt_username = By.id("username");
	public static By txt_password =  By.id("password");
	public static By btn_singin =  By.id("defaultSignInUser");
	public static By btn_crtacnt =  By.id("createID");
	public static By box_rememberme =  By.id("box");
	public static By SecurityAnswer_Label=By.xpath("//DIV[@id='maindatacontent']/DIV[1]/FIELDSET/DIV[2]/DL/DD[1]/LABEL");
	public static By SecurityAnswer_TextField = By.xpath("//INPUT[@id='securityAnswer']");
	public static By SecurityPage_Next = By.xpath("//INPUT[@id='defaultsecurityqn-next'] ");
	public static By SecurityAnswerLink=By.xpath("//a[@id='resendsignInCode']");
	public static By txt_errmsg =  By.id("errormsbox");
	
	//Digital user page
	public static By lnk_freeEdtn =  By.id("tcfURL");
	
	
}
