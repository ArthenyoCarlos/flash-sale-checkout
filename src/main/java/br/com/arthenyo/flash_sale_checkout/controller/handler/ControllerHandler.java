package br.com.arthenyo.flash_sale_checkout.controller.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.arthenyo.flash_sale_checkout.exceptions.CodigoErro;
import br.com.arthenyo.flash_sale_checkout.exceptions.ErroResposta;
import br.com.arthenyo.flash_sale_checkout.exceptions.EstoqueInsuficienteException;
import br.com.arthenyo.flash_sale_checkout.exceptions.ObjetoNaoEncontradoException;
import br.com.arthenyo.flash_sale_checkout.exceptions.RegrasNegocioException;

@RestControllerAdvice
public class ControllerHandler {


    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> handleObjetoNaoEncontrado(ObjetoNaoEncontradoException ex) {
        ErroResposta erroResposta = new ErroResposta
        (
            ex.getMessage(), 
            404, 
            System.currentTimeMillis(), 
            null,
            CodigoErro.PRODUTO_NAO_ENCONTRADO
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta);
    }


    @ExceptionHandler(RegrasNegocioException.class)
    public ResponseEntity<ErroResposta> handleRegrasNegocioException(RegrasNegocioException ex) {
        ErroResposta erroResposta = new ErroResposta
        (
            ex.getMessage(), 
            400, 
            System.currentTimeMillis(), 
            null,
            CodigoErro.REGRAS_NEGOCIO
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResposta);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<ErroResposta> handleEstoqueInsuficienteException(EstoqueInsuficienteException ex) {
        ErroResposta erroResposta = new ErroResposta
        (
            ex.getMessage(), 
            409, 
            System.currentTimeMillis(), 
            null,
            CodigoErro.ESTOQUE_ESGOTADO
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroResposta);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErroResposta> handleObjectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex) {
        ErroResposta erroResposta = new ErroResposta
        (
            ex.getMessage(), 
            409, 
            System.currentTimeMillis(), 
            null,
            CodigoErro.CONFLITO_CONCORRENCIA
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroResposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
