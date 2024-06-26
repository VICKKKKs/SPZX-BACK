package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> selectAll(ProductDto productDto);

    void save(Product product);

    Product selectById(Long id);

    void updateById(Product product);

    void deleteById(Long id);
}
