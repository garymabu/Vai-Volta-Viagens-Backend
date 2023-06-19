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

import br.com.vvv.Domain.DTO.DataOutlet;
import br.com.vvv.Domain.DTO.DataRegisterOutlet;
import br.com.vvv.Domain.DTO.DataUpdateOutlet;
import br.com.vvv.Service.OutletService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("v1/outlet")
@RestController
@Slf4j
public class OutletController {

    @Autowired
    private OutletService outletService;
    
    @PostMapping()
    @Transactional
    public ResponseEntity<String> createOutlet(@RequestBody @Valid DataRegisterOutlet dataRegisterOutlet) {
        log.info("[OutletController.createOutlet] - [Controller]");
        try {
            outletService.createOutlet(dataRegisterOutlet);
            return ResponseEntity.created(null).body("Ponto de Venda criado com sucesso");
        } catch (Exception exc) {
            return ResponseEntity.badRequest().body("Erro ao tentar criar o: " + exc.getMessage());
        }
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<String> updateOutlet(@RequestBody @Valid DataUpdateOutlet dataUpdateOutlet) {
        log.info("[OutletController.updateOutlet] - [Controller]");
        try {
            outletService.updateOutlet(dataUpdateOutlet);
            return ResponseEntity.ok("Ponto de Venda atualizado com sucesso");
        } catch (IllegalArgumentException iException) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao tentar atualizar o: " + ex.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Page<DataOutlet>> findAllOutlets(@PageableDefault(size = 10) Pageable pageable) {
        log.info("[OutletController.findAllOutlets] - [Controller]");
        Page<DataOutlet> outlets = outletService.findAllOutlets(pageable);
        return ResponseEntity.ok().body(outlets);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteOutlet(@PathVariable String id) {
        log.info("[OutletController.deleteOutlet] - [Controller]");
        try {
            outletService.deleteOutlet(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException iException) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao tentar excluir o Ponto de Venda: " + ex.getMessage());
        }
    }
}