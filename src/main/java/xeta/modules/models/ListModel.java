package xeta.modules.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class ListModel {
	private final String type;
	private final List<String> keys;
	private final String sort;
	private final String sortValues;
	private final String fields;
	private final String preview;
}
