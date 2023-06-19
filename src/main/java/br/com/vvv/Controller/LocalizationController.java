package br.com.vvv.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vvv.Domain.DTO.DataLocalizations;
import br.com.vvv.Domain.DTO.DataUpdateLocalization;
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

    @GetMapping()
    public ResponseEntity<Page<DataLocalizations>> findAllLocalizations(@PageableDefault(size = 10) Pageable pageable) {
        log.info("[LocalizationController.findAllLocalizations] - [Controller]");
        Page<DataLocalizations> localizations = localizationService.findAllLocalizations(pageable);
        return ResponseEntity.ok().body(localizations);
    }

    @PutMapping("/{id}")
    @Transactional()
    public ResponseEntity<String> updateLocalization(@PathVariable String id, @RequestBody DataUpdateLocalization dataUpdateLocalization) {
        log.info("[LocalizationController.updateLocalization] - [Controller]");
        try {
            localizationService.updateLocalization(id, dataUpdateLocalization);
            return ResponseEntity.ok("Localização atualizada com sucesso");
        } catch (IllegalArgumentException iException) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao tentar atualizar: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional()
    public ResponseEntity<String> deleteLocalization(@PathVariable String id) {
        log.info("[LocalizationController.deleteLocalization] - [Controller]");
        try {
            localizationService.deleteLocalization(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException iException) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao tentar excluir: " + ex.getMessage());
        }
    }

}