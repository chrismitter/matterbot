package org.matterbot.services.openweather;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.matterbot.services.URLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class OpenWeatherService implements URLQueryService {

    private OpenWeatherClient openWeatherClient;

    @NotNull
    @NotEmpty
    @NotBlank
    @Value("${openweather.client.apikey}")
    private String apiKey;

    @Autowired
    public OpenWeatherService(OpenWeatherClient openWeatherClient) {
        this.openWeatherClient = openWeatherClient;
    }

    @Override
    public String getUrl(Strategy strategy) {
        return getUrl(strategy, "");
    }

    @Override
    public String getUrl(Strategy strategy, String term) {

        Call<String> call = openWeatherClient.getCurrentWeather(apiKey, term);
        String jsonpath = "$.weather[0].icon";

        return queryCall(call, jsonpath);
    }

    @Override
    public List<String> getUrlList(String term) {
        return null;
    }

    private String queryCall(Call<String> call, String jsonPath) {
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
