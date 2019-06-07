package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import it.uniroma3.siw.controller.storage.StorageService;
import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.services.FotoService;

@Controller
public class FotoController {
	
	private final StorageService storageService;

    @Autowired
    public FotoController(StorageService storageService) {
        this.storageService = storageService;
    }
    
	@Autowired
	private FotoService fotoService;

	@RequestMapping("/inserisciFoto")
	public String addFoto(Model model) {
		model.addAttribute("foto",new Foto());
		return "fotoForm.html";
	}

	@RequestMapping(value = "/foto/{id}" , method = RequestMethod.GET)
	public String getFoto(@PathVariable("id") Long id, Model model) {
		Foto foto = this.fotoService.getFotoById(id);
		model.addAttribute("foto",foto);
		String base64EncodedImage = Base64.encodeBase64String(foto.getImage());
		model.addAttribute("base64EncodedImage", base64EncodedImage);
		return "foto.html";
	}

	@RequestMapping(value="/salvaFoto",method = RequestMethod.POST)
	public String newFoto(@Valid @ModelAttribute("foto") Foto foto, Model model,BindingResult bindingResult, @RequestParam("file") MultipartFile file) {
		storageService.store(file);
		if(!bindingResult.hasErrors())//in caso non ci siano errori
			this.fotoService.salvaFoto(foto); //esegui il persist
		return "index.html";
	}
	
	 @PostMapping("/uploadFile")
	    public String uploadFile(@RequestParam("file") MultipartFile file) {
	        Foto foto = fotoService.storeFile(file);

	       

	        return "redirect:/index.html";
	    }

}
