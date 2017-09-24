package com.ustglobal.common;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import com.ustglobal.common.Utility.FinalizableInterface;


public class SystemTrayMonitor {
	public SystemTrayMonitor(FinalizableInterface objToFinalize) throws Exception{		
		
		if (!SystemTray.isSupported()) {
	        System.out.println("SystemTray is not supported");
	        return;
	    }
		final URL imageURL = SystemTrayMonitor.class.getResource("/images/Tray_Running.gif");                
		final java.awt.Image imgIcon = new ImageIcon(imageURL, "tray icon").getImage();		
		final SystemTray tray = SystemTray.getSystemTray(); //Access the system tray	                
		final TrayIcon trayIcon = new TrayIcon(imgIcon); //Make a trayIcon to be added to system tray
		final Thread objThreadCaller = Thread.currentThread();	    
	    try {
	        trayIcon.setImageAutoSize(true);	                
	        tray.add(trayIcon); //Add trayIcon to system tray
	        trayIcon.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent event){
					// TODO Auto-generated method stub			
					if(SwingUtilities.isLeftMouseButton(event)){
						try {
							URL imageURL = SystemTrayMonitor.class.getResource("/images/Tray_Exiting.gif");                
	    	                java.awt.Image imgIcon = new ImageIcon(imageURL, "tray icon").getImage(); 
	    					trayIcon.setImage(imgIcon);
	    					objThreadCaller.interrupt();
						} catch (Exception e) {
							//e.printStackTrace();
							System.out.println("WARNING: There is an exception thrown while stopping a thread");
						}
					}	                        
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {}
				@Override
				public void mouseExited(MouseEvent arg0) {}
				@Override
				public void mousePressed(MouseEvent arg0) {}
				@Override
				public void mouseReleased(MouseEvent arg0) {}
			});            
	        
		} catch (AWTException e) {
			e.printStackTrace();
		}
	
	    ExecutorService executor = Executors.newFixedThreadPool(1);		
		executor.execute( new Runnable() { //Run asynchronously
	        @Override
			public void run() {
	        	String strThreadStatus="RUNNABLE";
	        	while(true){
	            	try {
						Thread.currentThread();
						Thread.sleep(1000);//monitor polling interval
						strThreadStatus = objThreadCaller.isInterrupted()?"INTERRUPTED":objThreadCaller.getState().toString();
					}
	            	catch (InterruptedException e1) {
						e1.printStackTrace();
					}
	            	if (strThreadStatus.equals("TERMINATED")||strThreadStatus.equals("WAITING")||strThreadStatus.equals("INTERRUPTED")){
	            		break;
	            	}
	        	}
	        	try {
	        		URL imageURL = SystemTrayMonitor.class.getResource("/images/Tray_Exiting.gif");                
	                java.awt.Image imgIcon = new ImageIcon(imageURL, "tray icon").getImage(); 
					trayIcon.setImage(imgIcon);
					objThreadCaller.suspend(); 
					try{					
						objToFinalize.Finalize(strThreadStatus);
					}
					catch(Exception e){
						System.out.println("Exception caught in thread - SystemTrayMonitor()");
					}
					tray.remove(trayIcon);
					objThreadCaller.stop(); //important for BatchRunner logic, though this is depreciated
					objThreadCaller.join(); //Thread.join was healthy compared to Thread.stop wrt the parent thread BatchRunner										
				}
	        	catch (Exception e) {
					//e.printStackTrace(); //comment this to avoid unnecessary red line warnings            		
	        		System.out.println("WARNING: There is an exception thrown while stopping a thread - SystemTrayMonitor()");
				}
				executor.shutdown();
	        }
		});
	}
}
