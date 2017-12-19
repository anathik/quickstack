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
import org.quickstack.sports.api.model.Player;
import org.quickstack.sports.api.model.Team;;

//THIS IS AN EXAMPLE ENTITY RESOURCE
@Path("players")
public class PlayerResource {
	private volatile ITeamDAOService playerService;
	
	public void checkForPlayer(String playerName) throws NoSuchModelException, ApplicationException {
		Player check = playerService.getPlayerByName(playerName);
		if (check != null) {
			System.out.println("Changes to " + playerName + "successfully made!");
		} else {
			System.out.println(playerName + " changes could not be made. Messed up somewhere...");
		}
	}

	///////////////////////////////////////////////////////////////G E T//////////////////////////////////////////////////////////////
	/** Get list of all Players : HTTPRequest **/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> all(@QueryParam("start") int start, @QueryParam("end") int end) throws ApplicationException {
		return playerService.findAllProblemDifficulties(start, end);
	}
	
	/** Get one Player by Name (String) : HTTPRequest **/
	@GET
	@Path("{playerName}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Player findPlayer(@PathParam("playerName") String playerName)
			throws NoSuchModelException, ApplicationException {
		return playerService.getPlayerByName(playerName);
	}
	
	/** Get one Player by ID (Long) : HTTPRequest **/
	@GET
	@Path("{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Player getPlayer(@PathParam("playerId") long playerId)
			throws NoSuchModelException, ApplicationException {
		return playerService.getPlayerByPrimary(playerId);
	}
	
	
	
	///////////////////////////////////////////////////////////////P U T//////////////////////////////////////////////////////////////
	/** Create Player in Data-Defaults with Local Variables : Back-end **/
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void providePlayer(Player player) throws ApplicationException {
		playerService.providePlayer(player);
	}
	
	/** Create Player by Name (String) and Rank (integer) : HTTPRequest **/
	@PUT
	@Produces(MediaType.APPLICATION_JSON) 
	public Player createPlayer(@PathParam("playerName") String playerName)
			throws NoSuchModelException, ApplicationException {
		Player doesTeamExist = playerService.getPlayerByName(playerName);
		if (doesTeamExist != null) {
			System.out.println(playerName + " already exists. Updating team");
		}
		Player newPlayer = new Player(playerName);
		checkForPlayer(playerName);
		System.out.println(playerName + " has been created and modified");
		return playerService.providePlayer(newPlayer);		
	}
	
	//////////////////////////////////////////////////////////////P O S T/////////////////////////////////////////////////////////////
	/** Update Player in Data-Defaults with Local Variables : Back-end **/
	@POST
	@Path("{playerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatePlayer(Player player) throws ApplicationException {
		playerService.updatePlayer(player);
	}

	
	
	////////////////////////////////////////////////////////////D E L E T E///////////////////////////////////////////////////////////
	/** Delete Player by ID (Long) : HTTPRequest **/
	@DELETE
	@Path("id={playerId}")
	public String deleteByID(@PathParam("playerId") long playerId) throws NoSuchModelException, ApplicationException {
		playerService.deletePlayer(playerId);
		String confirmDeletion = playerService.getPlayerByPrimary(playerId).getName() + " (" + playerId + ")" + " deleted by PlayerID.";
		return confirmDeletion;
	}
	
	/** Delete Player by Name (String) : HTTPRequest **/
	@DELETE
	@Path("name={playerName}")
	public String deleteByName(@PathParam("playerName") String playerName) throws NoSuchModelException, ApplicationException {
		long playerId = playerService.getPlayerByName(playerName).getId();
		Player doesTeamExist = playerService.getPlayerByName(playerName);
		if (doesTeamExist == null) {
			System.out.println(playerName + " does not exist. No changes to be made.");
		} else {
			playerService.deletePlayer(playerId);
			System.out.println(playerName + " (" + playerId +") " + " deleted by PlayerName.");
		}
		String confirmDeletion = playerName + " (" + playerId +") " + "Deleted";
		return confirmDeletion;
	}

}
