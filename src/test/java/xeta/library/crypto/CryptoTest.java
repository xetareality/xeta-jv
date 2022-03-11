package xeta.library.crypto;

import org.junit.Test;

import static org.junit.Assert.*;

public class CryptoTest {

	@Test
	public void brainwallet() {
		assertEquals("8Z6qDXPCKtn2cEAk97uQd4VVFSgELqyGFdfD48uDPgPp", Crypto.brainwallet("xetaxetaxeta", "444444444444"));
	}
}