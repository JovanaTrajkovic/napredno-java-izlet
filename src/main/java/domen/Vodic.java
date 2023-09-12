package domen;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jovana
 */
public class Vodic extends OpstiDomenskiObjekat {
	private Long vodicID;
	private String imeVodica;
	private String prezimeVodica;
	private String brojTelefona;
	private int godineIskustva;

	public Vodic() {
	}

	public Vodic(Long vodicID, String imeVodica, String prezimeVodica, String brojTelefona, int godineIskustva) {
		setVodicID(vodicID);
		setImeVodica(imeVodica);
		setPrezimeVodica(prezimeVodica);
		setBrojTelefona(brojTelefona);
		setGodineIskustva(godineIskustva);
	}

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

	public Long getVodicID() {
		return vodicID;
	}

	public void setVodicID(Long vodicID) {
		this.vodicID = vodicID;
	}

	public String getImeVodica() {
		return imeVodica;
	}

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

	public String getPrezimeVodica() {
		return prezimeVodica;
	}

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

	public int getGodineIskustva() {
		return godineIskustva;
	}

	public void setGodineIskustva(int godineIskustva) {
		if (godineIskustva < 0) {
			throw new IllegalArgumentException("Godine iskustva ne smeju biti manje od 0 .");
		}
		this.godineIskustva = godineIskustva;
	}

}
