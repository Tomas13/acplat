package im.actor.runtime;

import com.squareup.okhttp.*;
import im.actor.runtime.Log;
import im.actor.runtime.OTGHttpRuntime;
import im.actor.runtime.http.HTTPError;
import im.actor.runtime.http.HTTPResponse;
import im.actor.runtime.promise.Promise;

import java.io.IOException;

public class AndroidOTGHttpProvider implements OTGHttpRuntime {
    private static final String TAG = "AndroidOTGHTTP";

    private final OkHttpClient client = new OkHttpClient();

    private final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    public AndroidOTGHttpProvider() {
    }

    @Override
    public Promise<HTTPResponse> postJsonMethod(String url, String json) {
        return new Promise<>(resolver -> {
            final Request request = new Request.Builder()
                    .url(url)
                    .method("POST", RequestBody.create(JSON_TYPE, json))
                    .build();
            Log.d(TAG, "Post json request: " + request.toString());
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.d(TAG, "Post json error: " + request.toString());
                    e.printStackTrace();
                    // TODO: Better error?
                    resolver.error(new HTTPError(0));
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    Log.d(TAG, "Post json response: " + request.toString() + " -> " + response.toString());
                    if (response.code() >= 200 && response.code() < 300) {
                        resolver.result(new HTTPResponse(response.code(), null));
                    } else {
                        resolver.error(new HTTPError(response.code()));
                    }
                }
            });
        });
    }
}
