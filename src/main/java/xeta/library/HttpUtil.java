package xeta.library;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtil {

	public static String GET(String url) {
		return ExceptionWrappers.wrapExceptions(() -> {
			final HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(url))
				.GET()
				.build();

			return HttpClient.newHttpClient()
				.send(request, BodyHandlers.ofString())
				.body();
		});
	}

	public static String POST(String url, String body) {
		return ExceptionWrappers.wrapExceptions(() -> {
			final HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(url))
				.POST(HttpRequest.BodyPublishers.ofString(body))
				.build();

			return HttpClient.newHttpClient()
				.send(request, BodyHandlers.ofString())
				.body();
		});
	}
}
