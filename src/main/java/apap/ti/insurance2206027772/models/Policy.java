package apap.ti.insurance2206027772.models;

import apap.ti.insurance2206027772.enums.PolicyStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
public class Policy extends BaseCreatedUpdated {

  @Id
  @Column(unique = true, length = 12)
  private String id;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private PolicyStatus status = PolicyStatus.CREATED;

  @NotNull
  private Date expiryDate;

  @NotNull
  private Long totalCoverage;

  @NotNull
  private Long totalCovered = 0L;

  public String getTotalCoverageString() {
    String formatted = String.format("%,d", totalCoverage);

    return String.format("IDR %s.00", formatted);
  }

  public String getTotalCoveredString() {
    String formatted = String.format("%,d", totalCovered);

    return String.format("IDR %s.00", formatted);
  }

  public void setTotalCovered(Long totalCovered) {
    if (status != PolicyStatus.CANCELLED || status != PolicyStatus.EXPIRED) {
      this.totalCovered = totalCovered;

      if (this.totalCovered >= totalCoverage) {
        setStatus(PolicyStatus.FULLY_CLAIMED);
      } else {
        setStatus(PolicyStatus.PARTIALLY_CLAIMED);
      }
    }
  }

  public void refreshStatus() {
    if (status != PolicyStatus.CANCELLED) {
      if (totalCovered == totalCoverage) {
        setStatus(PolicyStatus.FULLY_CLAIMED);
      }

      Date today = new Date();
      if (today.getTime() >= expiryDate.getTime()) {
        setStatus(PolicyStatus.CANCELLED);
      }

      if (totalCovered > 0 && totalCovered < totalCoverage) {
        setStatus(PolicyStatus.PARTIALLY_CLAIMED);
      }

      setStatus(PolicyStatus.CREATED);
    }
  }
}
