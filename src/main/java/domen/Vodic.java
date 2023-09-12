package domen;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja vodica izleta. Ima identifikator, ime, prezime, broj telefona i
 * godine isustva.
 * 
 * Nasledjuje apstraktnu domensku klasu OpstiDomenskiObjekat i implementira sve
 * njene apstraktne metode.
 * 
 * @author Jovana
 */
public class Vodic extends OpstiDomenskiObjekat {
	/**
	 * Identifikator vodica
	 */
	private Long vodicID;
	/**
	 * Ime vodica
	 */
	private String imeVodica;
	/**
	 * Prezime vodica
	 */
	private String prezimeVodica;
	/**
	 * Broj telefona vodica
	 */
	private String brojTelefona;
	/**
	 * Godine isustva vodica
	 */
	private int godineIskustva;

	/**
	 * 
	 * Neparametrizovani konstruktor koji pravi novog praznog vodica.
	 * 
	 */
	public Vodic() {
	}

	/**
	 * Parametrizovani konstruktor koji inicijalizuje vodica i postavlja mu
	 * prosledjene vrednosti.
	 * 
	 * @param vodicID        id vodica kao Long
	 * @param imeVodica      Ime vodica kao String.
	 * @param prezimeVodica  Prezime vodica kao String.
	 * @param brojTelefona   Broj telefona vodica kao String.
	 * @param godineIskustva Godine iskustva kao ceo broja.
	 */
	public Vodic(Long vodicID, String imeVodica, String prezimeVodica, String brojTelefona, int godineIskustva) {
		setVodicID(vodicID);
		setImeVodica(imeVodica);
		setPrezimeVodica(prezimeVodica);
		setBrojTelefona(brojTelefona);
		setGodineIskustva(godineIskustva);
	}

	/**
	 * Vraca String sa imenom i prezimenom vodica.
	 * 
	 * @return ime i prezime vodica kao String
	 */
	@Override
	public String toString() {
		return imeVodica + " " + prezimeVodica;
	}

	@Override
	public String nazivTabele() {
		return " vodic ";
	}

	@Override
	public String alijas() {
		return " v ";
	}

	@Override
	public String join() {
		return "";
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			Vodic v = new Vodic(rs.getLong("vodicID"), rs.getString("imeVodica"), rs.getString("prezimeVodica"),
					rs.getString("brojTelefona"), rs.getInt("godineIskustva"));

			lista.add(v);
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
		return " vodicID = " + vodicID;
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
	 * Vraca identifikator vodica.
	 * 
	 * @return identifikator vodica kao Long
	 */
	public Long getVodicID() {
		return vodicID;
	}

	/**
	 * Postavlja vrednost za identifikator vodica.
	 * 
	 * @param vodicID kao nova vrednost za identifikator vodica
	 */
	public void setVodicID(Long vodicID) {
		this.vodicID = vodicID;
	}

	/**
	 * Vraca ime vodica.
	 * 
	 * @return ime vodica kao String
	 */
	public String getImeVodica() {
		return imeVodica;
	}

	/**
	 * Postavlja vrednost za ime vodica.
	 * 
	 * @param imeVodica kao nova vrednost za ime vodica.
	 * 
	 * @throws NullPointerException     ako je uneto ime null
	 * @throws IllegalArgumentException ako je uneto ime krace od tri karaktera.
	 * @throws IllegalArgumentException ako uneto ime sadrzi broj.
	 */
	public void setImeVodica(String imeVodica) {
		if (imeVodica == null)
			throw new NullPointerException();
		if (imeVodica.length() < 3)
			throw new IllegalArgumentException("Ime  mora biti duze od 2 karaktera.");
		char[] ime = imeVodica.toCharArray();
		for (Character c : ime) {
			if (!Character.isLetter(c)) {
				throw new IllegalArgumentException("Ime mora sadrzati samo slova.");
			}
		}
		this.imeVodica = imeVodica;
	}

	/**
	 * Vraca prezime vodica.
	 * 
	 * @return prezime vodica kao String
	 */
	public String getPrezimeVodica() {
		return prezimeVodica;
	}

	/**
	 * Postavlja vrednost za prezime vodica.
	 * 
	 * @param prezimeVodica kao nova vrednost za prezime vodica.
	 * 
	 * @throws NullPointerException     ako je uneto prezime null
	 * @throws IllegalArgumentException ako je uneto prezime krace od tri karaktera.
	 * @throws IllegalArgumentException ako uneto prezime sadrzi broj.
	 */
	public void setPrezimeVodica(String prezimeVodica) {
		if (prezimeVodica == null)
			throw new NullPointerException();
		if (prezimeVodica.length() < 3)
			throw new IllegalArgumentException("Prezime mora biti duze od 2 karaktera.");
		char[] prezime = prezimeVodica.toCharArray();
		for (Character c : prezime) {
			if (!Character.isLetter(c)) {
				throw new IllegalArgumentException("Prezime mora sadrzati samo slova.");
			}
		}
		this.prezimeVodica = prezimeVodica;
	}

	/**
	 * Vraca broj telefona vodica.
	 * 
	 * @return broj telefona kao String
	 */
	public String getBrojTelefona() {
		return brojTelefona;
	}

	/**
	 * Postavlja vrednost za broj telefona vodica.
	 * 
	 * @param brojTelefona kao nova vrednost za broj telefona vodica.
	 * 
	 * @throws NullPointerException     ako je uneti broj null
	 * @throws IllegalArgumentException ako je uneti broj kraci od 9 karaktera ili
	 *                                  duzi od 10 karaktera
	 * @throws IllegalArgumentException ako uneti broj sadrzi karakter
	 */
	public void setBrojTelefona(String brojTelefona) {
		if (brojTelefona == null) {
			throw new NullPointerException();
		}
		if (brojTelefona.length() < 9 || brojTelefona.length() > 10) {
			throw new IllegalArgumentException("Broj telefona mora imati 9 ili 10 cifara.");
		}
		char[] brtel = brojTelefona.toCharArray();
		for (Character c : brtel) {
			if (!Character.isDigit(c)) {
				throw new IllegalArgumentException("Broj telefona moze sadrzati samo cifre.");
			}
		}
		this.brojTelefona = brojTelefona;
	}

	/**
	 * Vraca godine iskustva vodica.
	 * 
	 * @return godineIskustva kao ceo broj
	 */
	public int getGodineIskustva() {
		return godineIskustva;
	}

	/**
	 * Postavlja vrednost za godine iskustva vodica.
	 * 
	 * @param godineIskustva kao nova vrednost za godin iskustva vodica
	 * @throws IllegalArgumentException ako su unete godine manje od 0
	 */
	public void setGodineIskustva(int godineIskustva) {
		if (godineIskustva < 0) {
			throw new IllegalArgumentException("Godine iskustva ne smeju biti manje od 0 .");
		}
		this.godineIskustva = godineIskustva;
	}

}
