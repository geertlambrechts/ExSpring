package be.abis.exercise.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import be.abis.exercise.Exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;


public interface TrainingRepository {
	public void enrollForSession (Person person, Course course, LocalDate date) throws EnrollException;
	public List<Course> showFollowedCourses(Person person);
}
