package com.techventuras.TechVenturas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceDTO {
    private Integer UID = 1;
    private String vendor;
    private LocalDate dateCreated;
    private Boolean online = false;
    private String serialNumber;
}
