package org.matterbot.services.urbandictionary;

import lombok.extern.slf4j.Slf4j;
import org.matterbot.services.BaseURLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.util.List;

@Slf4j
@Service
public class UrbanDictionaryService extends BaseURLQueryService {

    private UrbanDictionaryClient urbanDictionaryClient;

    @Autowired
    public UrbanDictionaryService(UrbanDictionaryClient urbanDictionaryClient) {
        this.urbanDictionaryClient = urbanDictionaryClient;
    }

    @Override
    public String getUrl(Strategy strategy) {
        return getUrl(strategy, "");
    }

    @Override
    public String getUrl(Strategy strategy, String term) {
        Call<String> call = null;
        String jsonpath = "";

        switch (strategy) {
            case SEARCH:
                call = urbanDictionaryClient.getTranslation(term);
                jsonpath = "$.list[0].definition";
                break;
            default:
                return "WHAT DO YOU WANT, DUDE?";
        }
        return queryCall(call, jsonpath);
    }

    @Override
    public List<String> getUrlList(String term) {
        return null;
    }

}
