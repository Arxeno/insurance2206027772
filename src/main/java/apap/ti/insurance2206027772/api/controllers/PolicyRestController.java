package apap.ti.insurance2206027772.api.controllers;

import apap.ti.insurance2206027772.api.dtos.response.BaseResponseDTO;
import apap.ti.insurance2206027772.api.dtos.response.PolicyResponseDTO;
import apap.ti.insurance2206027772.api.services.interfaces.PolicyRestService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/policy")
public class PolicyRestController {

  @Autowired
  private PolicyRestService policyRestService;

  @GetMapping("/detail")
  public ResponseEntity<BaseResponseDTO<PolicyResponseDTO>> detailPolicy(
    @RequestParam("id") String id
  ) {
    var baseResponseDTO = new BaseResponseDTO<PolicyResponseDTO>();

    PolicyResponseDTO detailPolicy = policyRestService.getPolicyById(id);

    baseResponseDTO.setStatus(HttpStatus.OK.value());
    baseResponseDTO.setData(detailPolicy);
    baseResponseDTO.setMessage(String.format("Success get detail policy!"));
    baseResponseDTO.setTimestamp(new Date());
    return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
  }
}
