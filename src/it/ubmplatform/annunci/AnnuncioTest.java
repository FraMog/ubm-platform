package it.ubmplatform.annunci;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.After;
import org.junit.Before;
import java.sql.Date;
import it.ubmplatform.annunci.Annuncio;
import it.ubmplatform.eccezioni.BadInputAnnuncioException;
public class AnnuncioTest {
	private Annuncio annuncio;
	
	@Before
	public void setUp(){
		//testo i costruttori
		try{
			//dovrebbe lanciare l'eccezione per edizione negativa
			annuncio = new Annuncio (1, "Vendo Libro IS", "Libro", "Informatica", "img.jpg", "1234", "Pearson", -2, "Ingegneria software", "Buone", "Libro usato", 15.00, "alfonso@studenti.unisa.it", new Date(0));
			fail("Dovrebbe lanciare l'eccezione per edizione negativa!");
		}catch(BadInputAnnuncioException e){
			assertEquals(null, annuncio);				
		}
		try{
			//dovrebbe lanciare l'eccezione per prezzo negativo
			annuncio = new Annuncio (1, "Vendo Libro IS", "Libro", "Informatica", "img.jpg", "1234", "Pearson", 2, "Ingegneria software", "Buone", "Libro usato", -15.00, "alfonso@studenti.unisa.it", new Date(0));
			fail("Dovrebbe lanciare l'eccezione per prezzo negativo!");
		}catch(BadInputAnnuncioException e){
			assertEquals(null, annuncio);				
		}
		try{
			//dovrebbe lanciare l'eccezione per email errata
			annuncio = new Annuncio (1, "Vendo Libro IS", "Libro", "Informatica", "img.jpg", "1234", "Pearson", 2, "Ingegneria software", "Buone", "Libro usato", 15.00, "alfonso@yahoo.it", new Date(0));
			fail("Dovrebbe lanciare l'eccezione per email errata!");
		}catch(BadInputAnnuncioException e){
			assertEquals(null, annuncio);				
		}
		try{
			//non dovrebbe dare eccezioni
			annuncio = new Annuncio (1, "Vendo Libro IS", "Libro", "Informatica", "img.jpg", "1234", "Pearson", 2, "Ingegneria software", "Buone", "Libro usato", 15.00, "alfonso@studenti.unisa.it", new Date(0));
		}catch(BadInputAnnuncioException e){
			fail("Non dovrebbe lanciare l'eccezione");			
		}
	}
	
	@After
	public void tearDown() throws Exception {
		annuncio = null;
	}
	
	@Test
	public void testGetFoto() {
		String foto = annuncio.getFoto();
		assertEquals("img.jpg", foto);
	}
	@Test
	public void testGetId() {
		int id = annuncio.getId();
		assertEquals(1, id);
	}
	@Test
	public void testGetEdizione() {
		int edizione = annuncio.getEdizione();
		assertEquals(2, edizione);
	}
	@Test
	public void testGetEmail() {
		String email = annuncio.getEmail();
		assertEquals("alfonso@studenti.unisa.it", email);
	}
	@Test
	public void testGetTitolo() {
		String titolo = annuncio.getTitolo();
		assertEquals("Vendo Libro IS", titolo);
	}
	@Test
	public void testGetDescrizione() {
		String descrizione = annuncio.getDescrizione();
		assertEquals("Libro usato", descrizione);
	}
	@Test
	public void testGetCondizioni() {
		String condizioni = annuncio.getCondizioni();
		assertEquals("Buone", condizioni);
	}
	@Test
	public void testGetFacolta() {
		String facolta = annuncio.getFacolta();
		assertEquals("Informatica", facolta);
	}
	@Test
	public void testGetAutoreLibro() {
		String autoreLibro = annuncio.getAutoreLibro();
		assertEquals("Pearson", autoreLibro);
	}
	@Test
	public void testGetDataPubblicazione() {
		Date dataPubblicazione = annuncio.getDataPubblicazione();
		assertEquals(new Date(0), dataPubblicazione);
	}
	@Test
	public void testGetIsbn() {
		String isbn = annuncio.getIsbn();
		assertEquals("1234", isbn);
	}
	@Test
	public void testGetMateria() {
		String materia = annuncio.getMateria();
		assertEquals("Ingegneria software", materia);
	}
	@Test
	public void testGetPrezzo() {
		double prezzo = annuncio.getPrezzo();
		assertEquals(15.00, prezzo, 0.1);
	}
	@Test
	public void testGetCategoria() {
		String categoria = annuncio.getCategoria();
		assertEquals("Libro", categoria);
	}
	@Test
	public void testSetFoto() {
		String foto = "img.jpg";
		annuncio.setFoto(foto);
		assertEquals("img.jpg", annuncio.getFoto());
	}
	@Test
	public void testSetEdizione() {
		int edizione = 2;
		annuncio.setEdizione(edizione);
		assertEquals(2, annuncio.getEdizione());
	}
	@Test
	public void testSetTitolo() {
		String titolo = "Vendo libro IS";
		annuncio.setTitolo(titolo);
		assertEquals("Vendo libro IS", annuncio.getTitolo());
	}
	@Test
	public void testSetDescrizione() {
		String descrizione = "Libro usato";
		annuncio.setDescrizione(descrizione);
		assertEquals("Libro usato", annuncio.getDescrizione());
	}
	@Test
	public void testSetCondizioni() {
		String condizioni = "Buone";
		annuncio.setCondizioni(condizioni);
		assertEquals("Buone", annuncio.getCondizioni());
	}
	@Test
	public void testSetFacolta() {
		String facolta = "Informatica";
		annuncio.setFacolta(facolta);
		assertEquals("Informatica", annuncio.getFacolta());
	}
	@Test
	public void testSetCategoria() {
		String categoria = "Libro";
		annuncio.setCategoria(categoria);
		assertEquals("Libro", annuncio.getCategoria());
	}
	@Test
	public void testSetAutoreLibro() {
		String autoreLibro = "Pearson";
		annuncio.setAutoreLibro(autoreLibro);
		assertEquals("Pearson", annuncio.getAutoreLibro());
	}
	@Test
	public void testSetIsbn() {
		String isbn = "1234";
		annuncio.setIsbn(isbn);
		assertEquals("1234", annuncio.getIsbn());
	}
	@Test
	public void testSetMateria() {
		String materia = "Ingegneria software";
		annuncio.setMateria(materia);
		assertEquals("Ingegneria software", annuncio.getMateria());
	}
	@Test
	public void testSetPrezzo() {
		double prezzo = 15.00;
		annuncio.setPrezzo(prezzo);
		assertEquals(15.00, annuncio.getPrezzo(), 0.1);
	}
	@Test
	public void testSetId() {
		int id = 1;
		annuncio.setId(id);
		assertEquals(1, annuncio.getId());
	}
	@Test
	public void testSetDataPubblicazione() {
		Date dataPubblicazione = new Date(0);
		annuncio.setDataPubblicazione(dataPubblicazione);
		assertEquals(new Date(0), annuncio.getDataPubblicazione());
	}
	@Test
	public void testSetEmail() {
		String email = "alfonso@studenti.unisa.it";
		annuncio.setEmail(email);
		assertEquals("alfonso@studenti.unisa.it", annuncio.getEmail());
	}
}