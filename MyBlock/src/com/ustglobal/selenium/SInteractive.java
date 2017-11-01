package com.ustglobal.selenium;



import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.google.common.io.Files;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.ustglobal.common.Debugger;
import com.ustglobal.common.SystemTrayMonitor;
import com.ustglobal.common.Utility;
import com.ustglobal.common.Utility.CustomReporter;
import com.ustglobal.common.Utility.FinalizableInterface;

public class SInteractive implements FinalizableInterface {
	public WebDriver objWebDriver = null;
	public static String strBrowserType="";
	public String strMobileEmulationType="";
	public boolean boolBringElementToFocus = true;
	public int nThreadRampUpInMillis = 2000;
	private int nSynchWaitInMillis = 20000; //in milliseconds
	private int nSynchResponsivenessInMillis = 1000; //in milliseconds	
	private String strReportDirectory = System.getProperty("user.dir") +  "\\test-report";
	private String strReportDetails = Utility.GetUniqueTestNameIdentifier();
	private String strReportFileName = "TestReport_"+ strReportDetails + ".pdf";
	private Dimension objBrowserSize = null;
	private Point objBrowserLocation = null;
	private DesiredCapabilities objCapability = null;
	private WebDriverWait objSyncWait = null;
	private WebDriver objAugmentedDriver = null;
	private CustomReporter objReporter = null;
	private CustomElement objElement = null;
	private String strLastAttemptedIdentification = null;
	private Actions objAction = null;
	private Thread objThread = null;
	private SystemTrayMonitor objSystemTrayControl = null;
	public Debugger objDebugger;
	public boolean boolExceptionDetected = false;
	
	public static boolean boolGlobalSettingReportsEnabled = true;
	public static boolean boolGlobalSystemTrayEnabled = true;
	public static boolean boolGlobalDebuggerEnabled = true;
	
	//Return the reporter object. [Pappan: disabling this for timebeing. Looking for a better interface for external access]
	/*public CustomReporter fnGetNativeReporter(){
		return objReporter;
	}*/
		
	//Enable Debug option by adding SInteractive and derived child class object (Eg TCXClient)
	//[Pappan: commented below, we needed the single contructor for Debugger to take care of everything]
	/*public void fnEnableDebug(Object objClient){
		System.out.println("Going to save SInteractive client for debugging");
		objDebugger.fnRegisterClient("SInteractive",objClient); // Register SInteractive
		objDebugger.fnRegisterClient("",objClient); //Register Derived class
	}*/
		
	private SInteractive(String sBrowserType, URL objHostURL) throws Exception { 
		//Marked private since no users yet. Also some confusion to users on the objHostURL, whether it's the browser url
		this.InitiateDriver(sBrowserType, objHostURL);
	
	}
	public SInteractive(String sBrowserType) throws Exception{
		this.InitiateDriver(sBrowserType);
	}
	public SInteractive(String sBrowserType, String sHostNameOrIP) throws Exception{
		this.InitiateDriver(sBrowserType, sHostNameOrIP);
		objReporter.AddToSummary("Remote Server", sHostNameOrIP);//For writing Remote Server Name in PDF Report -- Added by TCX team
	}
	/*
	//For Appium
	public SInteractive(String sAppiumServerUrl, String[] sCapabilities) throws Exception{
		this.InitiateDriver(sAppiumServerUrl, sCapabilities);
	}
	
	
	*/
	
	public static String checkBrowser(){
		return strBrowserType;
	}
	
	public SInteractive(ITestContext context) throws Exception{ //For TestNG users
		this.InitiateDriver(context);
	}
	//Added by TCX team
	public SInteractive(String[] args) throws Exception{
		String sBrowserType= args[0];
		if (args.length==1){
			this.InitiateDriver(sBrowserType);
		}
		else
		{
			String sHostNameOrIP= args[1];
			this.InitiateDriver(sBrowserType, sHostNameOrIP);
			objReporter.AddToSummary("Remote Server", sHostNameOrIP);//For writing Remote Server Name in PDF Report -- Added by TCX team
		}
		
	}

