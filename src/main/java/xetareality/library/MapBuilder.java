package xetareality.library;


import java.util.HashMap;
import java.util.Map;

public class MapBuilder {

	private final Map<String, String> map;

	private MapBuilder() {
		this.map = new HashMap<>();
	}

	public static MapBuilder builder() {
		return new MapBuilder();
	}

	public MapBuilder put(String key, String value) {
		map.put(key, value);
		return this;
	}

	public MapBuilder remove(String key) {
		map.remove(key);
		return this;
	}

	public MapBuilder putAll(Map<String, String> toPut) {
		map.putAll(toPut);
		return this;
	}

	public Map<String, String> build() {
		return this.map;
	}
}
