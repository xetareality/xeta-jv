package xeta.modules;

import org.junit.Before;
import org.junit.Test;
import xeta.library.Config;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;
import xeta.modules.models.TransferData;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class TransferTest {

	@Before
	public void before() {
		Config.dev = true;
		Config.publicKey = "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY";
		Config.privateKey = "2fjNNm5R34W95FGiS6XoebRpeVx7SUPL5HjfwzPmZq3G";
	}

	@Test
	public void create() {
		final TransactionData transactionData = Transfer.create("8eVYQCDDXsMRaPpxgeUZDJZ6Kes454owbrjkgwV3Rmgs", "11111111111111111111111111111xeta", "5", "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "for christmas", TransactionRequest.builder().build(), true);
		assertNotNull(transactionData);
	}
	// hash HqkmEnCdR9U5dUek1LaGH3BMki61thMZkTkAbMkFG61P
	//
	@Test
	public void read() {
		final TransferData transfer = Transfer.read("BLmbFUnUuDJPtXb6WJo4uUNtTSEKePgChBMgBN9HcTp6");
		assertNotNull(transfer);
		assertEquals("for christmas", transfer.getMessage());
	}

	@Test
	public void list() {
		final List<TransferData> list = Transfer.list(asList("BLmbFUnUuDJPtXb6WJo4uUNtTSEKePgChBMgBN9HcTp6", "Ctx9mNhtr62HtjukT2cvzaaqbp6BRyMsz7osz5UYCvaL"));
		assertEquals(2, list.size());
	}

	@Test
	public void scanSenderCreated() {
		final List<TransferData> transferData = Transfer.scanSenderCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", null, null);
		assertEquals(1, transferData.size());
	}

	@Test
	public void scanFromCreated() {
		final List<TransferData> transferData = Transfer.scanFromCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", null, null);
		assertEquals(1, transferData.size());
	}

	@Test
	public void scanToCreated() {
		final List<TransferData> transferData = Transfer.scanToCreated("8eVYQCDDXsMRaPpxgeUZDJZ6Kes454owbrjkgwV3Rmgs", null, null);
		assertEquals(7, transferData.size());
	}

	@Test
	public void scanTokenCreated() {
		final List<TransferData> transferData = Transfer.scanTokenCreated("11111111111111111111111111111xeta", null, null);
		assertEquals(25, transferData.size());
	}

	@Test
	public void scanFromTokenCreated() {
		final List<TransferData> transferData = Transfer.scanFromTokenCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "11111111111111111111111111111xeta", null, null);
		assertEquals(1, transferData.size());
	}

	@Test
	public void scanToTokenCreated() {
		final List<TransferData> transferData = Transfer.scanToTokenCreated("8eVYQCDDXsMRaPpxgeUZDJZ6Kes454owbrjkgwV3Rmgs", "11111111111111111111111111111xeta", null, null);
		assertEquals(5, transferData.size());
	}
}