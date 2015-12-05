package hospitalsystem;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

import finalproject.Controller;
import finalproject.Doctor;
import finalproject.Patient;
import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;



public class MainGui {
	
	/*
	 * These are all variables related to the main GUI
	 */
	public JFrame frmHospitalRegistrySystem;
	private JPasswordField passwordField;
	private JPanel containerPanel;//Change this
	private JPanel loginPanel;
	private JPanel tabsPanel;
	private JFormattedTextField usernameField;
	private JButton loginButton;

	private JButton registerButton;
	private JButton viewButton;
	private JButton logoutButton;
	private JButton importButton;
	
	private JButton btnEditPatient;
	private JButton btnExportPatients;
	
	/*
	 * Variables related to the registry card
	 */	
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblDateOfBirth;
	private JLabel lblAddressLine;
	private JLabel lblEmergencyPhoneNumber;
	private JLabel lblMedicalCondition;
	private JLabel lblMedicalURL;
	private JLabel lblComments;
	private StringBuilder medicalURL;
	private String generatedURL;
	private JButton btnGenerateURL;
	private JButton btnUploadScan;
	private JButton btnUploadScan_1;
	private JButton btnUploadScan_2;
	private BufferedImage profilePhoto;
	private BufferedImage scanPhoto1;
	private BufferedImage scanPhoto2;
	private BufferedImage scanPhoto3;
	private File pp;
	private File scanfile1;
	private File scanfile2;
	private File scanfile3;
	private JLabel scan1;
	private JLabel scan2;
	private JLabel scan3;
	private JTextField tffirstName;
	private JTextField tfLastName;
	private JTextField tfDob;
	private JTextPane addressPane;
	private JTextField tfEmergencyPhone;
	private JTextField tfMedicalCondition;
	private JComboBox<?> billingBox;
	private JTextPane commentPane;
	private JLabel profileArea;
	private JButton btnUploadProfile;
	private JLabel lblBillingCycle;
	private JScrollPane commentScrollPane;
	private JScrollPane addressScrollPane;
	private static List<String> billingList = Arrays.asList("Monthly","Quarterly","Annual");
	private JButton btnConfirmNewPatient;
	
	private JPanel registrycard;
	private JPanel patientcard;
	
	private boolean editPressed = false;
	
	public Controller controller;
	private ArrayList<Patient> patientlist;
	private DefaultTableModel tablemodel;
	public TableRowSorter<DefaultTableModel> sorter;
	private Patient addpatient;
	private Doctor adddoctor;
	Patient replacementpatient;
	
	
	/*
	 * These are the fields for my tablePanel, which is in the main CSV because it involves complex interfacing with the controller, so better handled here
	 * 
	 */
	
	
	private JTable patientTable;
	private JButton deletePatients;	
	private JTextField txtSearchByDob;
	private JTextField txtSearchByMedical;
	private JTextField txtSearchByBilling;
	private JTextField txtSearchById;
	private JTextField filterText;
	private JScrollPane patientscrollPane;
	private String filename = "/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/src/masterpatients.csv";
	private File file = new File(filename);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frmHospitalRegistrySystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		controller = new Controller();//Only initialised once
		try {
			writenewFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		patientlist = controller.readtoArrayList(filename);
		initializeFrame();
		//generateTablePanel();
//		generateRegistryCard();//These are for redesign purposes for the main GUI
	}

	public void writenewFile() throws FileNotFoundException {
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);//Because this is false, the current file is overriten
			@SuppressWarnings("resource")
			BufferedWriter bw = new BufferedWriter(fw);
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeFrame() {
		frmHospitalRegistrySystem = new JFrame();
		frmHospitalRegistrySystem.setTitle("Hospital Registry System");
		frmHospitalRegistrySystem.setBounds(100, 100, 1200, 700);
		frmHospitalRegistrySystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHospitalRegistrySystem.getContentPane().setLayout(null);	
		
		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 1200, 700);
		frmHospitalRegistrySystem.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		
		usernameField = new JFormattedTextField();
		usernameField.setBounds(468, 329, 159, 25);
		usernameField.setText("Enter Email");
		loginPanel.add(usernameField);

