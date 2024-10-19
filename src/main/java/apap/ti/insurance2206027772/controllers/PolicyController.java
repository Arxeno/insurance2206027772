package apap.ti.insurance2206027772.controllers;

import apap.ti.insurance2206027772.dtos.request.AddPolicyAndPatientRequestDTO;
import apap.ti.insurance2206027772.dtos.request.AddPolicyRequestDTO;
import apap.ti.insurance2206027772.dtos.request.UpdatePolicyRequestDTO;
import apap.ti.insurance2206027772.enums.PolicyStatus;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.models.Coverage;
import apap.ti.insurance2206027772.models.Patient;
import apap.ti.insurance2206027772.models.Policy;
import apap.ti.insurance2206027772.services.interfaces.CompanyService;
import apap.ti.insurance2206027772.services.interfaces.CoverageService;
import apap.ti.insurance2206027772.services.interfaces.PatientService;
import apap.ti.insurance2206027772.services.interfaces.PolicyService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/policy")
public class PolicyController {

  private final PolicyService policyService;

  private final PatientService patientService;

  private final CompanyService companyService;

  private final CoverageService coverageService;

  public PolicyController(
    PolicyService policyService,
    PatientService patientService,
    CompanyService companyService,
    CoverageService coverageService
  ) {
    this.policyService = policyService;
    this.patientService = patientService;
    this.companyService = companyService;
    this.coverageService = coverageService;
  }

  @GetMapping("/all")
  public String getListPoliciesPage(
    @RequestParam(name = "status", required = false) PolicyStatus status,
    @RequestParam(name = "minCoverage", required = false) Long minCoverage,
    @RequestParam(name = "maxCoverage", required = false) Long maxCoverage,
    Model model
  ) {
    List<Policy> policies = policyService.getAllPolicies(
      status,
      minCoverage,
      maxCoverage
    );

    model.addAttribute("policies", policies);

    return "policy-list";
  }

  @GetMapping("/{id}")
  public String getDetailPolicyPage(@PathVariable("id") String id, Model model)
    throws NotFound {
    Policy policy = policyService.getPolicyById(id);

    if (policy == null) {
      throw new NotFound(
        String.format(
          "Policy dengan ID %s tidak dapat ditemukan.",
          id.toString()
        )
      );
    }

    model.addAttribute("policy", policy);

    return "policy-details";
  }

  @GetMapping("/create/search-patient")
  public String getSearchPatientForm() {
    return "search-patient";
  }

  @PostMapping("/create/search-patient")
  public String postSearchPatient(
    @ModelAttribute(name = "nik") String nik,
    Model model
  ) {
    Patient patient = patientService.getPatientByNik(nik);

    if (patient == null) {
      model.addAttribute("nik", nik);
      return "patient-not-found";
    }

    model.addAttribute("patient", patient);

    return "patient-found";
  }

  @GetMapping("/create-with-patient")
  public String getCreatePolicyWithPatientForm(Model model) {
    AddPolicyAndPatientRequestDTO dto = new AddPolicyAndPatientRequestDTO();

    List<Company> companies = companyService.getAllCompanies();

    model.addAttribute("dto", dto);
    model.addAttribute("companies", companies);

    return "create-policy-and-patient-form";
  }

  @PostMapping(value = "/create-with-patient", params = { "loadCoverage" })
  public String loadCoverage(
    @ModelAttribute AddPolicyAndPatientRequestDTO dto,
    Model model
  ) {
    List<Company> companies = companyService.getAllCompanies();
    Company company = companyService.getCompanyById(dto.getIdCompany());

    model.addAttribute("dto", dto);
    model.addAttribute("companies", companies);
    model.addAttribute("company", company);

    return "create-policy-and-patient-form";
  }

  @PostMapping("/create-with-patient")
  public String postCreatePolicyWithPatient(
    @Valid @ModelAttribute AddPolicyAndPatientRequestDTO dto,
    BindingResult bindingResult,
    Model model
  ) throws BadRequestException, NotFound {
    if (bindingResult.hasErrors()) {
      List<String> errors = bindingResult
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + " " + error.getDefaultMessage())
        .toList();

      List<Company> companies = companyService.getAllCompanies();
      Company company = companyService.getCompanyById(dto.getIdCompany());

      model.addAttribute("errors", errors);
      model.addAttribute("dto", dto);
      model.addAttribute("companies", companies);
      model.addAttribute("company", company);

      return "create-policy-and-patient-form";
    }

