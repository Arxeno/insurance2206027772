package apap.ti.insurance2206027772.repositories;

import apap.ti.insurance2206027772.models.Coverage;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverageDb extends JpaRepository<Coverage, UUID> {}
