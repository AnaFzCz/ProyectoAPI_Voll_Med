package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Anotación que facilita el manejo global de excepciones en aplicaciones RESTful
@RestControllerAdvice
public class TratadorDeErros {

    //Cada vez que exista un error 404 de un dato no encontrado ingresará, NOT FOUND
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    //Error 400 generado cuando ingresa datos inválidos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException ex) {
// devulve una lista de errores que devulve MethodArgumentNotValidException
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErrorRegraDeNegocio(ValidacaoException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    //DTO creado para obtener los datos de errores 400 con los campos que requerimos
    public record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
