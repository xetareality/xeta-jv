package xeta.programs;

import lombok.RequiredArgsConstructor;
import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.modules.Instruction;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

@RequiredArgsConstructor
public class Launch implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to launch pool
	 */
	public TransactionData transfer(String amount, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "launch.transfer")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Resolve launch pool
	 */
	public TransactionData transfer(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "launch.resolve")
				.put("pool", this.pool.getAddress())
				.put("token", this.pool.getToken())
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Claim from launch pool
	 */
	public TransactionData claim(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "launch.claim")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Deposit to launch pool
	 */
	public TransactionData deposit(String amount, String unlocks, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "launch.deposit")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.put("unlocks", unlocks)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Withdraw from launch pool
	 */
	public TransactionData withdraw(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "launch.withdraw")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Close launch pool
	 */
	public TransactionData close(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "launch.close")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}

}
