package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author Jovana
 *
 */
public class AdministratorTest {

	// private mora
	private Administrator admin;

	@BeforeEach
	void setUp() throws Exception {
		admin = new Administrator();
	}

	@AfterEach
	void tearDown() throws Exception {
		admin = null;
	}

	// public!!!!
	@Test
	public void testKonstruktor() {
		admin = new Administrator(123L, "Pera", "Peric", "pera", "pera123");
		assertEquals(123L, admin.getAdministratorID());
		assertEquals("Pera", admin.getIme());
		assertEquals("Peric", admin.getPrezime());
		assertEquals("pera", admin.getUsername());
		assertEquals("pera123", admin.getPassword());
	}

	@Test
	void testID() {
		admin.setAdministratorID(1L);
		assertEquals(1, admin.getAdministratorID());
	}	
	
	@Test
	void testUsername() {
		admin.setUsername("jovana");
		assertEquals("jovana", admin.getUsername());
	}

	@Test
	public void testSetUsernameNUll() {
		assertThrows(java.lang.NullPointerException.class, () -> admin.setUsername(null));
	}

	@Test
	public void testSetUsernameNeispravno() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> admin.setUsername("mi"));
	}

	@Test
	void testPassword() {
		admin.setPassword("jovana123");
		assertEquals("jovana123", admin.getPassword());
	}

	@Test
	public void testSetPasswordNeispravno() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> admin.setPassword("mina"));
		assertThrows(java.lang.IllegalArgumentException.class, () -> admin.setPassword(""));
	}

	@Test
	public void testSetPasswordNUll() {
		assertThrows(java.lang.NullPointerException.class, () -> admin.setPassword(null));
	}

	@Test
	public void testIme() {
		admin.setIme("Jovana");

		assertEquals("Jovana", admin.getIme());
	}

	@Test
	public void testSetImeNeispravno() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> admin.setIme("Mi"));
		assertThrows(java.lang.IllegalArgumentException.class, () -> admin.setIme(""));
	}

	@Test
	public void testSetImeNUll() {
		assertThrows(java.lang.NullPointerException.class, () -> admin.setIme(null));
	}

	@Test
	void testPrezime() {
		admin.setPrezime("Jocic");
		assertEquals("Jocic", admin.getPrezime());
	}

	@Test
	public void testGetImeTabele() {
		assertEquals("administrator", admin.nazivTabele());
	}

	@Test
	public void testToString() {
		admin = new Administrator(1l, "Joca", "Jocic", "joca", "joca123");
		String s = admin.toString();
		assertTrue(s.contains("Joca"));
		assertTrue(s.contains("Jocic"));
	}

	@Test
	public void testAlijas() {
		String s = admin.alijas();
		assertTrue(s.toLowerCase().contains("a"));
	}

	@Test
	public void testJoin() {
		assertEquals("", admin.join());
	}

	@Test
	public void testKoloneZaInsert() {
		assertEquals(" (ime, prezime, username, password) ", admin.koloneZaInsert());
	}

	@Test
	public void testPrimarniKljuc() {
		admin.setAdministratorID(1l);

		String s = admin.vrednostZaPrimarniKljuc();

		assertTrue(s.contains("1"));
	}

	@Test
	public void testVrednostiZaInsert() {
		admin.setUsername("joca");
		admin.setPassword("joca123");
		admin.setIme("Joca");
		admin.setPrezime("Jocic");

		String s = admin.vrednostiZaInsert();

		assertTrue(s.contains("joca"));
		assertTrue(s.contains("joca123"));
		assertTrue(s.contains("Joca"));
		assertTrue(s.contains("Jocic"));
	}

	@Test
	public void testVrednostiZaUpdate() {
		admin.setUsername("joca");
		admin.setPassword("joca123");
		admin.setIme("Joca");
		admin.setPrezime("Jocic");

		String s = admin.vrednostiZaUpdate();

		assertTrue(s.contains("joca"));
		assertTrue(s.contains("joca123"));
		assertTrue(s.contains("Joca"));
		assertTrue(s.contains("Jocic"));
	}

	@Test
	public void testUslov() {
		assertEquals("", admin.uslov());
	}

}