	//TODO: Merging of 'SInteractive' contructors and 'InitiateDriver' (later to be removed) is under pilot mode
	private void InitiateDriver(String sBrowserType, URL objHostURL) throws Exception{		

		  //KillDriver(true);
		  if((sBrowserType==null)||sBrowserType.trim().equals("")){
			  sBrowserType = "IE"; //Didn't prefer FIREFOX as IE comes with Windows and is predominant
		  }
		  strBrowserType = Utility.RegExpExtract(sBrowserType, "[A-z]*").toUpperCase().trim();
		  strMobileEmulationType = Utility.RegExpExtract(sBrowserType, ".*\\.(.*)").trim();
		  
		  
		  if(strBrowserType.equals("IE")) {
			  objCapability = DesiredCapabilities.internetExplorer(); 
			  objCapability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
			  objCapability.setCapability("requireWindowFocus", true); //true: takes away mouse and focus on elements. native app events work and text fields recognize typing. not consistent on automation side.
			  //objCapability.setCapability("enablePersistentHover", false); //false: moderately fast, not as fast though. text field events fired and app recognizes. not consistent on automation side.
			  //objCapability.setCapability("nativeEvents", false); //false: makes it fast and consistent. however application fails consistently on certain text fields. type event unrecognized.
			  //NOTE: Specific to IE, there is no property as of now to have consistency, speed and validity. Nor can we switch modes during runtime.
			  //NOTE: comparatively better option is enablePersistentHover=false among all.

			  if(objHostURL==null){//prefer local webdriver to grid for performance reasons
				  String decoded = URLDecoder.decode(SInteractive.class.getResource("/drivers/IEDriverServer.exe").getPath(), "UTF-8");
				  System.setProperty("webdriver.ie.driver", decoded);				  
				  try{
					objWebDriver = new InternetExplorerDriver(objCapability);	
				  }
				  catch(Exception e){
				  	throw new Exception("Aborted: Please set browser zoom to 100%");
				  }
				  objAugmentedDriver = objWebDriver; //no augmentation required for local webdriver
			  }
			  else{
				  try{
					  objWebDriver = new RemoteWebDriver(objHostURL,objCapability);
				  }
				  catch(Exception e){
					 throw new Exception("Aborted: Please set browser zoom to 100%");
				  }
				  objAugmentedDriver = new Augmenter().augment(objWebDriver); //use only for for remote/RC initiation
			  }
		  }
		  else if(strBrowserType.equals("FIREFOX")) {
			  if(objHostURL==null){//prefer local webdriver to grid for performance reasons
				  String decoded = URLDecoder.decode(SInteractive.class.getResource("/drivers/geckodriver.exe").getPath(), "UTF-8");
				  System.setProperty("webdriver.gecko.driver", decoded);
				  objWebDriver = new FirefoxDriver();
				  objAugmentedDriver = objWebDriver;
			  }
			  else{
				  objCapability = DesiredCapabilities.firefox();
				  objWebDriver = new RemoteWebDriver(objHostURL,objCapability);
				  objAugmentedDriver = new Augmenter().augment(objWebDriver); //use only for for remote/RC initiation
			  }			  
		  }
		  //Added by TCX team
		  else if(strBrowserType.equals("EDGE")) {
			  if(objHostURL==null){//prefer local webdriver to grid for performance reasons
				  String decoded = URLDecoder.decode(SInteractive.class.getResource("/drivers/MicrosoftWebDriver.exe").getPath(), "UTF-8");
				  System.setProperty("webdriver.edge.driver", decoded);
				  objWebDriver = new EdgeDriver();
				  objAugmentedDriver = objWebDriver;
			  }
			  else{
				  objCapability = DesiredCapabilities.edge();
				  objWebDriver = new RemoteWebDriver(objHostURL,objCapability);
				  objAugmentedDriver = new Augmenter().augment(objWebDriver); //use only for for remote/RC initiation
			  }			  
		  }
		  
		  
		  else if(strBrowserType.equals("CHROME")) {
			  if(strMobileEmulationType.equals("")){
				  if(objHostURL==null){ //prefer local webdriver to grid for performance reasons
					  String decoded = URLDecoder.decode(SInteractive.class.getResource("/drivers/chromedriver.exe").getPath(), "UTF-8");
					  System.setProperty("webdriver.chrome.driver", decoded);
					  objWebDriver = new ChromeDriver();
					  objAugmentedDriver = objWebDriver; //no augmentation required for local webdriver 
				  }
				  else{
					  objCapability = DesiredCapabilities.chrome();
					  objWebDriver = new RemoteWebDriver(objHostURL,objCapability);
					  objAugmentedDriver = new Augmenter().augment(objWebDriver); //use only for for remote/RC initiation
				  }				  
			  }
			  else {
				  HashMap<String, String> mobileEmulation = new HashMap<String, String>();
				  mobileEmulation.put("deviceName", strMobileEmulationType); //"Samsung Galaxy S4", "Apple iPad"
				  HashMap<String, Object> chromeOptions = new HashMap<String, Object>();
			      chromeOptions.put("mobileEmulation", mobileEmulation);			      
				  objCapability = DesiredCapabilities.chrome();
				  objCapability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);			  
				  if(objHostURL==null){ //prefer local webdriver to grid for performance reasons
					  String decoded = URLDecoder.decode(SInteractive.class.getResource("/drivers/chromedriver.exe").getPath(), "UTF-8");
					  System.setProperty("webdriver.chrome.driver", decoded);				  
					  objWebDriver = new ChromeDriver(objCapability);
					  objAugmentedDriver = objWebDriver; //no augmentation required for local webdriver 
				  }
				  else{
					  objWebDriver = new RemoteWebDriver(objHostURL,objCapability);
					  objAugmentedDriver = new Augmenter().augment(objWebDriver); //use only for for remote/RC initiation
				  }
				  //SetBrowserSizeToMatchClientSize();
			  }
		  }
		  else if(strBrowserType.equals("SAFARI")) {
			  if(objHostURL==null){//prefer local webdriver to grid for performance reasons
				  objWebDriver = new  SafariDriver();
				  objAugmentedDriver = objWebDriver; //no augmentation required for local webdriver
			  }
			  else{
				  objCapability = DesiredCapabilities.safari();
				  objCapability.setPlatform(Platform.ANY);
				  objWebDriver = new RemoteWebDriver(objHostURL,objCapability);
				  objAugmentedDriver = new Augmenter().augment(objWebDriver);				  
			  }
		  }
		  else if(strBrowserType.equals("OPERA")) {
			  objCapability = new DesiredCapabilities(); //DesiredCapabilities.opera(); //
			  if(objHostURL==null){//prefer local webdriver to grid for performance reasons	
				  OperaOptions operaOptions = new OperaOptions();
				  operaOptions.setBinary(System.getenv("LOCALAPPDATA") + "\\Programs\\Opera\\launcher.exe"); //It needs opera installation location
				  objCapability.setCapability(OperaOptions.CAPABILITY, operaOptions);
				  String decoded = URLDecoder.decode(SInteractive.class.getResource("/drivers/operadriver.exe").getPath(), "UTF-8");
				  System.setProperty("webdriver.opera.driver", decoded);				  
				  objWebDriver = new OperaDriver(objCapability);
				  objAugmentedDriver = objWebDriver; //no augmentation required for local webdriver
			  }
			  else{				  
				  objWebDriver = new RemoteWebDriver(objHostURL,objCapability);
				  objAugmentedDriver = new Augmenter().augment(objWebDriver); //use only for for remote/RC initiation
			  }
		  }
		  else {			  
			  Assert.assertFalse("Unimplemented Browser Type specified : " + strBrowserType, true);
			  throw new Exception("Unimplemented Browser Type specified : " + strBrowserType);
		  }
		  if(boolGlobalSettingReportsEnabled){			  
			  Utility.CreateFolder(strReportDirectory, false);
			  objReporter = new CustomReporter(strReportDetails, strReportDirectory+"\\"+strReportFileName);
			  objReporter.boolDeletePNGs = true; 
			  objReporter.nImageScalePercentage = 50; //This will reduce size of PDF report at a cost of image quality. 60% is a readable quality.
			  objReporter.AddToSummary("Browser", strMobileEmulationType.equals("")?strBrowserType:strBrowserType + "." + strMobileEmulationType);
			  objWebDriver = new EventFiringWebDriver(objWebDriver).register(new CustomEventListener(this));
		  }
		  SetSynchWaitTime(this.nSynchWaitInMillis);
		  objAction = new Actions(objWebDriver);
		  objElement = new CustomElement(this);
		  
