package apap.ti.insurance2206027772.models;

import apap.ti.insurance2206027772.enums.Gender;
import apap.ti.insurance2206027772.enums.PClass;
import jakarta.persistence.Column;
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
public class Patient extends Base {

  @Column(unique = true)
  private String nik;

  @NotNull
  private String name;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private Gender gender;

  @NotNull
  private Date birthDate;

  @NotNull
  private String email;

  private PClass pClass;
}
