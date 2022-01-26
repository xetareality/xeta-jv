package xeta.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.hash.HashUtil;
import xeta.modules.models.ListModel;
import xeta.modules.models.ReadModel;
import xeta.modules.models.ScanModel;
import xeta.modules.models.BalanceData;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Balance {

	/**
	 * Read balance by hash
	 */
	public static BalanceData read(String hash) {
		return read(ReadModel.builder().build(), hash);
	}
	public static BalanceData read(ReadModel model, String hash) {
		return Resource.read(
			model.toBuilder()
				.type("balance")
				.key(hash)
				.build(),
			BalanceData.class
		);
	}

	/**
	 * Read balance by address and token
	 */
	public static BalanceData readAddressToken(String address, String token) {
		return readAddressToken(ReadModel.builder().build(), address, token);
	}
	public static BalanceData readAddressToken(ReadModel model, String address, String token) {
		return Resource.read(
			model.toBuilder()
				.type("balance")
				.key(HashUtil.balance(address, token))
				.build(),
			BalanceData.class
		);
	}

	/**
	 * List balances by hashes
	 */
	public static List<BalanceData> list(List<String> hashes) {
		return list(ListModel.builder().build(), hashes);
	}
	public static List<BalanceData> list(ListModel model, List<String> hashes) {
		return Resource.list(
			model.toBuilder()
				.type("balance")
				.keys(hashes)
				.build(),
			BalanceData.class
		);
	}

	/**
	 * Scan balances by address, sort by amount
	 */
	public static List<BalanceData> scanAddressAmount(String address, String amount, String hash) {
		return scanAddressAmount(ScanModel.builder().build(), address, amount, hash);
	}
	public static List<BalanceData> scanAddressAmount(ScanModel model, String address, String amount, String hash) {
		return Resource.scan(
			model.toBuilder()
				.type("balance")
				.index("address")
				.indexValue(address)
				.sort("amount")
				.sortValue(amount)
				.keyValue(hash)
				.build(),
			BalanceData.class
		);
	}

	/**
	 * Scan balances by token, sort by amount
	 */
	public static List<BalanceData> scanTokenAmount(String token, String amount, String hash) {
		return scanTokenAmount(ScanModel.builder().build(), token, amount, hash);
	}
	public static List<BalanceData> scanTokenAmount(ScanModel model, String token, String amount, String hash) {
		return Resource.scan(
			model.toBuilder()
				.type("balance")
				.index("token")
				.indexValue(token)
				.sort("amount")
				.sortValue(amount)
				.keyValue(hash)
				.build(),
			BalanceData.class
		);
	}
}
