package Milestone1_Runner;
import java.util.HashMap;
import java.util.Map;



public enum COSC3327_STUDENT_SMALL implements Student {
	KART("Dr.", "Kart"),
	SMITH("John", "Smith");

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
		studentToDisplayNameMap.put(KART, "GOLD STANDARD");
		
		studentToDisplayNameMap.put(SMITH, "John Smith");
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
