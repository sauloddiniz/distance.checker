package com.distancechecker;

import com.distancechecker.dto.ResultDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ApplicationTests {

    @Test
    void testeConverterJsonAddressToJavaObject() {

        ResultDto resultDto = TestUtils.json("address.json");

        Assertions.assertNotNull(resultDto);
    }
    @Test
    void testeConverterJsonAddressesToListJavaObject() {

        int expectedSizeListReturnJsonList = 3;

        List<ResultDto> resultDto = TestUtils.listJson("listAddress.json");

        Assertions.assertEquals(resultDto.size(),expectedSizeListReturnJsonList);
    }

}
