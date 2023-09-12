package domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VodicTest {
	Vodic vodic;

	@BeforeEach
	void setUp() throws Exception {
		vodic = new Vodic();
	}

	@AfterEach
	void tearDown() throws Exception {
		vodic = null;
	}

	@Test
	public void testKonstruktor() {

		vodic = new Vodic(1L, "Ana", "Aleksic", "069677920", 12);
		assertEquals(1L, vodic.getVodicID());
		assertEquals("Ana", vodic.getImeVodica());
		assertEquals("069677920", vodic.getBrojTelefona());
		assertEquals("Aleksic", vodic.getPrezimeVodica());
		assertEquals(12, vodic.getGodineIskustva());

	}

	@Test
	public void testGodineIksustvaIspravno() {
		vodic.setGodineIskustva(12);
		assertEquals(12, vodic.getGodineIskustva());
	}

	@Test
	public void testGodineIskustvaManjeod0() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> vodic.setGodineIskustva(-1));

	}

	@Test
	public void testiD() {
		vodic.setVodicID(1L);
		assertEquals(1, vodic.getVodicID());
	}

	@Test
	public void testIme() {
		vodic.setImeVodica("Jovana");
		assertEquals("Jovana", vodic.getImeVodica());
	}

	@Test
	public void testSetImeNeispravnoSazdrziBroj() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> vodic.setImeVodica("jagoda5"));
	}

	@Test
	public void testSetImeNeispravnoKraceod3Karaktera() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> vodic.setImeVodica("ja"));
	}

	@Test
	public void testSetImeNULL() {
		assertThrows(java.lang.NullPointerException.class, () -> vodic.setImeVodica(null));
	}

	@Test
	public void testPrezime() {
		vodic.setPrezimeVodica("Jagodic");
		assertEquals("Jagodic", vodic.getPrezimeVodica());
	}

	@Test
	public void testsetPrezimeNeispravnoSazdrziBroj() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> vodic.setPrezimeVodica("Jagodic5"));
	}

	@Test
	public void testSetPrezimeNeispravnoKraceod3Karaktera() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> vodic.setPrezimeVodica("Ja"));
	}

	@Test
	public void testSetPrezimeNULL() {
		assertThrows(java.lang.NullPointerException.class, () -> vodic.setPrezimeVodica(null));
	}

	////////
	@Test
	public void testBrojTelefona() {
		vodic.setBrojTelefona("069677920");
		assertEquals("069677920", vodic.getBrojTelefona());
	}

	@Test
	public void testBrojTelefonaNeipravnoViseOd10Cifara() {

		assertThrows(java.lang.IllegalArgumentException.class, () -> vodic.setBrojTelefona("06967792001"));
	}

	@Test
	public void testBrojTelefonaNeispravnoManjeod9Cifara() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> vodic.setBrojTelefona("06967792"));
	}

	@Test
	public void testSetBrojTelefonaNeipravnoSadrziKarakter() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> vodic.setBrojTelefona("069677920A"));
	}

	@Test
	public void testSetBrojTelefonaNeipravnoNULL() {
		assertThrows(java.lang.NullPointerException.class, () -> vodic.setBrojTelefona(null));
	}

///////////
	@Test
	public void testGetImeTabele() {
		assertEquals(" vodic ", vodic.nazivTabele());
	}

	@Test
	void testToString() {

		vodic = new Vodic(1L, "Ana", "Aleksic", "069677920", 12);
		String s = vodic.toString();
		assertTrue(s.contains("Ana"));
		assertTrue(s.contains("Aleksic"));
	}

	@Test
	void testAlijas() {
		String s = vodic.alijas();
		assertTrue(s.toLowerCase().contains("v"));
	}

	@Test
	public void testJoin() {
		assertEquals("", vodic.join());
	}

	@Test
	public void testKoloneZaInsert() {
		assertEquals("", vodic.koloneZaInsert());
	}

	@Test
	public void testPrimarniKljuc() {
		vodic.setVodicID(1L);
		String s = vodic.vrednostZaPrimarniKljuc();
		assertTrue(s.contains("1"));
	}

	@Test
	public void testVrednostiZaInsert() {

		String s = vodic.vrednostiZaInsert();
		assertTrue(s.contains(""));

	}

	@Test
	public void testVrednostiZaUpdate() {

		String s = vodic.vrednostiZaUpdate();
		assertTrue(s.contains(""));

	}

	@Test
	void testUslov() {
		assertEquals("", vodic.uslov());
	}

}
