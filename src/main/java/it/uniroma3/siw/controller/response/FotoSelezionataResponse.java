package it.uniroma3.siw.controller.response;

import it.uniroma3.siw.model.Foto;

public class FotoSelezionataResponse {

	private String id;
	private String nome;
	private String descrizione;
	private String imageType;
	private String filePath;
	private long album_id;
	private long fotografo_id;
	
	/* METODI GETTERS & SETTERS */
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(long album_id) {
		this.album_id = album_id;
	}
	public long getFotografo_id() {
		return fotografo_id;
	}
	public void setFotografo_id(long fotografo_id) {
		this.fotografo_id = fotografo_id;
	}
	
	/* METODI DI SERVIZIO */
	
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
		FotoSelezionataResponse other = (FotoSelezionataResponse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "FotoSelezionataResponse [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", imageType="
				+ imageType + ", filePath=" + filePath + ", album_id=" + album_id + ", fotografo_id=" + fotografo_id
				+ "]";
	}
	
	/* COSTRUTTORI */
	
	public FotoSelezionataResponse() {
		
	}
	
	public FotoSelezionataResponse(String id, String nome, String descrizione, String imageType, String filePath,
			long album_id, long fotografo_id) {
		this();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.imageType = imageType;
		this.filePath = filePath;
		this.album_id = album_id;
		this.fotografo_id = fotografo_id;
	}
	
	public FotoSelezionataResponse(Foto foto) {
		this();
		this.id = foto.getId();
		this.nome = foto.getNome();
		this.descrizione = foto.getDescrizione();
		this.imageType = foto.getFilePath();
		this.filePath = foto.getFilePath();
		if(foto.getAlbum()!= null)
			this.album_id = foto.getAlbum().getId();
		if(foto.getFotografo()!= null)
			this.fotografo_id = foto.getFotografo().getId();
	}	
	
}
