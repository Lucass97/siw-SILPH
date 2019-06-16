package it.uniroma3.siw.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.form.RichiestaForm;
import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.model.Richiesta;
import it.uniroma3.siw.services.RichiestaService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RichiestaController {
	
	@Autowired
	RichiestaService richiestaService;
	
	@GetMapping(value = "/inviaRichiesta")
	public String inviaRichiesta(Model model, HttpSession session) {
		model.addAttribute("richiestaForm", new RichiestaForm());
		@SuppressWarnings("unchecked")
		Map<String, Foto> fotoSelezionate = (Map<String, Foto>) session.getAttribute("fotoSelezionate");
		if(fotoSelezionate == null) {
			fotoSelezionate = new HashMap<String,Foto>();
		}
		model.addAttribute("fotoSelezionate",fotoSelezionate.values());
		model.addAttribute("richiestaForm", new RichiestaForm());
		return "richiesta.html";
	}
	
	@PostMapping(value = "/salvaRichiesta")
	public String salvaRichiesta(@Valid @ModelAttribute("richiestaForm") RichiestaForm richiestaForm,Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<String, Foto> fotoSelezionate = (Map<String, Foto>) session.getAttribute("fotoSelezionate");
		if(fotoSelezionate == null) {
			fotoSelezionate = new HashMap<String,Foto>();
		}
		Richiesta richiesta = new Richiesta();
		richiesta.setEmail(richiestaForm.getEmail());
		richiesta.setDescrizione(richiestaForm.getDescrizione());
		richiesta.aggiungiFoto(fotoSelezionate.values());
		this.richiestaService.salvaRichiesta(richiesta);
		session.removeAttribute("fotoSelezionate"); //rimuovo dalla sessione perche non mi servono piu
		return "redirect:/";
	}
}
