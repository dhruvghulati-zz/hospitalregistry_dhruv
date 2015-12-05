package finalproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class View {

	private JFrame frame;

	/*
	 * All my panels
	 */
	private JPanel loginPanel;
	private JPanel containerPanel;
	private JPanel tabsPanel;
	private JPanel registrycard;
	private JPanel patientcard = new JPanel();
	private JPanel registerDoctorPanel;

	/**
	 * My fixed arraylist of master doctors
	 */
	private ArrayList<Doctor> doctorlist;
	/**
	 * My fixed arraylist of master patients.
	 */
	private ArrayList<Patient> patientlist;


	/**
	 * I initialise a new controller to be able to access data outside of the GUI.
	 */
	private Controller controller = new Controller();


	/**
	 * This is the index of a patient if found in a master patient list, to prevent duplication
	 */
	private int patientindex;

	/*
	 * Components on doctor register panel
	 */
	private JTextField tfdRegisterFirstName;
	private JTextField tfdRegisterLastName;
	private JTextField tfdRegisterUserName;
	private JPasswordField tfdRegisterPassWord;
	private JPasswordField newPasswordField;
	private JButton btnRegisterDoctor;
	private JLabel lblRegisterIcon;

	/*
	 * Components on login panel;
	 */

	private JFormattedTextField loginUsernameField;
	private JPasswordField loginPasswordField;


	/*
	 * Components on registrypanel to access later
	 */
	private JTextField tffirstName;
	private JTextField tfLastName;
	private JTextField tfDob;
	private JTextPane addressPane;
	private JTextField tfEmergencyPhone;
	private JTextField tfMedicalCondition;
	private JComboBox<?> billingBox;
	private JTextPane commentPane;
	private JLabel profileArea;
	private JScrollPane commentScrollPane;
	private JScrollPane addressScrollPane;
	/**
	 * This is used to populate the JComboBox billingbBox.This is used to fill the JComboBox billingBox, the different billing cycle options. 
	 */
	private static List<String> billingList = Arrays.asList("Monthly","Quarterly","Annual");
	/**
	 * This is used to append to whatever has been placed in the Medical Condition text field, and is reset to null every time.
	 */
	private StringBuilder medicalURL;
	private String generatedURL;
	private File scanfile1;
	private File scanfile2;
	private File scanfile3;
	private JLabel scan1;
	private JLabel scan2;
	private JLabel scan3;
	private File pp;
	private BufferedImage profilePhoto;
	private BufferedImage scanPhoto1;
	private BufferedImage scanPhoto2;
	private BufferedImage scanPhoto3;
	private JLabel lblMedicalURL;
	/*
	 * Locations of the relevant resources for displaying the executable.
	 */
	private String hospitaLogo = "resources/images/icons/hospital_logo.png";
	private String profilePics = "resources/images/patientpics";
	private String brainPics = "resources/images/brainpics";


	/*
	 * Components on tablePanel to access
	 */

	private JTextField filterText;
	/**
	 * The table model on which the JTable is always based.
	 */
	private PatientTableModel tablemodel = null;
	/**
	 * The TableRowSorter on which the JTable is always based.
	 */
	private TableRowSorter<PatientTableModel> sorter;
	/**
	 * Ensures that the patientTable is always based only on one table model which changes are fired in and out of.
	 */
	private JTable patientTable = new JTable(tablemodel);
	private JScrollPane patientscrollPane = new JScrollPane(patientTable);
	/**
	 * The filtered list based on the doctor who is logged in at the time, to ensure doctors cannot see other doctors patients.
	 */
	private ArrayList<Patient> filteredPatientList;

	
	/**
	 * This component is a JLabel which takes inputs from the loggedinDoctor at the time
	 */
	private JLabel lblDoctorName;

	/**
	 * This is a switch which recognises if you are editing an old patient
	 */
	private boolean editPressed = false;

	/**
	 * This recognises the doctor on the actual session.
	 */
	private Doctor loggedInDoctor;

	/**
	 * Launch the application.
	 * @param args  The main arguments taken for the GUI are the constructor
	 * for the class, an EventQueue thread, and the JFrame.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * References:
	 * http://forums.codeguru.com/showthread.php?40721-Printing-a-JTable-vector-to-the-console
	 * 
	 */
	public View() {
		
		try {
			controller.writeNewDoctorFile();
			controller.writeNewPatientFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		doctorlist = controller.extractDoctorList();
		initialize();		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Hospital Registry System");
		frame.setBounds(0, 0, 1200, 700);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		generateLoginPanel();
		loginPanel.setVisible(true);

		registerDoctorPanel();
		registerDoctorPanel.setVisible(false);

		containerPanel = new JPanel();
		containerPanel.setBounds(0, 0, 1200, 678);
		containerPanel.setLayout(null);
		containerPanel.setVisible(false);
		frame.getContentPane().add(containerPanel);

		patientcard.setVisible(false);

		generateRegistryPanel();
		registrycard.setVisible(false);

		generateTabsPanel();

	}

	/**
	 * Allows a user to place inputs to register a new doctor, and verify them in the checkRegisterDoctorExists() method.
	* 
	*@return registryCard  A JPanel containing all the necessary fields to register a new doctor.
	 */
	
	private JPanel registerDoctorPanel() {
		registerDoctorPanel = new JPanel();
		registerDoctorPanel.setBounds(0, 0, 1200, 678);
		registerDoctorPanel.setLayout(null);
		frame.getContentPane().add(registerDoctorPanel);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(474, 231, 107, 16);
		registerDoctorPanel.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(474, 273, 107, 16);
		registerDoctorPanel.add(lblLastName);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(474, 313, 107, 16);
		registerDoctorPanel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(474, 363, 107, 16);
		registerDoctorPanel.add(lblPassword);

		JLabel lblRegister = new JLabel("Register as a Doctor");
		lblRegister.setFont(new Font("Helvetica", Font.PLAIN, 18));
		lblRegister.setBounds(519, 190, 175, 16);
		registerDoctorPanel.add(lblRegister);

		tfdRegisterFirstName = new JTextField();
		tfdRegisterFirstName.setBounds(636, 226, 130, 26);
		registerDoctorPanel.add(tfdRegisterFirstName);
		tfdRegisterFirstName.setColumns(10);

		tfdRegisterLastName = new JTextField();
		tfdRegisterLastName.setColumns(10);
		tfdRegisterLastName.setBounds(636, 268, 130, 26);
		registerDoctorPanel.add(tfdRegisterLastName);

		tfdRegisterUserName = new JTextField();
		tfdRegisterUserName.setColumns(10);
		tfdRegisterUserName.setBounds(636, 308, 130, 26);
		registerDoctorPanel.add(tfdRegisterUserName);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(474, 408, 161, 16);
		registerDoctorPanel.add(lblConfirmPassword);

		JButton btnBack = new JButton("Back to Login");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(true);
				registerDoctorPanel.setVisible(false);
				loginPasswordField.setText("");
				loginUsernameField.setText("");
			}
		});
		btnBack.setBounds(432, 466, 161, 29);
		registerDoctorPanel.add(btnBack);

		btnRegisterDoctor = new JButton("Register and Login");
		btnRegisterDoctor.setBounds(605, 466, 161, 29);
		registerDoctorPanel.add(btnRegisterDoctor);
		btnRegisterDoctor.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(!tfdRegisterPassWord.getText().equals(newPasswordField.getText())){
					JOptionPane.showMessageDialog(frame, "Passwords must match!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
				else{
					Doctor adddoctor = setRegistryFields();
					checkRegisterDoctorExists(adddoctor);
				}

			}
		}
				);

		lblRegisterIcon = new JLabel("");
		lblRegisterIcon.setBounds(519, 41, 186, 122);
		registerDoctorPanel.add(lblRegisterIcon);

		ImageIcon imageIcon = new ImageIcon(hospitaLogo); 
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		lblRegisterIcon.setIcon(imageIcon);


		JSeparator separator = new JSeparator();
		separator.setBounds(455, 207, 337, 12);
		registerDoctorPanel.add(separator);

		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(636, 358, 130, 26);
		registerDoctorPanel.add(newPasswordField);

		tfdRegisterPassWord = new JPasswordField();
		tfdRegisterPassWord.setBounds(636, 403, 130, 26);
		registerDoctorPanel.add(tfdRegisterPassWord);
		registerDoctorPanel.setVisible(false);

		return registerDoctorPanel;

	}
	
	/**
	 * Similar to checkDoctorExists, based on what has been inputted in the register panel, does verification and conditional actions to either log
	 * the user in with a new session or log the user in with an existing doctor, or go back to login.
	* 
	*@param adddoctor  The doctor to be verified against via the full fields inputted in the registry panel (even though only username and password
	*truly matter to make the doctor unique).
	*@return doctorexists  Whether the doctor actually exists in the master doctor list or not.
	 */
	private boolean checkRegisterDoctorExists(Doctor adddoctor){
		boolean doctorexists = false;
		int addID = adddoctor.getdID();
		for(Doctor d:doctorlist){
			if(d.getdID()==addID) {	
				doctorexists = true;
				adddoctor = d;
				break;
			}
		}
		if(!doctorexists){
			JOptionPane.showMessageDialog(frame, "Congratulations! You are successfully registered as "+
					adddoctor.getFirstName()+" "+adddoctor.getLastName(),"Login Successful",
					JOptionPane.INFORMATION_MESSAGE);
			doctorlist.add(adddoctor);
			try {
				controller.writeDoctorFile(doctorlist);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			lblDoctorName.setText("Logged in as "+adddoctor.getFirstName()+" "+adddoctor.getLastName());
			loggedInDoctor = adddoctor;
			patientlist = controller.extractPatientList();
			storeImages(patientlist);
			filteredPatientList=new ArrayList<Patient>();
			for( Patient p : patientlist) {
				if (p.getpatientdID()==loggedInDoctor.getdID()){
					filteredPatientList.add(p);
				}
			}
			for(Patient f:filteredPatientList){
				if (f.getpatientdID()==loggedInDoctor.getdID()){
					patientlist.remove(f);
				}
			}
			tablemodel = new PatientTableModel(filteredPatientList);
			generateTablePanel();
			clearFields();
			patientcard.setVisible(false);
			loginPanel.setVisible(false);
			containerPanel.setVisible(true);
			registrycard.setVisible(true);
			registerDoctorPanel.setVisible(false);
		}if(doctorexists){
			int reply = JOptionPane.showConfirmDialog(null, adddoctor.getFirstName()+" "+adddoctor.getLastName()+" seems to be already "
					+ "registered. Do you want to login as this doctor? If no, change the username and password and try again"
					, "Registration", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(frame, "Congratulations! You are successfully logged in as "+
						adddoctor.getFirstName()+" "+adddoctor.getLastName(),"Login Successful",
						JOptionPane.INFORMATION_MESSAGE);
				lblDoctorName.setText("Logged in as "+adddoctor.getFirstName()+" "+adddoctor.getLastName());
				loggedInDoctor = adddoctor;
				patientlist = controller.extractPatientList();
				storeImages(patientlist);
				filteredPatientList=new ArrayList<Patient>();
				for( Patient p : patientlist) {
					if (p.getpatientdID()==loggedInDoctor.getdID()){
						filteredPatientList.add(p);
					}
				}
				for(Patient f:filteredPatientList){
					if (f.getpatientdID()==loggedInDoctor.getdID()){
						patientlist.remove(f);
					}
				}
				tablemodel = new PatientTableModel(filteredPatientList);
				generateTablePanel();
				clearFields();
				patientcard.setVisible(false);
				loginPanel.setVisible(false);
				registerDoctorPanel.setVisible(false);
				containerPanel.setVisible(true);
				registrycard.setVisible(true);	
			}				
		}

		return doctorexists;

	}
	
	/**
	 * This method clears all the fields on the register patient panel to be null.
	 */
	private void clearFields(){
		addressPane.setText("");
		commentPane.setText("");
		tfMedicalCondition.setText("");
		Component[] components = registrycard.getComponents();
		for (Component component : components) {
			if(component.equals(tfDob)){
				tfDob.setText("");
			}
			if (component instanceof JTextField) {
				JTextComponent specificObject = (JTextComponent) component;
				specificObject.setText("");				
			}if(component.equals(scan1)||component.equals(scan2) ||component.equals(scan3) || component.equals(profileArea)){
				JLabel specificObject = (JLabel) component;
				specificObject.setIcon(null);
			}else if(component.equals(lblMedicalURL)){
				lblMedicalURL.setText("<html> Wikipedia Link : <a href=\"\">"+tfMedicalCondition.getText()+"</a></html>");
			}

		}
	}
	
	/**
	 * This generates the login panel
	 * @return loginPanel  The login panel or first screen.
	 */
	private JPanel generateLoginPanel(){
		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 1200, 678);
		frame.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);

		loginUsernameField = new JFormattedTextField();
		loginUsernameField.setBounds(517, 330, 159, 25);
		loginUsernameField.setText("Enter Username");
		loginUsernameField.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				loginUsernameField.setText("");
			}
		});
		loginPanel.add(loginUsernameField);

		loginPasswordField = new JPasswordField();
		loginPasswordField.setText("Enter Password");
		loginPasswordField.setBounds(517, 391, 159, 25);
		loginPasswordField.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				loginPasswordField.setText("");
			}
		});
		loginPanel.add(loginPasswordField);


		JButton loginButton = new JButton("Login");
		loginButton.setBounds(517, 465, 159, 25);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doctor checkdoctor = setDoctorFields();//Obtain the fields inputted and create a new doctor to validate
				checkDoctorExists(checkdoctor);//Validate this new doctor and then perform some conditional actions.
			}
		});


		loginPanel.add(loginButton);	

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(517, 113, 159, 158);
		loginPanel.add(lblNewLabel);	
		ImageIcon imageIcon = new ImageIcon(hospitaLogo); 
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		lblNewLabel.setIcon(imageIcon);
		loginPanel.setVisible(false);
		return loginPanel;

	}

	/**
	 * Checks whether the doctor that has been inputted within the first login panel has been registered or not. If not, then proceeds to registry,
	 * if yes, then conducts the standard actions to be taken whenever a login has been confirmed and a "session" begun.
	* 
	*@param adddoctor  The doctor to be verified, taken directly from whatever has been inputted as the username and password within the first login panel
	*@return doctorexists  A boolean to check whether the doctor already exists, and if so, start the session, if not, take you to the registry panel
	 */
	private boolean checkDoctorExists(Doctor adddoctor) {
		boolean doctorexists = false;
		int addID = adddoctor.getdID();
		for(Doctor d:doctorlist){
			if(d.getdID()==addID) {	
				doctorexists = true;
				adddoctor = d;
				break;
			}
		}
		if(!doctorexists){
			int reply = JOptionPane.showConfirmDialog(null, "You don't seem to be registered - proceed to Registry? If no, try again."
					, "Registration", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				loginPanel.setVisible(false);
				registerDoctorPanel.setVisible(true);
				Component[] components = registerDoctorPanel.getComponents();
				for(Component c: components){
					if (c instanceof JTextField) {
						JTextComponent specificObject = (JTextComponent) c;
						specificObject.setText("");
					}
				}
			}			
		}else{
			JOptionPane.showMessageDialog(frame, "Congratulations! You are logged in as "+
					adddoctor.getFirstName()+" "+adddoctor.getLastName(),"Login Successful",
					JOptionPane.INFORMATION_MESSAGE);
			lblDoctorName.setText("Logged in as "+adddoctor.getFirstName()+" "+adddoctor.getLastName());
			loggedInDoctor = adddoctor;
			patientlist = controller.extractPatientList();
			storeImages(patientlist);
			filteredPatientList=new ArrayList<Patient>();
			for( Patient p : patientlist) {
				if (p.getpatientdID()==loggedInDoctor.getdID()){
					filteredPatientList.add(p);
				}
			}
			for(Patient f:filteredPatientList){
				if (f.getpatientdID()==loggedInDoctor.getdID()){
					patientlist.remove(f);
				}
			}
			tablemodel = new PatientTableModel(filteredPatientList);
			generateTablePanel();
			clearFields();
			patientcard.setVisible(false);
			loginPanel.setVisible(false);
			containerPanel.setVisible(true);
			registrycard.setVisible(true);			
		}

		return doctorexists;

	}
	
	/**
	 *
	 * Purely from the fields entered in the first login panel, creates the doctor ID which the user has entered in, to be then verified against.
	 * 
	 *@return checkdoctor  The doctor to be verified upon login. 
	 */
	
	@SuppressWarnings("deprecation")
	private Doctor setDoctorFields() {
		Doctor checkdoctor = new Doctor();

		checkdoctor.setDpassWord(loginPasswordField.getText());
		checkdoctor.setDuserName(loginUsernameField.getText());			
		checkdoctor.setdID(checkdoctor.hashCode());
		return checkdoctor;
	}

	
