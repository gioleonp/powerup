package com.pragma.plazoleta.infrastructure.exceptionhandler;

import com.pragma.plazoleta.domain.exception.DishNotCorrespondToRestaurantException;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.exception.NotClientToMakeAnOrderException;
import com.pragma.plazoleta.domain.exception.SameStateException;
import com.pragma.plazoleta.domain.exception.UserAlreadyHaveAnOrderPreparingPendingOrReadyException;
import com.pragma.plazoleta.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoleta.domain.exception.ProprietaryNotMatchException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        Collections.singletonMap(
                                MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(ProprietaryNotMatchException.class)
    public ResponseEntity<Map<String, String>> handleProprietaryException(
            ProprietaryNotMatchException ignoreProprietaryException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        Collections.singletonMap(
                                MESSAGE, ExceptionResponse.PROPRIETARY_NOT_MATCH.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            DomainException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }

    /*
    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<Map<String, String>> feignExceptions(
            RetryableException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }

     */

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, String>> DomainExceptions(
            DomainException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }

    @ExceptionHandler(SameStateException.class)
    public ResponseEntity<Map<String, String>> sameException(
            SameStateException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }

    @ExceptionHandler(DishNotCorrespondToRestaurantException.class)
    public ResponseEntity<Map<String, String>> DishNotCorrespondToRestaurantException(
            DishNotCorrespondToRestaurantException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }

    @ExceptionHandler(NotClientToMakeAnOrderException.class)
    public ResponseEntity<Map<String, String>> notClientException(
            NotClientToMakeAnOrderException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }

    @ExceptionHandler(UserAlreadyHaveAnOrderPreparingPendingOrReadyException.class)
    public ResponseEntity<Map<String, String>> userOrderState(
            UserAlreadyHaveAnOrderPreparingPendingOrReadyException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }
}
