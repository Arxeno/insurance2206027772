package apap.ti.insurance2206027772.controllers;

import apap.ti.insurance2206027772.dtos.request.AddCompanyRequestDTO;
import apap.ti.insurance2206027772.dtos.request.UpdateCompanyRequestDTO;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.models.Coverage;
import apap.ti.insurance2206027772.services.interfaces.CompanyService;
import apap.ti.insurance2206027772.services.interfaces.CoverageService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @Autowired
  private CoverageService coverageService;

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
  public String getCreateCompanyForm(Model model) {
    var dto = new AddCompanyRequestDTO();

    List<Coverage> coverages = coverageService.getAllCoverages();
    String totalCoverage = "IDR 0.00";

    model.addAttribute("dto", dto);
    model.addAttribute("totalCoverage", totalCoverage);
    model.addAttribute("coverages", coverages);

    return "create-company-form";
  }

  @PostMapping(value = "/add", params = { "addCoverage" })
  public String addCoverageRow(
    @Valid @ModelAttribute AddCompanyRequestDTO dto,
    Model model
  ) {
    if (dto.getListCoverage() == null || dto.getListCoverage().isEmpty()) {
      dto.setListCoverage(new ArrayList<>());
    }

    if (coverageService.getAllCoverages().size() > 0) {
      dto.getListCoverage().add(new Coverage());
    }

    List<Coverage> coverages = coverageService.getAllCoverages();
    String totalCoverage = dto.getTotalCoverageString(coverages);

    model.addAttribute("dto", dto);
    model.addAttribute("totalCoverage", totalCoverage);
    model.addAttribute("coverages", coverages);

    return "create-company-form";
  }

  @PostMapping(value = "/add", params = { "removeCoverage" })
  public String removeCoverageRow(
    @Valid @ModelAttribute AddCompanyRequestDTO dto,
    @RequestParam("removeCoverage") int row,
    Model model
  ) {
    dto.getListCoverage().remove(row);

    List<Coverage> coverages = coverageService.getAllCoverages();
    String totalCoverage = dto.getTotalCoverageString(coverages);

    model.addAttribute("dto", dto);
    model.addAttribute("totalCoverage", totalCoverage);
    model.addAttribute("coverages", coverages);

    return "create-company-form";
  }

  @PostMapping(value = "/add", params = { "refreshTotalCoverage" })
  public String refreshTotalCoverage(
    @Valid @ModelAttribute AddCompanyRequestDTO dto,
    Model model
  ) {
    List<Coverage> coverages = coverageService.getAllCoverages();
    String totalCoverage = dto.getTotalCoverageString(coverages);

    model.addAttribute("dto", dto);
    model.addAttribute("totalCoverage", totalCoverage);
    model.addAttribute("coverages", coverages);

    return "create-company-form";
  }

  @PostMapping("/add")
  public String postCreateCompany(
    @Valid @ModelAttribute AddCompanyRequestDTO dto,
    BindingResult bindingResult,
    Model model
  ) {
    if (bindingResult.hasErrors()) {
      List<String> errors = bindingResult
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + " " + error.getDefaultMessage())
        .toList();

      List<Coverage> coverages = coverageService.getAllCoverages();
      String totalCoverage = dto.getTotalCoverageString(coverages);

      model.addAttribute("errors", errors);
      model.addAttribute("dto", dto);
      model.addAttribute("totalCoverage", totalCoverage);
      model.addAttribute("coverages", coverages);

      return "create-company-form";
    }

    Company newCompany = new Company();
    newCompany.setName(dto.getName());
    newCompany.setContact(dto.getContact());
    newCompany.setEmail(dto.getEmail());
    newCompany.setAddress(dto.getAddress());
    newCompany.setListCoverage(dto.getListCoverage());

    companyService.createCompany(newCompany);

    model.addAttribute("message", "Berhasil membuat company!");

    return "response";
  }

  @GetMapping("/{id}/update")
  public String getUpdateCompanyForm(@PathVariable("id") UUID id, Model model)
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

    List<Coverage> coverages = coverageService.getAllCoverages();
    UpdateCompanyRequestDTO dto = new UpdateCompanyRequestDTO();
    dto.setId(company.getId());
    dto.setName(company.getName());
    dto.setContact(company.getContact());
    dto.setEmail(company.getEmail());
    dto.setAddress(company.getAddress());
    dto.setListCoverage(company.getListCoverage());
    String totalCoverage = dto.getTotalCoverageString(coverages);

    model.addAttribute("dto", dto);
    model.addAttribute("totalCoverage", totalCoverage);
    model.addAttribute("coverages", coverages);

    return "update-company-form";
  }

  @PostMapping(value = "/update", params = { "addCoverage" })
  public String addCoverageRow(
    @Valid @ModelAttribute UpdateCompanyRequestDTO dto,
    Model model
  ) {
    if (dto.getListCoverage() == null || dto.getListCoverage().isEmpty()) {
      dto.setListCoverage(new ArrayList<>());
    }

    if (coverageService.getAllCoverages().size() > 0) {
      dto.getListCoverage().add(new Coverage());
    }

    List<Coverage> coverages = coverageService.getAllCoverages();
    String totalCoverage = dto.getTotalCoverageString(coverages);

    model.addAttribute("dto", dto);
    model.addAttribute("totalCoverage", totalCoverage);
    model.addAttribute("coverages", coverages);

    return "update-company-form";
  }

  @PostMapping(value = "/update", params = { "removeCoverage" })
  public String removeCoverageRow(
    @Valid @ModelAttribute UpdateCompanyRequestDTO dto,
    @RequestParam("removeCoverage") int row,
    Model model
  ) {
    dto.getListCoverage().remove(row);

    List<Coverage> coverages = coverageService.getAllCoverages();
    String totalCoverage = dto.getTotalCoverageString(coverages);

    model.addAttribute("dto", dto);
    model.addAttribute("totalCoverage", totalCoverage);
    model.addAttribute("coverages", coverages);

    return "update-company-form";
  }

  @PostMapping(value = "/update", params = { "refreshTotalCoverage" })
  public String refreshTotalCoverage(
    @Valid @ModelAttribute UpdateCompanyRequestDTO dto,
    Model model
  ) {
    List<Coverage> coverages = coverageService.getAllCoverages();
    String totalCoverage = dto.getTotalCoverageString(coverages);

    model.addAttribute("dto", dto);
    model.addAttribute("totalCoverage", totalCoverage);
    model.addAttribute("coverages", coverages);

    return "update-company-form";
  }

  @PostMapping("/update")
  public String postUpdateCompany(
    @Valid @ModelAttribute UpdateCompanyRequestDTO dto,
    BindingResult bindingResult,
    Model model
  ) throws NotFound {
    if (bindingResult.hasErrors()) {
      List<String> errors = bindingResult
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + " " + error.getDefaultMessage())
        .toList();

      List<Coverage> coverages = coverageService.getAllCoverages();
      String totalCoverage = dto.getTotalCoverageString(coverages);

      model.addAttribute("errors", errors);
      model.addAttribute("dto", dto);
      model.addAttribute("totalCoverage", totalCoverage);
      model.addAttribute("coverages", coverages);

      return "create-company-form";
    }

    companyService.updateCompany(dto);

    model.addAttribute(
      "message",
      String.format("Company dengan ID %s berhasil di-update!", dto.getId())
    );

    return "response";
  }

  @PostMapping("/{id}/delete")
  public String postDeleteCompany(@PathVariable("id") UUID id, Model model)
    throws NotFound {
    companyService.deleteCompanyById(id);

    model.addAttribute(
      "message",
      String.format("Company dengan ID %s berhasil dihapus.", id.toString())
    );

    return "response";
  }
}
