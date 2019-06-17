package it.uniroma3.siw.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.controller.form.FotografoForm;
import it.uniroma3.siw.model.Fotografo;

@Component
public class FotografoValidator implements Validator{

	@Override
	public boolean supports(Class<?> aClass) {
		return Fotografo.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		
		FotografoForm fotografoForm = (FotografoForm) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "required");
		
		try {
			if(!fotografoForm.getDataNascita().isEmpty())
				LocalDate.parse(fotografoForm.getDataNascita(), DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.ITALY));
		} catch (DateTimeParseException e) {
			errors.rejectValue("dataNascita", "data_non_supportata");
		}
		
	}
	

}
