package pageObject;

import org.openqa.selenium.By;

public class ObjTaxestimator {

	//TaX Estimator page
	
	public static By lnk_aboutyou = By.id("tc-tab1");
	
	
	
	//About you 
	public static By txt_aboutyouheader= By.xpath("//*[@id='filingStatusForm']/fieldset/legend");
	public static By lnk_help= By.className("tc-help-btn");
	public static By txt_helpstep1= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[1]");
	public static By txt_helpstep2= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[2]");
	public static By txt_helpstep3= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[3]/p[1]");
	public static By txt_helpstep4= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[4]/p[1]");
	public static By txt_helpstep4_1= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[4]/ul/li[1]");
	public static By txt_helpstep4_2= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[4]/ul/li[2]");
	public static By txt_helpstep5= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[5]");
	public static By txt_helpstep5_1= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[5]/ul/li[1]");
	public static By txt_helpstep5_3= By.xpath("//div[@id='filing-status']/div/div[1]/ol/li[5]/ul/li[3]");
	
	public static By lnk_single= By.xpath("//*[@id='filingStatusForm']/fieldset/label[1]");
	public static By lnk_dependent0= By.xpath("//*[@id='nbrDepForm']/fieldset/label[1]");
	public static By lnk_continue= By.linkText("Continue");
	public static By inpt_dob= By.id("taxPayerDOB");
	public static By lnk_next= By.linkText("Next");
	
	//Income 
	public static By lnk_income = By.id("tc-tab2");
	public static By txt_incomeheader= By.xpath("//*[@id='incomeForm']/div[1]/div/fieldset/legend");
	
	public static By txt_step1= By.xpath("//div[@id='income']/div/div[1]/p[1]");
	public static By txt_step2= By.xpath("//div[@id='income']/div/div[1]/p[2]");
	public static By txt_step3= By.xpath("//div[@id='income']/div/div[1]/p[10]");
	public static By txt_step4= By.xpath("//div[@id='income']/div/div[1]/p[12]");
	public static By txt_step5= By.xpath("//div[@id='income']/div/div[1]/ul[6]/li[2]");

	public static By lnk_interest= By.xpath("//*[@id='incomeForm']/div[1]/div/fieldset/label[6]");
	public static By lnk_incme= By.xpath("//*[@id='incomeForm']/div[1]/div/fieldset/label[");
	public static By inpt_incomeamnt= By.id("interestIncome");
	
	//Expenses
	public static By lnk_expenses= By.id("tc-tab3");
	public static By txt_expenseheader= By.xpath("//*[@id='expenseForm']/div[1]/div/fieldset/legend");
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
