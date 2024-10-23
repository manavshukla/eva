package com.example.security.service.impl;

import com.example.security.model.Distributor;
import com.example.security.model.Procurement;
import com.example.security.model.ProcurementDetail;
import com.example.security.model.Product;
import com.example.security.model.User;
import com.example.security.payload.request.ProcurementDetailsRequest;
import com.example.security.payload.request.ProcurementRequest;
import com.example.security.repository.ProcurementDetailsRepository;
import com.example.security.repository.ProcurementRepository;
import com.example.security.repository.ProductRepository;
import com.example.security.service.ProcurementService;
import com.example.security.service.ProductService;
import com.example.security.utils.AuthUtil;
import java.beans.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProcurementServiceImpl implements ProcurementService {

  private final ProcurementRepository procurementRepository;
  private final ProcurementDetailsRepository procurementDetailsRepository;
  private final ProductService productService;

  @Transient
  public void save(ProcurementRequest procurementRequest) {

    //TODO: once we have distributor we can set this from there
    Distributor distributor = null;

    Procurement procurement = Procurement.builder()
                                  .modeOfPayment(procurementRequest.getModeOfPayment())
                                  .totalAmount(procurementRequest.getTotalAmount())
                                  .purchaseDate(procurementRequest.getPurchaseDate())
                                  .distributor(distributor)
                                  .build();

    procurementRepository.save(procurement);
    for (ProcurementDetailsRequest item : procurementRequest.getDetails()) {

      Product product = productService.getById(item.getProductId());

      ProcurementDetail detail = ProcurementDetail.builder()
                                     .quantity(item.getQuantity())
                                     .product(product)
                                     .procurement(procurement)
                                     .salePrice(item.getSalePrice())
                                     .buyingPrice(item.getBuyingPrice())
                                     .totalWholesalePrice(item.getTotalWholesalePrice())
                                     .totalProfit(item.getTotalProfit())
                                     .margin(item.getMargin())

                                     .build();

      procurementDetailsRepository.save(detail);

    }
  }

  @Override
  public Page<Procurement> list(Pageable pageable) {
    return procurementRepository.findAll(pageable);
  }

}
