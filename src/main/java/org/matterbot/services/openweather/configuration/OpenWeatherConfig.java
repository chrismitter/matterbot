package org.matterbot.services.openweather.configuration;

import lombok.Data;
import org.matterbot.services.openweather.OpenWeatherClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Configuration
public class OpenWeatherConfig {

    @NotNull
    @NotEmpty
    @Value("${openweather.client.apiurl}")
    private String apiurl;

    @Bean
    public OpenWeatherClient getOpenWeatherCallerService(Retrofit.Builder retroBuilder) {
        return retroBuilder
                .baseUrl(apiurl)
                .build()
                .create(OpenWeatherClient.class);
    }
}
