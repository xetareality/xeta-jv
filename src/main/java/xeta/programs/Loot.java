package xeta.programs;

import lombok.RequiredArgsConstructor;
import xeta.library.MapBuilder;
import xeta.modules.Instruction;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

@RequiredArgsConstructor
public class Loot implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to loot pool
	 */
	public TransactionData transfer(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "loot.transfer")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Deposit to loot pool
	 */
	public TransactionData deposit(String token, String unlocks, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "loot.deposit")
				.put("pool", this.pool.getAddress())
				.put("token", token)
				.put("unlocks", unlocks)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Withdraw from loot pool
	 */
	public TransactionData withdraw(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "loot.withdraw")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Clear loot pool
	 */
	public TransactionData clear(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "loot.clear")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}
}
