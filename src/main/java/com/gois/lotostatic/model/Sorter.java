package com.gois.lotostatic.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
@Entity
public class Sorter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private double concourse;
    @Column(unique = true, nullable = false)
    private LocalDate drawDate;
    private String numberOne;
    private String numberTwo;
    private String numberThree;
    private String numberFour;
    private String numberFive;
    private String numberSix;

}
