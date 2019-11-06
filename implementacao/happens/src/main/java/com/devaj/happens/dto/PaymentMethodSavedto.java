package com.devaj.happens.dto;

import com.devaj.happens.model.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PaymentMethodSavedto {

    @NotNull(message = "Metodo de pagamento requerido")
    private PaymentMethod payment_method;

    public PaymentMethod transformToPaymentMethod(){
        return  payment_method;
    }
}
