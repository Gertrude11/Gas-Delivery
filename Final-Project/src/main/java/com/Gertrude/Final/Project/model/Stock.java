package com.Gertrude.Final.Project.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
  //  private String name;
    @ManyToOne
    @JoinColumn(name = "cylinder_id")
    private Cylinder cylinder;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal total_cost;
}
