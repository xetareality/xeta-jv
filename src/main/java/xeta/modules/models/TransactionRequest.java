package xeta.modules.models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Data
@Builder(toBuilder = true)
public class TransactionRequest {
	private String hash;
	private String signature;
	private String sender;
	private List<Map<String, String>> instructions;
	private Long nonce;

	public void setSenderIfNull(String sender) {
		if (isNull(this.sender)) {
			this.sender = sender;
		}
	}

	public void setInstructionsIfNull(Map<String, String> instructions) {
		if (isNull(this.instructions)) {
			this.instructions = new ArrayList<>();
			this.instructions.add(instructions);
		}
	}

	public void setNonceIfNull(long nonce) {
		if (isNull(this.nonce)) {
			this.nonce = nonce;
		}
	}
}
