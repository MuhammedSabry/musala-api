package com.muhammed.sabry.musalaapi.mapper;

import com.muhammed.sabry.musalaapi.repository.DeviceEntity;
import com.muhammed.sabry.musalaclient.Device;

public class DeviceMapper {
	public static Device map(DeviceEntity entity) {
		return Device.builder()
				.ID(entity.getID())
				.vendor(entity.getVendor())
				.isOnline(entity.isOnline())
				.gatewayID(entity.getGateway().getUUID())
				.createDate(entity.getCreateDate())
				.build();
	}
}
