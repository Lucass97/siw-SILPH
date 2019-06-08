package it.uniroma3.siw.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String titolo;
	
	@OneToMany(cascade = {CascadeType.PERSIST})
	private List<Foto> foto;

	/* METODI GETTERS & SETTERS */
	
	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public List<Foto> getFoto() {
		return foto;
	}

	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}

	public long getId() {
		return id;
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
		Album other = (Album) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", titolo=" + titolo + "]";
	}
	
	/* METODI */
	
	public boolean aggiungiSingolaFoto(Foto foto) {
		return this.foto.add(foto);
	}
	
	public boolean aggiungiFoto(Collection<Foto> foto) {
		return this.foto.addAll(foto);
	}
	
	
	/* COSTRUTTORI */
	
	public Album (){
		this.foto = new LinkedList<Foto>();
	}
	
	
}