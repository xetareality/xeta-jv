package xetareality.library;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.Callable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionWrappers {
	public static <T> T wrapExceptions(Callable<T> possibleException) {
		try {
			return possibleException.call();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
