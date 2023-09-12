package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jovana
 */
public class Administrator extends OpstiDomenskiObjekat {
	private Long administratorID;
	private String ime;
	private String prezime;
	private String username;
	private String password;

	public Administrator() {
	}

	public Administrator(Long administratorID, String ime, String prezime, String username, String password) {
		setAdministratorID(administratorID);
		setIme(ime);
		setPrezime(prezime);
		setUsername(username);
		setPassword(password);
	}

	public Long getAdministratorID() {
		return administratorID;
	}

	public void setAdministratorID(Long administratorID) {
		this.administratorID = administratorID;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username == null)
			throw new NullPointerException();
		if (username.length() < 4)
			throw new IllegalArgumentException("Username  mora biti duzi od 4.");
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null)
			throw new NullPointerException();
		if (password.length() < 5 || password.equals(""))
			throw new IllegalArgumentException("Password mora imati vise od 5 karaktera");
		this.password = password;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		if (ime == null)
			throw new NullPointerException();
		if (ime.length() < 3)
			throw new IllegalArgumentException("Ime  mora biti duze od 2 karaktera.");
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		if (prezime == null)
			throw new NullPointerException();
		if (prezime.length() < 3)
			throw new IllegalArgumentException("Prezime mora biti duze od 2 karaktera.");
		this.prezime = prezime;
	}

	@Override
	public String toString() {
		return ime + " " + prezime;
	}

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
