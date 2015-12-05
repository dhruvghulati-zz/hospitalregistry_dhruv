package hospitalregistry;

public class Doctor {
	
	private int dID;
	private String duserField;
	private String dpasswordField;
	
	public Doctor(){
		this.dID = dID;
		this.duserField = duserField;
		this.dpasswordField = dpasswordField;
	}
	

	public int getdID() {
		return dID;
	}
	
	public String getDuserField() {
		return duserField;
	}
	
	public String getDpasswordField() {
		return dpasswordField;
	}


	public void setdID(int dID) {
		this.dID = dID;
	}


	public void setDuserField(String duserField) {
		this.duserField = duserField;
	}


	public void setDpasswordField(String dpasswordField) {
		this.dpasswordField = dpasswordField;
	}
	
}



//Authenticate - access constructor of the doctor
//Doctor constructed on username of the field
//Construct if the username and password
//Authentication class lookup - create doctor and move to the next step only in the match
