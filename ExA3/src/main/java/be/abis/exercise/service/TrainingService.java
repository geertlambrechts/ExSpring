package be.abis.exercise.service;

import java.time.LocalDate;
import java.util.List;

import be.abis.exercise.Exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;

public interface TrainingService {
	public Person findPerson(int id);
	public List<Course> showFollowedCourses(Person person);
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException;

}
