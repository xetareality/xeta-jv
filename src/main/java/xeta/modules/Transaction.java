package xeta.modules;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.Config;
import xeta.library.GeneralUtil;
import xeta.library.HttpUtil;
import xeta.library.Models;
import xeta.library.crypto.Crypto;
import xeta.library.hash.HashUtil;
import xeta.modules.models.ListModel;
import xeta.modules.models.ReadModel;
import xeta.modules.models.ScanModel;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Transaction {

	/**
	 * Create transaction
	 */
	public static TransactionData submit(Map<String, String> instructions, TransactionRequest tx) {
		tx.setInstructionsIfNull(instructions);
		tx.setSenderIfNull(Config.publicKey);
		tx.setNonceIfNull(Instant.now().getEpochSecond());

		Models.validFormats(tx);

		if (isNull(tx.getSignature()) && isNull(Config.privateKey))
			return TransactionData.fromRequest(tx, instructions);
		if (isNull(tx.getSignature())) tx.setSignature(Crypto.sign(HashUtil.transaction(tx), Config.privateKey));

		final String rawResponse = HttpUtil.POST(
			Config.network + (Config.dev ? "?dev=1" : ""),
			GeneralUtil.toJson(tx)
		);
		final TransactionData parsedResponse = new Gson().fromJson(rawResponse, TransactionData.class);

		if (nonNull(parsedResponse.getError())) throw new RuntimeException(parsedResponse.getError());
		return parsedResponse;
	}

	/**
	 * Poll a transactionPoll a transaction
	 */
	public static TransactionData poll(String hash, Double interval, Double timeout) {
		interval = nonNull(interval) ? interval : 0.5;
		timeout = nonNull(timeout) ? timeout : 5.0;

		final long start = Instant.now().toEpochMilli();
		while (Instant.now().toEpochMilli() < start + timeout) {
			final TransactionData result = Transaction.read(hash);
			if (nonNull(result)) return result;
			else GeneralUtil.sleep(interval);
		}
		return null;
	}

	/**
	 * Read transaction by hash
	 */
	public static TransactionData read(String hash) {
		return read(ReadModel.builder().build(), hash);
	}

	public static TransactionData read(ReadModel model, String hash) {
		return Resource.read(
			model.toBuilder()
				.type("transaction")
				.key(hash)
				.build(),
			TransactionData.class
		);
	}

	/**
	 * List transactions by hashes
	 */
	public static  List<TransactionData> list(List<String> hashes) {
		return list(ListModel.builder().build(), hashes);
	}

	public static List<TransactionData> list(ListModel model, List<String> hashes) {
		return Resource.list(
			model.toBuilder()
				.type("transaction")
				.keys(hashes)
				.build(),
			TransactionData.class
		);
	}

	/**
	 * Scan transactions by sender, sort by created
	 */
	public static List<TransactionData> scanSenderCreated(String sender, String created, String hash) {
		return scanSenderCreated(ScanModel.builder().build(), sender, created, hash);
	}
	public static List<TransactionData> scanSenderCreated(ScanModel model, String sender, String created, String hash) {
		return Resource.scan(
			model.toBuilder()
				.type("transaction")
				.index("sender")
				.indexValue(sender)
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			TransactionData.class
		);
	}

	/**
	 * Scan transactions by period, sort by created
	 */
	public static List<TransactionData> scanPeriodCreated(String period, String created, String hash) {
		return scanPeriodCreated(ScanModel.builder().build(), period, created, hash);
	}
	public static List<TransactionData> scanPeriodCreated(ScanModel model, String period, String created, String hash) {
		return Resource.scan(
			model.toBuilder()
				.type("transaction")
				.index("period")
				.indexValue(period)
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			TransactionData.class
		);
	}
}
