package com.techventuras.TechVenturas.service;

import com.techventuras.TechVenturas.dto.GatewayDTO;
import com.techventuras.TechVenturas.entity.Device;
import com.techventuras.TechVenturas.entity.Gateway;
import com.techventuras.TechVenturas.repo.DeviceRepo;
import com.techventuras.TechVenturas.repo.GatewayRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class GatewayServiceImpl implements GatewayService{

    @Autowired
    private GatewayRepo gatewayRepo;

    @Autowired
    private DeviceRepo deviceRepo;

    @Autowired
    private ModelMapper modelMapper;

    //Create gateway
    @Override
    public ResponseEntity<String> createGateway(GatewayDTO gatewayDTO){
        try {
            if(gatewayDTO.getSerialNumber().isEmpty()){
                return new ResponseEntity<>("Serial number cannot be null", HttpStatus.BAD_REQUEST);
            }

            if(gatewayDTO.getName().isEmpty()){
                return new ResponseEntity<>("Gateway name cannot be null", HttpStatus.BAD_REQUEST);
            }

            if(gatewayDTO.getIpAddress().isEmpty()) {
                return new ResponseEntity<>("Gateway IP address cannot be null", HttpStatus.BAD_REQUEST);
            }

            if(gatewayRepo.existsById(gatewayDTO.getSerialNumber())){
                return new ResponseEntity<>("Another gateway is already available from this serial number", HttpStatus.CONFLICT);
            }

            if(isValidIP(gatewayDTO.getIpAddress())){
                return new ResponseEntity<>("IP is not a valid IPv4 address", HttpStatus.BAD_REQUEST);
            }

            Gateway gateway = gatewayRepo.save(modelMapper.map(gatewayDTO, Gateway.class));
            System.out.println(gateway);

            return new ResponseEntity<>("Gateway has been created successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Fetch all gateways with connected peripheral devices
    @Override
    public ResponseEntity<List<GatewayDTO>> getGateways(){
        try {
            List<Gateway> gateways = gatewayRepo.findAll();

            System.out.println(gateways);

            List<GatewayDTO> gatewayDTOS = new ArrayList<>();

            //Get peripheral devices of each gateway and map them to GatewayDTO
            for(Gateway gateway : gateways){
                List<Device> devices = deviceRepo.fetchDeviceBySerialNumber(gateway.getSerialNumber());
                GatewayDTO gatewayDTO = modelMapper.map(gateway, GatewayDTO.class);
                gatewayDTO.setDevices(devices);
                gatewayDTOS.add(gatewayDTO);
            }

            return new ResponseEntity<>(gatewayDTOS, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Update IP address of the specific gateway
    @Override
    public ResponseEntity<String> updateIp(GatewayDTO gatewayDTO){
        try {
            if(gatewayDTO.getSerialNumber().isEmpty()){
                return new ResponseEntity<>("Serial number cannot be null", HttpStatus.BAD_REQUEST);
            }

            if(gatewayDTO.getIpAddress().isEmpty()) {
                return new ResponseEntity<>("Gateway IP address cannot be null", HttpStatus.BAD_REQUEST);
            }

            if(!(gatewayRepo.existsById(gatewayDTO.getSerialNumber()))){
                return new ResponseEntity<>("Gateway not found", HttpStatus.NOT_FOUND);
            }

            if(isValidIP(gatewayDTO.getIpAddress())){
                return new ResponseEntity<>("IP is not a valid IPv4 address", HttpStatus.BAD_REQUEST);
            }

            gatewayRepo.updateGatewayOfDevice(gatewayDTO.getSerialNumber(), gatewayDTO.getIpAddress());
            return new ResponseEntity<>("IP address of gateway has been updated successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Update entire gateway
    @Override
    public ResponseEntity<String> updateGateway(GatewayDTO gatewayDTO){
        try {
            if(gatewayDTO.getSerialNumber().isEmpty()){
                return new ResponseEntity<>("Serial number cannot be null", HttpStatus.BAD_REQUEST);
            }

            if(gatewayDTO.getName().isEmpty()){
                return new ResponseEntity<>("Gateway name cannot be null", HttpStatus.BAD_REQUEST);
            }

            if(gatewayDTO.getIpAddress().isEmpty()) {
                return new ResponseEntity<>("Gateway IP address cannot be null", HttpStatus.BAD_REQUEST);
            }

            if((!gatewayRepo.existsById(gatewayDTO.getSerialNumber()))){
                return new ResponseEntity<>("Gateway not found", HttpStatus.NOT_FOUND);
            }

            if(isValidIP(gatewayDTO.getIpAddress())){
                return new ResponseEntity<>("IP is not a valid IPv4 address", HttpStatus.BAD_REQUEST);
            }

            Gateway gateway = gatewayRepo.save(modelMapper.map(gatewayDTO, Gateway.class));
            System.out.println(gateway);

            return new ResponseEntity<>("Gateway has been updated successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Fetch gateway with connected peripheral devices by the ID
    @Override
    public ResponseEntity<GatewayDTO> getGatewayByID(String serialNumber){
        try {
            Optional<Gateway> gateway = gatewayRepo.findById(serialNumber);

            GatewayDTO gatewayDTO = new GatewayDTO();

            if(gateway.isPresent()){
                gatewayDTO = modelMapper.map(gateway.get(), GatewayDTO.class);
                gatewayDTO.setDevices(deviceRepo.fetchDeviceBySerialNumber(serialNumber));
            }

            return new ResponseEntity<>(gatewayDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Delete gateway
    @Override
    public ResponseEntity<String> deleteGateway(String serialNumber){
        try {
            if ((!gatewayRepo.existsById(serialNumber))){
                return new ResponseEntity<>("Gateway not found", HttpStatus.NOT_FOUND);
            }

            //Disconnect connected peripheral devices of deleting gateway(Set null)
            List<Device> devices = deviceRepo.fetchDeviceBySerialNumber(serialNumber);
            for(Device device : devices){
                deviceRepo.updateGatewayOfDevice(device.getUID(), null);
            }

            gatewayRepo.deleteById(serialNumber);
            return new ResponseEntity<>("Gateway has been deleted successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Check the validity of IP address
    @Override
    public Boolean isValidIP(String ip){
        String IPRegex = "(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}";
        return !(Pattern.matches(IPRegex, ip));
    }
}