package apap.ti.insurance2206027772.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class Company extends Base {

  @NotNull
  private String name;

  @NotNull
  private String contact;

  @NotNull
  private String email;

  @NotNull
  private String address;

  @ManyToMany
  @JoinTable(
    name = "coverage_company",
    joinColumns = @JoinColumn(name = "company_id"),
    inverseJoinColumns = @JoinColumn(name = "coverage_id")
  )
  private List<Coverage> coverages;
}
