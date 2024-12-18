package com.gandec.ganadecs.Excepciones;

import com.gandec.ganadecs.DTO.ErrorDetailers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoCriaFoundException.class)
    public ResponseEntity<String> handleNoCriaFoundException(NoCriaFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BovinoVendidoException.class)
    public ResponseEntity<String> handleBovinoVendidoException(BovinoVendidoException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyDestetadaException.class)
    public ResponseEntity<String> handleAlreadyDestetadaException(AlreadyDestetadaException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDesteteDateException.class)
    public ResponseEntity<String> handleInvalidDesteteDateException(InvalidDesteteDateException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UniqueUsernameException.class)
    public ResponseEntity<String> handleUniqueUsernameException(UniqueUsernameException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(BovinoNotFoundException.class)
    public ResponseEntity<String> handleBovinoNotFoundException(BovinoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidOperationForMaleBovinoException.class)
    public ResponseEntity<String> handleInvalidOperationForMaleBovinoException(InvalidOperationForMaleBovinoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UniqueEmailException.class)
    public ResponseEntity<String> handleUniqueEmailException(UniqueEmailException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(UniqueConstraintException.class)
    public ResponseEntity<String> handleUniqueConstraintException(UniqueConstraintException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nombre de usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.");
    }
    @ExceptionHandler(ResourceNotFoundExcepcion.class)
    public ResponseEntity<ErrorDetailers> mannerResourceNotFoundException(ResourceNotFoundExcepcion exception, WebRequest webRequest){
        ErrorDetailers errorDetailers = new ErrorDetailers(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailers, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<ErrorDetailers> mannerBlogAppException(BlogAppException exception, WebRequest webRequest){
        ErrorDetailers errorDetailers = new ErrorDetailers(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailers,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailers> mannerGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetailers errorDetailers = new ErrorDetailers(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailers,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(equalPaddocks.class)
    @ResponseBody
    public ResponseEntity<String> handleequalPaddocks(equalPaddocks ex) {
        String messageerror = ex.getMessage();
        return new ResponseEntity<>(messageerror, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmptyListExcepcion.class)
    @ResponseBody
    public ResponseEntity<String> handleEmptyListExcepcion(EmptyListExcepcion ex) {
        String messageerror = ex.getMessage();
        return new ResponseEntity<>(messageerror, HttpStatus.BAD_REQUEST);
    }




@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        Map<String, String> errors =new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String NameCampo=((FieldError)error).getField();
            String  message=error.getDefaultMessage();

            errors.put(NameCampo,message);
        });
         return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        //return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }




}
