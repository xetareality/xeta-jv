package xetareality.modules.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CandleData {
	private String key;
	private String period;
	private Long time;
	private String open;
	private String high;
	private String low;
	private String close;
	private Long change;
	private String volume;
	private String turnover;
	private Long trades;
	private Long first;
	private Long last;
}
