package br.com.vvv.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vvv.Domain.DTO.DataRegisterLocalization;
import br.com.vvv.Service.LocalizationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("v1/localization")
@RestController
@Slf4j
public class LocalizationController {

    @Autowired
    private LocalizationService localizationService;
    
    @PostMapping()
    @Transactional
    public ResponseEntity<String> createLocalization(@RequestBody @Valid DataRegisterLocalization dataRegisterLocalization) {
        log.info("[LocalizationController.createLocalization] - [Controller]");
        try {
            localizationService.createLocalization(dataRegisterLocalization);
            return ResponseEntity.ok("Localização criada com sucesso");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao tentar criar a:" + ex.getMessage());
        }
    }
}