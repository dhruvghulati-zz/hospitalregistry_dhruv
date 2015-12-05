package finalproject;
	
	/**
	 * Each patient has its own ID, the ID of its doctor, a first name, last name, date of birth, address, phone number, billing cycle,
	 * medical condition, and comments
	 */
public class Patient extends Person {
	
	private int pID;
	private int patientdID;
	private String patientduserName;
	private String pFirstName;
	private String pLastName;
	private String pDob;
	private String pAddress;
	private String pPhoneNumber;
	private String pBillingCycle;
	private String pMedicalCondition;
	private String pComments;
	
	/**
	 * The patient object inherits from a Person object - when a Patient is created, first name and last name variables are automatically initialised,
	 * as is a person (super() calls the Person constructor). However, due to legacy reasons, I actually have unique first name and last name variables
	 * within the Patient class (pFirstName and pLastName). These could have easily been ignored, and the variables of the parent used instead.
	 */
	public Patient(){
		super();
	}
	
	/**
	 * I override the toString() method of the Patient object to not be a garbage collection for an ArrayList, but for each object to produce a custom
	 * string of its attributes that can fit into a CSV file in a nice format.
	 */
	@Override
	public String toString() {
		return this.patientdID+","+patientduserName+","+this.pID + "," + pFirstName + "," + pLastName + "," + pDob
				+ "," + pAddress + "," + pPhoneNumber + "," + pBillingCycle
				+ "," + pMedicalCondition + "," + pComments;
	}
	
	
	/**
	 * 
	* <dl>
	* <dt> Purpose:
	* <dd> This method generates unique hashcodes for the patients, based off only their doctor ID (the logged in doctor who registered them), their
	* first name, and last name. I override the equals and hashcode methods in Java.
	*
	* <dt> Description:
	* <dd> The hashcodes are unique based on the first name and last name of the patient.
	* 
	* <dt> References:
	* <dd>
	 * https://msdn.microsoft.com/en-us/library/aa990337(v=vs.80).aspx
	 * http://www.programcreek.com/2011/07/java-equals-and-hashcode-contract/
	 * http://www.javamex.com/tutorials/collections/hash_function_guidelines.shtml - string hashcodes
	 * https://en.wikipedia.org/wiki/Java_hashCode() - good article, public int hashcode
	 * http://www.javaworld.com/article/2074996/hashcode-and-equals-method-in-java-object---a-pragmatic-concept.html
	 * http://www.tutorialspoint.com/java/java_string_hashcode.htm - hashcode for a string
	 * http://tutorials.jenkov.com/java-collections/hashcode-equals.html
	 * http://howtodoinjava.com/2012/10/09/working-with-hashcode-and-equals-methods-in-java/
	 * http://stackoverflow.com/questions/2066917/overriding-equals-hashcode-in-sub-classes-considering-super-fields
		http://stackoverflow.com/questions/22449772/how-hashcode-works-in-java
		http://www.coderanch.com/t/321515/java/java/HashCode
		https://www.youtube.com/watch?v=fAlRR2p9Le0
		http://www.javapractices.com/topic/TopicAction.do?Id=28
		http://stackoverflow.com/questions/113511/best-implementation-for-hashcode-method
		http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
		http://javarevisited.blogspot.co.uk/2011/10/override-hashcode-in-java-example.html
		http://www.javaranch.com/journal/2002/10/equalhash.html
		http://stackoverflow.com/questions/2066917/overriding-equals-hashcode-in-sub-classes-considering-super-fields
	* 
	* </dd>
	* </dl>
	*
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pFirstName == null) ? 0 : pFirstName.hashCode());
		result = prime * result + ((pLastName == null) ? 0 : pLastName.hashCode());
		result = prime * result + patientdID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (pFirstName == null) {
			if (other.pFirstName != null)
				return false;
		} else if (!pFirstName.equals(other.pFirstName))
			return false;
		if (pLastName == null) {
			if (other.pLastName != null)
				return false;
		} else if (!pLastName.equals(other.pLastName))
			return false;
		if (patientdID != other.patientdID)
			return false;
		return true;
	}

	/*
	 * Getters and setters
	 */
	public int getpID() {
		return pID;
	}


	public void setpID(int pID) {
		this.pID = pID;
	}
	

	public int getpatientdID() {
		return patientdID;
	}


	public void setpatientdID(int patientdID) {
		this.patientdID = patientdID;
	}

	public String getpatientdUserName() {
		return patientduserName;
	}


	public void setpatientdUserName(String patientdUserName) {
		this.patientduserName = patientdUserName;
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


	public String getpComments() {
		return pComments;
	}


	public void setpComments(String pComments) {
		this.pComments = pComments;
	}
		
}


