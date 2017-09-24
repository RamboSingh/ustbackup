package com.ustglobal.genericclient;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import com.ustglobal.appium.AppiumClient;
import com.ustglobal.common.SystemTrayMonitor;
import com.ustglobal.common.Utility;
import com.ustglobal.common.Utility.FinalizableInterface;
import com.ustglobal.common.Utility.JsonObj;
import com.ustglobal.seetestclient.SeeTestClient;
import com.ustglobal.selenium.SInteractive;

public class GenericClient implements FinalizableInterface {
	private String sToolName="";
	
	private boolean bLastActionResult;
	private String sLastActionStatus;
	private String sLastError;
	private String sLastActionOutput;
	private ParamExtractor objParamExtract;
	
	
	private SeeTestClient oSTclient=null;
	private SeeTestParameters STParameters;
	
	private SInteractive oSelClient=null;
	private AppiumClient oAppiumClient=null;
	private SeleniumParameters SelParameters;
	private boolean bAppiumManualLaunch=true; //Flag to indicate if Appium is launched manually or not 
	
	private String sAppPackage=null;
	private String sAppLaunchingActivity=null;
	private boolean bNativeApp=false; //Whether running Native app or not
	
	private String[] sCapabilities=null;
	
	public static final int LEAST_PORT = 1024;
	private static String sBootStrapPort;
	private static String sPort;
	private static String sChromePort;
	
	private SystemTrayMonitor objSystemTrayControl = null;
	 
	public Flags Settings;
	public boolean ReportGenerator=false;
	//public boolean bWebViewOnNativeApp=false; //Web view on a native app, used for appium to decide whether to update xpath or not

	public GenericClient(String[] args) throws Exception{
		
		Settings=new Flags();
		if (args.length<1){
			System.out.println("Unable to create Generic Client, Tool name not mentioned");
			throw new Exception("Unable to create Generic Client, Tool name not mentioned");
		}
		
		if (args.length==1){
	
			if(args[0].contains("{")){
				JsonObj.JsonFormat(args[0]);
			}else{
				JsonObj.JsonArray(args[0]);	
			}
						
			args = new String[JsonObj.Getlength()];  //Dynamic array
		
			args[0]=JsonObj.Get("ToolName");
		
			switch(args[0].toUpperCase()){			
				case "SEETEST":
					args[1]=JsonObj.Get("LocalHost");
					args[2]=JsonObj.Get("Port");
					args[3]=JsonObj.Get("DeviceName");
					args[4]=JsonObj.Get("BaseDirectory");
					args[5]=JsonObj.Get("IntermediateSteps");
					
					break;
				case "APPIUM":
					
					args[0]=JsonObj.Get("ToolName");
					args[1]=JsonObj.Get("AppiumSilentMode");
					args[2]=JsonObj.Get("AppiumFolderPath");
					args[3]=JsonObj.Get("AppiumServerUrl");
					args[8]=JsonObj.Get("udid");
					JsonObj.Remove("ToolName");
					JsonObj.Remove("AppiumSilentMode");
					JsonObj.Remove("AppiumFolderPath");
					JsonObj.Remove("AppiumServerUrl");
					  Iterator<String> keys = JsonObj.Obj.keys();						
					    Integer i=4;
						do{
					        String k = keys.next().toString();
					        args[i]=k+"="+(String) JsonObj.Obj.get(k);				 				        
					        i++;
					    }while(keys.hasNext());
						sCapabilities=new String[args.length];					
						sCapabilities=Arrays.copyOfRange(args, 4, args.length);	
											
					break;
				case "SELENIUM":
					break;
			}
			
		}
		
		
		String sTool=args[0].trim();
		sTool=Utility.RegExpExtract(sTool, "[A-z]*").toUpperCase().trim();
		switch(sTool){
			case "SEETEST":
				sToolName="SEETEST";
				STParameters=new SeeTestParameters();
				if(!STParameters.ExtractSeeTestParameters(args)){
					System.out.println("Unable to create Generic Client, Error while extracting SeeTest Parameters");
					throw new Exception("Unable to create Generic Client, Error while extracting SeeTest Parameters");
				}
				if(!STParameters.TakeIntermediateScreenShots(args)){
					
				}
				oSTclient = new SeeTestClient(STParameters.sHostName, STParameters.iPortNo,STParameters.sDeviceName, STParameters.sProjectBaseDirectory,STParameters.bTakeIntermediateScreenShots);
				break;
			case "APPIUM":
				sToolName="APPIUM";
				
				if (args.length>4){
					
					String sStartAppiumServerFlag=args[1];
					String sAppiumFolderPath=args[2];
					//Start appium server automatically, if asked to do so
					if (sStartAppiumServerFlag.equalsIgnoreCase("Yes")){
						
						bAppiumManualLaunch=false;
						
						//Fetching Free port 
						sPort=nextFreePort().toString();
						sBootStrapPort=nextFreePort().toString();
						sChromePort=nextFreePort().toString();
						System.out.println("Ports are assined to Appium server: "+sPort+",Bootstrap : "+sBootStrapPort+" and Chrome : "+sChromePort);
						
						//Stop running Appium instances, if any
						//appiumStop();
						//Get Appium folder path and create NodePath and Appium JS paths
						String sNodePath="";
						String sAppiumJSPath="";
						if(sAppiumFolderPath.substring(sAppiumFolderPath.length()-1).equalsIgnoreCase("\\")){
							sNodePath=sAppiumFolderPath+ "node.exe";
							sAppiumJSPath=sAppiumFolderPath+ "node_modules\\appium\\bin\\appium.js";
						}
						else{
							sNodePath=sAppiumFolderPath+ "\\node.exe";
							sAppiumJSPath=sAppiumFolderPath+ "\\node_modules\\appium\\bin\\appium.js";
						}
						String sServerIP=Utility.RegExpExtract(args[3].trim(), ".*://(.*):[0-9][0-9][0-9][0-9].*");
//						String sServerPort=Utility.RegExpExtract(args[3].trim(), ".*:([0-9][0-9][0-9][0-9]).*");
						System.out.println("Going to launch Appium using Node Path : "  + sNodePath + ", JS Path : " + sAppiumJSPath + ", Server IP : " + sServerIP + ", Server Port : " + sPort);
						appiumStart(sNodePath, sAppiumJSPath, sServerIP, sPort,sBootStrapPort,sChromePort);
						int iTimeout=0;
						while(true){
//							oAppiumClient=new AppiumClient(args[3], Arrays.copyOfRange(args, 4, args.length));
							oAppiumClient=new AppiumClient("http://"+sServerIP+":"+sPort+"/wd/hub", Arrays.copyOfRange(args, 4, args.length));
							
							System.out.println("TestingNode  "+ "http://"+sServerIP+":"+sPort+"/wd/hub");
							
							if (oAppiumClient.bConnectionSuccess){
								System.out.println("Appium Server started...");
								break;
							}
							else{
								System.out.println("Appium Server not started yet, waiting for some more time ...");
								fnExplicitWait(10000);
								iTimeout=iTimeout + 10000;
								if (iTimeout>180000){
									System.out.println("Appium Server Timedout..., Server not started even after waiting for " + iTimeout + " milli seconds !");
									System.out.println("Appium error : " + oAppiumClient.sLastError);
									break;
								}
							}
						}
					}
					else
							
						if(sCapabilities!=null){									
							oAppiumClient=new AppiumClient(args[3], sCapabilities);				
						}else{
							oAppiumClient=new AppiumClient(args[3], Arrays.copyOfRange(args, 4, args.length));
						}
			
				}
				else
					break;
				
				oAppiumClient.SetSynchWaitTime(20000);
				//Save Appium client and derived Business client in Debugger (this refers Business client object extended from Generic client)
				//oAppiumClient.fnEnableDebug(this); //[Pappan: need to change this to contructor]
				oAppiumClient.objDebugger.RegisterClient(this);
				sAppPackage=(String)oAppiumClient.GetCapabilityObject().getCapability("appPackage");
				sAppLaunchingActivity=(String)oAppiumClient.GetCapabilityObject().getCapability("appActivity");
				
				if (sAppPackage != null){
					bNativeApp=true;
				}
				break;

			case "SELENIUM":
				System.out.println(args[1]);
				if (args.length>2)
					oSelClient=new SInteractive(args[1], args[2]);
				else
					oSelClient=new SInteractive(args[1]);
				sToolName="SELENIUM";
				SelParameters=new SeleniumParameters();
				SelParameters.sBrowserType=args[0];
				//Extract and set browser type and emulator
				SelParameters.SetBrowserTypeAndEmulator();
				SelParameters.iSyncWaitTime=20000;
				//SUtility.DisplaySystemTrayMenu(oSelClient);
				oSelClient.SetSynchWaitTime(SelParameters.iSyncWaitTime);				
				//Save SInteractive client and derived Business client in Debugger (this refers Business client object extended from Generic client)
				//oSelClient.fnEnableDebug(this);
				oSelClient.objDebugger.RegisterClient(this);
				break;
			default:
				System.out.println("Unable to create Generic Client, Invalid Tool Name specified in GenericClient constructor");
				throw new Exception("Unable to create Generic Client, Invalid Tool Name specified in GenericClient constructor");		
		}
		objParamExtract=new ParamExtractor();
		objSystemTrayControl = new SystemTrayMonitor(this);
	}
	

