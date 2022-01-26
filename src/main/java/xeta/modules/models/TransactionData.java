package xeta.modules.models;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
@Builder(toBuilder = true)
public class TransactionData {
	private String hash;
	private String signature;
	private String sender;
	private String fee;
	private List<Map<String, String>> instructions;
	private Long nonce;
	private Long created;
	private Boolean sponsored;
	private Long period;
	private String partition;
	private List<List<String>> outputs;
	private String error;
	private Long confirmed;
	private Long confirmations;

	public static TransactionData fromRequest(TransactionRequest tx, Map<String, String> instructions) {
		return TransactionData.builder()
			.hash(tx.getHash())
			.signature(tx.getSignature())
			.sender(tx.getSender())
			.instructions(Collections.singletonList(instructions))
			.nonce(tx.getNonce())
			.build();
	}
}
