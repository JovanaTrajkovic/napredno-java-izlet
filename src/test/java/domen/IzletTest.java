package domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IzletTest {
	private Izlet i;

	@BeforeEach
	void setUp() throws Exception {
		i = new Izlet();
	}

	@AfterEach
	void tearDown() throws Exception {
		i = null;
	}

	@Test
	public void testKonstruktor() {
		Mesto m = new Mesto(1L, "Negotin");
		i = new Izlet(1L, 1500, Kategorija.VanSezone, m);
		assertEquals(1L, i.getIzletID());
		assertEquals(1500, i.getCena());
		assertEquals(Kategorija.VanSezone, i.getKategorija());
		assertEquals(1L, i.getMesto().getMestoID());

	}

	@Test
	public void testCenaIspravno() {
		i.setCena(1500);
		assertEquals(1500, i.getCena());
	}

	@Test
	public void testCenaManjaod1000() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> i.setCena(900));

	}

	@Test
	public void testSetMesto() {
		Mesto m = new Mesto(1L, "Negotin");
		i = new Izlet();
		i.setMesto(m);
		assertEquals("Negotin", m.getNazivMesta());
		assertEquals(1L, m.getMestoID());
	}

	@Test
	public void testKategorija() {
		i.setKategorija(Kategorija.VanSezone);
		assertEquals(Kategorija.VanSezone, i.getKategorija());
	}

	@Test
	public void testiD() {
		i.setIzletID(1L);
		assertEquals(1, i.getIzletID());
	}

	@Test
	public void testGetImeTabele() {
		assertEquals(" izlet ", i.nazivTabele());
	}

	@Test
	void testToString() {
		Mesto m = new Mesto(1L, "Negotin");
		i = new Izlet(1L, 1500, Kategorija.VanSezone, m);
		String s = i.toString();
		assertTrue(s.contains("Negotin"));
		assertEquals(i.getCena(), 1500);
	}

	@Test
	void testAlijas() {
		String s = i.alijas();
		assertTrue(s.toLowerCase().contains("i"));
	}

	@Test
	public void testJoin() {
		assertEquals(" JOIN mesto m  ON (m.mestoID= i.mestoID) ", i.join());
	}

	@Test
	public void testKoloneZaInsert() {
		assertEquals("", i.koloneZaInsert());
	}

	@Test
	public void testPrimarniKljuc() {
		i.setIzletID(1L);

		String s = i.vrednostZaPrimarniKljuc();

		assertTrue(s.contains("1"));
	}

	@Test
	public void testVrednostiZaInsert() {
		Mesto m = new Mesto(1L, "Negotin");
		i = new Izlet(1L, 1500, Kategorija.VanSezone, m);
		String s = i.vrednostiZaInsert();

		assertTrue(s.contains(""));

	}

	@Test
	public void testVrednostiZaUpdate() {
		Mesto m = new Mesto(1L, "Negotin");
		i = new Izlet(1L, 1500, Kategorija.VanSezone, m);
		String s = i.vrednostiZaInsert();

		assertTrue(s.contains(""));

	}

	@Test
	void testUslov() {
		assertEquals("", i.uslov());
	}

}
