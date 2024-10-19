package apap.ti.insurance2206027772.dtos.request;

import apap.ti.insurance2206027772.enums.Gender;
import apap.ti.insurance2206027772.enums.PClass;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;

public class AddPatientRequestDTO {

  @NotBlank
  private String nik;

  @NotBlank
  private String name;

  @NotBlank
  @Enumerated(EnumType.ORDINAL)
  private Gender gender;

  @NotBlank
  private Date birthDate;

  @NotBlank
  private String email;

  @NotBlank
  @Enumerated(EnumType.ORDINAL)
  private PClass pClass;
}
