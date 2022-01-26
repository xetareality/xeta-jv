package xeta.modules;

import org.junit.Test;
import xeta.modules.models.CandleData;

import java.util.List;

import static org.junit.Assert.*;

public class CandleTest {

	@Test
	public void read() {
		final CandleData read = Candle.read("5m", "11111111111111111111111111111xusd", null);
		assertNull(read);
	}

	@Test
	public void scanIntervalTokenTime() {
		final List<CandleData> candleData = Candle.scanIntervalTokenTime("5m", "11111111111111111111111111111xusd", null);
		assertNotNull(candleData);
	}
}