package com.muhammed.sabry.musalaapi.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity()
@Data()
@Builder()
@NoArgsConstructor()
@AllArgsConstructor()
public class GatewayEntity {
	@Id
	private String UUID;
	private String name;
	private String address;
	@OneToMany(orphanRemoval = true, mappedBy = "gateway")
	private List<DeviceEntity> deviceList;
	
	public GatewayEntity(String UUID) {
		this.UUID = UUID;
	}
}
