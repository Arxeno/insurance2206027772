package apap.ti.insurance2206027772.repositories;

import apap.ti.insurance2206027772.models.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyDb extends JpaRepository<Policy, String> {}
