package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.dto.product.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductSku> selectProductSkuBySale();

    List<ProductSku> selectProductSkuById(Long id);

    List<ProductSku> selectProductSkuByPage(ProductSkuDto productSkuDto);
}
