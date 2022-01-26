package xetareality.modules.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ReadModel {
	private final String type;
	private final String key;
	private final String sort;
	private final String sortValue;
	private final String fields;
	private final String preview;
}
