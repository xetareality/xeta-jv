package xeta.library.crypto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.hash.Base58;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Crypto {

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
}
