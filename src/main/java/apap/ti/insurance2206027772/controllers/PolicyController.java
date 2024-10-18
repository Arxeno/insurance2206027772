package apap.ti.insurance2206027772.controllers;

import apap.ti.insurance2206027772.enums.PolicyStatus;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/policy")
public class PolicyController {

  @GetMapping("/all")
  public String getListPoliciesPage(
    @RequestParam(name = "status", required = false) PolicyStatus status,
    @RequestParam(name = "minCoverage", required = false) Long minCoverage,
    @RequestParam(name = "maxCoverage", required = false) Long maxCoverage
  ) {
    // TODO

    return "policy-list";
  }

  @GetMapping("/{id}")
  public String getDetailPolicyPage(@PathVariable("id") String id) {
    // TODO

    return "policy-details";
  }

  @GetMapping("/create/search-patient")
  public String getSearchPatientForm() {
    // TODO

    return "search-patient";
  }

  @PostMapping("/create/search-patient")
  public String postSearchPatient() {
    //TODO: process POST request

    return ""; // TODO
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
}
