package apap.ti.insurance2206027772.services.interfaces;

import apap.ti.insurance2206027772.enums.PolicyStatus;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Policy;
import java.util.List;

public interface PolicyService {
  long getTotalPoliciesCount();
  List<Policy> getAllPolicies();
  List<Policy> getAllPolicies(
    PolicyStatus status,
    Long minCoverage,
    Long maxCoverage
  );
  Policy getPolicyById(String id);
  Policy createPolicy(Policy policy);
  void deletePolicyById(String id) throws NotFound;
}
