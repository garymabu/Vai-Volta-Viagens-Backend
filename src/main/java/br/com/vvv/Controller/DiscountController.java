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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vvv.Domain.DTO.DataDiscounts;
import br.com.vvv.Domain.DTO.DataRegisterDiscount;
import br.com.vvv.Service.DiscountService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("v1/discount")
@RestController
@Slf4j
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping()
    @Transactional
    public ResponseEntity<String> createDiscount(@RequestBody @Valid DataRegisterDiscount dataRegisterDiscount) {
        log.info("[DiscountController.createDiscount] - [Controller]");
        try {
            discountService.createDiscount(dataRegisterDiscount);
            return ResponseEntity.ok("Desconto criado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao tentar criar: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<DataDiscounts>> findAllDiscounts(@PageableDefault(size = 10) Pageable pageable) {
        log.info("[DiscountController.findAllDiscounts] - [Controller]");
        Page<DataDiscounts> discounts = discountService.findAllDiscounts(pageable);
        return ResponseEntity.ok().body(discounts);
    } 

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteDiscount(@PathVariable String id) {
        log.info("[DiscountController.deleteDiscount] - [Controller]");
        try {
            discountService.deleteDiscount(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao tentar excluir: " + e.getMessage());
        }
    }
    
}