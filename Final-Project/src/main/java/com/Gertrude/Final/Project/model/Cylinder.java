package com.Gertrude.Final.Project.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cylinder")
public class Cylinder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String code;
    private String name;
    private String color;
    private BigDecimal weight;
    private BigDecimal capacity;

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuels;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal total_cost;

    @ManyToMany(mappedBy = "cylinders",cascade = CascadeType.ALL)
    private List<CustomerOrder> orders;

    @Override
    public String toString() {
        return "Cylinder{id=" + id + ", code='" + code + "', name='" + name + "', color='" + color + "', weight=" + weight + ", capacity=" + capacity + ", price=" + price + ", quantity=" + quantity + ", total_cost=" + total_cost + "}";
    }


//    public void updateQuantityAndTotalCost(int orderQuantity) {
//        if (quantity < orderQuantity) {
//            throw new InsufficientQuantityException("Order quantity is greater than available quantity in the cylinder.");
//        }
//
//        quantity -= orderQuantity;
//        total_cost = total_cost.subtract(price.multiply(BigDecimal.valueOf(orderQuantity)));
//    }

}



