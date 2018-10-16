package org.matterbot.services.openweather;

import lombok.Builder;
import lombok.Data;
import org.matterbot.mattermost.MattermostMessage;

@Data
@Builder
public class OpenWeatherMessage implements MattermostMessage {

    private String icon;

    private String caption;

    private final String NEWLINE = "\n";
    private final String ICON_BASE_URL = "http://openweathermap.org/img/w/";

    @Override
    public String getMessage() {
        return caption + NEWLINE + ICON_BASE_URL + icon + ".png";
    }
}
