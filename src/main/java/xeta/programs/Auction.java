package xeta.programs;

import lombok.RequiredArgsConstructor;
import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.modules.Instruction;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

@RequiredArgsConstructor
public class Auction implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to auction pool
	 */
	public TransactionData transfer(String amount, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "auction.transfer")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Deposit to auction pool
	 */
	public TransactionData deposit(String unlocks, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "auction.deposit")
				.put("pool", this.pool.getAddress())
				.put("unlocks", unlocks)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Resolve auction pool
	 */
	public TransactionData resolve(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "auction.resolve")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Cancel auction pool
	 */
	public TransactionData cancel(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "auction.cancel")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Close auction pool
	 */
	public TransactionData close(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "auction.close")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}

}
