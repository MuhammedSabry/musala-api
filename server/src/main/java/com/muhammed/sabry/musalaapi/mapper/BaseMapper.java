package com.muhammed.sabry.musalaapi.mapper;

public abstract class BaseMapper<E, C> {
	public abstract E mapToEntity(C clientData);
	
	public abstract C mapToClient(E entityData);
}
