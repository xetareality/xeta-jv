package xeta.programs;

import lombok.RequiredArgsConstructor;
import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.modules.Instruction;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

@RequiredArgsConstructor
public class Lottery implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to lottery pool
	 */
	public TransactionData transfer(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lottery.transfer")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Claim from lottery pool
	 */
	public TransactionData claim(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lottery.claim")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Resolve NFT lottery pool
	 */
	public TransactionData resolve(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lottery.resolve")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Deposit to lottery pool
	 */
	public TransactionData deposit(String amount, String unlocks, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lottery.deposit")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.put("unlocks", unlocks)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Withdraw from lottery pool
	 */
	public TransactionData withdraw(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lottery.withdraw")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Close lottery pool
	 */
	public TransactionData close(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lottery.close")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Clear lottery pool
	 */
	public TransactionData clear(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lottery.clear")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}
}
