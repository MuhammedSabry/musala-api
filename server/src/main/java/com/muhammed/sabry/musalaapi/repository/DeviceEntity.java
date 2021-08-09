package com.muhammed.sabry.musalaapi.repository;


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
public class DeviceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "UUID")
	private GatewayEntity gateway;
	private String vendor;
	private Date createDate;
	private boolean isOnline;
}
