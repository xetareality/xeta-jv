package xeta.modules.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class PoolData {

	private final String address;
	private final String creator;
	private final String token;
	private final String program;
	private final Long created;
	private final String origin;
	private final String tokenProgram;
	private final String creatorProgram;
	private final String activeProgram;

	private final String name;
	private final String description;
	private final String mechanism;
	private final List<String> candidates;
	private final Long rate;
	private final Long percentage;
	private final List<String> answers;
	private final Object meta;

	private final Long expires;
	private final String minAmount;
	private final String maxAmount;
	private final Long minTime;
	private final Long maxTime;
	private final Long transfersLimit;
	private final Long claimsLimit;
	private final String tokenLimit;
	private final String xetaLimit;
	private final String tokenTarget;
	private final String xetaTarget;

	private final String xetaBalance;
	private final String tokenBalance;
	private final String xetaTurnover;
	private final String tokenTurnover;
	private final Long transfersCount;
	private final Long claimsCount;
	private final Boolean closed;
	private final Long number;
}
