package apap.ti.insurance2206027772.models;

import apap.ti.insurance2206027772.enums.Gender;
import apap.ti.insurance2206027772.enums.PClass;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
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
public class Patient extends Base {

  @Column(unique = true)
  @NotNull
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

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private PClass pClass;

  @OneToMany(mappedBy = "patient")
  private List<Policy> listPolicy;

  public Period getAge() {
    LocalDate birthDateLocal = birthDate
      .toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
    LocalDate currenDate = LocalDate.now();

    Period age = Period.between(birthDateLocal, currenDate);

    return age;
  }

  public Long getInsuranceLimit() {
    return PClass.getClassLimit(pClass);
  }

  public String getInsuranceLimitString() {
    String formatted = String.format("%,d", getInsuranceLimit());

    return String.format("IDR %s.00", formatted);
  }

  public Long getAvailableLimit() {
    Long availableLimit = getInsuranceLimit();

    if (listPolicy == null) {
      return availableLimit;
    }

    for (Policy policy : listPolicy) {
      availableLimit -= policy.getTotalCoverage();
    }

    return availableLimit;
  }

  public String getAvailableLimitString() {
    String formatted = String.format("%,d", getAvailableLimit());

    return String.format("IDR %s.00", formatted);
  }
}
