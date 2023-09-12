package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Predstavlja putnika koji je clan grupe. Ima identifikator, ime, prezime,email
 * i broj telefona.
 * 
 * Nasledjuje apstraktnu domensku klasu OpstiDomenskiObjekat i implementira sve
 * njene apstraktne metode.
 * 
 * @author Jovana
 */
public class Putnik extends OpstiDomenskiObjekat {
	/**
	 * Identifikator putnika
	 */
	private Long putnikID;
	/**
	 * Ime putnika
	 */
	private String imePutnika;
	/**
	 * Prezime putnika
	 */
	private String prezimePutnika;
	/**
	 * Email putnika
	 */
	private String email;
	/**
	 * Broj telefona putnika
	 */
	private String brojTelefona;

	/**
	 * 
	 * Neparametrizovani konstruktor koji pravi novog praznog putnika
	 * 
	 */
	public Putnik() {
	}

	/**
	 * Parametrizovani konstruktor koji inicijalizuje putnika i postavlja mu
	 * prosledjene vrednosti.
	 * 
	 * @param putnikID       id putnika kao Long
	 * @param imePutnika     Ime putnika kao String.
	 * @param prezimePutnika Prezime putnika kao String.
	 * @param brojTelefona   Broj telefona putnika kao String.
	 * @param godineIskustva Godine iskustva kao ceo broja.
	 */
	public Putnik(Long putnikID, String imePutnika, String prezimePutnika, String email, String brojTelefona) {
		setPutnikID(putnikID);
		setImePutnika(imePutnika);
		setPrezimePutnika(prezimePutnika);
		setEmail(email);
		setBrojTelefona(brojTelefona);
	}

	/**
	 * Vraca ime putnika.
	 * 
	 * @return ime putnika kao String
	 */
	public String getImePutnika() {
		return imePutnika;
	}

	/**
	 * Postavlja vrednost za ime putnika.
	 * 
	 * @param imePutnika kao nova vrednost za ime putnika.
	 * 
	 * @throws NullPointerException     ako je uneto ime null
	 * @throws IllegalArgumentException ako je uneto ime krace od tri karaktera.
	 * @throws IllegalArgumentException ako uneto ime sadrzi broj.
	 */
	public void setImePutnika(String imePutnika) {
		if (imePutnika == null)
			throw new NullPointerException();
		if (imePutnika.length() < 3)
			throw new IllegalArgumentException("Ime  mora biti duze od 2 karaktera.");
		char[] ime = imePutnika.toCharArray();
		for (Character c : ime) {
			if (!Character.isLetter(c)) {
				throw new IllegalArgumentException("Ime mora sadrzati samo slova.");
			}
		}
		this.imePutnika = imePutnika;
	}

	/**
	 * Vraca prezime putnika.
	 * 
	 * @return prezime putnika kao String
	 */

	public String getPrezimePutnika() {
		return prezimePutnika;
	}

	/**
	 * Postavlja vrednost za prezime putnika.
	 * 
	 * @param prezimePutnika kao nova vrednost za prezime putnika.
	 * 
	 * @throws NullPointerException     ako je uneto prezime null
	 * @throws IllegalArgumentException ako je uneto prezime krace od tri karaktera.
	 * @throws IllegalArgumentException ako uneto prezime sadrzi broj.
	 */
	public void setPrezimePutnika(String prezimePutnika) {
		if (prezimePutnika == null)
			throw new NullPointerException();
		if (prezimePutnika.length() < 3)
			throw new IllegalArgumentException("Prezime mora biti duze od 2 karaktera.");
		char[] prezime = prezimePutnika.toCharArray();
		for (Character c : prezime) {
			if (!Character.isLetter(c)) {
				throw new IllegalArgumentException("Prezime mora sadrzati samo slova.");
			}
		}
		this.prezimePutnika = prezimePutnika;
	}

	/**
	 * Vraca email putnika.
	 * 
	 * @return email putnika kao String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Postavlja vrednost za email putnika.
	 * 
	 * @param email kao nova vrednost za emmail putnika.
	 * 
	 * @throws NullPointerException     ako je uneti email null
	 * @throws IllegalArgumentException ako je uneti email krace od tri karaktera.
	 */
	public void setEmail(String email) {
		if (email == null) {
			throw new NullPointerException();
		}
		if (email.length() < 3) {
			throw new IllegalArgumentException("Email ne sme  imati manje od 3 karaktera.");
		}
		this.email = email;
	}

	/**
	 * Vraca broj telefona .
	 * 
	 * @return brojTelefona putnika kao String
	 */
	public String getBrojTelefona() {
		return brojTelefona;
	}

	/**
	 * Postavlja vrednost za broj telefona putnika.
	 * 
	 * @param brojTelefona kao nova vrednost za broj telefona putnika.
	 * 
	 * @throws NullPointerException     ako je uneti broj null
	 * @throws IllegalArgumentException ako je uneti broj manji od 9 ili veci od 10
	 *                                  karaktera. * @throws
	 *                                  IllegalArgumentException ako je uneti broj
	 *                                  sadrzi slova.
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
				throw new IllegalArgumentException("Broj telefona mora sadrzati samo cifre.");
			}
		}
		this.brojTelefona = brojTelefona;
	}

	@Override
	public String nazivTabele() {
		return " putnik ";
	}

	@Override
	public String alijas() {
		return " p ";
	}

	@Override
	public String join() {
		return "";
	}

	/**
	 * Vraca String sa emailom i brojem telefona putnika .
	 * 
	 * @return email + brojTelefona putnika kao String
	 */
	@Override
	public String toString() {
		return "" + email + "" + brojTelefona;
	}

	@Override
	public String koloneZaInsert() {
		return " (imePutnika, prezimePutnika, email, brojTelefona) ";
	}

	@Override
	public String vrednostZaPrimarniKljuc() {
		return " PutnikID = " + putnikID;
	}

	@Override
	public String vrednostiZaInsert() {
		return "'" + imePutnika + "', '" + prezimePutnika + "', " + "'" + email + "', '" + brojTelefona + "'";
	}

	@Override
	public String vrednostiZaUpdate() {
		return " email = '" + email + "', brojTelefona = '" + brojTelefona + "' " + ",prezimePutnika = '"
				+ prezimePutnika + "' ";
	}

	@Override
	public String uslov() {
		return "";
	}

	/**
	 * Vraca id putnika .
	 * 
	 * @return identifikator kao Long
	 */
	public Long getPutnikID() {
		return putnikID;
	}

	/**
	 * Postavlja vrednost za identifikator putnika.
	 * 
	 * @param putnikID nova vrednost za identifikator putnika
	 */
	public void setPutnikID(Long putnikID) {
		this.putnikID = putnikID;
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			Putnik p = new Putnik(rs.getLong("putnikID"), rs.getString("imePutnika"), rs.getString("prezimePutnika"),
					rs.getString("email"), rs.getString("brojTelefona"));

			lista.add(p);
		}

		rs.close();
		return lista;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Putnik other = (Putnik) obj;
		return Objects.equals(email, other.email) || Objects.equals(brojTelefona, other.brojTelefona);
	}

}
