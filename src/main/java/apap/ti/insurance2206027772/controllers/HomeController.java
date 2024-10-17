package apap.ti.insurance2206027772.controllers;

import apap.ti.insurance2206027772.services.interfaces.CompanyService;
import apap.ti.insurance2206027772.services.interfaces.PatientService;
import apap.ti.insurance2206027772.services.interfaces.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  @Autowired
  private PolicyService policyService;

  @Autowired
  CompanyService companyService;

  @Autowired
  private PatientService patientService;

  @GetMapping
  public String getHomePage(Model model) {
    long countPolicy = policyService.getTotalPoliciesCount();
    long countCompany = companyService.getTotalCompaniesCount();
    long countPatient = patientService.getTotalPatientsCount();

    model.addAttribute("countPolicy", countPolicy);
    model.addAttribute("countCompany", countCompany);
    model.addAttribute("countPatient", countPatient);

    return "home";
  }
}
