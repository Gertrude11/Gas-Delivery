package com.Gertrude.Final.Project.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "order_id")

    private CustomerOrder customerOrder;

    @Enumerated(EnumType.STRING)
    private EPayment payment_mode;

    private String payment_number;

    @CreationTimestamp
    private LocalDate payment_date;

    private Boolean status;

}
