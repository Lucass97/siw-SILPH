package it.uniroma3.siw.controller;

import org.springframework.web.multipart.MultipartFile;

public class FotoForm {
	
	private String nome;
	private String descrizione;
	private long album_id;
	private long fotografo_id;
	private MultipartFile fileImage;
	
	/* METODI GETTERS & SETTERS */
	
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
	public MultipartFile getFileImage() {
		return fileImage;
	}
	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}
	
	/* COSTRUTTORI */
	
	public FotoForm() {
	}
	
	public FotoForm(String nome, String descrizione, long album_id, long fotografo_id, MultipartFile fileImage) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.album_id = album_id;
		this.fotografo_id = fotografo_id;
		this.fileImage = fileImage;
	}
	
}
