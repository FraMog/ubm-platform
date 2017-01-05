package it.ubmplatform.annunci;

import java.sql.Date;

import it.ubmplatform.eccezioni.BadInputAnnuncioException;

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
	 * @param dataPubblicazione La data di pubblicazione dell'annuncio
	 */
	public Annuncio(int id, String titolo, String categoria, String facolta, String foto, String isbn, String autoreLibro, int edizione, String materia, String condizioni, String descrizione, double prezzo, String email, Date dataPubblicazione) throws BadInputAnnuncioException {
		if (edizione < 0 || prezzo < 0 || email.indexOf("unisa.it") == - 1) {
			throw new BadInputAnnuncioException();
		}
		else {
		this.id=id;
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
		}
	}
	

	/**
	 * Crea un annuncio
	 */
	public Annuncio() {
	}
	public String getFoto() {
		return foto;
	}
	public int getId() {
		return id;
	}
	public int getEdizione() {
		return edizione;
	}
	public String getEmail() {
		return email;
	}
	public String getTitolo() {
		return titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public String getCondizioni() {
		return condizioni;
	}
	public String getFacolta() {
		return facolta;
	}
	public String getAutoreLibro() {
		return autoreLibro;
	}
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getMateria() {
		return materia;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public String getCategoria(){
		return categoria;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	void setEdizione(int edizione) {
		this.edizione = edizione;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setCondizioni(String condizioni) {
		this.condizioni = condizioni;
	}
	public void setFacolta(String facolta) {
		this.facolta = facolta;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setAutoreLibro(String autoreLibro) {
		this.autoreLibro = autoreLibro;
	}
	public  void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	private String foto;
	private int id, edizione;
	private String email, titolo, descrizione, condizioni, facolta, categoria, autoreLibro, isbn, materia;
	private double prezzo;
	private Date dataPubblicazione;
}
