package xeta.library.hash;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.ExceptionWrappers;
import xeta.modules.models.TransactionRequest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static xeta.library.GeneralUtil.toJson;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HashUtil {

	public static String transaction(TransactionRequest tx) {
		return values(new Object[] { tx.getSender(), tx.getInstructions(), tx.getNonce()});
	}

	public static String allowance(final String address, final String spender, final String token) {
		return values(new String[] { address, spender, token });
	}

	public static String balance(final String address, final String token) {
		return values(new String[] { address, token });
	}

	public static String values(final Object[] values) {
		final String jsonValues = toJson(values);
		final byte[] encrypted = sha256(jsonValues);
		return Base58.encode(encrypted);
	}

	public static String string(final String body) {
		final byte[] encrypted = sha256(body, true);
		return Base58.encode(encrypted);
	}

	private static byte[] sha256(final String message) {
		return sha256(message, false);
	}
	private static byte[] sha256(String message, Boolean doubleHashing) {
		final MessageDigest digest = ExceptionWrappers.wrapExceptions(() -> MessageDigest.getInstance("SHA-256"));
		final byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
		digest.update(bytes);
		if (Boolean.TRUE.equals(doubleHashing)) digest.update(bytes);
		return digest.digest();
	}

}
