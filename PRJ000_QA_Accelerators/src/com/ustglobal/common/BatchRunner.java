package com.ustglobal.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.ustglobal.selenium.SInteractive;

public class BatchRunner {
	private JFrame frame;
	private JPanel panel;
	private JTextField txtBatchName;
	private JLabel labelBatchName;
	private JButton btnRun;
	private JButton btnSave;
	private JButton btnRefresh;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JTable table;
	private JPopupMenu menu;
	private JTextField txtCountParallelTests;
	private JLabel lblParallel;
	private JButton btnStopAll;
	private JLabel labelConfig;
	private JTextArea txtConfigData;
	private JComboBox<String> lstboxPackageFilter;
	private JComboBox lstboxApplyConfigScope;
	private JButton btnApplyConfig;

	private ArrayList<String> listExclusions = null;
	private String strWorkingDirectory = null;
	private final static int nColUserSelection = 0;
	private final static int nColPackageClassName = 1;
	private final static int nColTestConfig = 2;
	private final static int nColExecutionStatus = 3;
	public static HashMap<String, String> mapThreadsMonitor = new HashMap();
	public static HashMap<String, String> mapRowsToReportFile = new HashMap();
	private JCheckBox chckbxEnableDebugger;
	private JCheckBox chckbxEnableReporter;
	private JCheckBox chckbxEnableSystemTrayMonitor;

	public static void Launch() { // This is a static method. A java program can
									// call this as a utility method.
		final ArrayList<String> listExclusions = new ArrayList<String>();
		StackTraceElement[] arrEx = new Exception().getStackTrace();
		for (int i = 0; i < arrEx.length; i++) {
			System.out.println();
			listExclusions.add(arrEx[i].getClassName());

		}
		try {// Batch runner could be opened as multiple instances by users, and
				// from different places. Hence non static
			BatchRunner batchRunner = new BatchRunner(listExclusions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BatchRunner(ArrayList<String> listExclusions) {
		this.listExclusions = listExclusions;
		this.strWorkingDirectory = System.getProperty("user.dir") + "\\bin\\";

		frame = new JFrame();
		frame.setTitle("Batch Runner");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("left:60dlu"), FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("116px:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("40px"), FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("80px"), FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("80px"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("80px"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("80px"), }, // 60px:grow
				new RowSpec[] { FormSpecs.LINE_GAP_ROWSPEC, RowSpec.decode("25px"), FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("35px"), FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("116px:grow"),
						RowSpec.decode("35px"), }));

		labelBatchName = new JLabel("Package Filter :");
		panel.add(labelBatchName, "1, 2, right, center");

		lstboxPackageFilter = new JComboBox<String>();
		lstboxPackageFilter.setModel(new DefaultComboBoxModel(new String[] { "--select all--" }));

		panel.add(lstboxPackageFilter, "3,2,fill,center");

		lblParallel = new JLabel("Parallel tests:");
		panel.add(lblParallel, "5, 2, right, default");

		txtCountParallelTests = new JTextField();
		txtCountParallelTests.setText("1");
		txtCountParallelTests.setToolTipText("The number of parallel tests can be run at a time");
		txtCountParallelTests.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(txtCountParallelTests, "7, 2, fill, default");
		txtCountParallelTests.setColumns(10);

		btnRun = new JButton("Run");
		panel.add(btnRun, "9, 2, fill, top");

		btnStopAll = new JButton("Stop");
		panel.add(btnStopAll, "11, 2");

		model = new DefaultTableModel();
		model.addColumn("Execute");
		model.addColumn("Test case name");
		model.addColumn("Configuration");
		model.addColumn("Execution Status");
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		btnRefresh = new JButton("Refresh");
		panel.add(btnRefresh, "13, 2, fill, top");

		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});

		btnSave = new JButton("Save");
		panel.add(btnSave, "15, 2, fill, top");

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

		labelConfig = new JLabel("Config data :");
		panel.add(labelConfig, "1, 4, right, center");

		txtConfigData = new JTextArea(1, 2);
		txtConfigData.setTabSize(4);
		txtConfigData.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		scrollPane = new JScrollPane(txtConfigData, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane, "3, 4, 9, 1, fill, center");
		txtConfigData.setColumns(10);

		menu = new JPopupMenu();
		JMenuItem itemSelect = new JMenuItem("Select");
		menu.add(itemSelect);
		JMenuItem itemSelectThisAlone = new JMenuItem("Select this alone");
		menu.add(itemSelectThisAlone);
		JMenuItem itemSelectAll = new JMenuItem("Select all");
		menu.add(itemSelectAll);
		JMenuItem itemDeSelect = new JMenuItem("De-Select");
		menu.add(itemDeSelect);
		JMenuItem itemRunHighlighted = new JMenuItem("Run highlighted");
		menu.add(itemRunHighlighted);

		table = new JTable(model);

		table.getColumn("Execute").setMinWidth(40);
		table.getColumn("Execute").setMaxWidth(60);
		table.getColumn("Execute").setPreferredWidth(60);
		table.getColumn("Execute").setCellRenderer(centerRenderer);

		table.getColumn("Test case name").setMinWidth(50);
		table.getColumn("Configuration").setMinWidth(50);

		table.getColumn("Execution Status").setMinWidth(60);
		table.getColumn("Execution Status").setMaxWidth(100);
		table.getColumn("Execution Status").setPreferredWidth(100);
		table.getColumn("Execution Status").setCellRenderer(centerRenderer);

		lstboxApplyConfigScope = new JComboBox();
		lstboxApplyConfigScope
				.setModel(new DefaultComboBoxModel(new String[] { "All rows", "Selected", "Highlighted" }));
		panel.add(lstboxApplyConfigScope, "13, 4, fill, default");

		btnApplyConfig = new JButton("Apply");
		panel.add(btnApplyConfig, "15, 4");

		scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, "1, 6, 15, 1, fill, fill");
		table.setComponentPopupMenu(menu);

		chckbxEnableSystemTrayMonitor = new JCheckBox("SysTray");
		panel.add(chckbxEnableSystemTrayMonitor, "11, 7");

		chckbxEnableReporter = new JCheckBox("Reporter");
		panel.add(chckbxEnableReporter, "13, 7");

		chckbxEnableDebugger = new JCheckBox("Debugger");
		panel.add(chckbxEnableDebugger, "15, 7");

		itemSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int nRow : table.getSelectedRows()) {
					model.setValueAt("Yes", nRow, 0);

				}
			}
		});

		itemSelectThisAlone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int arraySelectedRowIDs[] = table.getSelectedRows();
				int nRowCount = model.getRowCount();
				for (int nRow = 0; nRow < nRowCount; nRow++) {
					model.setValueAt(Arrays.binarySearch(arraySelectedRowIDs, nRow) < 0 ? "No" : "Yes", nRow, 0);
				}
			}

		});

		itemSelectAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.selectAll();
				int nRowCount = model.getRowCount();
				for (int nRow = 0; nRow < nRowCount; nRow++) {
					model.setValueAt("Yes", nRow, 0);
				}
			}
		});

		itemDeSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int nRow : table.getSelectedRows()) {
					model.setValueAt("No", nRow, 0);
				}
			}
		});

		itemRunHighlighted.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ExecuteBatchRuns(false); // false=run only highlighted rows
			}
		});

		lstboxPackageFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO: implement
			}
		});

		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ExecuteBatchRuns(true);
			}
		});

		btnStopAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		// Format setter for "Execution Status" column
		table.getColumn("Execution Status").setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				ImageIcon icon = null;
				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, column);
				switch (value.toString()) {
				case "PASS":
					icon = new ImageIcon(Utility.GetResourcePath("/images/imgPDF_Icon_16x16.png"));
					renderer.setIcon(icon);
					renderer.setFont(new Font("", Font.BOLD, 12));
					renderer.setForeground(Color.GREEN.darker());
					break;
				case "FAIL":
					icon = new ImageIcon(Utility.GetResourcePath("/images/imgPDF_Icon_16x16.png"));
					renderer.setIcon(icon);
					renderer.setFont(new Font("", Font.BOLD, 12));
					renderer.setForeground(Color.RED);
					break;
				case "Running..":
					icon = new ImageIcon(Utility.GetResourcePath("/images/imgBatch_Running_16x16.png"));
					renderer.setIcon(icon);
					renderer.setFont(new Font("", Font.PLAIN, 12));
					renderer.setForeground(Color.BLUE);
					break;
				case "Completed":
					icon = new ImageIcon(Utility.GetResourcePath("/images/imgDone_16x16.png"));
					renderer.setIcon(icon);
					renderer.setFont(new Font("", Font.BOLD, 12));
					renderer.setForeground(Color.BLACK);
					break;
				case "not run":
				case "":
				default:
					renderer.setIcon(null);
					renderer.setFont(new Font("", Font.PLAIN, 12));
					renderer.setForeground(Color.BLACK);
					break;
				}
				return renderer;
			}
		});

		// Action setter for "Execution Status" column
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int nCol = table.columnAtPoint(evt.getPoint());
				if (nCol == nColExecutionStatus) {
					int nRow = table.rowAtPoint(evt.getPoint());
					String strReportFilePath = mapRowsToReportFile.get("R" + nRow + "C" + nCol);
					if (strReportFilePath != null) {
						try {
							File file = new File(strReportFilePath);
							Desktop.getDesktop().open(file);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(frame,
									"The report file is currently not available, or cannot be opened", "File not found",
									JOptionPane.PLAIN_MESSAGE);
						}
					}
				}
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// TODO: how to show an underline in PASS/FAIL?
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				// TODO: how to show an underline in PASS/FAIL?
			}
		});

		RefreshAndReloadGUI();
		frame.setVisible(true);

	}// End of constructor

	private void RefreshAndReloadGUI() { // This will be called initially, or on
											// pressing the Refresh button
		Collection<?> files = FileUtils.listFiles(new File(strWorkingDirectory), new RegexFileFilter(".*\\.class"),
				DirectoryFileFilter.DIRECTORY);
		files.forEach((file) -> {
			int nCount = 0;
			String strPackageAndClassName = file.toString().replace(strWorkingDirectory, "").replace(".class", "")
					.replace("\\", ".");
			if (!listExclusions.contains(strPackageAndClassName)) {// BatchRunner
																	// doesn't
																	// select
																	// class
																	// files
																	// from
																	// standard
																	// libraries
				try {
					if (Class.forName(strPackageAndClassName).getMethod("main", String[].class) != null) {
						model.addRow(new Object[] { "No", strPackageAndClassName, "", "not run" });
						int nIndexEndPackageName = strPackageAndClassName.lastIndexOf(".");
						if (nIndexEndPackageName != -1) { // TODO: test the
															// default package
															// cases. Group them
															// as --default
															// package-- item
							String sPackageName = strPackageAndClassName.substring(0, nIndexEndPackageName);
							lstboxPackageFilter.addItem(sPackageName); // TODO:
																		// implement
																		// a
																		// JSON
																		// datastructure
																		// to
																		// filter
																		// as
																		// well
																		// as
																		// serialize
																		// user
																		// cache
																		// file
						}
					}
				} catch (Exception e1) {
					// e1.printStackTrace();
				}
			}
		});
	}// end of RefreshAndReloadGUI

	private void ExecuteBatchRuns(boolean boolRunNotJustHighlighted) { // boolNotJustHighlighted=true
		// implies
		// run
		// all
		// user
		// selected
		// "Y"
		// ones
		int nCountParallelTests = Integer.parseInt(txtCountParallelTests.getText());
		if (nCountParallelTests < 1) {
			nCountParallelTests = 1;
		}
		txtCountParallelTests.setText(nCountParallelTests + "");
		ExecutorService objMonitor = Executors.newFixedThreadPool(nCountParallelTests); // count
		// of
		// parallel
		// monitors
		// at
		// a
		// time
		ExecutorService objExecutor = Executors.newFixedThreadPool(nCountParallelTests); // count
		// of
		// parallel
		// threads
		// at
		// a
		// time
		int nRowCount = table.getRowCount();
		for (int nRow = 0; nRow < nRowCount; nRow++) {
			final String strSelection = model.getValueAt(nRow, nColUserSelection).toString().trim().toUpperCase();
			final String strPackageAndClassName = model.getValueAt(nRow, nColPackageClassName).toString().trim();
			// final String strTestCaseConfig = model.getValueAt(nRow,
			// nColTestConfig).toString().trim();
			// final Object[] args = new Object[]{new
			// String[]{strTestCaseConfig}};
			String strTestCaseConfig = model.getValueAt(nRow, nColTestConfig).toString().trim();
			// If individual testcase config is blank, then take it from general
			// config
			if (strTestCaseConfig.isEmpty())
				strTestCaseConfig = txtConfigData.getText().trim();

			Object[] args;
			if (strTestCaseConfig.isEmpty())
				args = new Object[] { new String[] {} };
			else
				args = new Object[] { new String[] { strTestCaseConfig } };

			final int nRowCurrent = nRow;
			boolean boolExecute = boolRunNotJustHighlighted ? (strSelection.equals("YES") || strSelection.equals("Y"))
					: table.isRowSelected(nRowCurrent);
			if (boolExecute) {
				model.setValueAt("Scheduled..", nRowCurrent, nColExecutionStatus);
				// Start the execution thread
				objExecutor.execute(new Runnable() {
					@Override
					public void run() {
						Thread objTestClassThread = Thread.currentThread();
						long nThreadIDChild = objTestClassThread.getId();
						// Start the monitoring thread
						objMonitor.execute(new Runnable() {
							@Override
							public void run() {
								model.setValueAt("Running..", nRowCurrent, nColExecutionStatus);
								mapThreadsMonitor.put("THREAD_ID" + nThreadIDChild + "_REPORT_PATH", null);
								mapThreadsMonitor.put("THREAD_ID" + nThreadIDChild + "_TEST_STATUS", null);
								mapRowsToReportFile.put("R" + nRowCurrent + "C" + nColExecutionStatus, null);
								String strReportPath, strExecStatus;
								while (true) {
									try {
										if (!objTestClassThread.isAlive()) {
											strReportPath = mapThreadsMonitor
													.get("THREAD_ID" + nThreadIDChild + "_REPORT_PATH");
											strExecStatus = mapThreadsMonitor
													.get("THREAD_ID" + nThreadIDChild + "_TEST_STATUS");
											if (strExecStatus != null) {
												for (int nAttempt = 0; nAttempt <= 120; nAttempt++) { // Sometimes
													// there
													// is
													// a
													// time
													// lag
													strExecStatus = mapThreadsMonitor
															.get("THREAD_ID" + nThreadIDChild + "_TEST_STATUS");
													if (strExecStatus.equals("")) {
														model.setValueAt("Finalizing(" + nAttempt + ")..", nRowCurrent,
																nColExecutionStatus);
														Thread.sleep(500);
													} else {
														break;
													}
												}
												model.setValueAt(strExecStatus.equals("") ? "Completed" : strExecStatus,
														nRowCurrent, nColExecutionStatus);
												strReportPath = mapThreadsMonitor
														.get("THREAD_ID" + nThreadIDChild + "_REPORT_PATH");
												mapRowsToReportFile.put("R" + nRowCurrent + "C" + nColExecutionStatus,
														strExecStatus.equals("") ? null : strReportPath);
											} else { // reporter or client was
												// not initialized or
												// used, however the
												// test case execution
												// completed
												model.setValueAt("Completed", nRowCurrent, nColExecutionStatus);
											}
											break;
										}
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										System.out.println("Exception thrown in BatchRunner-Monitor thread");
										break;
									}
								}
							}
						}); // end of monitor Runnable()

						// Start the execution
						Class<?> clazz;
						try {
							clazz = Class.forName(strPackageAndClassName);
							final Method method = clazz.getMethod("main", String[].class);
							method.invoke(strPackageAndClassName, args);
						} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							e.printStackTrace(); // comment this to avoid
							// unnecessary red remarks
							// in the console
							System.out.println(
									"An exception was handled in BatchRunner (the user killed a test case thread externally. This is a conceived error, and not critical)");
						}
						objTestClassThread.stop(); // important.
						// SystemTrayMonitor gets
						// the interrupt abruptly
					} // end of run implements
				}); // end of executor Runnable. end of execute
			} // end of IF row selection="YES"
			else {
				model.setValueAt("", nRowCurrent, nColExecutionStatus);
				System.out.println("Skipping: " + strPackageAndClassName);
			}
		} // end of for loop through all rows

		objExecutor.shutdown(); // should not shutdown objMonitor
		ExecutorService objSupervisor = Executors.newFixedThreadPool(1);
		objSupervisor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					while (!objExecutor.isTerminated()) {
						Thread.sleep(1000);
					}

					System.out.println("Cleaned up all BatchRunner threads");
					
					objMonitor.shutdown();

					objSupervisor.shutdown();
					
					try {
						CreatePdfSuammary();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (InterruptedException e) {

				}
			}
		});

	}// end of function - ExecuteBatchRuns( )

	private void CreatePdfSuammary() throws Exception {
		int nSkipCount = 0;
		int nPassCount = 0;
		int nFailCount = 0;
		int nTableCellPadding = 2;
		String pdfFilePath = System.getProperty("user.dir") + "/test-report/SummaryReport/Execution_Summary" + ".pdf"; // "C:\\Users\\u19627\\Documents\\SeleniumTraining\\Execution_Summary"
																														// +
																														// ".pdf";
		// OutputStream fos = new FileOutputStream(new File(pdfFilePath));
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(pdfFilePath)));
		// DocWriter writer = PdfWriter.getInstance(document, fos);
		document.open();

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);

		PdfPCell cell1 = new PdfPCell(new Paragraph("Test Summary Report"));
		cell1.setBorderColor(BaseColor.BLACK);
		cell1.setBackgroundColor(new BaseColor(140, 221, 8));
		cell1.setPaddingLeft(10);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell2 = new PdfPCell(new Paragraph("Date : " + new Date()));
		cell2.setBorderColor(BaseColor.BLACK);
		cell2.setBackgroundColor(new BaseColor(211, 211, 211));
		cell2.setPaddingLeft(10);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell3 = new PdfPCell(new Paragraph("Browser : " + SInteractive.strBrowserType));
		cell3.setBorderColor(BaseColor.BLACK);
		cell3.setBackgroundColor(new BaseColor(211, 211, 211));
		cell3.setPaddingLeft(10);
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell4 = new PdfPCell(new Paragraph("Application : MyBlock Web"));
		cell4.setBorderColor(BaseColor.BLACK);
		cell4.setBackgroundColor(new BaseColor(211, 211, 211));
		cell4.setPaddingLeft(10);
		cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

		cell1.setUseBorderPadding(true);
		cell2.setUseBorderPadding(true);
		cell3.setUseBorderPadding(true);
		cell4.setUseBorderPadding(true);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		document.add(table);

		for (int s = 0; s < (model.getRowCount() - 1); s++) {
			String sStatus = model.getValueAt(s, nColExecutionStatus).toString();
			// System.out.println("counting from table " + sStatus);
			if (sStatus.equalsIgnoreCase("PASS")) {
				nPassCount++;
				System.out.println("Pass count summary" + nPassCount);
			} else if (sStatus.equalsIgnoreCase("FAIL")) {
				nFailCount++;
				System.out.println("Fail count summary" + nFailCount);
			} else if (sStatus.equalsIgnoreCase("")) {
				nSkipCount++;
				System.out.println("Skipp count summary" + nSkipCount);
			}

		}

		DefaultPieDataset defaultCategoryDataset = new DefaultPieDataset();

		defaultCategoryDataset.setValue("Fail = " +nFailCount, nFailCount);
		defaultCategoryDataset.setValue("Skipped = "+nSkipCount, nSkipCount);
		defaultCategoryDataset.setValue("Pass = "+nPassCount, nPassCount);
		

		JFreeChart jFreeChart = ChartFactory.createPieChart("MyBlock Execution Summary", defaultCategoryDataset, false, false, false);
		PiePlot plot = (PiePlot) jFreeChart.getPlot();
		// if (defaultCategoryDataset.getKey(0).equals("Fail"))
		plot.setSectionPaint(0, Color.RED);
		// if (defaultCategoryDataset.getKey(1).equals("Pass"))
		plot.setSectionPaint(1, Color.WHITE);
		// if (defaultCategoryDataset.getKey(-1).equals("Skipped"))
		plot.setSectionPaint(2, Color.GREEN);

		PdfContentByte pdfContentByte = writer.getDirectContent();
		// change the size of rectangle
		int width = 525;
		int height = 200;
		PdfTemplate pdfTemplate = pdfContentByte.createTemplate(width, height);
		Graphics2D graphics2d = pdfTemplate.createGraphics(width, height, new DefaultFontMapper());
		java.awt.geom.Rectangle2D rectangle2d = new java.awt.geom.Rectangle2D.Double(0, 0, width, height);
		jFreeChart.draw(graphics2d, rectangle2d);
		graphics2d.dispose();
		// change the location value to adjust side-height from bottom.
		pdfContentByte.addTemplate(pdfTemplate, 35, 510);

		document.close();
		writer.close();

		System.out.println("PDF created in >> " + pdfFilePath);
		Runtime.getRuntime().exec("wscript D:/Dailyupdate/myblockTeam/MyBlock/resources/sendmail.vbs");
	}

}// End of BatchRunner class definition

