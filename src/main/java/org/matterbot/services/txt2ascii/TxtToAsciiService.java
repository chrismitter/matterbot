package org.matterbot.services.txt2ascii;

import lombok.extern.slf4j.Slf4j;
import org.matterbot.services.BaseURLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class TxtToAsciiService extends BaseURLQueryService {

    private TxtToAsciiClient txtToAsciiClient;

    @Autowired
    public TxtToAsciiService(TxtToAsciiClient txtToAsciiClient) {
        this.txtToAsciiClient = txtToAsciiClient;
    }

    @Override
    public String getUrl(Strategy strategy) {
        return getUrl(strategy, "");
    }

    @Override
    public String getUrl(Strategy strategy, String term) {
        try {
            Call<String> call = null;
            if (strategy == Strategy.SEARCH) {
                call = txtToAsciiClient.getAsciiArt(term);
            } else {
                return "WHAT DO YOU WANT, DUDE?";
            }
            return call.execute().body();
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return "";
    }

    @Override
    public List<String> getUrlList(String term) {
        return null;
    }

}
