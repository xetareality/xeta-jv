package xetareality.modules;

import org.junit.Before;
import org.junit.Test;
import xetareality.library.Config;
import xetareality.modules.models.AllowanceData;
import xetareality.modules.models.TransactionData;
import xetareality.modules.models.TransactionRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AllowanceTest {

	@Before
	public void before() {
		Config.dev = true;
		Config.publicKey = "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY";
		Config.privateKey = "2fjNNm5R34W95FGiS6XoebRpeVx7SUPL5HjfwzPmZq3G";
	}

	@Test
	public void update() {
		final TransactionData data = Allowance.update("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "11111111111111111111111111111xeta", "1", TransactionRequest.builder().build(), true);
		assertNotNull(data);
	}

	@Test
	public void read() {
		final AllowanceData allowance = Allowance.read("FcAASte8QmJUMoxScmbJ3vZD9LpcWetik3JSp1A8exEr");
		assertNotNull(allowance);
	}

	@Test
	public void list() {
		final List<AllowanceData> list = Allowance.list(Collections.singletonList("FcAASte8QmJUMoxScmbJ3vZD9LpcWetik3JSp1A8exEr"));
		assertEquals(1, list.size());
	}

	@Test
	public void readAddressSpenderToken() {
		final AllowanceData allowanceData = Allowance.readAddressSpenderToken("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "11111111111111111111111111111xeta");
		assertNotNull(allowanceData);
	}

	@Test
	public void scanAddressCreated() {
		final List<AllowanceData> data = Allowance.scanAddressCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", null, "FcAASte8QmJUMoxScmbJ3vZD9LpcWetik3JSp1A8exEr");
		assertNotNull(data);
		assertEquals(1, data.size());
	}

	@Test
	public void scanSpenderCreated() {
		final List<AllowanceData> data = Allowance.scanSpenderCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", null, "FcAASte8QmJUMoxScmbJ3vZD9LpcWetik3JSp1A8exEr");
		assertEquals(1, data.size());
	}
}