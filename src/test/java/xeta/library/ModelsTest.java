package xeta.library;

import org.junit.Test;

import static org.junit.Assert.*;

public class ModelsTest {

	@Test
	public void testVerifyHash() {
		assertTrue(Models.verifyHash("CUb1dvYa9wxek9HyfqyLSx5QnCWoQB5DNphwMLdK2scW"));
		assertTrue(Models.verifyHash("FKHyhLvdqVngcFEop4qgqxuJaxdAFPjwinYG2JT65qwX"));

		assertFalse(Models.verifyHash("aJRDxKLdqE3NMoH3jZkuF1DA3tiZ2kQM4YTJGsnEqdu5ExmKdgUYL6QMrtwbFRYscgGFpFC1ESMQkTECAdJGCDM"));
		assertFalse(Models.verifyHash("https://xeta.network/transfer/?hash=FKHyhLvdqVngcFEop4qgqxuJaxdAFPjwinYG2JT65qwX"));
		assertFalse(Models.verifyHash("this should fail as there are spaces"));
	}
}