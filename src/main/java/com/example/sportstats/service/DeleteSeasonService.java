package com.example.sportstats.service;

import com.example.sportstats.domain.Season;
import java.util.HashMap;
import java.util.Map;

/**
 * Service that deletes seasons.
 *
 * @author Claes Andersson
 */
public class DeleteSeasonService extends BaseService<Map> {

    private final Integer id;

    public DeleteSeasonService(Integer id) {

        this.id = id;
    }

    @Override
    public Map execute() {

        Season season = getBrokerFactory().getSeasonBroker().findById(id);
        if (season == null) {
            throw new SportstatsServiceException("There are no sport with id: " + id);
        }
        Map deletedSeason = new HashMap();
        deletedSeason.put("id", season.getId());
        deletedSeason.put("leagueId", season.getLeagueId());
        deletedSeason.put("startYear", season.getStartYear());
        deletedSeason.put("endYear", season.getEndYear());
        deletedSeason.put("nbrOfRounds", season.getNbrOfRounds());
        season.delete();

        return deletedSeason;
    }
}
