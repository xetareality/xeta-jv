package xetareality.library;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xetareality.modules.models.TransactionRequest;

import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Models {

	private static final int MIN_INTEGER_NUMBER = 0;
	private static final long MAX_INTEGER_NUMBER = 1_000_000_000_000_000L;
	private static final String HASH_REGEX = "^[123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]+$";

	/**
	 * Guarantee that object contains only specified fields
	 */
	public static void exclusiveFields(Map<String, String> object, List<String> fields) {
		object.keySet().forEach(key -> {
			if (!fields.contains(key)) throw new ValidationException(key + ":invalid");
		});
	}

	/**
	 * Validate formats
	 */
	public static void validFormats(TransactionRequest tx) {
		if (nonNull(tx.getHash()) && !Models.verifyHash(tx.getHash())) throw new ValidationException("hash:format");
		if (nonNull(tx.getSignature()) && !Models.verifyString(tx.getSignature())) throw new ValidationException("signature:format");
		if (nonNull(tx.getSender()) && !Models.verifyHash(tx.getSender())) throw new ValidationException("sender:format");
		if (nonNull(tx.getNonce()) && !Models.verifyIntegerNumber(tx.getNonce())) throw new ValidationException("nonce:format");
	}

	public static boolean verifyString(String value) {
		return value.length() > 0 && value.length() <= 256;
	}

	public static boolean verifyHash(String value) {
		return value.length() >= 32 && value.length() <= 44 && value.matches(HASH_REGEX);
	}

	public static boolean verifyIntegerNumber(Long value) {
		return value >= MIN_INTEGER_NUMBER && value <= MAX_INTEGER_NUMBER;
	}


	public static class ValidationException extends RuntimeException {
		public ValidationException(String e) {
			super(e);
		}
	}
}
