package apap.ti.insurance2206027772.services.interfaces;

import apap.ti.insurance2206027772.models.Patient;

public interface PatientService {
  long getTotalPatientsCount();
  Patient getPatientByNik(String nik);
}
