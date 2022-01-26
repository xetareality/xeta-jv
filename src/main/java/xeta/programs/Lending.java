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
public class Lending implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to lending pool
	 */
	public TransactionData transfer(String amount, String collateralization, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lending.transfer")
				.put("pool", this.pool.getAddress())
				.put("token", this.pool.getToken())
				.put("amount", GeneralUtil.amount(amount))
				.put("collateralization", collateralization)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Settle claim from lending pool
	 */
	public TransactionData settle(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lending.settle")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Liquidate claim from lending pool
	 */
	public TransactionData liquidate(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lending.liquidate")
				.put("pool", this.pool.getAddress())
				.put("token", this.pool.getToken())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Deposit to lending pool
	 */
	public TransactionData deposit(String amount, String unlocks, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lending.deposit")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(amount))
				.put("unlocks", unlocks)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Withdraw from lending pool
	 */
	public TransactionData withdraw(String claim, Double percentage, TransactionRequest tx, Boolean submitTx) {
		percentage = isNull(percentage) ? 1.0 : percentage;
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "lending.withdraw")
				.put("pool", this.pool.getAddress())
				.put("token", this.pool.getToken())
				.put("claim", claim)
				.put("percentage", Double.toString(percentage))
				.build(),
			tx,
			submitTx
		);
	}
}
