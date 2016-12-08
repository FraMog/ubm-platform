package it.ubmplatform.annunci;

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
	 * @param nomeProdotto Il nome del prodotto a cui è riferito l'annuncio
	 * @param descrizioneProdotto La descrizione del prodotto a cui è riferito l'annuncio
	 * @param statoProdotto Lo stato del prodotto a cui è riferito l'annuncio
	 * @param titoloAnnuncio Il titolo dell'annuncio
	 * @param autoreLibro Eventualmente, l'autore del libro a cui è riferito l'annuncio, null se non è presente/conosciuto
	 * @param isbn Eventualmente, l'isbn del libro a cui è riferito l'annuncio, null se non è presente/conosciuto
	 * @param materia La materia a cui è riferito il prodotto dell'annuncio
	 * @param prezzo Il prezzo del prodotto a cui è riferito l'annuncio
	 */
	public Annuncio(String foto, int edizione, String email, String nomeProdotto, String descrizioneProdotto,
			String statoProdotto, String titoloAnnuncio, String autoreLibro, String isbn, String materia,
			float prezzo) {
		this.foto = foto;
		this.edizione = edizione;
		this.email = email;
		this.nomeProdotto = nomeProdotto;
		this.descrizioneProdotto = descrizioneProdotto;
		this.statoProdotto = statoProdotto;
		this.titoloAnnuncio = titoloAnnuncio;
		this.autoreLibro = autoreLibro;
		this.isbn = isbn;
		this.materia = materia;
		this.prezzo = prezzo;
		
		//DATA DA SETTARE ---------
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
	String getNomeProdotto() {
		return nomeProdotto;
	}
	String getDescrizioneProdotto() {
		return descrizioneProdotto;
	}
	String getStatoProdotto() {
		return statoProdotto;
	}
	String getTitoloAnnuncio() {
		return titoloAnnuncio;
	}
	String getAutoreLibro() {
		return autoreLibro;
	}
	String getData() {
		return data;
	}
	String getIsbn() {
		return isbn;
	}
	String getMateria() {
		return materia;
	}
	
	float getPrezzo() {
		return prezzo;
	}
	
	void setFoto(String foto) {
		this.foto = foto;
	}
	void setEdizione(int edizione) {
		this.edizione = edizione;
	}
	void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
	void setDescrizioneProdotto(String descrizioneProdotto) {
		this.descrizioneProdotto = descrizioneProdotto;
	}
	void setStatoProdotto(String statoProdotto) {
		this.statoProdotto = statoProdotto;
	}
	void setTitoloAnnuncio(String titoloAnnuncio) {
		this.titoloAnnuncio = titoloAnnuncio;
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
	void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}



	/**
	 * Foto dell'annuncio
	 */
	private String foto;
	
	private int id, edizione;
	private String email, nomeProdotto, descrizioneProdotto, statoProdotto, titoloAnnuncio, autoreLibro, data, isbn, materia;
	private float prezzo;
	
}
