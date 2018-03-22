package pageObject;

import org.openqa.selenium.By;

public class ObjTaxestimator {

	//TaX Estimator page
	
	public static By lnk_aboutyou = By.xpath("//*[@id='ng-app']/div/div/div/ul/li[1]/button");
	
	
	
	//About you 
	public static By txt_aboutyouheader= By.xpath("//*[@id='form-views']/div[1]/div[2]/h2");
	public static By lnk_help= By.className("help-text-btn");
	public static By txt_helpstep1= By.xpath("//div[@id='help-text-status']/div/ol/li[1]");
	public static By txt_helpstep2= By.xpath("//div[@id='help-text-status']/div/ol/li[2]");
	public static By txt_helpstep3= By.xpath("//div[@id='help-text-status']/div/ol/li[3]");
	public static By txt_helpstep4= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[4]/p[1]");
	public static By txt_helpstep4_1= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[4]/ul/li[1]");
	public static By txt_helpstep4_2= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[4]/ul/li[2]");
	public static By txt_helpstep5= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[5]");
	public static By txt_helpstep5_1= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[5]/ul/li[1]");
	public static By txt_helpstep5_3= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[5]/ul/li[3]");
	
	public static By lnk_single= By.xpath("//*[@id='form-views']/div[1]/div[2]/div/div/fieldset/div/div[1]/label");
	
	public static By lnk_dependent0= By.xpath("//*[@id='nbrDepForm']/fieldset/label[1]");
	public static By lnk_continue= By.xpath("//*[@id='form-views']/div[2]/div[2]/div/div[3]/div[2]/div/fieldset/div[2]/button[2]");
	public static By inpt_dob= By.id("tpAge");
	public static By lnk_next1= By.xpath("//*[@id='form-views']/div[1]/div[3]/button[1]");
	public static By lnk_next2= By.xpath("//*[@id='form-views']/div[2]/div[3]/button[2]");

	
	//Income 
	public static By lnk_income = By.xpath("//*[@id='ng-app']/div/div/div/ul/li[2]/button");
	public static By txt_incomeheader= By.xpath("//*[@id='form-views']/div[1]/div[2]/h2");
	
	public static By txt_step1= By.className("help-text-wrap");
	public static By txt_step2= By.xpath("//div[@id='income']/div/div[1]/p[2]");
	public static By txt_step3= By.xpath("//div[@id='income']/div/div[1]/p[10]");
	public static By txt_step4= By.xpath("//div[@id='income']/div/div[1]/p[12]");
	public static By txt_step5= By.xpath("//div[@id='income']/div/div[1]/ul[6]/li[2]");

	public static By inpt_interest= By.id("bEmployment");
	public static By lnk_interest= By.xpath("//*[@id='form-views']/div[1]/div[2]/div[2]/div/div[3]/div[1]/div/fieldset/div[1]/label[1]/span[1]");
	public static By lnk_incme= By.xpath("//*[@id='incomeForm']/div[1]/div/fieldset/label");
	public static By inpt_incomeamnt= By.id("interestIncome");
	public static By lnk_next3 = By.xpath("//*[@id='form-views']/div[1]/div[3]/button[2]");
	public static By lnk_next4 = By.xpath("//*[@id='form-views']/div[2]/div[12]/button[2]");
	
	//Expenses
	public static By lnk_expenses= By.xpath("//*[@id='ng-app']/div/div/div/ul/li[3]/button");
	public static By txt_expenseheader= By.xpath("//*[@id='form-views']/div[1]/div[2]/h2");
	public static By txt_step01= By.xpath("//div[@id='expenses']/div/div[1]/p[11]");
	public static By txt_step02= By.xpath("//div[@id='expenses']/div/div[1]/p[15]");
	public static By txt_step03= By.xpath("//div[@id='expenses']/div/div[1]/ul[9]/li[1]");

	public static By txt_step04= By.xpath("//div[@id='expenses']/div/div[1]/ul[9]/li[2]");
	public static By txt_step05= By.xpath("//div[@id='expenses']/div/div[1]/ul[9]/li[3]");
	
	public static By txt_step06= By.xpath("//div[@id='expenses']/div/div[1]/p[23]");
	public static By txt_step07= By.xpath("//div[@id='expenses']/div/div[1]/p[24]");
	public static By txt_step08= By.xpath("//div[@id='expenses']/div/div[1]/p[26]");
	public static By txt_step09= By.xpath("//div[@id='expenses']/div/div[1]/ul[17]/li[2]");
	public static By txt_step10= By.xpath("//div[@id='expenses']/div/div[1]/p[37]");
		
	//result page
	public static By btn_online = By.className("online-rec");
	

	
}