		passwordField = new JPasswordField();
		passwordField.setBounds(468, 400, 159, 22);
		passwordField.setToolTipText("Enter Password");
		loginPanel.add(passwordField);
		passwordField.setVisible(true);		

		loginButton = new JButton("Login");
		loginButton.setBounds(468, 469, 159, 25);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
				initialiseSystem();
				
			}
		});
		loginPanel.add(loginButton);	
	}
	
	private void initialiseSystem(){
		
		containerPanel = new JPanel();
		containerPanel.setBounds(0, 0, 1200, 680);
		frmHospitalRegistrySystem.getContentPane().add(containerPanel);
		containerPanel.setLayout(null);
		
		tabsPanel = new JPanel(new GridLayout(20,0));
		tabsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		tabsPanel.setBounds(0, 0, 200, 680);
		containerPanel.add(tabsPanel);
		
		registerButton = new JButton("Register New Patient"); 
		tabsPanel.add(registerButton);
		
		viewButton = new JButton("View Patients"); 
		tabsPanel.add(viewButton);
		
		importButton = new JButton("Import Patient CSV"); 
		tabsPanel.add(importButton);
		
		logoutButton = new JButton("Logout"); 
		tabsPanel.add(logoutButton);	

		generateRegistryCard();
		registrycard.setVisible(false);
		
		generateTablePanel();
		registrycard.setVisible(false);
					
		registerButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent event) {
				editPressed = false;
				System.out.println("You have clicked the register button");
//				for(Patient p:patientlist){
//					System.out.println(p.toString());
//				}
				patientcard.setVisible(false);
				registrycard.setVisible(true);
				Component[] components = registrycard.getComponents();
			    for (Component component : components) {
			        if (component instanceof JTextField || component instanceof JTextPane) {
			            JTextComponent specificObject = (JTextComponent) component;
			            specificObject.setText("");
			        }
			    }
			} 
		});
			
		viewButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent event) {
				editPressed = false;
				System.out.println("You have clicked the view patients button");
//				for(Patient p:patientlist){
//					System.out.println(p.toString());
//				}
				DefaultTableModel tablemodel = createModel(patientlist);
				patientTable.setModel(tablemodel);
				patientcard.setVisible(true);
				registrycard.setVisible(false);
			} 
		});
		
		logoutButton.addActionListener(new ActionListener() { 
			@Override public void actionPerformed(ActionEvent event) {		
				editPressed = false;
				int reply = JOptionPane.showConfirmDialog(frmHospitalRegistrySystem, "Are you sure you want to logout?", null, JOptionPane.YES_NO_OPTION);
				switch(reply){
				case JOptionPane.YES_OPTION:
					containerPanel.setVisible(false);
					loginPanel.setVisible(true);
					break;
				default:
					containerPanel.setVisible(true);
					loginPanel.setVisible(false);
					break;
				}

			} 
		});
					
	}
	
	private JPanel generateRegistryCard(){
		registrycard = new JPanel();
		
		registrycard.setBounds(200, 0, 1000, 680);
		registrycard.setBackground(new Color(224, 255, 255));
		registrycard.setLayout(new MigLayout("", "[141px][8px][37px][5px][132px][4px][236.00px][9.00px][134px][46px][261.00px]", "[26px][4px][4px][1px][21px][4px][26px][9px][40.00px][3px][48px][11px][26px][12px][29px][14px][22px][12px][4px][1px][38px][12px][107px][22.00px][22.00px][0.00px][110px]"));
		
		lblFirstName = new JLabel("First Name");
		registrycard.add(lblFirstName, "cell 0 0,growx,aligny center");
		
		lblLastName = new JLabel("Last Name");
		registrycard.add(lblLastName, "cell 0 4,growx,aligny top");
		
		lblDateOfBirth = new JLabel("Date of Birth");
		registrycard.add(lblDateOfBirth, "cell 0 6,growx,aligny center");
		
		lblAddressLine = new JLabel("Address");
		registrycard.add(lblAddressLine, "cell 0 8,growx,aligny top");

		lblEmergencyPhoneNumber = new JLabel("Emergency Phone");
		registrycard.add(lblEmergencyPhoneNumber, "cell 0 12,growx,aligny center");

		lblMedicalCondition = new JLabel("Enter Medical Condition e.g. 'Glaucoma'");
		registrycard.add(lblMedicalCondition, "cell 0 14 3 1,growx,aligny center");

		lblMedicalURL = new JLabel();
		lblMedicalURL.setText("<html> Type medical condition above then 'generate link'</html>");
		registrycard.add(lblMedicalURL, "cell 0 16,growx,aligny top");
			

		lblBillingCycle = new JLabel("Billing Cycle");
		registrycard.add(lblBillingCycle, "cell 0 20,growx,aligny center");

		lblComments = new JLabel("Comments");
		registrycard.add(lblComments, "cell 0 22,growx,aligny top");
		
		tffirstName = new JTextField();
		registrycard.add(tffirstName, "cell 4 0,alignx left,aligny top");
		tffirstName.setColumns(10);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		registrycard.add(tfLastName, "cell 4 2 1 3,alignx left,aligny top");
			
		addressPane = new JTextPane();
		addressScrollPane = new JScrollPane(addressPane);
		registrycard.add(addressScrollPane, "cell 4 8 1 3,grow");
		
		tfDob = new JTextField();
		tfDob.setColumns(10);
		registrycard.add(tfDob, "cell 4 6,alignx left,aligny top");
		
		tfEmergencyPhone = new JTextField();
		tfEmergencyPhone.setColumns(10);
		registrycard.add(tfEmergencyPhone, "cell 4 12,alignx left,aligny top");
		
		lblMedicalURL = new JLabel();
		registrycard.add(lblMedicalURL, "cell 4 16 3 3,grow");
		lblMedicalURL.setText("<html> Type medical condition above then 'generate link'</html>");
		
		medicalURL = new StringBuilder();
		medicalURL.append("http://en.wikipedia.org/wiki/");
		tfMedicalCondition = new JTextField();
		tfMedicalCondition.setColumns(10);
		registrycard.add(tfMedicalCondition, "cell 4 14,alignx left,aligny top");
		
		btnGenerateURL = new JButton("Generate URL");
		registrycard.add(btnGenerateURL, "cell 6 14,alignx left,aligny top");
		btnGenerateURL.addActionListener(new ActionListener() {//Why is this throwing an error
			public void actionPerformed(ActionEvent e) {
				medicalURL.append(tfMedicalCondition.getText());
				generatedURL = medicalURL.toString();
				lblMedicalURL.setText("<html> Wikipedia Link : <a href=\"\">"+tfMedicalCondition.getText()+"</a></html>");
				goWebsite(lblMedicalURL, generatedURL, tfMedicalCondition.getText());
				medicalURL.setLength(0);
				medicalURL.append("http://en.wikipedia.org/wiki/");	
			}
		});	
			
		billingBox = new JComboBox<Object>(billingList.toArray());
		registrycard.add(billingBox, "cell 4 20,growx,aligny bottom");
		
	
		commentPane = new JTextPane();
		commentScrollPane = new JScrollPane(commentPane);
		registrycard.add(commentScrollPane, "cell 4 22,grow");
		
		profileArea = new JLabel();
		registrycard.add(profileArea, "cell 10 22,grow");
		
		/*
		 * Here I generate the image upload buttons
		 * http://stackoverflow.com/questions/13512612/browse-for-image-file-and-display-it-using-java-swing
		 * http://stackoverflow.com/questions/15182329/how-to-set-icon-to-a-jlabel-from-an-image-from-a-folder
		 * http://stackoverflow.com/questions/8883937/image-wont-display-in-jlabel
		 * http://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
		 * http://stackoverflow.com/questions/9147977/getwidth-and-getheight-are-0-after-calling-setpreferredsize
		 */
		
		btnUploadProfile = new JButton("Upload Profile");
		registrycard.add(btnUploadProfile, "cell 8 22,alignx left,aligny center");
		btnUploadProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/sourcefiles/patientpics");
				chooser.showOpenDialog(null);
				try {
					profilePhoto = null;
					pp = chooser.getSelectedFile();
					profilePhoto = ImageIO.read(pp);
					Image dimg = profilePhoto.getScaledInstance(120, 120,Image.SCALE_SMOOTH);//This scales the image
					profileArea.setIcon(new ImageIcon(dimg));
				} catch (IOException io) {

				}

			}
		});
		
		scan3 = new JLabel();
		registrycard.add(scan3, "cell 10 16 1 5,grow");
		
		btnUploadScan_2 = new JButton("Upload Scan 3");
		registrycard.add(btnUploadScan_2, "cell 8 16,alignx right,aligny top");
		btnUploadScan_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/sourcefiles/brainpics");
				chooser.showOpenDialog(null);
				try {
					scanfile3 = chooser.getSelectedFile();
					scanPhoto3 = null;
					scanPhoto3 = ImageIO.read(scanfile3);
					Image scanphoto3 = scanPhoto3.getScaledInstance(120, 120,Image.SCALE_SMOOTH);//This scales the image
					scan3.setIcon(new ImageIcon(scanphoto3));
					System.out.println(scan3.getIcon());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
				
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		registrycard.add(separator, "cell 0 24 7 1,growx,aligny top");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.LIGHT_GRAY);
		registrycard.add(separator_1, "cell 7 0 1 27,grow");
		
		scan1 = new JLabel();
		registrycard.add(scan1, "cell 10 0 1 9,grow");
		
		scan2 = new JLabel();
		registrycard.add(scan2, "cell 10 10 1 5,grow");
		
		btnUploadScan = new JButton("Upload Scan 1");
		registrycard.add(btnUploadScan, "cell 8 0 1 3,alignx right,aligny bottom");
		btnUploadScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/sourcefiles/brainpics");
				chooser.showOpenDialog(null);
				try {
					scanfile1 = chooser.getSelectedFile();
					scanPhoto1 = null;
					scanPhoto1 = ImageIO.read(scanfile1);
					Image scanphoto1 = scanPhoto1.getScaledInstance(120, 120,Image.SCALE_SMOOTH);//This scales the image
					scan1.setIcon(new ImageIcon(scanphoto1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnUploadScan_1 = new JButton("Upload Scan 2");
		registrycard.add(btnUploadScan_1, "cell 8 10,alignx right,aligny top");
		btnUploadScan_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/sourcefiles/brainpics");
				chooser.showOpenDialog(null);
				try {
					scanfile2 = chooser.getSelectedFile();
					scanPhoto2 = null;
					scanPhoto2 = ImageIO.read(scanfile2);
					Image scanphoto2 = scanPhoto2.getScaledInstance(120, 120,Image.SCALE_SMOOTH);//This scales the image
					scan2.setIcon(new ImageIcon(scanphoto2));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		registrycard.add(separator_2, "cell 8 24 3 1,growx,aligny top");
		
		btnConfirmNewPatient = new JButton("Confirm Patient");
		btnConfirmNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFields();
				JOptionPane.showMessageDialog(frmHospitalRegistrySystem, "New patient has been added");
			}
		});
		registrycard.add(btnConfirmNewPatient, "cell 8 26 3 1,alignx left,aligny top");
		containerPanel.add(registrycard);
		registrycard.setVisible(true);
		
		return registrycard;
	}//Ends the registry Card method

	
	private JPanel generateTablePanel(){
		
		patientcard = new JPanel();	
		DefaultTableModel tablemodel = createModel(patientlist);
		System.out.println("New table model"+tablemodel+ "has been created");
		
		patientcard.setBackground(new Color(153, 204, 255));
		patientcard.setBounds(200, 0, 1000, 680);
		patientcard.setLayout(null);
		
		patientTable = new JTable(tablemodel);
		
		sorter = new TableRowSorter<DefaultTableModel>(tablemodel);//This should maybe create a new sorter based on a new tablemodel, and pass in arguments?
		patientTable.setRowSorter(sorter);
		
		filterText = new JTextField("Type to search for a patient across fields");
		filterText.setSize(520, 18);
		filterText.setLocation(220, 28);
		patientcard.add(filterText);
		int columncount = tablemodel.getColumnCount();
		for(int i=0;i<columncount;i++){//Prevent column sorting to avoid issues
			sorter.setSortable(i, false);
			
		}	
		filterText.setColumns(10);
		filterText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	filterText.setText("");
            }
        });
	    filterText.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = filterText.getText();
		        if (text.length() == 0) {
		          sorter.setRowFilter(null);
		        } else {
		          sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
		        }		
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
	    });
		
		;
		patientscrollPane = new JScrollPane(patientTable);
		patientscrollPane.setLocation(6, 154);
		patientscrollPane.setSize(968, 505);
		patientcard.add(patientscrollPane);
		
		btnExportPatients = new JButton("Export Selected Patients");
		btnExportPatients.setBounds(795, 123, 179, 19);
		patientcard.add(btnExportPatients);
		
		btnExportPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPressed = false;
				
			}
		});
		
		btnEditPatient = new JButton("Edit Selected Patient");
		btnEditPatient.setBounds(314, 115, 179, 24);
		patientcard.add(btnEditPatient);	
		btnEditPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = patientTable.getSelectedRow();
				Patient editPatient = patientlist.get(patientTable.convertRowIndexToModel(selectedRow));	//This is definitely the selected patient, I have checked via debug mode			
				System.out.println(editPatient);
				setEditText(editPatient);
				editPressed = true;
				
			}
		});
		
		txtSearchByDob = new JTextField("Search by DOB");
		txtSearchByDob.setColumns(10);
		txtSearchByDob.setBounds(26, 62, 136, 18);
		patientcard.add(txtSearchByDob);
		
		txtSearchByMedical = new JTextField("Search by Medical Condition");
		txtSearchByMedical.setColumns(10);
		txtSearchByMedical.setBounds(764, 58, 210, 18);
		patientcard.add(txtSearchByMedical);
		
		txtSearchByBilling = new JTextField("Search by Billing Cycle");
		txtSearchByBilling.setColumns(10);
		txtSearchByBilling.setBounds(530, 58, 210, 18);
		patientcard.add(txtSearchByBilling);
		
		txtSearchById = new JTextField("Search by ID, First Name, Last Name");
		txtSearchById.setColumns(10);
		txtSearchById.setBounds(220, 58, 273, 18);
		patientcard.add(txtSearchById);
		
		deletePatients = new JButton("Delete Selected Patient");
		deletePatients.setBounds(26, 115, 200, 24);
		patientcard.add(deletePatients);	
		deletePatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = patientTable.getSelectedRow();
				Patient deletePatient = patientlist.get(patientTable.convertRowIndexToModel(selectedRow));
				int deleteID = deletePatient.getpID();
				System.out.println("here1: " + deleteID);
				for(Patient p:patientlist){
					System.out.println(p);
				}
				Iterator<Patient> it = patientlist.iterator();
				while (it.hasNext()) {
				  Patient patient = it.next();
				  if (patient.getpID() == deleteID) {
					  System.out.println("here2: " + it);
				    it.remove();
				  }
				}
				for(Patient p:patientlist){
					System.out.println(p);
				}
				DefaultTableModel tablemodel = createModel(patientlist);
				patientTable.setModel(tablemodel);
				patientTable.repaint();
				try {
					controller.writeFile(patientlist);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		containerPanel.add(patientcard);
		patientcard.setVisible(true);
		return patientcard;
	}//Ends the tablePanel method
	
	public void generatesearchBox(DefaultTableModel m){	
		
	    filterText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	filterText.setText("");
            }
        });
	    filterText.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = filterText.getText();
		        if (text.length() == 0) {
		          sorter.setRowFilter(null);
		        } else {
		          sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
		        }		
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	}//Ends the searchBox generation method
		
	public DefaultTableModel createModel(ArrayList<Patient> patientlist) {
		DefaultTableModel m = new DefaultTableModel();
		m.setColumnIdentifiers(new String[] { "Selected", "Doctor ID", "Doctor Username", "Patient ID", "First Name", "Last Name", "DOB",
				"Address", "Phone Number", "Billing Cycle", "Medical Condition", "Comments"});
		m.setRowCount(patientlist.size());
		int row = 0;
		for (Patient patient : patientlist) {
			m.setValueAt(patient.getpatientdID(), row, 1);
			m.setValueAt(patient.getpatientdUserName(), row, 2);
			m.setValueAt(patient.getpID(), row, 3);
			m.setValueAt(patient.getpFirstName(), row, 4);
			m.setValueAt(patient.getpLastName(), row, 5);
			m.setValueAt(patient.getpDob(), row, 6);
			m.setValueAt(patient.getpAddress(), row, 7);
			m.setValueAt(patient.getpPhoneNumber(), row, 8);
			m.setValueAt(patient.getpBillingCycle(), row, 9);
			m.setValueAt(patient.getpMedicalCondition(), row, 10);
			m.setValueAt(patient.getpComments(), row, 11);
			row++;
		}
		return m;
		}
	
	
	private void setFields(){
		Patient addpatient = new Patient();
		Doctor adddoctor = new Doctor();

		addpatient.setpFirstName(tffirstName.getText());
		addpatient.setpLastName(tfLastName.getText());
		addpatient.setpDob(tfDob.getText());
		addpatient.setpAddress(addressPane.getText().replaceAll("\n|\t", " "));
		addpatient.setpBillingCycle((String) billingBox.getSelectedItem());
		addpatient.setpMedicalCondition(tfMedicalCondition.getText());
		addpatient.setpComments(commentPane.getText().replaceAll("\n|\t", " "));
		addpatient.setpPhoneNumber(tfEmergencyPhone.getText());
		adddoctor.setDuserName(usernameField.getText());
		addpatient.setpatientdUserName(usernameField.getText());
		adddoctor.setDpassWord(passwordField.getPassword().toString());		
		generateIDs(addpatient, adddoctor);
		

		//This generates a new table with the file when a new patient is added.
	}
	
	/**
	 * 
	 * <dl>
	 * <dt> Purpose:
	 * <dd> Generates IDs for the patients added based on hashcodes
	 *
	 * <dt> Description:
	 * <dd> [Insert description]
	 * </dl>
	 *
	 * @author dhruv
	 * @version 25 Nov 2015
	 */

	private void generateIDs(Patient addpatient, Doctor adddoctor){
		addpatient.setpID(addpatient.hashCode());
		addpatient.setpatientdID(adddoctor.hashCode());
		adddoctor.setdID(adddoctor.hashCode());

		int addID = addpatient.getpID();
		System.out.println(addID);
		boolean uniquepatient = true;//The fact you have clicked the edit button should be logged, and it should find and then edit the fields for the patient in the arraylist if uniquepatient is false and edit button is pressed, then the edit button should be unchecked
		int replacementindex = 0;
		for(Patient p:patientlist){
			if(p.getpID()!= addID) {	
				uniquepatient = false;
				replacementpatient = p;//This is not setting the patient to this.
				break;
			}
		}		
		System.out.println(replacementpatient.toString());
		for(Patient p:patientlist){
			System.out.println(p);
		}
		
		if(uniquepatient && editPressed == false){
			System.out.println(uniquepatient);
			System.out.println("We have a new patient");
			storeImages(addpatient);
			arrayListAdd(addpatient);
		}if(!uniquepatient && editPressed == true){//Ensures also the patient being edited is always in the existing arraylist as well.
			System.out.println("You are editing a previous patient");
			for(Patient p:patientlist){
				System.out.println(p);
			}
		}
//			storeImages(addpatient);
//			patientlist.remove(replacementindex);
//			patientlist.set(replacementindex,addpatient);//We are not adding anything to a patientlist.
//			for(Patient p:patientlist){
//				System.out.println(p);
//			}
//			DefaultTableModel tablemodel = createModel(patientlist);
//			System.out.println("New table model"+tablemodel+ "has been created");
//			DefaultTableModel dm = (DefaultTableModel)patientTable.getModel();
//			dm.getDataVector().removeAllElements();
////			dm.fireTableDataChanged();
//			patientTable.setModel(tablemodel);
////			tablemodel.fireTableDataChanged();
//			patientTable.repaint();
//			try {
//				controller.writeFile(patientlist);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//		else System.out.println("This patient already exists! I haven't added to the patientlist");			
	}
	
	
	
	private void arrayListAdd(Patient addpatient){
		patientlist.add(addpatient);
		DefaultTableModel tablemodel = createModel(patientlist);
		System.out.println("New table model"+tablemodel+ "has been created");
		patientTable.setModel(tablemodel);
		patientTable.repaint();
		System.out.println("You added to the patientlist");
		for(Patient p:patientlist){
			System.out.println(p.toString());
		}
		try {
			controller.writeFile(patientlist);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
/**
 * 
 * <dl>
 * <dt> Purpose:
 * <dd> Appends the patient to the main arraylist
 *
 * <dt> Description:
 * <dd> [Insert description]
 * </dl>
 *
 * @author dhruv
 * @version 25 Nov 2015
 * @param editPatient 
 */
	
	private void setEditText(Patient editPatient){
		editPressed = true;
		tffirstName.setText(editPatient.getpFirstName());
		tfLastName.setText(editPatient.getpLastName());
		tfDob.setText(editPatient.getpDob());
		addressPane.setText(editPatient.getpAddress());
		tfEmergencyPhone.setText(editPatient.getpPhoneNumber());
		tfMedicalCondition.setText(editPatient.getpMedicalCondition());
		billingBox.setSelectedItem(editPatient.getpBillingCycle());
		commentPane.setText(editPatient.getpComments());
		registrycard.setVisible(true);
		patientcard.setVisible(false);
		//Something to clear the fields
	}
	
	private void tableRepaint(){	//Solve this
		
		patientTable.setModel(tablemodel);
//		tablemodel.fireTableDataChanged();
		patientTable.repaint();
		try {
			controller.writeFile(patientlist);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <dl>
	 * <dt> Purpose:
	 * <dd> Redirects the user to the URL generated.
	 *
	 * <dt> Description:
	 * <dd> Taken from http://stackoverflow.com/questions/8669350/jlabel-hyperlink-to-open-browser-at-correct-url as a reference
	 * </dl>
	 *
	 * @author dhruv
	 * @version 24 Nov 2015
	 */
	
	
	private void goWebsite(JLabel website, final String url, String text) {
		lblMedicalURL.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblMedicalURL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    try {
                            Desktop.getDesktop().browse(new URI(url));
                    } catch (URISyntaxException | IOException ex) {
                    }
            }
        });
    }
	
	/**
	 * 
	 * <dl>
	 * <dt> Purpose:
	 * <dd> Generate a file store for each uploaded image, in a new folder for the correct patient which has been updated 
	 *
	 * <dt> Description:
	 * <dd> [Insert description]
	 * </dl>
	 *
	 * @author dhruv
	 * @version 25 Nov 2015
	 */
	
	private void storeImages(Patient patient){
		String imagepath = "/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/src/"+"patient_"+
	String.valueOf(patient.getpID()+patient.getpFirstName().toLowerCase()+"_"+"_"+patient.getpLastName().toLowerCase());
		File imagefolder = new File(imagepath);
		boolean success = imagefolder.mkdirs();
		if (!success) {
			System.out.println("Folder for images not created");
		}else System.out.println("New folder for storing images created");
		try {
			// retrieve image
			if(profilePhoto!=null){
				BufferedImage profilebufferimage = profilePhoto;//This gets what was set as profilePhoto before
				File profilefile = new File(imagepath+"/profilephoto.png");
				ImageIO.write(profilebufferimage, "png", profilefile);
			}
			
			if(scanPhoto1!=null){
				BufferedImage scan1bufferimage = scanPhoto1;
				File scan1file = new File(imagepath+"/scan1.png");
				ImageIO.write(scan1bufferimage, "png", scan1file);
			}
			
			if(scanPhoto2!=null){
				BufferedImage scan2bufferimage = scanPhoto2;
				File scan2file = new File(imagepath+"/scan2.png");
				ImageIO.write(scan2bufferimage, "png", scan2file);
			}
			
			if(scanPhoto3!=null){
				BufferedImage scan3bufferimage = scanPhoto3;
				File scan3file = new File(imagepath+"/scan3.png");
				ImageIO.write(scan3bufferimage, "png", scan3file);
			}	

		} catch (IOException e) {

		}
	}
	
	
	
}
