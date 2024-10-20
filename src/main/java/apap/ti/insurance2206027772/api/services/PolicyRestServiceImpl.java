package apap.ti.insurance2206027772.api.services;

import apap.ti.insurance2206027772.api.dtos.response.CompanyResponseDTO;
import apap.ti.insurance2206027772.api.dtos.response.PatientResponseDTO;
import apap.ti.insurance2206027772.api.dtos.response.PolicyResponseDTO;
import apap.ti.insurance2206027772.api.dtos.response.PolicyStatisticResponseDTO;
import apap.ti.insurance2206027772.api.services.interfaces.PolicyRestService;
import apap.ti.insurance2206027772.enums.PolicyPeriod;
import apap.ti.insurance2206027772.enums.PolicyStatus;
import apap.ti.insurance2206027772.enums.Quarter;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.models.Coverage;
import apap.ti.insurance2206027772.models.Patient;
import apap.ti.insurance2206027772.models.Policy;
import apap.ti.insurance2206027772.repositories.PolicyDb;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyRestServiceImpl implements PolicyRestService {

  @Autowired
  private PolicyDb policyDb;

  @Override
  public PolicyResponseDTO getPolicyById(String id) throws NotFound {
    Policy policy = policyDb.findById(id).orElse(null);

    if (policy == null) {
      throw new NotFound(
        String.format("Policy dengan ID %s tidak dapat ditemukan.", id)
      );
    }

    if (policy != null) {
      policy.refreshStatus();
      policyDb.save(policy);
    }

    return policyToPolicyResponseDTO(policy);
  }

  @Override
  public PolicyResponseDTO createPolicyForPatient(
    Patient patient,
    Company company,
    Date expiryDate
  ) {
    Policy policy = new Policy();
    policy.setId(generatePolicyId(policy));
    policy.setPatient(patient);
    policy.setCompany(company);
    policy.setExpiryDate(expiryDate);
    policy.setTotalCoverage(company.getTotalCoverage());
    policy.setTotalCovered(0L);
    policy.setStatus(PolicyStatus.CREATED);

    policyDb.save(policy);

    return policyToPolicyResponseDTO(policy);
  }

  private String mapMonth(Month month) {
    switch (month) {
      case JANUARY:
        return "Jan";
      case FEBRUARY:
        return "Feb";
      case MARCH:
        return "Mar";
      case APRIL:
        return "Apr";
      case MAY:
        return "May";
      case JUNE:
        return "Jun";
      case JULY:
        return "Jul";
      case AUGUST:
        return "Aug";
      case SEPTEMBER:
        return "Sep";
      case OCTOBER:
        return "Oct";
      case NOVEMBER:
        return "Nov";
      default:
        return "Des";
    }
  }

  @Override
  public List<PolicyStatisticResponseDTO> getPolicyStatistics(
    PolicyPeriod period,
    int year
  ) {
    List<PolicyStatisticResponseDTO> result = new ArrayList<>();

    if (period == PolicyPeriod.MONTHLY) {
      for (Month month : Month.values()) {
        PolicyStatisticResponseDTO dto = new PolicyStatisticResponseDTO();
        dto.setMonthOrQuartal(mapMonth(month));
        dto.setQty(policyDb.countByCreatedMonthAndYear(month.getValue(), year));
        result.add(dto);
      }
    } else {
      for (Quarter quarter : Quarter.values()) {
        PolicyStatisticResponseDTO dto = new PolicyStatisticResponseDTO();
        dto.setMonthOrQuartal(quarter.getValue());

        long totalQty = 0;
        for (Month month : Quarter.getMonths(quarter)) {
          totalQty +=
            policyDb.countByCreatedMonthAndYear(month.getValue(), year);
        }
        dto.setQty(totalQty);
        result.add(dto);
      }
    }

    return result;
  }

  private long getTotalPoliciesCount() {
    return policyDb.count();
  }

  private String generatePolicyId(Policy policy) {
    String patientInitials = generatePatientInitials(policy);
    String companyPrefix = policy
      .getCompany()
      .getName()
      .substring(0, 3)
      .toUpperCase();
    long policyCount = getTotalPoliciesCount() + 1;

    return String.format(
      "POL%s%s%04d",
      patientInitials,
      companyPrefix,
      policyCount
    );
  }

  private String generatePatientInitials(Policy policy) {
    String[] nameParts = policy.getPatient().getName().split(" ");
    String firstName = nameParts[0];
    String lastName = nameParts.length > 1 ? nameParts[1] : firstName;

    return (firstName.substring(0, 1) + lastName.substring(0, 1)).toUpperCase();
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
