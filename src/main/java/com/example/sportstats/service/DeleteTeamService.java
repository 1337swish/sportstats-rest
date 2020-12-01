package com.example.sportstats.service;

import com.example.sportstats.domain.Team;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

/**
 * Service that deletes teams.
 *
 * @author Claes Andersson
 */
public class DeleteTeamService extends BaseService<Map> {

    private final Integer id;

    public DeleteTeamService(Integer id) {

        this.id = id;
    }

    @Override
    public Map execute() {

        Team team = getBrokerFactory().getTeamBroker().findById(id);
        if (team == null) {
            throw new SportstatsServiceException("There are no team with id: " + id, HttpStatus.NOT_FOUND);
        }
        Map deletedTeam = new HashMap();
        deletedTeam.put("id", team.getId());
        deletedTeam.put("name", team.getName());
        deletedTeam.put("arenaId", team.getArenaId());
        deletedTeam.put("sportId", team.getSportId());
        deletedTeam.put("createdYear", team.getCreatedYear());
        deletedTeam.put("sportName", getBrokerFactory().getSportBroker().findById(team.getSportId()).getName());
        deletedTeam.put("arenaName", getBrokerFactory().getArenaBroker().findById(team.getArenaId()).getName());
        team.delete();

        return deletedTeam;
    }
}
