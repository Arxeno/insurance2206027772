package apap.ti.insurance2206027772.api.dtos.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@Data
public class AddPolicyRequestRestDTO {

  @NotNull
  private UUID idPatient;

  @NotNull
  private UUID idCompany;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @FutureOrPresent
  private Date expiryDate;
}
