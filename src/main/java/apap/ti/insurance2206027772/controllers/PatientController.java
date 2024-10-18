package apap.ti.insurance2206027772.controllers;

import apap.ti.insurance2206027772.enums.PClass;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/patient")
public class PatientController {

  @GetMapping("/{id}/upgrade-class")
  public String getUpgradeClassPatientForm(@PathVariable("id") UUID id) {
    // TODO: dto etc

    return "upgrade-class-patient-form";
  }

  @PostMapping("/upgrade-class")
  public String upgradeClassPatient(Model model) {
    //TODO: process POST request
    String name = "John Doe";
    PClass pClass = PClass.LEVEL_1;

    model.addAttribute(
      "message",
      String.format(
        "Class pasien %s berhasil di-upgrade menjadi Class %d.",
        name,
        pClass
      )
    );

    return "response";
  }
}
