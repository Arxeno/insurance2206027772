package apap.ti.insurance2206027772.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class Base extends BaseCreatedUpdated {

  @Id
  private UUID id = UUID.randomUUID();
}
