package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Predstavlja grupu za izlet. Ima identifikator, datum kada se krece na izlet,
 * kapacitet grupe,prevoz odnsono kojim autobusom se ide, izlet na koji se
 * ide,vodica grupe, administratora koji dodaje,menja ili brise grupu i clanove
 * date grupe .
 *
 *
 * Nasledjuje apstraktnu domensku klasu OpstiDomenskiObjekat i implementira sve
 * njene apstraktne metode.
 *
 * @author Jovana
 */

public class Grupa extends OpstiDomenskiObjekat {
	/**
	 * Identifikator grupe
	 * 
	 */
	private Long grupaID;
	/**
	 * DatumPolaska grupe na izlet
	 */
	private Date datumPolaska;
	/**
	 * Kapacitet grupe
	 */
	private int kapacitetGrupe;
	/**
	 * Prevoz kojim grupa ide .
	 */
	private String prevoz;
	/**
	 * Izlet na koji grupa ide.
	 */
	private Izlet izlet;
	/**
	 * Vodic koji predvodi grupu.
	 */
	private Vodic vodic;
	/**
	 * Administrator koji brise,menja ili unosi grupu .
	 */
	private Administrator administrator;

	/**
	 * Clanovi kao putnici grupe
	 */
	private ArrayList<Clan> clanovi;

	/**
	 * Neparametrizovani konstruktor koji pravi novu grupu.
	 */
	public Grupa() {
	}

	/**
	 * Parametrizovani konstruktor koji inicijalizuje grupu i postavlja mu
	 * prosledjene vrednosti.
	 * 
	 * @param grupaID        Id grupe kao Long.
	 * @param datumPolaska   datum polaska grupe kao Date.
	 * @param kapacitetGrupe kapacitet grupe kao ceo broj .
	 * @param prevoz         prevoz kojim grupa ide kao String.
	 * @param izlet          izlet na koji grupa ide kao Izlet.
	 * @param vodic          vodic koji vodi grupu kao Vodic.
	 * @param administrator  administrator koji unosi,brise,menja grupu kao
	 *                       Administrator.
	 * @param clanovi        lista clanova koji su u datoj grupi kao
	 *                       ArrayList<Clan>.
	 */
	public Grupa(Long grupaID, Date datumPolaska, int kapacitetGrupe, String prevoz, Izlet izlet, Vodic vodic,
			Administrator administrator, ArrayList<Clan> clanovi) {
		setGrupaID(grupaID);
		setDatumPolaska(datumPolaska);
		setKapacitetGrupe(kapacitetGrupe);
		setPrevoz(prevoz);
		setIzlet(izlet);
		setVodic(vodic);
		setAdministrator(administrator);
		setClanovi(clanovi);
	}

	@Override
	public String nazivTabele() {
		return " grupa ";
	}

	@Override
	public String alijas() {
		return " g ";
	}

	@Override
	public String join() {
		return " JOIN izlet i ON (i.izletID = g.izletID) " + "JOIN mesto m ON (m.mestoID = i.mestoID) "
				+ "JOIN vodic v ON (v.vodicID = g.vodicID) "
				+ "JOIN administrator a ON (a.administratorID = g.administratorID)";
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

			Vodic v = new Vodic(rs.getLong("vodicID"), rs.getString("imeVodica"), rs.getString("prezimeVodica"),
					rs.getString("brojTelefona"), rs.getInt("godineIskustva"));

			Grupa g = new Grupa(rs.getLong("grupaID"), rs.getDate("datumPolaska"), rs.getInt("kapacitetGrupe"),
					rs.getString("prevoz"), i, v, a, null);

			lista.add(g);
		}

