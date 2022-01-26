package xeta.programs;

import lombok.RequiredArgsConstructor;
import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.modules.Instruction;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

@RequiredArgsConstructor
public class Lock implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to lock pool
	 */
	public TransactionData transfer(String amount, String unlocks, String expires, String address,
									TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lock.transfer")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.put("unlocks", unlocks)
				.put("expires", expires)
				.put("address", address)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Claim from lock pool
	 */
	public TransactionData claim(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lock.claim")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}
}
