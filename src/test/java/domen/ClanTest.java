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

class ClanTest {
	Clan clan;

	@BeforeEach
	void setUp() throws Exception {
		clan = new Clan();
	}

	@AfterEach
	void tearDown() throws Exception {
		clan = null;
	}

	@Test
	public void testKonstruktor() {
		ArrayList<Clan> clanovi = new ArrayList<>();
		Putnik p1 = new Putnik(2L, "Jovana", "Trajkovic", "jovana@gmail.com", "069677920");

		Grupa g = new Grupa();
		g.setGrupaID(1L);
		clan = new Clan(g, 1, "Spojiti sa Perom u busu", true, p1);

		assertEquals(1L, clan.getGrupa().getGrupaID());
		assertEquals(1, clan.getRbClana());
		assertEquals("Spojiti sa Perom u busu", clan.getNapomena());
		assertEquals(true, clan.isFakultativniIzleti());
		assertEquals(2L,clan.getPutnik().getPutnikID());

	}

	@Test
	public void testRbClana() {
		clan.setRbClana(1);
		assertEquals(1, clan.getRbClana());
	}

	@Test
	public void testPutnik() {
		Putnik p1 = new Putnik(1L, "Jovana", "Trajkovic", "jovana@gmail.com", "069677920");
		clan.setPutnik(p1);
		assertEquals(1L, clan.getPutnik().getPutnikID());
	}

	@Test
	public void testFakultativniIzleti() {
		boolean fakultativno = true;
		clan.setFakultativniIzleti(fakultativno);
		assertEquals(true, clan.isFakultativniIzleti());
	}

	@Test
	public void testNapomena() {
		String napomena = "Nema";
		clan.setNapomena(napomena);
		assertEquals("Nema", clan.getNapomena());

	}

	@Test
	public void testGrupa() {
		Grupa g = new Grupa();
		g.setGrupaID(1L);
		clan.setGrupa(g);
		assertEquals(1L, clan.getGrupa().getGrupaID());

	}

	@Test
	public void testGetImeTabele() {
		assertEquals("Clan", clan.nazivTabele());
	}

	@Test
	void testToString() {

		Putnik p1 = new Putnik(1L, "Jovana", "Trajkovic", "jovana@gmail.com", "069677920");
		clan.setPutnik(p1);
		String s = clan.toString();
		assertTrue(s.contains("Jovana"));
		assertTrue(s.contains("Trajkovic"));

	}

	@Test
	void testAlijas() {
		String s = clan.alijas();
		assertTrue(s.toLowerCase().contains("c"));
	}

	@Test
	public void testJoin() {
		assertEquals("  JOIN putnik p USING (putnikID) " + "JOIN grupa g USING (grupaID) "
				+ "JOIN izlet i ON (i.izletID = g.izletID) " + "JOIN mesto m ON (m.mestoID = i.mestoID) "
				+ "JOIN  vodic v ON (v.vodicID= g.vodicID) "
				+ "JOIN administrator a ON (a.administratorID= g.administratorID) ", clan.join());
	}

	@Test
	public void testKoloneZaInsert() {
		assertEquals(" (grupaID, rbClana, napomena,fakultativniIzleti, putnikID) ", clan.koloneZaInsert());
	}

	@Test
	public void testPrimarniKljuc() {
		Grupa g = new Grupa();
		g.setGrupaID(1L);
		clan.setGrupa(g);

		String s = clan.vrednostZaPrimarniKljuc();
		assertTrue(s.contains("1"));
	}

	@Test
	public void testVrednostiZaInsert() {
		Grupa g = new Grupa();
		g.setGrupaID(1L);
		clan.setGrupa(g);
		clan.setRbClana(2);
		Putnik p1 = new Putnik(3L, "Jovana", "Trajkovic", "jovana@gmail.com", "069677920");
		clan.setPutnik(p1);

		String s = clan.vrednostiZaInsert();
		assertTrue(s.contains("1"));
		assertTrue(s.contains("3"));
		assertTrue(s.contains("2"));

	}

	@Test
	public void testVrednostiZaUpdate() {

		String s = clan.vrednostiZaUpdate();
		System.out.println(s);
		assertTrue(s.contains(""));

	}

	@Test
	void testUslov() {
		Grupa g = new Grupa();
		g.setGrupaID(1L);
		clan.setGrupa(g);
		assertEquals(" WHERE g.grupaID = 1", clan.uslov());
	}

}
