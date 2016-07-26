package im.actor.runtime;


import com.google.j2objc.annotations.ObjectiveCName;
import im.actor.runtime.http.HTTPResponse;
import im.actor.runtime.promise.Promise;

/**
 * Created by jean on 7/20/2016.
 */
public interface OTGHttpRuntime {
    /**
     * Post Json. Always need to send only one header: Content-Type: application/json; charset=utf-8.
     *
     * @param url      url for post
     * @param json     json for post
     */
    @ObjectiveCName("postMethodWithUrl:withContents:")
    Promise<HTTPResponse> postJsonMethod(String url, String json);
}
