package apap.ti.insurance2206027772.controllers;

import apap.ti.insurance2206027772.exceptions.NotFound;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

@ControllerAdvice
public class GlobalExceptionController {

  @ExceptionHandler(NotFound.class)
  public String handleNotFound(final NotFound ex, Model model) {
    model.addAttribute("err", ex.getMessage());

    return "error/404";
  }

  @ExceptionHandler(BadRequest.class)
  public String handleBadRequest(final BadRequest ex, Model model) {
    model.addAttribute("err", ex.getMessage());

    return "error/400";
  }
}
