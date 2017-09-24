package pageObject;

import org.openqa.selenium.By;

public class objPOM {
	
	//Page launch link is in ObjDashboard
	//Page1
	public static By AppointmentLink = By.id("ifAppoint");
	public static By FreqAskedQuestions = By.linkText("Frequently Asked Questions");
	public static By lnk_95 = By.linkText("95");
	public static By BtnPOM = By.xpath(".//*[@id='skiptoMainContent']/div/div[2]/div/div[2]/div/a");
	
	public static By LetterFromAuthority = By.cssSelector(".ng-binding");
	
	public static By POM_PageHeader = By.xpath(".//*[@id='skiptoMainContent']/div/div[1]/div/div/div/h1/strong");
	public static By POM_Content = By.xpath(".//*[@id='skiptoMainContent']/div/div[2]/div/div[1]");
	
}
	
