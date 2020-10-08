package be.abis.exercise.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import be.abis.exercise.model.Course;

public interface CourseRepository {

	public List<Course> findAllCourses();
	public Course findCourse(int id);
	public Course findCourse(String shortTitle);
		
}
