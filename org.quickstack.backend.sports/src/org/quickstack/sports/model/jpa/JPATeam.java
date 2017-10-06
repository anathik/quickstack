package org.quickstack.sports.model.jpa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.quickstack.core.model.JPABaseEntity;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="qsdemo_team")
public class JPATeam extends JPABaseEntity implements Serializable {
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<JPAPlayer> questions = new java.util.HashSet<JPAPlayer>();

	public JPATeam() {
		super();
	}
}
