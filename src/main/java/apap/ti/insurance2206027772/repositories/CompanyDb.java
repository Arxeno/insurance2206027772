package apap.ti.insurance2206027772.repositories;

import apap.ti.insurance2206027772.models.Company;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDb extends JpaRepository<Company, UUID> {
  long countByDeletedAtNull();
  List<Company> findByDeletedAtNull();
  Company findByDeletedAtNullAndId(UUID id);
}
