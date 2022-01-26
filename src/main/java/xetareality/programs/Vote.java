package xetareality.programs;

import lombok.RequiredArgsConstructor;
import xetareality.library.GeneralUtil;
import xetareality.library.MapBuilder;
import xetareality.modules.Instruction;
import xetareality.modules.models.PoolData;
import xetareality.modules.models.TransactionData;
import xetareality.modules.models.TransactionRequest;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class Vote implements PoolDependent {

	private final PoolData pool;

	/**
	 * Transfer to vote pool
	 */
	public TransactionData transfer(Double amount, String answer, String number, TransactionRequest tx, Boolean submitTx) {
		amount = isNull(amount) ? 0.0 : amount;
		if ((nonNull(this.pool.getCandidates()) && isNull(answer)) || (nonNull(this.pool.getCandidates()) && nonNull(number))) {
			throw new RuntimeException("answer:invalid");
		}
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "vote.transfer")
				.put("pool", this.pool.getAddress())
				.put("amount", GeneralUtil.amount(Double.toString(amount)))
				.put("answer", answer)
				.put("number", number)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Claim from vote pool
	 */
	public TransactionData claim(String claim, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "vote.claim")
				.put("pool", this.pool.getAddress())
				.put("claim", claim)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Resolve vote pool
	 */
	public TransactionData resolve(TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "vote.resolve")
				.put("pool", this.pool.getAddress())
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Set accepted answer
	 */
	public TransactionData oracle(String answer, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "vote.oracle")
				.put("pool", this.pool.getAddress())
				.put("answer", answer)
				.build(),
			tx,
			submitTx
		);
	}
}
