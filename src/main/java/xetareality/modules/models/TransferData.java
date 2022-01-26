package xetareality.modules.models;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder(toBuilder = true)
public class TransferData {
	private String hash;
	private String sender;
	private String from;
	private String to;
	private String token;
	private String amount;
	private Long created;
	private String message;
	private String origin;
	private String fromToken;
	private String toToken;
}
