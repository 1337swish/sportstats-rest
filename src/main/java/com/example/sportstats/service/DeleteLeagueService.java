package com.example.sportstats.service;

import com.example.sportstats.domain.League;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

/**
 * Service that deletes leagues.
 *
 * @author Claes Andersson
 */
public class DeleteLeagueService extends BaseService<Map> {

    private final Integer id;

    public DeleteLeagueService(Integer id) {

        this.id = id;
    }

    @Override
    public Map execute() {

        League league = getBrokerFactory().getLeagueBroker().findById(id);
        if (league == null) {
            throw new SportstatsServiceException("There are no league with id: " + id, HttpStatus.NOT_FOUND);
        }
        Map deletedLeague = new HashMap();
        deletedLeague.put("id", league.getId());
        deletedLeague.put("name", league.getName());
        deletedLeague.put("country", league.getCountry());
        deletedLeague.put("sportId", league.getSportId());
        league.delete();

        return deletedLeague;
    }
}
