package com.ustglobal.seetestclient;

import java.io.File;
import java.util.Date;
import java.util.Map;

import com.experitest.client.Client;
import com.ustglobal.common.Utility;
import com.ustglobal.common.Utility.CustomReporter;

public class SeeTestClient extends Client{
	private CustomReporter reporter=null;
	private LastResult lastresult=null;
	private int nIndexLastSnapShot = -1;
	private String sRunDataFolderPath=null;
	private int nIndexIntermediateSteps = 0;
	private String sRunningDevice=null;
	private static boolean bTakeIntermediateScreenShots=true;
	public SeeTestClient(String sDeviceName,String sProjectBaseDirectory,boolean TakeIntermediateScreenShots) throws Exception{
		this("localhost",8889, sDeviceName,sProjectBaseDirectory,bTakeIntermediateScreenShots);
	}
	//Return the reporter object
	public CustomReporter fnGetNativeReporter(){
		return reporter;
	}
	public SeeTestClient(String sHost, int iPort,String sDeviceName,String sProjectBaseDirectory,boolean TakeIntermediateScreenShots) throws Exception{
		super(sHost,iPort,false);
		super.setDevice(sDeviceName);
		sRunningDevice=sDeviceName;
		if (!sProjectBaseDirectory.isEmpty())
			super.setProjectBaseDirectory(sProjectBaseDirectory);
		//Generate report in temp folder, this is just to enable screenshot caputring for emulator, SeeTest report will not be generated
		String sPathPDF=System.getProperty("user.dir")+"\\test-report";
		String sSeeTestReportFolder=System.getProperty("java.io.tmpdir")+"\\SeeTest-report";
		//Create test report folder
		Utility.CreateFolder(sPathPDF,false);
		//Enable see test report generation - This is required for android emulator 
		//On emulators, screenshot will not be generated if this option is not set
		super.setReporter("xml",sSeeTestReportFolder ,Utility.GetUniqueTestNameIdentifier());
		super.setShowImageInReport(true);
		sPathPDF=sPathPDF+"\\"+Utility.GetUniqueTestNameIdentifier()+".pdf";
	
		//Utility.DisplaySystemTrayMenu((FinalizableInterface) this); // TO open System tray Stop Button
		bTakeIntermediateScreenShots=TakeIntermediateScreenShots;
		lastresult=new LastResult();
		reporter=new CustomReporter("Mobile Automation Lab:Test Automation Report", sPathPDF);
		reporter.nImageScalePercentage=50; // Reduce PDF size to 60% by compressing embedded images 
		DeviceDetails deviceDetails = new DeviceDetails();
		// Add Test case name to Summary Page . This is done in base implementation.		
		reporter.AddToSummary("Device Manufacturer", deviceDetails.devicemanufacture);// Add Device Manufacture name to Summary Page.
		reporter.AddToSummary("Device Model", deviceDetails.devicemodel);				// Add Device model to Summary.
		reporter.AddToSummary("OS", deviceDetails.os);								// Add Mobile OS name to Summary Page.
		reporter.AddToSummary("Version", deviceDetails.version);						// Add Mobile Version name to Summary Page.
		reporter.AddToSummary("Date and Time", deviceDetails.datetime);
	
		
	}
	
	public void Finalize() throws Exception{
		reporter.Finalize();
		super.releaseClient();
	}
	@Override
	public void launch(String strURL, boolean boolInstrument, boolean boolStopIfRunning){
		super.launch(strURL, boolInstrument, boolStopIfRunning);
		//Try to get the first screenshot from see test LastcommandResultpMap
		lastresult.Refresh();
		String sFirstScreenShot=lastresult.sPathToSnapshot;
		System.out.println("Screenshot from Commandmap " + sFirstScreenShot);
		//Capture screenshot explicitly if screenshot file name not found in SeeTest LastCommandResultMap
		if (sFirstScreenShot==null){
			System.out.println("Image file name missing in command map, explicit screen capture");
			sFirstScreenShot=super.capture();
			System.out.println("explict screen capture " + sFirstScreenShot);
		}
		if (sFirstScreenShot!=null){
			File file = new File(sFirstScreenShot);
			//Extract file no 
			nIndexLastSnapShot = Integer.parseInt(file.getName().replaceAll("[^0-9]", ""));
			sRunDataFolderPath = file.getParent();			// Get parent class path to save the screenshots.
		}
		else
			System.out.println("Unable to capture screenshot on app launch");
	}
	public void Log(String sStepDesc ) throws Exception{
		lastresult.Refresh();
		//this.Log(sStepDesc.length()>15?(sStepDesc.substring(0,15)+".."):sStepDesc, sStepDesc,  lastresult.sPathToSnapshot);
		this.Log(sStepDesc, sStepDesc,  lastresult.sPathToSnapshot);
	}
	
	public void Log(String sStepDesc , String sLoadTime) throws Exception{
		lastresult.Refresh();
		//this.Log(sStepDesc.length()>15?(sStepDesc.substring(0,15)+".."):sStepDesc, sStepDesc, sLoadTime, lastresult.sPathToSnapshot);
		this.Log(sStepDesc, sStepDesc, sLoadTime, lastresult.sPathToSnapshot);
	}
	
	public void Log(String sStepName, String sStepDesc,String sLoadTime) throws Exception{
		lastresult.Refresh();
		this.Log(sStepName, sStepDesc, sLoadTime, lastresult.sPathToSnapshot);
	}		
	
