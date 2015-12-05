package hospitalregistry;

import java.awt.EventQueue;
import javax.swing.*;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.imageio.ImageIO;//Allows you to read in images from a file store or a web URL

public class Gui {

	public ArrayList<Patient> PatientList = new ArrayList<Patient>();
	public ArrayList<Doctor> DoctorList = new ArrayList<Doctor>();
	
	private JFrame frmDoctorLogin = new JFrame();
	private JPanel loginPanel = new JPanel();
	private JPanel basicInfoPane = new JPanel();
	private JPanel medicalInfoPane = new JPanel();
	private JPanel viewPatients = new JPanel();
	private JPanel uploadProfilePhoto = new JPanel();
	private JPanel viewScans = new JPanel();
	private JPanel uploadImages = new JPanel();
	private JPanel Home = new JPanel();
	
	private JTextField tfuserField;
	private JPasswordField tfpasswordField;
	
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfDob;
	private JTextField tfAddress;
	private JTextField tfPhoneNumber;
	private JTextField tfBillingCycle;
	private JTextField tfMedicalCondition;
	private JTextField tfLastPrescription;
	private JTextField tfLastAptDate;
	private JTextField tfComments;
	
	private JTextField tfUpload1;
	private JTextField tfUpload2;
	private JTextField tfUpload3;
	private JTextField tfUpload4;
	private JTextField tfProfilePhotoLocation;
	
//	private int pID;
//	private int dID;
//	
//	//Question - can I define the same variables as in the parent objects I create?
	//Patient.set
//	
//	public String dUsername;
//	public String dPassword;
//	public String pFirstName;
//	public String pLastName;
//	public String pDob;
//	public String pAddress;
//	public String pPhoneNumber;
//	public String pBillingCycle;
//	public String pMedicalCondition;
//	public String pLastPrescription;
//	public String pLastAptDate;
//	public String pComments;
	
	private JTable patientTable = new JTable();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmDoctorLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application. This is the constructor for the method.
	 * @throws IOException 
	 */
	public Gui() throws IOException {
		try{
			TextWriter tw = new TextWriter();
			initializeframe();
			//PatientList = TextWriter.readPatientFile()
		}
		finally{
			
		}
		
		//this. - should I put this in my constructor for the text fields?
		
		//addactionlisteners
	}

	private void initializeframe() {
			
		frmDoctorLogin.setTitle("Doctor Login");
		frmDoctorLogin.setBounds(100, 100, 452, 299);
		frmDoctorLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDoctorLogin.getContentPane().setLayout(null);
		
		initialiseLogin();		
	}
	
	//JTable.display
	
