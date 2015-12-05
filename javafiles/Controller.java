package finalproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 *The controller is a means of my main GUI generating database files, accessing my database files, and passing objects (in my case, arraylists) between
 *the view and the database.Contains methods which allow a user to extract arraylists from CSV files, overwrite CSV files with arraylists or indeed objects, and write new files.
 *It also contains methods to handle the importing and exporting of files.
     */
 
public class Controller {
	
    private static final int PDID = 0;
    private static final int PDUSERNAME = 1;
    private static final int PID = 2;
    private static final int PFIRSTNAME = 3;
    private static final int PLASTNAME = 4;
    private static final int PDOB = 5;
    private static final int PADDRESS = 6;
    private static final int PPHONENUMBER = 7;
    private static final int PBILLING = 8;
    private static final int PMEDICAL = 9;
    private static final int PCOMMENTS = 10;
    
    private static final int DID = 0;
    private static final int DUSERNAME = 1;
    private static final int DPASSWORD = 2;
    private static final int DFIRSTNAME = 3;
    private static final int DLASTNAME = 4;
       
    private static final String COMMA_DELIMITER = "\\s*,\\s*";
    
    
    private String patientfilename = "resources/masterpatients.csv";
    private String doctorfilename = "resources/masterdoctors.csv";
    private File patientfile = new File(patientfilename);
    private File doctorfile = new File(doctorfilename);

       
    /**
     * 
    * This method generates a new patient file in the relative path specified.
    * 
    * References:
    * http://examples.javacodegeeks.com/core-java/writeread-csv-files-in-java-example/
    * @throws FileNotFoundException  if the filename specified is incorrect
    * 
    *
     */
    public void writeNewPatientFile() throws FileNotFoundException {
    	if (!patientfile.exists()) {
    		try {
    			patientfile.createNewFile();
    			System.out.println("New patient file has been generated");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    /**
     * 
    * This method generates a new doctor file in the path specified.
    *  @throws FileNotFoundException  if the filename specified is incorrect or doesn't exist
    *
     */
    public void writeNewDoctorFile() throws FileNotFoundException {
		if (!doctorfile.exists()) {
			try {
				doctorfile.createNewFile();
				System.out.println("New doctor file has been generated");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
}
   
  		
    /**
     * 		
    * Similar method exists for extracting an arraylist of patients from a file - here I can extract an arraylist of Doctor objects from any file.
    * 
    *  @return doctorlist  A new arraylist of master doctors, obtained from the master database of doctors in the system.
     */
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
   
	/**
	 * <dl>
	* <dt>Purpose:
	* <dd> From any CSV file with the correct specifications of entry types in each column (for example, the ID columns must be populated with integers,
	* this method will generate an arraylist of Patient objects, assuming that each column of the CSV is essentially a variable for the Patient, placed in
	* a specific order (which is the same order as the toString() method of Patient).

	* <dt> References:
	* <dd>
	*  http://www.java2s.com/Code/Java/Development-Class/CSVfilewriter.htm
    	http://www.java2s.com/Code/Java/File-Input-Output/Writingdelimitedtextdatatoafileorastream.htm
    	http://www.java2s.com/Code/Java/File-Input-Output/Readeachlineinacommaseparatedfileintoanarray.htm
    	http://examples.javacodegeeks.com/core-java/writeread-csv-files-in-java-example/
    	http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
    	http://stackoverflow.com/questions/5797208/java-how-do-i-write-a-file-to-a-specified-directory
    	http://viralpatel.net/blogs/java-read-write-csv-file/
    	http://examples.javacodegeeks.com/core-java/writeread-csv-files-in-java-example/
    	http://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
	* </dl>
	* @param importfile  The file that is imported into the system, for which a patientlist needs to be generated and verified.
	* @return patientlist  An arraylist of patients to be verified.
	 */
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
    
    /**
	<dl>
    * <dt>Purpose:
    * <dd> From the patient file name who have specified in the class (relative to the root of the class), an arraylist of patientlists can be generated
    *
    * <dt> Description:
    * <dd> To ensure that the method waits for the full end of an expected row before doing its extraction (instead of looking for the next bit of
    * whitespace), I hard code the fact that the array of tokens generated should be 11 tokens long, instead of being less or more.
    * 
    * <dt> References:

    * <dd>http://stackoverflow.com/questions/20086784/java-how-to-read-file-into-arraylist-of-objects
    * http://stackoverflow.com/questions/7488643/how-to-convert-comma-separated-string-to-arraylist
    * </dl>
    * @return patientlist  From any CSV file with the correct headers and the types of columns (int, String), return an arraylist
    * of patients

    *
     */
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
    
    /**
     * 
    * <dl>
    * <dt> Purpose:
    * <dd> This method takes in an arraylist of patients to be exported, and the file location specified for the export, and then
    * writes a CSV file of this arraylist of patients generated from the users selection
    *
    * <dt> Description:
    * <dd> Because the file writer is false, if the file passed in as an argument exists already, it will be overritten.
    * 
    * @param exportfile  The file you want to export to
    * @param exportPatientList The patientlist the user has selected to export, via selecting rows in the JTable
    * @throws IOException  Throws an error if the buffered writer cannot be closed.
    * </dl>
    *
     */
    public void writeExportFile(ArrayList<Patient> exportPatientList, File exportfile) throws IOException{
    	FileWriter fw = new FileWriter(exportfile.getAbsoluteFile(), false);
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
    
    /**
     * This overwrites the patient file with the current value of the patientlist.
    *  @author dhruv
    *  @param patientlist  The patientlist which we are going to use and one by one reset the master file, and append each object 
    *  to.
    *  
    *  @throws FileNotFoundException  if the file is not found.
    *
     */
    public void writePatientFile(ArrayList<Patient> patientlist) throws FileNotFoundException {
		try {
			File file = new File(patientfilename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
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
    
    /**
     * 
    * Writes to the absolute path of the doctor file if we have a new doctor registered, including first names and the password registered with.

    *   @param doctorlist  The doctorlist which we are going to use and one by one reset the master file, and append each object 
    *  to.
    *  @throws FileNotFoundException for if we don't have a doctor file to write to
    *
     */
    public void writeDoctorFile(ArrayList<Doctor> doctorlist) throws FileNotFoundException {
    	try {
			File file = new File(doctorfilename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
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
			for(Doctor d: doctorlist){				
				bw.append(d.getdID() + ", " + d.getDuserName()+","+d.getDpassWord()+","+d.getFirstName()+","+d.getLastName());
				bw.newLine();
						}
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
