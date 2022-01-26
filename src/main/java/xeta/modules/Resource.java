package xeta.modules;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.*;
import xeta.modules.models.ListModel;
import xeta.modules.models.ReadModel;
import xeta.modules.models.ScanModel;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Resource {

	/**
	 * Read resource by key
	 */
	public static <T> T read(ReadModel model, Class<T> returnType) {
		isRequiredType(model.getType());

		final String parameters = StripUtil.stripNullsAndConvertToParameters(
			MapBuilder.builder()
				.put("type", model.getType())
				.put("keyValue", model.getKey())
				.put("sort", model.getSort())
				.put("sortValue", model.getSortValue())
				.put("fields", model.getFields())
				.put("preview", model.getPreview())
				.put("dev", Config.devString())
				.build()
		);
		final String rawResponse = HttpUtil.GET(Config.xetaInterface + "/read?" + parameters);
		return new Gson().fromJson(rawResponse, returnType);
	}

	/**
	 * List resources by keys
	 */
	public static <T> List<T> list(ListModel model, Class<T> returnType) {
		isRequiredType(model.getType());

		final String parameters = StripUtil.stripNullsAndConvertToParameters(
			MapBuilder.builder()
				.put("type", model.getType())
				.put("keyValues", String.join(",", model.getKeys()))
				.put("sort", model.getSort())
				.put("sortValues", model.getSortValues())
				.put("fields", model.getFields())
				.put("preview", model.getPreview())
				.put("dev", Config.devString())
				.build()
		);
		final String rawResponse = HttpUtil.GET(Config.xetaInterface + "/list?" + parameters);
		return new Gson().fromJson(rawResponse, TypeToken.getParameterized(List.class, returnType).getType());
	}

	/**
	 * Scan resources by index
	 * Candles and statistics support scanning without index (by key, sorted by time)
	 */
	public static <T> List<T> scan(ScanModel model, Class<T> returnType) {
		isRequiredType(model.getType());

		final int calculatedLimit;
		if (asList("candle", "statistic").contains(model.getType())) {
			calculatedLimit = nonNull(model.getLimit()) ? Math.min(model.getLimit(), 1000) : 200;
		} else {
			calculatedLimit = nonNull(model.getLimit()) ? Math.min(model.getLimit(), 25) : 25;
		}

		final String parameters = StripUtil.stripNullsAndConvertToParameters(
			MapBuilder.builder()
			.put("type", model.getType())
			.put("keyValue", model.getKeyValue())
			.put("index", model.getIndex())
			.put("indexValue", model.getIndexValue())
			.put("sort", model.getSort())
			.put("sortValue", model.getSortValue())
			.put("operator", model.getOperator())
			.put("asc", isNull(model.getAsc()) ? "acs" : model.getAsc() ? "asc" : "desc")
			.put("limit", Integer.toString(calculatedLimit))
			.put("preview", String.valueOf(model.getPreview()))
			.put("dev", Config.devString())
			.build()
		);
		final String rawResponse = HttpUtil.GET(Config.xetaInterface + "/scan?" + parameters);
		return new Gson().fromJson(rawResponse, TypeToken.getParameterized(List.class, returnType).getType());
	}


	private static void isRequiredType(String type) {
		if (!asList("allowance", "balance", "candle", "claim", "object", "pool", "statistic", "token", "transaction", "transfer", "wallet").contains(type)) {
			throw new IllegalArgumentException("type:invalid");
		}
	}
}
