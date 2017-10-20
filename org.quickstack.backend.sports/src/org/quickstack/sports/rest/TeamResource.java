package org.quickstack.sports.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.quickstack.core.api.ApplicationException;
import org.quickstack.core.api.dao.NoSuchModelException;
import org.quickstack.sports.api.dao.ITeamDAOService;
import org.quickstack.sports.api.model.Team;

@Path("teams")
public class TeamResource {
	private volatile ITeamDAOService teamService;

	/**
	 * 
	 * ========= Teams
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Team> all(@QueryParam("start") int start, @QueryParam("end") int end) throws ApplicationException {
		return teamService.findAll(start, end);
	}

	@GET
	@Path("{teamId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Team getTeam(@PathParam("teamId") long teamId)
			throws NoSuchModelException, ApplicationException {
		return teamService.getByPrimary(teamId);
	}

	@POST
	@Path("{teamId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateProblem(Team team) throws ApplicationException {
		teamService.update(team);
	}

	@DELETE
	@Path("{teamId}")
	public void delete(@PathParam("teamId") long teamId) throws NoSuchModelException, ApplicationException {
		teamService.delete(teamId);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void provide(Team team) throws ApplicationException {
		teamService.provide(team);
	}
}
