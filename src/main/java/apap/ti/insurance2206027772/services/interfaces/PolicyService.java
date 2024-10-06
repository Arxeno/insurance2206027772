package apap.ti.insurance2206027772.services.interfaces;

import apap.ti.insurance2206027772.models.Policy;

public interface PolicyService {
  long getTotalPoliciesCount();
  Policy savePolicy(Policy policy);
}
