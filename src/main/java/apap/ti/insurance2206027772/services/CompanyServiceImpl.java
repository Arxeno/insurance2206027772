package apap.ti.insurance2206027772.services;

import apap.ti.insurance2206027772.repositories.CompanyDb;
import apap.ti.insurance2206027772.services.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyServiceImpl implements CompanyService {

  @Autowired
  private CompanyDb companyDb;

  @Override
  public long getTotalCompaniesCount() {
    return companyDb.countByDeletedAtNull();
  }
}
