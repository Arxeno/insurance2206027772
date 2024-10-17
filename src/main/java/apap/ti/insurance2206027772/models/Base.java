package apap.ti.insurance2206027772.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Setter
@Getter
public class Base extends BaseCreatedUpdated {

  @Id
  private UUID id = UUID.randomUUID();
}