	private void initialiseLogin(){
		
		basicInfoPane.setVisible(false);
		medicalInfoPane.setVisible(false);
		viewPatients.setVisible(false);
		uploadProfilePhoto.setVisible(false);
		viewScans.setVisible(false);
		uploadImages.setVisible(false);
		Home.setVisible(false);
		loginPanel.setVisible(true);
		
		
		loginPanel.setBounds(0, 0, 450, 278);
		frmDoctorLogin.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(156, 62, 62, 16);
		loginPanel.add(lblUsername);

		tfuserField = new JTextField();
		tfuserField.setBounds(156, 79, 130, 26);
		loginPanel.add(tfuserField);
		tfuserField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(162, 117, 62, 16);
		loginPanel.add(lblPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(156, 177, 130, 29);
		loginPanel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateDoctor();
				initialiseHome();
			}
		});

		tfpasswordField = new JPasswordField();
		tfpasswordField.setBounds(156, 139, 131, 26);
		loginPanel.add(tfpasswordField);
		frmDoctorLogin.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{loginPanel, lblUsername, btnLogin, tfuserField, lblPassword, tfpasswordField}));

	}
	
	private void validateDoctor(){
		
	}
	
	private void initialiseUploadImages(){
		
		basicInfoPane.setVisible(false);
		medicalInfoPane.setVisible(false);
		viewPatients.setVisible(false);
		uploadProfilePhoto.setVisible(false);
		viewScans.setVisible(false);
		uploadImages.setVisible(true);
		Home.setVisible(false);
		loginPanel.setVisible(false);
		
		uploadImages.setBounds(0, 0, 450, 277);
		frmDoctorLogin.getContentPane().add(uploadImages);
		uploadImages.setLayout(null);

		JButton btnAttachImage = new JButton("Attach Image 1");
		btnAttachImage.setBounds(256, 40, 179, 29);
		uploadImages.add(btnAttachImage);

		tfUpload1 = new JTextField();
		tfUpload1.setBounds(16, 40, 228, 26);
		uploadImages.add(tfUpload1);
		tfUpload1.setColumns(10);

		JLabel lblAttachPatientImages = new JLabel("Attach Patient Scans (Max 4)");
		lblAttachPatientImages.setBounds(16, 12, 212, 16);
		uploadImages.add(lblAttachPatientImages);

		JButton btnAttachImage_1 = new JButton("Attach Image 2");
		btnAttachImage_1.setBounds(256, 81, 179, 29);
		uploadImages.add(btnAttachImage_1);

		tfUpload2 = new JTextField();
		tfUpload2.setBounds(16, 81, 228, 26);
		tfUpload2.setColumns(10);
		uploadImages.add(tfUpload2);

		JButton btnAttachImage_2 = new JButton("Attach Image 3");
		btnAttachImage_2.setBounds(256, 122, 179, 29);
		uploadImages.add(btnAttachImage_2);

		tfUpload3 = new JTextField();
		tfUpload3.setBounds(16, 122, 228, 26);
		tfUpload3.setColumns(10);
		uploadImages.add(tfUpload3);

		JButton btnAttachImage_3 = new JButton("Attach Image 4");
		btnAttachImage_3.setBounds(256, 161, 179, 29);
		uploadImages.add(btnAttachImage_3);
		//TODO Attaching images and populating text fields

		tfUpload4 = new JTextField();
		tfUpload4.setBounds(16, 161, 228, 26);
		tfUpload4.setColumns(10);
		uploadImages.add(tfUpload4);

		JButton btnUploadScan = new JButton("Upload Images");
		btnUploadScan.setBounds(256, 201, 179, 29);
		uploadImages.add(btnUploadScan);

		JButton btnBacktoMedicalInfo = new JButton("Back");
		btnBacktoMedicalInfo.setBounds(6, 242, 117, 29);
		uploadImages.add(btnBacktoMedicalInfo);
		btnBacktoMedicalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseMedicalInfoPane();
			}
		});


		JButton btnNext = new JButton("Next");
		btnNext.setBounds(135, 242, 117, 29);
		uploadImages.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseUploadProfilePanel();
			}
		});

		JButton btnLogoutfrmUploadScans = new JButton("Logout");
		btnLogoutfrmUploadScans.setBounds(256, 242, 179, 29);
		uploadImages.add(btnLogoutfrmUploadScans);
		btnLogoutfrmUploadScans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseLogin();
			}
		});

		JButton btnViewExistingImages = new JButton("View Existing Images");
		btnViewExistingImages.setBounds(16, 201, 172, 29);
		uploadImages.add(btnViewExistingImages);
		btnViewExistingImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseViewScans();
			}
		});

		JButton btnHomefrmUploadImages = new JButton("Home");
		btnHomefrmUploadImages.setBounds(256, 7, 179, 29);
		uploadImages.add(btnHomefrmUploadImages);
		btnHomefrmUploadImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseHome();
			}
		});

	}
	
	private void initialiseHome(){
		
		basicInfoPane.setVisible(false);
		medicalInfoPane.setVisible(false);
		viewPatients.setVisible(false);
		uploadProfilePhoto.setVisible(false);
		viewScans.setVisible(false);
		uploadImages.setVisible(false);
		Home.setVisible(true);
		loginPanel.setVisible(false);
		
		Home.setBounds(0, 0, 450, 277);
		frmDoctorLogin.getContentPane().add(Home);
		Home.setLayout(null);
		
		JButton btnAddNewPatient = new JButton("Add New Patient");
		btnAddNewPatient.setBounds(149, 84, 176, 29);
		Home.add(btnAddNewPatient);
		btnAddNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseBasicInfoPanel();

			}
		});

		
		JButton btnViewPatients = new JButton("View Patients");
		btnViewPatients.setBounds(149, 43, 176, 29);
		Home.add(btnViewPatients);
		btnViewPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseViewPatients();
			}
		});
		
		JButton btnHomeLogout = new JButton("Logout");
		btnHomeLogout.setBounds(149, 149, 176, 29);
		Home.add(btnHomeLogout);
		btnHomeLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseLogin();
			}
		});
	}
	
	private void initialiseMedicalInfoPane(){
		
		basicInfoPane.setVisible(false);
		medicalInfoPane.setVisible(true);
		viewPatients.setVisible(false);
		uploadProfilePhoto.setVisible(false);
		viewScans.setVisible(false);
		uploadImages.setVisible(false);
		Home.setVisible(false);
		loginPanel.setVisible(false);
		
		medicalInfoPane.setBounds(0, 0, 450, 278);
		frmDoctorLogin.getContentPane().add(medicalInfoPane);
		medicalInfoPane.setLayout(null);

		JLabel lblMedicalCondition = new JLabel("Medical Condition");
		lblMedicalCondition.setBounds(30, 37, 114, 16);
		medicalInfoPane.add(lblMedicalCondition);

		JLabel lblLastPrescription = new JLabel("Last Prescription");
		lblLastPrescription.setBounds(30, 65, 105, 16);
		medicalInfoPane.add(lblLastPrescription);

		JLabel lblLastAppointmentDate = new JLabel("Last Appointment Date");
		lblLastAppointmentDate.setBounds(28, 93, 145, 16);
		medicalInfoPane.add(lblLastAppointmentDate);

		JLabel lblComments = new JLabel("Comments");
		lblComments.setBounds(30, 121, 68, 16);
		medicalInfoPane.add(lblComments);

		tfMedicalCondition = new JTextField();
		tfMedicalCondition.setBounds(201, 32, 217, 26);
		medicalInfoPane.add(tfMedicalCondition);
		tfMedicalCondition.setColumns(10);

		tfLastPrescription = new JTextField();
		tfLastPrescription.setBounds(201, 60, 217, 26);
		tfLastPrescription.setColumns(10);
		medicalInfoPane.add(tfLastPrescription);

		tfLastAptDate = new JTextField();
		tfLastAptDate.setBounds(201, 88, 217, 26);
		tfLastAptDate.setColumns(10);
		medicalInfoPane.add(tfLastAptDate);

		tfComments = new JTextField();
		tfComments.setBounds(201, 116, 217, 108);
		tfComments.setColumns(10);
		medicalInfoPane.add(tfComments);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(106, 243, 75, 29);
		medicalInfoPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseBasicInfoPanel();
			}
		});
		
		
		JButton btToUploadImages = new JButton("Next Page");
		btToUploadImages.setBounds(271, 243, 117, 29);
		medicalInfoPane.add(btToUploadImages);
		btToUploadImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseUploadImages();
			}
		});
		
