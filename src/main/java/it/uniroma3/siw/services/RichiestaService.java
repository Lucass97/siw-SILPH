package it.uniroma3.siw.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Foto;
import it.uniroma3.siw.model.Richiesta;
import it.uniroma3.siw.repository.RichiestaRepository;

@Service
public class RichiestaService {
	 
		@Autowired
	    private RichiestaRepository richiestaRepository;
		
		@Transactional
		public Richiesta salvaRichiesta(Richiesta richiesta) {
			return this.richiestaRepository.save(richiesta);
		}
		
		@Transactional
		public Richiesta getRichiestaById(long id) {
			Optional<Richiesta> optionalRichiesta = this.richiestaRepository.findById(id);
			try {
				return optionalRichiesta.get();
			} catch (NoSuchElementException e) {
				return null;
			}
		}
		
		public List<Richiesta> getRandomRichieste(int limit) {
			return richiestaRepository.getRandomRichieste(limit);
		}
		
		@Transactional
		public List<Richiesta> tutti() {
			return richiestaRepository.findAll();
		}
		
		@Transactional
		public List<Richiesta> effettuRicercaPerParametro(String parametro) {
			return (List<Richiesta>) this.richiestaRepository.ricercaRichiesteByParametro(parametro);
		}
		
		@Transactional
		public void deleteFotoDaTutteLeRichieste(Foto foto){
			List<Richiesta> richieste = richiestaRepository.ricercaRichiestaByIdFoto(foto.getId());
			for(Richiesta richiesta : richieste) {
				richiesta.rimuoviSingolaFoto(foto);
				this.richiestaRepository.save(richiesta);
			}
		}
}
