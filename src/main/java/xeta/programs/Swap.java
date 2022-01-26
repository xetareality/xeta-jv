package xeta.programs;

import lombok.RequiredArgsConstructor;
import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.modules.Instruction;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class Swap implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to swap pool
	 */
	public TransactionData transfer(String token, String amount, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "swap.transfer")
				.put("pool", this.pool.getAddress())
				.put("token", token)
				.put("amount", GeneralUtil.amount(amount))
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Deposit to swap pool
	 */
	public TransactionData deposit(String amount, String xetaAmount, String unlocks, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "swap.deposit")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.put("xetaAmount", GeneralUtil.amount(xetaAmount))
				.put("unlocks", unlocks)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Withdraw from swap pool
	 */
	public TransactionData withdraw(String claim, Double percentage, TransactionRequest tx, Boolean submitTx) {
		percentage = isNull(percentage) ? 1.0 : percentage;
		if (percentage > 1.0) throw new RuntimeException("percentage:invalid");
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "swap.withdraw")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.put("percentage", Double.toString(percentage))
				.build(),
			tx,
			submitTx
		);
	}
}
