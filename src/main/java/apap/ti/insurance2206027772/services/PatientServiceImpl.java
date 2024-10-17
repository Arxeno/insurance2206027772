package apap.ti.insurance2206027772.services;

import apap.ti.insurance2206027772.repositories.PatientDb;
import apap.ti.insurance2206027772.services.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

  @Autowired
  private PatientDb patientDb;

  @Override
  public long getTotalPatientsCount() {
    return patientDb.count();
  }
}
