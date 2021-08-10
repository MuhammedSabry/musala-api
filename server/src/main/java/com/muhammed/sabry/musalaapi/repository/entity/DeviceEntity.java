package com.muhammed.sabry.musalaapi.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity()
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "GATEWAY_UUID")
	private GatewayEntity gateway;
	private String vendor;
	private Date createDate;
	private boolean isOnline;
}
