package xetareality.modules.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ScanModel {
	private final String type;
	private final String index;
	private final String indexValue;
	private final String sort;
	private final String sortValue;
	private final String keyValue;
	private final String operator;
	private final Boolean asc;
	private final Integer limit;
	private final Boolean preview;
}
