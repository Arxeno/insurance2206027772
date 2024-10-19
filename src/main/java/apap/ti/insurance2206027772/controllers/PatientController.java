package apap.ti.insurance2206027772.controllers;

import apap.ti.insurance2206027772.dtos.request.UpdatePatientRequestDTO;
import apap.ti.insurance2206027772.enums.PClass;
import apap.ti.insurance2206027772.exceptions.NotFound;
import apap.ti.insurance2206027772.models.Patient;
import apap.ti.insurance2206027772.services.interfaces.PatientService;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/patient")
public class PatientController {

  @Autowired
  private PatientService patientService;

  @GetMapping("/{id}/upgrade-class")
  public String getUpgradeClassPatientForm(
    @PathVariable("id") UUID id,
    Model model
  ) throws NotFound {
    Patient patient = patientService.getPatientById(id);

    if (patient == null) {
      throw new NotFound("Patient dengan ID " + id + " tidak dapat ditemukan.");
    }

    UpdatePatientRequestDTO dto = new UpdatePatientRequestDTO();
    dto.setId(patient.getId());
    dto.setNik(patient.getNik());
    dto.setName(patient.getName());
    dto.setGender(patient.getGender());
    dto.setBirthDate(patient.getBirthDate());
    dto.setEmail(patient.getEmail());
    dto.setPClass(patient.getPClass());

    model.addAttribute("dto", dto);

    return "upgrade-class-patient-form";
  }

  @PostMapping("/upgrade-class")
  public String upgradeClassPatient(
    @ModelAttribute UpdatePatientRequestDTO dto,
    // TODO: validation
    // @Valid @ModelAttribute UpdatePatientRequestDTO dto,
    // BindingResult bindingResult,
    Model model
  ) {
    //TODO: process POST request
    Patient patient = patientService.upgradeClassPatient(dto);

    model.addAttribute(
      "message",
      String.format(
        "Class pasien %s berhasil di-upgrade menjadi Class %s.",
        patient.getName(),
        patient.getPClass().toString()
      )
    );

    return "response";
  }
}
