package xeta.modules;

import org.junit.Before;
import org.junit.Test;
import xeta.library.Config;
import xeta.modules.models.ClaimData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ClaimTest {

	@Before
	public void before() {
		Config.dev = true;
		Config.publicKey = "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY";
		Config.privateKey = "2fjNNm5R34W95FGiS6XoebRpeVx7SUPL5HjfwzPmZq3G";
	}

	@Test
	public void create() {
		final TransactionData claim = Claim.create("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "11111111111111111111111111111xeta", "1", null, null, null, null, null, null, null, null, TransactionRequest.builder().build(), true);
		assertNotNull(claim);
		assertNull(claim.getError());
	}

	@Test
	public void update() {
		final TransactionData update = Claim.update("3kediNKGd87VnZ77gSkA25Lhisxrz8nB8XKagmUck7gQ", "3", "5", null, null, null, "gifts", null, "yes", null, TransactionRequest.builder().build(), true);
		assertNotNull(update);
		assertNull(update.getError());
	}

	@Test
	public void transfer() {
		final TransactionData transfer = Claim.transfer("DrVxYSKtj69FjNVb9LNkpja8oBaLo8PFjLVcFBJ1KT2o", "Ctx9mNhtr62HtjukT2cvzaaqbp6BRyMsz7osz5UYCvaL", TransactionRequest.builder().build(), true);
		assertNotNull(transfer);
		assertNull(transfer.getError());
	}

	@Test
	public void resolve() {
		final TransactionData resolve = Claim.resolve("3kediNKGd87VnZ77gSkA25Lhisxrz8nB8XKagmUck7gQ", TransactionRequest.builder().build(), true);
		assertNotNull(resolve);
		assertNull(resolve.getError());
	}

	@Test
	public void read() {
		final ClaimData claim = Claim.read("DrVxYSKtj69FjNVb9LNkpja8oBaLo8PFjLVcFBJ1KT2o");
		assertNotNull(claim);
	}

	@Test
	public void list() {
		final List<ClaimData> claims = Claim.list(Arrays.asList("DrVxYSKtj69FjNVb9LNkpja8oBaLo8PFjLVcFBJ1KT2o", "3kediNKGd87VnZ77gSkA25Lhisxrz8nB8XKagmUck7gQ"));
		assertEquals(2, claims.size());
	}

	@Test
	public void scanHolderCategoryCreated() {
		final List<ClaimData> claims = Claim.scanHolderCategoryCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "gifts", null, null);
		assertEquals(0, claims.size());
	}

	@Test
	public void scanIssuerCategoryCreated() {
		final List<ClaimData> claimData = Claim.scanIssuerCategoryCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "gifts", null, null);
		assertEquals(0, claimData.size());
	}

	@Test
	public void scanIssuerAnswer() {
		final List<ClaimData> claimData = Claim.scanIssuerAnswer("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "yes", null);
		assertEquals(1, claimData.size());
	}

	@Test
	public void scanIssuerNumber() {
		final List<ClaimData> claims = Claim.scanIssuerNumber("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "7", null);
		assertEquals(0, claims.size());
	}

	@Test
	public void scanIssuerTokenAmount() {
		final List<ClaimData> claims = Claim.scanIssuerTokenAmount("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "3", null);
		assertEquals(2, claims.size());
	}

	@Test
	public void scanIssuerXetaAmount() {
		final List<ClaimData> claims = Claim.scanIssuerXetaAmount("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "5", null);
		assertEquals(1, claims.size());
	}

	@Test
	public void scanIssuerCreated() {
		final List<ClaimData> claims = Claim.scanIssuerCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", null, null);
		assertEquals(2, claims.size());
	}

	@Test
	public void scanHolderCreated() {
		final List<ClaimData> claims = Claim.scanHolderCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", null, null);
		assertEquals(2, claims.size());
	}

	@Test
	public void scanIssuerTokenCreated() {
		final List<ClaimData> claims = Claim.scanIssuerTokenCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "11111111111111111111111111111xeta", null, null);
		assertEquals(1, claims.size());
	}

	@Test
	public void scanHolderTokenCreated() {
		final List<ClaimData> claims = Claim.scanHolderTokenCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "11111111111111111111111111111xeta", null, null);
		assertEquals(3, claims.size());
	}

	@Test
	public void scanIssuerHolder() {
		final List<ClaimData> claims = Claim.scanIssuerHolder("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", null, null);
		assertEquals(2, claims.size());
	}

	@Test
	public void scanIssuerHolderToken() {
		final List<ClaimData> claims = Claim.scanIssuerHolderToken("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "11111111111111111111111111111xeta", null, null);
		assertEquals(1, claims.size());
	}
}