		rs.close();
		return lista;
	}

	@Override
	public String koloneZaInsert() {
		return " (datumPolaska,kapacitetGrupe,prevoz,izletID, " + "vodicID, administratorID) ";
	}

	@Override
	public String vrednostZaPrimarniKljuc() {
		return " grupaID = " + grupaID;
	}

	@Override
	public String vrednostiZaInsert() {
		return "'" + new java.sql.Date(datumPolaska.getTime()) + "', " + kapacitetGrupe + ", '" + prevoz + "', "
				+ izlet.getIzletID() + ", " + vodic.getVodicID() + ", " + administrator.getAdministratorID();
	}

	@Override
	public String vrednostiZaUpdate() {
		return " datumPolaska= '" + new java.sql.Date(datumPolaska.getTime()) + "', " + "kapacitetGrupe = "
				+ kapacitetGrupe + ", prevoz = '" + prevoz + "', " + "izletID = " + izlet.getIzletID();
	}

	@Override
	public String uslov() {
		return "";
	}

	/**
	 * Vraca identifikator grupe.
	 * 
	 * @return grupaID grupe kao Long
	 */
	public Long getGrupaID() {
		return grupaID;
	}

	/**
	 * Postavlja vrednost za identifikator grupe.
	 * 
	 * @param grupaID nova vrednost za identifikator grupe
	 */
	public void setGrupaID(Long grupaID) {
		this.grupaID = grupaID;
	}

	/**
	 * Vraca datum polaska grupe.
	 * 
	 * @return datumPolaska grupe kao Date
	 */
	public Date getDatumPolaska() {
		return datumPolaska;
	}

	/**
	 * Postavlja vrednost za datum polaska grupe .
	 * 
	 * @param datumPolaska kao nova vrednost za datum polaska grupe
	 * 
	 * @throws NullPointerException     ako je unet datum null
	 * @throws IllegalArgumentException ako je unet datum u proslosti,odnosno pre
	 *                                  sadasnjeg datuma
	 */
	public void setDatumPolaska(Date datumPolaska) {
		if (datumPolaska == null)
			throw new NullPointerException();
		if (datumPolaska.before(new Date()))
			throw new IllegalArgumentException("Datum mora biti u budunosti!");

		this.datumPolaska = datumPolaska;
	}

	/**
	 * Vraca kapacitet grupe.
	 * 
	 * @return kapacitetGrupe kao int
	 */
	public int getKapacitetGrupe() {
		return kapacitetGrupe;
	}

	/**
	 * Postavlja vrednost za kapacitet grupe.
	 * 
	 * @param kapacitetGrupe kao nova vrednost za kapacitet grupe
	 * 
	 * @throws IllegalArgumentException ako je kapacitet grupe manji od 15 ili veci
	 *                                  od 60
	 */
	public void setKapacitetGrupe(int kapacitetGrupe) {
		if (kapacitetGrupe < 15 || kapacitetGrupe > 60) {
			throw new IllegalArgumentException("Kapacitet grupe mora biti izmedju 15 i 60!");
		}

		this.kapacitetGrupe = kapacitetGrupe;
	}

	/**
	 * Vraca prevoz grupe.
	 * 
	 * @return prevoz grupe kao String
	 */
	public String getPrevoz() {
		return prevoz;
	}

	/**
	 * Postavlja vrednost za prevoz .
	 * 
	 * @param prevoz kao nova vrednost za prevoz grupe
	 */
	public void setPrevoz(String prevoz) {
		this.prevoz = prevoz;
	}

	/**
	 * Vraca izlet grupe.
	 * 
	 * @return izlet grupe kao Izlet
	 */
	public Izlet getIzlet() {
		return izlet;
	}

	/**
	 * Postavlja vrednost za izlete grupe .
	 * 
	 * @param izlet kao nova vrednost za izlet grupe
	 */
	public void setIzlet(Izlet izlet) {
		this.izlet = izlet;
	}

	/**
	 * Vraca vodica grupe.
	 * 
	 * @return vodic grupe kao Vodic
	 */

	public Vodic getVodic() {
		return vodic;
	}

	/**
	 * Postavlja vrednost vodica grupe .
	 * 
	 * @param vodic kao nova vrednost za vodica grupe
	 */
	public void setVodic(Vodic vodic) {
		this.vodic = vodic;
	}

	/**
	 * Vraca administratora grupe
	 * 
	 * @return administrator grupe kao Aministrator
	 */
	public Administrator getAdministrator() {
		return administrator;
	}

	/**
	 * Postavlja vrednost administratora grupe .
	 * 
	 * @param administrator kao nova vrednost za administratora grupe
	 */
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	/**
	 * Vraca clanove grupe
	 * 
	 * @return clanovi grupe kao ArrayList<Clan>
	 */
	public ArrayList<Clan> getClanovi() {
		return clanovi;
	}

	/**
	 * Postavlja vrednost za clanove grupe.
	 * 
	 * @param clanovi ArrayList<Clan> kao nova vrednost za clanove grupe
	 */
	public void setClanovi(ArrayList<Clan> clanovi) {
		this.clanovi = clanovi;
	}

	/**
	 * Vraca String sa kapacitetom grupe ,vodicem ,prevozom i datumom polaska.
	 * 
	 * @return kapacitetGrupe,ime Vodica,prevoz,datumPolaska kao String
	 */
	@Override
	public String toString() {

		return (kapacitetGrupe + ", " + vodic.getImeVodica() + ", " + prevoz + ", " + datumPolaska);
	}

}
