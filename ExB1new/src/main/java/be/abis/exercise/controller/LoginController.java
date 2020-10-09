package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.*;

@Controller
public class LoginController {	
	
		@Autowired
		TrainingService trainingService;
		
		@PostMapping("/")
		public String verifyLogin(Model model, Login login) {
			String returnPage = "login";
			if (trainingService.verifyLogin(login)) {
				returnPage = "welcome";
			}
			return returnPage;
		}
}
