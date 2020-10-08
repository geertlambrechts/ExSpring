package be.abis.exercise.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import be.abis.exercise.Exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Enrollment;
import be.abis.exercise.model.Person;

@Repository
public class AbisTrainingRepository implements TrainingRepository {

	private List<Enrollment> allEnrollments;
	
	public AbisTrainingRepository() {
		allEnrollments = new ArrayList<Enrollment>();
	}

	@Override
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {
		List<Course> listCourses = showFollowedCourses(person);
		if (listCourses.contains(course)) {
			throw new EnrollException();
		}
	
		allEnrollments.add(new Enrollment(person, course, date));
	}


	@Override
	public List<Course> showFollowedCourses(Person person) {
		List<Course> trainingcoursesPerson = new ArrayList<>(); 
		for (Enrollment enrollment : allEnrollments) {	
			if (enrollment.getPerson() == person) {
				trainingcoursesPerson.add(enrollment.getCourse());
			}
		}
		return trainingcoursesPerson;
	}

	
}
