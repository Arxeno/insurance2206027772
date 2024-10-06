package apap.ti.insurance2206027772.models;

import jakarta.persistence.Id;
import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class Base {

  @Id
  private UUID id = UUID.randomUUID();

  @CreationTimestamp
  private Date createdAt;

  @UpdateTimestamp
  private Date updatedAt;

  private String createdBy;
  private String updatedBy;
}
