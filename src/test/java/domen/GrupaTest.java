package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrupaTest {
	Grupa g;

	@BeforeEach
	void setUp() throws Exception {
		g = new Grupa();
	}

	@AfterEach
	public void tearDown() throws Exception {
		g = null;
	}

	@Test
	public void testKonstruktor() {
		Administrator a = new Administrator(123L, "Pera", "Peric", "pera", "pera123");
		Vodic v = new Vodic(1L, "Ana", "Aleksic", "069677920", 12);
		Mesto m = new Mesto(1L, "Negotin");
		Izlet i = new Izlet(1L, 1500, Kategorija.VanSezone, m);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date d = null;
		try {
			d = sdf.parse("10.10.2100");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<Clan> clanovi = new ArrayList<>();
		Putnik p1 = new Putnik(2L, "Jovana", "Trajkovic", "jovana@gmail.com", "069677920");
		Putnik p2 = new Putnik(3L, "Pera", "Peric", "pera@gmail.com", "0696891021");

		g = new Grupa(1L, d, 20, "Bus1", i, v, a, clanovi);
		Clan clan1 = new Clan(g, 1, "Spojiti sa Perom u busu", true, p1);
		clanovi.add(clan1);
		Clan clan2 = new Clan(g, 2, "", true, p2);
		clanovi.add(clan2);
		assertEquals(1L, g.getGrupaID());
		assertEquals("Bus1", g.getPrevoz());
		assertEquals(d, g.getDatumPolaska());
		assertEquals(20, g.getKapacitetGrupe());
		assertEquals(123L, g.getAdministrator().getAdministratorID());
		assertEquals(1L, g.getVodic().getVodicID());
		assertEquals(1L, g.getIzlet().getIzletID());
		assertEquals(2, clanovi.size());
		assertEquals(1, g.getClanovi().get(0).getRbClana());
		assertEquals(2, g.getClanovi().get(1).getRbClana());

	}

	@Test
	public void testiD() {
		g.setGrupaID(1L);
		assertEquals(1, g.getGrupaID());
	}

	@Test
	public void testIzlet() {
		Mesto m = new Mesto(1L, "Negotin");
		Izlet i = new Izlet(1L, 1500, Kategorija.VanSezone, m);
		g.setIzlet(i);
		assertEquals(1, g.getIzlet().getIzletID());
	}

	@Test
	public void testVodic() {
		Vodic v = new Vodic(1L, "Ana", "Aleksic", "069677920", 12);
		g.setVodic(v);
		assertEquals(1, g.getVodic().getVodicID());
	}

	@Test
	public void testAdministrator() {
		Administrator a = new Administrator(123L, "Pera", "Peric", "pera", "pera123");
		g.setAdministrator(a);
		assertEquals(123L, g.getAdministrator().getAdministratorID());
	}

	@Test
	public void testClanoviIspravno() {
		g.setGrupaID(1L);
		ArrayList<Clan> clanovi = new ArrayList<>();
		Putnik p1 = new Putnik(1L, "Jovana", "Trajkovic", "jovana@gmail.com", "069677920");
		Putnik p2 = new Putnik(2L, "Pera", "Peric", "pera@gmail.com", "0696891021");
		Clan clan1 = new Clan(g, 1, "Spojiti sa Perom u busu", true, p1);
		clanovi.add(clan1);
		Clan clan2 = new Clan(g, 2, "", true, p2);
		clanovi.add(clan2);

		g.setClanovi(clanovi);

		assertEquals(1, g.getClanovi().get(0).getGrupa().getGrupaID());
		assertEquals(1, g.getClanovi().get(1).getGrupa().getGrupaID());
	}

	@Test
	public void testSetKapacitetManjiOD15() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> g.setKapacitetGrupe(10));
	}

	@Test
	public void testSetKapacitetVeciod60() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> g.setKapacitetGrupe(90));
	}

	@Test
	public void testKapactetIspravno() {
		g.setKapacitetGrupe(20);
		assertEquals(20, g.getKapacitetGrupe());

	}

	@Test
	public void testIspravnoVreme() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date d = null;
		try {
			d = sdf.parse("10.10.2100");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		g.setDatumPolaska(d);
		assertEquals(d, g.getDatumPolaska());
	}

	@Test
	public void testDatumNull() {
		assertThrows(java.lang.NullPointerException.class, () -> g.setDatumPolaska(null));
	}

	@Test
	public void testNeispravanDatumuProslosti() {
		Date datum = Date.from(LocalDate.of(2016, 9, 9).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		assertThrows(java.lang.IllegalArgumentException.class, () -> g.setDatumPolaska(datum));
	}

	@Test
	public void testGetImeTabele() {
		assertEquals(" grupa ", g.nazivTabele());
	}

	@Test
	void testToString() {
		g.setPrevoz("Bus1");
		Vodic v = new Vodic();
		v.setImeVodica("Hana");
		g.setVodic(v);
		String s = g.toString();
		assertTrue(s.contains("Bus1"));
		assertTrue(s.contains("Hana"));

	}

	@Test
	void testAlijas() {
		String s = g.alijas();
		assertTrue(s.toLowerCase().contains("g"));
	}

	@Test
	public void testJoin() {
		assertEquals(" JOIN izlet i ON (i.izletID = g.izletID) " + "JOIN mesto m ON (m.mestoID = i.mestoID) "
				+ "JOIN vodic v ON (v.vodicID = g.vodicID) "
				+ "JOIN administrator a ON (a.administratorID = g.administratorID)", g.join());
	}

	@Test
	public void testKoloneZaInsert() {
		assertEquals(" (datumPolaska,kapacitetGrupe,prevoz,izletID, " + "vodicID, administratorID) ",
				g.koloneZaInsert());
	}

	@Test
	public void testPrimarniKljuc() {
		g.setGrupaID(1L);
		String s = g.vrednostZaPrimarniKljuc();
		assertTrue(s.contains("1"));
	}

	@Test
	public void testVrednostiZaInsert() {
		Administrator a = new Administrator(123L, "Pera", "Peric", "pera", "pera123");
		Vodic v = new Vodic(2L, "Ana", "Aleksic", "069677920", 12);
		Mesto m = new Mesto(1L, "Negotin");
		Izlet i = new Izlet(1L, 1500, Kategorija.VanSezone, m);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date d = null;
		try {
			d = sdf.parse("10.10.2100");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<domen.Clan> clanovi = new ArrayList<>();
		Putnik p1 = new Putnik(2L, "Jovana", "Trajkovic", "jovana@gmail.com", "069677920");
		Putnik p2 = new Putnik(3L, "Pera", "Peric", "pera@gmail.com", "0696891021");

		g = new Grupa(1L, d, 20, "Bus1", i, v, a, clanovi);
		domen.Clan clan1 = new domen.Clan(g, 1, "Spojiti sa Perom u busu", true, p1);
		clanovi.add(clan1);
		domen.Clan clan2 = new domen.Clan(g, 2, "", true, p2);
		clanovi.add(clan2);
		String s = g.vrednostiZaInsert();
		assertTrue(s.contains("2"));
		assertTrue(s.contains("123"));
		assertTrue(s.contains("1"));
		assertTrue(s.contains("Bus1"));

	}

	@Test
	public void testVrednostiZaUpdate() {
		Administrator a = new Administrator(123L, "Pera", "Peric", "pera", "pera123");
		Vodic v = new Vodic(1L, "Ana", "Aleksic", "069677920", 12);
		Mesto m = new Mesto(1L, "Negotin");
		Izlet i = new Izlet(1L, 1500, Kategorija.VanSezone, m);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date d = null;
		try {
			d = sdf.parse("10.10.2100");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<domen.Clan> clanovi = new ArrayList<>();
		Putnik p1 = new Putnik(2L, "Jovana", "Trajkovic", "jovana@gmail.com", "069677920");
		Putnik p2 = new Putnik(3L, "Pera", "Peric", "pera@gmail.com", "0696891021");

		g = new Grupa(1L, d, 20, "Bus1", i, v, a, clanovi);
		domen.Clan clan1 = new domen.Clan(g, 1, "Spojiti sa Perom u busu", true, p1);
		clanovi.add(clan1);
		domen.Clan clan2 = new domen.Clan(g, 2, "", true, p2);
		clanovi.add(clan2);
		String s = g.vrednostiZaUpdate();
		System.out.println(s);
		assertTrue(s.contains("1"));
		assertTrue(s.contains("Bus1"));

	}

	@Test
	void testUslov() {
		assertEquals("", g.uslov());
	}

}
