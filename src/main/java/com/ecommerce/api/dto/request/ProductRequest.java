package com.ecommerce.api.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
@NotBlank(message = "campo obrigatorio")
@Min(3) @Max(30)
String name,
@NotBlank(message = "A descrição é obrigatoria")
@Min(10) @Max(400)
String description,
@NotNull(message = "Adicione um valor ao produto")
@Positive(message = " valor deve ser superior a 0")
BigDecimal price,
@Positive(message = "A quantidade precisa ser maior que 0")
@NotNull(message = "Adicione pelo 1 em estoque")
Integer stock
) {

}
