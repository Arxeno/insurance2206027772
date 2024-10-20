package apap.ti.insurance2206027772.controllers;

import apap.ti.insurance2206027772.exceptions.NotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice("apap.ti.insurance2206027772.controllers")
public class GlobalExceptionController {

  @ExceptionHandler(NotFound.class)
  public String handleNotFound(
    final NotFound ex,
    Model model,
    HttpServletRequest request
  ) {
    String requestUri = request.getRequestURI();

    if (requestUri.startsWith("/api")) {
      return null;
    }

    model.addAttribute("err", ex.getMessage());

    return "error/404";
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public String handleNotFound(
    final NoResourceFoundException ex,
    Model model,
    HttpServletRequest request
  ) {
    System.out.println("askjdasjkdhaj");

    String requestUri = request.getRequestURI();

    if (requestUri.startsWith("/api")) {
      return null;
    }

    model.addAttribute("err", ex.getMessage());

    return "error/404";
  }

  @ExceptionHandler(BadRequestException.class)
  public String handleBadRequestException(
    final BadRequestException ex,
    Model model,
    HttpServletRequest request
  ) {
    String requestUri = request.getRequestURI();

    if (requestUri.startsWith("/api")) {
      return null;
    }

    model.addAttribute("err", ex.getMessage());

    return "error/400";
  }

  @ExceptionHandler(BadRequest.class)
  public String handleBadRequest(
    final BadRequest ex,
    Model model,
    HttpServletRequest request
  ) {
    String requestUri = request.getRequestURI();

    if (requestUri.startsWith("/api")) {
      return null;
    }

    model.addAttribute("err", ex.getMessage());

    return "error/400";
  }
}
