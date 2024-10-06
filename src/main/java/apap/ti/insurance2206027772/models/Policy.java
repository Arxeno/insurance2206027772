package apap.ti.insurance2206027772.models;

import apap.ti.insurance2206027772.enums.PolicyStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Policy extends Base {

  // TODO: relationa (check all models relation too)
  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private PolicyStatus status;

  @NotNull
  private Date expiryDate;

  @NotNull
  private Long totalCoverage;

  @NotNull
  private Long totalCovered;
}
