package org.quickstack.sports.model.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.quickstack.core.model.Constants;
import org.quickstack.core.model.JPABaseEntity;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="qsdemo_player")
public class JPAPlayer extends JPABaseEntity implements Serializable {
	
	@ManyToOne
	private JPATeam playsFor;

	public JPAPlayer() {
		super();
	}

	public JPAPlayer(String name) {
		this();
		this.name = name;
	}
	
	

	public JPAPlayer(String name, JPATeam playsFor) {
		this(name);
		this.playsFor = playsFor;
	}

	public JPATeam getPlaysFor() {
		return playsFor;
	}

	public void setPlaysFor(JPATeam playsFor) {
		this.playsFor = playsFor;
	}	
}

