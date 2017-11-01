package com.ustglobal.common;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.openqa.selenium.By;

import com.google.common.io.Files;
import com.ustglobal.appium.AppiumClient;
import com.ustglobal.common.Utility.FinalizableInterface;
import com.ustglobal.genericclient.GenericClient;
import com.ustglobal.selenium.SInteractive;
import java.awt.ScrollPane;

public class Debugger {
	
	private Dimension dimensionFrame;
	private HashMap<String, Object> objHashMapClients = new HashMap<>();
	private String strCoreBaseClientName = "";
	private String sTestCaseName;
	private String strWorkingFolder;
	private String strDynamicClassName;
	private String strJavaClassName;
	private boolean bNoSessionError=false;
	public  Object objClientDebug=null;
	public boolean boolIsLaunched=false;
	public boolean boolFirstInspectClick=true;
	private final String strDebugFolderName="Debugger";
	
	private JFrame frameMain = null;
	private JTextField txtboxHighlighter = null;	
	private JComboBox listboxHighligter = null;
	private JTextPane lblHighlighter  = null;
	private JComboBox listboxClientNames  = null;
	private JButton btnExecute=null;
	private JTextArea txtrObjectInitialization;
	
	public static void main(String[] args) throws Exception { //Testing area. Comment the main( ) function once your design is complete.		
		Debugger objDebugger = new Debugger();
		objDebugger.Launch();
	}
	
	public Debugger(Object objClient) {
		this(); 
		RegisterClient(objClient); //Client registration along with constructor itself. Easy to constuct and register in one line.
	}
	
	public Debugger() {
		//Empty constructor. Assumption is that user would RegisterClient( ) sometime later, or many times later
		sTestCaseName = Utility.GetTestCaseClassName();
	}
	public Debugger RegisterClient(Object objClient){ //The return is enabled so you can use variable = new Debugger().RegisterClient(client);
		Class classClient = objClient.getClass();
		while(!classClient.getSimpleName().equals("Object")){
			if (!objHashMapClients.containsKey(classClient.getSimpleName())){ //Ignores re-registration
				System.out.println("Debugger is registering client of type " + classClient.getSimpleName());
				switch(classClient.getSimpleName()){
				case "SInteractive":
					objHashMapClients.put(classClient.getSimpleName(), (SInteractive) objClient);
					break;
				case "AppiumClient":
					objHashMapClients.put(classClient.getSimpleName(), (AppiumClient) objClient);
					break;
				case "GenericClient":
					objHashMapClients.put(classClient.getSimpleName(), (GenericClient) objClient);
					break;
				default://Business clients
					objHashMapClients.put(classClient.getSimpleName(), objClient);
				}				
			}
			classClient = classClient.getSuperclass();
		}
		return this;
	}
	
	public void Launch(){
		if(boolIsLaunched){
			System.out.println("A call to Debugger.Launch() is ignored since it is already launched");
			return;
		}
		boolIsLaunched = true;
		System.out.println("Launching Debugger for test case - " + sTestCaseName);
		
		dimensionFrame = Toolkit.getDefaultToolkit().getScreenSize();
		int nWidthFrame = dimensionFrame.width/3;
		int nXPosStartup = dimensionFrame.width - nWidthFrame;
		int nHeightFrame = dimensionFrame.height - 50;
		
		frameMain=new JFrame();
		frameMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //EXIT_ON_CLOSE causes parent batchrunner and all sibling testcase thread to close
		frameMain.setAlwaysOnTop(true);
		frameMain.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		frameMain.setTitle("Debugger - "+sTestCaseName);
		frameMain.setBounds(nXPosStartup, 0, 583, 901);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frameMain.setContentPane(contentPane);

		JPanel HighlighPanel = new JPanel();
		HighlighPanel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Element Highlighter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		HighlighPanel.setBackground(SystemColor.controlHighlight);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Script Executor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.controlHighlight);
		
		JPanel ElementInspectPanel = new JPanel();
		ElementInspectPanel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Element Inspector", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		ElementInspectPanel.setBackground(SystemColor.controlHighlight);
		
