package xeta;

import org.junit.Before;
import org.junit.Test;
import xeta.library.Config;
import xeta.modules.Transfer;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import static org.junit.Assert.assertNotNull;

public class MainnetTest {

	@Before
	public void before() {
		Config.dev = false;
		Config.publicKey = "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY";
		Config.privateKey = "2fjNNm5R34W95FGiS6XoebRpeVx7SUPL5HjfwzPmZq3G";
	}

	@Test
	public void mainnetTransfer() {
		final TransactionData transactionData = Transfer.create("8eVYQCDDXsMRaPpxgeUZDJZ6Kes454owbrjkgwV3Rmgs", "11111111111111111111111111111xeta", "5", "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "mainnet test", TransactionRequest.builder().build(), true);
		assertNotNull(transactionData);
	}
}
