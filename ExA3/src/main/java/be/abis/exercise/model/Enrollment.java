package be.abis.exercise.model;

import java.time.LocalDate;

public class Enrollment {
	private Person person;
	private Course course;
	private LocalDate dateEnrollment;
		
	public Enrollment() {
	}
	
	public Enrollment(Person person, Course course, LocalDate dateEnrollment) {
		super();
		this.person = person;
		this.course = course;
		this.dateEnrollment = dateEnrollment;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public LocalDate getDateEnrollment() {
		return dateEnrollment;
	}
	public void setDateEnrollment(LocalDate dateEnrollment) {
		this.dateEnrollment = dateEnrollment;
	} 
	
	
}
