package im.actor.runtime;

import im.actor.core.entity.Sex;
import im.actor.runtime.http.HTTPResponse;
import im.actor.runtime.promise.Promise;
import im.actor.runtime.json.JSONException;
import im.actor.runtime.json.JSONObject;

public class OTGHTTP {
    private static OTGHttpRuntime runtime = new OTGHttpRuntimeProvider();

    public static final String SIGN_UP_API_URL = "http://gate.messengercrm.com:8080/v1/api/signup";

    public static Promise<HTTPResponse> postJsonMethod(String url, String json) {
        return runtime.postJsonMethod(url, json);
    }

    public static Promise<HTTPResponse> signUpRequest(String name, Sex sex, String transactionHash, int age, String address, int homeNumber) {
        JSONObject json = new JSONObject();
        try {
            json.put("name", name);
            json.put("sex", sex.ordinal());
            json.put("transactionHash", transactionHash);
            json.put("age", age);
            json.put("address", address);
            json.put("homeNumber", homeNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJsonMethod(SIGN_UP_API_URL, json.toString());
    }
}
