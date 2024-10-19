package apap.ti.insurance2206027772.api.services.interfaces;

import apap.ti.insurance2206027772.api.dtos.response.PolicyResponseDTO;

public interface PolicyRestService {
  PolicyResponseDTO getPolicyById(String id);
}
