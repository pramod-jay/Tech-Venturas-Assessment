package com.techventuras.TechVenturas.service;

import com.techventuras.TechVenturas.dto.DeviceDTO;
import com.techventuras.TechVenturas.entity.Device;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    //Create peripheral device
    ResponseEntity<String> creteDevice(DeviceDTO deviceDTO);

    //Fetch all the devices
    ResponseEntity<List<Device>> getDevices();

    //Update status of the specific device
    ResponseEntity<String> updateStatus(DeviceDTO deviceDTO);

    //Update gateway of the specific device
    ResponseEntity<String> updateGateway(DeviceDTO deviceDTO);

    //Update entire device
    ResponseEntity<String> updateDevice(DeviceDTO deviceDTO);

    //Fetch device by the ID
    ResponseEntity<Optional<Device>> getDeviceByID(Integer uid);

    //Delete device
    ResponseEntity<String> deleteDevice(Integer uid);
}
