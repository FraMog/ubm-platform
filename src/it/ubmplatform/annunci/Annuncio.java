package it.ubmplatform.annunci;

import java.sql.Date;

/**
 * Bean Annuncio che rappresenta l'annuncio nel sistema
 * @author Marco
 *
 */
public class Annuncio {
	
	/**
	 * Crea un annuncio con le relative informazioni
	 * @param foto La foto dell'annuncio
	 * @param edizione L'edizione del prodotto (libro) a cui è riferito l'annuncio
	 * @param email L'email di chi ha pubblicato l'annuncio
	 * @param titolo Il titolo dell'annuncio
	 * @param descrizione La descrizione del prodotto a cui è riferito l'annuncio
	 * @param condizioni Lo stato del prodotto a cui è riferito l'annuncio
	 * @param facolta La facoltà alla quale fa riferimento l'annuncio
	 * @param categoria La categoria dell'annuncio (libro o appunti)
	 * @param autoreLibro Eventualmente, l'autore del libro a cui è riferito l'annuncio, null se non è presente/conosciuto
	 * @param isbn Eventualmente, l'isbn del libro a cui è riferito l'annuncio, null se non è presente/conosciuto
	 * @param materia La materia a cui è riferito il prodotto dell'annuncio
	 * @param prezzo Il prezzo del prodotto a cui è riferito l'annuncio
	 */
	public Annuncio(int id, String titolo, String categoria, String facolta, String foto, String isbn, String autoreLibro, int edizione, String materia, String condizioni, String descrizione, double prezzo, String email, Date dataPubblicazione) {
		this.foto = foto;
		this.edizione = edizione;
		this.email = email;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.facolta = facolta;
		this.condizioni = condizioni;
		this.categoria = categoria;
		this.autoreLibro = autoreLibro;
		this.isbn = isbn;
		this.materia = materia;
		this.prezzo = prezzo;
		this.dataPubblicazione= dataPubblicazione;
		
		//DATA DA SETTARE ---------
	}
	

	/**
	 * Crea un annuncio
	 */
	public Annuncio() {
	}
	String getFoto() {
		return foto;
	}
	int getId() {
		return id;
	}
	int getEdizione() {
		return edizione;
	}
	String getEmail() {
		return email;
	}
	String getTitolo() {
		return titolo;
	}
	String getDescrizione() {
		return descrizione;
	}
	String getCondizioni() {
		return condizioni;
	}
	String getFacolta() {
		return facolta;
	}
	String getAutoreLibro() {
		return autoreLibro;
	}
	Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	String getIsbn() {
		return isbn;
	}
	String getMateria() {
		return materia;
	}
	
	double getPrezzo() {
		return prezzo;
	}
	
	void setFoto(String foto) {
		this.foto = foto;
	}
	void setEdizione(int edizione) {
		this.edizione = edizione;
	}
	void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	void setCondizioni(String condizioni) {
		this.condizioni = condizioni;
	}
	void setFacolta(String facolta) {
		this.facolta = facolta;
	}
	void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	void setAutoreLibro(String autoreLibro) {
		this.autoreLibro = autoreLibro;
	}
	void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	void setMateria(String materia) {
		this.materia = materia;
	}
	void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	void setId(int id){
		this.id=id;
	}
	void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	


	private String foto;
	private int id, edizione;
	private String email, titolo, descrizione, condizioni, facolta, categoria, autoreLibro, isbn, materia;
	private double prezzo;
	private Date dataPubblicazione;
}
