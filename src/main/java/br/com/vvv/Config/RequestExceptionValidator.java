package br.com.vvv.Config;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RequestExceptionValidator {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> NotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationMessage>> NotValidException(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ValidationMessage::new).toList());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<?> DataIntegrityException(DataIntegrityViolationException ex) {
        String message = ex.getMessage();
        if (ex.getCause() instanceof ConstraintViolationException) {
            message = ((ConstraintViolationException) ex.getCause())
                .getSQLException()
                .getMessage();
        }

        return ResponseEntity.badRequest().body("Error: " + message);
    }

    private record ValidationMessage(String field, String message) {
        public ValidationMessage(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
    
}