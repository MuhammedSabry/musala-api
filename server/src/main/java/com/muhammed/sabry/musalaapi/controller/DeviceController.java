package com.muhammed.sabry.musalaapi.controller;

import com.muhammed.sabry.musalaapi.exception.InvalidArgumentException;
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
	
	@PutMapping("/device")
	public void updateDevice(@RequestBody Device device) {
		Optional<DeviceEntity> optionalDeviceEntity = this.deviceRepository.findById(device.getID());
		if (optionalDeviceEntity.isPresent()) {
			DeviceEntity deviceEntity = optionalDeviceEntity.get();
			this.deviceRepository.save(DeviceEntity.builder()
					.ID(device.getID())
					.vendor(device.getVendor())
					.isOnline(device.isOnline())
					.gateway(deviceEntity.getGateway())
					.createDate(deviceEntity.getCreateDate())
					.build());
		} else {
			throw new NotFoundException(String.format("Device with ID %s not found", device.getID()));
		}
	}
	
	@DeleteMapping("/device/{id}")
	public void removeDevice(@PathVariable("id") int ID) {
		Optional<DeviceEntity> optionalDeviceEntity = this.deviceRepository.findById(ID);
		if (optionalDeviceEntity.isPresent()) {
			this.deviceRepository.deleteById(ID);
		} else {
			throw new NotFoundException(String.format("Device with ID %s not found", ID));
		}
	}
	
	@PutMapping(path = "/device")
	public void addDevice(@RequestBody Device device) {
		
		String gatewayID = device.getGatewayID();
		if (isEmpty(gatewayID)) {
			throw new InvalidArgumentException("The device must include a gateway UUID, create one if none available");
		}
		
		Optional<GatewayEntity> gatewayOptional = this.gatewayRepository.findById(gatewayID);
		if (!gatewayOptional.isPresent()) {
			throw new NotFoundException("The provided gateway UUID isn't valid");
		}
		
		GatewayEntity gatewayEntity = gatewayOptional.get();
		if (gatewayEntity.getDeviceList().size() >= 10) {
			throw new InvalidArgumentException("The gateway has reached it's device limit, consider removing some devices or create a new gateway");
		}
		
		deviceRepository.save(DeviceEntity.builder()
				.createDate(new Date())
				.gateway(new GatewayEntity(gatewayID))
				.isOnline(device.isOnline())
				.vendor(device.getVendor())
				.build());
	}
}
