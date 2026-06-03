package org.wojo.earnings_app.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = EarningNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEarningNotFoundException(EarningNotFoundException e, HttpServletRequest request) {
        ApiErrorResponse body = new ApiErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getClass().getName(), e.getMessage(), request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
