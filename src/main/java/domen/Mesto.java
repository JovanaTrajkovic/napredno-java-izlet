package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Predstavlja mesto izleta. Ima identifikator i naziv mesta
 * 
 * Nasledjuje apstraktnu domensku klasu OpstiDomenskiObjekat i implementira sve
 * njene apstraktne metode.
 * 
 * @author Jovana
 */
public class Mesto extends OpstiDomenskiObjekat {
	/**
	 * Identifikator mesta
	 */
	private Long mestoID;
	
	/**
	 * Naziv mesta
	 */
	private String nazivMesta;
	
	
	/**
	 * Neparametrizovani konstruktor koji pravi novo prazno mesto.
	 * 
	 */
	public Mesto() {
	}

	/**
	 * Parametrizovani konstruktor koji inicijalizuje mesto i postavlja mu
	 * prosledjene vrednosti.
	 * 
	 * @param mestoID  Id mesta kao Long
	 * @param nazivMesta  naziv mesta kao String.
	 */
	public Mesto(Long mestoID, String nazivMesta) {
		setMestoID(mestoID);
		setNazivMesta(nazivMesta);
	}

	/**
	 * Vraca identifikator mesta
	 * 
	 * @return identifikator  mesta kao Long
	 */
	public Long getMestoID() {
		return mestoID;
	}

	/**
	 * Postavlja vrednost za identifikator mesta.
	 * 
	 * @param mestoID nova vrednost za identifikator mesta
	 */
	public void setMestoID(Long mestoID) {
		this.mestoID = mestoID;
	}

	
	/**
	 * Vraca naziv mesta
	 * 
	 * @return nazivMesta  kao String
	 */
	public String getNazivMesta() {
		return nazivMesta;
	}

	/**
	 * Postavlja vrednost za naziv mesta.
	 * 
	 * @param mestoID nova vrednost za naziv mesta
	 * * @throws NullPointerException     ako je naziv mesta null
	 * @throws IllegalArgumentException ako naziv mesta ima manje od 3 karaktera
	 */
	public void setNazivMesta(String nazivMesta) {
		if (nazivMesta == null)
			throw new NullPointerException();
		if (nazivMesta.length() < 3)
			throw new IllegalArgumentException("Naziv mesta  mora biti duzi od 2 karaktera.");
		this.nazivMesta = nazivMesta;
	}

	
	/**
	 * Vraca String sa nazivom mesta.
	 * 
	 * @return nazivMesta kao String
	 */
	@Override
	public String toString() {
		return nazivMesta;
	}

	@Override
	public String nazivTabele() {
		return " Mesto ";
	}

	@Override
	public String alijas() {
		return " m";
	}

	@Override
	public String join() {
		return "";
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			Mesto m = new Mesto(rs.getLong("mestoID"), rs.getString("nazivMesta"));
			lista.add(m);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		return "";
	}

	@Override
	public String vrednostZaPrimarniKljuc() {
		return " mestoID = " + mestoID;
	}

	@Override
	public String vrednostiZaInsert() {
		return "";
	}

	@Override
	public String vrednostiZaUpdate() {
		return "";
	}

	@Override
	public String uslov() {
		return "";
	}

}
