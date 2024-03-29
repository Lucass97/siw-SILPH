package it.uniroma3.siw.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/richieste")
	public String getRichieste(Model model){
		model.addAttribute("richieste", richiestaService.getRandomRichieste(5));
		return "listaRichieste.html";
	}
	
	@GetMapping("/richiesta/{id}")
	public String getRichiesta(@PathVariable("id") long id, Model model){
		Richiesta richiesta = this.richiestaService.getRichiestaById(id);
		if(richiesta == null)
			return "redirect:/richieste";
		model.addAttribute("richiesta", richiesta);
		return "richiestaDettagli.html";
	}	
	
	@PostMapping(value = "/cercaRichieste")
	public String cercaRichieste(Model model, @RequestParam("parametro") String parametro) {
		model.addAttribute("richieste",this.richiestaService.effettuaRicercaPerParametro(parametro));
		return "listaRichieste.html";
	}
	
}
