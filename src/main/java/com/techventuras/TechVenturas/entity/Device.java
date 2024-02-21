package com.techventuras.TechVenturas.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "device")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer UID = 1;
    private String vendor;
    private LocalDate dateCreated;
    private Boolean online = false;

    @ManyToOne()
    @JoinColumn(name = "serialNumber")
    private Gateway gateway;
}
