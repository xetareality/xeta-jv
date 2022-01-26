package xetareality.modules.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class TokenData {
	private String address;
	private String creator;
	private String name;
	private Long created;
	private String origin;

	private String description;
	private List<String> links;
	private Object meta;
	private String preview;

	private String symbol;
	private String supply;
	private String reserve;
	private Boolean whole;

	private String owner;
	private String object;
	private String mime;
	private String content;
	private Boolean frozen;
	private String category;
	private String ownerCategory;
	private String creatorCategory;
}