//		JButton btnUpdateRecord = new JButton("Update Patient Record");
//		btnUpdateRecord.setBounds(211, 243, 207, 29);
//		medicalInfoPane.add(btnUpdateRecord);
//		btnUpdateRecord.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//Create an object
//			}
//		});
		
		JButton btnLogoutfrmMedicalInfoPane = new JButton("Logout");
		btnLogoutfrmMedicalInfoPane.setBounds(6, 243, 88, 29);
		medicalInfoPane.add(btnLogoutfrmMedicalInfoPane);
		btnLogoutfrmMedicalInfoPane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicalInfoPane.setVisible(false);
				basicInfoPane.setVisible(false);
				loginPanel.setVisible(false);
				int reply = JOptionPane.showInternalConfirmDialog(basicInfoPane,
						"Are you sure you want to logout?", "information",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				switch(reply){
				case JOptionPane.YES_OPTION:
					medicalInfoPane.setVisible(false);
					basicInfoPane.setVisible(false);
					loginPanel.setVisible(true);
					break;
				default:
					basicInfoPane.setVisible(false);
					medicalInfoPane.setVisible(true);
					loginPanel.setVisible(false);
					break;
				}

			}
		});


	}
	
	private void initialiseBasicInfoPanel(){
		
		basicInfoPane.setVisible(true);
		medicalInfoPane.setVisible(false);
		viewPatients.setVisible(false);
		uploadProfilePhoto.setVisible(false);
		viewScans.setVisible(false);
		uploadImages.setVisible(false);
		Home.setVisible(false);
		loginPanel.setVisible(false);

		basicInfoPane.setBounds(0, 0, 450, 278);
		frmDoctorLogin.getContentPane().add(basicInfoPane);
		basicInfoPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(61, 23, 106, 16);
		basicInfoPane.add(lblNewLabel);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(61, 51, 106, 16);
		basicInfoPane.add(lblLastName);

		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(61, 79, 106, 16);
		basicInfoPane.add(lblDateOfBirth);

		JLabel lblAddress = new JLabel("Address\n");
		lblAddress.setBounds(61, 107, 106, 16);
		basicInfoPane.add(lblAddress);

		JLabel lblEmergencyPhoneNumber = new JLabel("Emergency Phone Number");
		lblEmergencyPhoneNumber.setBounds(61, 187, 177, 16);
		basicInfoPane.add(lblEmergencyPhoneNumber);

		JLabel lblBillingCycle = new JLabel("Billing Cycle");
		lblBillingCycle.setBounds(61, 215, 106, 16);
		basicInfoPane.add(lblBillingCycle);

		tfFirstName = new JTextField();
		tfFirstName.setBounds(271, 18, 130, 26);
		basicInfoPane.add(tfFirstName);
		tfFirstName.setColumns(10);

		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(271, 46, 130, 26);
		basicInfoPane.add(tfLastName);

		tfDob = new JTextField();
		tfDob.setColumns(10);
		tfDob.setBounds(271, 74, 130, 26);
		basicInfoPane.add(tfDob);

		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(271, 107, 130, 72);
		basicInfoPane.add(tfAddress);

		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(271, 182, 130, 26);
		basicInfoPane.add(tfPhoneNumber);


		tfBillingCycle = new JTextField();
		tfBillingCycle.setColumns(10);
		tfBillingCycle.setBounds(271, 210, 130, 26);
		basicInfoPane.add(tfBillingCycle);
		
		JButton btnLogoutfrmBasicInfo = new JButton("Logout");
		btnLogoutfrmBasicInfo.setBounds(6, 243, 117, 29);
		basicInfoPane.add(btnLogoutfrmBasicInfo);
		btnLogoutfrmBasicInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseLogin();
			}
		});
		
		//How do I make only this panel visible, others hidden.
		
		JButton btToMedicalInfoPane = new JButton("Next Page");
		btToMedicalInfoPane.setBounds(271, 243, 117, 29);
		basicInfoPane.add(btToMedicalInfoPane);
		btToMedicalInfoPane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseMedicalInfoPane();
			}
		});
		
	}
	
	private void initialiseUploadProfilePanel(){
		
		basicInfoPane.setVisible(false);
		medicalInfoPane.setVisible(false);
		viewPatients.setVisible(false);
		uploadProfilePhoto.setVisible(true);
		viewScans.setVisible(false);
		uploadImages.setVisible(false);
		Home.setVisible(false);
		loginPanel.setVisible(false);
		
		uploadProfilePhoto.setBounds(0, 0, 450, 277);
		frmDoctorLogin.getContentPane().add(uploadProfilePhoto);
		uploadProfilePhoto.setLayout(null);
		
		tfProfilePhotoLocation = new JTextField();
		tfProfilePhotoLocation.setBounds(6, 6, 263, 26);
		uploadProfilePhoto.add(tfProfilePhotoLocation);
		tfProfilePhotoLocation.setColumns(10);
		
		JButton btnUploadProfile = new JButton("Upload Profile Photo");
		btnUploadProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File pp = chooser.getSelectedFile();
				String profilepath = pp.getAbsolutePath();
				tfProfilePhotoLocation.setText(profilepath);

			}
		});
		btnUploadProfile.setBounds(281, 6, 155, 29);
		uploadProfilePhoto.add(btnUploadProfile);
		
		JButton btnConfirmPhoto = new JButton("Confirm Photo");
		btnConfirmPhoto.setBounds(281, 40, 155, 29);
		uploadProfilePhoto.add(btnConfirmPhoto);

		JButton btnBackfromProfile = new JButton("Back");
		btnBackfromProfile.setBounds(6, 242, 117, 29);
		uploadProfilePhoto.add(btnBackfromProfile);
		btnBackfromProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseUploadImages();
			}
		});

		JButton btnUpdatePatientRecordFinal = new JButton("Update Patient Record");
		btnUpdatePatientRecordFinal.setBounds(141, 242, 170, 29);
		uploadProfilePhoto.add(btnUpdatePatientRecordFinal);
		btnUpdatePatientRecordFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basicInfoPane.setVisible(false);
				medicalInfoPane.setVisible(false);
				viewPatients.setVisible(false);
				uploadProfilePhoto.setVisible(false);
				viewScans.setVisible(false);
				uploadImages.setVisible(false);
				Home.setVisible(false);
				loginPanel.setVisible(false);
				int reply = JOptionPane.showInternalConfirmDialog(uploadProfilePhoto,
						"Are you sure you want to update this patient?", "Add New Patient",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				switch(reply){
				case JOptionPane.YES_OPTION:
					generatePatientID();
					try {
						updateRecord();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				default:
					initialiseUploadProfilePanel();
					break;
				}
				
			}
			
		});

		JButton btnLogoutfromProfilePhoto = new JButton("Logout");
		btnLogoutfromProfilePhoto.setBounds(319, 242, 117, 29);
		uploadProfilePhoto.add(btnLogoutfromProfilePhoto);
		btnLogoutfromProfilePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basicInfoPane.setVisible(false);
				medicalInfoPane.setVisible(false);
				viewPatients.setVisible(false);
				uploadProfilePhoto.setVisible(false);
				viewScans.setVisible(false);
				uploadImages.setVisible(false);
				Home.setVisible(false);
				loginPanel.setVisible(false);
				int reply = JOptionPane.showInternalConfirmDialog(uploadProfilePhoto,
						"Are you sure you want to logout?", "information",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				switch(reply){
				case JOptionPane.YES_OPTION:
					initialiseLogin();
					break;
				default:
					initialiseUploadProfilePanel();
					break;
				}
				
			}
			
		});
	}
	
	private void generatePatientID() {
		
		
	}

	private void initialiseViewPatients(){
		
		basicInfoPane.setVisible(false);
		medicalInfoPane.setVisible(false);
		viewPatients.setVisible(true);
		uploadProfilePhoto.setVisible(false);
		viewScans.setVisible(false);
		uploadImages.setVisible(false);
		Home.setVisible(false);
		loginPanel.setVisible(false);
		
		viewPatients.setBounds(0, 0, 450, 277);
		frmDoctorLogin.getContentPane().add(viewPatients);
		viewPatients.setLayout(null);
		
		patientTable.setBounds(45, 247, 371, -169);
		viewPatients.add(patientTable);
		
		JButton btnHomefromViewPatients = new JButton("Home");
		btnHomefromViewPatients.setBounds(198, 6, 117, 29);
		viewPatients.add(btnHomefromViewPatients);
		btnHomefromViewPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseHome();
			}
		});
		
		JButton btnLogoutfrmViewPatients = new JButton("Logout");
		btnLogoutfrmViewPatients.setBounds(327, 6, 117, 29);
		viewPatients.add(btnLogoutfrmViewPatients);
		btnLogoutfrmViewPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseLogin();
			}
		});
		
	}
	
	private void initialiseViewScans(){
		
		basicInfoPane.setVisible(false);
		medicalInfoPane.setVisible(false);
		viewPatients.setVisible(false);
		uploadProfilePhoto.setVisible(false);
		viewScans.setVisible(true);
		uploadImages.setVisible(false);
		Home.setVisible(false);
		loginPanel.setVisible(false);
		
		
		viewScans.setBounds(0, 0, 450, 277);
		frmDoctorLogin.getContentPane().add(viewScans);
		viewScans.setLayout(null);
		
		JButton btnNextScan = new JButton(">");
		btnNextScan.setBounds(378, 205, 62, 29);
		viewScans.add(btnNextScan);

		JButton btnPreviousScan = new JButton("<");
		btnPreviousScan.setBounds(36, 205, 52, 29);
		viewScans.add(btnPreviousScan);

		JButton btnLogoutfrmViewScans = new JButton("Logout");
		btnLogoutfrmViewScans.setBounds(294, 242, 146, 29);
		viewScans.add(btnLogoutfrmViewScans);
		btnLogoutfrmViewScans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicalInfoPane.setVisible(false);
				basicInfoPane.setVisible(false);
				loginPanel.setVisible(false);
				int reply = JOptionPane.showInternalConfirmDialog(basicInfoPane,
						"Are you sure you want to logout?", "information",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				switch(reply){
				case JOptionPane.YES_OPTION:
					medicalInfoPane.setVisible(false);
					basicInfoPane.setVisible(false);
					loginPanel.setVisible(true);
					break;
				default:
					basicInfoPane.setVisible(false);
					medicalInfoPane.setVisible(true);
					loginPanel.setVisible(false);
					break;
				}
				
			}
			
		});

		JButton btnBackfrmViewScans = new JButton("Back");
		btnBackfrmViewScans.setBounds(6, 242, 116, 29);
		viewScans.add(btnBackfrmViewScans);

		JButton btnNextfrmViewScans = new JButton("Next");
		btnNextfrmViewScans.setBounds(154, 242, 106, 29);
		viewScans.add(btnNextfrmViewScans);

		JButton btnHomefrmViewScans = new JButton("Home");
		btnHomefrmViewScans.setBounds(294, 6, 146, 29);
		viewScans.add(btnHomefrmViewScans);
		btnHomefrmViewScans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiseHome();
			}
		});

	}
	//Create pateitn
