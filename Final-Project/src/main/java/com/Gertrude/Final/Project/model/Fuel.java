package com.Gertrude.Final.Project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "fuel")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String code;

    private String name;

    @Enumerated(EnumType.STRING)
    private EFuel fuel_type;

    private  String brand;

    @OneToMany(mappedBy = "fuels" ,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Cylinder> cylinders ;

    @Override
    public String toString() {
        return "Fuel{id=" + id + ", code='" + code + "', name='" + name + "', fuel_type=" + fuel_type + ", brand='" + brand + "'}";
    }

}
