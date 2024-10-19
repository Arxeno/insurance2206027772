package apap.ti.insurance2206027772;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import apap.ti.insurance2206027772.enums.PolicyStatus;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.models.Patient;
import apap.ti.insurance2206027772.models.Policy;
import apap.ti.insurance2206027772.repositories.PolicyDb;
import apap.ti.insurance2206027772.services.interfaces.PolicyService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Insurance2206027772ApplicationTests {

  @Mock
  private PolicyDb policyDb;

  @InjectMocks
  private PolicyService policyService;

  public Insurance2206027772ApplicationTests() {
    openMocks(this);
  }

  @Test
  public void testGetAllPolicies() {
    Company mockCompany = new Company();
    String companyName = "Starlink";
    mockCompany.setName(companyName);

    Patient mockPatient = new Patient();
    String patientName = "Raisyam Muhammad";
    mockPatient.setName(patientName);

    Policy mockPolicy = new Policy();
    String idPolicy = "POLRMSTAR0001";
    mockPolicy.setId(idPolicy);
    mockPolicy.setPatient(mockPatient);
    mockPolicy.setCompany(mockCompany);
    mockPolicy.setTotalCoverage(mockCompany.getTotalCoverage());
    mockPolicy.setStatus(PolicyStatus.CREATED);

    when(policyDb.findAll()).thenReturn(List.of(mockPolicy));

    List<Policy> policies = policyService.getAllPolicies();

    assertEquals(1, policies.size());
    assertEquals(patientName, policies.get(0).getPatient().getName());
    assertEquals(companyName, policies.get(0).getCompany().getName());
    assertEquals(
      mockCompany.getTotalCoverage(),
      policies.get(0).getTotalCoverage()
    );
    assertEquals(PolicyStatus.CREATED, policies.get(0).getStatus());
  }
}