	public void Log(String sStepName, String sStepDesc, String sLoadTime, String sPathIMG) throws Exception{

		if ((sPathIMG==null)||(!Utility.IsFileOrFolderExist(sPathIMG))) {
			//If image missing Try to get the last screenshot from run data folder - For android emulator
			sPathIMG=super.capture();
			//If RunDataFolder is empty, then get it from captured file, this is needed for GetLastFileNameFromSeeTestRunDataFolder()
			if ((sRunDataFolderPath==null)&&(sPathIMG!=null)){
				File ofile = new File(sPathIMG);
				sRunDataFolderPath=ofile.getParent();
			}
			String sLastScreenShotPath=GetLastFileNameFromSeeTestRunDataFolder(nIndexLastSnapShot);
			if(sLastScreenShotPath.isEmpty()){
				// Write text log with out image if last screenshot is not found in Run data folder
				reporter.Log(sStepName+"[FONT:BOLD]", sStepDesc);
				return;
			}
			else
				sPathIMG=sLastScreenShotPath;
		}
		
		File file = new File(sPathIMG);
		String sPathIMGInterim = null;			
		int nIndexNewSnapShot = Integer.parseInt(file.getName().replaceAll("[^0-9]", ""));	
		// Get filename of screenshot after trimming the path.
		
		if(bTakeIntermediateScreenShots==true){
			for (int nIndex=(nIndexLastSnapShot+1); nIndex<nIndexNewSnapShot; nIndex++){
				sPathIMGInterim = sRunDataFolderPath + "\\" + nIndex + ".PNG";
				//super.Log("Intermediate Step " + (++nIndexIntermediateSteps), "These are intermediate step actions (No description available)",  sLoadTime,  sPathIMGInterim);
				reporter.Log("Intermediate Step " + (++nIndexIntermediateSteps), "These are intermediate step actions (No description available)",  sPathIMGInterim);
			}	
	
			reporter.Log(sStepName+"[FONT:BOLD]", sStepDesc, sPathIMG);
			nIndexLastSnapShot = nIndexNewSnapShot;
		}
		else{
			reporter.Log(sStepName+"[FONT:BOLD]", sStepDesc, sPathIMG);
			nIndexLastSnapShot = nIndexNewSnapShot;
		}
	}
	//Write a text log without image
	public void LogText(String sLogItem) throws Exception{
		reporter.Log(sLogItem);
	}
	//Set a  native or web context
	public String SetContext(String sContext){
		
		try{
			
			if(sContext.trim().equalsIgnoreCase("NATIVE")){
				this.setProperty("android.native.nonInstrumented", "true");
				return "NATIVE";
			}
			else{
				this.setProperty("android.native.nonInstrumented", "false");
				return "WEB";
			}
		}
		catch(Exception e){
			System.out.println("Error while setting context : " + sContext + ", Error : " + e.getMessage());
			return "";
		}
	}
	private class DeviceDetails{// Class to obtain Device details and Date during a Device execution.
		String os = null;
		String devicemanufacture = null;
		String devicemodel = null;
		String datetime = null;
		String version = null;
		Date date = new Date();
		String details;	// Device information in XML format as a String is returned.
		
		public DeviceDetails (){	
			details =getDevicesInformation();	// Device information in XML format as a String is returned.
			datetime = date.toString();	// Fetch Execution time during execution.
			devicemodel = Utility.QueryFromXML(details, "/devices/device/@model");
			devicemanufacture = Utility.QueryFromXML(details, "/devices/device/@manufacture");
			os = Utility.QueryFromXML(details, "/devices/device/@os");
			version = Utility.QueryFromXML(details, "/devices/device/@version");
		}
	}
	
	private class LastResult{
		String sStatus = null;
		String sDetails = null;
		String sPathToSnapshot = null;
		public void Refresh(){

			Map<String, Object>  mapLastResult = getLastCommandResultMap();
			sStatus = (boolean) mapLastResult.get("status")?"Pass":"Fail";
			sPathToSnapshot = null;
			sPathToSnapshot = (String) mapLastResult.get("outFile");
			//If outfile missing in map, then exit - Happens for android emulator
			if (sPathToSnapshot==null) return;
			sDetails = getLastCommandResultMap().toString().replace("outFile="+sPathToSnapshot, "");
			//Wait for some time for outfile creation
			try {
				int iPollingCount=0;
				while (!Utility.IsFileOrFolderExist(sPathToSnapshot) && ++iPollingCount<=20){
					Thread.sleep(500);
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}   
	
	
	//Return the last screenshot file name from seetest run data folder starting from a base filename no
	public String GetLastFileNameFromSeeTestRunDataFolder(int iCurrentLastFileNo){
		int iFileNo=iCurrentLastFileNo+1;
		
		try{
	
			while(Utility.IsFileOrFolderExist(sRunDataFolderPath + Integer.toString(iFileNo) + ".png"))
				iFileNo++;
			iFileNo--;
			
			if (iFileNo>iCurrentLastFileNo)
				return sRunDataFolderPath + "\\" + Integer.toString(iFileNo) + ".png";
			else
				return "";
		}
		catch(Exception e){
			System.out.println("GetLastFileNameFromSeeTestRunDataFolder() - > Error while extracting Last file name from Run data folder : " + e.getMessage());
			return "";
		}
		
	}
	
	
}
