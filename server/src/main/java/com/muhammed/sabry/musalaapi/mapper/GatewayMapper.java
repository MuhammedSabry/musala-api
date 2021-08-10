package com.muhammed.sabry.musalaapi.mapper;

import com.muhammed.sabry.musalaapi.repository.entity.GatewayEntity;
import com.muhammed.sabry.musalaclient.Gateway;

import java.util.stream.Collectors;

public class GatewayMapper extends BaseMapper<GatewayEntity, Gateway> {
	@Override
	public GatewayEntity mapToEntity(Gateway clientData) {
		return GatewayEntity.builder()
				.UUID(clientData.getUUID())
				.address(clientData.getAddress())
				.name(clientData.getName())
				.build();
	}
	
	public Gateway mapToClient(GatewayEntity entity) {
		return Gateway.builder()
				.UUID(entity.getUUID())
				.address(entity.getAddress())
				.deviceList(entity.getDeviceList().stream().map(new DeviceMapper()::mapToClient).collect(Collectors.toList()))
				.name(entity.getName())
				.build();
	}
}
