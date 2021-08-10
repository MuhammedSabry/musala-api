package com.muhammed.sabry.musalaapi.mapper;

import com.muhammed.sabry.musalaapi.repository.entity.DeviceEntity;
import com.muhammed.sabry.musalaapi.repository.entity.GatewayEntity;
import com.muhammed.sabry.musalaclient.Device;


public class DeviceMapper extends BaseMapper<DeviceEntity, Device> {
	
	public Device mapToClient(DeviceEntity entity) {
		return Device.builder()
				.ID(entity.getID())
				.vendor(entity.getVendor())
				.isOnline(entity.isOnline())
				.gatewayID(entity.getGateway().getUUID())
				.createDate(entity.getCreateDate())
				.build();
	}
	
	public DeviceEntity mapToEntity(Device device) {
		return DeviceEntity.builder()
				.ID(device.getID())
				.gateway(new GatewayEntity(device.getGatewayID()))
				.createDate(device.getCreateDate())
				.isOnline(device.isOnline())
				.vendor(device.getVendor())
				.build();
	}
}
