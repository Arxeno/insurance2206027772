package apap.ti.insurance2206027772.services;

import apap.ti.insurance2206027772.dtos.request.AddPolicyAndPatientRequestDTO;
import apap.ti.insurance2206027772.models.Patient;
import apap.ti.insurance2206027772.repositories.PatientDb;
import apap.ti.insurance2206027772.services.interfaces.PatientService;
import java.util.UUID;
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

  @Override
  public Patient getPatientById(UUID id) {
    return patientDb.findById(id).orElse(null);
  }

  @Override
  public Patient getPatientByNik(String nik) {
    return patientDb.findByNik(nik);
  }

  @Override
  public Patient createPatient(Patient patient) {
    return patientDb.save(patient);
  }

  @Override
  public Patient createPatient(AddPolicyAndPatientRequestDTO dto) {
    Patient patient = new Patient();
    patient.setNik(dto.getNik());
    patient.setName(dto.getName());
    patient.setGender(dto.getGender());
    patient.setBirthDate(dto.getBirthDate());
    patient.setEmail(dto.getEmail());
    patient.setPClass(dto.getPClass());

    return createPatient(patient);
  }
}
