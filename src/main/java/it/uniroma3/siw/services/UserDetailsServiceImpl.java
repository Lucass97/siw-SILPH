package it.uniroma3.siw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Funzionario;
import it.uniroma3.siw.repository.FunzionarioRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private FunzionarioRepository funzionarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
    	Funzionario funzionario = funzionarioRepository.findByUsername(username);
        if (funzionario == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
           grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
           
           System.out.println(funzionario.getPassword());

        return new org.springframework.security.core.userdetails.User(funzionario.getUsername(), funzionario.getPassword(), grantedAuthorities);
    }
}