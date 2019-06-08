package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.services.ContentType;
import it.uniroma3.siw.services.FotoService;
import it.uniroma3.siw.storage.StorageService;

@Controller
public class FotoController {

	@Autowired
	private StorageService storageService;

	@Autowired
	private FotoService fotoService;

	@RequestMapping("/inserisciFoto")
	public String addFoto(Model model) {
		model.addAttribute("foto",new Foto());
		return "fotoForm.html";
	}

	@RequestMapping(value = "/foto/{id}" , method = RequestMethod.GET)
	public String getFoto(@PathVariable("id") String id, Model model) {
		Foto foto = this.fotoService.getFotoById(id);
		if(foto == null)
			return "redirect:/tuamadre.html";
		model.addAttribute("foto",foto);
		model.addAttribute("fileExtension",ContentType.contentTypeToExtension(foto.getImageType()));
		return "foto.html";
	}
	
	@RequestMapping(value = "/cancellaFoto/{id}" , method = RequestMethod.GET)
	public String deleteFoto(@PathVariable("id") String id, Model model) {
		Foto foto = this.fotoService.getFotoById(id);
		if(foto == null)
			return "redirect:/tuamadre.html";
		String fileNameDotExtension = id + ContentType.contentTypeToExtension(foto.getImageType());
		this.storageService.delete(fileNameDotExtension);
		this.fotoService.deleteFotoById(id);
		return "redirect:/index.html";
	}

	@RequestMapping(value="/salvaFoto",method = RequestMethod.POST)
	public String newFoto(@Valid @ModelAttribute("foto") Foto foto, Model model,BindingResult bindingResult, 
			@RequestParam("file") MultipartFile fileImage) {
		if(!bindingResult.hasErrors()) {//in caso non ci siano errori
			foto.setImageType(fileImage.getContentType());
			this.fotoService.salvaFoto(foto); //esegui il persistence
			this.storageService.store(fileImage,foto.getId());
			return "redirect:/foto/" + foto.getId();
		}
		return "redirect:/index.html";
	}

}
