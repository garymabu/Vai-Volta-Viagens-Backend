package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Discount;

public record DataDiscounts(
    String id,
    float value
) {
    public DataDiscounts(Discount discount) {
        this(discount.getId(), discount.getValue());
    }
}
