package com.inrech.jobs.youtube.requestapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class GetList extends RequestApi{
    // GET https://youtube.googleapis.com/youtube/v3/videos?part=snippet%2Cstatistics&chart=mostPopular&regionCode=US&key=AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8
    // --header 'Accept: application/json'
    private Logger logger;

    public GetList(){
        super("https://youtube.googleapis.com");
        this.logger = LoggerFactory.getLogger(GetList.class);

    }

    public Mono<String> getVideoList(String pageToken, String maxResults){
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("key", "AIzaSyC8HmTUHbODeCiSuOjqP79c28A7Ga6_b5E");
        params.add("part", "snippet,statistics");
        params.add("chart", "mostPopular");
        params.add("regionCode", "US");
        if(null != maxResults && !maxResults.isEmpty()){
            params.add("maxResults", maxResults);
        }
        if(pageToken != null && !pageToken.isEmpty()) {
            params.add("pageToken", pageToken);
        }
        String uri = UriComponentsBuilder.fromUriString("/youtube/v3/videos").queryParams(params).toUriString();
        return get(uri);
    }

    public Mono<String> searchVideoListOfChannel(String channelId, String pageToken, String maxResults){
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("key", "AIzaSyC8HmTUHbODeCiSuOjqP79c28A7Ga6_b5E");
        params.add("part", "snippet");
        params.add("channelId", channelId);
        params.add("type", "video");
        if(null != maxResults && !maxResults.isEmpty()){
            params.add("maxResults", maxResults);
        }
        if(pageToken != null && !pageToken.isEmpty()) {
            params.add("pageToken", pageToken);
        }
        String uri = UriComponentsBuilder.fromUriString("/youtube/v3/search").queryParams(params).toUriString();
        return get(uri);
    }

    public Mono<String> getVideoListOfPlaylist(String playlistId, String pageToken, String maxResults){
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("key", "AIzaSyC8HmTUHbODeCiSuOjqP79c28A7Ga6_b5E");
        params.add("part", "snippet");
        params.add("playlistId", playlistId);
        if(null != maxResults && !maxResults.isEmpty()){
            params.add("maxResults", maxResults);
        }
        if(pageToken != null && !pageToken.isEmpty()) {
            params.add("pageToken", pageToken);
        }
        String uri = UriComponentsBuilder.fromUriString("/youtube/v3/playlistItems").queryParams(params).toUriString();
        return get(uri);
    }

    public Mono<String> getPlaylistOfChannel(String channelId, String pageToken, String maxResults){
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("key", "AIzaSyC8HmTUHbODeCiSuOjqP79c28A7Ga6_b5E");
        params.add("part", "snippet");
        params.add("channelId", channelId);
        if(null != maxResults && !maxResults.isEmpty()){
            params.add("maxResults", maxResults);
        }
        if(pageToken != null && !pageToken.isEmpty()) {
            params.add("pageToken", pageToken);
        }
        String uri = UriComponentsBuilder.fromUriString("/youtube/v3/playlists").queryParams(params).toUriString();
        return get(uri);
    }

    public Mono<String> getChannel(String channelId){
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("key", "AIzaSyC8HmTUHbODeCiSuOjqP79c28A7Ga6_b5E");
        params.add("part", "snippet,statistics");
        params.add("id", channelId);
        String uri = UriComponentsBuilder.fromUriString("/youtube/v3/channels").queryParams(params).toUriString();
        return get(uri);
    }

    public Mono<String> searchVideoList(String channelId, String queryStr, String regionCode, String type, String order, String pageToken, String maxResults){
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("key", "AIzaSyC8HmTUHbODeCiSuOjqP79c28A7Ga6_b5E");
        params.add("part", "snippet");
        if(null != channelId && !channelId.isEmpty()){
            params.add("channelId", channelId);
        }
        if(null != queryStr && !queryStr.isEmpty()) {
            params.add("q", queryStr);
        }
        if(null != regionCode && !regionCode.isEmpty()) {
            params.add("regionCode", regionCode);
        }
        List<String> types = Arrays.asList("channel", "playlist", "video");
        if(types.contains(type)) {
            params.add("type", type);
        }
        List<String> orders = Arrays.asList("date", "rating", "relevance", "title", "videoCount", "viewCount");
        if(orders.contains(order)) {
            params.add("order", order);
        }
        if(null != maxResults && !maxResults.isEmpty()){
            params.add("maxResults", maxResults);
        }
        if(pageToken != null && !pageToken.isEmpty()) {
            params.add("pageToken", pageToken);
        }
        String uri = UriComponentsBuilder.fromUriString("/youtube/v3/search").queryParams(params).toUriString();
        return get(uri);
    }

    private Mono<String> get(String uri){
        logger.info("Retrieve uri: "+ uri);
        return client.get()
                .uri(uri)
                .acceptCharset(StandardCharsets.UTF_8)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}