/**
 * 	
	 * <dl>
	 * <dt> Purpose:
	 * <dd> This method generates a registration panel to register a new patient.
	 *
	 * <dt> Description:
	 * <dd> Allows a user to upload images onto JLabels as scaled icons (scans, profile pictures), contains validation via calling my DateVerifier
	 * and PhoneVerifier regex verifier classes (which are children of InputVerifier abstract class), and also contains a combination of JTextFields and
	 * JTextPanes. A doctor can edit or register a patient's first name, last name , date of birth, medical condition and other fields on this panel.
	 * In addition, a doctor can generate any clickable URL on Wikipedia for any medical condition, given it has a URL on Wikipedia, and then save this
	 * medical condition to the database.
	 * 
	 * <dt> References:
	 * <dd>http://stackoverflow.com/questions/13512612/browse-for-image-file-and-display-it-using-java-swing
	http://stackoverflow.com/questions/15182329/how-to-set-icon-to-a-jlabel-from-an-image-from-a-folder
	http://stackoverflow.com/questions/8883937/image-wont-display-in-jlabel
	http://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
	http://stackoverflow.com/questions/9147977/getwidth-and-getheight-are-0-after-calling-setpreferredsize
	http://www.java2s.com/Tutorial/Java/0240__Swing/JFormattedTextFieldwithSimpleDateFormat.htm
	http://www.java2s.com/Code/JavaAPI/javax.swing/newJFormattedTextFieldnewMaskFormatter.htm
	//http://stackoverflow.com/questions/4252257/jformattedtextfield-with-maskformatter
	 * 
	 * </dd>
	 * 
	 * 
	 * </dl>
	 * @return registrycard  A JPanel with the required fields.
	 *
 */
	private JPanel generateRegistryPanel(){
		registrycard = new JPanel();
		registrycard.setBackground(new Color(204, 255, 255));
		registrycard.setBounds(212, 0, 988, 678);	

		registrycard.setLayout(new MigLayout("", "[141px][8px][37px][5px][132px][4px][236.00px][9.00px][134px][46px][261.00px]", "[26px][4px][4px][1px][21px][4px][26px][9px][40.00px][3px][48px][11px][26px][12px][29px][14px][22px][12px][4px][1px][38px][12px][107px][22.00px][22.00px][0.00px][110px]"));

		JLabel lblFirstName = new JLabel("First Name");
		registrycard.add(lblFirstName, "cell 0 0,growx,aligny center");

		JLabel lblLastName = new JLabel("Last Name");
		registrycard.add(lblLastName, "cell 0 4,growx,aligny top");

		JLabel lblDateOfBirth = new JLabel("Date of Birth (dd/MM/yyyy)");
		registrycard.add(lblDateOfBirth, "cell 0 6,growx,aligny center");

		JLabel lblAddressLine = new JLabel("Address");
		registrycard.add(lblAddressLine, "cell 0 8,growx,aligny top");

		JLabel lblEmergencyPhoneNumber = new JLabel("Emergency Phone (UK format)");
		registrycard.add(lblEmergencyPhoneNumber, "cell 0 12,growx,aligny center");

		JLabel lblMedicalCondition = new JLabel("Enter Medical Condition e.g. 'Glaucoma'");
		registrycard.add(lblMedicalCondition, "cell 0 14 3 1,growx,aligny center");

		lblMedicalURL = new JLabel();
		lblMedicalURL.setText("<html> Type medical condition above then 'generate link'</html>");
		registrycard.add(lblMedicalURL, "cell 0 16,growx,aligny top");


		JLabel lblBillingCycle = new JLabel("Billing Cycle");
		registrycard.add(lblBillingCycle, "cell 0 20,growx,aligny center");

		JLabel lblComments = new JLabel("Comments");
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
		tfDob.setInputVerifier(new DateVerifier());
		tfDob.setColumns(10);
		registrycard.add(tfDob, "cell 4 6,alignx left,aligny top");

		tfEmergencyPhone = new JTextField();
		tfEmergencyPhone.setInputVerifier(new PhoneVerifier());
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

		JButton btnGenerateURL = new JButton("Generate URL");
		registrycard.add(btnGenerateURL, "cell 6 14,alignx left,aligny top");	
		btnGenerateURL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicalURL.append(tfMedicalCondition.getText().replaceAll(" ", "_"));
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

		JButton btnUploadProfile = new JButton("Upload Profile");
		registrycard.add(btnUploadProfile, "cell 8 22,alignx left,aligny center");
		btnUploadProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(profilePics);
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

		JButton btnUploadScan_2 = new JButton("Upload Scan 3");
		registrycard.add(btnUploadScan_2, "cell 8 16,alignx right,aligny top");
		btnUploadScan_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(brainPics);
				chooser.showOpenDialog(null);
				try {
					scanfile3 = chooser.getSelectedFile();
					scanPhoto3 = null;
					scanPhoto3 = ImageIO.read(scanfile3);
					Image scanphoto3 = scanPhoto3.getScaledInstance(120, 120,Image.SCALE_SMOOTH);//This scales the image
					scan3.setIcon(new ImageIcon(scanphoto3));
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

		JButton btnUploadScan = new JButton("Upload Scan 1");
		registrycard.add(btnUploadScan, "cell 8 0 1 3,alignx right,aligny bottom");
		btnUploadScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(brainPics);
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

		JButton btnUploadScan_1 = new JButton("Upload Scan 2");
		registrycard.add(btnUploadScan_1, "cell 8 10,alignx right,aligny top");
		btnUploadScan_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(brainPics);
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

		JButton btnConfirmNewPatient = new JButton("Confirm Patient");
		btnConfirmNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
					Patient addpatient = setPatientFields();
					checkNewPatient(addpatient);					
				}			
		});
		registrycard.add(btnConfirmNewPatient, "cell 8 26 3 1,alignx left,aligny top");
		containerPanel.add(registrycard);
		return registrycard;

	}

	/**
	 * This checks if this is a new patient or not, and accounts for if not a new patient, and edits the existing patient instead
	 * 
	 * References:
	 * http://stackoverflow.com/questions/7736273/jtable-refreshing
	 * 
	 * @param addpatient  The patient to be checked when doing any edits or additions of new patients.
	 */
	private void checkNewPatient(Patient addpatient){
		boolean patientexists = false;
		int addID = addpatient.getpID();
		patientindex = 0;
		Patient replacePatient = null;
		for(Patient p:filteredPatientList){
			if(p.getpID()==addID) {	
				patientexists = true;
				patientindex = filteredPatientList.indexOf(addpatient);
				replacePatient = filteredPatientList.get(patientindex);
				break;
			}
		}
		if(!patientexists && editPressed){	
			JOptionPane.showMessageDialog(frame,
					"You are changing the first name or last name of the patient. You cannot edit those fields - add a new"
							+ "patient instead if you prefer, otherwise edit another field",
							"Warning - trying to create new patient",
							JOptionPane.WARNING_MESSAGE);				

		}
		else if(patientexists && editPressed){
			patientlist.removeAll(filteredPatientList);
			filteredPatientList.set(patientindex, addpatient);
			tablemodel.setList(filteredPatientList);
			storeImages(addpatient);			
			try {
				patientlist.addAll(filteredPatientList);
				controller.writePatientFile(patientlist);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(frame, "Existing patient has been edited");
			patientcard.setVisible(true);
			registrycard.setVisible(false);
		}
		else if(!patientexists && !editPressed){			
			storeImages(addpatient);
			patientlist.removeAll(filteredPatientList);
			filteredPatientList.add(addpatient);
			tablemodel.setList(filteredPatientList);			
			try {
				patientlist.addAll(filteredPatientList);
				controller.writePatientFile(patientlist);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(frame, "New patient has been added");
			patientcard.setVisible(true);
			registrycard.setVisible(false);
		}else{
			int reply = JOptionPane.showConfirmDialog(frame, "This patient's name is already in the system! Want to edit the patient instead? If no,"
					+ "try adding a patient with a different name","Warning", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				editPressed = true;
				setEditText(replacePatient);
			}
		}
		
	}
	
	
	/**
	 * Upon initialiation of the system, creates folder for every single patient within the file, but doesn't edit any existing
	*photo files. Folders are placed in the databases directory for the correct name 
	* 
	*@param patientlist  The master arraylist of patients to create a filesystem for.
	 */
	private void storeImages(ArrayList<Patient> patientlist){
		for(Patient p: patientlist){
			String imagepath = "resources/imagedatabase/"+
					String.valueOf(p.getpatientdUserName().replaceAll("\\s+","")+"/"+p.getpFirstName().toLowerCase()+"_"+p.getpLastName().toLowerCase());
			File imagefolder = new File(imagepath);
			imagefolder.mkdirs();
		}
	}
	
	
	/**
	 * 
	* Stores the images of a patient, and replaces old images by writing the images as png fils with the exact same file name.
	* Because this is the exact path that identifies a patient as unique, the system will not create a new directory if we are looking at the same
	* patient.
	* 
	* @param patient  The patient which is being edited or added
	* and for which the files need to be added or changed.
	*
	 */
	private void storeImages(Patient patient){
			
		String imagepath = "resources/imagedatabase/"+
				String.valueOf(patient.getpatientdUserName().replaceAll("\\s+","")+"/"+patient.getpFirstName().toLowerCase()+"_"+patient.getpLastName().toLowerCase());
		File imagefolder = new File(imagepath);
		imagefolder.mkdirs();
		try {
			if(profilePhoto!=null){
				BufferedImage profilebufferimage = profilePhoto;
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
	
	/**
	 * 
	* For any patient attempting to be registered, whatever has been entered into the text fields by the user is taken as a variable and then by using
	* the setter of the Patient class, we set the object's variables to be certain things.
	* @return addpatient  The patient to be checked against with corresponding fields, and most importantly their patient hashcode or ID.
	*
	 */
	private Patient setPatientFields(){
		Patient addpatient = new Patient();

		addpatient.setpFirstName(tffirstName.getText());
		addpatient.setpLastName(tfLastName.getText());
		addpatient.setpDob(tfDob.getText());
		addpatient.setpAddress(addressPane.getText().replaceAll("\n|\t|,", " "));
		addpatient.setpBillingCycle((String) billingBox.getSelectedItem());
		addpatient.setpMedicalCondition(tfMedicalCondition.getText());
		addpatient.setpComments(commentPane.getText().replaceAll("\n|\t|,", " "));
		addpatient.setpPhoneNumber(tfEmergencyPhone.getText());
		addpatient.setpatientdID(loggedInDoctor.getdID());
		addpatient.setpatientdUserName(loggedInDoctor.getDuserName());
		addpatient.setpID(addpatient.hashCode());
		return addpatient;

	}

		
/**
 * 
	 * <dl>
	 * <dt> Purpose:
	 * <dd> This method sets the details of my table panel, which contains the main view of patients to be manipulated and viewed. I allow the 
	 * deleting of one patient at a time, editing of one patient at a time, and exporting of multiple patients to CSV format (this is hard coded to be CSV,
	 * but could be anything).
	 *
	 * <dt> Description:
	 * <dd> As well as setting specific parameters of the JTable, for example allowing columns to be resized, this method is very important as it
	 * sets the sorter and the table model of the JTable to be fixed (sorter and patienttable). This allows me to fire events to the JTable whenever any of
	 * these variables are changed, and also to know that my search is always based on the correct tablemodel.
	 * . The table also allows search to be possible across all fields, just date of birth, just billing cycle, just medical
	 * condition, or just by patient ID. I also disable sorting via column headers even though I convert user selections to the selection in the table
	 * model anyways, just to avoid any potential issues. There is an implementation of Document Listeners and Focus Listeners for the search, JTables
	 * and other aspects. When a patient is edited, a boolean "editPressed" variable is set which then changes to false when an edit is completed. 
	 * This affects whether I set an existing patients variables, or whether I add a new patient for example. Finally, on the table itself you can delete 
	 * a patient or export an array of patients to a new CSV. There is validation because a user can only delete or edit one patient at a time.
	 * 
	 * <dt> References:
	 * <dd>http://zawoad.blogspot.co.uk/2009/02/filter-jtable-row-with-input-in-text.html
	http://stackoverflow.com/questions/856888/getting-selected-row-through-abstracttablemodel
	http://stackoverflow.com/questions/5797208/java-how-do-i-write-a-file-to-a-specified-directory
http://stackoverflow.com/questions/20526917/load-arraylist-data-into-jtable
http://zawoad.blogspot.co.uk/2009/02/filter-jtable-row-with-input-in-text.html
http://stackoverflow.com/questions/5594639/how-can-i-put-a-jcheckbox-on-a-jtable
http://www.coderanch.com/t/343795/GUI/java/Check-Box-JTable-header
http://stackoverflow.com/questions/5594639/how-can-i-put-a-jcheckbox-on-a-jtable
https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
http://stackoverflow.com/questions/5721504/jfilechooser-set-directory-to-a-path-in-a-file
http://stackoverflow.com/questions/33785870/add-a-checkbox-to-a-jtable-which-has-been-generated-from-a-csv
http://stackoverflow.com/questions/4577792/how-to-clear-jtable
http://stackoverflow.com/questions/18456192/get-specific-objects-from-arraylist-when-objects-were-added-anonymously
http://stackoverflow.com/questions/10162950/java-updating-jtable-with-firetabledatachanged
http://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html
http://stackoverflow.com/questions/33926682/cant-get-jtable-to-refresh-repaint-a-defaulttablemodel-in-same-instance-of-gui
http://www.coderanch.com/t/570243/GUI/java/JTable-Enable-filtering-disable-sorting
http://stackoverflow.com/questions/10921351/get-object-from-selected-jtable
http://stackoverflow.com/questions/14416188/jtable-how-to-get-selected-cells
http://stackoverflow.com/questions/12301923/jtable-getselectedrow-does-not-return-the-selected-row-index
http://stackoverflow.com/questions/4266931/how-to-get-data-from-a-jtable-using-its-rowindex
http://stackoverflow.com/questions/14231688/how-to-remove-element-from-arraylist-by-checking-its-value
http://stackoverflow.com/questions/22379395/data-not-showing-in-jtable-using-defaulttablemodel-on-adding-as-per-below-code
http://stackoverflow.com/questions/16755003/when-processing-a-csv-string-with-an-empty-final-field-mystring-split-re
http://stackoverflow.com/questions/17450644/jtable-row-filtering-based-on-values-of-two-different-columns
http://www.jtable.org/demo/filtering
http://stackoverflow.com/questions/26031383/how-to-filter-jtable-with-respect-to-a-specific-column
http://www.java2s.com/Tutorial/Java/0240__Swing/JTableFiltering.htm
http://stackoverflow.com/questions/26031383/how-to-filter-jtable-with-respect-to-a-specific-column
http://stackoverflow.com/questions/5565632/filter-jtable-only-one-one-column
http://stackoverflow.com/questions/9941628/swing-jtable-customization-for-filtering-searching
http://www.java2s.com/Code/Java/JDK-6/FiltertablebythetextinaTextField.htm
https://www.youtube.com/watch?v=Hm14C30b1hg
https://www.youtube.com/watch?v=Uq4v-bIDAIk
http://stackoverflow.com/questions/19364436/jtable-row-filtering-by-jtextfield-value
http://stackoverflow.com/questions/31379332/dynamic-search-filter-in-jtable
http://stackoverflow.com/questions/22066387/how-to-search-an-element-in-a-jtable-java
http://stackoverflow.com/questions/12123456/how-to-implement-search-in-a-jtable
http://stackoverflow.com/questions/31158089/how-to-search-data-in-jtable-using-jtextfield
http://stackoverflow.com/questions/3245135/search-in-jtable
http://stackoverflow.com/questions/5565632/filter-jtable-only-one-one-column
http://www.coderanch.com/t/331433/GUI/java/JTable-Searching
http://www.coderanch.com/t/332026/GUI/java/Filtering-searching-facilities-JTable
https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
http://docs.oracle.com/javase/6/docs/api/javax/swing/RowFilter.html
http://docs.oracle.com/javase/8/docs/api/javax/swing/table/TableRowSorter.html
http://docs.oracle.com/javase/8/docs/api/javax/swing/RowFilter.html
http://www.java-tips.org/java-se-tips-100019/15-javax-swing/1010-sorting-and-filtering-tables.html
http://stackoverflow.com/questions/22864095/reading-data-from-a-specific-csv-file-and-displaying-it-in-a-jtable
http://www.java-tips.org/java-se-tips-100019/15-javax-swing/2392-read-a-data-file-into-a-jtable.html
http://www.ictforu.com/index.php/programming/java/93-how-to-read-from-a-csv-file-sort-the-contents-and-populate-a-jtable
http://stackoverflow.com/questions/23106416/how-to-filter-rows-from-csv-file-and-dispaly-it-on-jtable-using-java
https://www.reddit.com/r/javaexamples/comments/344kch/reading_and_parsing_data_from_a_file/
http://stackoverflow.com/questions/9036346/fill-some-jtable-columns-with-data-from-array
http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#sorting
http://www.dreamincode.net/forums/topic/273289-arraylist-to-jtable/
http://codereview.stackexchange.com/questions/10681/java-function-to-read-a-csv-file
http://www.java2s.com/Questions_And_Answers/Swing/JTable/File-Name.htm
http://sourceforge.net/p/javacsv/discussion/103820/thread/708107e0/
http://stackoverflow.com/questions/16436928/how-to-export-a-jtable-to-a-csv-file
http://stackoverflow.com/questions/3709799/how-to-export-data-from-jtable-to-csv
https://community.oracle.com/thread/1357495
http://www.experts-exchange.com/questions/22419444/How-to-save-the-content-of-a-JTable-to-a-csv-file.html
http://codereview.stackexchange.com/questions/42914/writing-a-jtable-to-a-tab-delimited-file
https://www.youtube.com/watch?v=3_40oiUdLG8
	 * 
	 * </dd>
	 * 
	 * </dl>
	 *  @author dhruv
	 *  @return tablePanel  The JTable panel which my system relies on.
	 *
 */
	private JPanel generateTablePanel(){

		patientcard.setBackground(new Color(153, 204, 255));
		patientcard.setBounds(212, 0, 988, 678);
		
		patientcard.setLayout(null);
		patientTable.setModel(tablemodel);
		patientTable.setFillsViewportHeight(true);
		patientTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		sorter = new TableRowSorter<PatientTableModel>(tablemodel);
		
		int columncount = tablemodel.getColumnCount();
		for(int i=0;i<columncount;i++){
			sorter.setSortable(i, false);

		}			
		
		patientTable.setRowSorter(sorter);
					
		
		filterText = new JTextField("Type to search for a patient across fields");
		filterText.setSize(520, 18);
		filterText.setLocation(220, 28);
		patientcard.add(filterText);

		filterText.setColumns(10);
		filterText.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				filterText.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
			}

		});
		filterText.getDocument().addDocumentListener(new DocumentListener()  {
			RowFilter< PatientTableModel, Object> rf = null;
			String text = filterText.getText();
			public void changedUpdate(DocumentEvent e) {				
				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					try{
						rf = RowFilter.regexFilter("(?i)" + filterText.getText());
						sorter.setRowFilter(rf);
					}catch(java.util.regex.PatternSyntaxException e1){
						return;
					}					
				}		   
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					try{
						rf = RowFilter.regexFilter("(?i)" + filterText.getText()); 
						sorter.setRowFilter(rf);
					}catch(java.util.regex.PatternSyntaxException e1){
						return;
					}			
				}				
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					try{
						rf = RowFilter.regexFilter("(?i)" + filterText.getText()); 
						sorter.setRowFilter(rf);
					}catch(java.util.regex.PatternSyntaxException e1){
						return;
					}			
				}				
			}
		}  
				);  

		patientscrollPane.setLocation(6, 154);
		patientscrollPane.setSize(968, 505);
		patientcard.add(patientscrollPane);

		JButton btnExportPatients = new JButton("Export Selected Patients");
		btnExportPatients.setBounds(795, 123, 179, 19);
		patientcard.add(btnExportPatients);
		btnExportPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPressed = false;
				int[] selection = patientTable.getSelectedRows();
				ArrayList<Patient> exportPatientList = new ArrayList<Patient>();
				for (int i = 0; i < selection.length; i++) {
					selection[i] = patientTable.convertRowIndexToModel(selection[i]);
					exportPatientList.add(filteredPatientList.get(patientTable.convertRowIndexToModel(selection[i])));
				}
				JFileChooser fc = new JFileChooser("./databases");
				int returnVal = fc.showSaveDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File exportfile = new File(fc.getSelectedFile()+".csv");			
					try {
						controller.writeExportFile(exportPatientList, exportfile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(frame, "Congratulations! Your file has been exported", "Confirmed",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});

		JButton btnEditPatient = new JButton("Edit Selected Patient");
		btnEditPatient.setBounds(314, 115, 179, 24);
		patientcard.add(btnEditPatient);	
		btnEditPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						
				if(patientTable.getSelectedRowCount()>1){
					JOptionPane.showMessageDialog(frame, "You can only edit one patient at a time!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
				else{
					editPressed = true;
					int selectedRow = patientTable.getSelectedRow();
					Patient editPatient = filteredPatientList.get(patientTable.convertRowIndexToModel(selectedRow));	//This is definitely the selected patient, I have checked via debug mode			
					setEditText(editPatient);
				}			
			}
		});

		
		JButton deletePatients = new JButton("Delete Selected Patient");
		deletePatients.setBounds(26, 115, 200, 24);
		patientcard.add(deletePatients);	
		deletePatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(patientTable.getSelectedRowCount()>1){
					JOptionPane.showMessageDialog(frame, "You can only delete one patient at a time!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}else {
					int modelRow = patientTable.convertRowIndexToModel(patientTable.getSelectedRow());
					Patient deletepatient = filteredPatientList.get(modelRow);
					int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete "+deletepatient.getpFirstName()+" "+deletepatient.getpLastName()
					, "Confirm delete", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						patientlist.removeAll(filteredPatientList);
						filteredPatientList.remove(deletepatient);
						tablemodel.setList(filteredPatientList);
						String imagepath = "resources/imagedatabase/"+
								String.valueOf(deletepatient.getpatientdUserName().replaceAll("\\s+","")+"/"+deletepatient.getpFirstName().toLowerCase()+"_"+deletepatient.getpLastName().toLowerCase());
						File imagefolder = new File(imagepath);
						deleteImages(imagefolder);					
						try {
							patientlist.addAll(filteredPatientList);
							controller.writePatientFile(patientlist);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		containerPanel.add(patientcard);
		patientcard.setVisible(true);
		return patientcard;
	}

	/**
	 *This method deletes a patients stored image file if the user has chosen to delete the patient.
	 * http://java-demos.blogspot.co.uk/2012/10/how-to-remove-directory-in-java-easily.html
	 * 
	 * @param file  The identified folder within which all subfiles will
	 * be deleted.
	 */
	public void deleteImages(File file)
	{
		if(file.isDirectory())
		{
			File[] files=file.listFiles();
			for(int i=0;i<files.length;i++)
			{
				deleteImages(files[i]);

			}
			file.delete();
		}
		else
		{
			file.delete();
		}
	}
	
	/**
	 * This method sets the fields within the Registry panel by using the getters of the Patient selected from the JTable, and then makes the registrypanel
	 * visible.
	* 
	*@param  editPatient  The patient to be edited
	 */
	private void setEditText(Patient editPatient){
		editPressed = true;
		tffirstName.setText(editPatient.getpFirstName());
		tfLastName.setText(editPatient.getpLastName());
		tfDob.setText(editPatient.getpDob());
		addressPane.setText(editPatient.getpAddress());
		tfEmergencyPhone.setText(editPatient.getpPhoneNumber());
		tfMedicalCondition.setText(editPatient.getpMedicalCondition());
		
		medicalURL = new StringBuilder();
		medicalURL.append("http://en.wikipedia.org/wiki/");
		medicalURL.append(tfMedicalCondition.getText().replaceAll(" ", "_"));
		generatedURL = medicalURL.toString();
		lblMedicalURL.setText("<html> Wikipedia Link : <a href=\"\">"+tfMedicalCondition.getText()+"</a></html>");
		goWebsite(lblMedicalURL, generatedURL, tfMedicalCondition.getText());
		medicalURL.setLength(0);
		medicalURL.append("http://en.wikipedia.org/wiki/");	
		
		billingBox.setSelectedItem(editPatient.getpBillingCycle());
		commentPane.setText(editPatient.getpComments());
		String imagepath = "resources/imagedatabase/"+
				String.valueOf(editPatient.getpatientdUserName().replaceAll("\\s+","")+"/"+editPatient.getpFirstName().toLowerCase()+"_"+editPatient.getpLastName().toLowerCase());
		File profilefile = new File(imagepath+"/profilephoto.png");
		File scan1file = new File(imagepath+"/scan1.png");
		File scan2file = new File(imagepath+"/scan2.png");
		File scan3file = new File(imagepath+"/scan3.png");
		try {
			if(profilefile.exists()){
				BufferedImage profilePhoto = ImageIO.read(profilefile);
				Image dimg = profilePhoto.getScaledInstance(120, 120,Image.SCALE_SMOOTH);//This scales the image
				profileArea.setIcon(new ImageIcon(dimg));
			}

			if(scan1file.exists()){
				BufferedImage scan1Photo = ImageIO.read(scan1file);
				Image dimg = scan1Photo.getScaledInstance(120, 120,Image.SCALE_SMOOTH);//This scales the image
				scan1.setIcon(new ImageIcon(dimg));
			}

			if(scan2file.exists()){
				BufferedImage scan2Photo = ImageIO.read(scan2file);
				Image dimg = scan2Photo.getScaledInstance(120, 120,Image.SCALE_SMOOTH);//This scales the image
				scan2.setIcon(new ImageIcon(dimg));
			}

			if(scan3file.exists()){
				BufferedImage scan3Photo = ImageIO.read(scan3file);
				Image dimg = scan3Photo.getScaledInstance(120, 120,Image.SCALE_SMOOTH);//This scales the image
				scan3.setIcon(new ImageIcon(dimg));
			}	

		} catch (IOException e) {

		}

		registrycard.setVisible(true);
		patientcard.setVisible(false);
	}
	
	/**
	 * 
	 * <dl>
		 * <dt> Purpose:
		 * <dd> To generate the master functionality of the system e.g. register a new patient, view patients, logout and import a CSV.
		 *
		 * <dt> Description:
		 * <dd> I can import a CSV of patients and add it to the running arraylist in the system (this was an implementation of nested for loops). In
		 * addition, I can register a new patient (clearing the old fields in the process), switch to the JTable panel, and also logout, removing a "session:
		 * by setting the loggedinDoctor variable to "null".
		 * 
		 * <dt> References:
		 * <dd>http://stackoverflow.com/questions/34043385/nested-for-loop-to-check-arraylist-against-another-arraylist/34043989#34043989
		 * https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
		 * 
		 * </dd>
		 *  
		 * </dl>
		 * @return tabsPanel  A panel which allows switching between cards in my system.
		 *
	 */
	private JPanel generateTabsPanel(){
		tabsPanel = new JPanel(new GridLayout(20,0));
		tabsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabsPanel.setBounds(0, 0, 213, 678);
		containerPanel.add(tabsPanel);
		tabsPanel.setBackground(new Color(153, 204, 204));

		lblDoctorName = new JLabel("");
		tabsPanel.add(lblDoctorName);

		
		JButton registerButton = new JButton("Register New Patient");
		tabsPanel.add(registerButton);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPressed = false;
				patientcard.setVisible(false);
				registrycard.setVisible(true);
				clearFields();
			}
		});		


		JButton viewButton = new JButton("View Patients"); 
		tabsPanel.add(viewButton);
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPressed = false;
				patientcard.setVisible(true);
				registrycard.setVisible(false);

			}	
		});


		JButton importButton = new JButton("Import Patient CSV"); 
		tabsPanel.add(importButton);
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("./databases");
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File importfile = fc.getSelectedFile();	
					ArrayList<Patient> importPatientList = controller.extractPatientList(importfile);
					ArrayList<Patient> checkedPatientList = new ArrayList<Patient>();
					boolean isunique=true;
					for(Patient imp: importPatientList){																			
						for(Patient p: filteredPatientList){
							if (imp.getpID()==p.getpID()){
								isunique=false;
								break;
							}
							else{
								isunique=true;
							}
						}
						if(isunique){
							checkedPatientList.add(imp);
						}
					}
					patientlist.removeAll(filteredPatientList);
					filteredPatientList.addAll(checkedPatientList);
					tablemodel.setList(filteredPatientList);
					patientlist.addAll(filteredPatientList);
					try {
						controller.writePatientFile(patientlist);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}	
				}

			}	
		});

		JButton logoutButton = new JButton("Logout"); 
		tabsPanel.add(logoutButton);
		tabsPanel.setVisible(true);
		logoutButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent event) {
				editPressed = false;
				int reply = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", null, JOptionPane.YES_NO_OPTION);
				switch(reply){
				case JOptionPane.YES_OPTION:
					loggedInDoctor = null;
					containerPanel.setVisible(false);
					loginPanel.setVisible(true);
					loginUsernameField.setText("");
					loginPasswordField.setText("");
					Component[] registernents = registerDoctorPanel.getComponents();
					for (Component c : registernents) {
						if (c instanceof JTextField) {
							JTextComponent specificObject = (JTextComponent) c;
							specificObject.setText("");				

					}
					}
					Component[] components = registrycard.getComponents();
					for (Component component : components) {
						if(component.equals(tfDob)){
							tfDob.setText("");
						}
						if (component instanceof JTextField) {
							JTextComponent specificObject = (JTextComponent) component;
							specificObject.setText("");				
						}else if(component.equals(scan1)||component.equals(scan2) ||component.equals(scan3) || component.equals(profileArea)){
							JLabel specificObject = (JLabel) component;
							specificObject.setIcon(null);
						}

					}
					break;
				default:
					containerPanel.setVisible(true);
					loginPanel.setVisible(false);
					break;
				}
			} 
		});

		return tabsPanel;
	}

	/**
	 * <dl>
		 * <dt> Purpose:
		 * <dd> Generating the doctor object from what has been entered in the registry panel
		 *
		 * <dt> Description:
		 * <dd>	Again, I ensure that a hashcode is only generated after the fields required for the hashcode are set.
		 * For future development I would consider improving this to place JPasswordField charsets into a database, and decrypt them when called from the
		 * database or inserted by a user.
		 * 
		 * </dl>
		 *  @author dhruv
		 *  @return adddoctor  The doctor to be verified against upon
		 *  registry
		 *
	 */
	@SuppressWarnings("deprecation")
	private Doctor setRegistryFields(){
		Doctor adddoctor = new Doctor();

		adddoctor.setDpassWord(newPasswordField.getText());
		adddoctor.setDuserName(tfdRegisterUserName.getText());
		adddoctor.setFirstName(tfdRegisterFirstName.getText());
		adddoctor.setLastName(tfdRegisterLastName.getText());
		adddoctor.setdID(adddoctor.hashCode());

		return adddoctor;

	}


	/**
	 * 
	 * <dl>
		 * <dt> Purpose:
		 * <dd> This method is called to change the label of the medical condition to be a clickable URL 
		 * accessible from your current default browser window based on the string entered in the medical condition text field.
		 *
		 * <dt> Description:
		 * <dd> Utilises the Java Desktop library within the Java SDK (http://docs.oracle.com/javase/7/docs/api/java/awt/Desktop.html).
		 * 
		 * <dt> References:
		 * <dd>http://stackoverflow.com/questions/8669350/jlabel-hyperlink-to-open-browser-at-correct-url
		 * 
		 * </dd>
		 * </dl>
		 * 
		 * @param website  The JLabel containing the hyperlink generated.
		 * @param url  The String to be used to generate the wikipedia URL
		 * @param text  The text to be inserted into the JLabel
		 *
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
}