// old code commented below. for referring few unimplemented methods
// ((DefaultTableCellRenderer)table.getCellRenderer(nRowCurrent,
// nColExecutionStatus)).setBackground(Color.YELLOW);
// .getTableCellRendererComponent(table, "Running...", false, false,
// nRowCurrent, nColExecutionStatus);
// DefaultTableCellRenderer objCellRenderer = new DefaultTableCellRenderer();
// objCellRenderer.setBackground(Color.YELLOW);
// DefaultTableCellRenderer objCellRenderer = new DefaultTableCellRenderer();
// objCellRenderer.getTableCellRendererComponent(table, "Running...", false,
// false, nRowCurrent, nColExecutionStatus);

/*
 * public static void ReportExecutionStatus(long nThreadIDReportCreation, String
 * strExecStatus, String strPathPDFReport){ System.out.println("THREAD_ID_" +
 * nThreadIDReportCreation +"_BATCH_TABLE_ROW");
 * if(!BatchRunner.mapThreadToTableRow.containsKey("THREAD_ID_" +
 * nThreadIDReportCreation +"_BATCH_TABLE_ROW")){ System.out.
 * println("The status reporting call to BatchRunner.ReportExecutionStatus( ) is ignored"
 * ); return; } int nRowCurrent =
 * BatchRunner.mapThreadToTableRow.get("THREAD_ID_" + nThreadIDReportCreation
 * +"_BATCH_TABLE_ROW"); DefaultTableModel model = (DefaultTableModel)
 * BatchRunner.mapThreadToTableModel.get("THREAD_ID_" + nThreadIDReportCreation
 * +"_BATCH_TABLE_MODEL"); JButton btnResult = new JButton(strExecStatus);
 * btnResult.setBackground(strExecStatus.equals("PASS")?Color.GREEN:Color.RED);
 * btnResult.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent arg0) { if
 * (Desktop.isDesktopSupported()) { File file= new File(strPathPDFReport); try {
 * Desktop.getDesktop().open(file); } catch (IOException e) {
 * e.printStackTrace(); } } } }); //end of btnResult.addActionListener
 * model.setValueAt(strExecStatus, nRowCurrent, nColExecutionStatus); }
 */
