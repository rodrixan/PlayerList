package com.projects.rodrixan.playerlist.mock;

import android.support.test.InstrumentationRegistry;

import com.projects.rodrixan.playerlist.common.Constants;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class MockedServer {
    private static final String PLAYER_LIST_RESPONSE_JSON = "player_list.json";

    private final int PORT = 12345;
    private final MockWebServer server;

    public MockedServer() throws Exception {
        server = new MockWebServer();
        server.start(PORT);
        Constants.BASE_URL = server.url("/").toString();
        setDispatcher();
    }

    private void setDispatcher() {

        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(final RecordedRequest request) throws
                    InterruptedException {
                try {
                    final String requestPath = request.getPath();

                    final MockResponse response = new MockResponse().setResponseCode(
                            HttpURLConnection.HTTP_OK);
                    String filename;

                    if (requestPath.contains("/bins/66851")) {
                        filename = PLAYER_LIST_RESPONSE_JSON;
                    }
                    else {
                        // no response
                        return new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND);
                    }

                    response.setBody(TestUtils.getStringFromFile(
                            InstrumentationRegistry.getInstrumentation().getContext(), filename));
                    return response;
                }
                catch (final Exception e) {
                    throw new InterruptedException(e.getMessage());
                }
            }
        };
        server.setDispatcher(dispatcher);
    }

    public void shutdownServer() throws IOException {
        server.shutdown();
    }

}
