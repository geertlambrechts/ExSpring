package be.abis.exercise.service;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercise.Exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.repository.TrainingRepository;

@Service
public class AbisTrainingService implements TrainingService {
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	TrainingRepository trainingRepository;
		
	public AbisTrainingService() {
	}

	public AbisTrainingService(PersonRepository personRepository, TrainingRepository trainingRepository) {
		super();
		this.personRepository = personRepository;
		this.trainingRepository = trainingRepository;
	}

	@Override
	public Person findPerson(int id) {
		return personRepository.findPerson(id);
	}
	
	@Override
	public List<Course> showFollowedCourses(Person person) {
		return trainingRepository.showFollowedCourses(person);	
	}
	
	@Override
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {
		trainingRepository.enrollForSession(person, course, date);
	}
}
