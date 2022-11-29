package com.telepass.challenge.repository;

import com.telepass.challenge.model.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<DeviceModel, String> {

}
