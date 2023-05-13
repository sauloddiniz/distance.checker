package com.distancechecker.client;

import com.distancechecker.exceptions.ClientApiFeingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ConnectErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodName, Response response) {
        try {
            byte[] bodyBytes = StreamUtils.copyToByteArray(response.body().asInputStream());
            final String error = new String(bodyBytes, StandardCharsets.UTF_8);
            ErrorResponseGeolocationApi objectError = converterStringToObject(error);
            throw new ClientApiFeingException(objectError.getErrorMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ErrorResponseGeolocationApi converterStringToObject(final String error) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            ErrorResponseGeolocationApi errorResponseGeolocationApi = mapper.readValue(error, ErrorResponseGeolocationApi.class);
            return errorResponseGeolocationApi;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
