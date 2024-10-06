package apap.ti.insurance2206027772.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
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
}
