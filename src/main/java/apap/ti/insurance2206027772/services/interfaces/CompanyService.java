package apap.ti.insurance2206027772.services.interfaces;

import apap.ti.insurance2206027772.dtos.request.UpdateCompanyRequestDTO;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Company;
import java.util.List;
import java.util.UUID;

public interface CompanyService {
  long getTotalCompaniesCount();
  List<Company> getAllCompanies();
  Company getCompanyById(UUID id);
  Company createCompany(Company company);
  void deleteCompanyById(UUID id) throws NotFound;
  Company updateCompany(UpdateCompanyRequestDTO dto) throws NotFound;
}
