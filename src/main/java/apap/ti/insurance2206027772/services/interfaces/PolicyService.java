package apap.ti.insurance2206027772.services.interfaces;

import apap.ti.insurance2206027772.dtos.request.AddPolicyAndPatientRequestDTO;
import apap.ti.insurance2206027772.dtos.request.AddPolicyRequestDTO;
import apap.ti.insurance2206027772.enums.PolicyStatus;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Policy;
import java.util.List;
import org.apache.coyote.BadRequestException;

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
  Policy createPolicy(AddPolicyRequestDTO dto)
    throws NotFound, BadRequestException;
  Policy createPolicy(AddPolicyAndPatientRequestDTO dto)
    throws NotFound, BadRequestException;
  void deletePolicyById(String id) throws NotFound;
}
