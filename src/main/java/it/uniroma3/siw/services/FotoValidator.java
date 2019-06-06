package it.uniroma3.siw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Foto;

public class FotoValidator implements Validator{
	
	@Autowired
	private FotoService fotoService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Foto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		
		if(this.fotoService.alreadyExists((Foto) o)){
			errors.reject("duplicato");
		}
		
	}

}
