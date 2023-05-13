package com.distancechecker.dto.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.io.Serializable;

@Data
@Builder
public class ErrorFieldDto implements Serializable {
    private String objectName;
    private String field;
    private String defaultMessage;
    public static ErrorFieldDto converter(FieldError objectError){
        return ErrorFieldDto.builder()
                .objectName(objectError.getObjectName())
                .field(objectError.getField())
                .defaultMessage(objectError.getDefaultMessage())
                .build();
    }
}
