package hospitalsystem;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import finalproject.Controller;
import finalproject.Doctor;
import finalproject.Patient;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class HospitalGui {

	public JFrame frame;
	private CardLayout cards; 
	private JPanel cardPanel;
	private JPasswordField passwordField;
	private JPanel containerPanel;//Change this
	private JPanel loginPanel;
	private JPanel tabsPanel;
	private JFormattedTextField usernameField;
	private JButton loginButton;
	private JPanel registryCard;
	private JButton registerButton;
	private JButton viewButton;
	private JButton logoutButton;
	private JButton importButton;
	private JPanel patientsCard;//TODO This should be in the CSVTable
	private JPanel imageCard;
	
	public JTextField tffirstName;
	public JTextField tfLastName;
	public JTextField tfDob;
	public JTextPane addressPane;
	public JTextField tfEmergencyPhone;//Should these be static?
	public JTextField tfMedicalCondition;
	public JComboBox billingBox;
	public JTextPane commentPane;
	
	private JLabel scan1;
	private JLabel scan2;
	private JLabel scan3;
	private JButton btnConfirmNewPatient;
	private BufferedImage profilePhoto;
	private BufferedImage scanPhoto1;
	private BufferedImage scanPhoto2;
	private BufferedImage scanPhoto3;
	private JLabel profileArea;
	
	private List<String> billingList;
	
	
//	private JTextField fldComments;
//	private JButton uploadProfile;
//	private JTextField fldFirstName;
//	private JTextField fldFirstName;
	private JLabel lblMedicalURL;
	private JLabel lblComments;
	private StringBuilder medicalURL;
	private String generatedURL;
	private JButton btngenerateURL;
	private JButton btnUploadScan;
	private JButton btnUploadScan_1;
	private JButton btnUploadScan_2;
	
	private File pp;
	private File scanfile1;
	private File scanfile2;
	private File scanfile3;
	
	public Patient patient;
	public Doctor doctor;
	public ArrayList<Patient> patientlist;
	public ArrayList<Doctor> doctorlist = new ArrayList<Doctor>(); //I don't have a master list
	public Controller controller;
	public DefaultTableModel m;
	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalGui window = new HospitalGui();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HospitalGui() {
		
		controller = new Controller();
		patientlist = controller.readtoArrayList();
		m = controller.createModel(patientlist);
		
		initialiseFrame();
		initializeLogin();
			
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialiseFrame(){
		frame = new JFrame();
		frame.setTitle("Hospital Registry System");
		frame.setBounds(100, 100, 1200, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
	}
	
	
	private void initializeLogin() {
		
		containerPanel = new JPanel();
		containerPanel.setBounds(0, 0, 1200, 698);
		frame.getContentPane().add(containerPanel);
		containerPanel.setLayout(new BorderLayout(0, 0));
		containerPanel.setVisible(true);
		
		tabsPanel = new JPanel(new GridLayout(20,0));
		//tabsPanel.setBorder(outline);
		tabsPanel.setBackground(Color.CYAN);
		containerPanel.add(tabsPanel, BorderLayout.WEST);
		
		viewButton = new JButton("View Patients"); 
		tabsPanel.add(viewButton);
		viewButton.addActionListener(new ActionListener() { 
			@Override public void actionPerformed(ActionEvent event) {
				cards.show(cardPanel,"View Patients Panel");
			} 
		});
		
		importButton = new JButton("Import Patient CSV"); 
		tabsPanel.add(importButton);
		
		logoutButton = new JButton("Logout"); 
		tabsPanel.add(logoutButton);
		
		registryCard = new JPanel();
		tabsPanel.add(registryCard);
		registryCard.setBackground(Color.LIGHT_GRAY); 
		registryCard.setLayout(null);
		logoutButton.addActionListener(new ActionListener() { 
			@Override public void actionPerformed(ActionEvent event) {
				//http://stackoverflow.com/questions/14821952/changing-panels-using-the-card-layout
				int reply = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", null, JOptionPane.YES_NO_OPTION);
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
		
		cards = new CardLayout(); 
		cardPanel = new JPanel();
		containerPanel.add(cardPanel, BorderLayout.NORTH);
		cardPanel.setLayout(cards); 
		cards.show(cardPanel, null);
		
		lblMedicalURL = new JLabel();
		lblMedicalURL.setText("<html> Type medical condition above then 'generate link'</html>");
		
		btngenerateURL = new JButton("Generate Link");
		btngenerateURL.setLocation(97, 71);
		btngenerateURL.setSize(20, 20);
		btngenerateURL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicalURL.append(tfMedicalCondition.getText());
				generatedURL = medicalURL.toString();
				lblMedicalURL.setText("<html> Wikipedia Link : <a href=\"\">"+tfMedicalCondition.getText()+"</a></html>");
				goWebsite(lblMedicalURL, generatedURL, tfMedicalCondition.getText());
				System.out.println(generatedURL);
				medicalURL.setLength(0);
				medicalURL.append("http://en.wikipedia.org/wiki/");	
			}
		});	
		
				//billingBox = new JComboBox(billingList.toArray());	
				
				commentPane = new JTextPane();
				JScrollPane commentScrollPane = new JScrollPane(commentPane);
				//commentPane.setPreferredSize(commentDimension);
				commentScrollPane.setBackground(Color.WHITE);
				
				JButton btnUploadProfile = new JButton("Upload Profile");
				btnUploadProfile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = new JFileChooser("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/sourcefiles/patientpics");
						chooser.showOpenDialog(null);
						try {
							profilePhoto = null;
							pp = chooser.getSelectedFile();
							profilePhoto = ImageIO.read(pp);
							Image dimg = profilePhoto.getScaledInstance(150, 150,Image.SCALE_SMOOTH);//This scales the image
							System.out.println(profileArea.getIcon());
							profileArea.setIcon(new ImageIcon(dimg));
							System.out.println(profileArea.getIcon());
						} catch (IOException io) {
							
						}
						
					}
				});
				
				btnUploadScan = new JButton("Upload Scan 1");
				btnUploadScan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = new JFileChooser("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/sourcefiles/brainpics");
						chooser.showOpenDialog(null);
						try {
							scanfile1 = chooser.getSelectedFile();
							scanPhoto1 = null;
							scanPhoto1 = ImageIO.read(scanfile1);
							Image scanphoto1 = scanPhoto1.getScaledInstance(150, 150,Image.SCALE_SMOOTH);//This scales the image
							scan1.setIcon(new ImageIcon(scanphoto1));
							System.out.println(scan1.getIcon());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				
				btnUploadScan_1 = new JButton("Upload Scan 2");
				btnUploadScan_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = new JFileChooser("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/sourcefiles/brainpics");
						chooser.showOpenDialog(null);
						try {
							scanfile2 = chooser.getSelectedFile();
							scanPhoto2 = null;
							scanPhoto2 = ImageIO.read(scanfile2);
							Image scanphoto2 = scanPhoto2.getScaledInstance(150, 150,Image.SCALE_SMOOTH);//This scales the image
							scan2.setIcon(new ImageIcon(scanphoto2));
							System.out.println(scan2.getIcon());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				
				btnUploadScan_2 = new JButton("Upload Scan 3");
				btnUploadScan_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = new JFileChooser("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/sourcefiles/brainpics");
						chooser.showOpenDialog(null);
						try {
							scanfile3 = chooser.getSelectedFile();
							scanPhoto3 = null;
							scanPhoto3 = ImageIO.read(scanfile3);
							Image scanphoto3 = scanPhoto3.getScaledInstance(150, 150,Image.SCALE_SMOOTH);//This scales the image
							scan3.setIcon(new ImageIcon(scanphoto3));
							System.out.println(scan3.getIcon());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				//scan1.setSize(scan1Dimension);		
				
				scan2 = new JLabel();
				scan2.setBackground(Color.WHITE);
				scan2.setOpaque(true);
				//scan1.setSize(scan2Dimension);		
				
				scan3 = new JLabel();
				scan3.setBackground(Color.WHITE);
				scan3.setOpaque(true);
				patientsCard = new CSVTable(m,patientlist);
				cardPanel.add(patientsCard, "View Patients Panel");
				patientsCard.setVisible(false);
				

				btnConfirmNewPatient = new JButton("Confirm New Patient");
				btnConfirmNewPatient.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setFields();
						generateID();
						storeImages();
						patientlist = arraylistAppend();
						rewriteFile();
						m.fireTableDataChanged();
//				m = controller.createModel(patientlist);
						reRenderTable(m, patientlist);
						JOptionPane.showMessageDialog(frame, "New patient has been added");
					}
				});
				
//		imageCard = new JPanel();
//		imageCard.setBackground(Color.MAGENTA);
				

				
				registerButton = new JButton("Register New Patient"); 
				registerButton.addActionListener(new ActionListener() { 
					@Override public void actionPerformed(ActionEvent event) {
						cards.show(cardPanel,"Registration Panel");
					} 
				});

		loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel);
		loginPanel.setBounds(0, 0, 1200, 698);
		loginPanel.setVisible(true);
		loginPanel.setLayout(null);
		
		usernameField = new JFormattedTextField();
		usernameField.setBounds(320, 340, 159, 25);
		usernameField.setText("Enter Email");
		loginPanel.add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(320, 402, 159, 22);
		passwordField.setToolTipText("Enter Password");
		loginPanel.add(passwordField);
		passwordField.setVisible(true);
			
		
		loginButton = new JButton("Login");
		loginButton.setBounds(320, 480, 159, 25);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
			}
		});
		loginPanel.add(loginButton);

		
	}
	
	private void initialiseSystem(){
		Border outline = BorderFactory.createLineBorder(Color.black);
		
		/*
		 * Set the constraints
		 */
		
		/*
		 * Initialise the first column with JLabels
		 */
		
		JLabel lblFirstName = new JLabel("First Name");
		
		JLabel lblLastName = new JLabel("Last Name");
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		
		JLabel lblAddressLine = new JLabel("Address");
		
		JLabel lblEmergencyPhoneNumber = new JLabel("Emergency Phone");
		lblEmergencyPhoneNumber.setBounds(62, 240, 141, 16);
		
		JLabel lblMedicalCondition = new JLabel("Medical Condition");
		lblMedicalCondition.setBounds(62, 278, 141, 16);
		
		lblMedicalURL = new JLabel("Medical URL");
		lblMedicalURL.setBounds(62, 278, 141, 16);

		
		JLabel lblBillingCycle = new JLabel("Billing Cycle");
		lblBillingCycle.setBounds(62, 309, 141, 16);
		
		lblComments = new JLabel("Comments");
		lblComments.setBounds(62, 344, 141, 16);
		
		/*
		 * Initialise the second column with text fields
		 */
		
		tffirstName = new JTextField();
		tffirstName.setColumns(20);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(20);
	
		tfDob = new JTextField();
		tfDob.setColumns(20);
		
		addressPane = new JTextPane();
		Dimension addressPaneDimension = new Dimension(240,100);
		addressPane.setPreferredSize(addressPaneDimension);
		JScrollPane addressScrollPane = new JScrollPane(addressPane);
		addressScrollPane.setBackground(Color.WHITE);
		
		tfEmergencyPhone = new JTextField();
		tfEmergencyPhone.setColumns(20);
		
		tfMedicalCondition = new JTextField();
		tfMedicalCondition.setColumns(10);
		
		
		medicalURL = new StringBuilder();
		medicalURL.append("http://en.wikipedia.org/wiki/");	
		
		/*
		 * 	//http://stackoverflow.com/questions/8669350/jlabel-hyperlink-to-open-browser-at-correct-url
		//http://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
		 */
		
		List<String> billingList = Arrays.asList("Monthly","Quarterly","Annual");
		Dimension commentDimension = new Dimension(240,100);
		
		
		
		
		/*
		 * Here I generate the image upload buttons
		 * http://stackoverflow.com/questions/13512612/browse-for-image-file-and-display-it-using-java-swing
		 * http://stackoverflow.com/questions/15182329/how-to-set-icon-to-a-jlabel-from-an-image-from-a-folder
		 * http://stackoverflow.com/questions/8883937/image-wont-display-in-jlabel
		 * http://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
		 * http://stackoverflow.com/questions/9147977/getwidth-and-getheight-are-0-after-calling-setpreferredsize
		 */
		
		/*
		 * Here I generate the JLabels for images
		 */
		
		profileArea = new JLabel();
		profileArea.setBackground(Color.WHITE);
		profileArea.setOpaque(true);
		
		Dimension photoDimension = new Dimension(20,20);
		profileArea.setMaximumSize(photoDimension);
//		profileArea.setSize(photoDimension);
		
		scan1 = new JLabel();
		scan1.setBackground(Color.WHITE);
		scan1.setOpaque(true);
		//c.gridheight = GridBagConstraints.RELATIVE;
		Dimension scan1Dimension = new Dimension(20,20);
		scan1.setMaximumSize(scan1Dimension);
		Dimension scan2Dimension = new Dimension(20,20);
		//c.gridheight = GridBagConstraints.RELATIVE;
		Dimension scan3Dimension = new Dimension(20,20);
		
		
		//frame.setVisible(true);	 
	}

	private void goWebsite(JLabel website, final String url, String text) {
		lblMedicalURL.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblMedicalURL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    try {
                            Desktop.getDesktop().browse(new URI(url));
                    } catch (URISyntaxException | IOException ex) {
                            //It looks like there's a problem
                    }
            }
        });
    }
	
	private void setFields(){
		patient = new Patient();
		doctor = new Doctor();

		patient.setpFirstName(tffirstName.getText());
		patient.setpLastName(tfLastName.getText());
		patient.setpDob(tfDob.getText());
		patient.setpAddress(addressPane.getText().replaceAll("\n|\t", " "));
		patient.setpBillingCycle((String) billingBox.getSelectedItem());
		patient.setpMedicalCondition(tfMedicalCondition.getText());
		patient.setpComments(commentPane.getText().replaceAll("\n|\t", " "));
		patient.setpPhoneNumber(tfEmergencyPhone.getText());
		doctor.setDuserName(usernameField.getText());
		patient.setpatientdUserName(usernameField.getText());
		doctor.setDpassWord(passwordField.getPassword());
			
		//This generates a new table with the file when a new patient is added.
	}
	
	private void generateID(){
		patient.setpID(patient.hashCode());
		patient.setpatientdID(doctor.hashCode());
		doctor.setdID(doctor.hashCode());
	}
	
	/*
	 * http://stackoverflow.com/questions/4801971/how-to-create-empty-folder-in-java
	 * http://stackoverflow.com/questions/10897221/how-to-save-the-the-image-in-folder-on-disk-using-java
	 */
	
	private void storeImages(){
		String imagepath = "/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/src/"+"patient-"+String.valueOf(patient.getpID());
		System.out.println(imagepath);
		boolean success = (new File(imagepath)).mkdirs();
		if (!success) {
		    System.out.println("Folder for images not created");
		}
		try {
		    // retrieve image
		    BufferedImage profilebufferimage = profilePhoto;
		    File profilefile = new File(imagepath+"/profilephoto.png");
		    ImageIO.write(profilebufferimage, "png", profilefile);
		    
//		    BufferedImage scan1bufferimage = profilePhoto;
//		    File scan1file = new File(imagepath+"/profilephoto.png");
//		    ImageIO.write(profilebufferimage, "png", profilefile);
//		    
//		    BufferedImage scan1bufferimage = profilePhoto;
//		    File scan1file = new File(imagepath+"/profilephoto.png");
//		    ImageIO.write(profilebufferimage, "png", profilefile);
//		    
//		    BufferedImage scan1bufferimage = profilePhoto;
//		    File scan1file = new File(imagepath+"/profilephoto.png");
//		    ImageIO.write(profilebufferimage, "png", profilefile);
		    
		} catch (IOException e) {
		    
		}
	}
	
	private ArrayList<Patient> arraylistAppend(){
		patientlist.add(patient);
		//System.out.print(patientlist.get(patientlist.size() - 1).toString());
		for(int i=0;i<patientlist.size();i++){
			System.out.print(patientlist.get(i).toString());
		}
		doctorlist.add(doctor);
		for(int i=0;i<patientlist.size();i++){
			System.out.print(patientlist.get(i).toString());
		}
		return patientlist;
		//System.out.print(patientlist.get(patientlist.size() - 1).toString());
	}
	
	private void rewriteFile(){
		controller = new Controller();
		try {
			controller.writeFile(patient);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void reRenderTable(DefaultTableModel m, ArrayList<Patient> patientlist){
		m.fireTableDataChanged();
		patientsCard.repaint();
		//patientsCard = new CSVTable(m,patientlist);
	}
		
}
