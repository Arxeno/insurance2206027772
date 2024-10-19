package apap.ti.insurance2206027772.api.dtos.response;

import apap.ti.insurance2206027772.enums.PolicyStatus;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PolicyResponseDTO {

  private String id;

  private CompanyResponseDTO company;

  private PatientResponseDTO patient;

  private PolicyStatus status;

  private Date expiryDate;

  private Long totalCoverage;

  private Long totalCovered;
}