    Patient patient = patientService.createPatient(dto);
    dto.setIdPatient(patient.getId());
    policyService.createPolicy(dto);

    model.addAttribute("message", "Berhasil membuat policy dan patient!");

    return "response";
  }

  @GetMapping("/{nik}/create")
  public String getCreatePolicyForRegisteredPatientForm(
    @PathVariable("nik") String nik,
    Model model
  ) throws NotFound {
    Patient patient = patientService.getPatientByNik(nik);

    if (patient == null) {
      throw new NotFound(
        String.format("Patient dengan NIK %s tidak dapat ditemukan", nik)
      );
    }

    AddPolicyRequestDTO dto = new AddPolicyRequestDTO();
    dto.setIdPatient(patient.getId());

    model.addAttribute("dto", dto);
    model.addAttribute("patient", patient);
    model.addAttribute("companies", companyService.getAllCompanies());

    return "create-policy-form";
  }

  @PostMapping(value = "/{nik}/create", params = { "loadCoverage" })
  public String loadCoverage(
    @PathVariable("nik") String nik,
    @Valid @ModelAttribute AddPolicyRequestDTO dto,
    BindingResult bindingResult,
    Model model
  ) throws BadRequestException, NotFound {
    Patient patient = patientService.getPatientByNik(nik);
    if (patient == null) {
      return "patient-not-found";
    }

    Company company = companyService.getCompanyById(dto.getIdCompany());
    List<Coverage> coverages = company.getListCoverage();

    model.addAttribute("dto", dto);
    model.addAttribute("patient", patient);
    model.addAttribute("companies", companyService.getAllCompanies());
    model.addAttribute("company", company);
    model.addAttribute("coverages", coverages);

    return "create-policy-form";
  }

  @PostMapping("/{nik}/create")
  public String postCreatePolicyForRegisteredPatient(
    @PathVariable("nik") String nik,
    @Valid @ModelAttribute AddPolicyRequestDTO dto,
    BindingResult bindingResult,
    Model model
  ) throws BadRequestException, NotFound {
    if (bindingResult.hasErrors()) {
      List<String> errors = bindingResult
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + " " + error.getDefaultMessage())
        .toList();

      Patient patient = patientService.getPatientByNik(nik);
      if (patient == null) {
        return "patient-not-found";
      }

      model.addAttribute("errors", errors);
      model.addAttribute("dto", dto);
      model.addAttribute("patient", patient);
      model.addAttribute("companies", companyService.getAllCompanies());

      return "create-policy-form";
    }

    policyService.createPolicy(dto);

    model.addAttribute(
      "message",
      String.format("Berhasil membuat Policy untuk Patient NIK %s.", nik)
    );

    return "response";
  }

  @GetMapping("/{id}/update")
  public String getUpdatePolicyForm(@PathVariable("id") String id, Model model)
    throws NotFound {
    Policy policy = policyService.getPolicyById(id);

    if (policy == null) {
      throw new NotFound("Tidak dapat menemukan policy dengan ID " + id);
    }

    UpdatePolicyRequestDTO dto = new UpdatePolicyRequestDTO();
    dto.setId(policy.getId());
    dto.setIdPatient(policy.getPatient().getId());
    dto.setIdCompany(policy.getCompany().getId());
    dto.setExpiryDate(policy.getExpiryDate());

    model.addAttribute("dto", dto);
    model.addAttribute("policy", policy);

    return "update-policy-form";
  }

  @PostMapping("/update")
  public String postUpdatePolicy(
    @ModelAttribute UpdatePolicyRequestDTO dto,
    Model model
  ) throws NotFound {
    policyService.updatePolicy(dto);

    model.addAttribute(
      "message",
      "Berhasil update policy dengan ID " + dto.getId()
    );

    return "response";
  }

  @GetMapping("/{id}/delete")
  public String cancelPolicy(@PathVariable("id") String id, Model model)
    throws NotFound {
    policyService.deletePolicyById(id);
    Policy policy = policyService.getPolicyById(id);

    model.addAttribute("message", "Berhasil membatalkan policy!");
    model.addAttribute("policy", policy);

    return "policy-details";
  }
}
