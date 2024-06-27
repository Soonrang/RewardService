package com.example.rewardservice.domain.Shop;

import com.example.rewardservice.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "product_category")
    private String category;

    //회사 계정을 따로 만들어 연결
    @Column(name = "product_company")
    private String company;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages;

    @Column(name= "product_description")
    private String description;

}