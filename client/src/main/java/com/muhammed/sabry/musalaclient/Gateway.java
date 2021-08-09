package com.muhammed.sabry.musalaclient;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class Gateway {
	private String UUID;
	private String name;
	private String address;
	private List<Device> deviceList;
}
