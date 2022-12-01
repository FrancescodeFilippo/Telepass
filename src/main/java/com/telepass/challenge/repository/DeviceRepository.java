package com.telepass.challenge.repository;

import com.telepass.challenge.model.DeviceId;
import com.telepass.challenge.model.DeviceModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceModel, DeviceId> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Device d WHERE d.uuid like %?1 AND d.fiscal_code like %?2", nativeQuery = true)
    void deleteDevices(String uuid,String fiscalCode);
}