// public class BatchRunner {
//
// private JFrame frame;
// private JPanel panel;
// private JTextField txtBatchName;
// private JLabel labelBatchName;
// private JButton btnRun;
// private JButton btnSave;
// private JButton btnRefresh;
// private JScrollPane scrollPane;
// private DefaultTableModel model;
// private JTable table;
// private JPopupMenu menu;
// private JTextField txtCountParallelTests;
// private JLabel lblParallel;
// private JButton btnStopAll;
// private JLabel labelConfig;
// private JTextArea txtConfigData;
// private static JComboBox<String> lstboxPackageFilter; //
// private Set<String> keys;
//
// private String sTestcaseNames="";
// private HashMap<String, String> hPackagesAndTestcases=new HashMap<>();
//
//
// //CustomReporter obj=new CustomReporter(); //Added by abhijith
// HashMap<Integer, String> hPdfPaths = new HashMap<Integer, String>();
//
// public String sPdfPath;
//
// public static ArrayList<String> listBatchTestReportsPath = new
// ArrayList<String>();
// private JButton btnApplyConfigToAllRows;
// private JButton btnApplyConfigToSelectedRows;
// public static void main(String[] args) throws Exception {//Silent mode
// execution, invoked via command prompt
// BatchRunner.Launch_SilentMode(args);
// }
//
// private static void Launch_SilentMode(String[] args){//Private method since
// this is not likely to get called outside main()
// if (args.length == 0){
// System.out.println("no argument(s) specified for silent mode execution");
// System.exit(0);
// }
// switch (args[0]){
// case "Query":
// String strWorkingDirectory = System.getProperty("user.dir")+"\\bin\\";
// Collection<?> files = FileUtils.listFiles(
// new File(strWorkingDirectory),
// new RegexFileFilter(".*\\.class"),
// DirectoryFileFilter.DIRECTORY
// );
//
// files.forEach((file) -> {
// String strPackageAndClassName =
// file.toString().replace(strWorkingDirectory,"").replace(".class",
// "").replace("\\",".");
// try {
// if(Class.forName(strPackageAndClassName).getMethod("main",
// String[].class)!=null){
// System.out.println(strPackageAndClassName);
// }
// } catch (Exception e1) {
// //e1.printStackTrace();
// }
// });
// System.exit(0);
// break;
//
// case "Execute":
// listBatchTestReportsPath.clear();
// listBatchTestReportsPath = new ArrayList<String>();
// ExecutorService executor = Executors.newFixedThreadPool(20); //count of
// parallel threads at a time
// for (String strPackageAndClassName: args[1].split(";")){
// if(!strPackageAndClassName.equals("")){
// System.out.println("Executing: " + strPackageAndClassName);
// //final String[] arrayParams = new String[] { "Samsung S4", "Landscape"};
// final String[] arrayParams = new String[]{};
// executor.execute( new Runnable() { //Run asynchronously
// @Override
// public void run() {
// Class<?> clazz;
// final Object[] args = new Object[1];
// args[0] = arrayParams;
// try {
// //clazz = Class.forName(strPackageAndClassName);
// clazz=ClassLoader.getSystemClassLoader().loadClass(strPackageAndClassName);
// final Method method = clazz.getMethod("main", String[].class);
// method.invoke(strPackageAndClassName, args);
// } catch (ClassNotFoundException | IllegalAccessException |
// IllegalArgumentException | InvocationTargetException | NoSuchMethodException
// | SecurityException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
// }
// );
// }
// }
// executor.shutdown();
// try {
// executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
// } catch (InterruptedException e) {
// //e.printStackTrace();
// }
// for (String strPDFReportPath: listBatchTestReportsPath){
// System.out.println("\r\nReport path (to be attached):" + strPDFReportPath);
// Utility.PrintFileMetadata(strPDFReportPath);
// }
// System.exit(0);
// break;
// default:
// System.out.println("silent execution mode: command undefined - " + args[0]);
// break;
// }
// }
//
// /**
// * @wbp.parser.entryPoint
// */
// public static void Launch(){ //This is a static method. A java program can
// call this as a utility method.
//
// final ArrayList<String> listExclusions = new ArrayList<String>();
// StackTraceElement[] arrEx = new Exception().getStackTrace();
// for (int i=0; i <arrEx.length; i++){
// System.out.println();
// listExclusions.add(arrEx[i].getClassName());
// }
// try {
// //Batch runner could be opened as multiple instances by users, and from
// different places. Hence non static
// BatchRunner batchRunner = new BatchRunner(listExclusions);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
//
// public BatchRunner(ArrayList<String> listExclusions) {
// frame = new JFrame();
// frame.setTitle("Batch Runner");
// frame.setBounds(100, 100, 800, 600);
// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
// panel = new JPanel();
// frame.getContentPane().add(panel, BorderLayout.CENTER);
// panel.setLayout(new FormLayout(
// new ColumnSpec[] {
// ColumnSpec.decode("left:60dlu"),
// FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
// ColumnSpec.decode("116px:grow"),
// FormSpecs.RELATED_GAP_COLSPEC,
// FormSpecs.DEFAULT_COLSPEC,
// FormSpecs.RELATED_GAP_COLSPEC,
// ColumnSpec.decode("40px"),
// FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
// ColumnSpec.decode("70px"),
// FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
// ColumnSpec.decode("70px"),
// FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
// ColumnSpec.decode("90px"),
// FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
// ColumnSpec.decode("90px"),},
// new RowSpec[] {
// FormSpecs.LINE_GAP_ROWSPEC,
// RowSpec.decode("25px"),
// FormSpecs.RELATED_GAP_ROWSPEC,
// RowSpec.decode("35px"),
// FormSpecs.RELATED_GAP_ROWSPEC,
// RowSpec.decode("116px:grow"),
// }));
//
//
// labelBatchName = new JLabel("Choose Package :");
// panel.add(labelBatchName, "1, 2, right, center");
//
// lstboxPackageFilter = new JComboBox<String>();
//
// panel.add(lstboxPackageFilter,"3,2,fill,center");
//
// lblParallel = new JLabel("Parallel tests:");
// panel.add(lblParallel, "5, 2, right, default");
//
// txtCountParallelTests = new JTextField();
// txtCountParallelTests.setText("1");
// txtCountParallelTests.setToolTipText("The number of parallel tests can be run
// at a time");
// txtCountParallelTests.setHorizontalAlignment(SwingConstants.CENTER);
// panel.add(txtCountParallelTests, "7, 2, fill, default");
// txtCountParallelTests.setColumns(10);
//
// btnRun = new JButton("Run");
// panel.add(btnRun, "9, 2, fill, top");
//
// btnStopAll = new JButton("Stop");
// panel.add(btnStopAll, "11, 2");
//
// btnSave = new JButton("Save");
// panel.add(btnSave, "13, 2, fill, top");
//
// btnRefresh = new JButton("Refresh");
// panel.add(btnRefresh, "15, 2, fill, top");
//
// model = new DefaultTableModel();
// model.addColumn("Execute");
// model.addColumn("Test case name");
// model.addColumn("Execution Status");
// //model.addColumn("Run selected");
// model.addColumn("Last Report");
// model.addColumn("Result"); //Abhi
// DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
// centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
//
// labelConfig = new JLabel("Config data :");
// panel.add(labelConfig,"1, 4, right, center");
//
// txtConfigData = new JTextArea(1,2);
// txtConfigData.setTabSize(4);
// txtConfigData.getDocument().putProperty("filterNewlines", Boolean.TRUE);
// scrollPane = new
// JScrollPane(txtConfigData,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
// ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
// panel.add(scrollPane,"3, 4, 9, 1, fill, center");
// txtConfigData.setColumns(10);
//
// btnApplyConfigToAllRows = new JButton("Apply all");
// btnApplyConfigToAllRows.setToolTipText("Apply the config to all rows");
// panel.add(btnApplyConfigToAllRows, "13, 4, default, center");
//
// btnApplyConfigToSelectedRows = new JButton("Apply selected");
// btnApplyConfigToSelectedRows.setToolTipText("Apply the config to all selected
// rows");
// panel.add(btnApplyConfigToSelectedRows, "15, 4, default, center");
//
// menu = new JPopupMenu();
// JMenuItem itemSelect = new JMenuItem("Select");
// menu.add(itemSelect);
// JMenuItem itemSelectThisAlone = new JMenuItem("Select this alone");
// menu.add(itemSelectThisAlone);
// JMenuItem itemSelectAll = new JMenuItem("Select all");
// menu.add(itemSelectAll);
// JMenuItem itemDeSelect = new JMenuItem("De-Select");
// menu.add(itemDeSelect);
// JMenuItem itemRunHighlighted = new JMenuItem("Run highlighted");
// menu.add(itemRunHighlighted);
//
// table = new JTable(model);
//
// table.getColumn("Execute").setMinWidth(40);
// table.getColumn("Execute").setMaxWidth(60);
// table.getColumn("Execute").setPreferredWidth(60);
// table.getColumn("Execute").setCellRenderer( centerRenderer );
//
// table.getColumn("Test case name").setMinWidth(50);
//
// table.getColumn("Execution Status").setMinWidth(50);
// table.getColumn("Execution Status").setMaxWidth(100);
// table.getColumn("Execution Status").setPreferredWidth(100);
// table.getColumn("Last Report").setMinWidth(100);
// table.getColumn("Last Report").setMaxWidth(100);
// table.getColumn("Last Report").setPreferredWidth(100);
//
// table.getColumn("Result").setMinWidth(60); //Abhi
// table.getColumn("Result").setMaxWidth(60);
// table.getColumn("Result").setPreferredWidth(70);
//
// scrollPane = new
// JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
// ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
// panel.add(scrollPane,"1, 6, 15, 1, fill, fill");
// table.setComponentPopupMenu(menu);
//
//
//
//// Added by Abhi............RESULT COLOR
// CHANGER...........11/15/2016............................................./
//
// table.getColumn("Last Report").setCellRenderer(
// new DefaultTableCellRenderer() {
//
// @Override
// public Component getTableCellRendererComponent(JTable table, Object value,
// boolean isSelected, boolean hasFocus, int row, int column) {
// DefaultTableCellRenderer renderer1 = (DefaultTableCellRenderer)
// super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
// column);
// // setFont(new Font("", Font.ROMAN_BASELINE + Font.ITALIC, 12));
// if (hasFocus) {
// //setForeground(Color.red);
// setFont(new Font("", Font.PLAIN + Font.ITALIC, 12));
// } else {
// // setForeground(table.getForeground());
// setForeground(Color.BLUE);
// Map attributes = (new Font("", Font.PLAIN, 12)).getAttributes();
// //attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
// attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
// setFont(new Font(attributes));
// }
// return renderer1;
//
// }
// });
// table.getColumn("Result").setCellRenderer(
// new DefaultTableCellRenderer() {
// Color originalColor = null;
// @Override
// public Component getTableCellRendererComponent(JTable table, Object value,
// boolean isSelected, boolean hasFocus, int row, int column) {
// DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)
// super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
// column);
// if (originalColor == null) {
// originalColor = getForeground();
// }
//
// String status = (String)table.getModel().getValueAt(row, 4);
//
// if ("PASS".equals(status)) {
// //renderer.setBackground(Color.GREEN);
// renderer.setFont(new Font("", Font.BOLD, 12));
// renderer.setForeground(Color.GREEN.darker());
// } else if("FAIL".equals(status)) {
//
// //renderer.setBackground(Color.RED);
// renderer.setFont(new Font("", Font.BOLD, 12));
// renderer.setForeground(Color.RED);
// } else if("NO RUN".equals(status)){
// renderer.setFont(new Font("", Font.BOLD, 12));
// renderer.setForeground(Color.BLACK);
// }
// return renderer;
// }
//
// });
//
// table.getColumn("Execution Status").setCellRenderer(
// new DefaultTableCellRenderer() {
// @Override
// public Component getTableCellRendererComponent(JTable table, Object value,
// boolean isSelected, boolean hasFocus, int row, int column) {
// DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)
// super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
// column);
// String status = (String)table.getModel().getValueAt(row, 2);
// if ("Running...".equals(status)) {
// //renderer.setBackground(Color.GREEN);
// renderer.setFont(new Font("", Font.BOLD, 12));
// renderer.setForeground(Color.ORANGE.darker());
// } else{
// renderer.setForeground(Color.BLACK);
// }
// return renderer;
// }
//
// });
// table.getColumn("Execute").setCellRenderer(
// new DefaultTableCellRenderer() {
// @Override
// public Component getTableCellRendererComponent(JTable table, Object value,
// boolean isSelected, boolean hasFocus, int row, int column) {
// DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)
// super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
// column);
// String status = (String)table.getModel().getValueAt(row, 0);
// if("Yes".equalsIgnoreCase(status)) renderer.setFont(new Font("", Font.BOLD,
// 12));
// renderer.setHorizontalAlignment( SwingConstants.CENTER );
// return renderer;
// }
//
// });
//
//
// table.addMouseListener(new MouseAdapter() {
// @Override
// public void mouseClicked(MouseEvent e) {
// int row = table.rowAtPoint(new Point(e.getX(), e.getY()));
// int col = table.columnAtPoint(new Point(e.getX(), e.getY()));
// if(col==3 ){
// if(hPdfPaths.get(row)!=null)
// openLatesPdfReport(hPdfPaths.get(row));
// }
// }
// private void openLatesPdfReport(String url) {
// if (Desktop.isDesktopSupported()) {
// try {
// File file= new File(url);
// Desktop.getDesktop().open(file);
// } catch (IOException e) {
//
// /* TODO: error handling */ }
// } else { /* TODO: error handling */ }
// }
//
//
// @Override
// public void mouseEntered(MouseEvent e) {
//
// }
// @Override
// public void mouseExited(MouseEvent e) {
// }
// });
//
// itemSelect.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent arg0) {
// for(int nRow: table.getSelectedRows()){
// model.setValueAt("Yes", nRow, 0);
//
// }
// }
// });
//
// itemSelectThisAlone.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent arg0) {
// int arraySelectedRowIDs[] = table.getSelectedRows();
// int nRowCount = model.getRowCount();
// for(int nRow=0; nRow<nRowCount; nRow++){
// model.setValueAt(Arrays.binarySearch(arraySelectedRowIDs, nRow)<0?"No":"Yes",
// nRow, 0);
// }
// }
//
// });
//
// itemSelectAll.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent arg0) {
// table.selectAll();
// int nRowCount = model.getRowCount();
// for(int nRow=0; nRow<nRowCount; nRow++){
// model.setValueAt("Yes", nRow, 0);
// }
// }
// });
//
// itemDeSelect.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent arg0) {
// for(int nRow: table.getSelectedRows()){
// model.setValueAt("No", nRow, 0);
// }
// }
// });
//
// itemRunHighlighted.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent arg0) {
// int nCountParallelTests;
// try{
// nCountParallelTests = Integer.parseInt(txtCountParallelTests.getText());
// }catch(NumberFormatException e){
// nCountParallelTests = 1;
// txtCountParallelTests.setText("1");
// }
// ExecutorService executor = Executors.newFixedThreadPool(nCountParallelTests);
// //count of parallel threads at a time
// for(int nRow: table.getSelectedRows()){
//
// model.setValueAt("Scheduled..", nRow, 2);
// model.setValueAt("", nRow, 3);
// model.setValueAt("", nRow, 4);
//
// final String strPackageAndClassName = model.getValueAt(nRow,
// 1).toString().trim();
// //final String[] arrayParams = new String[] { "Samsung S4", "Landscape"};
// final String[] arrayParams = new String[]{};
// executor.execute( new Runnable() { //Run asynchronously
// @Override
// public void run() {
// Class<?> clazz;
// final Object[] args = new Object[1];
// args[0] = arrayParams;
// try {
// clazz = Class.forName(strPackageAndClassName);
// final Method method = clazz.getMethod("main", String[].class);
// method.invoke(strPackageAndClassName, args);
// } catch (ClassNotFoundException | IllegalAccessException |
// IllegalArgumentException | InvocationTargetException | NoSuchMethodException
// | SecurityException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
// }
// );
// ;
// }
// }
// });
//
// lstboxPackageFilter.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent arg0) {
// String sSelectedPackage = (String)lstboxPackageFilter.getSelectedItem();
// int rowCount = table.getRowCount();
// if(sSelectedPackage.equalsIgnoreCase("All")){
// for (int i = rowCount - 1; i >= 0; i--) {
// model.removeRow(i);
// }
// keys=hPackagesAndTestcases.keySet();
// for(String key: keys){
// fnReloadTableData(key);
// }
// }else{
//
// fnReloadTableData(sSelectedPackage);
// }
//
// }
// });
// frame.setVisible(true);
// String strWorkingDirectory = System.getProperty("user.dir")+"\\bin\\";
//
// Collection<?> files = FileUtils.listFiles(
// new File(strWorkingDirectory),
// new RegexFileFilter(".*\\.class"),
// DirectoryFileFilter.DIRECTORY
// );
//
//
// files.forEach((file) -> {
//
// int ncount=0;
// String strPackageAndClassName =
// file.toString().replace(strWorkingDirectory,"").replace(".class",
// "").replace("\\",".");
// String sPackageName="";
// if(!listExclusions.contains(strPackageAndClassName)){//BatchRunner doesn't
// select class files from standard libraries
// try {
// if(Class.forName(strPackageAndClassName).getMethod("main",
// String[].class)!=null){
// model.addRow(new Object[]{"No",strPackageAndClassName,"not run",""});
// int endIndex = strPackageAndClassName.lastIndexOf(".");
// if (endIndex != -1) sPackageName=strPackageAndClassName.substring(0,
// endIndex); // Extracting the packageName from the testcase
// int iPackageCount= hPackagesAndTestcases.size();
// if(strPackageAndClassName.contains(sPackageName)){
// sTestcaseNames+=strPackageAndClassName+",";
// hPackagesAndTestcases.put(sPackageName,sTestcaseNames);
// if(iPackageCount!=hPackagesAndTestcases.size())sTestcaseNames=strPackageAndClassName+",";
// //checking the package change and setting testcase names
// }
//
// }
// } catch (Exception e1) {
// //e1.printStackTrace();
// }
//
// }
//
//
// });
//
//
// fnSetPackageCombo(); //jcCombobox initial loading
// fnReadFromBatchFile();
//
//
// //Added by
// ABHI......................END..................................................../
//
// btnRun.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent arg0) {
// int nCountParallelTests;
// try{
// nCountParallelTests = Integer.parseInt(txtCountParallelTests.getText());
// }catch(NumberFormatException e){
// nCountParallelTests = 1;
// txtCountParallelTests.setText("1");
// }
// ExecutorService executor = Executors.newFixedThreadPool(nCountParallelTests);
// //count of parallel threads at a time
//
// int nRowCount = table.getRowCount();
// for(int nRow=0; nRow<nRowCount; nRow++){
//
// final String strOption = model.getValueAt(nRow,
// 0).toString().trim().toUpperCase();
// final String strPackageAndClassName = model.getValueAt(nRow,
// 1).toString().trim();
// final String[] arrayParams;
//
// //final String[] arrayParams = new String[]
// {"SEETEST","localhost","8889","ios_app:iPhone7","D:\\SeetestWorkspace"};
//
// if(txtConfigData.getText().isEmpty()){
// arrayParams=new String[]{};
// }else{
// arrayParams=new String[]{txtConfigData.getText().trim()};
// }
//
// if(strOption.trim().toUpperCase().equals("YES") ||
// strOption.trim().toUpperCase().equals("Y")){
// model.setValueAt("Scheduled..", nRow, 2);
// System.out.println("Starting: " + strPackageAndClassName);
// executor.execute( new Runnable() { //Run asynchronously
// @Override
// public void run() {
// Class<?> clazz;
// final Object[] args = new Object[1];
//
// args[0] = arrayParams;
//
// try {
// clazz = Class.forName(strPackageAndClassName);
// final Method method = clazz.getMethod("main", String[].class);
// for (int i = 0; i < table.getRowCount(); i++){
// if (table.getValueAt(i, 1).equals(strPackageAndClassName))
// {
// model.setValueAt("Running...",i,2);
// model.setValueAt("",i,3);
// model.setValueAt("",i,4);
// }
// }
//
// method.invoke(strPackageAndClassName, args);
//
// //Added by Abhi...-----------START
//
// for(int nRow=0; nRow<nRowCount; nRow++){
// final String CurrPackageClassName = model.getValueAt(nRow,
// 1).toString().trim();
// /*
// if(strPackageAndClassName.equalsIgnoreCase(CurrPackageClassName)){
//
// }
// */
// //Commented below temporoarily. I know it will break -- Pappan
//
// /*Iterator it = CustomReporter.ResultHash.entrySet().iterator();
// while (it.hasNext()) {
// Map.Entry pair = (Map.Entry)it.next();
// String[] ClassNameArr = CurrPackageClassName.split("\\.");
// String CurrentClasName = ClassNameArr[ClassNameArr.length-1];
// int nFailCount=Integer.parseInt(pair.getKey().toString().split(",")[0]);
// int nPassCount=Integer.parseInt(pair.getKey().toString().split(",")[1]);
//
// if(pair.getValue().toString().contains(CurrentClasName)){
// it.remove();
//
// if(nFailCount>0){
// model.setValueAt("FAIL",nRow,4 );
// model.setValueAt("Completed",nRow,2 );
// hPdfPaths.put(nRow, pair.getValue().toString());
// model.setValueAt("Click Here",nRow,3 );
// }else if(nFailCount==0 & nPassCount==0){
// model.setValueAt("NO RUN",nRow,4 );
// model.setValueAt("NO RUN",nRow,2 );
// hPdfPaths.put(nRow, pair.getValue().toString());
// model.setValueAt("Click Here",nRow,3 );
// }else{
// model.setValueAt("PASS",nRow,4 );
// model.setValueAt("Completed",nRow,2 );
// hPdfPaths.put(nRow, pair.getValue().toString());
// model.setValueAt("Click Here",nRow,3 );
// }
// }
// }
// */
// }
//
// //Added by Abhi-------END
//
// } catch (ClassNotFoundException | IllegalAccessException |
// IllegalArgumentException | InvocationTargetException | NoSuchMethodException
// | SecurityException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
// });
// }
// else{
//
// if(table.getModel().getValueAt(nRow, 4)==null) model.setValueAt("", nRow, 2);
// System.out.println("Skipping: " + strPackageAndClassName);
// }
//
//
// }
//
// }
// });
//
// btnStopAll.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent arg0) {
// }
// });
//
// btnSave.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
// // TODO Auto-generated method stub
//
// try{
// File file=new File(System.getProperty("user.dir")+"\\BatchFile.txt");
// if(!file.exists()){
// file.createNewFile();
// }
// FileWriter fw=new FileWriter(file.getAbsoluteFile());
// BufferedWriter bw=new BufferedWriter(fw);
// for(int i=0;i<table.getModel().getRowCount();i++){
// if(table.getModel().getValueAt(i, 0)=="Yes"){
// bw.write((String)table.getModel().getValueAt(i, 1));
// bw.write("\n");
// }
// }
//
//
// if(txtConfigData.getText().trim().length()>0){
// bw.write("CONFIG DATA ="+txtConfigData.getText().trim()+"\n");
// }
// bw.close();
// fw.close();
// JOptionPane.showMessageDialog(null, "Batch Data Saved");
//
// }
// catch(Exception ex){
// }
// } //keep
//
// });
//
// btnRefresh.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
// // TODO Auto-generated method stub
// }
// });
//
// }//End of constructor
//
// public void fnSetPackageCombo(){
// keys=hPackagesAndTestcases.keySet();
// lstboxPackageFilter.addItem("All");
// for(String key: keys){
// lstboxPackageFilter.addItem(key);
// }
// //lstboxPackageFilter.setSelectedIndex(0);
// }
//
// //--------------------------------------------READ FROM TEXT FILE
// 11/25--------------------------------------------------------------
//
// private void fnReadFromBatchFile() {
// String line = null;
// Lock lock = new ReentrantLock();
// lock.lock();
// File tfile=new File(System.getProperty("user.dir")+"\\BatchFile.txt");
// String sTestcasename = null;
// String sPackageName=null;
// boolean bFlag=true;
// boolean bfirst=true;
// String sFirstRowVal=null;
//
// boolean fileempty = !tfile.exists() || tfile.length() == 0;
// if (!fileempty){
//
// try{
// BufferedReader br = new BufferedReader(new java.io.FileReader(tfile));
// while ((line = br.readLine()) != null){
// if( line.trim().length() == 0 ){
// break; // Skip blank lines
// }else{
// sTestcasename=line.toString().trim();
// int endIndex = line.toString().trim().lastIndexOf(".");
// if(endIndex != -1) sPackageName=line.toString().trim().substring(0,
// endIndex); // Extracting the packageName from the Testcase
//
// if(bfirst){
// sFirstRowVal=sPackageName;
// bfirst=false;
// }
//
// if(line.startsWith("CONFIG DATA =")){
// if(line.length()>13){
// txtConfigData.setText(line.split("CONFIG DATA =")[1]);
// continue;
// }
// }else if(!sFirstRowVal.trim().equalsIgnoreCase(sPackageName.trim()) ){
// bFlag = false;
// break;
// }
// }
// }
// br.close();
//
// }catch (Exception ex) {
// //ex.printStackTrace();
//
// }
// if(bFlag){
// lstboxPackageFilter.setSelectedItem(sFirstRowVal);
// }else{
// lstboxPackageFilter.setSelectedItem("All");
// }
// fnHighlightTheSavedRows();
// }
//
// lock.unlock();
//
// }
// //---------------------------------END
// 11/25----------------------------------------------------------------------
// private void fnHighlightTheSavedRows() {
// String line = null;
// Lock lock = new ReentrantLock();
// lock.lock();
//
// try {
// File tfile=new File(System.getProperty("user.dir")+"\\BatchFile.txt");
// String sTestcasename = null;
// boolean fileempty = !tfile.exists() || tfile.length() == 0;
// BufferedReader br = new BufferedReader(new java.io.FileReader(tfile));
// while ((line = br.readLine()) != null) {
// if( line.trim().length() == 0 ){
// break; // Skip blank lines
// }else{
// sTestcasename=line.toString().trim();
// if (!fileempty){
// for(int i=0;i<table.getModel().getRowCount();i++){
// if(((String)table.getModel().getValueAt(i,1)).equals(sTestcasename)){
// model.setValueAt("Yes", i, 0);
// continue;
// }
// }
// }
// }
// }
// br.close();
// } catch (Exception e) {
// // TODO Auto-generated catch block
// //e.printStackTrace();
//
// }
// lock.unlock();
// }
// public void fnReloadTableData(String sSelectedPackage){
//
// int iTCCount;
// int rowCount = table.getRowCount();
// if(!lstboxPackageFilter.getSelectedItem().toString().trim().equalsIgnoreCase("All")){
// for (int i = rowCount - 1; i >= 0; i--) {
// model.removeRow(i);
// }
// }
// iTCCount=hPackagesAndTestcases.get(sSelectedPackage).split("\\,").length;
// for(int i=0;i<iTCCount;i++){
// model.addRow(new
// Object[]{"No",hPackagesAndTestcases.get(sSelectedPackage).split("\\,")[i],"not
// run",""});
// }
// fnHighlightTheSavedRows();
// }
//
// }//End of BatchRunner class
