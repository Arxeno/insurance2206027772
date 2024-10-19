package apap.ti.insurance2206027772.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UpdatePolicyRequestDTO extends AddPolicyRequestDTO {

  @NotBlank
  private String id;
}
