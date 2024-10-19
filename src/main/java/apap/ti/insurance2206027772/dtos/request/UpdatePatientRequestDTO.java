package apap.ti.insurance2206027772.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UpdatePatientRequestDTO extends AddPatientRequestDTO {

  @NotNull
  private UUID id;
}
