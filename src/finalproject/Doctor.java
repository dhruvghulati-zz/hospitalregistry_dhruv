package finalproject;

/**
 * The doctor class inherits from a Person class - when a Doctor is created, a first name and last name variables are automatically initialises,
 * as is a person (super() calls the Person constructor).
 */

public class Doctor extends Person{
	
	private int dID;
	private String duserName;
	private String dpassWord;
	
	/**
	 * The doctor object inherits from a Person object - when a Doctor is created, a first name and last name variables are automatically initialises,
	 * as is a person (super() calls the Person constructor).
	 */
	public Doctor(){
		super();
	}
	
	public int getdID() {
		return dID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dpassWord == null) ? 0 : dpassWord.hashCode());
		result = prime * result + ((duserName == null) ? 0 : duserName.hashCode());
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
		Doctor other = (Doctor) obj;
		if (dpassWord == null) {
			if (other.dpassWord != null)
				return false;
		} else if (!dpassWord.equals(other.dpassWord))
			return false;
		if (duserName == null) {
			if (other.duserName != null)
				return false;
		} else if (!duserName.equals(other.duserName))
			return false;
		return true;
	}

	public void setdID(int dID) {
		this.dID = dID;
	}

	public String getDuserName() {
		return duserName;
	}

	public void setDuserName(String duserName) {
		this.duserName = duserName;
	}

	public String getDpassWord() {
		return dpassWord;
	}

	public void setDpassWord(String dpassWord) {
		this.dpassWord = dpassWord;
	}
	
	/**
	 * I override the toString() method of the Doctor object to not be a garbage collection for an ArrayList, but for each object to produce a custom
	 * string of its attributes that can fit into a CSV file in a nice format.
	 */
	@Override
	public String toString() {
		return (this.dID+","+duserName+","+dpassWord.toString()+","+ firstName + "," + lastName);
	}
	
}
