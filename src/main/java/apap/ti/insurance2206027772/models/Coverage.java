package apap.ti.insurance2206027772.models;

import jakarta.persistence.Entity;
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
public class Coverage extends Base {

  @NotNull
  private String name;

  @NotNull
  private Long coverageAmount;

  @ManyToMany(mappedBy = "coverages")
  private List<Company> companies;
}
