package org.matterbot.services.giphy.configuration;

import lombok.Data;
import org.matterbot.services.giphy.GiphyClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Configuration
@ConfigurationProperties(prefix = "giphy.client")
public class GiphyConfig {

    @NotNull
    @NotEmpty
    private String apiurl;

    @Bean
    public GiphyClient getGiphyClient(Retrofit.Builder retroBuilder) {
        return retroBuilder
                .baseUrl(apiurl)
                .build()
                .create(GiphyClient.class);
    }
}
