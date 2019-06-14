package it.uniroma3.siw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Foto {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String imageType;

	private String nome;

	private String descrizione;

	@ManyToOne
	@JoinColumn(name = "album_id")
	private Album album;

	/*METODI GETTER E SETTER*/
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}


	public String getId() {
		return id;
	}
	
	/*METODI GETTER E SETTER*/
	
	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Foto other = (Foto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Foto [id=" + id + ", imageType=" + imageType + ", nome=" + nome + ", descrizione=" + descrizione + "]";
	}
	
	/* COSTRUTTORI */

	public Foto() {

	}

	public Foto(String id, String nome, String descrizione, Album album, String imageType) {
		this();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.album = album;
		this.imageType = imageType;
	}

}
