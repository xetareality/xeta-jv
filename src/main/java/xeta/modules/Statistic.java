package xeta.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.modules.models.ReadModel;
import xeta.modules.models.ScanModel;
import xeta.modules.models.StatisticData;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Statistic {

	/**
	 * Read statistic by key and time
	 */
	public static StatisticData read(String key, String time) {
		return read(ReadModel.builder().build(), key, time);
	}
	public static StatisticData read(ReadModel model, String key, String time) {
		return Resource.read(
			model.toBuilder()
				.type("statistic")
				.key(key)
				.sort("time")
				.sortValue(time)
				.build(),
			StatisticData.class
		);
	}

	/**
	 * Scan statistics by key, sort by time
	 */
	public static List<StatisticData> scan(String key, String time) {
		return scan(ScanModel.builder().build(), key, time);
	}
	public static List<StatisticData> scan(ScanModel model, String key, String time) {
		return Resource.scan(
			model.toBuilder()
				.type("statistic")
				.sort("time")
				.sortValue(time)
				.keyValue(key)
				.build(),
			StatisticData.class
		);
	}
}
