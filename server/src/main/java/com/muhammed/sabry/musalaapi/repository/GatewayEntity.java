package com.muhammed.sabry.musalaapi.repository;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity()
@Data()
@Builder()
@NoArgsConstructor()
@AllArgsConstructor()
@RequiredArgsConstructor()
public class GatewayEntity {
	@Id
	private String UUID;
	private String name;
	private String address;
	@OneToMany()
	private List<DeviceEntity> deviceList;
	
	public GatewayEntity(String UUID) {
		this.UUID = UUID;
	}
}
