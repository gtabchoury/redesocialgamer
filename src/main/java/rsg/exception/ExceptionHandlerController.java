package rsg.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import rsg.dto.DefaultDTO;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<DefaultDTO> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return new ResponseEntity<>(new DefaultDTO(false, "File too large"), HttpStatus.BAD_REQUEST);
    }
}
