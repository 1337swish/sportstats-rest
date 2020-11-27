package com.example.sportstats.service;

import com.example.sportstats.domain.League;
import com.example.sportstats.domain.Season;

/**
 * Service that edits an existing season.
 *
 * @author Claes Andersson
 */
public class EditSeasonService extends BaseService<Season> {

    private final Integer id;
    private final Integer leagueId;
    private final Integer startYear;
    private final Integer endYear;
    private final Integer rounds;

    public EditSeasonService(Integer id, Integer leagueId, Integer startYear, Integer endYear, Integer rounds) {

        if (rounds != null && rounds < 1) {
            throw new SportstatsServiceException("There can't be less than 1 round in a league");
        } else if (startYear != null && endYear != null && startYear > endYear) {
            throw new SportstatsServiceException("The starting year can't be after the ending year!");
        }
        this.id = id;
        this.leagueId = leagueId;
        this.startYear = startYear;
        this.endYear = endYear;
        this.rounds = rounds;
    }

    @Override
    public Season execute() {

        Season season = getBrokerFactory().getSeasonBroker().findById(id);
        League league = getBrokerFactory().getLeagueBroker().findById(leagueId);
        if (season == null) {
            throw new SportstatsServiceException("There are no season with id: " + id);
        }
        if (league == null) {
            throw new SportstatsServiceException("There are no league with id: " + leagueId);
        } else {
            season.setLeagueId(leagueId);
        }
        if (startYear != null) {
            season.setStartYear(startYear);
        }
        if (endYear != null) {
            season.setEndYear(endYear);
        }
        if (rounds != null) {
            season.setNbrOfRounds(rounds);
        }
        season.save();

        return season;
    }
}
