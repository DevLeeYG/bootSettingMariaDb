package com.springboot.jpa.data.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

    private String name;
    private int price;
    private int stock;




}
