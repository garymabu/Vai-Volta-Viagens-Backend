package br.com.vvv.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

import br.com.vvv.Domain.DTO.DataModal;
import br.com.vvv.Domain.DTO.DataRegisterModal;
import br.com.vvv.Domain.DTO.DataUpdateModal;
import br.com.vvv.Service.ModalService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/modal")
@Slf4j
public class ModalController {

    @Autowired
    private ModalService modalService;
    
    @PostMapping()
    @Transactional
    public ResponseEntity<String> createModal(@RequestBody @Valid DataRegisterModal modal) {
        log.info("[ModalController.createModal] - [Controller]");
        try {
            modalService.createModal(modal);
            return ResponseEntity.status(HttpStatus.CREATED).body("Modal Criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao tentar criar o: " + e.getMessage());
        }
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<String> updateModal(@RequestBody @Valid DataUpdateModal updatedModalData) {
        log.info("[ModalController.updateModal] - [Controller]");
        try {
            modalService.updateModal(updatedModalData);
            return ResponseEntity.ok("Modal atualizado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao tentar atualizar o modal: " + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Page<DataModal>> findAllModals(@PageableDefault(size = 10) Pageable pageable) {
        log.info("[ModalController.findAllModals] - [Controller]");
        Page<DataModal> modals = modalService.findAllModals(pageable);
        return ResponseEntity.ok(modals);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteModal(@PathVariable String id) {
        log.info("[ModalController.deleteModal] - [Controller]");
        try {
            modalService.deleteModal(id);
            return ResponseEntity.ok("Modal excluído com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao tentar excluir o modal: " + e.getMessage());
        }
    }
    
}