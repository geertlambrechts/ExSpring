package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Login;
import be.abis.exercise.repository.PersonRepository;

@Controller
public class LoginController {	
	
		@Autowired
		PersonRepository personRepository;
		
		@GetMapping("/login")
		public String showLogin(Model model) {
			Login login = new Login();
			model.addAttribute("login",login);
			return "login";
		}
		
		@PostMapping("/login")
		public String verifyLogin(Model model, Login login) {
			String returnPage = "login";
			if (personRepository.findPerson(login.getEmail(), login.getPassword()) != null) {
				returnPage = "welcome";
			}
			return returnPage;
		}
}
