package org.matterbot.services.openweather;

import lombok.extern.slf4j.Slf4j;
import org.matterbot.services.BaseURLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Service
public class OpenWeatherService extends BaseURLQueryService {

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

}
