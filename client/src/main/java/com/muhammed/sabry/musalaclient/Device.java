package com.muhammed.sabry.musalaclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data()
@Builder()
@NoArgsConstructor
@AllArgsConstructor
public class Device {
	private int ID;
	private String gatewayID;
	private String vendor;
	private Date createDate;
	private boolean isOnline;
}
