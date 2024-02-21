package com.techventuras.TechVenturas.controller;

import com.techventuras.TechVenturas.dto.DeviceDTO;
import com.techventuras.TechVenturas.entity.Device;
import com.techventuras.TechVenturas.service.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "device")
@CrossOrigin
public class DeviceController {
    @Autowired
    private DeviceServiceImpl deviceServiceImpl;

    //Create peripheral device
    @PostMapping(value = "/createDevice")
    public ResponseEntity<String> createDevice(@RequestBody DeviceDTO deviceDTO){
        return deviceServiceImpl.creteDevice(deviceDTO);
    }

    //Fetch all peripheral devices
    @GetMapping(value = "/getDevices")
    public ResponseEntity<List<Device>> getDevices(){
        return deviceServiceImpl.getDevices();
    }

    //Update status(Online/Offline) of the specific device
    @PatchMapping(value = "/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestBody DeviceDTO deviceDTO){
        return deviceServiceImpl.updateStatus(deviceDTO);
    }

    //Update connected gateway of the specific device
    @PatchMapping(value = "/updateGateway")
    public ResponseEntity<String> updateGateway(@RequestBody DeviceDTO deviceDTO){
        return deviceServiceImpl.updateGateway(deviceDTO);
    }

    //Update entire peripheral device
    @PutMapping(value = "/updateDevice")
    public ResponseEntity<String> updateDevice(@RequestBody DeviceDTO deviceDTO){
        return deviceServiceImpl.updateDevice(deviceDTO);
    }

    //Fetch peripheral device by the UID
    @GetMapping(value = "/getDeviceById/{uid}")
    public ResponseEntity<Optional<Device>> getDeviceById(@PathVariable(name = "uid")Integer uid){
        return deviceServiceImpl.getDeviceByID(uid);
    }

    //Delete specific peripheral device
    @DeleteMapping(value = "/deleteDevice/{uid}")
    public ResponseEntity<String> deleteDevice(@PathVariable(name = "uid")Integer uid){
        return deviceServiceImpl.deleteDevice(uid);
    }
}
