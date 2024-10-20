package apap.ti.insurance2206027772.services;

import apap.ti.insurance2206027772.models.Coverage;
import apap.ti.insurance2206027772.repositories.CoverageDb;
import apap.ti.insurance2206027772.services.interfaces.CoverageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverageServiceImpl implements CoverageService {

  @Autowired
  private CoverageDb coverageDb;

  @Override
  public List<Coverage> getAllCoverages() {
    return coverageDb.findAll();
  }
}
