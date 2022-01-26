package xeta.programs;

import lombok.RequiredArgsConstructor;
import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.modules.Instruction;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

@RequiredArgsConstructor
public class Royalty implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to royalty pool
	 */
	public TransactionData transfer(String token, TransactionRequest tx, Boolean submitTx) {
		return this.claim(token, tx, submitTx);
	}

	/**
	 * Claim from royalty pool
	 */
	public TransactionData claim(String token, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "royalty.claim")
				.put("pool", this.pool.getAddress())
				.put("token", token)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Deposit to royalty pool
	 */
	public TransactionData deposit(String amount, String unlocks, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "royalty.deposit")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.put("unlocks", unlocks)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Withdraw from royalty pool
	 */
	public TransactionData withdraw(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "royalty.withdraw")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Close royalty pool
	 */
	public TransactionData close(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "royalty.close")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}
}
