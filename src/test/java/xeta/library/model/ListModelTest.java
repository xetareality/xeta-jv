package xeta.library.model;

import org.junit.Test;
import xeta.modules.models.ListModel;

import static org.junit.Assert.*;

public class ListModelTest {

	@Test
	public void testBuilder() {
		final ListModel model = ListModel.builder().build();
		assertNotNull(model);
		assertNull(model.getType());
		assertNull(model.getFields());
		assertNull(model.getKeys());
		assertNull(model.getSort());
		assertNull(model.getSortValues());
	}

}