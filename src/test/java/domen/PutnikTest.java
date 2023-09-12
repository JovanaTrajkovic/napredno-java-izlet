package domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PutnikTest {

	// private mora
	private Putnik putnik;

	@BeforeEach
	void setUp() throws Exception {
		putnik = new Putnik();
	}

	@AfterEach
	void tearDown() throws Exception {
		putnik = null;
	}

	@Test
	public void testKonstruktor() {

		putnik = new Putnik(1L, "Lea", "Leic", "lea@gmail.com", "069677920");
		assertEquals(1L, putnik.getPutnikID());
		assertEquals("Lea", putnik.getImePutnika());
		assertEquals("Leic", putnik.getPrezimePutnika());
		assertEquals("lea@gmail.com", putnik.getEmail());
		assertEquals("069677920", putnik.getBrojTelefona());

	}

	@Test
	void testID() {
		putnik.setPutnikID(1L);
		assertEquals(1, putnik.getPutnikID());
	}

	@Test
	void testEmail() {
		putnik.setEmail("jovana@gmail.com");
		assertEquals("jovana@gmail.com", putnik.getEmail());
	}

	@Test
	public void testSetEmailNUll() {
		assertThrows(java.lang.NullPointerException.class, () -> putnik.setEmail(null));
	}

	@Test
	public void testSetEmailNeispravno() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> putnik.setEmail("jo"));
	}

	@Test
	public void testIme() {
		putnik.setImePutnika("Jovana");
		assertEquals("Jovana", putnik.getImePutnika());
	}

	@Test
	public void testSetImeNeispravnoSazdrziBroj() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> putnik.setImePutnika("jagoda5"));
	}

	@Test
	public void testSetImeNeispravnoKraceod3Karaktera() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> putnik.setImePutnika("ja"));
	}

	@Test
	public void testSetImeNULL() {
		assertThrows(java.lang.NullPointerException.class, () -> putnik.setImePutnika(null));
	}

	@Test
	public void testPrezime() {
		putnik.setPrezimePutnika("Jagodic");
		assertEquals("Jagodic", putnik.getPrezimePutnika());
	}

	@Test
	public void testsetPrezimeNeispravnoSazdrziBroj() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> putnik.setPrezimePutnika("Jagodic5"));
	}

	@Test
	public void testSetPrezimeNeispravnoKraceod3Karaktera() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> putnik.setPrezimePutnika("Ja"));
	}

	@Test
	public void testSetPrezimeNULL() {
		assertThrows(java.lang.NullPointerException.class, () -> putnik.setPrezimePutnika(null));
	}

	////////
	@Test
	public void testBrojTelefona() {
		putnik.setBrojTelefona("069677920");
		assertEquals("069677920", putnik.getBrojTelefona());
	}

	@Test
	public void testBrojTelefonaNeipravnoViseOd10Cifara() {

		assertThrows(java.lang.IllegalArgumentException.class, () -> putnik.setBrojTelefona("06967792001"));
	}

	@Test
	public void testBrojTelefonaNeispravnoManjeod9Cifara() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> putnik.setBrojTelefona("06967792"));
	}

	@Test
	public void testSetBrojTelefonaNeipravnoSadrziKarakter() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> putnik.setBrojTelefona("069677920A"));
	}

	@Test
	public void testSetBrojTelefonaNeipravnoNULL() {
		assertThrows(java.lang.NullPointerException.class, () -> putnik.setBrojTelefona(null));
	}

	@Test
	public void testGetImeTabele() {
		assertEquals(" putnik ", putnik.nazivTabele());
	}

	@Test
	void testToString() {
		putnik = new Putnik(1L, "Lea", "Leic", "lea@gmail.com", "069677920");
		String s = putnik.toString();
		assertTrue(s.contains("lea@gmail.com"));
		assertTrue(s.contains("069677920"));
	}

	@Test
	void testAlijas() {
		String s = putnik.alijas();
		assertTrue(s.toLowerCase().contains("p"));
	}

	@Test
	void testJoin() {
		assertEquals("", putnik.join());
	}

	@Test
	void testKoloneZaInsert() {
		assertEquals(" (imePutnika, prezimePutnika, email, brojTelefona) ", putnik.koloneZaInsert());
	}

	@Test
	void testPrimarniKljuc() {
		putnik.setPutnikID(1L);
		String s = putnik.vrednostZaPrimarniKljuc();
		assertTrue(s.contains("1"));
	}

	@Test
	void testVrednostiZaInsert() {
		putnik.setBrojTelefona("069677920");
		putnik.setImePutnika("Jovana");
		putnik.setPrezimePutnika("Trajkovic");
		putnik.setEmail("jovana@gmail.com");
		putnik.setPutnikID(1L);

		String s = putnik.vrednostiZaInsert();

		assertTrue(s.contains("jovana@gmail.com"));
		assertTrue(s.contains("069677920"));
		assertTrue(s.contains("Jovana"));
		assertTrue(s.contains("Trajkovic"));
	}

	@Test
	void testVrednostiZaUpdate() {
		putnik.setBrojTelefona("069677920");
		putnik.setImePutnika("Jovana");
		putnik.setPrezimePutnika("Trajkovic");
		putnik.setEmail("jovana@gmail.com");
		putnik.setPutnikID(1L);

		String s = putnik.vrednostiZaUpdate();

		assertTrue(s.contains("jovana@gmail.com"));
		assertTrue(s.contains("069677920"));
		assertTrue(s.contains("Trajkovic"));
	}

	@Test
	void testUslov() {
		assertEquals("", putnik.uslov());
	}

}
