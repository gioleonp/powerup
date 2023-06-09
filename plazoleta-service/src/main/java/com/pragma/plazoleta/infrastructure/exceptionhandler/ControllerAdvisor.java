package com.pragma.plazoleta.infrastructure.exceptionhandler;

import com.pragma.plazoleta.domain.exception.ClientIsNotOrderOwnerException;
import com.pragma.plazoleta.domain.exception.DishNotCorrespondToRestaurantException;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.exception.EmployeeIsNotOrderChefException;
import com.pragma.plazoleta.domain.exception.NotClientToMakeAnOrderException;
import com.pragma.plazoleta.domain.exception.OrderCodeDoNotMatchException;
import com.pragma.plazoleta.domain.exception.SameStateException;
import com.pragma.plazoleta.domain.exception.UserAlreadyHaveAnOrderPreparingPendingOrReadyException;
import com.pragma.plazoleta.domain.exception.UserIsNotAProprietaryException;
import com.pragma.plazoleta.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoleta.domain.exception.ProprietaryNotMatchException;
import feign.RetryableException;
import org.springframework.dao.DataIntegrityViolationException;import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;import java.util.Collections;
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
                                MESSAGE, ignoredNoDataFoundException.getMessage()));
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
            MethodArgumentNotValidException ignoredMethodValidationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        Collections.singletonMap(
                                MESSAGE,
                                ignoredMethodValidationException
                                        .getFieldError()
                                        .getDefaultMessage()));
    }

    /*
    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<Map<String, String>> feignExceptions(
            RetryableException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getLocalizedMessage()));
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

    @ExceptionHandler(OrderCodeDoNotMatchException.class)
    public ResponseEntity<Map<String, String>> orderCodeNotMatch(
            OrderCodeDoNotMatchException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }

    @ExceptionHandler(ClientIsNotOrderOwnerException.class)
    public ResponseEntity<Map<String, String>> clientNotOwner(
            ClientIsNotOrderOwnerException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }

    @ExceptionHandler(UserIsNotAProprietaryException.class)
    public ResponseEntity<Map<String, String>> userIsNotAProprietary(
            UserIsNotAProprietaryException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }

    @ExceptionHandler(EmployeeIsNotOrderChefException.class)
    public ResponseEntity<Map<String, String>> employeeIsNotOrderChef(
            EmployeeIsNotOrderChefException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> constraintException(
            ConstraintViolationException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> dataIntegrityException(
            DataIntegrityViolationException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ignoredDomainException.getMessage()));
    }
}
