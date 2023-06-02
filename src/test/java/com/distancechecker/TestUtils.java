package com.distancechecker;

import com.distancechecker.dto.ResultDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.List;

public class TestUtils {
    private static final String path = "src/test/java/resources/json-files/";
    static ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public static ResultDto json(String fileName) {
        return mapper.readValue(new File(path.concat(fileName)), ResultDto.class);
    }

    @SneakyThrows
    public static List<ResultDto> listJson(String fileName) {
        return mapper.readValue(new File(path.concat(fileName)), new TypeReference<List<ResultDto>>() {
        });
    }
}

