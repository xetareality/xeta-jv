package xetareality.modules;

import com.google.gson.Gson;
import org.junit.Test;
import xetareality.library.MapBuilder;
import xetareality.modules.models.ScanModel;
import xetareality.modules.models.TransactionData;
import xetareality.modules.models.TransactionRequest;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionTest {

	@Test
	public void testScanSenderCreated() {
		final List<TransactionData> transactionData = Transaction.scanSenderCreated(
			ScanModel.builder().build(),
			"8eVYQCDDXsMRaPpxgeUZDJZ6Kes454owbrjkgwV3Rmgs",
			null, null
		);
		assertNotNull(transactionData);
		assertEquals(25, transactionData.size());
	}

	@Test
	public void testGsonTransactionRequest() {
		final String json = new Gson().toJson(
			TransactionRequest.builder()
				.hash(null)
				.signature("signature33333")
				.sender("somedude32323")
				.instructions(
					asList(
						MapBuilder.builder()
							.put("first", "fvalue")
							.put("second", "svalue")
							.build()
					)
				)
				.nonce(1642856672018L)
		);
		assertEquals("{\"signature\":\"signature33333\",\"sender\":\"somedude32323\",\"instructions\":[{\"first\":\"fvalue\",\"second\":\"svalue\"}],\"nonce\":1642856672018}", json);
	}
}