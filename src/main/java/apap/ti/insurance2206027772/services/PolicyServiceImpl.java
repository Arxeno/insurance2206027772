package apap.ti.insurance2206027772.services;

import apap.ti.insurance2206027772.models.Policy;
import apap.ti.insurance2206027772.repositories.PolicyDb;
import apap.ti.insurance2206027772.services.interfaces.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;

public class PolicyServiceImpl implements PolicyService {

  @Autowired
  private PolicyDb policyRepository;

  public int getTotalPoliciesCount() {
    return (int) policyRepository.count();
  }

  public String generatePolicyId(Policy policy) {
    String patientInitials = generatePatientInitials(policy);
    String companyPrefix = policy
      .getCompany()
      .getName()
      .substring(0, 3)
      .toUpperCase();
    int policyCount = getTotalPoliciesCount() + 1;

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

  @Override
  public Policy savePolicy(Policy policy) {
    if (policy.getId() == null) {
      String policyId = generatePolicyId(policy);
      policy.setId(policyId);
    }
    return policyRepository.save(policy);
  }
}
