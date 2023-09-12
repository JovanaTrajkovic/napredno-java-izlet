package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja jednodnevne izlete na koje grupe idu. Ima identifikator,cenu
 * izleta,kategoriju kojoj izlet pripada, i mesto na koje se ide.
 *
 * Nasledjuje apstraktnu domensku klasu OpstiDomenskiObjekat i implementira sve
 * njene apstraktne metode.
 * 
 * @author Jovana
 */
public class Izlet extends OpstiDomenskiObjekat {

	/**
	 * Identifikator izleta
	 */
	private Long izletID;
	/**
	 * Cena izleta
	 */
	private int cena;
	/**
	 * Kategorija izleta
	 */
	private Kategorija kategorija;
	/**
	 * Mesto izleta
	 */
	private Mesto mesto;

	/**
	 * Neparametrizovani konstruktor koji pravi novi prazni izlet.
	 */
	public Izlet() {
	}

	/**
	 * 
	 * Parametrizovani konstruktor koji inicijalizuje izlet i postavlja mu
	 * prosledjene vrednosti.
	 * 
	 * @param izletID    id izleta kao Long
	 * @param cena       cena izleta kao ceo broj
	 * @param kategorija kategorija izleta kao enum
	 * @param mesto      mesto izleta kao Mesto
	 */
	public Izlet(Long izletID, int cena, Kategorija kategorija, Mesto mesto) {
		setIzletID(izletID);
		setCena(cena);
		setKategorija(kategorija);
		setMesto(mesto);
	}

	/**
	 * Vraca kategoriju izleta .
	 * 
	 * @return kategorija izleta kao enum Kategorija
	 */
	public Kategorija getKategorija() {
		return kategorija;
	}

	/**
	 * Postavlja vrednost za kategoriju izleta.
	 * 
	 * @param kategorijaa nova vrednost za kategoriju izleta.
	 */
	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	/**
	 * Vraca String sa mestom i cenom izleta.
	 * 
	 * @return mesto i cena izleta kao String
	 */
	@Override
	public String toString() {
		return mesto.getNazivMesta() + ", Cena: " + cena + "dinara)";
	}

	@Override
	public String nazivTabele() {
		return " izlet ";
	}

	@Override
	public String alijas() {
		return " i";
	}

	@Override
	public String join() {
		return " JOIN mesto m  ON (m.mestoID= i.mestoID) ";
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {

			Mesto m = new Mesto(rs.getLong("mestoID"), rs.getString("nazivMesta"));

			Izlet i = new Izlet(rs.getLong("izletID"), rs.getInt("cena"),
					Kategorija.valueOf(rs.getString("kategorija")), m);

			lista.add(i);
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
		return " izletID = " + izletID;
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

	/**
	 * Vraca identifikator izleta.
	 * 
	 * @return izletID kao Long
	 */
	public Long getIzletID() {
		return izletID;
	}

	/**
	 * Postavlja vrednost za identifikator izleta.
	 * 
	 * @param password nova vrednost za identifikator izleta
	 * 
	 */
	public void setIzletID(Long izletID) {
		this.izletID = izletID;
	}

	/**
	 * Vraca cenu izleta.
	 * 
	 * @return cena kao double.
	 */
	public double getCena() {
		return cena;
	}

	/**
	 * Postavlja vrednost za cenu izleta.
	 * 
	 * @param cena nova vrednost za cenu izleta
	 * @throws IllegalArgumentException ako je uneta cena manja od 100.
	 * 
	 */
	public void setCena(int cena) {
		if (cena < 1000) {
			throw new IllegalArgumentException("Cena mora biti veca od 1000");
		}
		this.cena = cena;
	}

	/**
	 * Vraca mesto izleta.
	 * 
	 * @return mesto kao Mesto.
	 */
	public Mesto getMesto() {
		return mesto;
	}

	/**
	 * Postavlja vrednost za mesto izleta.
	 * 
	 * @param mesto nova vrednost za mesto izleta
	 * 
	 */
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
}
