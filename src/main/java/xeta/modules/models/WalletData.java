package xeta.modules.models;

import lombok.Data;

@Data
public class WalletData {
	public final String hash;
	public final String account;
	public final String secret;
	public final String publicKey;
	public final String privateKey;
	public final Long created;
}
