package javafxLoginrecCrudForm;

public class StudentData {

	private Integer student_number;
	private String full_name;
	private String year;
	private String course;
	private String gender;
	
	public StudentData(Integer student_number, String full_name, String year, String course, String gender) {
		super();
		this.student_number = student_number;
		this.full_name = full_name;
		this.year = year;
		this.course = course;
		this.gender = gender;
	}

	public Integer getStudent_number() {
		return student_number;
	}

	public void setStudent_number(Integer student_number) {
		this.student_number = student_number;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	
	
}
