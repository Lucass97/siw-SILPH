package it.uniroma3.siw.controller;

public class FotoForm {
	
	private String nome;
	private String descrizione;
	private long album_id;
	
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
	
	/* COSTRUTTORI */
	
	public FotoForm() {
	}
	
	public FotoForm(String nome, String descrizione, long album_id) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.album_id = album_id;
	}
	
}