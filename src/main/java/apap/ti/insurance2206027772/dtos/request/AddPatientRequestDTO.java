package apap.ti.insurance2206027772.dtos.request;

import apap.ti.insurance2206027772.enums.Gender;
import apap.ti.insurance2206027772.enums.PClass;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@Data
public class AddPatientRequestDTO {

  @NotBlank
  private String nik;

  @NotBlank
  private String name;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private Gender gender;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent
  private Date birthDate;

  @NotBlank
  @Email
  private String email;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private PClass pClass;
}
