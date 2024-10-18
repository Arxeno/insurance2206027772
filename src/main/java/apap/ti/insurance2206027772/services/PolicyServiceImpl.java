package apap.ti.insurance2206027772.services;

import apap.ti.insurance2206027772.enums.PolicyStatus;
import apap.ti.insurance2206027772.models.Policy;
import apap.ti.insurance2206027772.repositories.PolicyDb;
import apap.ti.insurance2206027772.services.interfaces.PolicyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyServiceImpl implements PolicyService {

  @Autowired
  private PolicyDb policyDb;

  public long getTotalPoliciesCount() {
    return policyDb.count();
  }

  @Override
  public List<Policy> getAllPolicies() {
    return policyDb.findAll();
  }

  @Override
  public List<Policy> getAllPolicies(
    PolicyStatus status,
    Long minCoverage,
    Long maxCoverage
  ) {
    List<Policy> policies = getAllPolicies();

    if (status != null) {
      policies =
        policies
          .stream()
          .filter(policy -> policy.getStatus() == status)
          .toList();
    }

    if (minCoverage != null) {
      policies =
        policies
          .stream()
          .filter(policy -> policy.getTotalCoverage() >= minCoverage)
          .toList();
    }

    if (maxCoverage != null) {
      policies =
        policies
          .stream()
          .filter(policy -> policy.getTotalCoverage() <= maxCoverage)
          .toList();
    }

    return policies;
  }

  @Override
  public Policy getPolicyById(String id) {
    return policyDb.findById(id).orElse(null);
  }

  @Override
  public Policy createPolicy(Policy policy) {
    if (policy.getId() == null) {
      String policyId = generatePolicyId(policy);
      policy.setId(policyId);
    }
    return policyDb.save(policy);
  }

  @Override
  public void deletePolicyById(String id) {
    policyDb.deleteById(id);
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
}
