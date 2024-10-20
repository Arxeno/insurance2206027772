package apap.ti.insurance2206027772.models;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Setter
@Getter
public class BaseCreatedUpdated {

  @CreationTimestamp
  private LocalDate createdAt;

  @UpdateTimestamp
  private LocalDate updatedAt;
}
