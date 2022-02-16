package xeta.library;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneralUtilTest {

	@Test
	public void testAmount() {
		assertEquals("1", GeneralUtil.amount("1"));
		assertEquals("9", GeneralUtil.amount("009.000000003"));
		assertEquals("10", GeneralUtil.amount("10"));
		assertEquals("20000", GeneralUtil.amount("20000.00000"));
		assertEquals("727.7085", GeneralUtil.amount("727.70850"));
		assertEquals("3829.23609832", GeneralUtil.amount("3829.236098323564"));
		assertEquals("53764.39107887", GeneralUtil.amount("53764.391078867"));
		assertEquals("65836.625", GeneralUtil.amount("65836.625000003"));
		assertEquals("113390.31201709", GeneralUtil.amount("113390.312017091"));
		assertEquals("8499999997.74", GeneralUtil.amount("8499999997.74"));
		assertEquals("12345678912345.6", GeneralUtil.amount("12345678912345.6"));
	}
}