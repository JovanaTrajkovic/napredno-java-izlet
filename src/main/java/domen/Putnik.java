package domen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Putnik  extends OpstiDomenskiObjekat {

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
	        this.imePutnika = imePutnika;
	    }

	    public String getPrezimePutnika() {
	        return prezimePutnika;
	    }

	    public void setPrezimePutnika(String prezimePutnika) {
	        this.prezimePutnika = prezimePutnika;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getBrojTelefona() {
	        return brojTelefona;
	    }

	    public void setBrojTelefona(String brojTelefona) {
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
	    public String koloneZaInsert() {
	        return " (imePutnika, prezimePutnika, email, brojTelefona) ";
	    }

	    @Override
	    public String vrednostZaPrimarniKljuc() {
	          return " PutnikID = " +putnikID ;
	    }

	    @Override
	    public String vrednostiZaInsert() {
	      return "'" + imePutnika+ "', '" + prezimePutnika + "', "
	                + "'" + email + "', '" + brojTelefona+ "'";
	    }

	    @Override
	    public String vrednostiZaUpdate() {
	   return " email = '" + email + "', telefon = '" + brojTelefona + "' ";
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
		            Putnik p = new Putnik(rs.getLong("putnikID"),
		                    rs.getString("imePutnika"), rs.getString("prezimePutnika"),
		                    rs.getString("email"), rs.getString("brojTelefona"));

		            lista.add(p);
		        }

		        rs.close();
		        return lista;
		}
	    
}
