package xeta.library;

import static java.util.Objects.nonNull;

public class Config {

	public static String publicKey = null;
	public static String privateKey = null;
	public static String account = null;
	public static String secret = null;
	public static boolean dev = false;
	public static String xetaInterface = "https://interface.xetareality.com";
	public static String network = "https://network.xetareality.com";
	public static String xetaAddress = "11111111111111111111111111111xeta";
	public static String factoryAddress = "11111111111111111111111111factory";
	public static String xusdAddress = "11111111111111111111111111111xusd";
	public static String sponsoredAddress = "1111111111111111111111111sponsored";
	public static String consumedAddress = "11111111111111111111111111consumed";
	public static String zeroAddress = "11111111111111111111111111111zero";
	public static String burnAddress = "11111111111111111111111111111burn";

	public static void init(String networkEndpoint, String interfaceEndpoint, Boolean dev) {
		if (nonNull(networkEndpoint)) Config.network = networkEndpoint;
		if (nonNull(interfaceEndpoint)) Config.xetaInterface = interfaceEndpoint;
		if (nonNull(dev)) Config.dev = dev;
	}

	public static String devString() {
		return Boolean.toString(dev);
	}
}