		JTextArea txtAElementAttributes = new JTextArea();
		txtAElementAttributes.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		txtAElementAttributes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JScrollPane scrollPanetxtrElementAttributes = new JScrollPane(txtAElementAttributes,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		JButton btnInspect = new JButton("INSPECT");
		btnInspect.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInspect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SInteractive  client=(SInteractive) objClientDebug; 
				try {
				
					final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
					CLIPBOARD.setContents(new StringSelection(""), null);
					client.ExecuteJavaScript(Files.toString( new File(Utility.GetResourcePath("/template_javascript/StartInspect.js")), Charset.forName("utf-8")));			
					JOptionPane.showMessageDialog(frameMain, "Use mouse pointer to hightlight any web object.\nClick the element to copy its xpath/attributes into the clipboard.\nClick OK to stop inspect.\nThis also copies your latest clipboard to the scatchpad area.", "Inspect Help", JOptionPane.INFORMATION_MESSAGE);
					txtAElementAttributes.setText(getClipboardContents());//Copying last 
					if (client.IsAlertDisplayed())client.CloseAlert(true);//close alert if user forget to close
					client.ExecuteJavaScript(Files.toString( new File(Utility.GetResourcePath("/template_javascript/StopInspect.js")), Charset.forName("utf-8")));
				
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_ElementInspectPanel = new GroupLayout(ElementInspectPanel);
		gl_ElementInspectPanel.setHorizontalGroup(
			gl_ElementInspectPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_ElementInspectPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ElementInspectPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPanetxtrElementAttributes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
						.addComponent(btnInspect, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_ElementInspectPanel.setVerticalGroup(
			gl_ElementInspectPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_ElementInspectPanel.createSequentialGroup()
					.addComponent(scrollPanetxtrElementAttributes, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInspect, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		ElementInspectPanel.setLayout(gl_ElementInspectPanel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(ElementInspectPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
				.addComponent(HighlighPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(ElementInspectPanel, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(HighlighPanel, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
					.addGap(5))
		);
		JLabel lblLocator = new JLabel("Locator :");
		lblLocator.setFont(new Font("Tahoma", Font.BOLD, 13));		
		listboxHighligter = new JComboBox();
		JButton btnHighlight = new JButton("HIGHLIGHT");
		btnHighlight.setToolTipText("");
		//TODO : Design should be changed
		listboxHighligter.addItem("XPATH");
		listboxHighligter.addItem("ID");
		listboxHighligter.addItem("NAME");
		listboxHighligter.addItem("CSSSELECTOR");
		listboxHighligter.addItem("CLASSNAME");
		listboxHighligter.addItem("LINKTEXT");
		listboxHighligter.addItem("PARTIALLINKTEXT");
		listboxHighligter.addItem("TAGNAME");	
		listboxHighligter.setSelectedItem(0);
		
		btnHighlight.setFont(new Font("Tahoma", Font.BOLD, 13));
		JLabel lblvalue = new JLabel("Value :");
		lblvalue.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtboxHighlighter = new JTextField();
		txtboxHighlighter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtboxHighlighter.setHorizontalAlignment(SwingConstants.LEFT);
		txtboxHighlighter.setColumns(3);
		JScrollPane scrollpaneHighlighterText = new JScrollPane(txtboxHighlighter,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	

		lblHighlighter = new JTextPane();
		lblHighlighter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHighlighter.setText("Locator code generation");
		lblHighlighter.setEditable(false); // as before
		lblHighlighter.setBackground(null); // this is the same as a JLabel
		lblHighlighter.setBorder(null); // 
		
		
		GroupLayout gl_HighlighPanel = new GroupLayout(HighlighPanel);
		gl_HighlighPanel.setHorizontalGroup(
			gl_HighlighPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_HighlighPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_HighlighPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_HighlighPanel.createSequentialGroup()
							.addComponent(lblHighlighter, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_HighlighPanel.createSequentialGroup()
							.addComponent(lblLocator)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(listboxHighligter, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblvalue)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollpaneHighlighterText, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnHighlight, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(11))))
		);
		gl_HighlighPanel.setVerticalGroup(
			gl_HighlighPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_HighlighPanel.createSequentialGroup()
					.addGroup(gl_HighlighPanel.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(listboxHighligter, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLocator, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblvalue, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnHighlight, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollpaneHighlighterText, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblHighlighter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
		);
		HighlighPanel.setLayout(gl_HighlighPanel);
		JLabel label = new JLabel("Choose Client :");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		listboxClientNames = new JComboBox();
		
		btnExecute = new JButton("EXECUTE");
		btnExecute.setToolTipText("You can execute the code below, or select few lines and right click to execute only that portion");
		btnExecute.setFont(new Font("Tahoma", Font.BOLD, 13));
		JTextArea txtrImports = new JTextArea();
		JScrollPane scrollPanetxtrImports = new JScrollPane( txtrImports,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanetxtrImports.setViewportView(txtrImports);
		txtrImports.setText("//imports \n");
		txtrImports.setForeground(Color.BLUE);
		txtrImports.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrObjectInitialization = new JTextArea();
		txtrObjectInitialization.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtrObjectInitialization.setText("<dynamic> client=(<dynamic>) objClientDebug ;");
		txtrObjectInitialization.setForeground(Color.GRAY);
		txtrObjectInitialization.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrObjectInitialization.setEditable(false);
		
		JTextArea txtrCode = new JTextArea();
		txtrCode.setToolTipText(" select few lines and right click to execute only that portion");		
		txtrCode.setForeground(Color.BLUE);
		txtrCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JScrollPane scrollPanetxtrCode = new JScrollPane(txtrCode,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		


	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPanetxtrCode, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
						.addComponent(txtrObjectInitialization, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
						.addComponent(scrollPanetxtrImports)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(listboxClientNames, 0, 150, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGap(5)
							.addComponent(btnExecute, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(listboxClientNames, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExecute, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPanetxtrImports, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrObjectInitialization, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPanetxtrCode, GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
					.addGap(7))
		);
	
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	
		try{	
			String sClientName=objClientDebug.getClass().getSimpleName();
		    txtrObjectInitialization.setText(sClientName + " client=("+sClientName+") objClientDebug ;");
		}
		catch(Exception e){
			bNoSessionError=true;
		}
		
		//Adding client object into dropdown
	    Iterator it = objHashMapClients.entrySet().iterator();
	    while (it.hasNext()) { //Drills down those sub-standard clients like business clients, generic clients/wrappers etc
	        Map.Entry pair = (Map.Entry)it.next();
	        listboxClientNames.addItem(pair.getKey());
	        switch(pair.getKey().toString()){
		        case "SInteractive":
		        	strCoreBaseClientName = "SInteractive";
		        	break;
		        case "AppiumClient":
		        	strCoreBaseClientName = "AppiumClient";
		        	break;
		        case "SeeTestClient":
		        	strCoreBaseClientName = "SeeTestClient";
		        	break;
	        }
	    } 
	    listboxClientNames.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
		 		objClientDebug=objHashMapClients.get(listboxClientNames.getSelectedItem());
				String sClientName=objClientDebug.getClass().getSimpleName();
				txtrImports.setText("//imports \n");
				txtrImports.append("import org.openqa.selenium.*;\n");
			    txtrImports.append("import " + objClientDebug.getClass().getName() +";\n");
			    txtrObjectInitialization.setText(sClientName + " client=("+sClientName+") objClientDebug ;");
			}
		});
	    if(objHashMapClients.size()!=0){
			listboxClientNames.setSelectedIndex(0);
		}
		
		//All the GUI Event handlers are defined below		
		listboxHighligter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					InvokeHighlighter(false); //false implies - only illustrate the code, do not execute
				}
				catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		
		txtboxHighlighter.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				try {
					InvokeHighlighter(false);//false implies - only illustrate the code, do not execute
				}
				catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
		
		btnHighlight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0){
				try {
					InvokeHighlighter(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}	    
			}
		});
		
		//---------------------Menu for executing highlighted code - 12-july-20217  Abhi
		JPopupMenu menu = new JPopupMenu();
		JMenuItem jMenuExecuteAll = new JMenuItem("Execute All");
		menu.add(jMenuExecuteAll);			
		JMenuItem jMenuExecuteHighlighted = new JMenuItem("Execute highlighted");
		menu.add(jMenuExecuteHighlighted);
		txtrCode.setComponentPopupMenu(menu);
		
		jMenuExecuteAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtrCode.getText() !=null && txtrCode.getText().trim().length()>0 ){
					GenerateAndExecuteDebuggerJavaClass(txtrImports.getText(), txtrObjectInitialization.getText(),txtrCode.getText());
				}
			}
			
		});
		jMenuExecuteHighlighted.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtrCode.getSelectedText() !=null && txtrCode.getSelectedText().trim().length()>0 ){
					GenerateAndExecuteDebuggerJavaClass(txtrImports.getText(), txtrObjectInitialization.getText(), txtrCode.getSelectedText());
				}else{
					JOptionPane.showMessageDialog(frameMain, "Please make a selection first", "Warning...", JOptionPane.WARNING_MESSAGE);
				}	
			}
			
		});
		btnExecute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GenerateAndExecuteDebuggerJavaClass(txtrImports.getText(), txtrObjectInitialization.getText(),txtrCode.getText() );
			}	
		});
		//Handle the close event, delete the clients
		frameMain.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	boolIsLaunched=false;
		    	frameMain.hide();
		    	objClientDebug=objHashMapClients.get(listboxClientNames.getSelectedItem().toString());
		    	//Note: Do not call FinalizableInterface.Finalize("TERMINATED"). Exception flag is set already, so it repeats calling the Debugger.
		    	//Avoid: ((FinalizableInterface) objClientDebug).Finalize("TERMINATED");
		    	try{
		    		((FinalizableInterface) objClientDebug).Finalize("FINALIZED_BY_DEBUGGER");	    		
			    }
			    catch(Exception e){
			    	e.printStackTrace();
			    }		    	
			    //For removing all debugger files
			    File index = new File(System.getProperty("user.dir")+"\\src\\Debugger\\");
			    String[]entries = index.list();
			    if(entries!=null){
			    	for(String s: entries){
			    		File currentFile = new File(index.getPath(),s);
			    		currentFile.delete();
			    	}
			    }

		    }
		});
		
		frameMain.setVisible(true);
	}

	
	
	 public String getClipboardContents() {
		    String sClipboardData = "";
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    //odd: the Object param of getContents is not currently used
		    Transferable contents = clipboard.getContents(null);
		    boolean hasTransferableText =
		      (contents != null) &&
		      contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		    if (hasTransferableText) {
		      try {
		    	  sClipboardData = (String)contents.getTransferData(DataFlavor.stringFlavor);
		      }
		      catch (UnsupportedFlavorException | IOException ex){
		        System.out.println(ex);
		        ex.printStackTrace();
		      }
		    }
		    return sClipboardData;
		  }
	private void GenerateAndExecuteDebuggerJavaClass(String sImports,String sObjectInitialization,String sCode ){
		
		ExecutorService objDebuggerExecutor = Executors.newFixedThreadPool(1); //count of parallel threads at a time		
		objDebuggerExecutor.execute( new Runnable() {
	        @Override
			public void run() {	
				try {			
					strWorkingFolder=System.getProperty("user.dir")+"\\src\\" + strDebugFolderName +"\\";
					//if(strJavaClassName!=null)fnRemoveJavaFile(strJavaClassName); //Removing
					File DebugFolder = new File(strWorkingFolder);
					if (!DebugFolder.exists())DebugFolder.mkdir(); 
					strDynamicClassName="Debugger_"+Utility.GetRandomNumber(6);
					strJavaClassName=strWorkingFolder+"\\" +strDynamicClassName+ ".java";		
					FileWriter fileWriter =new FileWriter(strWorkingFolder+"\\" +strDynamicClassName+ ".java");
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write("package " +strDebugFolderName+ ";\n");	
			    	bufferedWriter.write(sImports.trim()+ "\n");	
					bufferedWriter.write(" public class "+strDynamicClassName+ "\n");		
					bufferedWriter.write(" {"+ "\n");
					bufferedWriter.write("public void fnDebugger(Object objClientDebug) throws Exception{" + "\n");		
					bufferedWriter.write(sObjectInitialization.trim() + "\n");		
					bufferedWriter.write(sCode.trim()+ "\n");				            
					bufferedWriter.write(" }"+ "\n");
					bufferedWriter.write("}"+ "\n");
					bufferedWriter.close(); // Always close files.
					
					File file = new File(System.getProperty("user.dir")+"\\bin\\" +strDebugFolderName+ "\\" +strDynamicClassName+ ".class");
					int i=0;
					do{
						Thread.sleep(2000);
						i++;
					}while((!file.exists() &&(i<5)));
					
					if(!file.exists()){
						JOptionPane.showMessageDialog(frameMain, "Please set the Eclipse preferences before proceeding (Windows->Preferences->General->Workspace-> check top three options)", "Compile time Error", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						//Executing the java file created
						Class<?> clazz; 
						try {
							objClientDebug=objHashMapClients.get(listboxClientNames.getSelectedItem().toString()); 
							Class[] DebugObject = new Class[1];
							DebugObject[0] = Object.class;
							clazz = Class.forName(strDebugFolderName+"."+strDynamicClassName);
							final Object[] args = new Object[1];
							Object obj=clazz.newInstance();    
							final Method method = clazz.getMethod("fnDebugger", DebugObject);    	    
				    	    method.invoke(obj,objClientDebug);
				    	    Utility.DeleteFile(strJavaClassName);
						}
						catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(frameMain, "Compile time error.(Modify the code)", "Compile time Error", JOptionPane.WARNING_MESSAGE);
							e.printStackTrace();
						}
					}
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	        }
	    });  
		
	}
	private void InvokeHighlighter(boolean boolIllustrateAndExecute) throws Exception{ 
		//boolIllustrateAndExecute=false would only show the code, not execute
		By objBy=null;
		String strBy = null;
		String strHelperCode = "undefined";
		String sLocatorValue = txtboxHighlighter.getText(); //Do not trim or convert to upper case
		switch(listboxHighligter.getSelectedItem().toString().trim().toUpperCase()){
			case "XPATH":
				strBy = "By.xpath(\"" + sLocatorValue + "\")";
				if(boolIllustrateAndExecute){
					objBy = By.xpath(sLocatorValue);
				}				
				break;
			case "CSSSELECTOR":
				strBy = "By.cssSelector(\"" + sLocatorValue + "\")";
				if(boolIllustrateAndExecute){
					objBy = By.cssSelector(sLocatorValue);
				}
				break;
			case "CLASSNAME":
				strBy = "By.className(\"" + sLocatorValue + "\")";
				if(boolIllustrateAndExecute){
					objBy = By.className(sLocatorValue);
				}
				break;
			case "ID":
				strBy = "By.id(\"" + sLocatorValue + "\")";
				if(boolIllustrateAndExecute){
					objBy = By.id(sLocatorValue);
				}
				break;
			case "NAME":
				strBy = "By.name(\"" + sLocatorValue + "\")";
				if(boolIllustrateAndExecute){
					objBy = By.name(sLocatorValue);
				}
				break;
			case "LINKTEXT":
				strBy = "By.linkText(\"" + sLocatorValue + "\")";
				if(boolIllustrateAndExecute){
					objBy = By.linkText(sLocatorValue);
				}
				break;
			case "PARTIALLINKTEXT":
				strBy = "By.partialLinkText(\"" + sLocatorValue + "\")";
				if(boolIllustrateAndExecute){
					objBy = By.partialLinkText(sLocatorValue);
				}
				break;
			case "TAGNAME":
				strBy = "By.tagName(\"" + sLocatorValue + "\")";
				if(boolIllustrateAndExecute){
					objBy = By.tagName(sLocatorValue);
				}
				break;
		}
		if(boolIllustrateAndExecute){
			objClientDebug=objHashMapClients.get(listboxClientNames.getSelectedItem().toString()); 
		}
		switch(strCoreBaseClientName){ //My logical choice on: strCoreBaseClientName versus listboxClientNames.getSelectedItem().toString()
			case "SInteractive":
				strHelperCode = "client.Element(" + strBy + ").ufxHighlightElement();";
				if(boolIllustrateAndExecute){
			    	SInteractive  client=(SInteractive) objClientDebug; 
			    	if(client.ElementExists(objBy,0)){
			    		client.Element(objBy).ufxHighlightElement(3,200); 		//params for LoopingCount
			    	}
			    	else{
			    		JOptionPane.showMessageDialog(frameMain, "Element not found please check your locatorValue", "Element Not Found", JOptionPane.ERROR_MESSAGE);		
			    	}
				}
				break;
			case "AppiumClient":
				strHelperCode = "client.Element(" + strBy + ").Method();";
				if(boolIllustrateAndExecute){
		    		AppiumClient  client=(AppiumClient) objClientDebug ;
		    		if(client.ElementExists(objBy,0)){
		    			JOptionPane.showMessageDialog(frameMain, "Element Found", "Element Found", JOptionPane.PLAIN_MESSAGE);
			    	}
		    		else{
			    		JOptionPane.showMessageDialog(frameMain, "Element NOT Found", "Element Not Found", JOptionPane.ERROR_MESSAGE);		
			    	}
				}
				break;
			case "SeeTestClient":
				strHelperCode = "client.Element(" + strBy + ").Method();";
				if(boolIllustrateAndExecute){
					JOptionPane.showMessageDialog(frameMain, "Not implemented", "Not yet implemented in Debugger.java", JOptionPane.PLAIN_MESSAGE);
				}
				break;
			default:
				JOptionPane.showMessageDialog(frameMain, "Tool client not registered for Debug", "Element Highlight failed", JOptionPane.ERROR_MESSAGE);
				break;
		}
		lblHighlighter.setText(strHelperCode);//This is how it illustrates the helper code
		
	}
}//End of Debugger class

