package com.atguigu.spzx.model.dto.product;

import lombok.Data;

@Data
public class ProductSkuDto {

    private String keyword;

    private Long brandId;

    private Long category1Id;

    private Long category2Id;

    private Long category3Id;

    private Integer order = 1;

}