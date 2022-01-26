package xetareality.modules;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchTest {

	@Test
	public void testSearch() {
		final String read = Search.search("J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB");
		System.out.println(read);
		assertEquals("[{\"key\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"type\":\"address\"}]", read);
	}
}