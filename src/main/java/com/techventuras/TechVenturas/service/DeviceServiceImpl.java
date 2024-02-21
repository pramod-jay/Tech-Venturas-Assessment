package com.techventuras.TechVenturas.service;

import com.techventuras.TechVenturas.dto.DeviceDTO;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private DeviceRepo deviceRepo;

    @Autowired
    private GatewayRepo gatewayRepo;

    @Autowired
    private ModelMapper modelMapper;

    //Create peripheral device
    @Override
    public ResponseEntity<String> creteDevice(DeviceDTO deviceDTO){
        try {
            if(deviceDTO.getVendor().isEmpty()){
                return new ResponseEntity<>("Vendor cannot be null", HttpStatus.BAD_REQUEST);
            }

            if(deviceDTO.getOnline()==null){
                return new ResponseEntity<>("Set online status as true or false", HttpStatus.BAD_REQUEST);
            }

            Gateway gateway = null;

            if(!(deviceDTO.getSerialNumber().isEmpty())){
                gateway = gatewayRepo.fetchGatewayByID(deviceDTO.getSerialNumber());
                if(gateway==null){
                    return new ResponseEntity<>("Gateway not found", HttpStatus.NOT_FOUND);
                }

                Integer deviceCount = deviceRepo.fetchDeviceCount(deviceDTO.getSerialNumber());
                if(deviceCount==10){
                    return new ResponseEntity<>("Cannot connect devices more than 10 to the device -> "+deviceDTO.getSerialNumber(), HttpStatus.BAD_REQUEST);
                }
            }

            Device device = modelMapper.map(deviceDTO, Device.class);
            device.setDateCreated(LocalDate.now());
            device.setGateway(gateway);

            deviceRepo.save(device);

            return new ResponseEntity<>("Device has been created successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Fetch all the devices
    @Override
    public ResponseEntity<List<Device>> getDevices(){
        try {
            List<Device> devices = deviceRepo.findAll();
            return new ResponseEntity<>(devices, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Update status of the specific device
    @Override
    public ResponseEntity<String> updateStatus(DeviceDTO deviceDTO){
        try {
            if(!(deviceRepo.existsById(deviceDTO.getUID()))){
                return new ResponseEntity<>("Device not found", HttpStatus.NOT_FOUND);
            }

            deviceRepo.updateStatusOfDevice(deviceDTO.getUID(), deviceDTO.getOnline());
            return new ResponseEntity<>("Status of device has been updated successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Update gateway of the specific device
    @Override
    public ResponseEntity<String> updateGateway(DeviceDTO deviceDTO){
        try {
            if(!(deviceRepo.existsById(deviceDTO.getUID()))){
                return new ResponseEntity<>("Device not found", HttpStatus.NOT_FOUND);
            }

            if(!(gatewayRepo.existsById(deviceDTO.getSerialNumber()))){
                return new ResponseEntity<>("Gateway not found", HttpStatus.NOT_FOUND);
            }

            Integer deviceCount = deviceRepo.fetchDeviceCount(deviceDTO.getSerialNumber());
            if(deviceCount==10){
                return new ResponseEntity<>("Cannot connect devices more than 10 to the device -> "+deviceDTO.getSerialNumber(), HttpStatus.BAD_REQUEST);
            }

            deviceRepo.updateGatewayOfDevice(deviceDTO.getUID(), deviceDTO.getSerialNumber());
            return new ResponseEntity<>("Gateway of device has been updated successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Update entire device
    @Override
    public ResponseEntity<String> updateDevice(DeviceDTO deviceDTO){
        try {
            if(!(deviceRepo.existsById(deviceDTO.getUID()))){
                return new ResponseEntity<>("Device not found", HttpStatus.ACCEPTED);
            }

            if(deviceDTO.getVendor().isEmpty()){
                return new ResponseEntity<>("Vendor cannot be null", HttpStatus.BAD_REQUEST);
            }

            if(deviceDTO.getOnline()==null){
                return new ResponseEntity<>("Set online status as true or false", HttpStatus.BAD_REQUEST);
            }

            Gateway gateway = null;

            if(!(deviceDTO.getSerialNumber().isEmpty())){
                gateway = gatewayRepo.fetchGatewayByID(deviceDTO.getSerialNumber());
                if(gateway==null){
                    return new ResponseEntity<>("Gateway not found", HttpStatus.NOT_FOUND);
                }

                Integer deviceCount = deviceRepo.fetchDeviceCount(deviceDTO.getSerialNumber());
                if(deviceCount==10){
                    return new ResponseEntity<>("Cannot connect devices more than 10 to the device -> "+deviceDTO.getSerialNumber(), HttpStatus.BAD_REQUEST);
                }
            }

            Device device = modelMapper.map(deviceDTO, Device.class);
            device.setDateCreated(LocalDate.now());
            device.setGateway(gateway);


            deviceRepo.save(device);
            return new ResponseEntity<>("Device has been updated successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Fetch device by the ID
    @Override
    public ResponseEntity<Optional<Device>> getDeviceByID(Integer uid){
        try {
            Optional<Device> device = deviceRepo.findById(uid);
            return new ResponseEntity<>(device, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Delete device
    @Override
    public ResponseEntity<String> deleteDevice(Integer uid){
        try {
            if((!deviceRepo.existsById(uid))){
                return new ResponseEntity<>("Device not found", HttpStatus.NOT_FOUND);
            }

            deviceRepo.deleteById(uid);
            return new ResponseEntity<>("Device has been deleted successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}