package Spring2014Users;
import java.util.HashMap;
import java.util.Map;

public enum Student implements MISC.Student {
	//names subject to change
	BOB("Dr.", "Bob"),
	STUDENT("Joe", "Student"),
	HAJAR("Hebah", "Hajar"),
	HARPER("Matisen", "Harper"),
	HOFFPAUIR("Alex", "Hoffpauir"),
	KART("DR.", "Kart"),
	ROCHA("Freddie", "Rocha");
	
	

	private static Map<Student, String> studentToDisplayNameMap;
	private static Map<String, Student> lastNameToStudentMap;
	private String firstName;
	private String lastName;
	private String fileNameSuffix;
	
	Student(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.fileNameSuffix = lastName;
	}
	
	Student(String firstName, String lastName, String fileNameSuffix)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.fileNameSuffix = fileNameSuffix;
	}
	
	static
	{
		studentToDisplayNameMap = new HashMap<Student, String>();
		studentToDisplayNameMap.put(BOB, "Dr. Bob");
		studentToDisplayNameMap.put(STUDENT, "Joe Student");
		studentToDisplayNameMap.put(HAJAR, "Hebah Hajar");
		studentToDisplayNameMap.put(HARPER,"Matisen Harper");
		studentToDisplayNameMap.put(HOFFPAUIR, "Alex Hoffpauir");
		studentToDisplayNameMap.put(KART, "DR. Kart");
		studentToDisplayNameMap.put(ROCHA, "Freddie Rocha");
		
		
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
	
	public static Student getStudent(String lastName)
	{
		return lastNameToStudentMap.get(lastName);
	}
}
