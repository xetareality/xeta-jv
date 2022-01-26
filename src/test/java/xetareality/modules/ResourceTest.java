package xetareality.modules;

import org.junit.Test;
import xetareality.library.StripUtil;
import xetareality.modules.models.ListModel;
import xetareality.modules.models.ReadModel;
import xetareality.modules.models.ScanModel;
import xetareality.modules.models.StatisticData;
import xetareality.modules.models.TransferData;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class ResourceTest {

	@Test
	public void testRead() {
		final StatisticData statisticData = Resource.read(
			ReadModel.builder()
				.type("statistic")
				.key("transfers:total:h")
				.sort("time")
				.sortValue("1642532400")
				.build(),
			StatisticData.class
		);
		System.out.println(statisticData);
		assertNotNull(statisticData);
		assertEquals("transfers:total:h", statisticData.getKey());
		assertEquals(1642532400L, statisticData.getTime().longValue());
		assertEquals(1642536000L, statisticData.getUntil().longValue());
		assertEquals(9196L, statisticData.getValue().longValue());
	}

	// https://interface.xetareality.com/list?type=transfer&keyValues=H2ymSADc3ypaiahVMgztQdh8bRkL4a1CyYCWU6pRbBtc,aAzTAcJA7NNGct57HTREMcpSyucxF4GWJPcEgLpsbiN,9uPc4mxHPcz6B2Wt17HJn1HXvt7uR18cP1WCXe1kFJsx
	@Test
	public void testList() {
		final List<TransferData> list = Resource.list(
			ListModel.builder()
				.type("transfer")
				.keys(asList("H2ymSADc3ypaiahVMgztQdh8bRkL4a1CyYCWU6pRbBtc", "aAzTAcJA7NNGct57HTREMcpSyucxF4GWJPcEgLpsbiN", "9uPc4mxHPcz6B2Wt17HJn1HXvt7uR18cP1WCXe1kFJsx"))
				.build(),
			TransferData.class
		);
		System.out.println(list);
		assertEquals(3, list.size());
		assertEquals("ujnUbGoQ", list.get(0).getFromToken());
		assertEquals("ujnUbGoQ", list.get(1).getFromToken());
		assertEquals("ujnUbGoQ", list.get(2).getFromToken());
		assertEquals("suPNsDpW", list.get(0).getToToken());
		assertEquals("suPNsDpW", list.get(1).getToToken());
		assertEquals("suPNsDpW", list.get(2).getToToken());
	}

	// type=transfer&index=token&indexValue=11111111111111111111111111111xeta&sort=created&asc&limit=25&preview=true
	@Test
	public void testScan() {
		final List<TransferData> scan = Resource.scan(
			ScanModel.builder()
				.type("transfer")
				.index("token")
				.indexValue("11111111111111111111111111111xeta")
				.sort("created")
				.asc(true)
				.limit(25)
				.preview(true)
				.build(),
			TransferData.class
		);
		System.out.println(scan);
		assertEquals(25, scan.size());
//		assertEquals(
//			"[{\"sender\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"created\":1642600128683,\"from\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"to\":\"CMpTekLg2MKrxdendLW7xpyQUyDM2two9gXwfe3JzHEA\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"CsArJ8GvXf13YeYPm3QWcd16VXPJAzERL4TD22B4QgHb\",\"amount\":\"10\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"created\":1642597627224,\"from\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"to\":\"A1MjC2FowXDZkQeA6R24H2zJWnZJfnTpqrKKeQC76ncp\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"43W7ADDSsMN9epw5Xc7mzWQX8fhekuFzv4VwaAKPby7M\",\"amount\":\"7742\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"created\":1642596864322,\"from\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"to\":\"6QUj1oouxpDFPLwhYEm9BL5CCLEtjZhVfoHQjSZJMiet\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"CAMtoTLCCtv3YZMELgPb2bTocd6TUvGXWfpvD4stJNKg\",\"amount\":\"10\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"ExQ2ocy5zL7wdr95Mo9dH8LQZMu7WiQqddCyGQgniHgV\",\"created\":1642591023118,\"from\":\"ExQ2ocy5zL7wdr95Mo9dH8LQZMu7WiQqddCyGQgniHgV\",\"to\":\"AwrATWDVP3gwHdg7LVEvSBDFHvjPpz16AmCgJvGDB38U\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"BBU3R1ZsBUrYumQwzvekM2KeU9pq65p8MUMq7bX8TSWk\",\"amount\":\"100\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"created\":1642588566511,\"from\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"to\":\"4XspcJmBqLbPQ4FJZ6BQdXeG9AKDHMSV6DGGGTmMkEGa\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"7eZuvHmB1YNec8GREHSwE2ijuhrCBjf6T5z1aDrtitmp\",\"amount\":\"53764.39107887\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"created\":1642588238084,\"from\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"to\":\"EvjSG42nnrkUNF1xFQm8KM1YAMezDdg3DxYkzAbyFQL3\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"Dxtr5t73kvXTS4ThaHmNeDAjZXFoSPxssMymDuCCCEbr\",\"amount\":\"10\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"created\":1642588028394,\"from\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"to\":\"EvjSG42nnrkUNF1xFQm8KM1YAMezDdg3DxYkzAbyFQL3\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"8ox19YUYAYJrznZviVtFCxxt4AzhLuSDnzaLMVQbxnWH\",\"amount\":\"99936.817\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"created\":1642583761939,\"from\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"to\":\"96Cr4ruVp3fR84rUwR2YUa68unZrmLGfaGfAp2Vm1nuW\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"9he9nzGTb4MxLhEdZpPSX5o6qnhx7ivTRRUVtwsdTTkE\",\"amount\":\"41550.4170659\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"6kuDrA2e4VWNah9VL4YjgmQQqEEcHAi3bnBCNZyopp8b\",\"created\":1642579292765,\"from\":\"6kuDrA2e4VWNah9VL4YjgmQQqEEcHAi3bnBCNZyopp8b\",\"to\":\"CYM9TghPsXHMNuCRtduYFztXzdwSsjkRbrQDuyCHiA3\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"H2ymSADc3ypaiahVMgztQdh8bRkL4a1CyYCWU6pRbBtc\",\"amount\":\"6\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"6kuDrA2e4VWNah9VL4YjgmQQqEEcHAi3bnBCNZyopp8b\",\"created\":1642579145444,\"from\":\"6kuDrA2e4VWNah9VL4YjgmQQqEEcHAi3bnBCNZyopp8b\",\"to\":\"CYM9TghPsXHMNuCRtduYFztXzdwSsjkRbrQDuyCHiA3\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"aAzTAcJA7NNGct57HTREMcpSyucxF4GWJPcEgLpsbiN\",\"amount\":\"6\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"6kuDrA2e4VWNah9VL4YjgmQQqEEcHAi3bnBCNZyopp8b\",\"created\":1642579061486,\"from\":\"6kuDrA2e4VWNah9VL4YjgmQQqEEcHAi3bnBCNZyopp8b\",\"to\":\"CYM9TghPsXHMNuCRtduYFztXzdwSsjkRbrQDuyCHiA3\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"9uPc4mxHPcz6B2Wt17HJn1HXvt7uR18cP1WCXe1kFJsx\",\"amount\":\"1\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"6kuDrA2e4VWNah9VL4YjgmQQqEEcHAi3bnBCNZyopp8b\",\"created\":1642578971244,\"from\":\"6kuDrA2e4VWNah9VL4YjgmQQqEEcHAi3bnBCNZyopp8b\",\"to\":\"CYM9TghPsXHMNuCRtduYFztXzdwSsjkRbrQDuyCHiA3\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"3vfA1NfL8sxge19YnsF6qtE6ZPtni75yUZxFQsYddqJg\",\"amount\":\"1\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"6kuDrA2e4VWNah9VL4YjgmQQqEEcHAi3bnBCNZyopp8b\",\"created\":1642578359434,\"from\":\"6kuDrA2e4VWNah9VL4YjgmQQqEEcHAi3bnBCNZyopp8b\",\"to\":\"o838PyBj5iEZadnipPGehLkyWFg1rJiTQXHHDvajM7N\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"6Dk5ZYtmJVxjiCnwC1s49Bc3m9PT2E3BtqspQsbWTHRv\",\"amount\":\"210\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"o838PyBj5iEZadnipPGehLkyWFg1rJiTQXHHDvajM7N\",\"created\":1642578359434,\"from\":\"o838PyBj5iEZadnipPGehLkyWFg1rJiTQXHHDvajM7N\",\"to\":\"6zpUt7rU36YaSnx6rvSC5oqmqaMaRsCkp4VunX8jBcoi\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"EoV5mVRp9dLkr986XwhrVPiYbpxNSFRzGnBq7HPig9sd\",\"amount\":\"201\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"created\":1642575112608,\"from\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"to\":\"49EdScX34oYsDueYBueyvBaNRpDFsetMbLzqoSX14Zui\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"3i41dXAy9aoKoxYGBrh1iz2dJoZyJzgLELwQXFjdVbXJ\",\"amount\":\"10\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"49EdScX34oYsDueYBueyvBaNRpDFsetMbLzqoSX14Zui\",\"created\":1642574909742,\"from\":\"49EdScX34oYsDueYBueyvBaNRpDFsetMbLzqoSX14Zui\",\"to\":\"CYM9TghPsXHMNuCRtduYFztXzdwSsjkRbrQDuyCHiA3\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"2FmdMcfkPaGRaa1LvWZ1g3uQ2X62b6Ya3aHACrRJkCgd\",\"amount\":\"5\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"created\":1642567837069,\"from\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"to\":\"FJ23fB4HRf693f1gnure6Aba3MrVRsqMmTHEJikZvhBN\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"FyrmmDxx6BhRQNrnWmzcxnTirnmahCP5sHKnsgKLxSb8\",\"amount\":\"10\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"created\":1642567335481,\"from\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"to\":\"FJ23fB4HRf693f1gnure6Aba3MrVRsqMmTHEJikZvhBN\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"HB1zv6FHe4bsWyzfb3vKfBsp59jaPkb84bVqgTTu4zA9\",\"amount\":\"501650\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"created\":1642565828230,\"from\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"to\":\"7J5C1skEvo9YrrQex5z9g2Uk4ThZas4LNxXcLopDFZrG\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"8Be8mw4eqfsY7TRFMABVTg73cpz1MmNKS5wTKtjxtPxR\",\"amount\":\"987.5\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"created\":1642565828089,\"from\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"to\":\"63vfYvjDut9SKuM5aVcDPYmKQtLJQAWUmEmKT8tBVzUW\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"2z7oTV2sCMZdMahEqKyH8tyqni5yETdmRpBfyGibGCxo\",\"amount\":\"65836.625\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"created\":1642565407216,\"from\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"to\":\"63vfYvjDut9SKuM5aVcDPYmKQtLJQAWUmEmKT8tBVzUW\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"ChZ2vkaXsHkafwomozdLtz7HAmw8GRApT486WxefJD9a\",\"amount\":\"9875\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"created\":1642564074014,\"from\":\"J9Z8i8Mt2vk7bgVaziAZuiMTxPN2WficsEgQSnoCTEoB\",\"to\":\"DtU1REaDyk5SLDPeRRkTUsteSihFMTPP5nJ63krRVMLP\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"7525o4Cx3yGmtywHunVJg5A31WHaECnf3oX23M6R9XgY\",\"amount\":\"10\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"created\":1642563728580,\"from\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"to\":\"CEVQWsXqJMnhuXqpH81V9tupBWy4yxQ9MAE1HMGEDGod\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"AiF1Xyjvk52SVFDn6MyUNeemuPnU2f6re7pD2csk8w1k\",\"amount\":\"101208.875\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"created\":1642563067275,\"from\":\"14axkTARTgoVKkvgcjk9xb81aPZKU69C9CWrBEhdSu32\",\"to\":\"CEVQWsXqJMnhuXqpH81V9tupBWy4yxQ9MAE1HMGEDGod\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"9ZNd2FuCeEEwChjAvTqzfpnFs6bpTYZNxorDFuVzzJp6\",\"amount\":\"101712.5\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}},{\"sender\":\"F5PpNbeBNvUJ6CK3r4K3a9WinxBKaz7F2o9FsY8W1PS1\",\"created\":1642560179197,\"from\":\"F5PpNbeBNvUJ6CK3r4K3a9WinxBKaz7F2o9FsY8W1PS1\",\"to\":\"CYM9TghPsXHMNuCRtduYFztXzdwSsjkRbrQDuyCHiA3\",\"token\":\"11111111111111111111111111111xeta\",\"hash\":\"GutimazHdB1oRu5NszYprdRbfEMXY7MorV3T64zWKG2m\",\"amount\":\"9\",\"tokenPreview\":{\"supply\":\"10000000000\",\"creator\":\"CDKy54acH7srs18MqrxhwXkYbZXgN9CydXdnfyNps4eG\",\"symbol\":\"XETA\",\"name\":\"Xeta\"}}]",
//			scan
//		);
	}

	@Test
	public void testStripNulls() {
		final HashMap<Object, Object> toStripWithNulls = new HashMap<>() {{
			put("type", "statistic");
			put("keyValue", "transfers:total:h");
			put("sort", "time");
			put("sortValue", "1642532400");
			put("fields", null);
			put("preview", null);
			put("dev", null);
		}};

		assertEquals("type=statistic&keyValue=transfers:total:h&sort=time&sortValue=1642532400".length(), StripUtil.stripNullsAndConvertToParameters(toStripWithNulls).length());
	}
}