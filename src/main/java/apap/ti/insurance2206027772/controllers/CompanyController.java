package apap.ti.insurance2206027772.controllers;

import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.services.interfaces.CompanyService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Controller
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @GetMapping("/all")
  public String getListCompaniesPage(Model model) {
    List<Company> companies = companyService.getAllCompanies();

    model.addAttribute("companies", companies);

    return "company-list";
  }

  @GetMapping("/{id}")
  public String getDetailCompanyPage(@PathVariable("id") UUID id, Model model)
    throws NotFound {
    Company company = companyService.getCompanyById(id);

    if (company == null) {
      throw new NotFound(
        String.format(
          "Company dengan ID %s tidak dapat ditemukan.",
          id.toString()
        )
      );
    }

    model.addAttribute("company", company);

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
