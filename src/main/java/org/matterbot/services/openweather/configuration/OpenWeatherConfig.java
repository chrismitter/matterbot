package org.matterbot.services.openweather.configuration;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.matterbot.services.openweather.OpenWeatherClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import javax.validation.constraints.NotNull;

@Data
@Configuration
@ConfigurationProperties(prefix = "openweather.client")
public class OpenWeatherConfig {

    @NotNull
    @NotEmpty
    private String apiurl;

    @Bean
    public OpenWeatherClient getOpenWeatherCallerService(Retrofit.Builder retroBuilder){
        return retroBuilder
                .baseUrl(apiurl)
                .build()
                .create(OpenWeatherClient.class);
    }
}
