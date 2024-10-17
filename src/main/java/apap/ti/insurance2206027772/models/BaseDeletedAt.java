package apap.ti.insurance2206027772.models;

import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class BaseDeletedAt extends Base {

  private Date deletedAt;
}
