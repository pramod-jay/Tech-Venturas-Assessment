package com.techventuras.TechVenturas.repo;

import com.techventuras.TechVenturas.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepo extends JpaRepository<Device, Number> {
    @Query(value = "SELECT * FROM techventuras.device WHERE serial_number = ?1", nativeQuery = true)
    List<Device> fetchDeviceBySerialNumber(String serialNumber);

    @Modifying
    @Query(value = "UPDATE `techventuras`.`device` SET `online` = ?2 WHERE (`uid` = ?1)", nativeQuery = true)
    void updateStatusOfDevice(Integer uid, Boolean status);

    @Modifying
    @Query(value = "UPDATE `techventuras`.`device` SET `serial_number` = ?2 WHERE (`uid` = ?1)", nativeQuery = true)
    void updateGatewayOfDevice(Integer uid, String serialNumber);

    @Query(value = "SELECT COUNT(uid) FROM techventuras.device WHERE serial_number= ?1", nativeQuery = true)
    Integer fetchDeviceCount(String serialNumber);
}

;