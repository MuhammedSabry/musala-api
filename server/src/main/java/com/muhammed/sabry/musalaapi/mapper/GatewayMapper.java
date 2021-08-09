package com.muhammed.sabry.musalaapi.mapper;

import com.muhammed.sabry.musalaapi.repository.GatewayEntity;
import com.muhammed.sabry.musalaclient.Gateway;

import java.util.Collections;
import java.util.stream.Collectors;

public class GatewayMapper {
	public static Gateway map(GatewayEntity entity) {
		return Gateway.builder()
				.address(entity.getAddress())
				.deviceList(entity.getDeviceList().stream().map(DeviceMapper::map).collect(Collectors.toList()))
				.name(entity.getName())
				.build();
	}
}
