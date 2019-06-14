package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.model.Funzionario;

public interface FunzionarioRepository extends JpaRepository<Funzionario, String> {

	public Funzionario findByUsername(String username);
}
