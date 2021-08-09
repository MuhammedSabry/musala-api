package com.muhammed.sabry.musalaapi.controller;

import com.muhammed.sabry.musalaapi.exception.NotFoundException;
import com.muhammed.sabry.musalaapi.mapper.GatewayMapper;
import com.muhammed.sabry.musalaapi.repository.DeviceRepository;
import com.muhammed.sabry.musalaapi.repository.GatewayEntity;
import com.muhammed.sabry.musalaapi.repository.GatewayRepository;
import com.muhammed.sabry.musalaclient.Device;
import com.muhammed.sabry.musalaclient.Gateway;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController(value = "Gateway-Controller")
public class GatewayController {
	private final GatewayRepository gatewayRepository;
	
	public GatewayController(GatewayRepository gatewayRepository) {
		this.gatewayRepository = gatewayRepository;
	}
	
	@PostMapping(path = "/gateway")
	public void addGateway(@RequestBody Gateway gateway) {
		gatewayRepository.save(GatewayEntity.builder()
				.UUID(UUID.randomUUID().toString())
				.address(gateway.getAddress())
				.name(gateway.getName())
				.build());
	}
	
	@GetMapping(path = "/gateway/list")
	public List<Gateway> getGateways() {
		Stream<GatewayEntity> entityStream = StreamSupport.stream(gatewayRepository.findAll().spliterator(), false);
		return entityStream.map(GatewayMapper::map)
				.collect(Collectors.toList());
	}
	
	@GetMapping(path = "/gateway/{uuid}")
	public Gateway getGatewayByUUID(@PathVariable String uuid) {
		Optional<GatewayEntity> entityOptional = gatewayRepository.findById(uuid);
		if (entityOptional.isPresent()) {
			return GatewayMapper.map(entityOptional.get());
		} else {
			throw new NotFoundException(String.format("Ops, gateway with UUID: %s not found", uuid));
		}
	}
}
