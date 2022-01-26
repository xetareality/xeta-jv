package xeta.modules;

import org.junit.Before;
import org.junit.Test;
import xeta.library.Config;
import xeta.modules.models.PoolData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PoolTest {

	@Before
	public void before() {
		Config.dev = true;
		Config.publicKey = "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY";
		Config.privateKey = "2fjNNm5R34W95FGiS6XoebRpeVx7SUPL5HjfwzPmZq3G";
	}

	@Test
	public void create() {
		final TransactionData transactionData = Pool.create("11111111111111111111111111111xeta", "auction", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, TransactionRequest.builder().build(), true);
		assertNotNull(transactionData);
	}

	@Test
	public void instance() {
	}

	@Test
	public void read() {
		final PoolData pool = Pool.read("xbjrAvN67M8mcChWue9nAjHLdNmtvLkc7DBrtgHECba");
		assertNotNull(pool);
	}

	@Test
	public void list() {
		final List<PoolData> pool = Pool.list(Arrays.asList("DjNV2DdgfA5ds28Mvm3PzyRh3xjeMaGzcLK5VFfE5d6k", "xbjrAvN67M8mcChWue9nAjHLdNmtvLkc7DBrtgHECba"));
		assertEquals(2, pool.size());
	}

	@Test
	public void scanTokenProgramCreated() {
		final List<PoolData> auction = Pool.scanTokenProgramCreated("11111111111111111111111111111xeta", "auction", null, null);
		assertTrue(auction.isEmpty());
	}

	@Test
	public void scanCreatorProgramCreated() {
		final List<PoolData> pool = Pool.scanCreatorProgramCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "auction", null, null);
		assertTrue(pool.isEmpty());
	}

	@Test
	public void scanNameCreated() {
		final List<PoolData> basicPool = Pool.scanNameCreated("basicPool", null, null);
		assertTrue(basicPool.isEmpty());
	}

	@Test
	public void scanCreatorCreated() {
		final List<PoolData> pool = Pool.scanCreatorCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", null, null);
		assertTrue(pool.isEmpty());
	}

	@Test
	public void scanProgramCreated() {
		final List<PoolData> pool = Pool.scanProgramCreated("auction", null, null);
		assertEquals(25, pool.size());
	}

	@Test
	public void scanProgramExpires() {
		final List<PoolData> pool = Pool.scanProgramExpires("auction", null, null);
		assertEquals(25, pool.size());
	}

	@Test
	public void scanProgramNumber() {
		final List<PoolData> pool = Pool.scanProgramNumber("auction", null, null);
		assertTrue(pool.isEmpty());
	}

	@Test
	public void scanProgramXetaBalance() {
		final List<PoolData> pool = Pool.scanProgramXetaBalance("auction", null, null);
		assertEquals(13, pool.size());
	}

	@Test
	public void scanProgramTokenBalance() {
		final List<PoolData> pool = Pool.scanProgramTokenBalance("auction", null, null);
		assertEquals(25, pool.size());
	}

	@Test
	public void scanProgramTransfersCount() {
		final List<PoolData> pool = Pool.scanProgramTransfersCount("auction", null, null);
		assertEquals(13, pool.size());
	}
}