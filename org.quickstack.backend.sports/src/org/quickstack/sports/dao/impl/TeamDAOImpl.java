package org.quickstack.sports.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.amdatu.jta.Transactional;
import org.quickstack.core.api.ApplicationException;
import org.quickstack.core.api.dao.NoSuchModelException;
import org.quickstack.core.commons.util.Validator;
import org.quickstack.core.commons.util.jpa.JPAEntityUtil;
import org.quickstack.core.model.JPABaseEntity;
import org.quickstack.core.service.impl.BaseServiceImpl;
import org.quickstack.sports.api.dao.ITeamDAOService;
import org.quickstack.sports.api.model.Player;
import org.quickstack.sports.api.model.Team;
import org.quickstack.sports.model.jpa.JPAPlayer;
import org.quickstack.sports.model.jpa.JPATeam;
import org.osgi.service.log.LogService;


@SuppressWarnings("restriction")
@Transactional
public class TeamDAOImpl extends BaseServiceImpl implements ITeamDAOService {
	//Problems
	private volatile LogService logger;
	
	private volatile EntityManager em;
	
	@Override
	public LogService getLogger() {
		return logger;
	}

	public void setLogger(LogService logger) {
		this.logger = logger;
	}

	@Override
	public EntityManager getEm() {
		return em;
	}	
	
	@Override
	public List<Team> findAll(int start, int end) throws ApplicationException {
		List<Team> result = new ArrayList<>();
		try {
			List<JPABaseEntity> resultList = super.findAll(JPATeam.class,start,end);
			result = JPAEntityUtil.copy(resultList, Team.class);
		}
		catch (Exception e) {
			throw processException(e);
		}
		return result;		
	}
	
	@Override
	public long countAll() throws ApplicationException {
		long res = 0;
		try {
			res = super.countAll(JPATeam.class);
		}
		catch (Exception e) {
			throw processException(e);
		}
		return res;
	}	
	
	@Override
	public Team getByPrimary(Long pk) throws ApplicationException, NoSuchModelException {
		JPATeam jpaEntity = (JPATeam) super.findByPrimaryKey(JPATeam.class, pk);
		return JPAEntityUtil.copy(jpaEntity, Team.class);
	}

	@Override
	public Team provide(Team record)
			  throws ApplicationException {
		Team existingTeam = getByCode(record.getCode());
		if (Validator.isNull(existingTeam))
		{
			JPABaseEntity res = super.add(JPAEntityUtil.copy(record, JPATeam.class));
			existingTeam = JPAEntityUtil.copy(res, Team.class);
		}
		else {
			return update(record);
		}

		return existingTeam;
	}
	
	@Override
	public Team update(Team record) throws ApplicationException {
		JPABaseEntity res = super.update(JPAEntityUtil.copy(record, JPATeam.class));
		Team dto = JPAEntityUtil.copy(res, Team.class);
		return dto;	
	}	
	
	@Override
	public Team delete(Long id) throws ApplicationException, NoSuchModelException {
		JPATeam jpaEntity = (JPATeam) super.findByPrimaryKey(JPATeam.class, id);
		super.remove(jpaEntity);
		return JPAEntityUtil.copy(jpaEntity, Team.class);
	}
	
	@Override
	public Team getByCode(String code) throws ApplicationException {
		JPATeam jpaEntity = (JPATeam) super.findWithAttribute(JPATeam.class, String.class,"code", code);
		return JPAEntityUtil.copy(jpaEntity, Team.class);
	}


	@Override
	public Team getByName(String name) throws ApplicationException {
		JPATeam jpaEntity = (JPATeam) super.findWithAttribute(JPATeam.class, String.class,"name", name);
		return JPAEntityUtil.copy(jpaEntity, Team.class);
	}
	
	
	//Player
	@Override 
	public List<Player> findAllProblemDifficulties(int start, int end) throws ApplicationException {
		List<Player> result = new ArrayList<>();
		try {
			List<JPABaseEntity> resultList = super.findAll(JPAPlayer.class,start,end);
			result = JPAEntityUtil.copy(resultList, Player.class);
		}
		catch (Exception e) {
			throw processException(e);
		}
		return result;		
	}
	
	@Override
	public long countAllProblemDifficulties() throws ApplicationException {
		long res = 0;
		try {
			res = super.countAll(JPAPlayer.class);
		}
		catch (Exception e) {
			throw processException(e);
		}
		return res;
	}	
	
	@Override
	public Player getPlayerByPrimary(Long pk) throws ApplicationException, NoSuchModelException {
		JPAPlayer jpaEntity = (JPAPlayer) super.findByPrimaryKey(JPAPlayer.class, pk);
		return JPAEntityUtil.copy(jpaEntity, Player.class);
	}
	
	@Override 
	public Player providePlayer(Player record) throws ApplicationException {
		Player existingCountry = getPlayerByCode(record.getCode());
		if (Validator.isNull(existingCountry))
		{
			JPABaseEntity res = super.add(JPAEntityUtil.copy(record, JPAPlayer.class));
			existingCountry = JPAEntityUtil.copy(res, Player.class);
		}

		return existingCountry;	
	}
	
	@Override
	public Player providePlayer(String name) throws ApplicationException {
		Player existingCountry = getPlayerByCode(name);
		if (Validator.isNull(existingCountry))
		{
			final Player record = new Player(name);
			JPABaseEntity res = super.add(JPAEntityUtil.copy(record, JPAPlayer.class));
			existingCountry = JPAEntityUtil.copy(res, Player.class);
		}

		return existingCountry;			
	}
	
	public Player updatePlayer(JPAPlayer record) throws ApplicationException {
		JPABaseEntity res = super.update(JPAEntityUtil.copy(record, JPAPlayer.class));
		Player dto = JPAEntityUtil.copy(res, Player.class);
		return dto;	
	}	
	
	@Override
	public Player getPlayerByCode(String code) throws ApplicationException{
		JPAPlayer jpaEntity = (JPAPlayer) super.findWithAttribute(JPAPlayer.class, String.class,"code", code);
		return JPAEntityUtil.copy(jpaEntity, Player.class);		
	}
	
	@Override
	public Player getPlayerByName(String code) throws ApplicationException{
		JPAPlayer jpaEntity = (JPAPlayer) super.findWithAttribute(JPAPlayer.class, String.class,"name", code);
		return JPAEntityUtil.copy(jpaEntity, Player.class);		
	}
	
	@Override
	public Player deletePlayer(Long id) throws ApplicationException, NoSuchModelException {
		JPAPlayer jpaEntity = (JPAPlayer) super.findByPrimaryKey(JPAPlayer.class, id);
		super.remove(jpaEntity);
		return JPAEntityUtil.copy(jpaEntity, Player.class);
	}
		
	
	@Override
	public void createDefaults() throws ApplicationException {
	}

}