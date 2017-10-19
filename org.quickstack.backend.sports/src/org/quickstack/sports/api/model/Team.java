package org.quickstack.sports.api.model;

import java.io.Serializable;
import java.util.Set;

import org.quickstack.core.model.BaseEntity;

public class Team extends BaseEntity implements Serializable {
	private Set<Player> players;
	
	public Team() {
		super();
	}
	
	public Team(final String name) {
		this();
		setName(name);
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	
}
