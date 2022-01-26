package xetareality.modules.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ClaimData {
	private String hash;
	private String creator;
	private Long created;
	private String owner;
	private String token;
	private String tokenAmount;
	private String xetaAmount;
	private Long expires;
	private Long unlocks;
	private String origin;
	private Long resolved;
	private String resolution;

	private Boolean frozen;
	private String category;
	private String meta;
	private String answer;
	private Long number;
	private String holderCategory;
	private String issuerCategory;

	private String holder;
	private String issuer;
	private String holderToken;
	private String issuerToken;
	private String issuerHolder;
	private String issuerHolderToken;
}
