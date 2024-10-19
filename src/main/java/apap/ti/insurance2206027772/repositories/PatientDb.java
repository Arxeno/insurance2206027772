package apap.ti.insurance2206027772.repositories;

import apap.ti.insurance2206027772.models.Patient;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDb extends JpaRepository<Patient, UUID> {
  Patient findByNik(String nik);
}
