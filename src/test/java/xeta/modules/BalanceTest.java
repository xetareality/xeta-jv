package xeta.modules;

import org.junit.Before;
import org.junit.Test;
import xeta.library.Config;
import xeta.modules.models.BalanceData;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class BalanceTest {

	@Before
	public void before() {
		Config.dev = true;
		Config.publicKey = "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY";
		Config.privateKey = "2fjNNm5R34W95FGiS6XoebRpeVx7SUPL5HjfwzPmZq3G";
	}

	@Test
	public void read() {
		final BalanceData read = Balance.read("8uAfnQzDsx5HruwXQfGShwSkQr3vBYpAr8JQzDD1VGe");
		assertNotNull(read);
	}

	@Test
	public void readAddressToken() {
		final BalanceData balance = Balance.readAddressToken("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "11111111111111111111111111111xeta");
		assertNotNull(balance);
	}

	@Test public void list() {
		final List<BalanceData> list = Balance.list(asList("9qDSn6Ba5LQs7uw6F3Kse2AkKZWUVW66rVNJfLY73RVo", "8uAfnQzDsx5HruwXQfGShwSkQr3vBYpAr8JQzDD1VGe"));
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals(2, list.size());
	}

	@Test
	public void scanAddressAmount() {
		final List<BalanceData> balances = Balance.scanAddressAmount(
			"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG",
			null, null
		);
		assertEquals(3, balances.size());
	}

	@Test
	public void scanTokenAmount() {
		final List<BalanceData> balanceData = Balance.scanTokenAmount("11111111111111111111111111111xeta", null, "9qDSn6Ba5LQs7uw6F3Kse2AkKZWUVW66rVNJfLY73RVo");
		assertNotNull(balanceData);
		assertEquals(25, balanceData.size());
	}
}