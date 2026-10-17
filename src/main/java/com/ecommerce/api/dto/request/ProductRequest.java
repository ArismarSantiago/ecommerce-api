package com.ecommerce.api.dto.request;

import com.ecommerce.api.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
@NotBlank(message = "campo obrigatorio")

String name,
@NotBlank(message = "A descrição é obrigatoria")

String description,
@NotNull(message = "Adicione um valor ao produto")
@Positive(message = " valor deve ser superior a 0")
BigDecimal price,
@Positive(message = "A quantidade precisa ser maior que 0")
@NotNull(message = "Adicione pelo 1 em estoque")
Integer stock,
@NotNull(message = "Digite o id da categoria do produto")
Long categoryId,
@Enumerated(EnumType.STRING)
ProductStatus status
) {

}
