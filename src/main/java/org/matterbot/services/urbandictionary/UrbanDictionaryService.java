package org.matterbot.services.urbandictionary;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.matterbot.services.URLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Service
public class UrbanDictionaryService implements URLQueryService {

    private UrbanDictionaryClient urbanDictionaryClient;

    @Autowired
    public UrbanDictionaryService(UrbanDictionaryClient urbanDictionaryClient){
        this.urbanDictionaryClient = urbanDictionaryClient;
    }

    @Override
    public String getUrl(Strategy strategy) { return getUrl(strategy, ""); }

    @Override
    public String getUrl(Strategy strategy, String term) {
        Call<String> call = null;
        String jsonpath = "";

        switch (strategy){
            case SEARCH:
                call = urbanDictionaryClient.getTranslation(term);
                jsonpath = "$.list.0.definition";
                break;
            default:
                return "WHAT DO YOU WANT, DUDE?";
        }
        return queryCall(call, jsonpath);
    }

    private String queryCall(Call<String> call, String jsonPath){
        Response<String> callResult = null;
        try {
            callResult = call.execute();
            if (callResult.isSuccessful()) {
                DocumentContext jsonContext = JsonPath.parse(callResult.body());
                return jsonContext.read(jsonPath);
            } else {
                log.error("STATUS: {}, BODY: {}", callResult.code(), callResult.errorBody().string());
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
        return "ERROR CALLING";
    }
}
