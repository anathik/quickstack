package org.quickstack.sports.api.dao;

import java.util.List;

import org.quickstack.core.api.ApplicationException;
import org.quickstack.core.api.dao.NoSuchModelException;
import org.quickstack.core.api.service.IBaseService;
import org.quickstack.sports.api.model.Player;
import org.quickstack.sports.api.model.Team;

public interface ITeamDAOService extends IBaseService {
	// Team
	public List<Team> findAll(int start, int end) throws ApplicationException;
	
	public long countAll() throws ApplicationException;
	
	public Team provide(Team record) throws ApplicationException;
	
	public Team update(Team record) throws ApplicationException;
	
	public Team delete(Long id) throws ApplicationException, NoSuchModelException;
	
	public Team getByPrimary(Long pk) throws ApplicationException, NoSuchModelException;

	public Team getByCode(String code) throws ApplicationException;

	public Team getByName(String name) throws ApplicationException;
	

	//Players
	public List<Player> findAllProblemDifficulties(int start, int end) throws ApplicationException;
	
	public long countAllProblemDifficulties() throws ApplicationException;
	
	public Player getPlayerByPrimary(Long pk)  throws ApplicationException, NoSuchModelException;
	
	public Player providePlayer(Player record) throws ApplicationException;
	
	public Player providePlayer(String name) throws ApplicationException;
	
	public Player getPlayerByCode(String code) throws ApplicationException;
	
	public Player getPlayerByName(String name) throws ApplicationException;
	
	public Player deletePlayer(Long id) throws ApplicationException, NoSuchModelException;	
}
