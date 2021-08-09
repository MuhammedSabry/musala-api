package com.muhammed.sabry.musalaapi.controller;

import com.muhammed.sabry.musalaapi.exception.NotFoundException;
import com.muhammed.sabry.musalaapi.mapper.DeviceMapper;
import com.muhammed.sabry.musalaapi.repository.DeviceEntity;
import com.muhammed.sabry.musalaapi.repository.DeviceRepository;
import com.muhammed.sabry.musalaapi.repository.GatewayEntity;
import com.muhammed.sabry.musalaapi.repository.GatewayRepository;
import com.muhammed.sabry.musalaclient.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

import static org.apache.http.util.TextUtils.isEmpty;

@RestController(value = "Device-Controller")
public class DeviceController {
	private final DeviceRepository deviceRepository;
	private final GatewayRepository gatewayRepository;
	
	@Autowired
	public DeviceController(DeviceRepository deviceRepository, GatewayRepository gatewayRepository) {
		this.deviceRepository = deviceRepository;
		this.gatewayRepository = gatewayRepository;
	}
	
	@GetMapping("/device/{id}")
	public Device getDeviceById(@PathVariable("id") int ID) {
		Optional<DeviceEntity> optionalDeviceEntity = this.deviceRepository.findById(ID);
		if (optionalDeviceEntity.isPresent()) {
			DeviceEntity deviceEntity = optionalDeviceEntity.get();
			return DeviceMapper.map(deviceEntity);
		} else {
			throw new NotFoundException(String.format("Device with ID %s not found", ID));
		}
	}
	
	@PutMapping(path = "/device")
	public void addDevice(@RequestBody Device device) {
		if (isEmpty(device.getGatewayID())) {
			throw
		}
		deviceRepository.save(DeviceEntity.builder()
				.createDate(new Date())
				.gateway(new GatewayEntity(device.getGatewayID()))
				.isOnline(device.isOnline())
				.vendor(device.getVendor())
				.build());
	}
}
