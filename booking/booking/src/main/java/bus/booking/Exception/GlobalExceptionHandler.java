package bus.booking.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import bus.booking.DTO.ApiResponse;

import java.util.stream.Collectors;

@RestControllerAdvice
public	class	GlobalExceptionHandler	{
    @ExceptionHandler(ResourceNotFoundException.class)
    public	ResponseEntity<ApiResponse<?>>	handleNotFound(ResourceNotFoundException	ex)	{
        return	ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false,	ex.getMessage(),	null));
    }
    @ExceptionHandler(SeatUnavailableException.class)
    public	ResponseEntity<ApiResponse<?>>	handleSeatUnavailable(SeatUnavailableException	ex)	{
        return	ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new	ApiResponse<>(false,	ex.getMessage(),	null));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public	ResponseEntity<ApiResponse<?>>	handleValidation(MethodArgumentNotValidException	ex)	{
        var	errors	=	ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,	FieldError::getDefaultMessage));
        return	ResponseEntity.badRequest().body(new	ApiResponse<>(false,	"Validation	failed",	errors));
    }
    @ExceptionHandler(Exception.class)
    public	ResponseEntity<ApiResponse<?>>	handleGeneric(Exception	ex)	{
        return	ResponseEntity.internalServerError()
                .body(new	ApiResponse<>(false,	"Something	went	wrong:	"	+	ex.getMessage(),	null));
    }
}
