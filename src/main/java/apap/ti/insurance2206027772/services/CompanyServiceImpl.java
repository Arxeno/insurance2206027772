package apap.ti.insurance2206027772.services;

import apap.ti.insurance2206027772.dtos.request.UpdateCompanyRequestDTO;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.repositories.CompanyDb;
import apap.ti.insurance2206027772.services.interfaces.CompanyService;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
  public void deleteCompanyById(UUID id) throws NotFound {
    Company company = companyDb.findById(id).orElse(null);

    if (company == null) {
      throw new NotFound(
        String.format(
          "Company dengan ID %s tidak dapat ditemukan.",
          id.toString()
        )
      );
    }

    company.setDeletedAt(new Date());

    companyDb.save(company);
  }

  @Override
  public Company updateCompany(UpdateCompanyRequestDTO dto) throws NotFound {
    Company company = getCompanyById(dto.getId());

    if (company == null) {
      throw new NotFound(
        String.format(
          "Company dengan ID %s tidak dapat ditemukan.",
          dto.getId().toString()
        )
      );
    }

    company.setName(dto.getName());
    company.setContact(dto.getContact());
    company.setEmail(dto.getEmail());
    company.setAddress(dto.getAddress());
    company.setListCoverage(dto.getListCoverage());

    company = companyDb.save(company);

    return company;
  }
}
