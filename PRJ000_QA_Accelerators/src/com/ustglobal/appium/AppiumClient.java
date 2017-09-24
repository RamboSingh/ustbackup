package com.ustglobal.appium;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ustglobal.common.Debugger;
import com.ustglobal.common.Utility;
import com.ustglobal.common.Utility.CustomReporter;
import com.ustglobal.common.Utility.FinalizableInterface;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class AppiumClient implements FinalizableInterface {
	private int nSynchWaitInMillis = 20000; //in milliseconds
	private int nSynchResponsivenessInMillis = 1000; //in milliseconds		
	private DesiredCapabilities objCapability = null;
	private AndroidDriver objDriver=null;
	private WebDriverWait objSyncWait = null;
	private Actions objAction = null;
	public static boolean boolGlobalSettingReportsEnabled = true;
	private boolean bClearAppData=true; //Clear App data before loading the app for Appium
	private CustomReporter objReporter = null;
	private String strReportDirectory = System.getProperty("user.dir") +  "\\test-report";
	private String strReportDetails = Utility.GetUniqueTestNameIdentifier();
	private String strReportFileName = "TestReport_"+ strReportDetails + ".pdf";
	
	private CustomElement objElement = null;
	private String sPrevContext="";
	
	public boolean boolBringElementToFocus = true;
	public boolean bConnectionSuccess=false;
	public String sLastError="";
	private boolean bWebViewOnNativeApp=false; //Web view on a native app - used in Capture screenshot due to the bug with chromedriver
	public Debugger objDebugger;
	//Return the reporter object
	public CustomReporter fnGetNativeReporter(){
		return objReporter;
	}
	//Enable Debug option by adding AppiumClient and derived child class object (if any)
	/*public void fnEnableDebug(Object objClient){
		System.out.println("Going to save AppiumClient for debugging");
		objDebugger.fnRegisterClient("AppiumClient",objClient);
		objDebugger.fnRegisterClient("",objClient);
	}*/
	public AppiumClient(String sAppiumServerUrl, String[] sCapabilities) throws Exception{
		objCapability = new DesiredCapabilities();
		bConnectionSuccess=false;
		objCapability.setCapability("newCommandTimeout", "600"); //By Default the chromeBrowser will stay until 10 mints after test case failed-----  22-06-2017
		for(String sCapabilityItem:sCapabilities){
			String[] sItem=sCapabilityItem.split("=",2);
			String sCapabilityName=sItem[0];
			String sCapabilityValue;
			if (sItem.length>1)
				sCapabilityValue=sItem[1];
			else
				sCapabilityValue="";
			objCapability.setCapability(sCapabilityName, sCapabilityValue);
			
		}
		try{
			String sAppPackage=(String)objCapability.getCapability("appPackage");
			if (sAppPackage != null){
				//Clear app data through ADB
				if(bClearAppData){
					//System.out.println("Going to clear application data ");
					//Utility.RunShellCommand("adb shell pm clear " + sAppPackage);
				}
			}
			objDriver = new AndroidDriver(new URL(sAppiumServerUrl), objCapability);
			bConnectionSuccess=true;
		}
		catch(Exception e){
		//	System.out.println("Error while starting appium, " + e.getMessage());
			sLastError=e.getMessage();
			bConnectionSuccess=false;
			return;
		}
		SetSynchWaitTime(this.nSynchWaitInMillis);
		objElement = new CustomElement(this);
		objAction = new Actions(objDriver);
		
		if(boolGlobalSettingReportsEnabled){
			  Utility.CreateFolder(strReportDirectory, false);
			  objReporter = new CustomReporter(strReportDetails, strReportDirectory+"\\"+strReportFileName);
			  objReporter.boolDeletePNGs = true; 
			  objReporter.nImageScalePercentage = 50; // Don't change this, Currenlty there is issue with scale % for Appium images, if 60% given, iText Table infinite loop error appears, objImage.scalePercent is the culprit there
		}
		//Setting Appium Driver instance in Debugger
		objDebugger=new Debugger(this); //[Pappan: should change to objDebugger=new Debugger(this); ]
		//fnEnableDebug(this); //[Pappan: remove this function ]
	}
	

	public boolean Navigate(String sURL) throws Exception{		
		objDriver.get(sURL);
		return true;
	}
	public AndroidDriver GetNativeClient(){
		return objDriver;
	}
	public boolean click(By objBy){
		objDriver.findElement(objBy).click();
		return true;
	}
	// Return the capability object
	public DesiredCapabilities GetCapabilityObject(){
		return objCapability;
	}
	

	public boolean ElementExists(By byIdentifier, int nTimeOut) throws Exception{
		  int nAttemptsRemaining = nTimeOut/(this.nSynchResponsivenessInMillis);
		  while(this.objDriver.findElements(byIdentifier).size() == 0 && --nAttemptsRemaining>0){
			  Thread.sleep(this.nSynchResponsivenessInMillis);
		  }
		  return (this.objDriver.findElements(byIdentifier).size()>0);
	}
	public void SetSynchWaitTime(int nSynchWaitInMillis) throws Exception {
		  //Neither implicit nor explicit wait implementation proved efficient. We need a pageloadsync() badly
		  this.nSynchWaitInMillis = nSynchWaitInMillis;
		  this.objSyncWait = null; //delete existing object if already created
		  this.objSyncWait = new WebDriverWait(objDriver,(this.nSynchWaitInMillis/1000>0? this.nSynchWaitInMillis/1000:1));
		  this.objSyncWait.pollingEvery(this.nSynchResponsivenessInMillis, TimeUnit.MILLISECONDS);
	}
	public class CustomElement implements WebElement{
		private AppiumClient parent = null;
		private WebElement nativeElement = null;
		
		public CustomElement (AppiumClient oClient){ 
			this.parent = oClient;
		}
		
		private void SetNativeElement (WebElement nativeElement){ 
			this.nativeElement = nativeElement;
		}
		
		public void ufxFocus(){
			parent.objAction.moveToElement(this.nativeElement).perform();
		}
		public void ufxScrollElementToView(){
			try{
				((JavascriptExecutor) objDriver).executeScript("arguments[0].scrollIntoView(true);", this.nativeElement);
			}
			catch(Exception e){
				
			}
		}
		
		public void ufxClick(){
	
				this.nativeElement.click();

		}
		
		public void ufxSelectFromDropdown(String strItemToSelect) throws Exception{
			Select objSelect=new Select(this.nativeElement);
			try{
				objSelect.selectByVisibleText(strItemToSelect);
				this.nativeElement.sendKeys(Keys.TAB);
			}
			catch (Exception e){
				System.out.println("\n");
				Iterator<WebElement> objListOptions = objSelect.getOptions().iterator();
				String strListItem = "";
				while(objListOptions.hasNext()){
					strListItem = objListOptions.next().getAttribute("outerText");
					System.out.println("OPTION SELECT ERROR:"+(strItemToSelect==strListItem) + ":" + strItemToSelect + "==" + strListItem);
				}
				throw new Exception("Dropdown select/synch issue:"+strItemToSelect);
			}
			objSelect=null;
		}
		
		public void ufxSelectCheckbox(boolean boolCheck) throws Exception{		
			String strQualifiedName = getElementName();
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
						strQualifiedName = strQualifiedName + "." + objElement.getAttribute("id");
						boolCurrentState = objElement.isSelected();
						boolFound = true;
						break;
					}
				}
				childElements = null;				
			}
			//System.out.println(strQualifiedName + ":" + boolCurrentState);
			if(!boolFound){
				parent.objReporter.Log("Warning - checkbox selection", "There's some technical difficulty in determining the current state of the checkbox (" + strQualifiedName + ")", TakeSnapShot());
				//parent.objAction.moveToElement(this.nativeElement).click();
			}
			else{
				parent.objReporter.Log(boolCheck?"Select checkbox":"De-Select checkbox", "Next step is to " + (boolCheck?"Select the checkbox ":"DeSelect the checkbox ") + strQualifiedName, TakeSnapShot());
				if((!boolCurrentState && boolCheck)||(boolCurrentState && !boolCheck)){
				//	parent.objAction.moveToElement(this.nativeElement).click();
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
				
				String strQualifiedName=getElementName();
				objReporter.Log("Going to Click " + strQualifiedName, "Next step is to click on the webelement " + strQualifiedName, TakeSnapShot());
				
				//Expected condition added by Anil (Added since the below code is removed from CustomElement Element(By ByID)  method) 
				objSyncWait.until(ExpectedConditions.elementToBeClickable(this.nativeElement));
				this.nativeElement.click(); 
				//Seen issues when clicking checkboxes within a SPAN element. Use 'ufxSelectCheckbox()' instead
			}
			catch(Exception ex){
				System.out.println("Exception error on element click\r\n" + ex.getMessage());
				if(this.nativeElement.getTagName().equals("span")){
					try {
						Log("Element click failed", "Element click failed (likely to be SPAN element click issue)\r\n" + ex.getMessage());
					} 
					catch (Exception e) {
						
					}
				}
				else{
					try {
						Log("Element click failed", "Element click failed for some unknown reason\r\n" + ex.getMessage());
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

		@Override
		public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
			return this.nativeElement.getScreenshotAs(arg0);
		}
		public String ufxSetAttributeValue(String sAttribute, String sValue){
			
		//	JavascriptExecutor js = (JavascriptExecutor) parent.GetNativeClient(); 

		    String scriptSetAttrValue = "arguments[0].setAttribute('arguments[1]','arguments[2]');";
		    parent.GetNativeClient().executeScript(scriptSetAttrValue, this.nativeElement, sAttribute, sValue);
		  //  js.executeScript(scriptSetAttrValue, this.nativeElement, sAttribute, sValue);
		    
			
		    return this.nativeElement.getAttribute(sAttribute);
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
		//Return the mobileelement
		public MobileElement GetMobileElement(){
			return (MobileElement) this.nativeElement;
		}
	}
	
	public CustomElement Element(By ByID) throws Exception{
		//WaitForPageLoad(); //this is not proven to work
		//Think about ajax controls too: ExpectedConditions.presenceOfElementLocated//elementToBeClickable		
		objSyncWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ByID));
		//objElement.SetNativeElement(objSyncWait.until(ExpectedConditions.elementToBeClickable(ByID)));
		//New code by Anil, To support wait for element until the element is clickable, Webdrivewait is added under click method in CustomElement class
		objElement.SetNativeElement(objDriver.findElement(ByID));
		if(boolBringElementToFocus){
			//Set focus only on web not on native app
			if (!GetCurrentContext().equalsIgnoreCase("NATIVE_APP"))
				objElement.ufxFocus();
		}
		return objElement;
	}

	
	public CustomElement Element(By ByID, String sVisibleTextRegExpMatch) throws Exception{
		List <WebElement> listWebElements = objDriver.findElements(ByID);
		String sExtracted = "";
		for(int nIndex=0; nIndex< listWebElements.size(); nIndex++){
			sExtracted = listWebElements.get(nIndex).getAttribute("outerText");
			if (Pattern.matches(sVisibleTextRegExpMatch,sExtracted )){
				objElement.SetNativeElement(listWebElements.get(nIndex));
				return objElement;
			}
		}		
		//return null;	
		throw new Exception("Element multi-match identification failed: " + sVisibleTextRegExpMatch);
	}
	public boolean ActivateFrame(By byIdentifier) throws Exception{
		objDriver.switchTo().frame(Element(byIdentifier).nativeElement); 	
		return true;
	}
	public boolean ActivateParentFrame() throws Exception{
		objDriver.switchTo().defaultContent();
		return true;
	}
	//Go back to parent frame
	public void ActivateFrame() {
		objDriver.switchTo().defaultContent();
	}
	public void Log(String sStepDesc) throws Exception{
	//	String sPathIMG = Utility.TakeSnapShot(objDriver,null);
	//	Log(sStepDesc.length()>15?(sStepDesc.substring(0,15)+".."):sStepDesc, sStepDesc, sPathIMG);
	//	objReporter.Log(sStepDesc.length()>15?(sStepDesc.substring(0,15)+".."):sStepDesc, sStepDesc);
		objReporter.Log(sStepDesc, sStepDesc);
	}
	
	public void Log(String sStepName, String sStepDesc) throws Exception{
	//	String sPathIMG = Utility.TakeSnapShot(objDriver,null);
	//	Log(sStepName, sStepDesc, sPathIMG);
		objReporter.Log(sStepName+"[FONT:BOLD]", sStepDesc);
	}
	
	public void Log(String sStepName, String sStepDesc, String sPathIMG) throws Exception{
		if(objReporter!=null){		
			objReporter.Log(sStepName+"[FONT:BOLD]", sStepDesc, sPathIMG);
		}
		else {
			System.out.println("Warning: Log() function was called after finalize, or before initializing the reporter. The call is ignored.");
		}
	}
	//Write a text log without image
	public void LogText(String sLogItem) throws Exception{
		objReporter.Log(sLogItem);
	}
	public String GetReportFilePath(){		
		return strReportDirectory+"\\" + strReportFileName;
	}
	
	public String GetReportFileName(){
		return strReportFileName;
	}
	//Destructor of Appium client, java finalize() is not suitable to the purpose as it gets called only during System GC
		public void Finalize(boolean boolCloseBrowser) throws Exception{		
			try{
				System.out.println("Finalize called, Value ="+boolCloseBrowser);
				
				if(objDriver!=null){
					System.out.println("Driver not null");
					if(!boolCloseBrowser) {
						System.out.println("Going to launch Debugger");
						objDebugger.Launch();
					}
					//if(!objDebugger.boolIsLaunched){ //commented temporarily by pappan
						if(objDriver!=null){
							try{
								System.out.println("Closing Driver");
								objDriver.close();
								objDriver.quit();
							}
							catch(Exception e){
								System.out.println("Browser might have closed manually by user");
							}
							objDriver = null;	
						}
						if(objReporter!=null){
							System.out.println("Reporter not null, finalizing");
							objReporter.Finalize();
							objReporter = null;
						}
						else
							System.out.println("Reporter Null");
					//}
				}
				else{ //Finalize report if for some reason objDriver goes null (Like, browser is closed manually by user)

					System.out.println("Driver null");
					if(objReporter!=null){
						System.out.println("Reporter not null, finalizing report");
						objReporter.Finalize();
						objReporter = null;
					}
					else
						System.out.println("Reporter Null");
						
				}
			}catch(Exception e){
				//Reporter to be finalized prior to driver. Nothing to be logged at this point.
				System.out.println("Exception caught on Appium Client Finalize(boolean) " + e.getMessage());
			}
		}
		
		public void Finalize() throws Exception{ 
			//Finalize(!objDebugger.boolIsLaunched); //commented temporarily by pappan
		}
		
		@Override
		public void Finalize(String strFinalStatus) throws Exception{
			switch(strFinalStatus){
				case "TERMINATED":
					Log("Final page", "Snapshot of final page", TakeSnapShot()); 
					//Finalize(!objDebugger.boolIsLaunched); //commented temporarily by pappan
					break;
				case "WAITING":
					objReporter.ValidateTest(false);
					Log("USER ABORT[FONT:Italic]", "User aborted the test by clicking on the system tray, stop button", null);
					//Finalize(!objDebugger.boolIsLaunched); //commented temporarily by pappan
					break;
				case "INTERRUPTED":
					objReporter.ValidateTest(false);
					Log("USER ABORT[FONT:Italic]", "User aborted the test by clicking on the system tray, stop button", null);
					//Finalize(!objDebugger.boolIsLaunched); //commented temporarily by pappan
					break;
				case "TIMED_WAITING":	
					//TO DO : uncomment the code and fix by comparing with SInteractive
				//	String strErrorTraceLog = "Attempted element: " + strLastAttemptedIdentification + "\r\n" + Utility.GetStackTrace(objThread);
				//	System.out.println(strErrorTraceLog);
					objReporter.ValidateTest(false);
					Log("UNEXPECTED ERROR[FONT:Italic]", "", null);
					//Finalize(!objDebugger.boolIsLaunched);//commented temporarily by pappan
					break;
				default:
					Log("Finalized (" + strFinalStatus + ")", "User finalized the report. Snapshot of final page", TakeSnapShot());
					//Finalize(!objDebugger.boolIsLaunched);//commented temporarily by pappan
					break;
			}
		}
	public String TakeSnapShot() {
		String sFileName="";
		try{
			System.out.println("Going to take screenshot");
			//TakeSnapshot() waits for ever while on webview on native app, Issue with chrome driver, might get resolved in latest chrome driver
			
			if (bWebViewOnNativeApp){
				String sPervContext=SetNativeAppContext();
				sFileName=objDriver.getScreenshotAs(OutputType.FILE).getAbsolutePath();
				SetContext(sPervContext);
			}
			else
				sFileName=objDriver.getScreenshotAs(OutputType.FILE).getAbsolutePath();
		}
		catch (Exception e){
			System.out.println("Unable to take screeshot, error : " + e.getMessage());
			sFileName="";
		}
		return sFileName;
	}
	
	public String GetCurrentContext(){
		return objDriver.getContext();
	}
	//Set a  new context and returns the pervious context
	public String SetContext(String sContext){
		sContext=sContext.trim();
		String sCurrentContext=objDriver.getContext();
		try{
			sPrevContext=sCurrentContext;
			objDriver.context(sContext);
			System.out.println("Context set to : " + sContext);
			if (sContext.toUpperCase().contains("WEBVIEW")){
				System.out.println("Setting bWebViewOnNativeApp flag");
				bWebViewOnNativeApp=true;
			}
			else{
				System.out.println("Un Setting bWebViewOnNativeApp flag");
				bWebViewOnNativeApp=false;
			}
			
			return sCurrentContext;
		}
		catch(Exception e){
			System.out.println("Error while setting context : " + sContext + ", Error : " + e.getMessage());
			return sCurrentContext;
		}
	}
	//Set the context back to the previous context, which was changed by SetContext()
	public String SetPreviousContext(){
		String sCurrentContext=objDriver.getContext();
		try{
			if (!sPrevContext.isEmpty()){
				SetContext(sPrevContext);
				sPrevContext=sCurrentContext;
			}
			return sCurrentContext;
		}
		catch(Exception e){
			return sCurrentContext;
		}
	}
	//Set  context back to Native app and returns the previous context
	public String SetNativeAppContext(){
		try{
			return SetContext("NATIVE_APP");
		}
		catch(Exception e){
			return "";
		}
	}
	
	/*
	//Set a  new context and returns the current context
	public String SetLatestContext(){
		String sLatestContext="";
		String sCurrentContext=objDriver.getContext();
		try{
			sPrevContext=sCurrentContext;
			
			Set<String> contextNames = objDriver.getContextHandles();
			for (String sContext : contextNames) {
				System.out.println(sContext); 
				sLatestContext=sContext;
			}
			if(!sLatestContext.isEmpty())
				SetContext(sLatestContext);
			return sLatestContext;
		}
		catch(Exception e){
			return "";
		}
	}
	*/
	public String SetWebViewContext(){
		String sLatestContext="";

		try{
			Set<String> contextNames = objDriver.getContextHandles();
			for (String sContext : contextNames) {
				if (sContext.contains("WEBVIEW"))
					sLatestContext=sContext;
			}
			if(!sLatestContext.isEmpty())
				SetContext(sLatestContext);
			Thread.sleep(1500);
			return sLatestContext;
		}
		catch(Exception e){
			return "";
		}
	}
	public boolean WaitForWebView(int iTimeout, int iPolling){
		int iTimeCount=0;

		try{
			while (true){
				Set<String> contextNames = objDriver.getContextHandles();
				for (String sContext : contextNames) {
					if (sContext.contains("WEBVIEW"))
						return true;
				}
				Thread.sleep(iPolling);
				iTimeCount=iTimeCount+iPolling;
				if (iTimeCount>=iTimeout) break;
			}
			return false;
		}
		catch(Exception e){
			return false;
		}
	}
		
	public boolean IsWebViewContext(){
		if (GetCurrentContext().contains("WEBVIEW"))
				return true;
			else
				return false;
	}
	//Close keyboard if displayed
	public boolean CloseKeyboard(){
		String sCurrentContext=objDriver.getContext();
		try{
			objDriver.context("NATIVE_APP");
			objDriver.hideKeyboard();
			objDriver.context(sCurrentContext);
			return true;
		}
		catch(Exception e){
			objDriver.context(sCurrentContext);
			return false;
		}
	}
	//Swipe based on device dimension percentage
	public boolean Swipe(int iStartXPercentage,int iStartYPercentage,int iEndXPercentage,int iEndYPercentage, int iDelay){
		Dimension winSize;
		String sContext=objDriver.getContext();
		try{
			System.out.println("Going to Swipe and changing context to Native");
			objDriver.context("NATIVE_APP");
			// Retrieve the actual device dimensions
			winSize = objDriver.manage().window().getSize();
			int iStartX=(winSize.width * iStartXPercentage) / 100;
			int iStartY =(winSize.height * iStartYPercentage) / 100;
			int iEndX=(winSize.width * iEndXPercentage) / 100;
			int iEndY =(winSize.height * iEndYPercentage) / 100;
			objDriver.swipe(iStartX, iStartY,iEndX, iEndY,iDelay);
			System.out.println("Swipe completed and changing context back to " + sContext);
			objDriver.context(sContext);
			
			return true;
		}
		catch(Exception e){
			System.out.println("Swipe failed, on Exception changing context back to " + sContext);
			objDriver.context(sContext);
			return false;
		}
		
	}
	//Swipe using exact coordinates on device 
	public boolean SwipeOnCoordinates(int iStartX,int iStartY,int iEndX,int iEndY, int iDelay){
			
		try{
			String sContext=objDriver.getContext();
			objDriver.context("NATIVE_APP");
			// Retrieve the actual device dimensions
			objDriver.swipe(iStartX, iStartY,iEndX, iEndY,iDelay);
			objDriver.context(sContext);
			
			return true;
		}
		catch(Exception e){
			return false;
		}
		
	}
}
