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

// THIS IS AN EXAMPLE ENTITY RESOURCE
@Path("teams")
public class TeamResource {
	private volatile ITeamDAOService teamService;

	///////////////////////////////////////////////////////////////G E T//////////////////////////////////////////////////////////////
	/** Get a list of all Teams : HTTPRequest**/
	@GET  
	@Produces(MediaType.APPLICATION_JSON)
	public List<Team> all(@QueryParam("start") int start, @QueryParam("end") int end) throws ApplicationException {
		return teamService.findAll(start, end);
	}
	
	/** Get a specific Team by ID : HTTPRequest **/
	@GET  
	@Path("{teamId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Team getTeam(@PathParam("teamId") long teamId)
			throws NoSuchModelException, ApplicationException {
		return teamService.getByPrimary(teamId);
	}
	
	
	
	///////////////////////////////////////////////////////////////P U T//////////////////////////////////////////////////////////////
	/** Create Team By Name (String) and Rank (integer) : HTTPRequest **/
	@PUT
	@Path("{teamName}/{teamRank}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Team createTeam(@PathParam("teamName") String teamName, @PathParam("teamRank") int teamRank)
			throws NoSuchModelException, ApplicationException {
		Team doesTeamExist = teamService.getByName(teamName);
		if (doesTeamExist != null) {
			System.out.println(teamName + " already exists. Updating team");
		}
		Team newTeam = new Team(teamName, teamRank);
		checkForTeam(teamName);
		System.out.println(teamName + " has been created and modified");
		return teamService.provide(newTeam);		
	}
	
	/** Create Team by Data-Defaults or Local Variables : Back-end **/
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void provide(Team team) throws ApplicationException {
		teamService.provide(team);
	}
	
	
	
	////////////////////////////////////////////////////////////D E L E T E///////////////////////////////////////////////////////////
	/** Delete Team by Name (String) **/
	@DELETE
	@Path("byname/{teamName}")
	@Produces(MediaType.APPLICATION_JSON) 
	public String deleteByName(@PathParam("teamName") String teamName)
			throws NoSuchModelException, ApplicationException {
		Team doesTeamExist = teamService.getByName(teamName);
		if (doesTeamExist == null) {
			System.out.println(teamName + " does not exist. No changes to be made.");
		} else {
			long teamID = teamService.getByName(teamName).getId();
			teamService.delete(teamID);
			System.out.println(teamName + " deleted.");
		}
		String confirmDeletion = "Team Deleted";
		return confirmDeletion;	
	}
	public void checkForTeam(String teamName) throws NoSuchModelException, ApplicationException {
		Team check = teamService.getByName(teamName);
		if (check != null) {
			System.out.println("Changes to " + teamName + "successfully made!");
		} else {
			System.out.println(teamName + " changes could not be made. Messed up somewhere...");
		}
	}
	
	/** Delete Team by ID (Long) **/
	@DELETE
	@Path("byid/{teamId}")
	public void deleteById(@PathParam("teamId") long teamId) throws NoSuchModelException, ApplicationException {
		teamService.delete(teamId);
	}

	
	
	//////////////////////////////////////////////////////////////P O S T/////////////////////////////////////////////////////////////
	/** Update Team : Back-end **/
	@POST
	@Path("{teamId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateProblem(Team team) throws ApplicationException {
		teamService.update(team);
	}
	
}
