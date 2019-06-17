package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.form.FotografoForm;
import it.uniroma3.siw.model.Fotografo;
import it.uniroma3.siw.services.FotografoService;
import it.uniroma3.siw.services.FotografoValidator;

@Controller
public class FotografoController {

	@Autowired
	private FotografoService fotografoService;
	
	@Autowired
	private FotografoValidator fotografoValidator;

	@GetMapping(value = "/fotografo/{id}")
	public String getFotografo(@PathVariable("id") Long id, Model model) {
		Fotografo fotografo = this.fotografoService.getFotografoById(id);
		if(fotografo == null)
			return "redirect:/";
		model.addAttribute("fotografo",fotografo);
		return "fotografo.html";
	}
	
	@GetMapping(value = "/cancellaFotografo/{id}")
	public String cancellaFotografo(@PathVariable("id") Long id, Model model) {
		fotografoService.deleteFotografoById(id);
		return "redirect:/fotografi";
	
	}
	
	@GetMapping(value = "/fotografi")
	public String getFotografi(Model model) {
		model.addAttribute("fotografi",fotografoService.getRandomFotografi(5));
		model.addAttribute("fotografoForm",new FotografoForm());
		return "listaFotografi.html";
	}
	
	@PostMapping(value="/salvaFotografo")
	public String salvaFoto(@Valid @ModelAttribute("fotografoForm") FotografoForm fotografoForm,
			Model model, BindingResult bindingResult) {
		this.fotografoValidator.validate(fotografoForm, bindingResult);
		if(!bindingResult.hasErrors()) {//in caso non ci siano errori
			Fotografo fotografo = new Fotografo();
			fotografo.setNome(fotografoForm.getNome());
			fotografo.setCognome(fotografoForm.getCognome());
			fotografo.setEmail(fotografoForm.getEmail());
			fotografo.setDataNascita(LocalDate.parse(fotografoForm.getDataNascita(), DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.ITALY)));
			this.fotografoService.salvaFotografo(fotografo);
			return "redirect:/fotografi";
		}
		model.addAttribute("fotografi",fotografoService.getRandomFotografi(5));
		return "listaFotografi.html";
	}
	
	@PostMapping(value = "/cercaFotografi")
	public String cercaFotografo(Model model, @RequestParam("parametro") String parametro) {
		model.addAttribute("fotografi",fotografoService.effettuaRicercaPerParametro(parametro));
		model.addAttribute("fotografoForm",new FotografoForm());
		return "listaFotografi.html";
	}
}

