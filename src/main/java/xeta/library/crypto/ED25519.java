package xeta.library.crypto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.ExceptionWrappers;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ED25519 {

	private static final String ED_25519 = "Ed25519";

	public static byte[] randomPrivateKey() {
		return randomPrivateKeyObject().getEncoded();
	}
	public static PrivateKey randomPrivateKeyObject() {
		return ExceptionWrappers.wrapExceptions(() -> {
			final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ED_25519);
			final KeyPair keyPair = keyPairGenerator.generateKeyPair();
			return keyPair.getPrivate();
		});
	}

	public static byte[] getPublicKey(byte[] serializedKey) {
		return bytesToPublicKey(serializedKey).getEncoded();
	}

	public static byte[] sign(byte[] message, byte[] privateKey) {
		return ExceptionWrappers.wrapExceptions(() -> {
			final Signature ed25519 = Signature.getInstance(ED_25519);
			ed25519.initSign(bytesToPrivateKey(privateKey));
			ed25519.update(message);
			return ed25519.sign();
		});
	}

	public static boolean verify(byte[] signature, byte[] message, byte[] publicKey) {
		return ExceptionWrappers.wrapExceptions(() -> {
			final Signature ed25519 = Signature.getInstance(ED_25519);
			ed25519.initVerify(bytesToPublicKey(publicKey));
			ed25519.update(message);
			return ed25519.verify(signature);
		});
	}


	private static PublicKey bytesToPublicKey(byte[] pk) {
		return ExceptionWrappers.wrapExceptions(() -> {
			final KeyFactory keyFactory = KeyFactory.getInstance(ED_25519);

			// determine if x was odd.
			boolean xisodd = false;
			int lastbyteInt = pk[pk.length - 1];
			if ((lastbyteInt & 255) >> 7 == 1) {
				xisodd = true;
			}
			// make sure most significant bit will be 0 - after reversing.
			pk[pk.length - 1] &= 127;
			// reverse the byte array
			for(int i = 0; i < pk.length / 2; i++) {
				byte temp = pk[i];
				pk[i] = pk[pk.length - i - 1];
				pk[pk.length - i - 1] = temp;
			}
			BigInteger y = new BigInteger(1, pk);

			final NamedParameterSpec paramSpec = new NamedParameterSpec(ED_25519);
			final EdECPoint ep = new EdECPoint(xisodd, y);
			final EdECPublicKeySpec keySpec = new EdECPublicKeySpec(paramSpec, ep);
			return keyFactory.generatePublic(keySpec);
		});
	}

	private static PrivateKey bytesToPrivateKey(final byte[] serializedKey) {
		return ExceptionWrappers.wrapExceptions(() -> {
			final KeyFactory keyFactory = KeyFactory.getInstance(ED_25519);
			final NamedParameterSpec paramSpec = new NamedParameterSpec(ED_25519);
			final EdECPrivateKeySpec keySpec = new EdECPrivateKeySpec(paramSpec, serializedKey);
			return keyFactory.generatePrivate(keySpec);
		});
	}
}
