package com.example.sportstats.service;

import com.example.sportstats.domain.SeasonTeam;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

/**
 * Service that deletes seasonTeams.
 *
 * @author Claes Andersson
 */
public class DeleteSeasonTeamService extends BaseService<Map> {

    private final Integer id;

    public DeleteSeasonTeamService(Integer id) {

        this.id = id;
    }

    @Override
    public Map execute() {

        SeasonTeam seasonTeam = getBrokerFactory().getSeasonsTeamsBroker().findById(id);
        if (seasonTeam == null) {
            throw new SportstatsServiceException("There are no seasonTeam with id: " + id, HttpStatus.NOT_FOUND);
        }
        Map deletedSeasonTeam = new HashMap();
        deletedSeasonTeam.put("id", seasonTeam.getId());
        deletedSeasonTeam.put("teamId", seasonTeam.getTeamId());
        deletedSeasonTeam.put("seasonId", seasonTeam.getSeasonId());
        seasonTeam.delete();

        return deletedSeasonTeam;
    }
}
