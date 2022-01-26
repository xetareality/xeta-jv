package xeta.modules.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AllowanceData {
	private String hash;
	private String token;
	private String address;
	private String spender;
	private String amount;
	private Long created;
	private String origin;
}
