package xeta.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.library.hash.HashUtil;
import xeta.modules.models.ListModel;
import xeta.modules.models.ReadModel;
import xeta.modules.models.ScanModel;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;
import xeta.programs.*;

import java.util.List;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Pool {

	/**
	 * Create pool
	 */
	public static TransactionData create(String token, String program, String name, String description, String mechanism,
										 String candidates, String rate, String percentage, String number, String expires,
										 String answers, String meta, String minAmount, String maxAmount, String minTime,
										 String maxTime, String transfersLimit, String claimsLimit, String tokenLimit,
										 String xetaLimit, String tokenTarget, String xetaTarget,
										 TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "pool.create")
				.put("token", token)
				.put("program", program)
				.put("name", name)
				.put("description", description)
				.put("mechanism", mechanism)
				.put("candidates", candidates)
				.put("rate", rate)
				.put("percentage", percentage)
				.put("number", number)
				.put("expires", expires)
				.put("answers", answers)
				.put("meta", meta)
				.put("minAmount", GeneralUtil.amountOrNull(minAmount))
				.put("maxAmount", GeneralUtil.amountOrNull(maxAmount))
				.put("minTime", minTime)
				.put("maxTime", maxTime)
				.put("transfersLimit", transfersLimit)
				.put("claimsLimit", claimsLimit)
				.put("tokenLimit", GeneralUtil.amountOrNull(tokenLimit))
				.put("xetaLimit", GeneralUtil.amountOrNull(xetaLimit))
				.put("tokenTarget", GeneralUtil.amountOrNull(tokenTarget))
				.put("xetaTarget", GeneralUtil.amountOrNull(xetaTarget))
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Get pool by address
	 * Return as program instance
	 */
	public static PoolDependent instance(String address) {
		final PoolData pool = Pool.read(address);
		if (isNull(pool)) return null;

		final PoolDependent result;
		switch (pool.getProgram()) {
			case "auction":
				result = new Auction(pool);
				break;
			case "launch":
				result = new Lending(pool);
				break;
			case "lock":
				result = new Lock(pool);
				break;
			case "loot":
				result = new Loot(pool);
				break;
			case "lottery":
				result = new Lottery(pool);
				break;
			case "royalty":
				result = new Royalty(pool);
				break;
			case "staking":
				result = new Staking(pool);
				break;
			case "swap":
				result = new Swap(pool);
				break;
			case "vote":
				result = new Vote(pool);
				break;
			default:
				throw new RuntimeException("program:invalid");
		}
		return result;
	}


	/**
	 * Read pool by address
	 */
	public static PoolData read(String address) {
		return Pool.read(ReadModel.builder().build(), address);
	}
	public static PoolData read(ReadModel model, String address) {
		return Resource.read(
			model.toBuilder()
				.type("pool")
				.key(address)
				.build(),
			PoolData.class
		);
	}

	/**
	 * List pools by addresses
	 */
	public static List<PoolData> list(List<String> addresses) {
		return list(ListModel.builder().build(), addresses);
	}
	public static List<PoolData> list(ListModel model, List<String> addresses) {
		return Resource.list(
			model.toBuilder()
				.type("pool")
				.keys(addresses)
				.build(),
			PoolData.class
		);
	}

	/**
	 * Scan pools by token and program, sort by created
	 */
	public static List<PoolData> scanTokenProgramCreated(String token, String program, String created, String address) {
		return scanTokenProgramCreated(ScanModel.builder().build(), token, program, created, address);
	}
	public static List<PoolData> scanTokenProgramCreated(ScanModel model, String token, String program, String created, String address) {
		final String hashComposed = HashUtil.values(new String[]{token, program});
		return Resource.scan(
			model.toBuilder()
				.type("pool")
				.index("tokenProgram")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			PoolData.class
		);
	}

	/**
	 * Scan pools by creator and program, sort by created
	 */
	public static List<PoolData> scanCreatorProgramCreated(String creator, String program, String created, String address) {
		return scanCreatorProgramCreated(ScanModel.builder().build(), creator, program, created, address);
	}
	public static List<PoolData> scanCreatorProgramCreated(ScanModel model, String creator, String program, String created, String address) {
		final String hashComposed = HashUtil.values(new String[]{creator, program});
		return Resource.scan(
			model.toBuilder()
				.type("pool")
				.index("creatorProgram")
				.indexValue(hashComposed.substring(hashComposed.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			PoolData.class
		);
	}

	/**
	 * Scan pools by name, sort by created
	 */
	public static List<PoolData> scanNameCreated(String name, String created, String address) {
		return scanNameCreated(ScanModel.builder().build(), name, created, address);
	}
	public static List<PoolData> scanNameCreated(ScanModel model, String name, String created, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("pool")
				.index("name")
				.indexValue(name)
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			PoolData.class
		);
	}

	/**
	 * Scan pools by creator, sort by created
	 */
	public static List<PoolData> scanCreatorCreated(String creator, String created, String address) {
		return scanCreatorCreated(ScanModel.builder().build(), creator, created, address);
	}
	public static List<PoolData> scanCreatorCreated(ScanModel model, String creator, String created, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("pool")
				.index("creator")
				.indexValue(creator)
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			PoolData.class
		);
	}

	/**
	 * Scan pools by active program, sort by created
	 */
	public static List<PoolData> scanProgramCreated(String program, String created, String address) {
		return scanProgramCreated(ScanModel.builder().build(), program, created, address);
	}
	public static List<PoolData> scanProgramCreated(ScanModel model, String program, String created, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("pool")
				.index("activeProgram")
				.indexValue(program)
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			PoolData.class
		);
	}

	/**
	 * Scan pools by active program, sort by expires
	 */
	public static List<PoolData> scanProgramExpires(String program, String expires, String address) {
		return scanProgramExpires(ScanModel.builder().build(), program, expires, address);
	}
	public static List<PoolData> scanProgramExpires(ScanModel model, String program, String expires, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("pool")
				.index("activeProgram")
				.indexValue(program)
				.sort("expires")
				.sortValue(expires)
				.keyValue(address)
				.build(),
			PoolData.class
		);
	}

	/**
	 * Scan pools by active program, sort by number
	 */
	public static List<PoolData> scanProgramNumber(String program, String number, String address) {
		return scanProgramNumber(ScanModel.builder().build(), program, number, address);
	}
	public static List<PoolData> scanProgramNumber(ScanModel model, String program, String number, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("pool")
				.index("activeProgram")
				.indexValue(program)
				.sort("number")
				.sortValue(number)
				.keyValue(address)
				.build(),
			PoolData.class
		);
	}

	/**
	 * Scan pools by active program, sort by xetaBalance
	 */
	public static List<PoolData> scanProgramXetaBalance(String program, String xetaBalance, String address) {
		return scanProgramXetaBalance(ScanModel.builder().build(), program, xetaBalance, address);
	}
	public static List<PoolData> scanProgramXetaBalance(ScanModel model, String program, String xetaBalance, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("pool")
				.index("activeProgram")
				.indexValue(program)
				.sort("xetaBalance")
				.sortValue(xetaBalance)
				.keyValue(address)
				.build(),
			PoolData.class
		);
	}

	/**
	 * Scan pools by active program, sort by tokenBalance
	 */
	public static List<PoolData> scanProgramTokenBalance(String program, String tokenBalance, String address) {
		return scanProgramTokenBalance(ScanModel.builder().build(), program, tokenBalance, address);
	}
	public static List<PoolData> scanProgramTokenBalance(ScanModel model, String program, String tokenBalance, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("pool")
				.index("activeProgram")
				.indexValue(program)
				.sort("tokenBalance")
				.sortValue(tokenBalance)
				.keyValue(address)
				.build(),
			PoolData.class
		);
	}

	/**
	 * Scan pools by active program, sort by transfersCount
	 */
	public static List<PoolData> scanProgramTransfersCount(String program, String transfersCount, String address) {
		return scanProgramTransfersCount(ScanModel.builder().build(), program, transfersCount, address);
	}
	public static List<PoolData> scanProgramTransfersCount(ScanModel model, String program, String transfersCount, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("pool")
				.index("activeProgram")
				.indexValue(program)
				.sort("transfersCount")
				.sortValue(transfersCount)
				.keyValue(address)
				.build(),
			PoolData.class
		);
	}
}
