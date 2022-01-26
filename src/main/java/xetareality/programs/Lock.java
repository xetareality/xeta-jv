package xetareality.programs;

import lombok.RequiredArgsConstructor;
import xetareality.library.GeneralUtil;
import xetareality.library.MapBuilder;
import xetareality.modules.Instruction;
import xetareality.modules.models.PoolData;
import xetareality.modules.models.TransactionData;
import xetareality.modules.models.TransactionRequest;

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
