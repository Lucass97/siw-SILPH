package it.uniroma3.siw.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Richiesta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String email;
	private String descrizione;
	
	@ManyToMany
	private List<Foto> foto;
	
	/* METODI GETTERS & SETTERS */
	
	public long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public List<Foto> getFoto() {
		return foto;
	}
	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}
	
	/* METODI DI SERVIZIO */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Richiesta other = (Richiesta) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Richiesta [id=" + id + ", email=" + email + ", descrizione=" + descrizione + "]";
	}
	
	/* METODI */
	
	public boolean aggiungiSingolaFoto(Foto foto) {
		return this.foto.add(foto);
	}
	
	public boolean aggiungiFoto(Collection<Foto> foto) {
		return this.foto.addAll(foto);
	}
	
	/* COSTRUTTORI */
	public Richiesta() {
		this.foto = new LinkedList<Foto>();
	}
	
	public Richiesta(String email, String descrizione, List<Foto> foto) {
		this();
		this.email = email;
		this.descrizione = descrizione;
		this.foto = foto;
	}
	
	
	
}
