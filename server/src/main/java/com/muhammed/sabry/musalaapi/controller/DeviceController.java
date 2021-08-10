package com.muhammed.sabry.musalaapi.controller;

import com.muhammed.sabry.musalaapi.exception.InvalidArgumentException;
import com.muhammed.sabry.musalaapi.exception.NotFoundException;
import com.muhammed.sabry.musalaapi.mapper.DeviceMapper;
import com.muhammed.sabry.musalaapi.repository.base.DeviceRepository;
import com.muhammed.sabry.musalaapi.repository.base.GatewayRepository;
import com.muhammed.sabry.musalaapi.repository.entity.DeviceEntity;
import com.muhammed.sabry.musalaapi.repository.entity.GatewayEntity;
import com.muhammed.sabry.musalaclient.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.apache.http.util.TextUtils.isEmpty;

@RestController()
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
		DeviceEntity deviceEntity = this.deviceRepository.findById(ID).orElse(null);
		if (deviceEntity != null)
			return new DeviceMapper().mapToClient(deviceEntity);
		else
			throw new NotFoundException(String.format("Device with ID %s not found", ID));
	}
	
	@PutMapping("/device")
	public void updateDevice(@RequestBody Device device) {
		DeviceEntity deviceEntity = this.deviceRepository.findById(device.getID()).orElse(null);
		if (deviceEntity != null)
			this.deviceRepository.save(new DeviceMapper().mapToEntity(device));
		else
			throw new NotFoundException(String.format("Device with ID %s not found", device.getID()));
		
	}
	
	@DeleteMapping("/device/{id}")
	public void removeDevice(@PathVariable("id") int ID) {
		DeviceEntity deviceEntity = this.deviceRepository.findById(ID).orElse(null);
		if (deviceEntity != null)
			this.deviceRepository.deleteById(deviceEntity.getID());
		else
			throw new NotFoundException(String.format("Device with ID %s not found", ID));
	}
	
	@PostMapping(path = "/device")
	public void addDevice(@RequestBody Device device) {
		
		String gatewayID = device.getGatewayID();
		if (isEmpty(gatewayID))
			throw new InvalidArgumentException("The device must include a gateway UUID, create one if none available");
		
		GatewayEntity gatewayEntity = this.gatewayRepository.findById(gatewayID).orElse(null);
		if (gatewayEntity == null)
			throw new NotFoundException("The provided gateway UUID isn't valid");
		
		if (gatewayEntity.getDeviceList().size() >= 10)
			throw new InvalidArgumentException("The gateway has reached it's device limit, consider removing some devices or create a new gateway");
		
		DeviceEntity deviceEntity = new DeviceMapper().mapToEntity(device);
		deviceEntity.setCreateDate(new Date());
		deviceEntity.setGateway(new GatewayEntity(gatewayID));
		deviceEntity.setID(0);
		deviceRepository.save(deviceEntity);
	}
}
