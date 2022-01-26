package xetareality.modules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xetareality.library.MapBuilder;
import xetareality.modules.models.TransactionData;
import xetareality.modules.models.TransactionRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Profile {

	public static TransactionData update(String name, String description, String links, String meta, String preview, String category,
										 TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "profile.update")
				.put("name", name)
				.put("description", description)
				.put("links", links)
				.put("meta", meta)
				.put("preview", preview)
				.put("category", category)
				.build(),
			tx,
			submitTx
		);
	}
}
