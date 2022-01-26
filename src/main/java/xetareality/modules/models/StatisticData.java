package xetareality.modules.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class StatisticData {
	private String key;
	private Long time;
	private Long until;
	private Long value;
}
