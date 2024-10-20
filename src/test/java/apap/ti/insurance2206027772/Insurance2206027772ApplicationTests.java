package apap.ti.insurance2206027772;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import apap.ti.insurance2206027772.enums.PolicyStatus;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.models.Patient;
import apap.ti.insurance2206027772.models.Policy;
import apap.ti.insurance2206027772.repositories.PolicyDb;
import apap.ti.insurance2206027772.services.PolicyServiceImpl;
import com.github.javafaker.Faker;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class Insurance2206027772ApplicationTests {

  @MockBean
  private PolicyDb policyDb;

  @Autowired
  private PolicyServiceImpl policyService;

  public Insurance2206027772ApplicationTests() {
    openMocks(this);
  }

  @Test
  public void testGetAllPolicies() {
    Faker faker = new Faker();

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
    mockPolicy.setExpiryDate(
      faker.date().future(365, java.util.concurrent.TimeUnit.DAYS)
    );
    mockPolicy.setStatus(PolicyStatus.CREATED);

    when(policyDb.findAll()).thenReturn(List.of(mockPolicy));

    when(policyDb.save(mockPolicy)).thenReturn(mockPolicy);

    System.out.println(policyDb.findAll());

    List<Policy> policies = policyService.getAllPolicies();

    System.out.println(policies);

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
