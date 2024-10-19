package apap.ti.insurance2206027772.api.dtos.response;

import apap.ti.insurance2206027772.models.Coverage;
import apap.ti.insurance2206027772.models.Policy;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyResponseDTO {

  private String name;

  private String contact;

  private String email;

  private String address;

  private List<Coverage> listCoverage;

  private List<Policy> listPolicy;
}
