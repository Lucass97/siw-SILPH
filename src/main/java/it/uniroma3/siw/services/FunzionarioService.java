package it.uniroma3.siw.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Funzionario;
import it.uniroma3.siw.repository.FunzionarioRepository;

@Service
public class FunzionarioService{
   
	@Autowired
    private FunzionarioRepository funzionarioRepository;
 
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void save(Funzionario funzionario) {
        funzionario.setPassword(bCryptPasswordEncoder.encode(funzionario.getPassword()));
        funzionarioRepository.save(funzionario);
    }
    
    @Transactional
    public Funzionario findByUsername(String username) {
        return funzionarioRepository.findByUsername(username);
    }
    
    @Transactional
    public boolean esiste(String username) {
    	Funzionario funzionarioTrovato = this.funzionarioRepository.findByUsername(username);
    	if(funzionarioTrovato==null)
    		return false;
    	return true;
    }
}