package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja putnika kao clana u grupi. Ima informaciju o grupi u kojoj je
 * clan, redni broj, napomenu u vezi putnika, da li ce putnik ici na dodatne
 * (fakultativne) izlete za taj izlet kao i informacij o putniku koji je clan
 * grupe.
 * 
 *
 * Nasledjuje apstraktnu domensku klasu OpstiDomenskiObjekat i implementira sve
 * njene apstraktne metode.
 * 
 * @author Jovana
 */
public class Clan extends OpstiDomenskiObjekat {
	/**
	 * Grupa u kojoj je putnik clan
	 */
	private Grupa grupa;
	/**
	 * Redni broj clana
	 */
	private int rbClana;
	/**
	 * Napomena vezana za dodatne zahteve putnika koji je clan u grupi
	 */
	private String napomena;
	/**
	 * 
	 * Dodatni izleti,na koje clan bira da li ce ici
	 *
	 */
	private boolean fakultativniIzleti;
	/**
	 * Putnik koji je clan grupe
	 * 
	 */
	private Putnik putnik;

	/**
	 * Neparametrizovani konstruktor koji pravi novog praznog clana.
	 */
	public Clan() {
	}

	/**
	 * Parametrizovani konstruktor koji inicijalizuje clana i postavlja mu
	 * prosledjene vrednosti.
	 * 
	 * @param grupa              grupa clana kao Grupa
	 * @param rbClana            redni broj clana kao ceo broj
	 * @param napomena           napomena kao String
	 * @param fakultativniIzleti dodatniIzleti kao boolean
	 * @param putnik             putnik koji je clan grupe kao Putnik
	 */
	public Clan(Grupa grupa, int rbClana, String napomena, boolean fakultativniIzleti, Putnik putnik) {
		setGrupa(grupa);
		setRbClana(rbClana);
		setNapomena(napomena);
		setFakultativniIzleti(fakultativniIzleti);
		setPutnik(putnik);
	}

	/**
	 * Vraca String sa imenom i prezimenom putnika.
	 * 
	 * @return ime i prezime putnika kao String
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return putnik.getImePutnika() + ", " + putnik.getPrezimePutnika();
	}

	/**
	 * Vraca grupu u kojoj pripada clan
	 * 
	 * @return grupa kao Grupa
	 */
	public Grupa getGrupa() {
		return grupa;
	}

	/**
	 * Postavlja vrednost za grupu kojoj clan pripada.
	 * 
	 * @param grupa nova vrednost za grupu .
	 */
	public void setGrupa(Grupa grupa) {
		this.grupa = grupa;
	}

	/**
	 * Vraca redni broj clana
	 * 
	 * @return rbClana kao int
	 */
	public int getRbClana() {
		return rbClana;
	}

	/**
	 * Postavlja redni broj clana.
	 * 
	 * @param rbClananova nova vrednost za redni broj clana.
	 */
	public void setRbClana(int rbClana) {
		this.rbClana = rbClana;
	}

	/**
	 * Vraca napomenu za putnika kao clana.
	 * 
	 * @return napomena kao String
	 */
	public String getNapomena() {
		return napomena;
	}

	/**
	 * Postavlja napomenu za clana.
	 * 
	 * @param napomena nova vrednost za napomenu clana.
	 */
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	/**
	 * Vraca putnika koji je clan u grupi
	 * 
	 * @return putnik kao Putnik
	 */
	public Putnik getPutnik() {
		return putnik;
	}

	/**
	 * Postavlja putnika koji je clan grupe.
	 * 
	 * @param putnik nova vrednost za putnika koji je clan grupe.
	 */
	public void setPutnik(Putnik putnik) {
		this.putnik = putnik;
	}

	/**
	 * Vraca da li clan ide na fakultativneIzlete.
	 * 
	 * @return fakultativniIzleti kao boolean
	 */
	public boolean isFakultativniIzleti() {
		return fakultativniIzleti;
	}

	/**
	 * Postavlja fakultativne izlete clana.
	 * 
	 * @param fakultativniIzleti nova vrednost za fakultativniIzlete clana.
	 */
	public void setFakultativniIzleti(boolean fakultativniIzleti) {
		this.fakultativniIzleti = fakultativniIzleti;
	}

	@Override
	public String nazivTabele() {
		return "Clan";
	}

	@Override
	public String alijas() {
		return " c ";
	}

	@Override
	public String join() {
		return "  JOIN putnik p USING (putnikID) " + "JOIN grupa g USING (grupaID) "
				+ "JOIN izlet i ON (i.izletID = g.izletID) " + "JOIN mesto m ON (m.mestoID = i.mestoID) "
				+ "JOIN  vodic v ON (v.vodicID= g.vodicID) "
				+ "JOIN administrator a ON (a.administratorID= g.administratorID) ";
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			Administrator a = new Administrator(rs.getLong("administratorID"), rs.getString("ime"),
					rs.getString("prezime"), rs.getString("username"), rs.getString("password"));

			Mesto m = new Mesto(rs.getLong("mestoID"), rs.getString("nazivMesta"));

			Izlet i = new Izlet(rs.getLong("izletID"), rs.getInt("cena"),
					Kategorija.valueOf(rs.getString("kategorija")), m);

			Putnik p = new Putnik(rs.getLong("putnikID"), rs.getString("imePutnika"), rs.getString("prezimePutnika"),
					rs.getString("email"), rs.getString("brojTelefona"));

			Vodic v = new Vodic(rs.getLong("vodicID"), rs.getString("imeVodica"), rs.getString("prezimeVodica"),
					rs.getString("brojTelefona"), rs.getInt("godineIskustva"));

			Grupa g = new Grupa(rs.getLong("grupaID"), rs.getDate("datumPolaska"), rs.getInt("kapacitetGrupe"),
					rs.getString("prevoz"), i, v, a, null);

			Clan c = new Clan(g, rs.getInt("rbClana"), rs.getString("napomena"), rs.getBoolean("fakultativniIzleti"),
					p);

			lista.add(c);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		return " (grupaID, rbClana, napomena,fakultativniIzleti, putnikID) ";
	}

	@Override
	public String vrednostZaPrimarniKljuc() {
		return " grupaID = " + grupa.getGrupaID();
	}

	@Override
	public String vrednostiZaInsert() {
		return " " + grupa.getGrupaID() + ", " + rbClana + ", " + "'" + napomena + "', " + fakultativniIzleti + ", "
				+ putnik.getPutnikID() + " ";
	}

	@Override
	public String vrednostiZaUpdate() {
		return "";
	}

	@Override
	public String uslov() {
		return " WHERE g.grupaID = " + grupa.getGrupaID();
	}

}
