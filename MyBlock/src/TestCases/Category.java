package TestCases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import business_actions.MyblockActions;
import pageObject.ObjDashboard;
import pageObject.ObjLogin;
import utility.Config;



public class Category {

	
	MyblockActions mblock;
	public static WebDriver driver;
	public void clickcat() throws Exception{
		try {
			boolean lnk = mblock.ElementExists(ObjDashboard.lnk_category);
			
			if(lnk){
			mblock.Element(ObjDashboard.lnk_category).ufxFocus();
			mblock.Element(ObjDashboard.lnk_category).ufxClick();
			mblock.Element(ObjDashboard.lnk_income).ufxFocus();
			mblock.Element(ObjDashboard.lnk_income).ufxClick();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		try {

			
			Config objConfig = new Config();
			MyblockActions mblock = new MyblockActions(objConfig.GetConfig());
			
			Category cat = new Category();

			// Step:1 Navigate to the browser
			mblock.NavigateToURL();

			// step:2 Run login action
			mblock.actLogin.login();
			
			mblock.Element(ObjDashboard.lnk_calctr).ufxFocus();
			mblock.Element(ObjDashboard.lnk_taxorgnzr).ufxFocus();
			mblock.Element(ObjDashboard.lnk_taxorgnzr).ufxClick();
			mblock.ElementExists(ObjDashboard.btn_additem);
			mblock.Element(ObjDashboard.btn_additem).click();
			boolean lnk = mblock.ElementExists(ObjDashboard.lnk_category);
			WebElement val = mblock.Element(ObjDashboard.wedit_itemtitle);
			
			val.click();
			val.sendKeys(Keys.TAB,Keys.RETURN,Keys.ARROW_DOWN,Keys.RETURN);
			//mblock.Element(ObjDashboard.lnk_income1).ufxSelectFromDropdown("Dividend income (Form 1099-DIV)");
			
			mblock.Element(ObjDashboard.btn_additem).click();
			mblock.Element(ObjDashboard.btn_additem).click();
			mblock.Element(ObjDashboard.lnk_category).click();
			mblock.Element(ObjDashboard.lnk_dede).click();
			
			
			//mblock.Element(ObjDashboard.lnk_category).click();
			
			//boolean lnk2 = mblock.ElementExists(ObjDashboard.lnk_income);
			boolean test = mblock.ElementExists(ObjDashboard.lnk_income);
			
			
					
					//WebElement test1 = mblock.objWebDriver.findElement(ObjDashboard.lnk_income);
					//boolean val1 = test1.isDisplayed();
			//test.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_RIGHT,Keys.RETURN);
			
			//WebElement btn=driver.findElement(By.className("btn btn-mini"));
					//mblock.Element(ObjDashboard.lnk_income).ufxFocus();
					//mblock.Element(ObjDashboard.lnk_income).click();
			// JavascriptExecutor jse=(JavascriptExecutor)mblock.objWebDriver;
			 
			// jse.executeScript("arguments[0].click();",test1);
			//Thread.sleep(100);
			//test.sendKeys(Keys.RETURN);
			System.out.println("hello");
			
			//test.click();
			//test.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_RIGHT);
			//test.sendKeys();
		//	mblock.Element(ObjDashboard.lnk_income, "Income").ufxFocus();
			//mblock.Element(ObjDashboard.lnk_income, "Income").click();
					
	
			
			
			//mblock.Element(ObjDashboard.lnk_category).sen
			//mblsesendKeys(Keys.ARROW_DOWN,Keys.ARROW_RIGHT);
			
			
		
		/*	boolean lnk1 = mblock.ElementExists(ObjDashboard.lnk_income);
			mblock.Element(ObjDashboard.lnk_income).ufxFocus();
			boolean lnk2 = mblock.ElementExists(ObjDashboard.lnk_income1);
			mblock.Element(ObjDashboard.lnk_income1).ufxClick();
			cat.clickcat();*/
			
			
					

			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}

