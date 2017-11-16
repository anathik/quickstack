package org.quickstack.sports.model.jpa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	
    @Basic
    @Column(name = "rank")    
    private Integer rank;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="playsFor")
	private Set<JPAPlayer> players = new java.util.HashSet<JPAPlayer>();

	public JPATeam() {
		super();
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Set<JPAPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(Set<JPAPlayer> players) {
		this.players = players;
	}
}
