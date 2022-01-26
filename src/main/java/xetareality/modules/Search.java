package xetareality.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xetareality.library.Config;
import xetareality.library.HttpUtil;
import xetareality.library.MapBuilder;
import xetareality.library.StripUtil;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Search {

	public static String search(String query) {
		final String parameters = StripUtil.stripNullsAndConvertToParameters(
			MapBuilder.builder()
				.put("query", query)
				.put("dev", Config.devString())
				.build()
		);
		return HttpUtil.GET(Config.xetaInterface + "/search?" + parameters);
	}
}
