package org.quickstack.sports.defaults;

import org.osgi.service.log.LogService;
import org.quickstack.core.api.ApplicationException;
import org.quickstack.core.api.dao.NoSuchModelException;
import org.quickstack.sports.api.dao.ITeamDAOService;
import org.quickstack.sports.api.model.Player;
import org.quickstack.sports.api.model.Team;


public class Controller {
	private volatile LogService logger;
	private volatile ITeamDAOService teamService;
	private volatile ITeamDAOService playerService;
	
	private void start() throws ApplicationException, NoSuchModelException {
		//-- teams
		Team t1 = new Team("Team-A", 2);
		teamService.provide(t1);
		
		Team t2 = new Team("Team-B", 1);
		teamService.provide(t2);
		
		Player t1p1 = new Player("Jushi");
		playerService.addPlayer(t1.getName(), t1p1);
		
		Player t1p2 = new Player("Nathoniel");
		playerService.addPlayer(t1.getName(), t1p2);
		
		Player t1p3 = new Player("Goeffri");
		playerService.addPlayer(t1.getName(), t1p3);
		
		Player t2p1 = new Player("Ripley");
		playerService.addPlayer(t2.getName(), t2p1);
		
		Player t2p2 = new Player("Zabumafoo");
		playerService.addPlayer(t2.getName(), t2p2);
		
		Player t2p3 = new Player("Sips");
		playerService.addPlayer(t2.getName(), t2p3);
		
		Player t2p4 = new Player("Waouhaha");
		playerService.addPlayer(t2.getName(), t2p4);
		
		Player t2p5 = new Player("Lisboikovsovich");
		playerService.addPlayer(t2.getName(), t2p5);
		
	}
}
