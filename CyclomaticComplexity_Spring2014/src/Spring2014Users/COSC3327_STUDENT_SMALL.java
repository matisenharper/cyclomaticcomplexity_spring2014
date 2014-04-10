package Spring2014Users;
import java.util.HashMap;
import java.util.Map;

import MISC.Student;

public enum COSC3327_STUDENT_SMALL implements Student {
	BOB("Dr.", "Bob"),
	STUDENT("Joe", "Student");

	private static Map<COSC3327_STUDENT_SMALL, String> studentToDisplayNameMap;
	private static Map<String, COSC3327_STUDENT_SMALL> lastNameToStudentMap;
	private String firstName;
	private String lastName;
	private String fileNameSuffix;
	
	COSC3327_STUDENT_SMALL(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.fileNameSuffix = lastName;
	}
	
	COSC3327_STUDENT_SMALL(String firstName, String lastName, String fileNameSuffix)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.fileNameSuffix = fileNameSuffix;
	}
	
	static
	{
		studentToDisplayNameMap = new HashMap<COSC3327_STUDENT_SMALL, String>();
		studentToDisplayNameMap.put(BOB, "Dr. Bob");
		studentToDisplayNameMap.put(STUDENT, "Joe Student");
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getFileNameSuffix()
	{
		return fileNameSuffix;
	}
	
	public String toString()
	{
		return studentToDisplayNameMap.get(this);
	}
	
	public static COSC3327_STUDENT_SMALL getStudent(String lastName)
	{
		return lastNameToStudentMap.get(lastName);
	}
}
