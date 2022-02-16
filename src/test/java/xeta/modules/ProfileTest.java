package xeta.modules;

import org.junit.Before;
import org.junit.Test;
import xeta.library.Config;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import static org.junit.Assert.*;

public class ProfileTest {

	@Before
	public void before() {
		Config.dev = true;
		Config.publicKey = "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY";
		Config.privateKey = "2fjNNm5R34W95FGiS6XoebRpeVx7SUPL5HjfwzPmZq3G";
	}

	@Test
	public void update() {
		final TransactionData update = Profile.update("shumykprofile", "test profile", null, null, null, "shumykcategory", TransactionRequest.builder().build(), true);
		assertNotNull(update);
		assertNull(update.getError());
	}
}