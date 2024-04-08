package com.inrech.jobs.youtube.requestapi;

import com.inrech.jobs.youtube.error.YtBadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

public class RequestApi {
    protected WebClient client;

    public RequestApi(String apiBaseUrl){
        ExchangeFilterFunction errorResponseFilter = ExchangeFilterFunction
                .ofResponseProcessor(RequestApi::exchangeFilterResponseProcessor);

        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(tcpClient -> tcpClient
                        .proxy(proxy -> proxy
                                .type(ProxyProvider.Proxy.SOCKS5)
                                .host("127.0.0.1")
                                .port(9000))).noSSL();
        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
        this.client = WebClient.builder().clientConnector(connector)
                .baseUrl(apiBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filter(errorResponseFilter)
                .build();
    }

    private static Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        HttpStatusCode status = response.statusCode();
        if (status.value() >= 400) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new YtBadRequestException(body)));
        }
        return Mono.just(response);
    }
}