	//Returns whether Native  app is running or not
	public boolean IsNativeApp(){
		return bNativeApp;
	}
	//Get the Pass/Fail status of the last action
	public boolean GetLastActionResult(){
		return bLastActionResult;
	}
	
	
	

	public void LanchDebugger() {
		switch(sToolName){
		case "SEETEST":	
			break;
		case "APPIUM":
			break;
		case "SELENIUM":
			oSelClient.objDebugger.Launch();
			break;
	}
		
	}
	
	//---------For getting Device Details name------------Updated 11/10/2016-----------------------
	
	public String GetDeviceDetails(String sdata) {
		 
		String sVal="";

    	switch(sdata.toLowerCase()){
    		case "name":
    	    	sVal=oSTclient.getProperty("device.name");
    			break;
    		case "os":
    	    	sVal=oSTclient.getProperty("device.os");
    			break;
    		case "manufacture":
    	    	sVal=oSTclient.getProperty("device.manufacture");
    			break;
    		case "model":
    			sVal=oSTclient.getProperty("device.model");
    			break;
    		case  "sn":
    			sVal=oSTclient.getProperty("device.sn");
    				break;
    		case "version":
    			sVal=oSTclient.getProperty("device.version");
    				break;
    		case "device.remote":
    			sVal=oSTclient.getProperty("device.remote");
				break;
    		case "orientation":
    			sVal=oSTclient.getProperty("orientation");
				break;
    		case "app_version":
    			sVal=oSTclient.getProperty("app.version");
				break;
    		case "screensize":
    			sVal=oSTclient.getProperty("device.screensize");
				break;
    		default:
    				
    	}
 
    	return sVal;
    	
	}
	
	//--------------------------------------------------------------------------------
	
	//Get the output value of last action (For action like ElementGetText, ElementGetProperty etc)
	public String GetLastActionOutputValue(){
		return sLastActionOutput;
	}
	
	public SeeTestClient GetSeeTestClient(){
		return oSTclient;
	}
	
	public SInteractive GetSeleniumClient(){
		return oSelClient;
	}
	
	public AppiumClient GetAppiumClient(){
		return oAppiumClient;
	}
	
	//Get the error string corresponding to last action
	public String GetLastActionError(){
		return sLastError;
	}
	
	//Returns whether appium is launched manually or not
	public boolean fnIsAppiumLaunchedManually(){
		return bAppiumManualLaunch;
	}
	
	
	
	public void Finalize() throws Exception{
		switch(sToolName){
			case "SEETEST":
				oSTclient.Finalize();
				//SeeTest_Finalize();
				break;
			case "APPIUM":
				
				oAppiumClient.Finalize();
				if (!bAppiumManualLaunch) //Close appium if launched automatically
					appiumStop(sPort);
					System.out.println("appium stop "+ sPort);
				break;
			case "SELENIUM":
				oSelClient.Finalize();
				break;
			
		}

	}
	
