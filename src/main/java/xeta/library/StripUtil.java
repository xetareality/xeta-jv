package xeta.library;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StripUtil {

	private static final int DEFAULT_MIN = 1;

	/**
	 * Strip null values from map
	 */
	public static <K, V> Map<K, V> stripNulls(final Map<K, V> inputMap) {
		return stripNulls(inputMap, DEFAULT_MIN);
	}
	public static <K, V> Map<K, V> stripNulls(final Map<K, V> inputMap, final int minimum) {
		return inputMap.entrySet()
			.stream()
			.filter(e -> nonNull(e.getValue()))
			.collect(Collectors.collectingAndThen(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue), result -> {
				if (result.size() < minimum) throw new IllegalArgumentException("parameters:missing");
				return result;
			}));
	}

	/**
	 * Strip null values from map and covert it to parameters string
	 */
	public static <K, V> String stripNullsAndConvertToParameters(final Map<K, V> inputMap) {
		return stripNullsAndConvertToParameters(inputMap, DEFAULT_MIN);
	}
	public static <K, V> String stripNullsAndConvertToParameters(final Map<K, V> inputMap, final int minimum) {
		return stripNulls(inputMap, minimum)
			.entrySet()
			.stream()
			.map(e -> e.getKey() + "=" + e.getValue())
			.collect(Collectors.joining("&"));
	}
}
