package apap.ti.insurance2206027772.api.controllers;

import apap.ti.insurance2206027772.api.dtos.response.BaseResponseDTO;
import apap.ti.insurance2206027772.exceptions.NotFound;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@RequestMapping("/api")
public class GlobalExceptionRestController {

  @ExceptionHandler(NotFound.class)
  public ResponseEntity<BaseResponseDTO<?>> handleNotFound(
    final NotFound ex,
    Model model
  ) {
    var baseResponseDTO = new BaseResponseDTO<>();
    baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
    baseResponseDTO.setMessage(
      ex.getMessage() != null ? ex.getMessage() : "Path URI tidak ditemukan"
    );
    baseResponseDTO.setTimestamp(new Date());

    ex.printStackTrace();

    return new ResponseEntity<BaseResponseDTO<?>>(
      baseResponseDTO,
      HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<BaseResponseDTO<?>> handleNotFound(
    final NoResourceFoundException ex,
    Model model
  ) {
    var baseResponseDTO = new BaseResponseDTO<>();
    baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
    baseResponseDTO.setMessage(
      ex.getMessage() != null ? ex.getMessage() : "Path URI tidak ditemukan"
    );
    baseResponseDTO.setTimestamp(new Date());

    ex.printStackTrace();

    return new ResponseEntity<BaseResponseDTO<?>>(
      baseResponseDTO,
      HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponseDTO<?>> handleNotFound(
    final Exception ex,
    Model model
  ) {
    var baseResponseDTO = new BaseResponseDTO<>();
    baseResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    baseResponseDTO.setMessage("Internal Server Error.");
    baseResponseDTO.setTimestamp(new Date());

    ex.printStackTrace();

    return new ResponseEntity<BaseResponseDTO<?>>(
      baseResponseDTO,
      HttpStatus.INTERNAL_SERVER_ERROR
    );
  }
}
