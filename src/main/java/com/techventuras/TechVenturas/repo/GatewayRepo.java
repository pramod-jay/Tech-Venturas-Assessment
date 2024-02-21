package com.techventuras.TechVenturas.repo;

import com.techventuras.TechVenturas.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GatewayRepo extends JpaRepository<Gateway, String> {
    @Query(value = "SELECT * FROM techventuras.gateway WHERE serial_number = ?1", nativeQuery = true)
    Gateway fetchGatewayByID(String serialNumber);

    @Modifying
    @Query(value = "UPDATE `techventuras`.`gateway` SET `ip_address` = ?2 WHERE (`serial_number` = ?1)", nativeQuery = true)
    void updateGatewayOfDevice(String serialNumber, String ipAddress);
}