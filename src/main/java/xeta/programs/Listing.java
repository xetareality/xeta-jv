package xeta.programs;

import lombok.RequiredArgsConstructor;
import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.modules.Instruction;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

@RequiredArgsConstructor
public class Listing implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to listing pool
	 */
	public TransactionData transfer(String amount, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "listing.transfer")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Deposit to listing pool
	 */
	public TransactionData deposit(String amount, String unlocks, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "listing.deposit")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.put("unlocks", unlocks)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Close listing pool
	 */
	public TransactionData close(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "listing.close")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}
}
