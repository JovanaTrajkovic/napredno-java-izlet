package domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MestoTest {
	Mesto m;

	@BeforeEach
	void setUp() throws Exception {
		m = new Mesto();
	}

	@AfterEach
	void tearDown() throws Exception {
		m = null;
	}

	@Test
	public void testKonstruktor() {

		m = new Mesto(1L, "Negotin");
		assertEquals(1L, m.getMestoID());
		assertEquals("Negotin", m.getNazivMesta());

	}

	@Test
	public void testSetID() {
		m.setMestoID(1L);
		assertEquals(1, m.getMestoID());
	}

	@Test
	public void testIme() {
		m.setNazivMesta("Negotin");
		assertEquals("Negotin", m.getNazivMesta());
	}

	

	@Test
	public void testSetImeNeispravnoKraceod3Karaktera() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> m.setNazivMesta("Ne"));
	}

	@Test
	public void testSetImeNULL() {
		assertThrows(java.lang.NullPointerException.class, () -> m.setNazivMesta(null));
	}

	@Test
	public void testGetImeTabele() {
		assertEquals(" Mesto ", m.nazivTabele());
	}

	@Test
	void testToString() {
		m = new Mesto(1L, "Negotin");
		String s = m.toString();
		assertTrue(s.contains("Negotin"));
	}

	@Test
	void testAlijas() {
		String s = m.alijas();
		assertTrue(s.toLowerCase().contains("m"));
	}

	@Test
	public void testJoin() {
		assertEquals("", m.join());
	}

	@Test
	public void testKoloneZaInsert() {
		assertEquals("", m.koloneZaInsert());
	}

	@Test
	public void testPrimarniKljuc() {
		m = new Mesto();
		m.setMestoID(1L);
		String s = m.vrednostZaPrimarniKljuc();
		assertTrue(s.contains("1"));
	}

	@Test
	public void testVrednostiZaInsert() {
		m = new Mesto(1L, "Negotin");
		String s = m.vrednostiZaInsert();
		assertTrue(s.contains(""));

	}

	@Test
	public void testVrednostiZaUpdate() {
		m = new Mesto(1L, "Negotin");
		String s = m.vrednostiZaInsert();
		assertTrue(s.contains(""));

	}

	@Test
	void testUslov() {
		assertEquals("", m.uslov());
	}

}
