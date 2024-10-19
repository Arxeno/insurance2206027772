package apap.ti.insurance2206027772.api.dtos.response;

import apap.ti.insurance2206027772.enums.Gender;
import apap.ti.insurance2206027772.enums.PClass;
import apap.ti.insurance2206027772.models.Policy;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientResponseDTO {

  private String nik;

  private String name;

  private Gender gender;

  private Date birthDate;

  private String email;

  private PClass pClass;

  private List<Policy> listPolicy;
}
