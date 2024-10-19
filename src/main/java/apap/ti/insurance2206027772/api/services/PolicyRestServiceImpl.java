package apap.ti.insurance2206027772.api.services;

import apap.ti.insurance2206027772.api.dtos.response.CompanyResponseDTO;
import apap.ti.insurance2206027772.api.dtos.response.PatientResponseDTO;
import apap.ti.insurance2206027772.api.dtos.response.PolicyResponseDTO;
import apap.ti.insurance2206027772.api.services.interfaces.PolicyRestService;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.models.Patient;
import apap.ti.insurance2206027772.models.Policy;
import apap.ti.insurance2206027772.repositories.PolicyDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyRestServiceImpl implements PolicyRestService {

  @Autowired
  private PolicyDb policyDb;

  @Override
  public PolicyResponseDTO getPolicyById(String id) {
    Policy policy = policyDb.findById(id).orElse(null);

    if (policy != null) {
      policy.refreshStatus();
      policyDb.save(policy);
    }

    return policyToPolicyResponseDTO(policy);
  }

  private PolicyResponseDTO policyToPolicyResponseDTO(Policy policy) {
    PolicyResponseDTO policyResponseDTO = new PolicyResponseDTO();
    policyResponseDTO.setId(policy.getId());

    CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();
    Company company = policy.getCompany();
    companyResponseDTO.setName(company.getName());
    companyResponseDTO.setContact(company.getContact());
    companyResponseDTO.setEmail(company.getEmail());
    companyResponseDTO.setAddress(company.getAddress());
    policyResponseDTO.setCompany(companyResponseDTO);

    PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
    Patient patient = policy.getPatient();
    patientResponseDTO.setNik(patient.getNik());
    patientResponseDTO.setName(patient.getName());
    patientResponseDTO.setGender(patient.getGender());
    patientResponseDTO.setBirthDate(patient.getBirthDate());
    patientResponseDTO.setEmail(patient.getEmail());
    patientResponseDTO.setPClass(patient.getPClass());
    policyResponseDTO.setPatient(patientResponseDTO);

    policyResponseDTO.setStatus(policy.getStatus());

    policyResponseDTO.setExpiryDate(policy.getExpiryDate());

    policyResponseDTO.setTotalCoverage(policy.getTotalCoverage());

    policyResponseDTO.setTotalCovered(policy.getTotalCovered());

    return policyResponseDTO;
  }
}
