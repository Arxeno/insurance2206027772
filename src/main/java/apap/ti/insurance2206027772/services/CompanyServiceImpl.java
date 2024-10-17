package apap.ti.insurance2206027772.services;

import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.repositories.CompanyDb;
import apap.ti.insurance2206027772.services.interfaces.CompanyService;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.resource.NoResourceFoundException;

public class CompanyServiceImpl implements CompanyService {

  @Autowired
  private CompanyDb companyDb;

  @Override
  public long getTotalCompaniesCount() {
    return companyDb.countByDeletedAtNull();
  }

  @Override
  public List<Company> getAllCompanies() {
    return companyDb.findByDeletedAtNull();
  }

  @Override
  public Company getCompanyById(UUID id) {
    return companyDb.findByDeletedAtNullAndId(id);
  }

  @Override
  public Company createCompany(Company company) {
    return companyDb.save(company);
  }

  @Override
  public void deleteCompanyById(UUID id) throws NoResourceFoundException {
    Company company = companyDb.findById(id).orElse(null);

    if (company == null) {
      throw new NoResourceFoundException(null, null);
    }

    company.setDeletedAt(new Date());

    companyDb.save(company);
  }
}
