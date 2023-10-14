package com.example.springbatch.domain.webclient.service;

import com.example.springbatch.domain.kobis.constants.KobisConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParseException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

@Service
public class WebClientService {
    private WebClient webClient = WebClient.builder()
            .baseUrl(KobisConstants.BASE_URL)
            .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                    .maxInMemorySize(-1))  // 응답 데이터 크기 제한 해제
            .build();

    public <T> T get(String path, MultiValueMap<String, String> params, Class<T> responseDto) {
        String jsonData = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParams(params)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(RuntimeException::new)
                .block();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  // 클래스에서 정의하지 않property가 있을 때 예외를 던지지 않도록 설정
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);  // 따옴표가 없는 문자열도 허용
        try {
            return objectMapper.readValue(jsonData, responseDto);
        } catch (JsonProcessingException e) {
            throw new JsonParseException(e);
        }
    }
}
