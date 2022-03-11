package xeta.library.crypto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bouncycastle.crypto.generators.SCrypt;
import xeta.library.hash.Base58;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Crypto {

	private static final String BRAINWALLET_REGEX = "^[a-zA-Z0-9-+@_\\.]*$";

	public static String generatePrivate() {
		return Base58.encode(ED25519.randomPrivateKey());
	}

	public static String generatePublic(String privateKey) {
		return Base58.encode(ED25519.getPublicKey(Base58.decode(privateKey)));
	}

	public static String[] generateKeyPair() {
		final ED25519.KeyPairBytes pair = ED25519.generateKeyPair();
		return new String[] {Base58.encode(pair.getPublicKey()), Base58.encode(pair.getPrivateKey())};
	}

	public static String sign(String message, String privateKey) {
		return Base58.encode(
			ED25519.sign(Base58.decode(message), Base58.decode(privateKey))
		);
	}

	public static boolean verify(String message, String signature, String publicKey) {
		return ED25519.verify(
			Base58.decode(signature),
			Base58.decode(message),
			Base58.decode(publicKey)
		);
	}

	/**
	 * Script implementation to generate brain wallet
	 * Uses account value as salt combined with secret password
	 */
	public static String brainwallet(String account, String secret) {
		if (account.length() < 6 || account.length() > 80 || !account.matches(BRAINWALLET_REGEX)) throw new RuntimeException("account:format");
		if (secret.length() < 6 || secret.length() > 80 || !secret.matches(BRAINWALLET_REGEX)) throw new RuntimeException("secret:format");

		final byte[] bytes = SCrypt.generate(
			secret.getBytes(StandardCharsets.UTF_8),
			account.getBytes(StandardCharsets.UTF_8),
			16384, 8, 1, 32
		);
		return Base58.encode(bytes);
	}
}
