package xetareality.modules;

import org.junit.Test;
import xetareality.library.Config;
import xetareality.modules.models.TransactionData;
import xetareality.modules.models.TransactionRequest;
import xetareality.modules.models.WalletData;

import static org.junit.Assert.*;

public class WalletTest {

	@Test
	public void managed() {
		Config.dev = true;

		final WalletData wallet = Wallet.managed("shumyk", "ebec4ever", "true", null);
		assertNotNull(wallet);
		assertEquals("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", wallet.getPublicKey());
	}

	@Test
	public void testSign() {
		Config.dev = true;

		final TransactionData sign = Wallet.sign("shumyk", "ebec4ever", TransactionRequest.builder().build());
		assertNotNull(sign);
		assertEquals("J7ZH7xcjDNzkvme88QgFrM3MStgxpaWYhdqLJhnNWqnh", sign.getHash());
		assertEquals("4mKZ31GYCcauwdfHHjbpwQTd4xUMFocQQspgbQTHZQN1zLZjqVSNCjmKyQi75nMYs6HVirgG2hbEFGM2Ry9zXSUm", sign.getSignature());
	}
	// public
	// GVen9LScAPCxzGS4UmuBeW56KZvKpHvUDD12155FSr8x
	// private
	// 5E956PCmtDtLhrk24VbnuvhRn1Js7LfsYk6e3TQ9kTZ4

	// managed
	// public
	// Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY
	// private
	// 2fjNNm5R34W95FGiS6XoebRpeVx7SUPL5HjfwzPmZq3G
}