package org.matterbot.mattermost;

import org.matterbot.mattermost.payload.MattermostHookData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MattermostInHookClient {
    @POST("/hooks/{hookId}")
    @Headers("Content-Type: application/json")
    Call<String> sendMessage(@Path("hookId") String hookId, @Body MattermostHookData mattermostHookData);
}
