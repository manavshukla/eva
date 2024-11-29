package com.example.security.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "procurements")
public class Procurement extends BaseShopEntity{

    private Double totalAmount;
    private String modeOfPayment;
    private LocalDate purchaseDate;

    @OneToMany(mappedBy = "procurement")
    private Set<ProcurementDetail> details;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "distributor_id", referencedColumnName = "id")
    Distributor distributor;

    private Long createdBy;
}