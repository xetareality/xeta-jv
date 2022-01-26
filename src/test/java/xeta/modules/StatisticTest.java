package xeta.modules;

import org.junit.Test;
import xeta.modules.models.ScanModel;
import xeta.modules.models.StatisticData;

import java.util.List;

import static org.junit.Assert.*;

public class StatisticTest {

	@Test
	public void testRead() {
		final List<StatisticData> scanTransfers = Statistic.scan(
			ScanModel.builder().build(),
			"transfers:total:d",
			null
		);
		System.out.println(scanTransfers);
//		assertEquals(
//			"[{\"key\":\"transfers:total:d\",\"time\":1642550400,\"until\":1642636800,\"value\":9309},{\"key\":\"transfers:total:d\",\"time\":1642464000,\"until\":1642550400,\"value\":9227},{\"key\":\"transfers:total:d\",\"time\":1642377600,\"until\":1642464000,\"value\":9192},{\"key\":\"transfers:total:d\",\"time\":1642291200,\"until\":1642377600,\"value\":9190},{\"key\":\"transfers:total:d\",\"time\":1642204800,\"until\":1642291200,\"value\":9178},{\"key\":\"transfers:total:d\",\"time\":1642118400,\"until\":1642204800,\"value\":9147},{\"key\":\"transfers:total:d\",\"time\":1642032000,\"until\":1642118400,\"value\":9115},{\"key\":\"transfers:total:d\",\"time\":1641945600,\"until\":1642032000,\"value\":9075},{\"key\":\"transfers:total:d\",\"time\":1641859200,\"until\":1641945600,\"value\":9025},{\"key\":\"transfers:total:d\",\"time\":1641772800,\"until\":1641859200,\"value\":8931},{\"key\":\"transfers:total:d\",\"time\":1641686400,\"until\":1641772800,\"value\":8811},{\"key\":\"transfers:total:d\",\"time\":1641600000,\"until\":1641686400,\"value\":8635},{\"key\":\"transfers:total:d\",\"time\":1641513600,\"until\":1641600000,\"value\":7950},{\"key\":\"transfers:total:d\",\"time\":1641427200,\"until\":1641513600,\"value\":7610},{\"key\":\"transfers:total:d\",\"time\":1641340800,\"until\":1641427200,\"value\":7578},{\"key\":\"transfers:total:d\",\"time\":1641254400,\"until\":1641340800,\"value\":0}]",
//			scanTransfers
//		);
		assertEquals(0, scanTransfers.size());

		final List<StatisticData> scanStatistics = Statistic.scan(
			ScanModel.builder().build(),
			"statistics:total:d",
			null
		);
		System.out.println(scanStatistics);
//		assertEquals(
//			"[{\"key\":\"statistics:total:d\",\"time\":1642550400,\"until\":1642636800,\"value\":15288},{\"key\":\"statistics:total:d\",\"time\":1642464000,\"until\":1642550400,\"value\":14196},{\"key\":\"statistics:total:d\",\"time\":1642377600,\"until\":1642464000,\"value\":13146},{\"key\":\"statistics:total:d\",\"time\":1642291200,\"until\":1642377600,\"value\":12054},{\"key\":\"statistics:total:d\",\"time\":1642204800,\"until\":1642291200,\"value\":10962},{\"key\":\"statistics:total:d\",\"time\":1642118400,\"until\":1642204800,\"value\":10080},{\"key\":\"statistics:total:d\",\"time\":1642032000,\"until\":1642118400,\"value\":8988},{\"key\":\"statistics:total:d\",\"time\":1641945600,\"until\":1642032000,\"value\":7938},{\"key\":\"statistics:total:d\",\"time\":1641859200,\"until\":1641945600,\"value\":6846},{\"key\":\"statistics:total:d\",\"time\":1641772800,\"until\":1641859200,\"value\":5754},{\"key\":\"statistics:total:d\",\"time\":1641686400,\"until\":1641772800,\"value\":4704},{\"key\":\"statistics:total:d\",\"time\":1641600000,\"until\":1641686400,\"value\":3780},{\"key\":\"statistics:total:d\",\"time\":1641513600,\"until\":1641600000,\"value\":2730},{\"key\":\"statistics:total:d\",\"time\":1641427200,\"until\":1641513600,\"value\":1638},{\"key\":\"statistics:total:d\",\"time\":1641340800,\"until\":1641427200,\"value\":588},{\"key\":\"statistics:total:d\",\"time\":1641254400,\"until\":1641340800,\"value\":0}]",
//			scanStatistics
//		);
		assertEquals(0, scanStatistics.size());
	}

	@Test
	public void scan() {
		final List<StatisticData> scan = Statistic.scan("statistics:total:d", null);
		assertNotNull(scan);
		assertEquals(0, scan.size());
	}

}