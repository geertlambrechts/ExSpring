package be.abis.exercise.it;

import static org.junit.Assert.*;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AbisCourseServiceTest {
	@Autowired
	CourseService courseService;

	@Test
	public void testFindCourse_with_id() {
		Course c = courseService.findCourse(7900);
		
		assertEquals("Workshop SQL",c.getShortTitle());
	}

}
