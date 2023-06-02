package com.distancechecker.client.config;

import com.distancechecker.exceptions.ClientApiFeingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ConnectErrorDecoder implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String methodName, Response response) {
        byte[] bodyBytes = StreamUtils.copyToByteArray(response.body().asInputStream());
        final String error = new String(bodyBytes, StandardCharsets.UTF_8);
        ErrorResponseGeolocationApi objectError = converterStringToObject(error);
        throw new ClientApiFeingException(objectError.getErrorMessage());
    }

    @SneakyThrows
    private ErrorResponseGeolocationApi converterStringToObject(final String error) {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(error, ErrorResponseGeolocationApi.class);
    }
}
