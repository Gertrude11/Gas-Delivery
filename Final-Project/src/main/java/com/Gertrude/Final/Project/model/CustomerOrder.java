package com.Gertrude.Final.Project.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customer_order")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fullNames;
    private String phone;
    private String email;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "customer_order_fuel",
            joinColumns = @JoinColumn(name = "customer_order_id"),
            inverseJoinColumns = @JoinColumn(name = "fuel_id")
    )
    private List<Cylinder> cylinders;

    private Integer quantity;

    private String destination;

    @CreationTimestamp
    private LocalDate order_date;

    private LocalDate delivery_date;

    private String delivery_hours;

    private BigDecimal total_cost;

    @OneToOne(mappedBy = "customerOrder", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Payment payment;

    private Boolean paid = false;

    public boolean isPaid() {
        return paid;
    }
    public String getCustomerEmail() {
        return email;
    }

}
