package apap.ti.insurance2206027772;

import apap.ti.insurance2206027772.enums.Gender;
import apap.ti.insurance2206027772.enums.PClass;
import apap.ti.insurance2206027772.enums.PolicyStatus;
import apap.ti.insurance2206027772.models.Company;
import apap.ti.insurance2206027772.models.Coverage;
import apap.ti.insurance2206027772.models.Patient;
import apap.ti.insurance2206027772.models.Policy;
import apap.ti.insurance2206027772.repositories.CompanyDb;
import apap.ti.insurance2206027772.repositories.CoverageDb;
import apap.ti.insurance2206027772.repositories.PatientDb;
import apap.ti.insurance2206027772.repositories.PolicyDb;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Insurance2206027772Application {

  public static void main(String[] args) {
    SpringApplication.run(Insurance2206027772Application.class, args);
  }

  @Bean
  @Transactional
  CommandLineRunner run(
    CompanyDb companyDb,
    CoverageDb coverageDb,
    PatientDb patientDb,
    PolicyDb policyDb
  ) {
    return args -> {
      Faker faker = new Faker();

      Coverage coverage1 = new Coverage();
      coverage1.setName("X-ray");
      coverage1.setCoverageAmount(150000L);

      Coverage coverage2 = new Coverage();
      coverage2.setName("CT Scan");
      coverage2.setCoverageAmount(1000000L);

      Coverage coverage3 = new Coverage();
      coverage3.setName("MRI");
      coverage3.setCoverageAmount(2500000L);

      Coverage coverage4 = new Coverage();
      coverage4.setName("Ultrasound");
      coverage4.setCoverageAmount(300000L);

      Coverage coverage5 = new Coverage();
      coverage5.setName("Blood Clotting Test");
      coverage5.setCoverageAmount(50000L);

      Coverage coverage6 = new Coverage();
      coverage6.setName("Blood Glucose Test");
      coverage6.setCoverageAmount(30000L);

      Coverage coverage7 = new Coverage();
      coverage7.setName("Liver Function Test");
      coverage7.setCoverageAmount(75000L);

      Coverage coverage8 = new Coverage();
      coverage8.setName("Complete Blood Count");
      coverage8.setCoverageAmount(50000L);

      Coverage coverage9 = new Coverage();
      coverage9.setName("Urinalysis");
      coverage9.setCoverageAmount(40000L);

      Coverage coverage10 = new Coverage();
      coverage10.setName("COVID-19 testing");
      coverage10.setCoverageAmount(150000L);

      Coverage coverage11 = new Coverage();
      coverage11.setName("Cholesterol Test");
      coverage11.setCoverageAmount(60000L);

      Coverage coverage12 = new Coverage();
      coverage12.setName("Inpatient care");
      coverage12.setCoverageAmount(1000000L);

      Coverage coverage13 = new Coverage();
      coverage13.setName("Surgery");
      coverage13.setCoverageAmount(7000000L);

      Coverage coverage14 = new Coverage();
      coverage14.setName("ICU");
      coverage14.setCoverageAmount(2000000L);

      Coverage coverage15 = new Coverage();
      coverage15.setName("ER");
      coverage15.setCoverageAmount(500000L);

      Coverage coverage16 = new Coverage();
      coverage16.setName("Flu shot");
      coverage16.setCoverageAmount(100000L);

      Coverage coverage17 = new Coverage();
      coverage17.setName("Hepatitis vaccine");
      coverage17.setCoverageAmount(200000L);

      Coverage coverage18 = new Coverage();
      coverage18.setName("COVID-19 Vaccine");
      coverage18.setCoverageAmount(200000L);

      Coverage coverage19 = new Coverage();
      coverage19.setName("MMR Vaccine");
      coverage19.setCoverageAmount(350000L);

      Coverage coverage20 = new Coverage();
      coverage20.setName("HPV Vaccine");
      coverage20.setCoverageAmount(800000L);

      Coverage coverage21 = new Coverage();
      coverage21.setName("Pneumococcal Vaccine");
      coverage21.setCoverageAmount(900000L);

      Coverage coverage22 = new Coverage();
      coverage22.setName("Herpes Zoster Vaccine");
      coverage22.setCoverageAmount(1500000L);

      Coverage coverage23 = new Coverage();
      coverage23.setName("Physical exam");
      coverage23.setCoverageAmount(250000L);

      Coverage coverage24 = new Coverage();
      coverage24.setName("Mammogram");
      coverage24.setCoverageAmount(500000L);

      Coverage coverage25 = new Coverage();
      coverage25.setName("Colonoscopy");
      coverage25.setCoverageAmount(3000000L);

      Coverage coverage26 = new Coverage();
      coverage26.setName("Dental X-ray");
      coverage26.setCoverageAmount(200000L);

      Coverage coverage27 = new Coverage();
      coverage27.setName("Fillings");
      coverage27.setCoverageAmount(400000L);

      Coverage coverage28 = new Coverage();
      coverage28.setName("Dental scaling");
      coverage28.setCoverageAmount(500000L);

      Coverage coverage29 = new Coverage();
      coverage29.setName("Physical therapy");
      coverage29.setCoverageAmount(250000L);

      Coverage coverage30 = new Coverage();
      coverage30.setName("Occupational therapy");
      coverage30.setCoverageAmount(300000L);

      Coverage coverage31 = new Coverage();
      coverage31.setName("Speech therapy");
      coverage31.setCoverageAmount(300000L);

      Coverage coverage32 = new Coverage();
      coverage32.setName("Psychiatric evaluation");
      coverage32.setCoverageAmount(600000L);

      Coverage coverage33 = new Coverage();
      coverage33.setName("Natural delivery");
      coverage33.setCoverageAmount(3500000L);

      Coverage coverage34 = new Coverage();
      coverage34.setName("C-section");
      coverage34.setCoverageAmount(12000000L);

      List<Coverage> coverages = List.of(
        coverage1,
        coverage2,
        coverage3,
        coverage4,
        coverage5,
        coverage6,
        coverage7,
        coverage8,
        coverage9,
        coverage10,
        coverage11,
        coverage12,
        coverage13,
        coverage14,
        coverage15,
        coverage16,
        coverage17,
        coverage18,
        coverage19,
        coverage20,
        coverage21,
        coverage22,
        coverage23,
        coverage24,
        coverage25,
        coverage26,
        coverage27,
        coverage28,
        coverage29,
        coverage30,
        coverage31,
        coverage32,
        coverage33,
        coverage34
      );
      coverageDb.saveAll(coverages);

      for (int i = 0; i < 5; i++) {
        Company company = new Company();
        company.setName(faker.company().name());
        company.setContact(faker.phoneNumber().phoneNumber());
        company.setEmail(faker.internet().emailAddress());
        company.setAddress(faker.address().fullAddress());
        company.setListCoverage(coverages);

        companyDb.save(company);
      }

      List<Patient> patients = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
        Patient patient = new Patient();
        patient.setNik(faker.idNumber().valid());
        patient.setName(faker.name().fullName());
        patient.setGender(faker.options().option(Gender.class));
        patient.setBirthDate(faker.date().birthday(18, 65));
        patient.setEmail(faker.internet().emailAddress());
        patient.setPClass(faker.options().option(PClass.class));

        patientDb.save(patient);
        patients.add(patient);
      }

      for (int i = 0; i < 5; i++) {
        for (Patient patient : patients) {
          Company company = companyDb
            .findAll()
            .get(faker.random().nextInt(0, 4));

          Policy policy = new Policy();
          policy.setId(generatePolicyId(patient, company, policyDb));
          policy.setCompany(company);
          policy.setPatient(patient);
          policy.setStatus(PolicyStatus.CREATED);
          policy.setExpiryDate(
            faker.date().future(365, java.util.concurrent.TimeUnit.DAYS)
          );
          policy.setTotalCoverage(
            faker.number().numberBetween(1000000L, 5000000L)
          );
          policy.setTotalCovered(0L);

          policyDb.save(policy);
        }
      }
    };
  }

  private String generatePolicyId(
    Patient patient,
    Company company,
    PolicyDb policyDb
  ) {
    String patientInitial = patient.getName().substring(0, 2).toUpperCase();
    String companyInitial = company.getName().substring(0, 3).toUpperCase();

    long policyCount = policyDb.count();

    return String.format(
      "POL%s%s%04d",
      patientInitial,
      companyInitial,
      policyCount + 1
    );
  }
}
