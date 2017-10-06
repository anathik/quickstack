package org.quickstack.sports.api.model;

import java.io.Serializable;

import org.quickstack.core.model.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Player extends BaseEntity implements Serializable {
	public Player() {
	}
	
	public Player(String name) {
		setName(name);
	}
}
