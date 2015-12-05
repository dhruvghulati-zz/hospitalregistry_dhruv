package hospitalsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ControllerTest {
	
	/*
	 * These are the column numbers for the patient file.
	 */
    private int PDID = 0;
    private int PDUSERNAME = 1;
    private int PID = 2;
    private int PFIRSTNAME = 3;
    private int PLASTNAME = 4;
    private int PDOB = 5;
    private int PADDRESS = 6;
    private int PPHONENUMBER = 7;
    private int PBILLING = 8;
    private int PMEDICAL = 9;
    private int PCOMMENTS = 10;
    
    /*
     * These are the column numbers for the doctor file.
     */
    private static final int DID = 0;
    private static final int DUSERNAME = 1;
    private static final int DPASSWORD = 2;
    private static final int DFIRSTNAME = 3;
    private static final int DLASTNAME = 4;
    
    
    
    private static final String COMMA_DELIMITER = ",";
    
    /*
     * This shows the directory of the database files the controller is handling.
     */
    private String patientfilename = "/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/databases/masterpatients.csv";//Changed this from private static
    private String doctorfilename = "/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/databases/masterdoctors.csv";
    private File patientfile = new File(patientfilename);
    private File doctorfile = new File(doctorfilename);
    private DefaultTableModel model;
    
    
    private Patient selectedPatient;
	
    /*
     * http://examples.javacodegeeks.com/core-java/writeread-csv-files-in-java-example/
     */
    public void deletePatients(int SelectedRow, ArrayList<Patient> patientlist){
    	selectedPatient = patientlist.get(SelectedRow);//This is the issue here
		System.out.println(selectedPatient.toString());
    	patientlist.remove(selectedPatient);
    }
    
    
    public void writeNewPatientFile() throws FileNotFoundException {
    	if (!patientfile.exists()) {
    		try {
    			patientfile.createNewFile();
    			System.out.println("New patient file has been generated");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
    
    public void writeNewDoctorFile() throws FileNotFoundException {
		if (!doctorfile.exists()) {
			try {
				doctorfile.createNewFile();
				System.out.println("New doctor file has been generated");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
       
    public ArrayList<Doctor> extractDoctorList(){
    	BufferedReader fileReader = null;
    	ArrayList<Doctor> doctorlist = new  ArrayList<Doctor>();
    	try {
    		String line = "";
    		fileReader = new BufferedReader(new FileReader(doctorfilename));
    		fileReader.readLine();
    		while ((line = fileReader.readLine()) != null) {
    			String[] tokens = line.split(COMMA_DELIMITER,5);
    			if (tokens.length > 0) {
    				Doctor doctor = new Doctor();
    				doctor.setFirstName(tokens[DFIRSTNAME]);
    				doctor.setLastName(tokens[DLASTNAME]);
    				doctor.setdID(Integer.parseInt(tokens[DID]));
    				doctor.setDuserName(tokens[DUSERNAME]);
    				doctor.setDpassWord(tokens[DPASSWORD]);
    				doctorlist.add(doctor);
    			}
    		}
    	}
    	catch (Exception e) {
    		System.out.println("Error in CSVFileReader!");
    		e.printStackTrace();
    	} 
    	finally {try {
    			fileReader.close();
    		} catch (IOException e) {
    			System.out.println("Error while closing fileReader!");
    			e.printStackTrace();
    		}
    	}
    	return doctorlist;
    }
   
    public ArrayList<Patient> extractPatientList(File importfile){
    	BufferedReader fileReader = null;
    	ArrayList<Patient> patientlist = new  ArrayList<Patient>();
    	try {
    		String line = "";
    		fileReader = new BufferedReader(new FileReader(importfile.getAbsolutePath()));
    		fileReader.readLine();
    		while ((line = fileReader.readLine()) != null) {
    			String[] tokens = line.split(COMMA_DELIMITER,11);
    			if (tokens.length > 0) {
    				Patient patient = new Patient();
    				patient.setpatientdID(Integer.parseInt(tokens[PDID]));
    				patient.setpatientdUserName(tokens[PDUSERNAME]);
    				patient.setpID(Integer.parseInt(tokens[PID]));
    				patient.setpFirstName(tokens[PFIRSTNAME]);
    				patient.setpLastName(tokens[PLASTNAME]);
    				patient.setpDob(tokens[PDOB]);
    				patient.setpAddress(tokens[PADDRESS]);
    				patient.setpPhoneNumber(tokens[PPHONENUMBER]);
    				patient.setpBillingCycle(tokens[PBILLING]);
    				patient.setpMedicalCondition(tokens[PMEDICAL]);
    				patient.setpComments(tokens[PCOMMENTS]);
    				patientlist.add(patient);
    			}
    		}
    	}
    	catch (Exception e) {
    		System.out.println("Error in CSVFileReader!");
    		e.printStackTrace();
    	} 
    	finally {try {
    			fileReader.close();
    		} catch (IOException e) {
    			System.out.println("Error while closing fileReader!");
    			e.printStackTrace();
    		}
    	}
    	return patientlist;
    }
    
    
    public ArrayList<Patient> extractPatientList(){
    	BufferedReader fileReader = null;
    	ArrayList<Patient> patientlist = new  ArrayList<Patient>();
    	try {
    		String line = "";
    		fileReader = new BufferedReader(new FileReader(patientfilename));
    		fileReader.readLine();
    		while ((line = fileReader.readLine()) != null) {
    			String[] tokens = line.split(COMMA_DELIMITER,11);
    			if (tokens.length > 0) {
    				Patient patient = new Patient();
    				patient.setpatientdID(Integer.parseInt(tokens[PDID]));
    				patient.setpatientdUserName(tokens[PDUSERNAME]);
    				patient.setpID(Integer.parseInt(tokens[PID]));
    				patient.setpFirstName(tokens[PFIRSTNAME]);
    				patient.setpLastName(tokens[PLASTNAME]);
    				patient.setpDob(tokens[PDOB]);
    				patient.setpAddress(tokens[PADDRESS]);
    				patient.setpPhoneNumber(tokens[PPHONENUMBER]);
    				patient.setpBillingCycle(tokens[PBILLING]);
    				patient.setpMedicalCondition(tokens[PMEDICAL]);
    				patient.setpComments(tokens[PCOMMENTS]);
    				patientlist.add(patient);
    			}
    		}
    	}
    	catch (Exception e) {
    		System.out.println("Error in CSVFileReader!");
    		e.printStackTrace();
    	} 
    	finally {try {
    			fileReader.close();
    		} catch (IOException e) {
    			System.out.println("Error while closing fileReader!");
    			e.printStackTrace();
    		}
    	}
    	return patientlist;
    }

    public void writeExportFile(ArrayList<Patient> exportPatientList, File exportfile) throws IOException{
    	FileWriter fw = new FileWriter(exportfile.getAbsoluteFile(), false);//Because this is false, the current file is overriten
    	BufferedWriter bw = new BufferedWriter(fw);
    	if (!exportfile.exists()) {
    		exportfile.createNewFile();
    		bw.write("Doctor ID, Username, Patient ID,First Name,Last Name,Date of Birth,Address,Phone Number,Billing Cycle,Medical Condition,Comments");
    		bw.write("\n");
    	}
    	@SuppressWarnings("resource")
    	BufferedReader input = new BufferedReader(new FileReader(exportfile));
    	@SuppressWarnings("unused")
    	String line = null;
    	if ((line = input.readLine()) == null) {
    		bw.write("Doctor ID, Username, Patient ID,First Name,Last Name,Date of Birth,Address,Phone Number,Billing Cycle,Medical Condition,Comments");
    		bw.write("\n");
    	}
    	for(Patient p: exportPatientList){
    		bw.append(p.getpatientdID() + ", " + p.getpatientdUserName()+","+p.getpID()+","+p.getpFirstName()+","+p.getpLastName()+","+p.getpDob()+
    				","+p.getpAddress()+","+p.getpPhoneNumber()+","+p.getpBillingCycle()+","+p.getpMedicalCondition()+","+p.getpComments());
    		bw.newLine();
    	}
    	try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void writePatientFile(ArrayList<Patient> patientlist) throws FileNotFoundException {

		try {
			File file = new File(patientfilename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);//Because this is false, the current file is overriten
			BufferedWriter bw = new BufferedWriter(fw);

			if (!file.exists()) {
				file.createNewFile();
				bw.write("Doctor ID, Username, Patient ID,First Name,Last Name,Date of Birth,Address,Phone Number,Billing Cycle,Medical Condition,Comments");
				bw.write("\n");
			}
			@SuppressWarnings("resource")
			BufferedReader input = new BufferedReader(new FileReader(file));
			@SuppressWarnings("unused")
			String line = null;
			if ((line = input.readLine()) == null) {
				bw.write("Doctor ID, Username, Patient ID,First Name,Last Name,Date of Birth,Address,Phone Number,Billing Cycle,Medical Condition,Comments");
				bw.write("\n");
			}
			for(Patient p: patientlist){
			bw.append(p.getpatientdID() + ", " + p.getpatientdUserName()+","+p.getpID()+","+p.getpFirstName()+","+p.getpLastName()+","+p.getpDob()+
					","+p.getpAddress()+","+p.getpPhoneNumber()+","+p.getpBillingCycle()+","+p.getpMedicalCondition()+","+p.getpComments());
			bw.newLine();
						}
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
  
    public void writeDoctorFile(Doctor doctor) throws FileNotFoundException {
		try {
			File file = new File(doctorfilename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			if (!file.exists()) {
				file.createNewFile();
				bw.write("Doctor ID, Username, Password,First Name,Last Name");
				bw.write("\n");
			}
			@SuppressWarnings("resource")
			BufferedReader input = new BufferedReader(new FileReader(file));
			@SuppressWarnings("unused")
			String line = null;
			if ((line = input.readLine()) == null) {
				bw.write("Doctor ID, Username, Password,First Name,Last Name");
				bw.write("\n");
			}
			bw.append(doctor.toString());//check this
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public void writePatientFile(Patient patient) throws FileNotFoundException {
		try {
			File file = new File(patientfilename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			if (!file.exists()) {
				file.createNewFile();
				bw.write("Doctor ID, Username, Patient ID,First Name,Last Name,Date of Birth,Address,Phone Number,Billing Cycle,Medical Condition,Comments");//Check order of this, and add images
				bw.write("\n");
			}
			@SuppressWarnings("resource")
			BufferedReader input = new BufferedReader(new FileReader(file));
			@SuppressWarnings("unused")
			String line = null;
			if ((line = input.readLine()) == null) {
				bw.write("Doctor ID, Username, Patient ID,First Name,Last Name,Date of Birth,Address,Phone Number,Billing Cycle,Medical Condition,Comments");
				bw.write("\n");
			}
			bw.append(patient.toString());
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public DefaultTableModel createModel(ArrayList<Patient> patientlist) {
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] { "Doctor ID", "Doctor Username","Patient ID", "First Name", "Last Name", "DOB",
				"Address", "Phone Number", "Billing Cycle", "Medical Condition", "Comments" });
		model.setRowCount(patientlist.size());
		int row = 0;
		for (Patient patient : patientlist) {
			model.setValueAt(patient.getpatientdID(), row, 0);
			model.setValueAt(patient.getpatientdUserName(), row, 1);
			model.setValueAt(patient.getpID(), row, 2);
			model.setValueAt(patient.getpFirstName(), row, 3);
			model.setValueAt(patient.getpLastName(), row, 4);
			model.setValueAt(patient.getpDob(), row, 5);
			model.setValueAt(patient.getpAddress(), row, 6);
			model.setValueAt(patient.getpPhoneNumber(), row, 7);
			model.setValueAt(patient.getpBillingCycle(), row, 8);
			model.setValueAt(patient.getpMedicalCondition(), row, 9);
			model.setValueAt(patient.getpComments(), row, 10);
			row++;
		}
		return model;
	}	
	
}
