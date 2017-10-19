package org.quickstack.sports.defaults;

import org.osgi.service.log.LogService;
import org.quickstack.core.api.ApplicationException;
import org.quickstack.sports.api.dao.ITeamDAOService;
import org.quickstack.sports.api.model.Team;


public class Controller {
	private volatile LogService logger;
	private volatile ITeamDAOService teamService;
	
	private void start() throws ApplicationException {
		//-- teams
		Team t1 = new Team("Team-A");
		teamService.provide(t1);
		
		Team t2 = new Team("Team-B");
		teamService.provide(t2);
	}
}
