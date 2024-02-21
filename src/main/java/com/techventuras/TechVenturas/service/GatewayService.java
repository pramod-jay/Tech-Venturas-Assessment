package com.techventuras.TechVenturas.service;

import com.techventuras.TechVenturas.dto.GatewayDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GatewayService {
    //Create gateway
    ResponseEntity<String> createGateway(GatewayDTO gatewayDTO);

    //Fetch all gateways with connected peripheral devices
    ResponseEntity<List<GatewayDTO>> getGateways();

    //Update IP address of the specific gateway
    ResponseEntity<String> updateIp(GatewayDTO gatewayDTO);

    //Update entire gateway
    ResponseEntity<String> updateGateway(GatewayDTO gatewayDTO);

    //Fetch gateway with connected peripheral devices by the ID
    ResponseEntity<GatewayDTO> getGatewayByID(String serialNumber);

    //Delete gateway
    ResponseEntity<String> deleteGateway(String serialNumber);

    //Check the validity of IP address
    public Boolean isValidIP(String ip);
}
