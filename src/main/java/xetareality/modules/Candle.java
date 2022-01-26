package xetareality.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xetareality.modules.models.ReadModel;
import xetareality.modules.models.ScanModel;
import xetareality.modules.models.CandleData;

import java.time.Instant;
import java.util.List;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Candle {

	/**
	 * Read candle by key (interval:token) and time
	 */
	public static CandleData read(String interval, String token, String time) {
		return read(ReadModel.builder().build(), interval, token, time);
	}
	public static CandleData read(ReadModel model, String interval, String token, String time) {
		if (isNull(time)) time = Long.toString(Instant.now().getEpochSecond() - Instant.now().getEpochSecond() % (60*60*24));

		return Resource.read(
			model.toBuilder()
				.type("candle")
				.key(interval + ":" + token)
				.sort("time")
				.sortValue(time)
				.build(),
			CandleData.class
		);
	}

	/**
	 * Scan candles by token and interval, sort by time
	 */
	public static List<CandleData> scanIntervalTokenTime(String interval, String token, String time) {
		return scanIntervalTokenTime(ScanModel.builder().build(), interval, token, time);
	}
	public static List<CandleData> scanIntervalTokenTime(ScanModel model, String interval, String token, String time) {
		return Resource.scan(
			model.toBuilder()
				.type("candle")
				.sort("time")
				.sortValue(time)
				.keyValue(interval + ":" + token)
				.build(),
			CandleData.class
		);
	}

	/**
	 * Scan candles by interval and time, sort by turnover
	 */
	public static List<CandleData> scanIntervalTimeTurnover(String interval, String time, String turnover, String key) {
		return scanIntervalTimeTurnover(ScanModel.builder().build(), interval, time, turnover, key);
	}
	public static List<CandleData> scanIntervalTimeTurnover(ScanModel model, String interval, String time, String turnover, String key) {
		if (isNull(time)) time = Long.toString(Instant.now().getEpochSecond() - Instant.now().getEpochSecond() % (60*60*24));

		return Resource.scan(
			model.toBuilder()
				.type("candle")
				.index("period")
				.indexValue(interval + ":" + time)
				.sort("turnover")
				.sortValue(turnover)
				.keyValue(key)
				.build(),
			CandleData.class
		);
	}

	/**
	 * Scan candles by interval and time, sort by change
	 */
	public static List<CandleData> scanIntervalTimeChange(String interval, String time, String change, String key) {
		return scanIntervalTimeChange(ScanModel.builder().build(), interval, time, change, key);
	}
	public static List<CandleData> scanIntervalTimeChange(ScanModel model, String interval, String time, String change, String key) {
		if (isNull(time)) time = Long.toString(Instant.now().getEpochSecond() - Instant.now().getEpochSecond() % (60*60*24));

		return Resource.scan(
			model.toBuilder()
				.type("candle")
				.index("period")
				.indexValue(interval + ":" + time)
				.sort("change")
				.sortValue(change)
				.keyValue(key)
				.build(),
			CandleData.class
		);
	}
}
