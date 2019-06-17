package it.uniroma3.siw.services.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.services.AlbumService;

@Component
public class AlbumValidator implements Validator{

	@Autowired
	private AlbumService albumService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Album.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {

		Album album = (Album) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		
	}

}
