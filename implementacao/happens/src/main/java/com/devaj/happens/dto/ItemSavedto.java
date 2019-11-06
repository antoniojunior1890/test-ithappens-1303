package com.devaj.happens.dto;

import com.devaj.happens.model.Item;
import com.devaj.happens.model.Stock;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemSavedto {

    @NotNull
    private Stock stock;

    @NotNull(message = "Quantidade requeride")
    @DecimalMin(value = "1", message = "Quantidade precisa ser maior que 0")
    private Integer amount;

    @NotNull(message = "Preço requerido")
    private Double price;

    public Item transformToItem(){
        Item item = new Item(null, this.stock, this.amount, this.price, null);

        return item;
    }

}
