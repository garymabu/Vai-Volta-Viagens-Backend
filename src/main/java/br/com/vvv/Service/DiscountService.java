package br.com.vvv.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.DataDiscounts;
import br.com.vvv.Domain.DTO.DataRegisterDiscount;
import br.com.vvv.Domain.Entity.Discount;
import br.com.vvv.Repository.DiscountRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public Discount createDiscount(DataRegisterDiscount dataRegisterDiscount) {
        log.info("[DiscountService.createDiscount] - [Service]");
        return discountRepository.save(new Discount(dataRegisterDiscount));
    }
    
    public Page<DataDiscounts> findAllDiscounts(Pageable pageable) {
        log.info("[DiscountService.findAllDiscounts] - [Service]");
        Page<Discount> discounts = discountRepository.findAll(pageable);
        return discounts.map(DataDiscounts::new);
    }

    public List<Discount> findAllDiscounts() {
        var discounts = discountRepository.findAll();
        return discounts;
    }

    public void deleteDiscount(String id) {
        log.info("[DiscountService.deleteDiscount] - [Service]");
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discount not found with id: " + id));

        discountRepository.deleteById(discount.getId());
    }


}