//	set again
//	try write patient to csv
//	read it again and store in patient arraylist
//	reset patientlist.
//	PatientCount++
//	Update the file
	
		//TODO Generate new array object
	private void updateRecord() throws IOException{
		
		//pID = TODO generation of patient and doctor IDs.
		//TODO - add images to the array of patient and doctor IDs
		
		//Question - should the constructor be blank, or contain many variables to construct it?
		Patient patient = new Patient();
		Doctor doctor = new Doctor();
		
		patient.setpFirstName(tfFirstName.getText());
		patient.setpAddress(tfAddress.getText());
		patient.setpDob(tfDob.getText());
		patient.setpMedicalCondition(tfMedicalCondition.getText());
		patient.setpLastAptDate(tfLastAptDate.getText());
		patient.setpLastPrescription(tfLastPrescription.getText());
		patient.setpLastName(tfLastName.getText());
		patient.setpComments(tfComments.getText());
		patient.setpPhoneNumber(tfPhoneNumber.getText());
		patient.setpBillingCycle(tfBillingCycle.getText());
		
		doctor.setDpasswordField(tfpasswordField.toString());//TODO - how to get password text when 
		doctor.setDuserField(tfuserField.getText());
		
//		TextWriter.addDoctor(doctor);
//		TextWriter.addPatient(patient);
//		Add doctor to doctor to doctorlist
//		TextWriter - write whole arraylist
			
//		TextWriter.writePatientFile(PatientList);
		initialiseLogin();
		
	}
}
	
		




