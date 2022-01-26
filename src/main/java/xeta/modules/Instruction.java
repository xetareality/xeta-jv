package xeta.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xeta.library.StripUtil;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Instruction {

	/**
	 * Wrap arguments as instruction
	 * Submit instruction as transaction
	 * Or return instruction if submit is false
	 */
	public static TransactionData wrap(Map<String, String> arguments, TransactionRequest tx, Boolean submitTx) {
		final Map<String, String> instructions = StripUtil.stripNulls(arguments);
		if (!submitTx) return TransactionData.fromRequest(tx, instructions);
		return Transaction.submit(instructions, tx);
	}
}
