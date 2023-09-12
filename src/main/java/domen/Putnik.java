package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Putnik extends OpstiDomenskiObjekat {

	private Long putnikID;
	private String imePutnika;
	private String prezimePutnika;
	private String email;
	private String brojTelefona;

	public Putnik() {
	}

	public Putnik(Long putnikID, String imePutnika, String prezimePutnika, String email, String brojTelefona) {
		setPutnikID(putnikID);
		setImePutnika(imePutnika);
		setPrezimePutnika(prezimePutnika);
		setEmail(email);
		setBrojTelefona(brojTelefona);
	}

	public String getImePutnika() {
		return imePutnika;
	}

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

	public String getPrezimePutnika() {
		return prezimePutnika;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null) {
			throw new NullPointerException();
		}
		if (email.length() < 3) {
			throw new IllegalArgumentException("Email ne sme  imati manje od 3 karaktera.");
		}
		this.email = email;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

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

	public Long getPutnikID() {
		return putnikID;
	}

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
