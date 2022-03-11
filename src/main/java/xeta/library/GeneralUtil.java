package xeta.library;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneralUtil {

	public static String amount(String amount) {
		if (isNull(amount)) return null;

		final DecimalFormat format = new DecimalFormat("###.########");
		final BigDecimal bigDecimal = new BigDecimal(amount);
		return format.format(bigDecimal);
	}

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static <T> T fromJson(String json, Class<T> returnType) {
		return new Gson().fromJson(json, new TypeToken<T>(){}.getType());
	}

	/**
	 * Sleep function
	 */
	public static void sleep(Double interval) {
		try {
			TimeUnit.MILLISECONDS.sleep((long) (interval * 1000));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
