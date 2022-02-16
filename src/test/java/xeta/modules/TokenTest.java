package xeta.modules;

import org.junit.Before;
import org.junit.Test;
import xeta.library.Config;
import xeta.modules.models.ReadModel;
import xeta.modules.models.TokenData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TokenTest {

	@Before
	public void before() {
		Config.dev = true;
		Config.publicKey = "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY";
		Config.privateKey = "2fjNNm5R34W95FGiS6XoebRpeVx7SUPL5HjfwzPmZq3G";
	}

	@Test
	public void create() {
		final TransactionData transactionData = Token.create("testXeta", "TEST", "100000", null, null, null, null, null, null, null, null, null, null, null, null, TransactionRequest.builder().build(), true);
		assertNotNull(transactionData);
		assertNull(transactionData.getError());
	}

	@Test
	public void read() {
		final TokenData token = Token.read(ReadModel.builder().build(), "11111111111111111111111111111xeta");
		assertNotNull(token);
		assertEquals("11111111111111111111111111111xeta", token.getAddress());
		assertEquals("10000000000", token.getSupply());
		assertEquals("CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG", token.getCreator());
		assertEquals("XETA", token.getSymbol());
		assertEquals("Xeta", token.getName());
		assertEquals("11111111111111111111111111111111", token.getOrigin());
	}

	@Test
	public void list() {
		final List<TokenData> list = Token.list(Arrays.asList("11111111111111111111111111111xeta", "11111111111111111111111111consumed"));
		assertEquals(2, list.size());
	}

	@Test
	public void scanCreatorCreated() {
		final List<TokenData> tokenData = Token.scanCreatorCreated("CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG", null, null);
		assertEquals(4, tokenData.size());
	}

	@Test
	public void scanNameCreated() {
		final List<TokenData> tokenData = Token.scanNameCreated("Xeta", null, null);
		assertEquals(1, tokenData.size());
	}

	@Test
	public void scanSymbolCreated() {
		final List<TokenData> tokenData = Token.scanSymbolCreated("XETA", null, null);
		assertEquals(3, tokenData.size());
	}

	@Test
	public void scanOwnerCreated() {
		final List<TokenData> tokenData = Token.scanOwnerCreated("CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG", null, null);
		assertEquals(0, tokenData.size());
	}

	@Test
	public void scanContentCreated() {
		final List<TokenData> tokenData = Token.scanContentCreated("content", null, null);
		assertEquals(0, tokenData.size());
	}

	@Test
	public void scanOwnerCategoryCreated() {
		final List<TokenData> tokenData = Token.scanOwnerCategoryCreated("owner", "category", null, null);
		assertEquals(0, tokenData.size());
	}

	@Test
	public void scanCreatorCategoryCreated() {
		final List<TokenData> tokenData = Token.scanCreatorCategoryCreated("CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG", "category", null, null);
		assertEquals(0, tokenData.size());
	}
}