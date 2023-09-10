package domen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jovana
 */
public class Clan extends OpstiDomenskiObjekat{
    private Grupa grupa;
    private int rbClana;
    private String napomena;
    private boolean fakultativniIzleti;
    private Putnik putnik;

    public Clan() {
    }

    public Clan(Grupa grupa, int rbClana, String napomena, boolean fakultativniIzleti, Putnik putnik) {
       setGrupa(grupa);
       setRbClana(rbClana);
       setNapomena(napomena);
        setFakultativniIzleti(fakultativniIzleti);
        setPutnik(putnik);
    }

  

 

    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public int getRbClana() {
        return rbClana;
    }

    public void setRbClana(int rbClana) {
        this.rbClana = rbClana;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Putnik getPutnik() {
        return putnik;
    }

    public void setPutnik(Putnik putnik) {
        this.putnik = putnik;
    }

    public boolean isFakultativniIzleti() {
        return fakultativniIzleti;
    }

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
       return "  JOIN putnik p USING (putnikID) "
                + "JOIN grupa g USING (grupaID) "
                + "JOIN izlet i ON (i.izletID = g.izletID) "
                + "JOIN mesto m ON (m.mestoID = i.mestoID) "
                + "JOIN  vodic v ON (v.vodicID= g.vodicID) "
                + "JOIN administrator a ON (a.administratorID= g.administratorID) ";
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

           
            Izlet i = new Izlet(rs.getLong("izletID"),rs.getInt("cena"),
            		Kategorija.valueOf(rs.getString("kategorija")),m);

            Putnik p = new Putnik(rs.getLong("putnikID"),
                    rs.getString("imePutnika"), rs.getString("prezimePutnika"),
                    rs.getString("email"), rs.getString("brojTelefona"));

            Vodic v = new Vodic(rs.getLong("vodicID"),
                    rs.getString("imeVodica"), rs.getString("prezimeVodica"),
                    rs.getString("brojTelefona"), rs.getInt("godineIskustva"));

            Grupa g = new Grupa(rs.getLong("grupaID"), rs.getDate("datumPolaska"),
                    rs.getInt("kapacitetGrupe"),
                    rs.getString("prevoz"), i, v, a, null);

            Clan c= new Clan(g, rs.getInt("rbClana"), rs.getString("napomena"),
                    rs.getBoolean(" fakultativniIzleti") ,p);

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
        return " " + grupa.getGrupaID() + ", " + rbClana+ ", "
                + "'" + napomena + "', " + fakultativniIzleti+", "+putnik.getPutnikID() + " ";
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
