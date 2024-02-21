package com.techventuras.TechVenturas.dto;

import com.techventuras.TechVenturas.entity.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GatewayDTO {
    private String serialNumber;
    private String name;
    private String ipAddress;
    private List<Device> devices;
}
