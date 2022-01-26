package xeta.modules;

import org.junit.Test;
import xeta.library.Config;

import static org.junit.Assert.*;

public class AddressTest {

	@Test
	public void testRead() {
		final String read = Address.read("5d1KYTwseBGzwuroT3wm5793pZ1fAXRmY4e9tBNErwYk");
		System.out.println(read);
		assertEquals("{\"balance\":{\"address\":\"5d1KYTwseBGzwuroT3wm5793pZ1fAXRmY4e9tBNErwYk\",\"xetaBalance\":\"1000\"}}", read);

		// below is on testnet
		Config.dev = true;
		final String read1 = Address.read("3evjhDg7u6tAVnUL6GK6q288GpNdNdPv9te446upsfdd");
		System.out.println(read1);
		assertEquals("{\"balance\":{\"address\":\"3evjhDg7u6tAVnUL6GK6q288GpNdNdPv9te446upsfdd\",\"xetaBalance\":\"1000\"}}", read1);

		Config.dev = false;
	}
}