package com.example.sportstats.service;

import com.example.sportstats.domain.Season;
import org.springframework.http.HttpStatus;

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
            throw new SportstatsServiceException("There can't be less than 1 round in a league", HttpStatus.BAD_REQUEST);
        } else if (startYear != null && endYear != null && startYear > endYear) {
            throw new SportstatsServiceException("The starting year can't be after the ending year!", HttpStatus.BAD_REQUEST);
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
        if (season == null) {
            throw new SportstatsServiceException("There are no season with id: " + id, HttpStatus.NOT_FOUND);
        }
        if (leagueId != null) {
            if (getBrokerFactory().getLeagueBroker().findById(leagueId) == null) {
                throw new SportstatsServiceException("There are no league with id: " + leagueId, HttpStatus.NOT_FOUND);
            } else {
                season.setLeagueId(leagueId);
            }
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
        season.setLeagueName(getBrokerFactory().getLeagueBroker().findById(leagueId).getName());
        season.save();

        return season;
    }
}