	//Added by Abhi
	public void Finalize(Boolean bStatus) throws Exception{
		switch(sToolName){
			case "SEETEST":
				//TODO
				break;
			case "APPIUM":
				//TODO
				oAppiumClient.Finalize(bStatus);
				break;
			case "SELENIUM":
				oSelClient.Finalize(bStatus);
				break;
			
		}

	}
	
	
	@Override
	public void Finalize(String strFinalStatus) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Calling Generic Client Finalize,  Stat : " + strFinalStatus);
		//Finalize();
		
		
		switch(sToolName){
			case "SEETEST":
				//TODO
				break;
			case "APPIUM":
				//TODO
				oAppiumClient.Finalize(strFinalStatus);
				break;
			case "SELENIUM":
				oSelClient.Finalize(strFinalStatus);
				break;
		
		}
		
		
	}
	

	public void applicationClearData(String sAppPackage) {
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.applicationClearData(sAppPackage);
				sLastActionStatus="SeeTest ApplicationClearData operation executed  on Aplication : " + sAppPackage;
				sLastError="";
				break;
		/*	
			case "APPIUM":
				oAppiumClient.GetNativeClient().resetApp();
				sLastActionStatus="Appium ApplicationClearData operation executed  on Aplication : " + sAppPackage;
				sLastError="";
				break;
				*/
			default:
				sLastError="applicationClearData operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public void applicationClose(String sAppPackage){
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.applicationClose(sAppPackage);
				sLastActionStatus="SeeTest ApplicationClose operation executed, Application closed : " + sAppPackage;
				sLastError="";
				break;
			case "APPIUM":
				oAppiumClient.GetNativeClient().closeApp();
				sLastActionStatus="Appium applicationClose operation executed  on Aplication : " + sAppPackage;
				sLastError="";
				break;
			default:
				sLastError="applicationClose operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public String capture() throws Exception {
		String sFileName=null;
		
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				sFileName=oSTclient.capture();
				sLastActionStatus="SeeTest Capture operation executed, screenshot captured : " + sFileName;
				sLastError="";
				break;
			case "SELENIUM":
				
				sFileName=oSelClient.TakeSnapShot();
				sLastActionStatus="Selenium Capture operation executed, screenshot captured : " + sFileName;
				sLastError="";
				break;
			case "APPIUM":
	
				//sFileName=Utility.TakeSnapShot(oAppiumClient.GetNativeClient(), null);
				sFileName=oAppiumClient.TakeSnapShot();
				
				sLastActionStatus="Appium Capture operation executed, screenshot captured : " + sFileName;
				sLastError="";
				break;
			default:
				sLastError="Capture operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		return sFileName;
	}
	
	
	
	
	public void click(String sZone,String sElement,int iIndx,int iClickCount) throws Exception{
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
			
				oSTclient.click(sZone, sElement, iIndx, iClickCount);
				sLastActionStatus="SeeTest Click operation executed on element : " + sElement;
				sLastError="";
				break;
			case "SELENIUM":
				objBy=GetByObject(sElement,true);
				oSelClient.Element(objBy).click();
				
				break;
			case "APPIUM":
				objBy=GetByObject(sElement,true);
				oAppiumClient.Element(objBy).click();
				break;
				
			default:
				sLastError="Click operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	
	
	public void SetPickerValues(String sZone,String sElement,int iIndx,int iWheelIndex,	String sValue) throws Exception{
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.setPickerValues(sZone, sElement, iIndx, iWheelIndex, sValue);
				sLastActionStatus="SeeTest Click operation executed on element : " + sElement;
				sLastError="";
				break;
			case "SELENIUM":
				sLastError="SetPickerValues operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
				break;
			case "APPIUM":
				sLastError="Click operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
				break;
				
			default:
				sLastError="Click operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	
	
	public int GetElementCount(String sZone,String sElement) throws Exception{
		int Count = 0;
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
			
				Count=oSTclient.getElementCount(sZone,sElement);
				
				sLastActionStatus="SeeTest Count operation executed on element : " + sElement;
				sLastError="";							
				break;
							
			case "SELENIUM":
							
				break;
			case "APPIUM":
				
				break;
				
			default:
				sLastError="Count operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
				return Count;
		}
		ExecuteAfterAction();
		return Count;
	}
	public boolean Pinch(Boolean Inside, int Xcordinate, int YCordinate, int Radius) throws Exception{
	
		boolean pinchvalue=true;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
			
				pinchvalue=oSTclient.pinch(Inside, Xcordinate, YCordinate, Radius);
				
				
				sLastActionStatus="SeeTest Pinch operation executed on element : " ;
				sLastError="";
							
				break;
				
				
			case "SELENIUM":
				
				
				break;
			case "APPIUM":
				
				break;
				
			default:
				sLastError="Pinch operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
				
		}
		ExecuteAfterAction();
		return pinchvalue;
		
	}
	
	public void click(String sZone,String sElement,int iIndx,int iClickCount, int iXOffsetFromElement, int iYOffsetFromElement) throws Exception{
		
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.click(sZone, sElement, iIndx, iClickCount, iXOffsetFromElement,iYOffsetFromElement);
				sLastActionStatus="SeeTest Click operation executed on element : " + sElement + " at (" + iXOffsetFromElement + "," + iYOffsetFromElement +")";
				sLastError="";
				break;
			case "SELENIUM":
				By objBy=GetByObject(sElement,true);
				oSelClient.Element(objBy).click();
				break;
				
			case "APPIUM":
				objBy=GetByObject(sElement,true);
				oAppiumClient.Element(objBy).click();
				break;
			default:
				sLastError="Click operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	

	
	public void clickCoordinate(int iX,int iY,int iClickCount) throws Exception{
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.clickCoordinate(iX, iY, iClickCount);
				sLastActionStatus="SeeTest ClickCoordinate operation executed at coordinate (" + iX + "," + iY +")";
				sLastError="";
				break;
		
			default:
				sLastError="clickCoordinate operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public void closeKeyboard(){
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				if(GetDeviceDetails("os").equalsIgnoreCase("ANDROID")){
					oSTclient.closeKeyboard();
					
					sLastActionStatus="SeeTest CloseKeyboard operation executed";
					sLastError="";	
				}else{
					oSTclient.sendText("{ESC}");
					sLastActionStatus="SeeTest CloseKeyboard operation executed";
					sLastError="";				
				}				
				break;
			case "APPIUM":
				
				oAppiumClient.CloseKeyboard();
				/*
				if (IsNativeApp())
					oAppiumClient.CloseKeyboard();
				else
					oAppiumClient.GetNativeClient().navigate().back();
				*/
				sLastActionStatus="Appium applicationClose operation executed  on Aplication : " + sAppPackage;
				sLastError="";
				break;
			default:
				sLastError="closeKeyboard operation not implemented in the Tool " + sToolName;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public String elementGetProperty(String sZone, String sElement, int iIndex, String sProperty) throws Exception{
		String sPropertyValue=null;
		By objBy;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				sPropertyValue=oSTclient.elementGetProperty(sZone, sElement, iIndex, sProperty);
				sLastActionStatus="SeeTest ElementGetProperty operation - property '" + sProperty +"', property value is " +sPropertyValue;
				sLastError="";
				break;
			
			case "SELENIUM":
				objBy=GetByObject(sElement,true);
				oSelClient.boolBringElementToFocus=false; //Hidden elements will throw error while activating the element
				switch(sProperty.toUpperCase()){
					case "TEXT": //Get text value
						sPropertyValue=oSelClient.Element(objBy).getText();
						break;
					default:
						sPropertyValue=oSelClient.Element(objBy).getAttribute(sProperty);
				}
				
				oSelClient.boolBringElementToFocus=true;
				break;
				
			case "APPIUM":
				objBy=GetByObject(sElement,true);
				oAppiumClient.boolBringElementToFocus=false; //Hidden elements will throw error while activating the element
				switch(sProperty.toUpperCase()){
					case "TEXT": //Get text value
						sPropertyValue=oAppiumClient.Element(objBy).getText();
						break;
					default:
						sPropertyValue=oAppiumClient.Element(objBy).getAttribute(sProperty);
				}
				oAppiumClient.boolBringElementToFocus=true;
				break;

			default:
				sLastError="elementGetProperty operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		return sPropertyValue;
	}
	public String elementGetText(String sZone, String sElement, int iIndex) throws Exception{
		String sTextValue=null;
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				sTextValue=oSTclient.elementGetText(sZone, sElement, iIndex);
				sLastActionStatus="SeeTest GetElementGetText operation- Extracted, value is " +sTextValue;
				sLastError="";
				break;
				
			case "SELENIUM":
				
				objBy=GetByObject(sElement,true);
				oSelClient.boolBringElementToFocus=false; //Hidden elements will throw error while activating the element
				sTextValue=oSelClient.Element(objBy).getText();
				oSelClient.boolBringElementToFocus=true;
				break;
				
			case "APPIUM":
				objBy=GetByObject(sElement,true);
				oAppiumClient.boolBringElementToFocus=false; //Hidden elements will throw error while activating the element
				sTextValue=oAppiumClient.Element(objBy).getText();
				oAppiumClient.boolBringElementToFocus=true;
				break;
			default:
				sLastError="elementGetText operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		return sTextValue;
	}
	public String elementSetProperty(String sZone, String sElement, int iIndex, String sProperty, String sPropertyValue) throws Exception{
		String sResult="";
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				sResult=oSTclient.elementSetProperty(sZone, sElement, iIndex, sProperty,sPropertyValue);
				sLastActionStatus="SeeTest ElementSetProperty operation - property '" + sProperty +"', value set to " +sPropertyValue;
				sLastError="";
				break;
				
			case "APPIUM":
				objBy=GetByObject(sElement,true);
				sPropertyValue=oAppiumClient.Element(objBy).ufxSetAttributeValue(sProperty, sPropertyValue);
				break;
			default:
				sLastError="elementSetProperty operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		return sResult;
	}
	public void elementListPick(String sListZone,String sListLocator, String sElementZone, String sElementLocator,int iElementIndex,boolean bClick) {
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.elementListPick(sListZone, sListLocator,sElementZone,sElementLocator,iElementIndex,bClick);
				sLastActionStatus="SeeTest Element List Pick operation - List locator : " + sListLocator + ", Element locator : " + sElementLocator;
				sLastError="";
				break;
		
			default:
				sLastError="elementListPick operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public void elementListSelect(String sListLocator, String sElementLocator,int iElementIndex,boolean bClick) throws Exception {
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.elementListSelect(sListLocator,sElementLocator,iElementIndex,bClick);
				sLastActionStatus="Element List Select operation - List locator : " + sListLocator + ", Element locator : " + sElementLocator;
				sLastError="";
				break;
			case "SELENIUM":
				objBy=GetByObject(sListLocator,true);
				oSelClient.Element(objBy).click();
				oSelClient.Element(objBy).ufxSelectFromDropdown(sElementLocator);
				break;
			case "APPIUM":	
				objBy=GetByObject(sListLocator,true);
				oAppiumClient.Element(objBy).click();
				oAppiumClient.Element(objBy).ufxSelectFromDropdown(sElementLocator);
				break;
			default:
				sLastError="elementListSelect operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public void elementSendText(String sZone, String sElement,int iIndx,String sTextVal) throws Exception{
		By objBy=null;
		ExecuteBeforeAction();
		
		//Clear current text contents, if required
		if (Settings.REPLACE_EXISTING_DATA_FOR_ELEMENT_SEND_TEXT)
			ElementClearText(sZone, sElement, iIndx);
		
		switch(sToolName){
			case "SEETEST":
				boolean bPressEnterKey=false;
				//For SeeTest, press enter key explicitly, if the Text value ends with \n
				if (sTextVal.endsWith("\n")){
					sTextVal=sTextVal.substring(0, sTextVal.length()-1);
					bPressEnterKey=true;
				}
				oSTclient.elementSendText(sZone, sElement, iIndx, sTextVal);
				if(bPressEnterKey)
					oSTclient.sendText("{ENTER}");
				sLastActionStatus="SeeTest ElementSendText operation - Text send '" + sTextVal + "' to Element " + sElement;
				sLastError="";
				break;
			case "SELENIUM":
				objBy=GetByObject(sElement,true);
				oSelClient.Element(objBy).sendKeys(sTextVal);
				break;
			case "APPIUM":
				objBy=GetByObject(sElement,true);
				AppiumSendText(objBy,sTextVal);
				break;
			default:
				sLastError="elementSendText operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	//Handle Send Text and special keycodes for Appium
	public void AppiumSendText(By objBy,String sValue) throws Exception{
		switch(sValue.toUpperCase()){
			case "{ENTER}":
				System.out.println("Going to hit enter key");
				oAppiumClient.GetNativeClient().pressKeyCode(66);
				//oAppiumClient.GetNativeClient().pressKeyCode(AndroidKeyCode.ENTER);
				break;
			default:
				oAppiumClient.Element(objBy).sendKeys(sValue);
				if (IsNativeApp()) //Close keyboard only when native app is running, on web it hangs if we try to closekeyboard
					oAppiumClient.CloseKeyboard();
		}
	}
	public void ElementClearText(String sZone, String sElement,int iIndx) throws Exception{
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.elementSendText(sZone, sElement, iIndx, "");
				sLastError="";
				break;
			case "SELENIUM":
				objBy=GetByObject(sElement,true);
				oSelClient.Element(objBy).clear();
				break;
			case "APPIUM":
				objBy=GetByObject(sElement,true);
				oAppiumClient.Element(objBy).clear();
				break;
			default:
				sLastError="ElementClearText operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public void elementSwipe(String sZone,String sElement,int iIndx,String sDirection,int iSwipeOffset,int iSwipeTime) throws Exception{
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.elementSwipe(sZone, sElement, iIndx, sDirection, iSwipeOffset, iSwipeTime);
				sLastActionStatus="ElementSwipe operation - Swipe direction " + sDirection;
				sLastError="";
				break;
			case "APPIUM":
				String sContext=oAppiumClient.GetCurrentContext();
				if(sZone.equalsIgnoreCase("NATIVE")) oAppiumClient.SetContext("NATIVE_APP");					
						
				sLastActionStatus="Appium Exit operation";
				int iLeftX=ElementGetLocation(sZone, sElement, 0).getX();
				int iUpperY=ElementGetLocation(sZone, sElement, 0).getY();
				int iRightX=iLeftX+ElementGetSize(sZone, sElement, 0).getWidth();
				int iLowerY=iUpperY+ElementGetSize(sZone, sElement, 0).getHeight();
				int middleX = (iRightX + iLeftX) / 2;
				if (sDirection.equalsIgnoreCase("Down"))
					GetAppiumClient().SwipeOnCoordinates(middleX, iUpperY+iSwipeOffset, middleX, iLowerY-5, iSwipeTime);
				else
					GetAppiumClient().SwipeOnCoordinates(middleX, iLowerY-5 , middleX,iUpperY+iSwipeOffset , iSwipeTime);
				sLastError="";

				oAppiumClient.SetContext(sContext);
				break;
			default:
				sLastError="elementSwipe operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public Dimension ElementGetSize(String sZone, String sElement, int iIndex) throws Exception{
		By objBy=null;
		Dimension objDim=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SELENIUM":
				
				objBy=GetByObject(sElement,true);
				oSelClient.boolBringElementToFocus=false; //Hidden elements will throw error while activating the element
				objDim=oSelClient.Element(objBy).getSize();
				oSelClient.boolBringElementToFocus=true;
				break;
				
			case "APPIUM":
				objBy=GetByObject(sElement,true);
				oAppiumClient.boolBringElementToFocus=false; //Hidden elements will throw error while activating the element
				objDim=oAppiumClient.Element(objBy).getSize();
				oAppiumClient.boolBringElementToFocus=true;
				break;
			default:
				sLastError="elementGetText operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		return objDim;
	}
	public Point ElementGetLocation(String sZone, String sElement, int iIndex) throws Exception{
		By objBy=null;
		Point objPoint=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SELENIUM":
				
				objBy=GetByObject(sElement,true);
				oSelClient.boolBringElementToFocus=false; //Hidden elements will throw error while activating the element
				objPoint=oSelClient.Element(objBy).getLocation();
				oSelClient.boolBringElementToFocus=true;
				break;
				
			case "APPIUM":
				objBy=GetByObject(sElement,true);
				oAppiumClient.boolBringElementToFocus=false; //Hidden elements will throw error while activating the element
				objPoint=oAppiumClient.Element(objBy).getLocation();
				oAppiumClient.boolBringElementToFocus=true;
				break;
			default:
				sLastError="elementGetText operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		return objPoint;
	}
	
	public boolean elementSwipeWhileNotFound(String sComponentZone,String sComponentElement,String sDirection,int iSwipeOffset,int iSwipeTime,String sElementZone,String sElementToFind,int iElementIndex, int iDelay, int iRounds, boolean bClick) throws Exception{
		boolean bResult=false;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				bResult=oSTclient.elementSwipeWhileNotFound(sComponentZone, sComponentElement, sDirection, iSwipeOffset, iSwipeTime, sElementZone, sElementToFind, iElementIndex, iDelay, iRounds, bClick);
				if (bResult){
					sLastActionStatus="ElementSwipeWhileNotFound passed, Element found " + sElementToFind;
					sLastError="";
				}
				else
					sLastError="ElementSwipeWhileNotFound FAILED, Element NOT found : " + sElementToFind;
					
				
				break;

			default:
				sLastError="elementSwipeWhileNotFound operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		return bResult;
	}
	public void exit(){
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.exit();
				sLastActionStatus="SeeTest Exit operation";
				sLastError="";
				break;
			case "SELENIUM":
				oSelClient.objWebDriver.quit();
				sLastActionStatus="Selenium Exit operation";
				sLastError="";
				break;
			case "APPIUM":
				oAppiumClient.GetNativeClient().quit();
				sLastActionStatus="Appium Exit operation";
				sLastError="";
				break;
			default:
				sLastError="exit operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public void hybridClearCache(boolean bClearCookies,boolean bClearCache) {
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.hybridClearCache(bClearCookies,bClearCache);
				sLastActionStatus="SeeTest HybridClearCache operation";
				sLastError="";
				break;
	
			default:
				sLastError="hybridClearCache operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		 
	}
	public void hybridSelect(String sWebViewLocator,int iIndex,String sIdentificationMethod,String sIdentificationValue,String sOptionToSelect) throws Exception {
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.hybridSelect(sWebViewLocator, iIndex, sIdentificationMethod, sIdentificationValue, sOptionToSelect);
				sLastActionStatus="SeeTest HybridSelect operation";
				sLastError="";
				break;
			case "APPIUM":
				By objBy;
				//If identification value contains locator, then don't append it
				if (sIdentificationValue.startsWith(sIdentificationMethod))
					objBy=GetByObject(sIdentificationValue,true);
				else
					objBy=GetByObject(sIdentificationMethod+"="+sIdentificationValue,true);
				oAppiumClient.Element(objBy).click();
				hybridselectvalue(objBy,sOptionToSelect,"xpath=//android.widget.ListView[1]/*[" + sOptionToSelect + "]");
				break;
			default:
				sLastError="hybridSelect operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		 
	}
	public void hybridselectvalue(By objBy,String sOptionToSelect,String sXpath) throws Exception{
		String sVal;
		sVal=sOptionToSelect;
		System.out.println(sXpath);
		String sContext=oAppiumClient.GetCurrentContext();
		oAppiumClient.SetContext("NATIVE_APP");
		objBy=GetByObject(sXpath,true);
		oAppiumClient.ElementExists(objBy, 5000);
		oAppiumClient.click(objBy);
		oAppiumClient.SetContext(sContext);
	}
	
	public boolean hybridWaitForPageLoad(int iTimeOut) {
		boolean bPageLoaded=false;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				try{
					oSTclient.hybridWaitForPageLoad(iTimeOut);
					bPageLoaded=true;
					sLastActionStatus="SeeTest HybridWaitForPageLoad operation - Wait timeout " + iTimeOut;
					sLastError="";
				}
				catch(Exception e){
					sLastActionStatus="";
					sLastError=e.getMessage();
				}
				
				break;
			case "APPIUM":
			case "SELENIUM":
				if(iTimeOut<5000) 
					iTimeOut=5000;
				System.out.println("Waiting for " + iTimeOut + " milli seconds...");
				sleep(iTimeOut);
				break;
				
			default:
				
				sLastError="hybridWaitForPageLoad operation not implemented for the Tool " + sToolName + ", waiting for 5 seconds..." ;
				System.out.println(sLastError);
				sleep(5000);
		}
		ExecuteAfterAction();
		return bPageLoaded;
	}
	public boolean install(String sAppPath,boolean bInstrument,boolean bKeepData){
		boolean bResult=false;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				sLastError="";
				bResult= oSTclient.install(sAppPath, bInstrument, bKeepData);
				break;
			case "APPIUM":
				oAppiumClient.GetNativeClient().installApp(sAppPath);
				bResult=true;
				break;
			default:
				sLastError="install operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		if (bResult)
			sLastActionStatus= "Install operation - Application installed : " + sAppPath;
		else
			sLastError="Install operation - installation filed for the application: " + sAppPath;
		ExecuteAfterAction();
		return bResult;
	}
	public boolean isElementFound(String sZone,String sElement) throws Exception{
		boolean bResult=false;
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				sLastError="";
				bResult= oSTclient.isElementFound(sZone, sElement);
				break;
			case "SELENIUM":
				objBy=GetByObject(sElement,false);
				bResult=oSelClient.ElementExists(objBy, 0);
				break;
			case "APPIUM":
				objBy=GetByObject(sElement,false);
				bResult=oAppiumClient.ElementExists(objBy, 0);
				break;
			default:
				sLastError="isElementFound operation not implemented for the tool " + sToolName;
				System.out.println(sLastError);
		}
		if (bResult)
			sLastActionStatus="IsElementFound operation - Element found";
		else
			sLastError="IsElementFound operation - Element NOT found";
		System.out.println("Element found : " +  bResult);
		ExecuteAfterAction();
		return bResult;
	}
	public boolean isElementFound(String sZone,String sElement,int iIndex) throws Exception{
		boolean bResult=false;
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				sLastError="";
				bResult= oSTclient.isElementFound(sZone, sElement,iIndex);
				break;
			case "SELENIUM":
				objBy=GetByObject(sElement,false);
				bResult=oSelClient.ElementExists(objBy, 0);
				break;
			case "APPIUM":
				objBy=GetByObject(sElement,false);
				bResult=oAppiumClient.ElementExists(objBy, 0);
				break;
			
			default:
				sLastError="isElementFound operation not implemented for the tool " + sToolName;
				System.out.println(sLastError);
		}
		if (bResult)
			sLastActionStatus="IsElementFound operation - Element found";
		else
			sLastError="IsElementFound operation - Element NOT found";
		ExecuteAfterAction();
		return bResult;
	}
	// For Selenium, launch will trigger navigate
	public void launch(String sActivityURL,boolean bInstrument,boolean bStopIfRunning) throws Exception{
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				oSTclient.launch(sActivityURL,bInstrument, bStopIfRunning);
				sLastActionStatus="SeeTest Launch operation - ActivityUrl launched : " + sActivityURL;
				sLastError="";
				break;
			case "SELENIUM":
				//Get the url alone (Remove prefix like chrome:http://www...)
				sActivityURL=fnRemoveBrowserNameFromUrl(sActivityURL);
				oSelClient.Navigate(sActivityURL);
				break;
			case "APPIUM":
				//If running Web app, then launch the url, else do nothing.
				//if (sActivityURL.matches(".*http(s)?://.*"))
				if(!IsNativeApp()){
					//Get the url alone (Remove prefix like chrome:http://www...)
					sActivityURL=fnRemoveBrowserNameFromUrl(sActivityURL);
					oAppiumClient.Navigate(sActivityURL);
				}
				break;
			default:
				sLastError="launch operation not implemented for the Tool " + sToolName;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	
	public void sendWhileNotFound(String sText,String sZone,String sElement,int iElementIndex,int iTimeOut,int iDelay){
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				oSTclient.sendWhileNotFound(sText, sZone, sElement, iElementIndex, iTimeOut, iDelay);
				sLastActionStatus="SeeTest sendWhileNotFound operation - Text send : " + sText;
				sLastError="";

			default:
				sLastError="sendWhileNotFound operation not implemented for the tool " + sToolName;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public void setDevice(String sDevice) throws Exception{
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				oSTclient.setDevice(sDevice);
				sLastActionStatus="SeeTest SetDevice operation - Device : " + sDevice;
				sLastError="";
				break;
			default:
				sLastError="setDevice operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public void sendText(String sText) throws Exception{
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				oSTclient.sendText(sText);
				sLastActionStatus="SeeTest SendText operation - Text send : " + sText;
				sLastError="";
				break;
			case "SELENIUM":
			
				oSelClient.objWebDriver.switchTo().activeElement().sendKeys(sText);
				sLastActionStatus="Selenium SendText operation - Text send : " + sText;
				sLastError="";				
				break;
			case "APPIUM":
				oAppiumClient.GetNativeClient().getKeyboard().sendKeys(sText);
				sLastActionStatus="Appium SendText operation - Text send : " + sText;
				sLastError="";	
				break;
			default:
				sLastError="sendText operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	
	
	
	public void sleep(int iTime){
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				oSTclient.sleep(iTime);
				sLastActionStatus="SeeTest Sleep operation - Sleep time : " + iTime;
				sLastError="";
				break;
			case "SELENIUM":
			case "APPIUM":
				sLastActionStatus="Sleep operation - Sleep time : " + iTime;
				sLastError="";
				fnExplicitWait(iTime);
				break;
			default:
				sLastError="sleep operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public void swipe(String sDirection,int iOffset,int iTime) throws Exception{
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				oSTclient.swipe(sDirection, iOffset, iTime);
				sLastActionStatus="SeeTest Sleep operation - Sleep time : " + iTime;
				sLastError="";
				break;
	
			default:
				sLastError="swipe operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	public boolean swipeWhileNotFound(String sDirection,int iSwipeOffset,int iSwipeTime,String sZone,String sElementToFind,int iElementToFindIndex,int iDelay,int iMaxSwipeRounds,boolean bClick) throws Exception{
		boolean bResult=false;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				sLastError="";
				bResult= oSTclient.swipeWhileNotFound(sDirection, iSwipeOffset, iSwipeTime, sZone, sElementToFind, iElementToFindIndex, iDelay, iMaxSwipeRounds, bClick);
				break;
				
			case "SELENIUM":
				if (bClick){
					By objBy=GetByObject(sElementToFind,true);
					oSelClient.Element(objBy).click();
				}
				break;

			default:
				sLastError="swipeWhileNotFound operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		if (bResult)
			sLastActionStatus="SeeTest swipeWhileNotFound operation successful";
		else
			sLastError="SeeTest swipeWhileNotFound operation failed";
		ExecuteAfterAction();
		return bResult;
	}
	public boolean sync(int iSilentTime,int iSensitivity,int iTimeout){
		boolean bResult=false;
		if(iSilentTime>=iTimeout)
			iTimeout=iSilentTime+10; //SeeTest 10.0 version onwards throws error if iSelect>=iTimeout
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				bResult= oSTclient.sync(iSilentTime, iSensitivity, iTimeout);
				break;
			default:
				sLastError="sync operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		if (!bResult)
			sLastError="Sync operation failed, Silent time " + iSilentTime;
		else
			sLastActionStatus="Sync operation passed, Silent time " + iSilentTime;
		return bResult;
	}
	public boolean syncElements(int iSilentTime,int iTimeout){
		boolean bResult=false;
		if(iSilentTime>=iTimeout)
			iTimeout=iSilentTime+10; //SeeTest 10.0 version onwards throws error if iSelect>=iTimeout
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				bResult= oSTclient.syncElements(iSilentTime, iTimeout);
				break;
			case "APPIUM":
				fnExplicitWait(iSilentTime);
				break;
			default:
				sLastError="syncElements operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		if (!bResult)
			sLastError="SyncElements operation failed, Silent time " + iSilentTime;
		else
			sLastActionStatus="SyncElements operation successful, Silent time " + iSilentTime;
		return bResult;
	}
	public boolean uninstall(String sApp) throws Exception{
		boolean bResult=false;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				bResult= oSTclient.uninstall(sApp);
				break;
			
			case "APPIUM":
				oAppiumClient.GetNativeClient().removeApp(sApp);
				bResult= true;
				break;
			default:
				sLastError="uninstall operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		if (!bResult)
			sLastError="";
		else
			sLastActionStatus="SeeTest UnInstall operation, Application uninstalled " + sApp;
		return bResult;
		
	}
	public boolean verifyElementFound(String sZone,String sElement,int iIndex) throws Exception{
		By objBy=null;
		boolean bResult=false;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				bResult=oSTclient.isElementFound(sZone, sElement, iIndex);
				break;
			case "SELENIUM":
				objBy=GetByObject(sElement,false);
				bResult=oSelClient.ElementExists(objBy, 0);
				break;
			case "APPIUM":
				objBy=GetByObject(sElement,false);
				bResult=oAppiumClient.ElementExists(objBy, 0);
				break;
			default:
				sLastError="verifyElementFound operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
		return bResult;
		
	}
	public boolean waitForElement(String sZone,String sElement,int iIndex,int iTimeout) throws Exception{
		boolean bResult=false;
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				bResult= oSTclient.waitForElement(sZone,sElement,iIndex,iTimeout);
				break;
			case "SELENIUM":
				objBy=GetByObject(sElement,false);
				bResult=oSelClient.ElementExists(objBy, iTimeout);
				System.out.println("Element Found " + (bResult ? "Yes":"No"));
				break;
			case "APPIUM":
				objBy=GetByObject(sElement,false);
				bResult=oAppiumClient.ElementExists(objBy, iTimeout);
				System.out.println("Element ( " +sElement + ") Found " + (bResult ? "Yes":"No"));
				break;
			default:
				sLastError="Tool " + sToolName + " Not supported by the framework";
				System.out.println(sLastError);
		}
	//	ValidateTest(bResult);
		ExecuteAfterAction();
		if (!bResult)
			sLastError="Element not found in " + iTimeout + " milli  seconds";
		else
			sLastActionStatus="SeeTest WaitForElement operation, Element found";
		return bResult;
	}
	public boolean waitForElementToVanish(String sZone,String sElement,int iIndex,int iTimeout) throws Exception{
		
		boolean bResult=false;
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				bResult= oSTclient.waitForElementToVanish(sZone,sElement,iIndex,iTimeout);
				break;
			case "APPIUM":
				objBy=GetByObject(sElement,false);
				int iTimeWaited=0;
				while(oAppiumClient.ElementExists(objBy,1000)){
					fnExplicitWait(1000);
					iTimeWaited=iTimeWaited+1000;
					if(iTimeWaited>iTimeout) return false;
				}
				return true;

			default:
				sLastError="waitForElementToVanish operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}

		ExecuteAfterAction();
		if (!bResult)
			sLastError="Element not vanished in " + iTimeout + " milli  seconds";
		else
			sLastActionStatus="waitForElementToVanish operation, Element vanished";
		return bResult;
	}
	public void ValidateTest(boolean bStatus) throws Exception{
		switch(sToolName){
			case "SEETEST":
				oSTclient.fnGetNativeReporter().ValidateTest(bStatus);
				break;
			case "APPIUM":
				oAppiumClient.fnGetNativeReporter().ValidateTest(bStatus);
				break;
			case "SELENIUM": //ValidateTest is a function needed by testscripter user. So that needs to go in the client: oSelClient.ValidateTest()
				//oSelClient.fnGetNativeReporter().ValidateTest(bStatus);
				break;
			default:
				sLastError="ValidateTest operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
	}
	public void ValidateTest(String strActual, String strExpected) throws Exception{
		switch(sToolName){
			case "SEETEST":
				oSTclient.fnGetNativeReporter().ValidateTest(strActual,strExpected);
				break;
			case "APPIUM":
				oAppiumClient.fnGetNativeReporter().ValidateTest(strActual,strExpected);
				break;
			case "SELENIUM": //ValidateTest is a function needed by testscripter user. So that needs to go in the client: oSelClient.ValidateTest()
				//oSelClient.fnGetNativeReporter().ValidateTest(bStatus);
				break;
			default:
				sLastError="ValidateTest operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
	}
	public void ValidateTest(boolean bStatus, String sLogDescr) throws Exception{
		switch(sToolName){
			case "SEETEST":
				
				try{
					String sImgPath=oSTclient.capture();
					sImgPath = Utility.ScaleDownImageFile(sImgPath, oSTclient.fnGetNativeReporter().nImageScalePercentage);
					oSTclient.fnGetNativeReporter().ValidateTest(bStatus, sLogDescr,sImgPath);
				}
				catch(Exception e){
					oSTclient.fnGetNativeReporter().ValidateTest(bStatus, sLogDescr);
				}
				
		//		oSTclient.fnGetNativeReporter().ValidateTest(bStatus, sLogDescr);
				break;
			
			case "APPIUM":
				try{
					String sImgPath;
					//TakeSnapshot() waits for ever while on webview on native app, Issue with chrome driver
					sImgPath=oAppiumClient.TakeSnapShot();
					oAppiumClient.fnGetNativeReporter().ValidateTest(bStatus, sLogDescr,sImgPath);
				}
				catch(Exception e){
					oAppiumClient.fnGetNativeReporter().ValidateTest(bStatus, sLogDescr);
				}
				
				break;
			
			case "SELENIUM":
				try{
					String sImgPath=oSelClient.TakeSnapShot();
					//ValidateTest is a function needed by testscripter user. So that needs to go in the client: oSelClient.ValidateTest()
					//oSelClient.fnGetNativeReporter().ValidateTest(bStatus, sLogDescr,sImgPath);
				}
				catch(Exception e){
					//ValidateTest is a function needed by testscripter user. So that needs to go in the client: oSelClient.ValidateTest()
					//oSelClient.fnGetNativeReporter().ValidateTest(bStatus, sLogDescr);
				}
				break;
			
			default:
				sLastError="ValidateTest operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
	}
	public void Log(String sStepDescription) throws Exception{
		switch(sToolName){
			case "SEETEST":
				oSTclient.Log(sStepDescription);
				break;
			case "APPIUM":
				oAppiumClient.Log(sStepDescription);
				break;
			case "SELENIUM":
				oSelClient.Log(sStepDescription);
				break;
			default:
				sLastError="Log operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
	}
	public void LogText(String sStepDescription) throws Exception{
		switch(sToolName){
			case "SEETEST":
				oSTclient.LogText(sStepDescription);
				break;
			case "APPIUM":
				oAppiumClient.LogText(sStepDescription);
				break;
			case "SELENIUM":
				oSelClient.Log(sStepDescription);
				break;
			default:
				sLastError="LogText operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
	}
	public void Log(String sStepDescription, String sLoadTime) throws Exception{
		switch(sToolName){
			case "SEETEST":
				oSTclient.Log(sStepDescription, sLoadTime);
				break;
			case "APPIUM":
				oAppiumClient.Log(sStepDescription, sLoadTime);
				break;
			case "SELENIUM":
				oSelClient.Log(sStepDescription, sLoadTime);
				break;
		}
	}
	public void Log(String sStepName,String sStepDescription, String sLoadTime) throws Exception{
		switch(sToolName){
			case "SEETEST":
				oSTclient.Log(sStepName,sStepDescription, sLoadTime);
				break;
			case "APPIUM":
				oAppiumClient.Log(sStepName,sStepDescription, sLoadTime);
				break;
			case "SELENIUM":
				//No implementation for load time for Selenium
				oSelClient.Log(sStepName,sStepDescription);
				break;
		}
	}
	public void Log(String sStepName,String sStepDescription, String sLoadTime,String sPathIMG) throws Exception{
		switch(sToolName){
			case "SEETEST":
				oSTclient.Log(sStepName,sStepDescription, sLoadTime,sPathIMG);
				break;
			case "APPIUM":
				break;
			case "SELENIUM":
				//No implementation for load time for Selenium
				oSelClient.Log(sStepName,sStepDescription, sPathIMG);
				break;
		}
	}
	
	public void setProperty(String sKey,String sValue) throws Exception{
		switch(sToolName){
			case "SEETEST":
				oSTclient.setProperty(sKey,sValue);
				break;
			case "APPIUM":
				break;
			case "SELENIUM":
				//No implementation for setProperty for Selenium				
				break;
		}
	}
	
	public void longClick(String sZone,String sElement,int iIndx,int iClickCount, int iXOffsetFromElement, int iYOffsetFromElement) throws Exception{
		switch(sToolName){
			case "SEETEST":
				oSTclient.longClick(sZone,sElement,iIndx,iClickCount,iXOffsetFromElement,iYOffsetFromElement);
				break;
			case "APPIUM":
				break;
			case "SELENIUM":
				//No implementation for setProperty for Selenium				
				break;
		}
	}
	

	//Placeholder to do some thing before executing an action
	public void ExecuteBeforeAction(){
		//To be implemented later - If required
	
	}
	//Placeholder to do some thing before executing an action
	public void ExecuteAfterAction(){
		//To be implemented later - If required
	}
		
	public class ParamExtractor {
		public static final String AUTO_TOOL_NAME="SEETEST";
		private String sStringParam;
		private int iIntParam;
		private boolean bBoolParam;
		
		
		public String GetStringParam(){
			return sStringParam;
		}
		public int GetIntegerParam(){
			return iIntParam;
		}
		public boolean GetBooleanParam(){
			return bBoolParam;
		}
		//Extract String parameter from the object parameter list
		public boolean ExtractStringParam(int iIndx, Object... oParams){
			if (oParams.length<=iIndx)
				return false;
			
			try{
				if (oParams[iIndx] instanceof String){
					sStringParam=(String)oParams[iIndx];
					return true;
				}
				else
					return false;
			}
			catch(Exception e){
				return false;
			}
		}
		
		//Extract Integer parameter from the object parameter list
		public boolean ExtractIntegerParam(int iIndx, Object... oParams){
			if (oParams.length<=iIndx)
				return false;
			
			try{
				if (oParams[iIndx] instanceof Integer){
					iIntParam=(int)oParams[iIndx];
					return true;
				}
				else
					return false;
			}
			catch(Exception e){
				return false;
			}
		}
		//Extract Boolean parameter from the object parameter list
		public boolean ExtractBooleanParam(int iIndx, Object... oParams){
			if (oParams.length<=iIndx)
				return false;
			
			try{
				if (oParams[iIndx] instanceof Boolean){
					bBoolParam=(boolean)oParams[iIndx];
					return true;
				}
				else
					return false;
			}
			catch(Exception e){
				return false;
			}
		}

	}
	class SeeTestParameters{
		public String sHostName ;
		public Integer iPortNo;
		public String sProjectBaseDirectory ;
		public String sDeviceName;
		public boolean bTakeIntermediateScreenShots=false;

		public boolean ExtractSeeTestParameters(String[] args){
			try{
				if (args.length<4) return false;
				sHostName=args[1];
				iPortNo=Integer.parseInt(args[2]);
				sDeviceName=args[3];
				if (args.length>4)
					sProjectBaseDirectory=args[4];
				else
					sProjectBaseDirectory="";
				return true;
			}
			catch(Exception e){
				System.out.println("Error while extracting SeeTest Parameters in Generic client " + e.getMessage());
				return false;
			}
		}
		public boolean TakeIntermediateScreenShots(String[] args){
		try{
			if(args[5].equalsIgnoreCase("Yes")){
				bTakeIntermediateScreenShots=true;
			}
			else if(args[5].equalsIgnoreCase("No")){
				bTakeIntermediateScreenShots=false;
			}
		}
		catch(Exception e){
			bTakeIntermediateScreenShots=true;
			}
		return bTakeIntermediateScreenShots;
	}
}	
	class SeleniumParameters{
		public String sBrowserType ;
		public String sMobileEmulator;
		public Integer iSyncWaitTime;
		public void SetBrowserTypeAndEmulator(){
			String sBrowserParam=sBrowserType;
			sBrowserType = Utility.RegExpExtract(sBrowserParam, "[A-z]*").toUpperCase().trim();
			sMobileEmulator = Utility.RegExpExtract(sBrowserParam, ".*\\.(.*)").trim();
			  
		}
	}
	public By GetByObject(String sIdentification, boolean bCheckElementExist){
		By objBy;
		String sLocator;
		String sLocatorValue;
		try{
			objBy=null;
			sLocator=sIdentification.split("=",2)[0];
			sLocatorValue=sIdentification.split("=",2)[1];
			
			switch(sLocator.toUpperCase().trim()){
				case "XPATH":
					if (sToolName=="APPIUM"){
						System.out.println("Xpath Locator for Appium before update  : "+sLocatorValue);
						//Replace id and content description for native apps, ignore for web app
						if (sAppPackage!=null){
							//If xpath is not given in appium syntax, then change to appium syntax  
							//Dont change if working with web view on native 
							//if(!bWebViewOnNativeApp){
							if(!GetAppiumClient().IsWebViewContext()){
								System.out.println("In xpath replace code segment");
								sLocatorValue=sLocatorValue.replaceAll("@id='", "@resource-id='" + sAppPackage + ":id/");  // for syntax like @id='id'
								sLocatorValue=sLocatorValue.replaceAll("@id", "@resource-id"); // for syntax like contains(@id,'id')
								sLocatorValue=sLocatorValue.replaceAll("@contentDescription","@content-desc");
							}
							else
								System.out.println("Native app but Outside xpath replace code segment");
						}
						else{
							//If working with native on web  where context was explicitly changed to native (Like handling calendar control)
							if (!GetAppiumClient().IsWebViewContext()){
								System.out.println("In xpath replace code segment");
								sLocatorValue=sLocatorValue.replaceAll("@id='", "@resource-id='android:id/");
								sLocatorValue=sLocatorValue.replaceAll("@id", "@resource-id");
								sLocatorValue=sLocatorValue.replaceAll("@contentDescription","@content-desc");
								System.out.println(sLocatorValue);
							}
							else
								System.out.println("Web app but Outside xpath replace code segment");
						}
						System.out.println("Xpath Locator for Appium after update  : "+sLocatorValue);
					}
					objBy = (By.xpath(sLocatorValue));
					break;
				case "CSS":
				case "CSSSELECTOR":
					objBy = (By.cssSelector(sLocatorValue));
					break;
				case "CLASSNAME":
					objBy = (By.className(sLocatorValue));
					break;
				case "ID":
					objBy = (By.id(sLocatorValue));
					break;
				case "NAME":
					objBy = (By.name(sLocatorValue));
					break;
				case "LINKTEXT":
					objBy = (By.linkText(sLocatorValue));
					break;
				case "PARTIALLINKTEXT":
					objBy = (By.partialLinkText(sLocatorValue));
					break;
				case "TAGNAME":
					objBy = (By.tagName(sLocatorValue));
					break;
			}
			if (objBy==null)
				throw new Exception("Incorrect object identification string - " + sIdentification);
			
			if (sToolName=="SELENIUM"){
				oSelClient.boolBringElementToFocus=true;
				if (bCheckElementExist && (!oSelClient.ElementExists(objBy, 0)))
					throw new Exception("Unable to identify object matching " + sLocator + "=" + sLocatorValue);
			}
			
			if (sToolName=="APPIUM"){
				oAppiumClient.boolBringElementToFocus=true;
				if (bCheckElementExist && (!oAppiumClient.ElementExists(objBy, 5000)))
					throw new Exception("Unable to identify object matching " + sLocator + "=" + sLocatorValue);
			}
			return objBy;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//Return ByObject from identification string
	public By fnGetByObject(String sElementIdentification,boolean bCheckElementExist){
		return GetByObject(sElementIdentification,bCheckElementExist);
	}
	//Get the url (Remove prefix like chrome:http://www...)
	public String fnRemoveBrowserNameFromUrl(String sUrl){

		return Utility.RegExpExtract(sUrl, "(https?:.*)");
	}
	public String fnGetToolName(){
		return sToolName;
	}
	//Wait for specified no of milliseconds\
	public boolean fnExplicitWait(int iMilliSeconds) {
		try {
			Thread.sleep(iMilliSeconds);
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void fnEnableDebug(Object objClient){
		
	//	objDebugger.fnRegisterClient(objClient);
		
		switch(sToolName){
			case "SEETEST":
				
				//NO DEBUG Required
				break;
			case "SELENIUM":
				//oSelClient.fnEnableDebug(this);
				break;
			case "APPIUM":
				//TO DO
				break;
			default:

		}
	}
	
	public void ActivateFrame(String sElement) throws Exception{
		By objBy;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				
				sLastActionStatus="SeeTest SendText operation - Text send : " ;
				sLastError="";
				break;
			case "SELENIUM":
				if ((sElement==null) || sElement.isEmpty()) //Switch to parent frame
					oSelClient.ActivateFrame();
				else{
					objBy=GetByObject(sElement,true);
					oSelClient.ActivateFrame(objBy);
				}
				break;
			case "APPIUM":
				if ((sElement==null) || sElement.isEmpty()) //Switch to parent frame
					oAppiumClient.ActivateFrame();
				else{
					objBy=GetByObject(sElement,true);
					oAppiumClient.ActivateFrame(objBy);
				}
				break;
			default:
				sLastError="sendText operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	//User defined operation
	public void TypeText(String sZone, String sElement,int iIndx,String sTextVal, int iTypeDelay) throws Exception{
		By objBy=null;
		ExecuteBeforeAction();
		switch(sToolName){
			case "SEETEST":
				for(char c : sTextVal.toCharArray()) {
					fnExplicitWait(iTypeDelay);
					oSTclient.elementSendText(sZone, sElement, iIndx, String.valueOf(c));
				}
				oSTclient.elementSendText(sZone, sElement, iIndx, sTextVal);
				sLastActionStatus="SeeTest TypeText operation - Text send '" + sTextVal + "' to Element " + sElement;
				sLastError="";
				break;
			case "SELENIUM":
				objBy=GetByObject(sElement,true);
				//Click on the element to activate
				oSelClient.Element(objBy).click();
				for(char c : sTextVal.toCharArray()) {
					fnExplicitWait(iTypeDelay);
					oSelClient.Element(objBy).sendKeys(String.valueOf(c));
				}
				sLastActionStatus="Selenium TypeText operation - Text send '" + sTextVal + "' to Element " + sElement;
				sLastError="";
				break;
			case "APPIUM":
				objBy=GetByObject(sElement,true);
				//Click on the element to activate
				oAppiumClient.click(objBy);
				for(char c : sTextVal.toCharArray()) {
					fnExplicitWait(iTypeDelay);
					oAppiumClient.GetNativeClient().getKeyboard().sendKeys(String.valueOf(c));
				}
				oAppiumClient.CloseKeyboard();
				sLastActionStatus="Appium TypeText operation - Text send '" + sTextVal + "' to Element " + sElement;
				sLastError="";
				break;
			default:
				sLastError="TypeText operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
		}
		ExecuteAfterAction();
	}
	//Generic flags
	public class Flags{
		public boolean REPLACE_EXISTING_DATA_FOR_ELEMENT_SEND_TEXT=false;
	}
	
	//Searching new free port.
	 public static Integer nextFreePort() {
	     Integer port = (int)( Math.random() * 8000 ) + LEAST_PORT;
	     while (true) {
	         try ( ServerSocket endpoint = new ServerSocket(port) ) {
	             return port;
	         } catch (IOException e) {
	             port = ThreadLocalRandom.current().nextInt();
	         }
	     }
	 }
	//Start appium server 
	public boolean appiumStart(String sNodePath, String sAppiumJSPath, String sServerIP, String sServerPort,String sBootStrapPort,String sChromDriverPort) {
		try{
			System.out.println("Going to Start Appium on Server " + sServerIP + ":"+sServerPort);
			CommandLine command = new CommandLine("cmd");
			command.addArgument("/c");
			command.addArgument(sNodePath);
			command.addArgument(sAppiumJSPath);
			command.addArgument("--address");
			command.addArgument(sServerIP);
			command.addArgument("--port");
			command.addArgument(sServerPort);
			command.addArgument("--bootstrap-port");
			command.addArgument(sBootStrapPort);
			command.addArgument("--chromedriver-port");
			command.addArgument(sChromDriverPort);

			//Get the chrome driver path from QA Accelerator resource folder
			String sChromeDriverPath=Utility.GetResourcePath("/drivers/chromedriver.exe");
			//sChromeDriverPath="D:\\Anils\\HnR\\Automation\\Appium\\ChromeDrivers\\2.23\\chromedriver_2.23\\chromedriver.exe";
			if (!sChromeDriverPath.isEmpty()){		
				if(sChromeDriverPath.startsWith("/"))
					sChromeDriverPath =sChromeDriverPath.substring(1); //to remove '/' from the beginning of the path
				command.addArgument("--chromedriver-executable");
				command.addArgument(sChromeDriverPath);
				System.out.println("Chrome driver path : " + sChromeDriverPath);
			}
			command.addArgument("--log-level");
			command.addArgument("warn:error");  //Show only warnings or error logs from Appium
			command.addArgument("--no-reset");
			//		  command.addArgument("--log");
			//		  command.addArgument("D://appiumLogs.txt");
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);
			
		
			executor.execute(command, resultHandler);
			// Wait for sometimes so that appium server can start properly before going for test execution.
			Thread.sleep(5000);
			return true;
		}
		catch(Exception e){
			System.out.println("Error while Starting Appium : " + e.getMessage());
			return false;
		}
	 }
	//Stop appium server 
	public boolean appiumStop(String Port)  {
		
		try{
//			System.out.println("Going to Kill Appium Server, if running...");
//			// Add different arguments In command line which requires to stop appium server.
//			  CommandLine command = new CommandLine("cmd");
//			  command.addArgument("/c");
//			  command.addArgument("taskkill");
//			  command.addArgument("/F");
//			  command.addArgument("/IM");
//			  command.addArgument("--port");
//			  command.addArgument(port);
//			  command.addArgument("node.exe");
//			  
//			  // Execute command line arguments to stop appium server.
//			  DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
//			  DefaultExecutor executor = new DefaultExecutor();
//			  executor.setExitValue(1);
//			  executor.execute(command, resultHandler);
			
			//Command to Kill PID using Port id
		 	String sCommandToKillPID ="for /f \"tokens=5\" %%a in ('netstat -aon ^| find \""+Port+"\"') do taskkill /f /pid %%a";
		 
		 	
		 	//Creating a Bat file to kill PID
			String sPath= System.getProperty("java.io.tmpdir");
		   	PrintWriter writer = new PrintWriter(sPath+"/KillProcess.bat", "UTF-8");
		    writer.println(sCommandToKillPID);
		    writer.close();
		    
		    //Killing the PID
			Process p = Runtime.getRuntime().exec(sPath+"/KillProcess.bat");
			p.waitFor();
				
			//Deleting the bat file			
			File file = new File(sPath+"/KillProcess.bat");	 
			file.delete();
			
			fnExplicitWait(5000);
			return true;
		}
		catch(Exception e){
			System.out.println("Error while stopping Appium : " + e.getMessage());
			return false;
		}
	}
	//Set Context for appium
	public String SetAppiumContext(String sContext){
		String sNewContext;
		switch(sToolName){
			case "APPIUM":
				sNewContext= oAppiumClient.SetContext(sContext);
				return sNewContext;
			default:
				sLastError="SetAppiumContext operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
				return "";
		}
	} 
	//Set the first available Web view context for appium
	public String SetAppiumWebViewContext(){
		String sNewContext;
		switch(sToolName){
			case "APPIUM":
				sNewContext= oAppiumClient.SetWebViewContext();
				return sNewContext;
			default:
				sLastError="SetAppiumWebViewContext operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
				return "";
		}
	}
	//Wait for Web view context in appium
	public boolean WaitForAppiumWebView(int iTimeout, int iPolling){
		switch(sToolName){
			case "APPIUM":
				return oAppiumClient.WaitForWebView(iTimeout, iPolling);
			default:
				sLastError="SetAppiumWebViewContext operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
				return false;
		}
	}
	

	
	//Set Native app context for appium
	public String SetAppiumNativeAppContext(){
		
		switch(sToolName){
			case "APPIUM":
					String sContext=oAppiumClient.SetNativeAppContext();
					return sContext;
			default:
				sLastError="SetAppiumNativeAppContext operation not implemented for the Tool " + sToolName ;
				System.out.println(sLastError);
				return "";
		}
	}
}

