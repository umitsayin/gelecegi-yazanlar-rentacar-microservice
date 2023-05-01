package com.turkcell.inventoryservice.entities;

import com.turkcell.inventoryservice.entities.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int modelYear;
    private String plate;

    @Enumerated(EnumType.STRING)
    private State state;
    private int dailyPrice;

    @ManyToOne
    private Model model;
}
