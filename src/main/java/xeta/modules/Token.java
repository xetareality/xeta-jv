package xeta.modules;

import xeta.library.GeneralUtil;
import xeta.library.MapBuilder;
import xeta.library.hash.HashUtil;
import xeta.modules.models.ListModel;
import xeta.modules.models.ReadModel;
import xeta.modules.models.ScanModel;
import xeta.modules.models.TokenData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;

import java.util.List;

public class Token {

	/**
	 * Create token
	 */
	public static TransactionData create(String name, String symbol, String supply, String reserve, String whole,
										 String description, String links, String meta, String preview, String owner,
										 String frozen, String category, String object, String mime, String content,
										 TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "token.create")
				.put("name", name)
				.put("symbol", symbol)
				.put("whole", whole)
				.put("supply", GeneralUtil.amount(supply))
				.put("reserve", GeneralUtil.amount(reserve))
				.put("description", description)
				.put("links", links)
				.put("meta", meta)
				.put("preview", preview)
				.put("owner", owner)
				.put("frozen", frozen)
				.put("category", category)
				.put("object", object)
				.put("mime", mime)
				.put("content", content)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Update specified values of a token
	 */
	public static TransactionData update(String token, String name, String description, String links, String meta,
										 String preview, String frozen, String category, String mime,
										 TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "token.update")
				.put("token", token)
				.put("name", name)
				.put("description", description)
				.put("links", links)
				.put("meta", meta)
				.put("preview", preview)
				.put("frozen", frozen)
				.put("category", category)
				.put("mime", mime)
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Mint from reserve
	 */
	public static TransactionData mint(String token, String amount, TransactionRequest tx, Boolean submitTx) {
		return Instruction.wrap(
			MapBuilder.builder()
				.put("function", "token.mint")
				.put("token", token)
				.put("amount", GeneralUtil.amount(amount))
				.build(),
			tx,
			submitTx
		);
	}

	/**
	 * Read token by address
	 */
	public static TokenData read(String address) {
		return read(ReadModel.builder().build(), address);
	}
	public static TokenData read(ReadModel model, String address) {
		return Resource.read(
			model.toBuilder()
				.type("token")
				.key(address)
				.build(),
			TokenData.class
		);
	}

	/**
	 * List tokens by addresses
	 */
	public static List<TokenData> list(List<String> addresses) {
		return list(ListModel.builder().build(), addresses);
	}
	public static List<TokenData> list(ListModel model, List<String> addresses) {
		return Resource.list(
			model.toBuilder()
				.type("token")
				.keys(addresses)
				.build(),
			TokenData.class
		);
	}

	/**
	 * Scan tokens by creator, sort by created
	 */
	public static List<TokenData> scanCreatorCreated(String creator, String created, String address) {
		return scanCreatorCreated(ScanModel.builder().build(), creator, created, address);
	}
	public static List<TokenData> scanCreatorCreated(ScanModel model, String creator, String created, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("token")
				.index("creator")
				.indexValue(creator)
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			TokenData.class
		);
	}

	/**
	 * Scan tokens by name, sort by created
	 */
	public static List<TokenData> scanNameCreated(String name, String created, String address) {
		return scanNameCreated(ScanModel.builder().build(), name, created, address);
	}
	public static List<TokenData> scanNameCreated(ScanModel model, String name, String created, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("token")
				.index("name")
				.indexValue(name)
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			TokenData.class
		);
	}

	/**
	 * Scan tokens by symbol, sort by created
	 */
	public static List<TokenData> scanSymbolCreated(String symbol, String created, String address) {
		return scanSymbolCreated(ScanModel.builder().build(), symbol, created, address);
	}
	public static List<TokenData> scanSymbolCreated(ScanModel model, String symbol, String created, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("token")
				.index("symbol")
				.indexValue(symbol)
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			TokenData.class
		);
	}

	/**
	 * Scan tokens by owner, sort by created
	 */
	public static List<TokenData> scanOwnerCreated(String owner, String created, String address) {
		return scanOwnerCreated(ScanModel.builder().build(), owner, created, address);
	}
	public static List<TokenData> scanOwnerCreated(ScanModel model, String owner, String created, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("token")
				.index("owner")
				.indexValue(owner)
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			TokenData.class
		);
	}

	/**
	 * Scan tokens by content, sort by created
	 */
	public static List<TokenData> scanContentCreated(String content, String created, String address) {
		return scanContentCreated(ScanModel.builder().build(), content, created, address);
	}
	public static List<TokenData> scanContentCreated(ScanModel model, String content, String created, String address) {
		return Resource.scan(
			model.toBuilder()
				.type("token")
				.index("content")
				.indexValue(content)
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			TokenData.class
		);
	}

	/**
	 * Scan tokens by owner and category, sort by created
	 */
	public static List<TokenData> scanOwnerCategoryCreated(String owner, String category, String created, String address) {
		return scanOwnerCategoryCreated(ScanModel.builder().build(), owner, category, created, address);
	}
	public static List<TokenData> scanOwnerCategoryCreated(ScanModel model, String owner, String category,
														   String created, String address) {
		String hash = HashUtil.values(new String[]{owner, category});
		return Resource.scan(
			model.toBuilder()
				.type("token")
				.index("ownerCategory")
				.indexValue(hash.substring(hash.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			TokenData.class
		);
	}

	/**
	 * Scan tokens by creator and category, sort by created
	 */
	public static List<TokenData> scanCreatorCategoryCreated(String creator, String category, String created, String address) {
		return scanCreatorCategoryCreated(ScanModel.builder().build(), creator, category, created, address);
	}
	public static List<TokenData> scanCreatorCategoryCreated(ScanModel model, String creator, String category,
															 String created, String address) {
		String hash = HashUtil.values(new String[]{creator, category});
		return Resource.scan(
			model.toBuilder()
				.type("token")
				.index("creatorCategory")
				.indexValue(hash.substring(hash.length() - 8))
				.sort("created")
				.sortValue(created)
				.keyValue(address)
				.build(),
			TokenData.class
		);
	}
}
