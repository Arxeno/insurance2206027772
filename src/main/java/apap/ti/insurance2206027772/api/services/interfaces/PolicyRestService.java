package apap.ti.insurance2206027772.api.services.interfaces;

import apap.ti.insurance2206027772.api.dtos.response.PolicyResponseDTO;
import apap.ti.insurance2206027772.api.dtos.response.PolicyStatisticResponseDTO;
import apap.ti.insurance2206027772.enums.PolicyPeriod;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.models.Patient;
import java.util.Date;
import java.util.List;

public interface PolicyRestService {
  PolicyResponseDTO getPolicyById(String id) throws NotFound;
  PolicyResponseDTO createPolicyForPatient(
    Patient patient,
    Company company,
    Date expiryDate
  );
  List<PolicyStatisticResponseDTO> getPolicyStatistics(
    PolicyPeriod period,
    int year
  );
}
