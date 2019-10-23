package org.matterbot.mattermost;

import lombok.extern.slf4j.Slf4j;
import org.matterbot.mattermost.payload.MattermostHookData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class MattermostService {

    private final String iconUrl;
    private final String name;
    private MattermostInHookClient mattermostInHookClient;

    private String hookId;

    @Autowired
    public MattermostService(MattermostInHookClient mattermostInHookClient,
                             @NotEmpty @NotBlank @Value("${mattermost.client.hook}") String hookId,
                             @NotEmpty @Value("${client.icon}") String iconUrl,
                             @NotEmpty @Value("${client.name}") String name) {
        this.mattermostInHookClient = mattermostInHookClient;
        this.hookId = hookId;
        this.iconUrl = iconUrl;
        this.name = name;
    }

    ResponseEntity<String> sendMessage(String text) throws IOException {
        MattermostHookData request = MattermostHookData.builder()
                .icon_url(iconUrl)
                .username(name)
                .text(URLDecoder.decode(text, StandardCharsets.UTF_8))
                .build();

        log.info("REQUEST: " + request.toString());
        Call<String> call = mattermostInHookClient.sendMessage(hookId, request);

        Response<String> callResult = call.execute();
        if (callResult.isSuccessful()) {
            return ResponseEntity.ok(callResult.body());
        } else {
            log.error("STATUS: {}, BODY: {}", callResult.code(), callResult.errorBody().string());
            throw new IOException("Failed");
        }
    }

    public ResponseEntity<String> sendMessage(MattermostMessage mattermostMessage) throws IOException {
        return sendMessage(mattermostMessage.getMessage());
    }

}
