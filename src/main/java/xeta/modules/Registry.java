package xeta.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.modules.models.ListModel;
import xeta.modules.models.ReadModel;
import xeta.modules.models.ScanModel;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Registry {
	// todo verify correct return type instead of string

	/**
	 * Read object by hash
	 */
	public static String read(String hash) {
		return read(ReadModel.builder().build(), hash);
	}
	public static String read(ReadModel model, String hash) {
		return Resource.read(
			model.toBuilder()
				.type("object")
				.key(hash)
				.build(),
			String.class
		);
	}

	/**
	 * List objects by hashes
	 */
	public static List<String> list(List<String> hashes) {
		return list(ListModel.builder().build(), hashes);
	}
	public static List<String> list(ListModel model, List<String> hashes) {
		return Resource.list(
			model.toBuilder()
				.type("object")
				.keys(hashes)
				.build(),
			String.class
		);
	}

	/**
	 * Scan objects by content, sort by created
	 */
	public static List<String> scanContentCreated(String content, String created, String token) {
		return scanContentCreated(ScanModel.builder().build(), content, created, token);
	}
	public static List<String> scanContentCreated(ScanModel model, String content, String created, String token) {
		return Resource.scan(
			model.toBuilder()
				.type("object")
				.index("content")
				.indexValue(content)
				.sort("created")
				.sortValue(created)
				.keyValue(token)
				.build(),
			String.class
		);
	}

	/**
	 * Scan objects by fingerprint, sort by created
	 */
	public static List<String> scanFingerprintCreated(String fingerprint, String created, String token) {
		return scanFingerprintCreated(ScanModel.builder().build(), fingerprint, created, token);
	}
	public static List<String> scanFingerprintCreated(ScanModel model, String fingerprint, String created, String token) {
		return Resource.scan(
			model.toBuilder()
				.type("object")
				.index("fingerprint")
				.indexValue(fingerprint)
				.sort("created")
				.sortValue(created)
				.keyValue(token)
				.build(),
			String.class
		);
	}

	/**
	 * Scan objects by cluster, sort by created
	 */
	public static List<String> scanClusterCreated(String cluster, String created, String token) {
		return scanClusterCreated(ScanModel.builder().build(), cluster, created, token);
	}
	public static List<String> scanClusterCreated(ScanModel model, String cluster, String created, String token) {
		return Resource.scan(
			model.toBuilder()
				.type("object")
				.index("cluster")
				.indexValue(cluster)
				.sort("created")
				.sortValue(created)
				.keyValue(token)
				.build(),
			String.class
		);
	}
}
