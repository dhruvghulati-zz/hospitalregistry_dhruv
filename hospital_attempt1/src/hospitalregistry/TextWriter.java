package hospitalregistry;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class TextWriter{

	private static ArrayList<Patient> PatientList = new ArrayList<Patient>();
	private static ArrayList<Doctor> DoctorList = new ArrayList<Doctor>();
	private static final String patientfilepath = "/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/Patients.txt";
	private static final File patientfile = new File(patientfilepath);
	
	public static String addPatient(Object patient){
		PatientList.add(patient);
		return PatientList.toString();
	}
	
	
	public static void addDoctor(Doctor doctor){
		DoctorList.add(doctor);
	}
	
	public String toPatientString(){
		return PatientList.toString();//TODO should a patientlist be a derived object from Patient?
	}

//	public String toDoctorString(){
//
//	}
	
	public ArrayList<Patient> readPatientFile(){
//		final String = beanReader. get header (true)
//				final Cell processors - get ProcessorsPatients
		Scanner s = new Scanner(patientfile);
		while (s.hasNextLine()){
		    addPatient(s.nextLine());
		}
		//TODO Define a new list and then return = patientlist
		For each line, Patient patient = the line
				patientlist.add Patient
		//Read through lines of CSV
		return ArrayList<Patient>
		s.close();
	}

	public void writePatientFile(File file){

	}

	public void readDoctorFile(File file){

	}

	public void writeDoctorFile(File file){

	}

	
	
	
	public static void readPatientFile(ArrayList<Patient>PatientList) throws FileNotFoundException{
		//Just reading a file, so no need for args
		Scanner s = new Scanner(new File("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/Patients.txt"));
		while (s.hasNextLine()){
			PatientList.add(s.nextLine());PatientList.addAll(patient);
			patient.setnumber
			add that patient to that list therefore for each, patient = set the line
			Long list of sets as you read each line, set
			Reads it.
			patientreader.read
			each line is a set of attributes of the patient list
		}
		s.close();

		BufferedReader reader = new BufferedReader(new FileReader("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/Patients.txt"));
		Patient line;
		while ((line = reader.readLine()) != null) {
			PatientList.add(line);
		}
		reader.close();
	}
	
	
	public static void readDoctorFile(ArrayList<Doctor>DoctorList) throws FileNotFoundException{
	
	}

	/**
	 * http://stackoverflow.com/questions/30444079/putting-several-textfields-into-one-arraylist
	 * https://www.youtube.com/watch?v=aakSrwq1ZO8
	 */
	
	public static void writePatientFile(ArrayList<Patient> PatientList) throws IOException {
		String str = "";
		for (int i=0; i<PatientList.size();i++){
			str += PatientList.get(i).toString() +" \n";
		}
		System.out.print(str);
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/Patients.csv"), "utf-8"))) {
			writer.write(str);
			((BufferedWriter) writer).newLine();
		}
	}
	
	

	
//	public static void writeDoctorFile(String dID, String dUserField, String dpasswordField) throws IOException {
//
//		FileWriter patientFile;
//		patientFile = new FileWriter("/Users/dhruv/Dropbox/uclcs/gc001_java/javacode/hospitalregistry/src/Doctors.txt");
//		try {
//			System.out.println(dID);
//			patientFile.write("\n");
//			patientFile.write(dUserField + "\n");
//			patientFile.write(dpasswordField + "\n");
//			patientFile.write("\n");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		patientFile.close();	
//	}
	
	
}
