package apap.ti.insurance2206027772.api.controllers;

import apap.ti.insurance2206027772.api.dtos.request.AddPolicyRequestRestDTO;
import apap.ti.insurance2206027772.api.dtos.response.BaseResponseDTO;
import apap.ti.insurance2206027772.api.dtos.response.PolicyResponseDTO;
import apap.ti.insurance2206027772.api.services.interfaces.PolicyRestService;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.models.Patient;
import apap.ti.insurance2206027772.services.interfaces.CompanyService;
import apap.ti.insurance2206027772.services.interfaces.PatientService;
import jakarta.validation.Valid;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/policy")
public class PolicyRestController {

  @Autowired
  private PolicyRestService policyRestService;

  @Autowired
  private PatientService patientService;

  @Autowired
  private CompanyService companyService;

  @GetMapping("/detail")
  public ResponseEntity<BaseResponseDTO<PolicyResponseDTO>> detailPolicy(
    @RequestParam("id") String id
  ) throws NotFound {
    var baseResponseDTO = new BaseResponseDTO<PolicyResponseDTO>();

    PolicyResponseDTO detailPolicy = policyRestService.getPolicyById(id);

    baseResponseDTO.setStatus(HttpStatus.OK.value());
    baseResponseDTO.setData(detailPolicy);
    baseResponseDTO.setMessage(String.format("Success get detail policy!"));
    baseResponseDTO.setTimestamp(new Date());
    return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<BaseResponseDTO<PolicyResponseDTO>> createPolicyForRegisteredPatient(
    @RequestParam("nik") String nik,
    @RequestBody @Valid AddPolicyRequestRestDTO dto
  ) {
    var baseResponseDTO = new BaseResponseDTO<PolicyResponseDTO>();

    Patient patient = patientService.getPatientByNik(nik);
    if (patient == null) {
      baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
      baseResponseDTO.setMessage(
        String.format("Patient dengan NIK %s tidak ditemukan.", nik)
      );
      baseResponseDTO.setTimestamp(new Date());
      return new ResponseEntity<>(baseResponseDTO, HttpStatus.NOT_FOUND);
    }

    Company company = companyService.getCompanyById(dto.getIdCompany());
    if (company == null) {
      baseResponseDTO.setStatus(HttpStatus.NOT_FOUND.value());
      baseResponseDTO.setMessage("Company tidak ditemukan.");
      baseResponseDTO.setTimestamp(new Date());
      return new ResponseEntity<>(baseResponseDTO, HttpStatus.NOT_FOUND);
    }

    PolicyResponseDTO createdPolicy = policyRestService.createPolicyForPatient(
      patient,
      company,
      dto.getExpiryDate()
    );

    baseResponseDTO.setStatus(HttpStatus.CREATED.value());
    baseResponseDTO.setData(createdPolicy);
    baseResponseDTO.setMessage("Policy berhasil dibuat.");
    baseResponseDTO.setTimestamp(new Date());

    return new ResponseEntity<>(baseResponseDTO, HttpStatus.CREATED);
  }
}
