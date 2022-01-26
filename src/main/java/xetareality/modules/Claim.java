package xetareality.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xetareality.library.GeneralUtil;
import xetareality.library.MapBuilder;
import xetareality.library.hash.HashUtil;
import xetareality.modules.models.ListModel;
import xetareality.modules.models.ReadModel;
import xetareality.modules.models.ScanModel;
import xetareality.modules.models.ClaimData;
import xetareality.modules.models.TransactionData;
import xetareality.modules.models.TransactionRequest;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Claim {
	/**
	 * Create claim
	 */
	public static TransactionData create(String owner, String token, String tokenAmount, String xetaAmount, String expires,
										 String unlocks, String frozen, String category, String meta, String answer, String number,
										 TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "claim.create")
				.put("owner", owner)
				.put("token", token)
				.put("tokenAmount", GeneralUtil.amountOrNull(tokenAmount))
				.put("xetaAmount", GeneralUtil.amountOrNull(xetaAmount))
				.put("expires", expires)
				.put("unlocks", unlocks)
				.put("frozen", frozen)
				.put("category", category)
				.put("meta", meta)
				.put("answer", answer)
				.put("number", number)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Update claim
	 */
	public static TransactionData update(String claim, String tokenAmount, String xetaAmount, String expires,
										 String unlocks, String frozen, String category, String meta, String answer, String number,
										 TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "claim.update")
				.put("claim", claim)
				.put("tokenAmount", GeneralUtil.amountOrNull(tokenAmount))
				.put("xetaAmount", GeneralUtil.amountOrNull(xetaAmount))
				.put("expires", expires)
				.put("unlocks", unlocks)
				.put("frozen", frozen)
				.put("category", category)
				.put("meta", meta)
				.put("answer", answer)
				.put("number", number)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Transfer claim
	 */
	public static TransactionData transfer(String claim, String to, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "claim.transfer")
				.put("claim", claim)
				.put("to", to)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Resolve claim
	 */
	public static TransactionData resolve(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "claim.resolve")
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Read claim by hash
	 */
	public static ClaimData read(String hash) {
		return read(ReadModel.builder().build(), hash);
	}
	public static ClaimData read(ReadModel model, String hash) {
		return Resource.read(
			model.toBuilder()
				.type("claim")
				.key(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * List claims by hashes
	 */
	public static List<ClaimData> list(List<String> hashes) {
		return list(ListModel.builder().build(), hashes);
	}
	public static List<ClaimData> list(ListModel model, List<String> hashes) {
		return Resource.list(
			model.toBuilder()
				.type("claim")
				.keys(hashes)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by holder and category, sort by created
	 */
	public static List<ClaimData> scanHolderCategoryCreated(String holder, String category, String created, String hash) {
		return scanHolderCategoryCreated(ScanModel.builder().build(), holder, category, created, hash);
	}
	public static List<ClaimData> scanHolderCategoryCreated(ScanModel model, String holder, String category, String created, String hash) {
		final String hashComposed = HashUtil.values(new String[]{holder, category});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("holderCategory")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by issuer and category, sort by created
	 */
	public static List<ClaimData> scanIssuerCategoryCreated(String issuer, String category, String created, String hash) {
		return scanIssuerCategoryCreated(ScanModel.builder().build(), issuer, category, created, hash);
	}
	public static List<ClaimData> scanIssuerCategoryCreated(ScanModel model, String issuer, String category, String created, String hash) {
		final String hashComposed = HashUtil.values(new String[]{issuer, category});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("issuerCategory")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by issuer, sort by answer
	 */
	public static List<ClaimData> scanIssuerAnswer(String issuer, String answer, String hash) {
		return scanIssuerAnswer(ScanModel.builder().build(), issuer, answer, hash);
	}
	public static List<ClaimData> scanIssuerAnswer(ScanModel model, String issuer, String answer, String hash) {
		final String hashComposed = HashUtil.values(new String[]{issuer});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("issuer")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("answer")
				.sortValue(answer)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by issuer, sort by number
	 */
	public static List<ClaimData> scanIssuerNumber(String issuer, String number, String hash) {
		return scanIssuerNumber(ScanModel.builder().build(), issuer, number, hash);
	}
	public static List<ClaimData> scanIssuerNumber(ScanModel model, String issuer, String number, String hash) {
		final String hashComposed = HashUtil.values(new String[]{issuer});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("issuer")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("number")
				.sortValue(number)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by issuer, sort by tokenAmount
	 */
	public static List<ClaimData> scanIssuerTokenAmount(String issuer, String tokenAmount, String hash) {
		return scanIssuerTokenAmount(ScanModel.builder().build(), issuer, tokenAmount, hash);
	}
	public static List<ClaimData> scanIssuerTokenAmount(ScanModel model, String issuer, String tokenAmount, String hash) {
		final String hashComposed = HashUtil.values(new String[]{issuer});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("issuer")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("tokenAmount")
				.sortValue(tokenAmount)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by issuer, sort by xetaAmount
	 */
	public static List<ClaimData> scanIssuerXetaAmount(String issuer, String xetaAmount, String hash) {
		return scanIssuerXetaAmount(ScanModel.builder().build(), issuer, xetaAmount, hash);
	}
	public static List<ClaimData> scanIssuerXetaAmount(ScanModel model, String issuer, String xetaAmount, String hash) {
		final String hashComposed = HashUtil.values(new String[]{issuer});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("issuer")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("xetaAmount")
				.sortValue(xetaAmount)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by issuer, sort by created
	 */
	public static List<ClaimData> scanIssuerCreated(String issuer, String created, String hash) {
		return scanIssuerCreated(ScanModel.builder().build(), issuer, created, hash);
	}
	public static List<ClaimData> scanIssuerCreated(ScanModel model, String issuer, String created, String hash) {
		final String hashComposed = HashUtil.values(new String[]{issuer});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("issuer")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by holder, sort by created
	 */
	public static List<ClaimData> scanHolderCreated(String holder, String created, String hash) {
		return scanHolderCreated(ScanModel.builder().build(), holder, created, hash);
	}
	public static List<ClaimData> scanHolderCreated(ScanModel model, String holder, String created, String hash) {
		final String hashComposed = HashUtil.values(new String[]{holder});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("holder")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by issuer and token, sort by created
	 */
	public static List<ClaimData> scanIssuerTokenCreated(String issuer, String token, String created, String hash) {
		return scanIssuerTokenCreated(ScanModel.builder().build(), issuer, token, created, hash);
	}
	public static List<ClaimData> scanIssuerTokenCreated(ScanModel model, String issuer, String token, String created, String hash) {
		final String hashComposed = HashUtil.values(new String[]{issuer, token});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("issuerToken")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by holder and token, sort by created
	 */
	public static List<ClaimData> scanHolderTokenCreated(String holder, String token, String created, String hash) {
		return scanHolderTokenCreated(ScanModel.builder().build(), holder, token, created, hash);
	}
	public static List<ClaimData> scanHolderTokenCreated(ScanModel model, String holder, String token, String created, String hash) {
		final String hashComposed = HashUtil.values(new String[]{holder, token});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("holderToken")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by issuer and holder, sort by created
	 */
	public static List<ClaimData> scanIssuerHolder(String issuer, String holder, String created, String hash) {
		return scanIssuerHolder(ScanModel.builder().build(), issuer, holder, created, hash);
	}
	public static List<ClaimData> scanIssuerHolder(ScanModel model, String issuer, String holder, String created, String hash) {
		final String hashComposed = HashUtil.values(new String[]{issuer, holder});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("issuerHolder")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}

	/**
	 * Scan claims by issuer, holder and token, sort by created
	 */
	public static List<ClaimData> scanIssuerHolderToken(String issuer, String holder, String token, String created, String hash) {
		return scanIssuerHolderToken(ScanModel.builder().build(), issuer, holder, token, created, hash);
	}
	public static List<ClaimData> scanIssuerHolderToken(ScanModel model, String issuer, String holder, String token, String created, String hash) {
		final String hashComposed = HashUtil.values(new String[]{issuer, holder, token});
		return Resource.scan(
			model.toBuilder()
				.type("claim")
				.index("issuerHolderToken")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(hash)
				.build(),
			ClaimData.class
		);
	}
}
