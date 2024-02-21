package com.techventuras.TechVenturas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "gateway")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Gateway {
    @Id
    private String serialNumber;
    private String name;
    @Column(length = 15)
    private String ipAddress;
}
