package be.abis.exercise.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.repository.TrainingRepository;
import be.abis.exercise.service.*;


@RunWith(MockitoJUnitRunner.class)
public class AbisCourseServiceTest {
	@Mock 
	TrainingRepository trainingRepositoryMock;
	
	@Mock 
	PersonRepository personRepositoryMock;
	
	@InjectMocks
	CourseService courseService;

	@Test
	public void testFindPerson_with_id() {
		Address addr = new Address ("Oude Leeuwenlaan", "Brussel", "1000", 10);
		Company comp = new Company ("BNP", "025651020", "BE01234567890", addr);
		Person pers = new Person (10, "Jan", "Jansen",25,"JanJanssen@proximus.be","JanJan","NL",comp);
		
		when(personRepositoryMock.findPerson(10)).thenReturn (pers);

	}

}
