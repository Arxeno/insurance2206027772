package apap.ti.insurance2206027772.services.interfaces;

import apap.ti.insurance2206027772.models.Policy;
import java.util.List;

public interface PolicyService {
  long getTotalPoliciesCount();
  List<Policy> getAllPolicies();
  Policy getPolicyById(String id);
  Policy createPolicy(Policy policy);
  void deletePolicyById(String id);
}
