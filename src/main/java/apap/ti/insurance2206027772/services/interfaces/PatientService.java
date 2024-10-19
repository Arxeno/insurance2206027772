package apap.ti.insurance2206027772.services.interfaces;

import apap.ti.insurance2206027772.models.Patient;
import java.util.UUID;

public interface PatientService {
  long getTotalPatientsCount();
  Patient getPatientById(UUID id);
  Patient getPatientByNik(String nik);
}
