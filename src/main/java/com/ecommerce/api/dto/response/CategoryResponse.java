package com.ecommerce.api.dto.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class CategoryResponse extends RepresentationModel<CategoryResponse> {
       private Long id;
        private String name;
}
