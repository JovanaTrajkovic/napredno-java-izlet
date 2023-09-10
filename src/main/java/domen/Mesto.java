package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jovana
 */
public class Mesto extends OpstiDomenskiObjekat{
       private Long mestoID;
    private String nazivMesta;

    public Mesto() {
    }

    public Mesto(Long mestoID, String nazivMesta) {
       setMestoID(mestoID);
       setNazivMesta(nazivMesta);
    }

    public Long getMestoID() {
        return mestoID;
    }

    public void setMestoID(Long mestoID) {
        this.mestoID = mestoID;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    @Override
    public String toString() {
     return nazivMesta;
    }

    
    @Override
    public String nazivTabele() {
       return " Mesto ";
    }

    @Override
    public String alijas() {
        return " m";
    }

    @Override
    public String join() {
       return "";
    }
    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
    ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Mesto m = new Mesto(rs.getLong("mestoID"),rs.getString("nazivMesta"));
          lista.add(m);
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
        return " mestoID = " + mestoID;
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
    
}
