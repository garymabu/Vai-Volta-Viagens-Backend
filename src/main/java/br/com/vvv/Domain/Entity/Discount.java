package br.com.vvv.Domain.Entity;

import br.com.vvv.Domain.DTO.DataRegisterDiscount;
import br.com.vvv.Helpers.DataHelper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "discount")
@Table(name = "discount")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private float value;

    public Discount(DataRegisterDiscount dataRegisterDiscount) {
        this.id = DataHelper.generatedUuid().toString();
        this.value = dataRegisterDiscount.value();
    }
    
}