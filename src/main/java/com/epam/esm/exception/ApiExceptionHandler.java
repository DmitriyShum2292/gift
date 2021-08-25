package com.epam.esm.exception;

import com.epam.esm.dto.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.sql.SQLException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        String errorCode = e.getId()+String.valueOf(badRequest.value());
        ResponseExceptionDTO responseExceptionDTO = new ResponseExceptionDTO(e.getMessage(),
                Integer.valueOf(errorCode),badRequest);
        return new ResponseEntity<>(responseExceptionDTO, badRequest);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        String errorCode = String.valueOf(e.getId())+notFound.value();
        ResponseExceptionDTO responseExceptionDTO = new ResponseExceptionDTO(e.getMessage(),
                Integer.valueOf(errorCode),notFound);
        return new ResponseEntity<>(responseExceptionDTO, notFound);
    }

    @ExceptionHandler(value = {SQLException.class})
    public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException e){
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        String errorCode = String.valueOf(e.getId())+internalServerError.value();
        ResponseExceptionDTO responseExceptionDTO = new ResponseExceptionDTO(e.getMessage(),
                Integer.valueOf(errorCode),internalServerError);
        return new ResponseEntity<>(responseExceptionDTO, internalServerError);
    }
}
