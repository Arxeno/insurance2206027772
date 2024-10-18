package apap.ti.insurance2206027772.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class UpdateCompanyRequestDTO extends AddCompanyRequestDTO {

  @NotNull
  private UUID id;
}
