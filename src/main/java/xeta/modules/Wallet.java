package xeta.modules;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.*;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;
import xeta.modules.models.WalletData;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Wallet {

	/**
	 * Set public and private key
	 */
	public static void init(String publicKey, String privateKey) {
		Config.publicKey = publicKey;
		Config.privateKey = privateKey;
	}

	/**
	 * Connect to managed wallet
	 */
	public static WalletData managed(String account, String secret, String unsafe, String create) {
		final Map<String, String> strippedBody = StripUtil.stripNulls(
			MapBuilder.builder()
				.put("account", account)
				.put("secret", secret)
				.put("unsafe", unsafe)
				.put("create", create)
				.build()
		);
		final String walletResponse = HttpUtil.POST(
			Config.xetaInterface + "/wallet" + (Config.dev ? "?dev=1" : ""),
			GeneralUtil.toJson(strippedBody)
		);

		final WalletData wallet = new Gson().fromJson(walletResponse, WalletData.class);
		Wallet.init(wallet.publicKey, wallet.privateKey);

		return wallet;
	}

	/**
	 * Sign transaction with managed wallet
	 * Returns transaction with signature
	 */
	public static TransactionData sign(String account, String secret, TransactionRequest tx) {
		Models.validFormats(tx);

		final Map<String, String> body = MapBuilder.builder()
			.put("account", account)
			.put("secret", secret)
			.put("transaction", GeneralUtil.toJson(tx))
			.build();
		final String rawResponse = HttpUtil.POST(
			Config.xetaInterface + "/sign" + (Config.dev ? "?dev=1" : ""),
			GeneralUtil.toJson(body)
		);
		return new Gson().fromJson(rawResponse, TransactionData.class);
	}

}
