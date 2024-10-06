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
public class Policy extends Base {

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
  private PolicyStatus status;

  @NotNull
  private Date expiryDate;

  @NotNull
  private Long totalCoverage;

  @NotNull
  private Long totalCovered;

  @PrePersist
  private void generatePolicyId() {
    if (this.id == null) {
      String patientInitials = generatePatientInitials();
      String companyPrefix = company.getName().substring(0, 3).toUpperCase();
      int policyCount = getTotalPoliciesCount() + 1;

      this.id =
        String.format(
          "POL%s%s%04d",
          patientInitials,
          companyPrefix,
          policyCount
        );
    }
  }

  private String generatePatientInitials() {
    String[] nameParts = patient.getName().split(" ");
    String firstName = nameParts[0];
    String lastName = nameParts.length > 1 ? nameParts[1] : firstName;

    return (firstName.substring(0, 1) + lastName.substring(0, 1)).toUpperCase();
  }

  private int getTotalPoliciesCount() {
    // TODO: return all policies, use policy repository
    return 0;
  }
}
