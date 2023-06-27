package lt.techin.crud.config;

import lombok.extern.slf4j.Slf4j;
import lt.techin.crud.api.dto.error.ErrorDto;
import lt.techin.crud.api.dto.error.ErrorFieldDto;
import lt.techin.crud.config.exception.CustomValidationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {


    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex) {
        log.info("SQLException Occured:: URL=" + request.getRequestURL());
        return "database_error";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException() {
        log.error("IOException handler executed");
        //returning 404 error code
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorDto> handleDataAccessException(HttpServletRequest request, DataAccessException dataAccessException) {
        log.error("DataAccessException: {}. Cause?: {}",
                dataAccessException.getMessage(), dataAccessException.getMostSpecificCause().getMessage());

        var errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorFields = List.of(
                new ErrorFieldDto("sql", dataAccessException.getMostSpecificCause().getMessage(), null)
        );
        var errorDto = new ErrorDto(request.getRequestURL().toString(),
                errorFields,
                dataAccessException.getMessage(),
                errorStatus.value(),
                errorStatus.getReasonPhrase(),
                request.getRequestURL().toString(),
                LocalDateTime.now());
        return ResponseEntity.internalServerError().body(errorDto);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ErrorDto> handleSchedulerValidationException(HttpServletRequest request, CustomValidationException schedulerValidationException) {
        log.error("schedulerValidationException: {}, for field: {}", schedulerValidationException.getMessage(), schedulerValidationException.getField());

        var errorStatus = HttpStatus.BAD_REQUEST;

        var errorFields = List.of(
                new ErrorFieldDto(schedulerValidationException.getField(), schedulerValidationException.getError(), schedulerValidationException.getRejectedValue())
        );

        var errorDto = new ErrorDto(request.getRequestURL().toString(),
                errorFields,
                schedulerValidationException.getMessage(),
                errorStatus.value(),
                errorStatus.getReasonPhrase(),
                request.getRequestURL().toString(),
                LocalDateTime.now());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "We do not support this")
    @ExceptionHandler(HttpMediaTypeException.class)
    public void handleHttpMediaTypeException(HttpMediaTypeException mediaTypeException) {
        log.error("Not supported request resulted in HttpMediaTypeException: {}", mediaTypeException.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Something really bad happened")
    @ExceptionHandler(Exception.class)
    public void handleAllExceptions(Exception exception) {
        log.error("All Exceptions handler: {}", exception.getMessage());
    }


}