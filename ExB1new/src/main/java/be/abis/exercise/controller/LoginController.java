package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;

@Controller
public class LoginController {
	Person person;

	@Autowired
	PersonRepository personRepository;

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
}
