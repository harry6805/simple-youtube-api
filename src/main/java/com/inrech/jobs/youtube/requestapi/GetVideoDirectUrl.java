package com.inrech.jobs.youtube.requestapi;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class GetVideoDirectUrl extends RequestApi{
    /* youtube api
      POST https://youtubei.googleapis.com/youtubei/v1/player?key=AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8
      Postbody:
        {
            "videoId": "pev6m5WVRjU",
            "context": {
                "client": {
                    "hl": "en",
                    "gl": "US",
                    "clientName": "ANDROID_TESTSUITE",
                    "clientVersion": "1.9",
                    "androidSdkVersion": 31
                }
            }
        }
    */
    public GetVideoDirectUrl(){
        super("https://youtubei.googleapis.com");
    }

    public Mono<String> post(String videoId){
        String postbody = "{\"videoId\":\"" + videoId +"\"," +
                "\"context\":{\"client\":{\"hl\":\"en\",\"gl\":\"US\",\"clientName\":\"ANDROID_TESTSUITE\"," +
                "\"clientVersion\":\"1.9\",\"androidSdkVersion\":31}}}";
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("key", "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8");
        String uri = UriComponentsBuilder.fromUriString("/youtubei/v1/player").queryParams(params).toUriString();
        return client.post()
                .uri(uri)
                .body(BodyInserters.fromValue(postbody))
                .acceptCharset(StandardCharsets.UTF_8)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}
