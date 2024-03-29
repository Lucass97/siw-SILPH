package it.uniroma3.siw.services.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.controller.form.FotoForm;
import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.services.ContentType;
import it.uniroma3.siw.services.FotografoService;

@Component 
public class FotoValidator implements Validator{
	
	@Autowired
	private FotografoService fotografoService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Foto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {

		FotoForm fotoForm = (FotoForm) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parametroFotografo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "album_id", "required");
		
		if(fotoForm.getFileImage().isEmpty())
			errors.rejectValue("fileImage", "required");
		if(!this.fotografoService.esisteFotografo(fotoForm.getParametroFotografo()))
			errors.rejectValue("parametroFotografo", "non_esiste_fotografo");
		/*if(!this.albumService.alreadyExistsById(fotoForm.getAlbum_id()))
			errors.rejectValue("album_id", "non_esiste_album");*/
		if(ContentType.contentTypeToExtension(fotoForm.getFileImage().getContentType())==null && errors.getFieldErrorCount("fileImage") == 0)
			errors.rejectValue("fileImage", "file_non_supportato");
		
	}

}
