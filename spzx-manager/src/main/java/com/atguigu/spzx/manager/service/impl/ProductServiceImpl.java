package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductDetailsMapper;
import com.atguigu.spzx.manager.mapper.ProductMapper;
import com.atguigu.spzx.manager.mapper.ProductSkuMapper;
import com.atguigu.spzx.manager.mapper.ProductUnitMapper;
import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductSkuMapper productSkuMapper;

    @Autowired
    ProductDetailsMapper productDetailsMapper;

    @Override
    public PageInfo<Product> findBypage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page, limit);
        List<Product> productList =  productMapper.selectAll(productDto);
        return new PageInfo<>(productList);
    }

    @Override
    public void save(Product product) {
        product.setStatus(0);
        product.setAuditStatus(0);
        product.setAuditMessage("未审核");
        productMapper.save(product);


        // 保存商品sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        int i  = 0;
        for (ProductSku productSku : productSkuList) {
            productSku.setProductId(product.getId());
            productSku.setSkuCode(product.getId() + "_" + i);
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            productSku.setSaleNum(0);
            productSku.setStatus(0);
            i++;
            productSkuMapper.save(productSku);
        }

        // 保存商品详情数据
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);
    }

    @Override
    public Product getById(Long id) {
        // 获取商品
        Product product = productMapper.selectById(id);
        // sku
        List<ProductSku> productSkuList = productSkuMapper.selectById(id);
        product.setProductSkuList(productSkuList);
        // detail
        ProductDetails productDetails = productDetailsMapper.selectById(id);
        product.setDetailsImageUrls(productDetails.getImageUrls());
        return product;
    }

    @Override
    public void updateById(Product product) {
        // product
        productMapper.updateById(product);
        // sku
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku ->{
                productSkuMapper.updateById(productSku);
        });
        // details
        ProductDetails productDetails = productDetailsMapper.selectById(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.updateById(productDetails);

    }

    @Override
    public void deleteById(Long id) {
        // product
        productMapper.deleteById(id);
        // sku
        productSkuMapper.deleteById(id);
        // details
        productDetailsMapper.deleteById(id);

    }

    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if(auditStatus == 1 ){
            product.setAuditStatus(1);
            product.setAuditMessage("审批通过");
        }else {
            product.setAuditStatus(-1);
            product.setAuditMessage("审批不通过");
        }
        productMapper.updateById(product);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if(status == 1 ){
            product.setStatus(1);
        }else {
            product.setStatus(-1);
        }
        productMapper.updateById(product);
    }


}
