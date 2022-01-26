package xetareality.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xetareality.library.GeneralUtil;
import xetareality.library.MapBuilder;
import xetareality.library.hash.HashUtil;
import xetareality.modules.models.ListModel;
import xetareality.modules.models.ReadModel;
import xetareality.modules.models.ScanModel;
import xetareality.modules.models.TransactionData;
import xetareality.modules.models.TransactionRequest;
import xetareality.modules.models.TransferData;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Transfer {

	/**
	 * Create transfer
	 */
	public static TransactionData create(String to, String token, String amount, String from, String message,
										 TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "transfer.create")
				.put("to", to)
				.put("token", token)
				.put("amount", GeneralUtil.amountOrNull(amount))
				.put("from", from)
				.put("message", message)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Read transfer by hash
	 */
	public static TransferData read(String hash) {
		return read(ReadModel.builder().build(), hash);
	}
	public static TransferData read(ReadModel model, String hash) {
		return Resource.read(
			model.toBuilder()
				.type("transfer")
				.key(hash)
				.build(),
			TransferData.class
		);
	}

	/**
	 * List transfers by hashes
	 */
	public static List<TransferData> list(List<String> hashes) {
		return list(ListModel.builder().build(), hashes);
	}
	public static List<TransferData> list(ListModel model, List<String> hashes) {
		return Resource.list(
			model.toBuilder()
				.type("transfer")
				.keys(hashes)
				.build(),
			TransferData.class
		);
	}

	/**
	 * Scan transfers by sender, sort by created
	 */
	public static List<TransferData> scanSenderCreated(String sender, String created, String hash) {
		return scanSenderCreated(ScanModel.builder().build(), sender, created, hash);
	}
	public static List<TransferData> scanSenderCreated(ScanModel model, String sender, String created, String hash) {
		return Resource.scan(
			model.toBuilder()
				.type("transfer")
				.index("sender")
				.indexValue(sender)
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			TransferData.class
		);
	}

	/**
	 * Scan transfers by from, sort by created
	 */
	public static List<TransferData> scanFromCreated(String from, String created, String hash) {
		return scanFromCreated(ScanModel.builder().build(), from, created, hash);
	}
	public static List<TransferData> scanFromCreated(ScanModel model, String from, String created, String hash) {
		return Resource.scan(
			model.toBuilder()
				.type("transfer")
				.index("from")
				.indexValue(from)
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			TransferData.class
		);
	}

	/**
	 * Scan transfers by to, sort by created
	 */
	public static List<TransferData> scanToCreated(String to, String created, String hash) {
		return scanToCreated(ScanModel.builder().build(), to, created, hash);
	}
	public static List<TransferData> scanToCreated(ScanModel model, String to, String created, String hash) {
		return Resource.scan(
			model.toBuilder()
				.type("transfer")
				.index("to")
				.indexValue(to)
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			TransferData.class
		);
	}

	/**
	 * Scan transfers by token, sort by created
	 */
	public static List<TransferData> scanTokenCreated(String token, String created, String hash) {
		return scanTokenCreated(ScanModel.builder().build(), token, created, hash);
	}
	public static List<TransferData> scanTokenCreated(ScanModel model, String token, String created, String hash) {
		return Resource.scan(
			model.toBuilder()
				.type("transfer")
				.index("token")
				.indexValue(token)
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			TransferData.class
		);
	}

	/**
	 * Scan transfers by fromToken, sort by created
	 */
	public static List<TransferData> scanFromTokenCreated(String from, String token, String created, String hash) {
		return scanFromTokenCreated(ScanModel.builder().build(), from, token, created, hash);
	}
	public static List<TransferData> scanFromTokenCreated(ScanModel model, String from, String token, String created, String hash) {
		String hashComposed = HashUtil.values(new String[]{from, token});
		return Resource.scan(
			model.toBuilder()
				.type("transfer")
				.index("fromToken")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			TransferData.class
		);
	}

	/**
	 * Scan transfers by toToken, sort by created
	 */
	public static List<TransferData> scanToTokenCreated(String to, String token, String created, String hash) {
		return scanToTokenCreated(ScanModel.builder().build(),  to, token, created, hash);
	}
	public static List<TransferData> scanToTokenCreated(ScanModel model, String to, String token, String created, String hash) {
		final String hashComposed = HashUtil.values(new String[]{to, token});
		return Resource.scan(
			model.toBuilder()
				.type("transfer")
				.index("toToken")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			TransferData.class
		);
	}
}
