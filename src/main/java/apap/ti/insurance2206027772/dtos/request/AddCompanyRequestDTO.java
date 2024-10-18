package apap.ti.insurance2206027772.dtos.request;

import apap.ti.insurance2206027772.models.Coverage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class AddCompanyRequestDTO {

  @NotBlank
  private String name;

  @NotBlank
  private String contact;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String address;

  private List<Coverage> listCoverage;

  public Long getTotalCoverage(List<Coverage> allCoveragesFromDb) {
    Long totalCoverage = 0L;

    if (listCoverage == null) {
      return totalCoverage;
    }

    for (Coverage coverage : listCoverage) {
      List<Coverage> selectedCoverage = allCoveragesFromDb
        .stream()
        .filter(coverageFromDb -> coverageFromDb.getId() == coverage.getId())
        .toList();

      Long coverageAmount = 0L;
      if (selectedCoverage.size() > 0) {
        coverageAmount = selectedCoverage.get(0).getCoverageAmount();
      } else {
        coverageAmount = allCoveragesFromDb.get(0).getCoverageAmount();
      }

      totalCoverage += coverageAmount;
    }

    return totalCoverage;
  }

  public String getTotalCoverageString(List<Coverage> allCoveragesFromDb) {
    String formatted = String.format(
      "%,d",
      getTotalCoverage(allCoveragesFromDb)
    );

    return String.format("IDR %s.00", formatted);
  }
}
