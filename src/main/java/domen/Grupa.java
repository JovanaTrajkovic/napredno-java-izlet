package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jovana
 */
public class Grupa extends OpstiDomenskiObjekat {
   private Long grupaID;
   private Date datumPolaska;
   private int kapacitetGrupe;
   private String prevoz;
    private Izlet izlet;
    private Vodic vodic;
    private Administrator administrator;
    private ArrayList<Clan> clanovi;

    public Grupa() {
    }

    public Grupa(Long grupaID, Date datumPolaska, int kapacitetGrupe, String prevoz, Izlet izlet, Vodic vodic, Administrator administrator, ArrayList<Clan> clanovi) {
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
         return " Grupa ";
    }

    @Override
    public String alijas() {
        return " g ";
    }

    @Override
    public String join() {
         return " JOIN izlet i ON (i.izletID = g.izletID) "
                + "JOIN mesto m ON (m.mestoID = i.mestoID) "
                + "JOIN vodic v ON (v.vodicID = g.vodicID) "
                + "JOIN administrator a ON (a.administratorID = g.administratorID)";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
    ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {

        	  Administrator a = new Administrator(rs.getLong("administratorID"),
                      rs.getString("ime"), rs.getString("prezime"),
                      rs.getString("username"), rs.getString("password"));

           Mesto m = new Mesto(rs.getLong("mestoID"),
                    rs.getString("nazivMesta"));

            Izlet i = new Izlet();

            Vodic v = new Vodic(rs.getLong("vodicID"),
                    rs.getString("imeVodica"), rs.getString("prezimeVodica"),
                   rs.getString("brojTelefona"), rs.getInt("godineIskustva"));

            Grupa g = new Grupa(rs.getLong("grupaID"), rs.getDate("datumPolaska"),
                   rs.getInt("kapacitetGrupe"),
                    rs.getString("prevoz"), i, v, a, null);

            lista.add(g);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
         return " (datumPolaska,kapacitetGrupe,prevoz,izletID, "
                + "vodicID, administratorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " grupaID = " + grupaID;
    }

    @Override
    public String vrednostiZaInsert() {
       return "'" + new java.sql.Date(datumPolaska.getTime()) + "', "
                + kapacitetGrupe + ", '" + prevoz+ "', " + izlet.getIzletID()
                + ", " + vodic.getVodicID() + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumPolaska= '" + new java.sql.Date(datumPolaska.getTime()) + "', "
               + "kapacitetGrupe = " + kapacitetGrupe + ", prevoz = '" + prevoz+ "', "
                + "izletID = " + izlet.getIzletID();
    }

    @Override
    public String uslov() {
          return "";
    }

    public Long getGrupaID() {
        return grupaID;
    }

    public void setGrupaID(Long grupaID) {
        this.grupaID = grupaID;
    }

    public Date getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(Date datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public int getKapacitetGrupe() {
        return kapacitetGrupe;
    }

    public void setKapacitetGrupe(int kapacitetGrupe) {
        this.kapacitetGrupe = kapacitetGrupe;
    }

    public String getPrevoz() {
        return prevoz;
    }

    public void setPrevoz(String prevoz) {
        this.prevoz = prevoz;
    }

    public Izlet getIzlet() {
        return izlet;
    }

    public void setIzlet(Izlet izlet) {
        this.izlet = izlet;
    }

    public Vodic getVodic() {
        return vodic;
    }

    public void setVodic(Vodic vodic) {
        this.vodic = vodic;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<Clan> getClanovi() {
        return clanovi;
    }

    public void setClanovi(ArrayList<Clan> clanovi) {
        this.clanovi = clanovi;
    }
    
    
}
