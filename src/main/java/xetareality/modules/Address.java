package xetareality.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xetareality.library.Config;
import xetareality.library.HttpUtil;
import xetareality.library.MapBuilder;
import xetareality.library.StripUtil;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {

	/**
	 * Read address data (balance, profile, pool, token)
	 */
	public static String read(String address) {
		final String parameters = StripUtil.stripNullsAndConvertToParameters(
			MapBuilder.builder()
				.put("address", address)
				.put("dev", Config.devString())
				.build()
		);
		return HttpUtil.GET(Config.xetaInterface + "/address?" + parameters);
	}
}
