package be.abis.exercise.it;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import be.abis.exercise.service.*;

import be.abis.exercise.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAbisTrainingService {
	
	@Autowired
	TrainingService trainingService;
	
	@Test
	public void test() {
		trainingService.findPerson(100);

	}

}
