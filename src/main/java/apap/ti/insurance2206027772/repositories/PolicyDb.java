package apap.ti.insurance2206027772.repositories;

import apap.ti.insurance2206027772.models.Policy;
import java.time.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyDb extends JpaRepository<Policy, String> {
  @Query(
    "SELECT COUNT(p) FROM Policy p WHERE EXTRACT(MONTH FROM p.createdAt) = :month AND EXTRACT(YEAR FROM p.createdAt) = :year"
  )
  Long countByCreatedMonthAndYear(
    @Param("month") int month,
    @Param("year") int year
  );
}
