package com.example.security.service.impl;

import com.example.security.model.Product;
import com.example.security.model.SalesDetail;
import com.example.security.model.User;
import com.example.security.payload.request.SalesDetailsRequest;
import com.example.security.payload.request.SalesRequest;
import com.example.security.repository.ProductRepository;
import com.example.security.repository.SaleDetailsRepository;
import com.example.security.service.ProductService;
import com.example.security.utils.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.security.model.Sale;
import com.example.security.repository.SaleRepository;
import com.example.security.service.SalesService;

import java.beans.Transient;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SalesServiceImpl implements SalesService {

  private final SaleRepository saleRepository;
  private final SaleDetailsRepository saleDetailsRepository;
  private final ProductService productService;
  private final AuthUtil authUtil;

  @Transactional
  public void save(SalesRequest salesRequest) {
    Sale sale = Sale.builder()
                    .modeOfPayment(salesRequest.getModeOfPayment())
                    .cashReceived(salesRequest.getCashReceived())
                    .totalAmount(salesRequest.getTotalAmount())
                    .totalProfit(salesRequest.getTotalProfit())
                    .createdBy(authUtil.getLoggedInUser().getId())
                    .build();

    saleRepository.save(sale);
    for (SalesDetailsRequest item : salesRequest.getDetails()) {

      Product product = productService.getById(item.getProductId());

      SalesDetail detail = SalesDetail.builder()
                               .price(item.getPrice())
                               .product(product)
                               .sale(sale)
                               .discount(item.getDiscount())
                               .profit(item.getProfit())
                               .quantity(item.getQuantity())
                               .build();

      saleDetailsRepository.save(detail);

    }
  }

  @Override
  public Page<Sale> list(Pageable pageable) {
    return saleRepository.findAll(pageable);
  }

}
