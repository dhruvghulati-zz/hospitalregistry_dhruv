package hospitalregistry;

import java.util.ArrayList;

public class Patient {
	
	private int pID;
	private String pFirstName;
	private String pLastName;
	private String pDob;
	private String pAddress;
	private String pPhoneNumber;
	private String pBillingCycle;
	private String pMedicalCondition;
	private String pLastPrescription;
	private String pLastAptDate;
	private String pComments;
	
//	String comments; Each record might contain a profile picture of the
//	patient and a set of pictures describing his/her condition such as brain scans
//	(please use any sample image, It doesn’t have to be realistic). 
	
	
	public Patient(){
		this.pID = pID;
		this.pFirstName = pFirstName;
		this.pLastName = pLastName;
		this.pDob = pDob;
		this.pAddress = pAddress;
		this.pPhoneNumber = pPhoneNumber;
		this.pBillingCycle = pMedicalCondition;
		this.pLastPrescription = pLastPrescription;
		this.pLastAptDate = pLastAptDate;
		this.pComments = pComments;
	}

	public int getpID() {
		return pID;
	}


	public void setpID(int pID) {
		this.pID = pID;
	}


	public String getpFirstName() {
		return pFirstName;
	}


	public void setpFirstName(String pFirstName) {
		this.pFirstName = pFirstName;
	}


	public String getpLastName() {
		return pLastName;
	}


	public void setpLastName(String pLastName) {
		this.pLastName = pLastName;
	}


	public String getpDob() {
		return pDob;
	}


	public void setpDob(String pDob) {
		this.pDob = pDob;
	}


	public String getpAddress() {
		return pAddress;
	}


	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}


	public String getpPhoneNumber() {
		return pPhoneNumber;
	}


	public void setpPhoneNumber(String pPhoneNumber) {
		this.pPhoneNumber = pPhoneNumber;
	}


	public String getpBillingCycle() {
		return pBillingCycle;
	}


	public void setpBillingCycle(String pBillingCycle) {
		this.pBillingCycle = pBillingCycle;
	}


	public String getpMedicalCondition() {
		return pMedicalCondition;
	}


	public void setpMedicalCondition(String pMedicalCondition) {
		this.pMedicalCondition = pMedicalCondition;
	}


	public String getpLastPrescription() {
		return pLastPrescription;
	}


	public void setpLastPrescription(String pLastPrescription) {
		this.pLastPrescription = pLastPrescription;
	}


	public String getpLastAptDate() {
		return pLastAptDate;
	}


	public void setpLastAptDate(String pLastAptDate) {
		this.pLastAptDate = pLastAptDate;
	}


	public String getpComments() {
		return pComments;
	}


	public void setpComments(String pComments) {
		this.pComments = pComments;
	}
	
	public String toString(){
			return pID+","+pFirstName + "," + pLastName+ "," +pDob+","+ pAddress+","+pPhoneNumber+","
					+pBillingCycle+","+pMedicalCondition+","+pLastPrescription+","+pLastAptDate+","+pComments;
		}
	
	//writePatient()
	
	
}

public class PatientCollection extends Patient{
	private static ArrayList<Patient> PatientList = new ArrayList<Patient>();
}


