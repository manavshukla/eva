package com.example.security.service.impl;

import com.example.security.payload.request.ProductRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.security.model.Product;
import com.example.security.repository.ProductRepository;
import com.example.security.service.ProductService;

@RequiredArgsConstructor
@Service
public class ProductsServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Transactional
  public void save(ProductRequest productRequest) {
    Product product = Product.builder()
                          .name(productRequest.getName())
                          .productDesc(productRequest.getProductDesc())
                          .barcode(productRequest.getBarcode())
                          .productType(productRequest.getProductType())
                          .productImg(productRequest.getProductImg())
                          .buyingPrice(productRequest.getBuyingPrice())
                          .category(productRequest.getCategory())
                          .salesPrice(productRequest.getSalesPrice())
                          .quantityUnitType(productRequest.getQuantityUnitType())
                          .build();
    productRepository.save(product);
  }

  @Override
  public Page<Product> list(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  @Override
  public Product getById(Long id) {
    return productRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("No product found"));
  }

  @Override
  public Product update(Long id, ProductRequest request) {
    Product product = getById(id);

    product.setName(request.getName());
    product.setProductDesc(request.getProductDesc());
    product.setBarcode(request.getBarcode());
    product.setProductType(request.getProductType());
    product.setProductImg(request.getProductImg());
    product.setBuyingPrice(request.getBuyingPrice());
    product.setCategory(request.getCategory());
    product.setSalesPrice(request.getSalesPrice());
    product.setQuantityUnitType(request.getQuantityUnitType());
    productRepository.save(product);
    return product;
  }

}
