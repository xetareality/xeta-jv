package xeta.programs;

import lombok.RequiredArgsConstructor;
import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.modules.Instruction;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import java.time.Instant;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class Staking implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to staking pool
	 */
	public TransactionData transfer(String amount, String unlocks, TransactionRequest tx, Boolean submitTx) {
		if (nonNull(unlocks) && Long.parseLong(unlocks) < Instant.now().toEpochMilli() + 24*60*60*1000) {
			throw new RuntimeException("invalid:time");
		}
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "staking.transfer")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.put("unlocks", unlocks)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Claim from staking pool
	 */
	public TransactionData claim(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "staking.claim")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Deposit to staking pool
	 */
	public TransactionData deposit(String amount, String unlocks, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "staking.deposit")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.put("unlocks", unlocks)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Withdraw from staking pool
	 */
	public TransactionData withdraw(String claim, Double percentage, TransactionRequest tx, Boolean submitTx) {
		percentage = isNull(percentage) ? 1.0 : percentage;
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "staking.withdraw")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.put("percentage", Double.toString(percentage))
				.build(),
			tx,
			submitTx
		);
	}
}
