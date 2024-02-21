package com.techventuras.TechVenturas.controller;

import com.techventuras.TechVenturas.dto.GatewayDTO;
import com.techventuras.TechVenturas.service.GatewayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "gateway")
@CrossOrigin
public class GatewayController{

    @Autowired
    private GatewayServiceImpl gatewayServiceImpl;

    //Create gateway
    @PostMapping("/createGateway")
    public ResponseEntity<String> postGateway(@RequestBody GatewayDTO gatewayDTO){
        return gatewayServiceImpl.createGateway(gatewayDTO);
    }

    //Fetch all gateways
    @GetMapping( "/getGateways")
    public ResponseEntity<List<GatewayDTO>> getGateway(){
       return gatewayServiceImpl.getGateways();
    }

    //Update IP address of specific gateway
    @PatchMapping("/updateIp")
    public ResponseEntity<String> updateIP(@RequestBody GatewayDTO gatewayDTO){
        return gatewayServiceImpl.updateIp(gatewayDTO);
    }

    //Update entire gateway
    @PutMapping("/updateGateway")
    public ResponseEntity<String> updateGateway(@RequestBody GatewayDTO gatewayDTO){
        return gatewayServiceImpl.updateGateway(gatewayDTO);
    }

    //Fetch gateway by the serial number
    @GetMapping("/getGatewayById/{sNo}")
    public ResponseEntity<GatewayDTO> getGatewayByID(@PathVariable(name = "sNo")String serialNumber){
        return gatewayServiceImpl.getGatewayByID(serialNumber);
    }

    //Delete gateway
    @DeleteMapping("/deleteGateway/{sNo}")
    public ResponseEntity<String> deleteGateway(@PathVariable(name = "sNo")String serialNumber){
        return gatewayServiceImpl.deleteGateway(serialNumber);
    }

}
