package xeta.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.library.hash.HashUtil;
import xeta.modules.models.ListModel;
import xeta.modules.models.ReadModel;
import xeta.modules.models.ScanModel;
import xeta.modules.models.AllowanceData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Allowance {

	/**
	 * Update allowance for spender address
	 */
	public static TransactionData update(String spender, String token, String amount, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "allowance.update")
				.put("spender", spender)
				.put("token", token)
				.put("amount", GeneralUtil.amount(amount))
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Read allowance by hash
	 */
	public static AllowanceData read(String hash) {
		return read(ReadModel.builder().build(), hash);
	}
	public static AllowanceData read(ReadModel model, String hash) {
		return Resource.read(
			model.toBuilder()
				.type("allowance")
				.key(hash)
				.build(),
			AllowanceData.class
		);
	}

	/**
	 * List allowances by hashes
	 */
	public static List<AllowanceData> list(List<String> hashes) {
		return list(ListModel.builder().build(), hashes);
	}
	public static List<AllowanceData> list(ListModel model, List<String> hashes) {
		return Resource.list(
			model.toBuilder()
				.type("allowance")
				.keys(hashes)
				.build(),
			AllowanceData.class
		);
	}

	/**
	 * Read allowance by address, spender, and token
	 */
	public static AllowanceData readAddressSpenderToken(String address, String spender, String token) {
		return readAddressSpenderToken(ReadModel.builder().build(), address, spender, token);
	}
	public static AllowanceData readAddressSpenderToken(ReadModel model, String address, String spender, String token) {
		return Resource.read(
			model.toBuilder()
				.type("allowance")
				.key(HashUtil.allowance(address, spender, token))
				.build(),
			AllowanceData.class
		);
	}

	/**
	 * Scan allowances by address, sort by created
	 */
	public static List<AllowanceData> scanAddressCreated(String address, String created, String hash) {
		return scanAddressCreated(ScanModel.builder().build(), address, created, hash);
	}
	public static List<AllowanceData> scanAddressCreated(ScanModel model, String address, String created, String hash) {
		return Resource.scan(
			model.toBuilder()
				.type("allowance")
				.index("address")
				.indexValue(address)
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			AllowanceData.class
		);
	}

	/**
	 * Scan allowances by spender, sort by created
	 */
	public static List<AllowanceData> scanSpenderCreated(String spender, String created, String hash) {
		return scanSpenderCreated(ScanModel.builder().build(), spender, created, hash);
	}
	public static List<AllowanceData> scanSpenderCreated(ScanModel model, String spender, String created, String hash) {
		return Resource.scan(
			model.toBuilder()
				.type("allowance")
				.index("spender")
				.indexValue(spender)
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			AllowanceData.class
		);
	}
}
