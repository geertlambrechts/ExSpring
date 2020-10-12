package be.abis.exercise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.PersonRepository;

@Controller
public class LoginController {
	Person person;

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	CourseRepository courseRepository;

	@GetMapping("/")
	public String showLogin(Model model) {
		Login login = new Login();
		model.addAttribute("login", login);
		return "login";
	}

	@PostMapping("/")
	public String verifyLogin(Model model, Login login) {
		String returnPage = "login";
		person = personRepository.findPerson(login.getEmail(), login.getPassword());
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
		List<Course> courses = courseRepository.findAllCourses();
		model.addAttribute("courselist", courses);
		return "showcourses";
	}
	

	@PostMapping("/findCourseById")
	public String findCourseById(Model model, Course course) {
		String courseid = course.getCourseId();
		List<Course> courses = new ArrayList<Course>();
		try {
			int myId = Integer.parseInt(courseid);
			Course courseFound = courseRepository.findCourse(myId);
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

		Course courseFound = courseRepository.findCourse(shortTitle);
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
}
