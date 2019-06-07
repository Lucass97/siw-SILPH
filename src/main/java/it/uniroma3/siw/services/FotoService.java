package it.uniroma3.siw.services;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.controller.storage.StorageException;
import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.repository.FotoRepository;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Transactional
	public Foto salvaFoto(Foto foto) {
		return this.fotoRepository.save(foto);
	}
	
	@Transactional
	public Foto getFotoById(long id) {
		Optional<Foto> optionalFoto = this.fotoRepository.findById(id);
		try {
			return optionalFoto.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Transactional
	public boolean alreadyExists(Foto foto) {
		return this.fotoRepository.existsById(foto.getId());
	}
	
	public Foto storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new StorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Foto foto = new Foto();
            foto.setNome("ienna");
            foto.setDescrizione("ienna");

            return fotoRepository.save(foto);
        } catch (Exception ex) {
            throw new StorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
