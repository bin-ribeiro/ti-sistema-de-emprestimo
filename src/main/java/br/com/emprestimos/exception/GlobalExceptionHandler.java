package br.com.emprestimos.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestControllerAdvice public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class) ResponseEntity<?> api(ApiException e) {
        return ResponseEntity.status(e.status()).body(Map.of("message",e.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> valid(MethodArgumentNotValidException e) {
        String m=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getField()+": "+x.getDefaultMessage()).orElse("Dados inválidos");
        return ResponseEntity.badRequest().body(Map.of("message",m));
    }
}
