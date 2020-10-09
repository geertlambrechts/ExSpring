package be.abis.exercise.it;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTrainingService {

	@Autowired
	TrainingService trainingService;
	
	@Test
	public void person2isMary() {
		Person found = trainingService.findPerson(2);
		assertEquals("Mary",found.getFirstName());
	}
	
	@Test
	public void course7900isSQL() {
		Course c = trainingService.getCourseService().findCourse(7900);
		assertEquals("Workshop SQL",c.getShortTitle());
	}
	
}
