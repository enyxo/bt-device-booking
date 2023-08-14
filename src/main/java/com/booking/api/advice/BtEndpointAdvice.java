package com.booking.api.advice;

import com.booking.api.exceptions.ApiError;
import com.booking.api.exceptions.ApiSubError;
import com.booking.api.exceptions.CommonApplicationException;
import com.booking.api.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class BtEndpointAdvice {

  private static Set<ApiSubError> buildApiErrors(final MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getAllErrors().stream()
      .map(error -> (FieldError) error)
      .map(
        fieldError -> new ApiSubError(
          fieldError.getObjectName(),
          fieldError.getField(),
          fieldError.getRejectedValue(),
          fieldError.getDefaultMessage(),
          fieldError.getCode()
        )
      )
      .collect(Collectors.toSet());
  }

  @ExceptionHandler(CommonApplicationException.class)
  public ResponseEntity<Object> handleGenericResponseStatusException(final CommonApplicationException ex) {
    final ApiError apiError = ApiError.builder()
      .message(ex.getMessage())
      .code(ex.getStatus().value())
      .error(ex.getStatus().getReasonPhrase())
      .timestamp(LocalDateTime.now())
      .build();

    addExceptionDetails(apiError, ex);

    if (ex.hasErrors()) {
      apiError.setErrors(ex.getErrors());
    }

    log.info(ExceptionUtils.getStackTrace(ex));

    return new ResponseEntity<>(apiError, ex.getStatus());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(final ResourceNotFoundException ex) {
    final ApiError apiError = ApiError.builder()
      .message(ex.getMessage())
      .code(ex.getStatus().value())
      .timestamp(LocalDateTime.now())
      .build();

    log.info(ExceptionUtils.getStackTrace(ex));

    return new ResponseEntity<>(apiError, ex.getStatus());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Object> handleAllUnhandledExceptions(RuntimeException ex) {
    ApiError apiError = ApiError.builder()
      .message(ex.getMessage())
      .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
      .timestamp(LocalDateTime.now())
      .build();
    addExceptionDetails(apiError, ex);

    log.error(ExceptionUtils.getStackTrace(ex));

    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ApiError> methodArgumentNotValid(final MissingServletRequestParameterException exception) {
    final ApiError apiError = ApiError.builder()
      .message("Required some parameter(s): " + exception.getMessage())
      .code(HttpStatus.BAD_REQUEST.value())
      .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
      .timestamp(LocalDateTime.now())
      .build();

    log.trace(exception.getMessage());

    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidationExceptions(final MethodArgumentNotValidException ex) {
    Set<ApiSubError> apiSubErrors = buildApiErrors(ex);
    final ApiError apiError = ApiError.builder()
      .message("Required some parameter(s): " + apiSubErrors.stream().map(ApiSubError::getMessage).collect(Collectors.joining(", ")))
      .code(HttpStatus.BAD_REQUEST.value())
      .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
      .errors(apiSubErrors)
      .timestamp(LocalDateTime.now())
      .build();

    log.trace(ex.getMessage());

    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handConstraintViolationException(ConstraintViolationException ex) {
    ApiError apiError = ApiError.builder()
      .message(ex.getMessage())
      .code(HttpStatus.BAD_REQUEST.value())
      .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
      .timestamp(LocalDateTime.now())
      .build();

    addExceptionDetails(apiError, ex);

    log.error(ExceptionUtils.getStackTrace(ex));

    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  private void addExceptionDetails(final ApiError apiError, final Exception ex) {
    apiError.setException(ex.getClass()
      .getName());
    apiError.setTrace(ExceptionUtils.getStackTrace(ex));
  }
}