		  objThread = Thread.currentThread();
		  if(boolGlobalSystemTrayEnabled){
			  objSystemTrayControl = new SystemTrayMonitor(this);
		  }
		  
		  if(boolGlobalDebuggerEnabled){
			  objDebugger=new Debugger(this); 
		  }
		  
		  //Setting SInteractive Driver instance in Debugger
		  //objDebugger=new Debugger();  
		  //fnEnableDebug(this); 

		  /*Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
			    public void uncaughtException(Thread th, Throwable ex) {
			        System.out.println("Uncaught exception: " + ex.getMessage());
			    }
		  };
		  objThread.setUncaughtExceptionHandler(h);*/
	}
	
	
	
	private void InitiateDriver(String sBrowserType) throws Exception {
		InitiateDriver(sBrowserType, (URL)null); //Casting needed to avoid ambiguity
	}
	
	private void InitiateDriver(String sBrowserType, String sHostNameOrIP) throws Exception {
		URL objHostURL;  
		if((sHostNameOrIP==null)||sHostNameOrIP.trim().equals("")||sHostNameOrIP.trim().toUpperCase().equals("LOCALHOST")){
			  objHostURL = null; //Run locally using WebDriver. And not using Grid.
		}
		else {
			  objHostURL = new URL("http://" + sHostNameOrIP + ":4444/wd/hub");
		}
		InitiateDriver(sBrowserType, objHostURL); //Casting needed to avoid ambiguity
	}
	
	private void InitiateDriver(ITestContext context) throws Exception { //For TestNG users
		  int nTestIndex = 0, nCountTests=0;
		  java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  nTestIndex = context.getCurrentXmlTest().getIndex();
		  nCountTests = context.getSuite().getXmlSuite().getTests().size();
		  String sBrowserType = context.getCurrentXmlTest().getParameter("browser_type");		  
		  String sHost = context.getCurrentXmlTest().getParameter("host");		  
		  Thread.sleep(nThreadRampUpInMillis * nTestIndex);
		  
		  //below are significant for demo purpose only when it comes to parallel tests
		  int nWidthNeeded = 1000; //or (screenSize.width/2)
		  int nHeightNeeded = 700; //screenSize.height-40;
		  if(nWidthNeeded>screenSize.width){
			  nWidthNeeded = screenSize.width;
		  }
		  if(nHeightNeeded>(screenSize.height-40)){
			  nHeightNeeded = screenSize.height-40;
		  }		  
		  int nXpos=0, nYpos = 0;
		  int nWidthBlankArea=screenSize.width - nWidthNeeded;
		  int nHeightBlankArea=screenSize.height - nHeightNeeded - 40;
		  if(nTestIndex>0){
			  nXpos = nTestIndex * (nWidthBlankArea/(nCountTests-1));
			  nYpos = nTestIndex * (nHeightBlankArea/(nCountTests-1));
		  }
		  objBrowserSize = new Dimension(nWidthNeeded,nHeightNeeded);
		  objBrowserLocation = new Point(nXpos,nYpos);
		  
		  //Below is for reporting needs
		  String sClassName = context.getCurrentXmlTest().getClass().getSimpleName();		
		  strReportFileName = "TestReport_"+ Utility.GetUniqueTestNameIdentifier(context)+"_"+sHost+"_"+sBrowserType+"_"+(nTestIndex+1)+"of"+nCountTests+".pdf";
		  strReportDetails = "Class Name=" + sClassName + ", Host="+sHost + ",Browser=" + sBrowserType + ", Iteration=" + (nTestIndex+1) + " of " + nCountTests;
		  
		  InitiateDriver(sBrowserType, sHost); //Casting needed to avoid ambiguity
		  ResizeBrowser(objBrowserSize);
		  MoveBrowser(objBrowserLocation);
		  Thread.sleep(2 * (nCountTests - nTestIndex)*nThreadRampUpInMillis);

	}

	
	//Destructor of SInteractive, java finalize() is not suitable to the purpose as it gets called only during System GC
	public void Finalize(boolean boolCloseBrowser) throws Exception{
		if(objReporter!=null){//Reporter to be finalized prior to driver. Nothing gets logged after this point.
			objReporter.Finalize();
			objReporter = null;
		}
		if(objWebDriver!=null){
			if(boolCloseBrowser) {
				if(!objDebugger.boolIsLaunched){ //never close a browser if Debugger is running
					objWebDriver.close();
					objWebDriver.quit();
					objWebDriver = null;//Decided to not put this outside. Designed to call Finalize(FALSE) many times and finally do a Finalize(TRUE)
				}
			}
		}
	}
	
	public void Finalize() throws Exception{ 
		Finalize(true);
	}
	
	@Override
	public void Finalize(String strFinalStatus) throws Exception{
		System.out.println("SInteractive.Finalize(...) called with " + strFinalStatus + " string parameter");
		switch(strFinalStatus){
			case "TERMINATED":
				System.out.println(boolExceptionDetected);
				if(!boolExceptionDetected){
					Log("Final page", "Snapshot of final page", TakeSnapShot()); 
					Finalize(true);
				}
				else{
					//No need for objReporter.ValidateTest(false). Taken care in the exception event handler
					Finalize(false); //BatchRunner will not show link to report if it's not finalized in few seconds
					objDebugger.Launch();
				}
				break;
			case "WAITING":
				objReporter.ValidateTest(false);
				Log("USER ABORT[FONT:Italic]", "User aborted the test by clicking on the system tray, stop button", null);
				Finalize(false);
				objDebugger.Launch();
				break;
			case "INTERRUPTED":
				objReporter.ValidateTest(false);
				Log("USER ABORT[FONT:Italic]", "User aborted the test by clicking on the system tray, stop button", null);
				Finalize(false);
				objDebugger.Launch();
				break;
			case "TIMED_WAITING":	//This block is getting more &
				//The state TIMED_WAITING can occur when testers write a Thread.sleep() in their test case level. So it's not reliable to detect a test case exception.
				//System.out.println(strErrorTraceLog);
				String strErrorTraceLog = "Attempted element: " + strLastAttemptedIdentification + "\r\n" + Utility.GetStackTrace(objThread);
				objReporter.ValidateTest(false, "UNEXPECTED ERROR[FONT:Italic]. " + strErrorTraceLog);
				Finalize(false); //BatchRunner will not show link to report if it's not finalized in few seconds
				objDebugger.Launch();
				break;
			default:
				Log("Finalized (" + strFinalStatus + ")", "User finalized the report. Snapshot of final page", TakeSnapShot());
				Finalize(true);
				break;
		}
	}
	
	public class CustomEventListener implements WebDriverEventListener { //private use by SetReporter() function
		SInteractive parent = null;
		public CustomEventListener(SInteractive sinteractive){ //Same way there can be different constructors for purposes other than reporting.
			this.parent = sinteractive;
		}

		@Override
		public void afterClickOn(WebElement arg0, WebDriver arg1) {
			//System.out.println("Reached - afterClickOn");
		}

		@Override
		public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
			//System.out.println("Reached - afterFindBy");
		}

		@Override
		public void afterNavigateBack(WebDriver arg0) {
			//System.out.println("Reached - afterNavigateBack");
		}

		@Override
		public void afterNavigateForward(WebDriver arg0) {
			//System.out.println("Reached - afterNavigateForward");
		}

		@Override
		public void afterNavigateTo(String arg0, WebDriver arg1) {
			//System.out.println("Reached - afterNavigateTo");
			try {
				parent.objReporter.Log("Browser nagivation", "Browser navigation to url: " + arg0, TakeSnapShot());
			} catch (Exception e) {

			}
		}

		@Override
		public void afterScript(String arg0, WebDriver arg1) {
			//System.out.println("Reached - afterScript");
		}

		//@Override
		public void beforeChangeValueOf(WebElement arg0, WebDriver arg1){
			//System.out.println("Reached - beforeChangeValueOf");			
			try {
				String strQualifiedName = GetElementDescription(arg0);
				parent.objReporter.Log("Change value " + strQualifiedName, "Next step is to change value of the webelement " + strQualifiedName, TakeSnapShot(arg0));
			} catch (Exception e) {

			}
		}

		@Override
		public void beforeClickOn(WebElement arg0, WebDriver arg1) {
			//System.out.println("Reached - beforeClickOn");			
			try {
				String strQualifiedName = GetElementDescription(arg0);				
				parent.objReporter.Log("Click " + strQualifiedName, "Next step is to click on the webelement " + strQualifiedName, TakeSnapShot(arg0));
			} 
			catch (Exception e) {

			}
		}

		@Override
		public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
			//System.out.println("Reached - beforeFindBy");
			strLastAttemptedIdentification = arg0.toString(); //To support a comprehensive exception logging
		}

		@Override
		public void beforeNavigateBack(WebDriver arg0) {
			//System.out.println("Reached - beforeNavigateBack");
		}

		@Override
		public void beforeNavigateForward(WebDriver arg0) {
			//System.out.println("Reached - beforeNavigateForward");
		}

		@Override
		public void beforeNavigateTo(String arg0, WebDriver arg1) {
			//System.out.println("Reached - beforeNavigateTo");
		}

		@Override
		public void beforeScript(String arg0, WebDriver arg1) {
			//System.out.println("Reached - beforeScript");
		}

		@Override
		public void onException(Throwable arg0, WebDriver arg1) {
			//System.out.println("Reached - onException");
			try {
				boolExceptionDetected = true;
				objReporter.ValidateTest(false, "Unexpected error detected.[FONT:Italic] An error was detected by the custom event listener\r\n" + arg0.getMessage(), TakeSnapShot());
			} 
			catch (Exception e) {

			}
		}

		@Override
		public void afterNavigateRefresh(WebDriver arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeNavigateRefresh(WebDriver arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterChangeValueOf(WebElement arg0, WebDriver arg1,
				CharSequence[] arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeChangeValueOf(WebElement arg0, WebDriver arg1,
				CharSequence[] arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterAlertAccept(WebDriver arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterAlertDismiss(WebDriver arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeAlertAccept(WebDriver arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeAlertDismiss(WebDriver arg0) {
			// TODO Auto-generated method stub
			
		}		
	}
	
	public class CustomElement implements WebElement{
		private SInteractive parent = null;
		private WebElement nativeElement = null;
		public CustomElement (SInteractive sinteractive){ 
			this.parent = sinteractive;
		}
		private void SetNativeElement (WebElement nativeElement){ 
			this.nativeElement = nativeElement;
		}
		
		public void ufxFocus(){
            switch(parent.strBrowserType){
                  case "SAFARI":
                         //Need separate implementation
                         break;
                  case "FIREFOX":
                         //Gecko driver is not supporting moveToElement() at this point (Apr 27, 2017)
                         break;
                  default:
                         parent.objAction.moveToElement(this.nativeElement).perform();
                         
            }
		}

		public void ufxScrollElementToView(){
			try{
				((JavascriptExecutor) objWebDriver).executeScript("arguments[0].scrollIntoView(true);", this.nativeElement);
				Coordinates coordinates = ((Locatable)this.nativeElement).getCoordinates();
			    coordinates.inViewPort();
			}
			catch(Exception e){
				
			}
		}
		
		public void ufxClick(){
			if (parent.strBrowserType.equals("IE")) { 
				//native Click() was clicking erroneous location in IE when links displayed within a frame
				this.nativeElement.sendKeys(Keys.ENTER);			
			}
			else {
				this.nativeElement.click();
			}
		}
		

		public void ufxHighlightElement(){
			ufxHighlightElement(3, 200); //Blink 3 times and 200 millseconds apart
		}
		
		public void ufxHighlightElement(int iLoopingCount,int iIntervel){
			try{				
				for(int i=1;i<=iLoopingCount;i++)
				{
					String getAttributeStyle= this.nativeElement.getAttribute("style");
					JavascriptExecutor js = (JavascriptExecutor) objAugmentedDriver;			
			        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", this.nativeElement, "color: red; border: 2px solid red;");
					Thread.sleep(iIntervel);
			        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", this.nativeElement, getAttributeStyle);
			        Thread.sleep(iIntervel);
				}
			}
			catch (Exception e) {
				System.out.println("Exception while element highlight ");
			}
		}
		
		//Set specif attribute value
		public void ufxSetAttribute(String sAttribute, String sValue){
			switch (sAttribute){
			case "value":
				((JavascriptExecutor) objWebDriver).executeScript("arguments[0]." + sAttribute + "='" + sValue + "' ;", this.nativeElement);
				break;
			case "click()":
				((JavascriptExecutor) objWebDriver).executeScript("arguments[0]." + sAttribute+";", this.nativeElement);
				break;
			}					
		}
		//Return the number of items in a dropdown
		public int ufxGetItemCountInDropdown(){
			Select objSelect=new Select(this.nativeElement);
			return objSelect.getOptions().size();
		}
		//Checks if the given item present in drop down
		public boolean ufxIsItemExistInDropdown(String sItem){
			return ufxIsItemExistInDropdown(sItem,false);
		}
		//Checks if the given item present in drop down, supports regular expression
		public boolean ufxIsItemExistInDropdown(String sItem, boolean bRegExpMatch){
			Select objSelect=new Select(this.nativeElement);
			
			Iterator<WebElement> objListOptions = objSelect.getOptions().iterator();
			while(objListOptions.hasNext()){
				String strListItem = objListOptions.next().getAttribute("outerText").trim();
				if(bRegExpMatch){
					if (strListItem.matches(sItem)) return true;
				}
				else
					if (strListItem.contentEquals(sItem)) return true;
			}
			return false;
			
		}
		//Returns the selected item in a drop down
		public CustomElement ufxGetSelectedItemInDropdown(){
			Select objSelect=new Select(this.nativeElement);
			
			if (objSelect.getOptions().size()==0)
				return null;
			else{
				CustomElement objElement=new CustomElement(parent);
				objElement.SetNativeElement(objSelect.getOptions().get(0));
				return objElement;
			}
		}
		//Checks if the given item is selected in drop down
		public boolean ufxIsItemSelectedInDropdown(String sItem){
			return ufxIsItemExistInDropdown(sItem,false);
		}
		//Checks if the given item is selected in drop down, supports regular expression
		public boolean ufxIsItemSelectedInDropdown(String sItem, boolean bRegExpMatch){
			Select objSelect=new Select(this.nativeElement);
			
			Iterator<WebElement> objListOptions = objSelect.getOptions().iterator();
			while(objListOptions.hasNext()){
				String strListItem = objListOptions.next().getAttribute("outerText").trim();
				if(bRegExpMatch){
					if (strListItem.matches(sItem)) return true;
				}
				else
					if (strListItem.contentEquals(sItem)) return true;
			}
			return false;
			
		}
		//Select item from drop down 
		public void ufxSelectFromDropdown(String strItemToSelect) throws Exception{
			ufxSelectFromDropdown(strItemToSelect, false);
		}
		//Select item from drop down and fire tab out event - 
		public void ufxSelectFromDropdown(String strItemToSelect, boolean boolFireTabOutEvent) throws Exception {
			Select objSelect=new Select(this.nativeElement);
			try{
				objSelect.selectByVisibleText(strItemToSelect);
				if(boolFireTabOutEvent){
					this.nativeElement.sendKeys(Keys.TAB);
				}
			}
			catch (Exception e){
				System.out.println("\n");
				Iterator<WebElement> objListOptions = objSelect.getOptions().iterator();
				String strListItem = "";
				while(objListOptions.hasNext()){
					strListItem = objListOptions.next().getAttribute("outerText");
					System.out.println("OPTION SELECT ERROR:"+(strItemToSelect==strListItem) + ":" + strItemToSelect + "==" + strListItem);
				}
				try {
					throw new Exception("Dropdown select/synch issue:"+strItemToSelect);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			objSelect=null;
		}
		
		public void ufxSelectCheckbox(boolean boolCheck) throws Exception{		
			String strQualifiedName = GetElementDescription(this.nativeElement);
			boolean boolFound = false;
			boolean boolCurrentState = false;
			if (this.nativeElement.getTagName().equals("checkbox")){
				boolCurrentState = this.nativeElement.isSelected();
				boolFound = true;
			}
			else{
				//Sometimes a checkbox can be embedded within a SPAN element. Unfortunately that can be hidden as well (implicitWait fails on the Element)
				List<WebElement> childElements = this.nativeElement.findElements(By.xpath(".//*"));
				for(WebElement objElement : childElements){
					if(objElement.getAttribute("type").equals("checkbox")){
						strQualifiedName = strQualifiedName + "." + GetElementDescription(objElement);
						boolCurrentState = objElement.isSelected();
						boolFound = true;
						break;
					}
				}
				childElements = null;				
			}
			//System.out.println(strQualifiedName + ":" + boolCurrentState);
			if(!boolFound){
				parent.objReporter.Log("Warning - checkbox selection", "There's some technical difficulty in determining the current state of the checkbox (" + strQualifiedName + ")", TakeSnapShot(this.nativeElement));
				parent.objAction.moveToElement(this.nativeElement).click();
			}
			else{
				parent.objReporter.Log(boolCheck?"Select checkbox":"De-Select checkbox", "Next step is to " + (boolCheck?"Select the checkbox ":"DeSelect the checkbox ") + strQualifiedName, TakeSnapShot(this.nativeElement));
				if((!boolCurrentState && boolCheck)||(boolCurrentState && !boolCheck)){
					parent.objAction.moveToElement(this.nativeElement).click();
				}//else you don't have anything to do, the checkbox is in desired state already
			}			
		}
	
		@Override
		public void clear() {
			// TODO Auto-generated method stub
			this.nativeElement.clear();
		}
	
		@Override
		public void click() {
			try{
				//Expected condition added by Anil (Added since the below code is removed from CustomElement Element(By ByID, int nOrdinalID)  method) 
				objSyncWait.until(ExpectedConditions.elementToBeClickable(this.nativeElement)); //[GR: Very good. Thanks]
				this.nativeElement.click(); 
				//Seen issues when clicking checkboxes within a SPAN element. Use 'ufxSelectCheckbox()' instead
			}
			catch(Exception ex){
				System.out.println("Exception error on element click\r\n" + ex.getMessage());
				if(this.nativeElement.getTagName().equals("span")){
					try {
						Log("Element click failed", "Element click failed (likely to be SPAN element click issue)\r\n" + ex.getMessage());
						objReporter.ValidateTest(false);
					} 
					catch (Exception e) {
						System.out.println("There is an unexpected exception in WebElement.click() override function. Please check.");
					}
				}
				else{
					try {
						Log("Element click failed", "Element click failed for some unknown reason\r\n" + ex.getMessage());
						objReporter.ValidateTest(false);
					} 
					catch (Exception e) {
						
					}
				}
			}
		}
	
		@Override
		public WebElement findElement(By arg0) {
			return this.nativeElement.findElement(arg0);
		}
	
		@Override
		public List<WebElement> findElements(By arg0) {
			return this.nativeElement.findElements(arg0);
		}
	
		@Override
		public String getAttribute(String arg0) {
			return this.nativeElement.getAttribute(arg0);
		}
	
		@Override
		public String getCssValue(String arg0) {
			return this.nativeElement.getCssValue(arg0);
		}
	
		@Override
		public Point getLocation() {
			return this.nativeElement.getLocation();
		}
	
		@Override
		public Dimension getSize() {
			return this.nativeElement.getSize();
		}
	
		@Override
		public String getTagName() {
			return this.nativeElement.getTagName();
		}
	
		@Override
		public String getText() {
			return this.nativeElement.getText();
		}
	
		@Override
		public boolean isDisplayed() {
			return this.nativeElement.isDisplayed();
		}
	
		@Override
		public boolean isEnabled() {
			return this.nativeElement.isEnabled();
		}
	
		@Override
		public boolean isSelected() {
			return this.nativeElement.isSelected();
		}
	
		@Override
		public void sendKeys(CharSequence... arg0) {
			this.nativeElement.clear();
			this.nativeElement.sendKeys(arg0);
		}
	
		@Override
		public void submit() {
			this.nativeElement.submit();
		}
		
		@Override
		public Rectangle getRect(){
			return this.nativeElement.getRect();
		}

		//Added by TCX team
		public void sendKeys(String arg0, boolean sListBox) {
			
			this.nativeElement.sendKeys(arg0);
		}
		
		@Override
		public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
			return this.nativeElement.getScreenshotAs(arg0);
		}
		
		//Returns the name of the webelement based on its attributes
		public String getElementName(){
			try{
			 return  !nativeElement.getAttribute("name").isEmpty()?nativeElement.getAttribute("name"):!nativeElement.getAttribute("id").isEmpty()?nativeElement.getAttribute("id"):!nativeElement.getText().isEmpty()?nativeElement.getText():!nativeElement.getAttribute("alt").isEmpty()?nativeElement.getAttribute("alt"):"Unnamed";
			}
			catch(Exception e){
				return "NoName";
			}
		}
	}
	
	public CustomElement Element(By ByID, int nOrdinalID) throws Exception{
		WaitForPageLoad(); //this is not proven to work
		//Think about ajax controls too: ExpectedConditions.presenceOfElementLocated//elementToBeClickable		
		/* Pappan's old code - There are situations where we need to check if element is invisible, in this case this code is not sufficient
		 * Also, if elemenet is not presently visilble on screen, this code will not find the element (in perspective of mobile emulation)
		if(nOrdinalID>0){
			objElement.SetNativeElement(objSyncWait.until(ExpectedConditions.elementToBeClickable(objWebDriver.findElements(ByID).get(nOrdinalID))));
		}
		else{
			objElement.SetNativeElement(objSyncWait.until(ExpectedConditions.elementToBeClickable(ByID)));
		}
		*/
		//New code by Anil, To support wait for element until the element is clickable, Webdrivewait is added under click method in CustomElement class
		//Commented below by Pappan since the polling wait was catching repeated exception events, in event listener, throwing repeated error logs in the reporter
		//objSyncWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ByID)); 
		if(nOrdinalID>0){
			objElement.SetNativeElement(objWebDriver.findElements(ByID).get(nOrdinalID));
		}
		else{
			objElement.SetNativeElement(objWebDriver.findElement(ByID));
		}
		if(boolBringElementToFocus){
			objElement.ufxFocus();
		}
		return objElement;
	}
	
	public CustomElement Element(By ByID) throws Exception{
		return Element(ByID, 0);
	}
	
	public CustomElement Element(By ByID, String sVisibleTextRegExpMatch) throws Exception{
		List <WebElement> listWebElements = objWebDriver.findElements(ByID);
		String sExtracted = "";
		for(int nIndex=0; nIndex< listWebElements.size(); nIndex++){
			sExtracted = listWebElements.get(nIndex).getAttribute("outerText").trim();
			if (Pattern.matches(sVisibleTextRegExpMatch,sExtracted )){
				objElement.SetNativeElement(listWebElements.get(nIndex));
				return objElement;
			}
		}		
		//return null;	
		throw new Exception("Element multi-match identification failed: " + sVisibleTextRegExpMatch);
	}
	
	public void WaitForPageLoad() {	//Trial only. This is not proven to work, but we need this badly
		
		/*(new WebDriverWait(objAugmentedDriver, 60,1000)).until(new ExpectedCondition<Boolean>() {
		      public Boolean apply(WebDriver d) {
		        return (((org.openqa.selenium.JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
		      }
		    }
		);*/
		
		/*
		System.out.println("=============");
		int n = 1;
		String strReadyState = "unknown";
		while (!strReadyState.equals("complete")){
			JavascriptExecutor js = (JavascriptExecutor) objAugmentedDriver;
	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", objElement, "color: red; border: 2px solid red;");

			String script = "return document.readyState;";
			strReadyState = (String) js.executeScript(script);
			System.out.println("Eval WaitForPageLoad(" + n + "): " + strReadyState);
			if (n++ > 10) {
				break;
			}
		}
		*/
		
	}

	public void SetSynchWaitTime(int nSynchWaitInMillis) throws Exception {
	  //Neither implicit nor explicit wait implementation proved efficient. We need a pageloadsync() badly
	  this.nSynchWaitInMillis = nSynchWaitInMillis;
	  this.objSyncWait = null; //delete existing object if already created	  
	  this.objSyncWait = new WebDriverWait(this.objWebDriver,(this.nSynchWaitInMillis/1000>0? this.nSynchWaitInMillis/1000:1));
	  this.objSyncWait.pollingEvery(this.nSynchResponsivenessInMillis, TimeUnit.MILLISECONDS);
	}
	
	public int GetSynchWaitTimeInMillis(){
		return this.nSynchWaitInMillis;
	}
	
	public boolean ElementExists(By byIdentifier, int nTimeOut) throws Exception{
	  int nAttemptsRemaining = nTimeOut/(this.nSynchResponsivenessInMillis);	  
	  while(this.objWebDriver.findElements(byIdentifier).size() == 0 && --nAttemptsRemaining>0){
		  Thread.sleep(this.nSynchResponsivenessInMillis);
	  }
	  return (this.objWebDriver.findElements(byIdentifier).size()>0);
	}
	

	public boolean ElementExists(By byIdentifier) throws Exception{
	  return ElementExists(byIdentifier, this.nSynchWaitInMillis);
	}
	
	public boolean ElementDoesNotExists(By byIdentifier, int nTimeOut) throws Exception{
		int nAttemptsRemaining = nTimeOut/(this.nSynchResponsivenessInMillis);
		while(this.objWebDriver.findElements(byIdentifier).size() > 0 && --nAttemptsRemaining>0){
			Thread.sleep(this.nSynchResponsivenessInMillis);
		}
		return (this.objWebDriver.findElements(byIdentifier).size()==0);
	}
	
	public boolean ElementDoesNotExists(By byIdentifier) throws Exception{
	  return ElementDoesNotExists(byIdentifier, this.nSynchWaitInMillis);
	}
	
	//Added by TCX team
	public boolean fnCheckElementVisibility(String sVisible,By sElement) throws Exception{
		boolean sStatus = true;              
		switch (sVisible){
			case "Visible":
				String sCssVal=objWebDriver.findElement(sElement).getCssValue("display");
				System.out.println("Css value: "+ sCssVal);
				if (sCssVal.equalsIgnoreCase("none"))
					sStatus=false;
				else
					sStatus = true;
					break;
			case "NotVisible":
				if (! ElementExists(sElement))
					sStatus=true;
				else{
					sCssVal=objWebDriver.findElement(sElement).getCssValue("display");
					if (sCssVal.equalsIgnoreCase("none"))
						sStatus=true;
				}
				break;
		}
		return sStatus;
    }
	
	private String GetElementDescriptionDebug(WebElement objElement){ //Designed to be private since this is mostly to be used internally, also arg type is WebElement and not By object which is not end user friendly
		String strType = objElement.getAttribute("type"); //objElement.getTagName() gets only tag names like 'input' not 'button' or 'textbox'
		if (strType==null){
			strType = objElement.getTagName() + " element";
		}
		strType = "[" + strType + "]";
		String strDebug = "";
		String strProperty = objElement.getText();
		strDebug = strDebug + "getText:" + strProperty;
		
		final String[] arrPropertyAttempts = {"value","id","name","alt"};//Preferred order is: "value","id","name","alt"
		for (String strPropertyAttempt:arrPropertyAttempts){
			strProperty = objElement.getAttribute(strPropertyAttempt);
			strDebug = strDebug + "," + strPropertyAttempt + ":" + strProperty;
		}
		return strType + " " + strDebug;
	}	
	private String GetElementDescription(WebElement objElement){ //Designed to be private since this is mostly to be used internally, also arg type is WebElement and not By object which is not end user friendly
		try{
			final String strGenericType = "web"; //generic div or span element
			String strType = objElement.getAttribute("type"); //objElement.getTagName() gets only tag names like 'input' not 'button' or 'textbox'
			strType = (strType!=null)?!strType.isEmpty()?strType:strGenericType:strGenericType;		
			final HashMap<String, String[]> lstPropPreference = new HashMap<String, String[]>() {{
			    put("button",new String[]{"value","id","name","getText","alt"});
			    put("text",new String[]{"id","name","value","getText","alt"});
			    put(strGenericType,new String[]{"getText","id","name","value","alt"});//generic div or span element
			}};
			final String[] arrDefaultPreference = new String[]{"id","name","value","getText","alt"};
			final String[] arrPropertyAttempts = lstPropPreference.containsKey(strType)?lstPropPreference.get(strType):arrDefaultPreference;
			String strProperty;
			for (String strPropertyAttempt:arrPropertyAttempts){
				strProperty = strPropertyAttempt.equals("getText")?objElement.getText():objElement.getAttribute(strPropertyAttempt);
				if(strProperty!=null){
					if(!strProperty.isEmpty()){
						return strProperty + " (" + strType + " element)";
					}
				}
			}
			return strLastAttemptedIdentification;
		}
		catch(Exception e){
			return "WebElement";
		}
	}
	
	public void Log(String sStepDesc) throws Exception{
		String sPathIMG = TakeSnapShot(null);
		Log(sStepDesc, sStepDesc, sPathIMG);
	}
	
	public void Log(String sStepName, String sStepDesc) throws Exception{
		String sPathIMG = TakeSnapShot(null);
		Log(sStepName, sStepDesc, sPathIMG);
	}
	
	public void Log(String sStepName, String sStepDesc, String sPathIMG) throws Exception{
		if(objReporter!=null){		
			objReporter.Log(sStepName+"[FONT:BOLD]", sStepDesc, sPathIMG);
		}
		else {
			System.out.println("Warning: Log() function was called after finalize, or before initializing the reporter. The call is ignored.");
		}
	}
	
	public void Log(String sStepName, By byIdentifier) throws Exception{//Left to right, Element() will be evaluated first
		Log(sStepName, "Snapshot capture of " + GetElementDescription(Element(byIdentifier)), TakeSnapShot(this.objElement.nativeElement));
	}
	
	public String GetReportFilePath(){		
		return strReportDirectory+"\\" + strReportFileName;
	}
	
	public String GetReportFileName(){
		return strReportFileName;
	}

	public boolean Navigate(String sURL) throws Exception{		
		  objWebDriver.get(sURL);
		  HandleCertError();
		  return true;
	}
	
	public void ResizeBrowser(Dimension objBrowserSize) {
		if (strBrowserType.equals("OPERA")) {
			//Need separate implementation
		}
		else {
			  objWebDriver.manage().window().setSize(objBrowserSize);			  
		}
	}
	
	//Added by TCX Team
	public void MaximizeBrowser() {		
		objWebDriver.manage().window().maximize();
	}
	
	public void MoveBrowser(Point objBrowserLocation) {			
		if (strBrowserType.equals("OPERA")) {
			//Need separate implementation
		}
		else {
			objWebDriver.manage().window().setPosition(objBrowserLocation); 
		}
	}
	
	public void SetBrowserSizeToMatchClientSize() throws Exception{
		Dimension objSize = objWebDriver.manage().window().getSize();
		WebElement objHtml = objWebDriver.findElement(By.tagName("html"));
		int nWidth = 1 + Integer.parseInt(objHtml.getAttribute("clientWidth"))/2;
		int nHeight = 65 + Integer.parseInt(objHtml.getAttribute("clientHeight"))/2;
		objWebDriver.manage().window().setSize(new Dimension(nWidth, nHeight));
	}
	
	public void HandleCertError(){
		  if(objWebDriver.getTitle().contains("Certificate Error")) {
			  if (strBrowserType.equals("IE")) {
				 objWebDriver.navigate().to("javascript:document.getElementById('overridelink').click()");
			  }
		  }
	}	

	public boolean ActivateFrame(By byIdentifier, int nTimeOut) throws Exception{
		String sWindowHandle = objWebDriver.getWindowHandle();
		objWebDriver = objWebDriver.switchTo().window(sWindowHandle);
		//objSyncWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(byIdentifier));
		//objWebDriver.switchTo().defaultContent();
		objWebDriver.switchTo().frame(Element(byIdentifier).nativeElement); 	
		return true;
	}
	//----------11-july-2017 -Abhi----------------------
	public void ExecuteJavaScript(String sScript) {
		JavascriptExecutor js = (JavascriptExecutor) objAugmentedDriver;
		js.executeScript(sScript);
	}
	public boolean ActivateFrame(By byIdentifier) throws Exception{
		return ActivateFrame(byIdentifier, this.nSynchWaitInMillis);
	}
	
	//Go back to parent frame
	public void ActivateFrame() {
		objWebDriver.switchTo().defaultContent();
	}
	//Check if Alert is displayed
	public boolean IsAlertDisplayed(){
		try{
			objSyncWait.until(ExpectedConditions.alertIsPresent());
			return true;
		}
		catch(Exception err){
			return false;
		}
	}
	//Return alert text, if no alert, then return blank
	public String GetAlertMessage(){
		try{
			Alert alert=objWebDriver.switchTo().alert();
			String sAlertText=alert.getText();
			return sAlertText;
		}
		catch(Exception err){
			return "";
		}
	}
	//Accept or dismiss alert based on the given value
	public boolean CloseAlert(boolean bAcceptAlert){
		try{
			Alert alert=objWebDriver.switchTo().alert();
			if(bAcceptAlert)
				alert.accept();
			else
				alert.dismiss();
			return true;
		}
		catch(Exception err){
			return false;
		}
	}
	// Return the capability object
	public DesiredCapabilities GetCapabilityObject(){
		return objCapability;
	}

	public String TakeSnapShot() throws Exception{
		return TakeSnapShot(null); //get the screen capture (no specific element to be highlighted)
	}
	
	private String TakeSnapShot(WebElement objWebElement) throws Exception{
		File objTempFile=null;
		try{
			String getAttributeStyle = ""; 
			if (strBrowserType.equals("FIREFOX")){
				objTempFile = ((TakesScreenshot) objAugmentedDriver).getScreenshotAs(OutputType.FILE);	
			}

			else if (strBrowserType!="FIREFOX") {
				if(objWebElement!=null){
					getAttributeStyle= objWebElement.getAttribute("style");
					JavascriptExecutor js = (JavascriptExecutor) objAugmentedDriver;			
			        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", objWebElement, "color: red; border: 2px solid red;");
				}
				objTempFile = ((TakesScreenshot) objAugmentedDriver).getScreenshotAs(OutputType.FILE);
				if(objWebElement!=null){
					JavascriptExecutor js = (JavascriptExecutor) objAugmentedDriver;
			        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", objWebElement, getAttributeStyle);
				}
			}	
				
				return objTempFile.getAbsolutePath();

		}
		catch (Exception e){
			return "(No fun with) ScreenShot Capture Error";
		}
		
		//Second style of implementation below. Element highlight position was inaccurate when elements are within iframes
		/*File objTempFile = ((TakesScreenshot) objAugmentedDriver).getScreenshotAs(OutputType.FILE);
		if(objWebElement!=null){
			BufferedImage oImage = ImageIO.read(objTempFile);
			Graphics oGraphics = oImage.getGraphics();
			oGraphics.setColor(Color.red);
			oGraphics.drawRect(objWebElement.getLocation().x, objWebElement.getLocation().y, objWebElement.getSize().getWidth(), objWebElement.getSize().getHeight());
	
			Object obj = ((JavascriptExecutor)objAugmentedDriver).executeScript("return arguments[0].getBoundingClientRect().left", objWebElement);
			int nLeft = Integer.parseInt(obj.toString());			
			obj = ((JavascriptExecutor)objAugmentedDriver).executeScript("return arguments[0].getBoundingClientRect().top", objWebElement);
			int nTop = Integer.parseInt(obj.toString());			
			oGraphics.setColor(Color.green);
			oGraphics.drawRect(nLeft, nTop, objWebElement.getSize().getWidth(), objWebElement.getSize().getHeight());
	
			ImageIO.write(oImage, "png", objTempFile);
			oImage = null;
			oGraphics = null;			
		}
		return objTempFile.getAbsolutePath();
		*/
	}
	
	//Added by TCX team
	public void ValidateTest(boolean bStatus, String sLogDescr) throws Exception{		
		ValidateTest(bStatus,false,sLogDescr);	
	}
	//Added by Anil - Add screenshot along with log message
	public void ValidateTest(boolean bStatus,boolean bCaptureScreenShot, String sLogDescr) throws Exception{		
		try{
			if (bCaptureScreenShot)
				objReporter.ValidateTest(bStatus, sLogDescr+ "[FONT:BOLD]",this.TakeSnapShot(null));
			else
				objReporter.ValidateTest(bStatus, sLogDescr+ "[FONT:BOLD]",null);
		}
		catch(Exception e){
			objReporter.ValidateTest(bStatus, sLogDescr + "[FONT:BOLD]");
		}
	}
	
	//Added by TCX team
	public String GetCurrentURL(){
		String Url = objWebDriver.getCurrentUrl();
		return Url;
	}
	//Added by Myblock
	//Create PDF Summary Report.
	
	

}
