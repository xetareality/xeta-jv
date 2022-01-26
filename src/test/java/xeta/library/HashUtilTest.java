package xeta.library;

import org.junit.Test;
import xeta.library.hash.HashUtil;
import xeta.modules.models.TransactionRequest;

import java.util.Collections;

import static org.junit.Assert.*;

public class HashUtilTest {

	@Test
	public void testTransaction() {
		final String transactionHash = HashUtil.transaction(
			TransactionRequest.builder()
				.sender("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY")
				.instructions(Collections.singletonList(
					MapBuilder.builder()
						.put("amount", "1")
						.put("spender", "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY")
						.put("function", "allowance.update")
						.put("token", "11111111111111111111111111111xeta")
						.build()
				))
				.nonce(1643125364L)
				.build()
		);
		assertEquals("DGRLRbCXS5nMrFefeN7CGU13qEcbJpMjditbpKikj4nx", transactionHash);
	}

	@Test
	public void testValues() {
		final String hash = HashUtil.values(new String[]{"Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "gifts"});
		assertEquals("F9vZm6FakhcwasyifycurJcmdcddMtnTBkP3n7BNW5Wk", hash);
		assertEquals("n7BNW5Wk", hash.substring(hash.length() - 8));
	}

	@Test
	public void testBalance() {
		final String hashBalance = HashUtil.balance("J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB", "6ZHDNt98wsSqnSpu6pLC5ukupdmMPgzFT23jE5nxYvaz");
		assertEquals("EDATEap53M63AzEq5XsjuvL9QmUbBeRswgHmqEijgSmT", hashBalance);
	}
}