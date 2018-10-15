package org.matterbot.mattermost.configuration;

import org.matterbot.mattermost.MattermostInHookClient;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import javax.validation.constraints.NotNull;

@Data
@Configuration
@ConfigurationProperties(prefix = "mattermost.client")
public class MattermostConfig {

    @NotNull
    @NotEmpty
    private String apiurl;

    @Bean
    public MattermostInHookClient getMattermostInHookClient(Retrofit.Builder retroBuilder){
        return retroBuilder
                .baseUrl(apiurl)
                .build()
                .create(MattermostInHookClient.class);
    }
}
