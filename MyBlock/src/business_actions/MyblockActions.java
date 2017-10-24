package business_actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.ustglobal.selenium.SInteractive;

import pageObject.ObjLogin;
import pageObject.Objcommon;
import utility.Constant_Class;

public class MyblockActions extends SInteractive {
	
	public ExcelAction actExcel;
	public LoginAction actLogin;
	public CreateNewAccount actCtracnt;
	public UploadDocAction actUplddoc;
	public CardIdentityAction actCardIdnty;
	public LogoutAction actLogout;
	public EmerlandcardAction actEmrldCard;
	public TaxEstimatorAction actTaxEstimator;
	public GeneralnavigationAction actGnrlNvgtn;
	public ProfileAction actProfile;
	public W4CalcAction actW4Calc;
	public DashboardActions actDashboard;
	public TaxHistoryAction actTaxhstry;
	public MydocumentAction actMydoc;
	public static String browser = null;

	public MyblockActions(String[] args) throws Exception {
		super(args);
		browser = args[0];
		// TODO Auto-generated constructor stub
		actExcel = new ExcelAction();
		actLogin = new LoginAction(this);
		actCtracnt = new CreateNewAccount(this);
		actUplddoc = new UploadDocAction(this);
		actCardIdnty = new CardIdentityAction(this);
		actLogout = new LogoutAction(this);
		actEmrldCard = new EmerlandcardAction(this);
		actTaxEstimator = new TaxEstimatorAction(this);
		actGnrlNvgtn = new GeneralnavigationAction(this);
		actProfile = new ProfileAction(this);
		actW4Calc = new W4CalcAction(this);
		actDashboard = new DashboardActions(this);
		actTaxhstry = new TaxHistoryAction(this);
		actMydoc = new MydocumentAction(this);
		}
	
	public void NavigateToURL() throws Exception{
		try {
			 
			  Navigate(ExcelAction.env);
			  
			/*  boolean element = ElementExists(Objcommon.Zsclr_clicklink);
			  boolean element1 = ElementExists(Objcommon.Zsclr_accptButtn);
			  if(element==true){
				  	Element(Objcommon.Zsclr_clicklink).click();
				  	ElementExists(Objcommon.Zsclr_accptButtn);
					Element(Objcommon.Zsclr_accptButtn).click();
			  }
			  else if (element==false&&element1==true){
					Element(Objcommon.Zsclr_accptButtn).click();
			  }
			  else{
				  System.out.println("page is loaded directly");
			  }*/
			} catch (Exception e) {
				// TODO: handle exception
				
			}
		}
	
	
	//Method to launching Digital user URL 
	public void NavigateToDigitalUsrURL() throws Exception{
		try {
			 
			  Navigate(Constant_Class.Digital_URL);
			 
			  ElementExists(ObjLogin.lnk_freeEdtn);
			  Element(ObjLogin.lnk_freeEdtn).click();
			/*  boolean element = ElementExists(Objcommon.Zsclr_clicklink);
			  boolean element1 = ElementExists(Objcommon.Zsclr_accptButtn);
			  if(element==true){
				  	Element(Objcommon.Zsclr_clicklink).click();
				  	ElementExists(Objcommon.Zsclr_accptButtn);
					Element(Objcommon.Zsclr_accptButtn).click();
			  }
			  else if (element==false&&element1==true){
					Element(Objcommon.Zsclr_accptButtn).click();
			  }
			  else{
				  System.out.println("page is loaded directly");
			  }*/
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	
	public void NavigateToSSDURL() throws Exception{
		try {
			  
			  Navigate(Constant_Class.URL1);
			/*  boolean element = ElementExists(Objcommon.Zsclr_clicklink);
			  if(element){
				  	Element(Objcommon.Zsclr_clicklink).click();
					WaitForPageLoad();
					Element(Objcommon.Zsclr_accptButtn).click();
					WaitForPageLoad();
			  }
			  
			  else{
				  System.out.println("page is loaded directly");
			  }*/
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	}
