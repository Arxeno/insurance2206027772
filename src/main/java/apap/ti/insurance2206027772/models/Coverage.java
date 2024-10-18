package apap.ti.insurance2206027772.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Coverage extends BaseCreatedUpdated {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private Long coverageAmount;

  @ManyToMany(mappedBy = "listCoverage")
  private List<Company> listCompany;

  public String getCoverageAmountString() {
    String formatted = String.format("%,d", coverageAmount);

    return String.format("IDR %s.00", formatted);
  }
}
