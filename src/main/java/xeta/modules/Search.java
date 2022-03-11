package xeta.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.Config;
import xeta.library.HttpUtil;
import xeta.library.MapBuilder;
import xeta.library.StripUtil;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Search {

	public static String search(String query) {
		final String parameters = StripUtil.stripNullsAndConvertToParameters(
			MapBuilder.builder()
				.put("query", query)
				.put("dev", Config.devString())
				.put("identity", Config.identity)
				.build()
		);
		return HttpUtil.GET(Config.xetaInterface + "/search?" + parameters);
	}
}
