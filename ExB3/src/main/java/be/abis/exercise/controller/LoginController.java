package be.abis.exercise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.*;



@Controller
public class LoginController {
	Person person;

	@Autowired
	TrainingService trainingService;
	
	@GetMapping("/")
	public String showLogin(Model model) {
		Login login = new Login();
		model.addAttribute("login", login);
		return "login";
	}

	@PostMapping("/")
	public String verifyLogin(Model model, Login login) {
		String returnPage = "login";
		person = trainingService.findPerson(login.getEmail(), login.getPassword());
		if (person != null) {
			returnPage = "redirect:/welcome";
		}
		return returnPage;
	}

	@GetMapping("/welcome")
	public String showName(Model model) {
		model.addAttribute("person", person);
		return "welcome";
	}
	
	@PostMapping("/logout")
	public String welcomeLogout(Model model) {
		person=null;
		return "redirect:/";
	}
	

	@GetMapping("/searchforcourses")
	public String searchforcourses(Model model) {
		System.out.println("searchforcourses");
		model.addAttribute("courseById", new Course());
		model.addAttribute("courseByShortTitle", new Course());
		return "searchforcourses";
	}
	

	@PostMapping("/showAllCourses")
	public String showAllCourses(Model model) {
		List<Course> courses = trainingService.getCourseService().findAllCourses();
		model.addAttribute("courselist", courses);
		return "showcourses";
	}
	

	@PostMapping("/findCourseById")
	public String findCourseById(Model model, Course courseById) {
		String courseid = courseById.getCourseId();
		List<Course> courses = new ArrayList<Course>();
		try {
			int myId = Integer.parseInt(courseid);
			Course courseFound = trainingService.getCourseService().findCourse(myId);
			if (courseFound != null) {
				courses.add(courseFound);
			}
		}
		catch (NumberFormatException e) {
		}

		model.addAttribute("courselist", courses);
		return "showcourses";
	}

	@PostMapping("/findCourseByShortTitle")
	public String findCourseByShortTitle(Model model, Course course) {
		String shortTitle = course.getShortTitle();
		List<Course> courses = new ArrayList<Course>();

		Course courseFound = trainingService.getCourseService().findCourse(shortTitle);
		if (courseFound != null) {
			courses.add(courseFound);
		}

		model.addAttribute("courselist", courses);
		return "showcourses";
	}
	
	
	@PostMapping("/backToWelcome")
	public String backToWelcome (Model model) {
		System.out.println("in backToWelcome");
		return "redirect:/welcome";
	}
	

	@GetMapping("/personadministration")
	public String personAdministration(Model model) {
		System.out.println("personadministration");
		return "personadministration";
	}
	

	@GetMapping("/changepassword")
	public String changePassword(Model model) {
		System.out.println("changepassword get");
		model.addAttribute("personPassword", new Person());
		model.addAttribute("info", new String());
		return "changepassword";
	}

	@PostMapping("/updatepassword")
	public String updatepassword (Model model, Person personPassword) {
		String info = new String();
		try {
			System.out.println("updatepassword post");
			
			if (personPassword.getPassword().equals("")) {
				info = "password can not be empty";
			}
			else {
				trainingService.changePassword(person, personPassword.getPassword());
				info = "password updated";
			}
		}
		catch (IOException ioE) {
			info = "error updating password : " + ioE.getMessage();
		}
		model.addAttribute("personPassword", new Person());
		model.addAttribute("info", info);
		return "changepassword";
	}
	

	@GetMapping("/searchforpersons")
	public String searchForPersons(Model model) {
		System.out.println("searchforpersons");
		model.addAttribute("personById", new Person());
		return "searchforpersons";
	}

	
	@PostMapping("/showAllPersons")
	public String showAllPersons(Model model) {
		List<Person> persons = trainingService.getAllPersons();
		model.addAttribute("personlist", persons);
		return "showpersons";
	}

	@PostMapping("/findPersonById")
	public String findPersonById(Model model, Person personById) {
		int personId = personById.getPersonId();
		Person personFound = trainingService.findPerson(personId);
			
		List<Person> persons = new ArrayList<Person>();
		if (personFound != null) {
			persons.add(personFound);
		}

		model.addAttribute("personlist", persons);
		return "showpersons";
	}

	@GetMapping("/newperson")
	public String newPerson(Model model) {
		System.out.println("newperson");
		model.addAttribute("newPerson", new Person());
		model.addAttribute("info", new String());
		model.addAttribute("languageOptions",getLanguageMap());
		return "newperson";
	}
	

	@PostMapping("/insertNewPerson")
	public String insertNewPerson(Model model, Person newPerson) {
		String info = new String();
		System.out.println("in insertNewPerson");
		Person personFound = trainingService.findPerson(newPerson.getPersonId());
		if (personFound == null) {
			try {
				trainingService.addPerson(newPerson);
				info = "person " + newPerson.getFirstName() + " " + newPerson.getLastName() + " added to DB";
				System.out.println("added Person=" + newPerson);
				newPerson = new Person();
			}
			catch (IOException ioE){
				info = "error adding person to DB : " + ioE.getMessage();
			}
		} 
		else {
			info = "Person with personId=" + newPerson.getPersonId() + " exists already";
		}
		model.addAttribute("newPerson", newPerson);
		model.addAttribute("info", info);
		model.addAttribute("languageOptions",getLanguageMap());
		return "newperson";
	}
	
	public Map<String, String> getLanguageMap() {
		Map<String, String> languageMap = new HashMap<>();
		languageMap.put("nl", "Dutch");
		languageMap.put("fr", "French");
		languageMap.put("en", "English");
		languageMap.put("es", "Spanish");
		languageMap.put("de", "German");
		return languageMap;
	}
	

	@GetMapping("/removeperson")
	public String removePerson(Model model) {
		System.out.println("in removeperson");
		model.addAttribute("personToRemove", new Person());
		model.addAttribute("info", new String());
		return "removeperson";
	}
	

	@PostMapping("/removePerson")
	public String removePerson(Model model, Person personToRemove) {
		String info = new String();
		System.out.println("in removePerson post");
		Person personFound = trainingService.findPerson(personToRemove.getPersonId());
		System.out.println("after");
		if (personFound != null) {
				trainingService.deletePerson(personToRemove.getPersonId());
				info = "person " + personFound.getFirstName() + " " + personFound.getLastName() + " removed from DB";
			}
		else {
			info = "Person with personId=" + personToRemove.getPersonId() + " does not exist in DB";
		}
		model.addAttribute("personToRemove", personToRemove);
		model.addAttribute("info", info);
		return "removeperson";
	}
	
}
