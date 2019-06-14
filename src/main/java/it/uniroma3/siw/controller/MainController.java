package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.model.Funzionario;

@Controller
public class MainController {
	
	 @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

  // Login form
  @GetMapping("/login")
  public String login(Model model) {
	model.addAttribute("funzionario",new Funzionario());
	System.out.println(bCryptPasswordEncoder.encode("ciao1234"));
    return "login.html";
  }

  // Login form with error
  @RequestMapping("/login-error.html")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "login.html";
  }
  
  @GetMapping("/statoLogin")
  public String statologin() {
    return "successoLogin.html";
  }

}
