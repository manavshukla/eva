package com.example.security.service.impl;

import com.example.security.model.Product;
import com.example.security.model.SalesDetail;
import com.example.security.model.Shop;
import com.example.security.payload.request.SalesDetailsRequest;
import com.example.security.payload.request.SalesRequest;
import com.example.security.repository.ProductRepository;
import com.example.security.repository.SaleDetailsRepository;
import com.example.security.utils.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.security.model.Sale;
import com.example.security.repository.SaleRepository;
import com.example.security.service.SalesService;

import java.beans.Transient;

@RequiredArgsConstructor
@Service
public class SalesServiceImpl implements SalesService {


    private final SaleRepository saleRepository;
    private final SaleDetailsRepository saleDetailsRepository;
    private final AuthUtil authUtil;
    private final ProductRepository productRepository;

    @Transient
    public void save(SalesRequest salesRequest) {
        Shop shop = authUtil.getLoggedInUser();

        Sale sale = Sale.builder()
                .modeOfPayment(salesRequest.getModeOfPayment())
                .cashReceived(salesRequest.getCashReceived())
                .totalAmount(salesRequest.getTotalAmount())
                .shop(shop)
                .userId(salesRequest.getUserId())
                .totalProfit(salesRequest.getTotalProfit())
                .build();

        saleRepository.save(sale);
        for (SalesDetailsRequest item : salesRequest.getDetails()) {

            Product product = productRepository.findByIdAndShopId(item.getProductId(), shop.getId())
                    .orElseThrow(() -> new RuntimeException(String.format("product does not exist for the shop %s with id %d", shop.getId(), item.getProductId())));

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
        Long shopId = authUtil.getLoggedInShopId();
        return saleRepository.findAllByShopId(shopId, pageable);
    }

}
