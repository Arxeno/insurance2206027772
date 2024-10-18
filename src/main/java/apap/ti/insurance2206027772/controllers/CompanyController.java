package apap.ti.insurance2206027772.controllers;

import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/company")
public class CompanyController {

  @GetMapping("/all")
  public String getListCompaniesPage() {
    // TODO

    return "company-list";
  }

  @GetMapping("/{id}")
  public String getDetailCompanyPage(@PathVariable("id") UUID id) {
    // TODO

    return "company-details";
  }

  @GetMapping("/add")
  public String getCreateCompanyForm() {
    // TODO

    return "create-company-form";
  }

  @PostMapping("/add")
  public String postCreateCompany() {
    //TODO: process POST request

    return "response";
  }

  @GetMapping("/{id}/update")
  public String getUpdateCompanyForm(@PathVariable("id") UUID id) {
    // TODO: dto and model

    return "update-company-form";
  }

  @PostMapping("/update")
  public String postUpdateCompany() {
    //TODO: process POST request

    return "response";
  }

  @PostMapping("/{id}/delete")
  public String postDeleteCompany(@PathVariable("id") UUID id) {
    //TODO: process POST request

    return "response";
  }
}
