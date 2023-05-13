package com.distancechecker.exceptions;

import com.distancechecker.dto.error.ErrorFieldDto;
import com.distancechecker.dto.error.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class DefaultResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientApiFeingException.class)
    public ResponseEntity<ErrorResponseDto> clientApiFeingException(ClientApiFeingException exception, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto
                .builder()
                    .message(exception.getMessage())
                    .status(HttpStatus.BAD_REQUEST.value())
                    .path(request.getServletPath())
                    .method(request.getMethod())
                .build());
    }

    @ExceptionHandler(AddressBlankException.class)
    public ResponseEntity<ErrorResponseDto> addressBlankException(AddressBlankException exception, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto
                        .builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers,
             HttpStatusCode status, WebRequest request) {

        List<ErrorFieldDto> listError = ex.getFieldErrors()
                .stream().map(ErrorFieldDto::converter)
                .collect(Collectors.toList());

        String path = getPath((ServletWebRequest) request);

        return ResponseEntity.badRequest().body(
                ErrorResponseDto
                        .builder()
                            .errors(listError)
                            .status(status.value())
                            .path(path)
                        .build());
    }

    private static String getPath(ServletWebRequest request) {
        return request.getRequest().getRequestURI();
    }
}
