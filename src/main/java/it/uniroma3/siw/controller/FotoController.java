package it.uniroma3.siw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.services.FotoService;
import it.uniroma3.siw.services.FotoValidator;
import it.uniroma3.siw.storage.StorageService;

@Controller
public class FotoController {

	@Autowired
	private StorageService storageService;

	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private FotoValidator fotoValidator;
	
	@RequestMapping("/inserisciFoto")
	public String addFoto(Model model) {
		System.out.println(this.fotoService.getRandomFoto(3));
		model.addAttribute("fotoForm",new FotoForm());
		return "fotoForm.html";
	}

	@RequestMapping(value = "/foto/{id}" , method = RequestMethod.GET)
	public String getFoto(@PathVariable("id") String id, Model model) {
		Foto foto = this.fotoService.getFotoById(id);
		if(foto == null)
			return "redirect:/tuamadre.html";
		model.addAttribute("foto",foto);
		model.addAttribute("filename",this.fotoService.generaNomeFile(foto));
		return "foto.html";
	}
	
	@RequestMapping(value = "/cancellaFoto/{id}" , method = RequestMethod.GET)
	public String deleteFoto(@PathVariable("id") String id, Model model) {
		Foto foto = this.fotoService.getFotoById(id);
		if(foto == null)
			return "redirect:/index.html";
		Album album = foto.getAlbum();
		this.storageService.delete(this.fotoService.generaNomeFile(foto));
		this.fotoService.deleteFotoById(id);
		return "redirect:/album/" + album.getId();
	}

	@RequestMapping(value="/salvaFoto",method = RequestMethod.POST)
	public String newFoto(@Valid @ModelAttribute("fotoForm") FotoForm fotoForm, 
			Model model,BindingResult bindingResult) {
		this.fotoValidator.validate(fotoForm, bindingResult);
		if(!bindingResult.hasErrors()) {//in caso non ci siano errori
			Foto foto = new Foto();
			foto.setNome(fotoForm.getNome());
			foto.setDescrizione(fotoForm.getDescrizione());
			foto.setImageType(fotoForm.getFileImage().getContentType());
			this.fotoService.salvaFoto(foto,fotoForm.getAlbum_id()); //esegui il persistence
			this.storageService.store(fotoForm.getFileImage(),fotoService.generaNomeFile(foto)); //salva immagine
			return "redirect:/foto/" + foto.getId();
		}
		return "fotoForm.html";
	}
	
	@RequestMapping(value = "/fotoCasuali/{limit}" , method = RequestMethod.GET)
	public String getFotoCasuali(@PathVariable("limit") int limit, Model model) {
		System.out.println(limit);
		List<Foto> fotos = this.fotoService.getRandomFoto(limit);
		if(fotos == null || fotos.isEmpty())
			return "redirect:/tuamadre.html";
		model.addAttribute("fotos",fotos);
		return "randomFoto.html";
	}
}