//	private void RunDebuggerCode(String strClassName) throws Exception{
//		
//				Class<?> clazz; 
//				try {
//					objClientDebug=objHashMapClients.get(listboxClientNames.getSelectedItem().toString()); 
//					Class[] DebugObject = new Class[1];
//					DebugObject[0] = Object.class;
//					clazz = Class.forName(strClassName);
//					final Object[] args = new Object[1];
//					Object obj=clazz.newInstance();    
//					final Method method = clazz.getMethod("fnDebugger", DebugObject);    	    
//		    	    method.invoke(obj,objClientDebug);
//		    	    Utility.DeleteFile(strJavaClassName);
//				}
//				catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
//					// TODO Auto-generated catch block
//					JOptionPane.showMessageDialog(frameMain, "Compile time error.(Modify the code)", "Compile time Error", JOptionPane.WARNING_MESSAGE);
//					e.printStackTrace();
//				}
//	}


/*			//TODO: need to change
			objClientDebug=objHashMapClients.get("SInteractive");
		    if (objClientDebug!=null){
		    	SInteractive  client=(SInteractive) objClientDebug; 
		    	if(client.ElementExists(objBy,0)){
		    		client.Element(objBy).ufxHighlightElement(3,200); 		//params for LoopingCount
		    	}else{
		    		JOptionPane.showMessageDialog(frameMain, "Element not found please check your locatorValue", "Element Not Found", JOptionPane.ERROR_MESSAGE);		
		    	}
		    }
		    else{
		    	objClientDebug=objHashMapClients.get("AppiumClient");
		    	if (objClientDebug!=null){
		    		AppiumClient  client=(AppiumClient) objClientDebug ;
		    		if(client.ElementExists(objBy,0)){
		    			JOptionPane.showMessageDialog(frameMain, "Element Found", "Element Found", JOptionPane.PLAIN_MESSAGE);
			    	}else{
			    		JOptionPane.showMessageDialog(frameMain, "Element NOT Found", "Element Not Found", JOptionPane.ERROR_MESSAGE);		
			    	}
		    	}else{
		    		JOptionPane.showMessageDialog(frameMain, "Tool client not registered for Debug", "Element Highlight failed", JOptionPane.ERROR_MESSAGE);		
		    	}
		    }
		    */


