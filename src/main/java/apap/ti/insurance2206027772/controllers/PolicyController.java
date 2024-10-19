package apap.ti.insurance2206027772.controllers;

import apap.ti.insurance2206027772.enums.PolicyStatus;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Patient;
import apap.ti.insurance2206027772.models.Policy;
import apap.ti.insurance2206027772.services.interfaces.PatientService;
import apap.ti.insurance2206027772.services.interfaces.PolicyService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

  @Autowired
  private PolicyService policyService;

  @Autowired
  private PatientService patientService;

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
  public String getCreatePolicyWithPatientForm() {
    return "create-policy-and-patient-form";
  }

  @PostMapping("/create-with-patient")
  public String postCreatePolicyWithPatient() {
    //TODO: process POST request

    return ""; // TODO
  }

  @GetMapping("/{nik}/create")
  public String getCreatePolicyForRegisteredPatientForm(
    @PathVariable("nik") String nik
  ) {
    // TODO

    return "create-policy-form";
  }

  @PostMapping("/{nik}/create")
  public String postCreatePolicyForRegisteredPatient(
    @PathVariable("nik") String nik
  ) {
    //TODO: process POST request

    return "response";
  }

  @GetMapping("/{id}/update")
  public String getUpdatePolicyForm(@PathVariable("id") String id) {
    return "update-policy-form";
  }

  @PostMapping("/update")
  public String postUpdatePolicy() {
    //TODO: process POST request

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
