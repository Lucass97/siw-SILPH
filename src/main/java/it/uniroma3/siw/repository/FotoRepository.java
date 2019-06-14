package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Foto;

public interface FotoRepository  extends CrudRepository<Foto, String>{

	@Query(value = "SELECT * FROM Foto ORDER BY RANDOM() LIMIT ?1", nativeQuery = true)
	List<Foto> getRandomFoto(int limit);
}