/*public void Launch() {
    
	frameMain=new JFrame();
	frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frameMain.setAlwaysOnTop(true);
	frameMain.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
	System.out.println(sTestCaseName);
	sTestCaseName = Utility.GetTestCaseClassName();
	frameMain.setTitle("Debugger -"+sTestCaseName);
	//frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frameMain.setBounds(100, 100, 921, 723);
	contentPane = new JPanel();
	contentPane.setBackground(SystemColor.controlHighlight);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	frameMain.setContentPane(contentPane);

	
	JPanel HighlighPanel = new JPanel();
	HighlighPanel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Element Highlighter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	HighlighPanel.setBackground(SystemColor.controlHighlight);
	JPanel panel = new JPanel();
	panel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Script Executor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	panel.setBackground(SystemColor.controlHighlight);
	GroupLayout gl_contentPane = new GroupLayout(contentPane);
	gl_contentPane.setHorizontalGroup(
		gl_contentPane.createParallelGroup(Alignment.TRAILING)
			.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
			.addComponent(HighlighPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
	);
	gl_contentPane.setVerticalGroup(
		gl_contentPane.createParallelGroup(Alignment.TRAILING)
			.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
				.addComponent(HighlighPanel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
				.addGap(0))
	);
	JLabel lblLocator = new JLabel("Locator :");
	lblLocator.setFont(new Font("Tahoma", Font.BOLD, 13));
	
	JComboBox listboxHighligter = new JComboBox();
	listboxHighligter.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}
	});
	
	JButton btnHighlight = new JButton("HIGHLIGHT");
	btnHighlight.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0){
			try {
				InvokeHighlighter(listboxHighligter.getSelectedItem().toString(), txtboxHighlighter.getText().trim());
			} catch (Exception e) {
				e.printStackTrace();
			}	    
		}
	});
	//TODO : Design should be changed
	listboxHighligter.addItem("XPATH");
	listboxHighligter.addItem("ID");
	listboxHighligter.addItem("NAME");
	listboxHighligter.addItem("CSSSELECTOR");
	listboxHighligter.addItem("CLASSNAME");
	listboxHighligter.addItem("LINKTEXT");
	listboxHighligter.addItem("PARTIALLINKTEXT");
	listboxHighligter.addItem("TAGNAME");	
	listboxHighligter.setSelectedItem(0);
	
	btnHighlight.setFont(new Font("Tahoma", Font.BOLD, 13));
	JLabel lblvalue = new JLabel("Value :");
	lblvalue.setFont(new Font("Tahoma", Font.BOLD, 13));
	txtboxHighlighter = new JTextField();
	txtboxHighlighter.setHorizontalAlignment(SwingConstants.LEFT);
	txtboxHighlighter.setColumns(3);
	GroupLayout gl_HighlighPanel = new GroupLayout(HighlighPanel);
	gl_HighlighPanel.setHorizontalGroup(
		gl_HighlighPanel.createParallelGroup(Alignment.TRAILING)
			.addGroup(gl_HighlighPanel.createSequentialGroup()
				.addGap(11)
				.addComponent(lblLocator)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(listboxHighligter, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblvalue)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtboxHighlighter, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnHighlight, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
				.addContainerGap())
	);
	gl_HighlighPanel.setVerticalGroup(
		gl_HighlighPanel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_HighlighPanel.createSequentialGroup()
				.addGap(6)
				.addGroup(gl_HighlighPanel.createParallelGroup(Alignment.BASELINE)
					.addComponent(listboxHighligter, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblLocator, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblvalue, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addComponent(txtboxHighlighter, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnHighlight, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(16, Short.MAX_VALUE))
	);
	HighlighPanel.setLayout(gl_HighlighPanel);
	JLabel label = new JLabel("Choose Client :");
	label.setFont(new Font("Tahoma", Font.BOLD, 14));
	listboxClientNames = new JComboBox();
	JButton btnExecute = new JButton("EXECUTE");
	btnExecute.setFont(new Font("Tahoma", Font.BOLD, 14));
	JScrollPane txtrImportsscrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JTextArea txtrObjectInitialization = new JTextArea();
	txtrObjectInitialization.setText("<dynamic> client=(<dynamic>) objClientDebug ;");
	txtrObjectInitialization.setForeground(Color.GRAY);
	txtrObjectInitialization.setFont(new Font("Monospaced", Font.BOLD, 19));
	txtrObjectInitialization.setEditable(false);
	
	JTextArea txtrCode = new JTextArea();
	txtrCode.setFont(new Font("Monospaced", Font.BOLD, 19));
	JScrollPane txtrCodescrollPane = new JScrollPane(txtrCode,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	GroupLayout gl_panel = new GroupLayout(panel);
	gl_panel.setHorizontalGroup(
		gl_panel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(txtrCodescrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
							.addComponent(txtrObjectInitialization, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
							.addComponent(txtrImportsscrollPane, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE))
						.addContainerGap())
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(listboxClientNames, 0, 379, Short.MAX_VALUE)
						.addGap(12)
						.addComponent(btnExecute, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
						.addGap(15))))
	);
	gl_panel.setVerticalGroup(
		gl_panel.createParallelGroup(Alignment.TRAILING)
			.addGroup(gl_panel.createSequentialGroup()
				.addGap(5)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(2)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(listboxClientNames, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addComponent(btnExecute, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtrImportsscrollPane, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtrObjectInitialization, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtrCodescrollPane, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
				.addGap(7))
	);
	txtrImports = new JTextArea();
	txtrImportsscrollPane.setViewportView(txtrImports);
	txtrImports.setLineWrap(true);
	txtrImports.setWrapStyleWord(true);
	txtrImports.setText("//imports \n");
	txtrImports.setForeground(Color.BLUE);
	txtrImports.setFont(new Font("Monospaced", Font.ITALIC, 18));
	panel.setLayout(gl_panel);
	contentPane.setLayout(gl_contentPane);
	try{	
		String sClientName=objClientDebug.getClass().getSimpleName();
	    txtrObjectInitialization.setText(sClientName + " client=("+sClientName+") objClientDebug ;");
	}catch(Exception e){
		bNoSessionError=true;
	}
	
	//fnLoadClientObjects(objHashMapClients);//Adding client object into dropdown
    Iterator it = objHashMapClients.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        listboxClientNames.addItem(pair.getKey());
    } 
	
	if(objHashMapClients.size()!=0){
		listboxClientNames.setSelectedIndex(0);
	}
	
	listboxClientNames.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
	 		objClientDebug=objHashMapClients.get(listboxClientNames.getSelectedItem());
			String sClientName=objClientDebug.getClass().getSimpleName();
			txtrImports.setText("//imports \n");
			txtrImports.append("import org.openqa.selenium.*;\n");
		    txtrImports.append("import " + objClientDebug.getClass().getName() +";\n");
		    txtrObjectInitialization.setText(sClientName + " client=("+sClientName+") objClientDebug ;");
		}
	});
	
	btnExecute.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {			
				strWorkingFolder=System.getProperty("user.dir")+"\\src\\" + strDebugFolderName +"\\";
				//if(strJavaClassName!=null)fnRemoveJavaFile(strJavaClassName); //Removing
				File DebugFolder = new File(strWorkingFolder);
				if (!DebugFolder.exists())DebugFolder.mkdir(); 
				
				strDynamicClassName="Debugger_"+Utility.GetRandomNumber(6);
				strJavaClassName=strWorkingFolder+"\\" +strDynamicClassName+ ".java";
				FileWriter fileWriter =new FileWriter(strWorkingFolder+"\\" +strDynamicClassName+ ".java");
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write("package " +strDebugFolderName+ ";\n");		
		    	bufferedWriter.write(txtrImports.getText().trim()+ "\n");	
				bufferedWriter.write(" public class "+strDynamicClassName+ "\n");		
				bufferedWriter.write(" {"+ "\n");
				bufferedWriter.write("public void fnDebugger(Object objClientDebug) throws Exception{" + "\n");		
				bufferedWriter.write(txtrObjectInitialization.getText().trim() + "\n");		
				bufferedWriter.write(txtrCode.getText().trim()+ "\n");				            
				bufferedWriter.write(" }"+ "\n");
				bufferedWriter.write("}"+ "\n");
				bufferedWriter.close(); // Always close files.
				
				File file = new File(System.getProperty("user.dir")+"\\bin\\" +strDebugFolderName+ "\\" +strDynamicClassName+ ".class");
				int i=0;
				do{
					Thread.sleep(2000);
					i++;
				}while((!file.exists() &&(i<5)));
				
				if(!file.exists()){
					JOptionPane.showMessageDialog(frameMain, "Please set the Eclipse preferences before proceeding", "Compile time Error", JOptionPane.INFORMATION_MESSAGE);
				}else{
					RunDebuggerCode(strDebugFolderName+"."+strDynamicClassName);						
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	});
	//Handle the close event, delete the clients
	frameMain.addWindowListener(new java.awt.event.WindowAdapter() {
	    @Override
	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	    	frameMain.hide();
	    	boolIsLaunched=false;
		    objClientDebug=objHashMapClients.get("SInteractive");
		    if (objClientDebug!=null){
		    	SInteractive  client=(SInteractive) objClientDebug;
		    	try{
		    			client.Finalize(true);
				}catch (Exception e) {
						e.printStackTrace();
				}
		    }else{
		    	objClientDebug=objHashMapClients.get("AppiumClient");
		    	AppiumClient client=(AppiumClient) objClientDebug ;
		    	try{
	    			client.Finalize(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
		    }   
		    //For removing all debugger files
		    File index = new File(System.getProperty("user.dir")+"\\src\\Debugger\\");
		    String[]entries = index.list();
		    for(String s: entries){
		        File currentFile = new File(index.getPath(),s);
		        currentFile.delete();
		    }
	    }
	});
	
	frameMain.setVisible(true);
	
}*/

