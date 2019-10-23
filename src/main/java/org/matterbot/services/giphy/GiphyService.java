package org.matterbot.services.giphy;

import lombok.extern.slf4j.Slf4j;
import org.matterbot.services.BaseURLQueryService;
import org.matterbot.services.giphy.model.DownsizedImage;
import org.matterbot.services.giphy.model.Gif;
import org.matterbot.services.giphy.model.Images;
import org.matterbot.services.giphy.response.SearchResponse;
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
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GiphyService extends BaseURLQueryService {

    private GiphyClient giphyClient;
    private static final int MAX_SEARCH_RESULT = 100;

    private String apiKey;

    @Autowired
    public GiphyService(GiphyClient giphyClient,
                        @NotNull @NotEmpty @NotBlank @Value("${giphy.client.apikey}") String apiKey) {
        this.giphyClient = giphyClient;
        this.apiKey = apiKey;
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
            case RANDOM:
                call = giphyClient.getRandom(apiKey);
                jsonpath = "$.data.image_original_url";
                break;
            case TRENDING:
                call = giphyClient.getTrending(apiKey);
                jsonpath = "$.data[0].images.original.url";
                break;
            case SEARCH:
                Call<SearchResponse> callSearch = giphyClient.search(apiKey, term, MAX_SEARCH_RESULT);
                String url = "";
                try {
                    Response<SearchResponse> response = callSearch.execute();
                    if (response.isSuccessful()) {
                        SearchResponse body = response.body();
                        int random = getRandomNumberInRange(0, body.getData().size());
                        url = body.getData().get(random).getImages().getOriginal().getUrl();
                    } else {
                        log.error("STATUS: {}, BODY: {}", response.code(), response.errorBody().string());
                    }
                } catch (IOException ex) {
                    log.error(ex.toString());
                }
                return url;
            default:
                return "WHAT DO YOU WANT, DUDE?";
        }
        return queryCall(call, jsonpath);
    }

    public List<String> getUrlList(String term) {
        Call<SearchResponse> callSearch2 = giphyClient.search(apiKey, term, MAX_SEARCH_RESULT);
        try {
            Response<SearchResponse> response = callSearch2.execute();
            if (response.isSuccessful()) {
                SearchResponse body = response.body();
                assert body != null;
                return body.getData().stream().map(Gif::getImages).map(Images::getDownsized).map(DownsizedImage::getUrl).collect(Collectors.toList()).subList(0, 5);
            }
        } catch (IOException ex) {
            log.error(ex.toString());
        }
        return List.of();
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, max).findFirst().getAsInt();
    }
}
