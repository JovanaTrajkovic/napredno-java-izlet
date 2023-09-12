package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja administratora sistema. Ima identifikator, ime, prezime,
 * korisnicko ime i lozinku.
 * 
 * Nasledjuje apstraktnu domensku klasu OpstiDomenskiObjekat i implementira sve
 * njene apstraktne metode.
 * 
 * @author Jovana
 */
public class Administrator extends OpstiDomenskiObjekat {
	/**
	 *  Identifikator administratora
	 */
	private Long administratorID;
	/**
	 * Ime administratora 
	 */
	private String ime;
	/**
	 * Prezime administratora
	 */
	private String prezime;
	/**
	 * Korisnicko ime administratora Koristi se za logovanje administratora na
	 * sistem.
	 */
	private String username;
	/**
	 * Lozinka administratora Koristi se za logovanje administratora na sistem.
	 */
	private String password;

	/**
	 * Neparametrizovani konstruktor koji pravi novog praznog administratora.
	 */

	public Administrator() {
	}

	/**
	 * Parametrizovani konstruktor koji inicijalizuje administratora i postavlja mu
	 * prosledjene vrednosti.
	 * 
	 * @param administratorID Id trenera kao Long.
	 * @param ime             Ime administratora kao String.
	 * @param prezime         Prezime administratora kao String.
	 * @param username        Username administratora kao String.
	 * @param password        Password administratora kao String.
	 */
	public Administrator(Long administratorID, String ime, String prezime, String username, String password) {
		setAdministratorID(administratorID);
		setIme(ime);
		setPrezime(prezime);
		setUsername(username);
		setPassword(password);
	}

	/**
	 * Vraca identifikator administratora.
	 * 
	 * @return identifikator administratora kao Long
	 */
	public Long getAdministratorID() {
		return administratorID;
	}

	/**
	 * Postavlja vrednost za identifikator administratora.
	 * 
	 * @param administratorID nova vrednost za identifikator administratora
	 */
	public void setAdministratorID(Long administratorID) {
		this.administratorID = administratorID;

	}

	/**
	 * Vraca korisnicko ime administratora.
	 * 
	 * @return korisnicko ime administratora kao String
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * Postavlja vrednost za korisnicko ime administratora.
	 * 
	 * @param username nova vrednost za korisnicko ime administratora.
	 * 
	 * @throws NullPointerException     ako je unet username null
	 * @throws IllegalArgumentException ako je unet username krace od cetiri
	 *                                  karaktera.
	 */
	public void setUsername(String username) {
		if (username == null)
			throw new NullPointerException();
		if (username.length() < 4)
			throw new IllegalArgumentException("Username  mora biti duzi od 4.");
		this.username = username;
	}

	/**
	 * Vraca lozinku administratora.
	 * 
	 * @return lozinka administratora kao String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Postavlja vrednost za lozinku administratora.
	 * 
	 * @param password nova vrednost za lozinku administratora
	 * 
	 * @throws NullPointerException     ako je unet passwod null
	 * @throws IllegalArgumentException ako je unet password kraci od pet karaktera
	 */
	public void setPassword(String password) {
		if (password == null)
			throw new NullPointerException();
		if (password.length() < 5 || password.equals(""))
			throw new IllegalArgumentException("Password mora imati vise od 5 karaktera");
		this.password = password;
	}

	/**
	 * Vraca ime administratora.
	 * 
	 * @return ime administratora kao String
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Postavlja vrednost za ime administratora.
	 * 
	 * @param ime nova vrednost za ime administratora
	 * 
	 * @throws NullPointerException     ako je uneto ime null
	 * @throws IllegalArgumentException ako je uneto ime krace od 3 karaktera
	 */
	public void setIme(String ime) {
		if (ime == null)
			throw new NullPointerException();
		if (ime.length() < 3)
			throw new IllegalArgumentException("Ime  mora biti duze od 2 karaktera.");
		this.ime = ime;
	}

	/**
	 * Vraca prezime administratora.
	 * 
	 * @return prezime administratora kao String.
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Postavlja vrednost za prezime administratora.
	 * 
	 * @param prezime nova vrednost za prezime administratora.
	 * 
	 * @throws NullPointerException     ako je unet password null
	 * @throws IllegalArgumentException ako je unet password kraci od 3 karaktera
	 */
	public void setPrezime(String prezime) {
		if (prezime == null)
			throw new NullPointerException();
		if (prezime.length() < 3)
			throw new IllegalArgumentException("Prezime mora biti duze od 2 karaktera.");
		this.prezime = prezime;
	}

	/**
	 * Vraca String sa imenom i prezimenom vodica.
	 * 
	 * @return ime i prezime vodica kao String
	 */
	@Override
	public String toString() {
		return ime + " " + prezime;
	}

	/**
	 * 
	 */
	@Override
	public String nazivTabele() {
		return "administrator";
	}

	@Override
	public String alijas() {
		return " a ";
	}

	@Override
	public String join() {
		return "";
	}

	@Override
	public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {

		ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

		while (rs.next()) {
			Administrator a = new Administrator(rs.getLong("administratorID"), rs.getString("ime"),
					rs.getString("prezime"), rs.getString("username"), rs.getString("password"));

			lista.add(a);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		return " (ime, prezime, username, password) ";
	}

	@Override
	public String vrednostZaPrimarniKljuc() {
		return " administratorID = " + administratorID;
	}

	@Override
	public String vrednostiZaInsert() {
		return "'" + ime + "', '" + prezime + "', " + "'" + username + "', '" + password + "'";
	}

	@Override
	public String vrednostiZaUpdate() {
		return " ime = '" + ime + "', prezime = '" + prezime + "', " + "username = '" + username + "', password = '"
				+ password + "' ";
	}

	@Override
	public String uslov() {
		return "";
	}

}
