package apap.ti.insurance2206027772.api.dtos.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PolicyStatisticResponseDTO {

  private String monthOrQuartal;
  private Long qty;
}
