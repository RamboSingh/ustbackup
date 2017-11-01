package com.ustglobal.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;


public class Packaging {
	static boolean boolForceOverwrite = false;
	
	public static void RunTestNG (String strPathPackageSlashXML) throws Exception{
		try {
			Utility.KillAllBrowsers();
			ExtractFromJar("/drivers/chromedriver.exe");
			ExtractFromJar("/drivers/IEDriverServer.exe");
			ExtractFromJar("/images/Logo.jpg");
			String sPathXML = ExtractFromJar(strPathPackageSlashXML);
			System.out.println("Starting............");
			TestNG testNG = new TestNG();
			List<String> xmlList = new ArrayList<>();
			List<XmlSuite> xmlSuiteList = new ArrayList<>();
			xmlList.add(sPathXML);
			XmlSuite suite = new XmlSuite();
			suite.setSuiteFiles(xmlList);
			xmlSuiteList.add(suite);
			testNG.setXmlSuites(xmlSuiteList);
			testNG.run();
			Utility.MessageBox("Run completed");
		} 
		catch (Throwable exception) {
			System.out.println("Self executable run exception");
			exception.printStackTrace();
			Utility.MessageBox(exception.toString());
		}
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static String ExtractFromJar(String fileName) throws Exception{			  
		  String fileNameDest = fileName.replaceAll("/", ".");
		  fileNameDest=fileNameDest.replaceFirst("\\.",""); //to replace first dot
		  File exeTempFile = new File(fileNameDest);
		  if(exeTempFile.exists()){
			  if(boolForceOverwrite){
				  System.out.println("Renew file:" + exeTempFile.getAbsolutePath());
				  exeTempFile.delete();
				  exeTempFile.createNewFile();
			  }
			  else {
				  System.out.println("File already exists:" + exeTempFile.getAbsolutePath());
				  return exeTempFile.getAbsolutePath();
			  }
		  }
		  else {
			  System.out.println("Extracted file:" + exeTempFile.getAbsolutePath());
		  }
		  InputStream src = Packaging.class.getResource(fileName).openStream();
		  FileOutputStream out = new FileOutputStream(exeTempFile);
		  byte[] temp = new byte[32768];
		  int rc;
		  while((rc = src.read(temp)) > 0)
		      out.write(temp, 0, rc);
		  src.close();
		  out.close();		  
		  return exeTempFile.getAbsolutePath();
	}
	
	/*public static void RunJavaClass(String strPackageClassName, String[] arrayParams){	
		
		SwingUtilities.invokeLater(new Runnable() { //Run asynchronously
            public void run() {
            	Class<?> clazz;
        		final Object[] args = new Object[1];
        		args[0] = arrayParams;
        		try {
        			clazz = Class.forName(strPackageClassName);
            	    final Method method = clazz.getMethod("main", String[].class);    	    
            	    method.invoke(strPackageClassName, args);
        		} catch (ClassnotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | noSuchMethodException | SecurityException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
            }
		});
		
	}*/
	
	/*public static void RunJavaClass(String strPackageClassName, String[] arrayParams){	
	
	SwingUtilities.invokeLater(new Runnable() { //Run asynchronously
        public void run() {
        	Class<?> clazz;
    		final Object[] args = new Object[1];
    		args[0] = arrayParams;
    		try {
    			clazz = Class.forName(strPackageClassName);
        	    final Method method = clazz.getMethod("main", String[].class);    	    
        	    method.invoke(strPackageClassName, args);
    		} catch (ClassnotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | noSuchMethodException | SecurityException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
	});	
	}*/
	
}
