package com.muhammed.sabry.musalaapi.controller;

import com.muhammed.sabry.musalaapi.exception.InvalidArgumentException;
import com.muhammed.sabry.musalaapi.exception.NotFoundException;
import com.muhammed.sabry.musalaapi.helper.AddressValidator;
import com.muhammed.sabry.musalaapi.mapper.GatewayMapper;
import com.muhammed.sabry.musalaapi.repository.base.GatewayRepository;
import com.muhammed.sabry.musalaapi.repository.entity.GatewayEntity;
import com.muhammed.sabry.musalaclient.Gateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController()
public class GatewayController {
	private final GatewayRepository gatewayRepository;
	
	public GatewayController(GatewayRepository gatewayRepository) {
		this.gatewayRepository = gatewayRepository;
	}
	
	@PostMapping(path = "/gateway")
	public void addGateway(@RequestBody Gateway gateway) {
		if (gateway.getAddress() == null)
			throw new InvalidArgumentException("Address cannot be empty");
		
		if (!AddressValidator.isValidAddress(gateway.getAddress()))
			throw new InvalidArgumentException("Address is invalid, must be in format xxx.xxx.xxx.xxx");
		
		GatewayEntity gatewayEntity = new GatewayMapper().mapToEntity(gateway);
		gatewayEntity.setUUID(UUID.randomUUID().toString());
		gatewayRepository.save(gatewayEntity);
	}
	
	@GetMapping(path = "/gateway/list")
	public List<Gateway> getGateways() {
		Stream<GatewayEntity> entityStream = StreamSupport.stream(gatewayRepository.findAll().spliterator(), false);
		return entityStream.map(new GatewayMapper()::mapToClient)
				.collect(Collectors.toList());
	}
	
	@GetMapping(path = "/gateway/{uuid}")
	public Gateway getGatewayByUUID(@PathVariable String uuid) {
		GatewayEntity gatewayEntity = gatewayRepository.findById(uuid).orElse(null);
		if (gatewayEntity != null)
			return new GatewayMapper().mapToClient(gatewayEntity);
		else
			throw new NotFoundException(String.format("Ops, gateway with UUID: %s not found", uuid));
		
	}
	
	@DeleteMapping(path = "/gateway/{uuid}")
	public void removeGatewayByUUID(@PathVariable String uuid) {
		GatewayEntity gatewayEntity = gatewayRepository.findById(uuid).orElse(null);
		if (gatewayEntity != null) {
			gatewayRepository.deleteById(uuid);
		} else {
			throw new NotFoundException(String.format("Ops, gateway with UUID: %s not found", uuid));
		}
		
	}
}
