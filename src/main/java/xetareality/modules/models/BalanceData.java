package xetareality.modules.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class BalanceData {
	private String hash;
	private String address;
	private String token;
	private String amount;
}
