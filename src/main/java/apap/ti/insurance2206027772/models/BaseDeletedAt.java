package apap.ti.insurance2206027772.models;

import jakarta.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseDeletedAt extends Base {

  private Date deletedAt;
}
