package com.muhammed.sabry.musalaclient;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data()
@Builder()
public class Device {
	private int ID;
	private String gatewayID;
	private String vendor;
	private Date createDate;
	private boolean isOnline;
}
