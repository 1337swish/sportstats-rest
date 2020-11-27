package com.example.sportstats.service;

import com.example.sportstats.domain.Round;
import com.example.sportstats.domain.Season;

/**
 * Service that adds a new season.
 *
 * @author Claes Andersson
 */
public class AddSeasonService extends BaseService<Season> {

    private final Integer leagueId;
    private final Integer startYear;
    private final Integer endYear;
    private final Integer rounds;
    private final Boolean autoAddRounds;

    public AddSeasonService(Integer leagueId, Integer startYear, Integer endYear, Integer rounds, Boolean autoAddRounds) {

        if (rounds < 1) {
            throw new SportstatsServiceException("There can't be less than 1 round in a league");
        } else if (startYear > endYear) {
            throw new SportstatsServiceException("The starting year can't be after the ending year!");
        }
        this.leagueId = leagueId;
        this.startYear = startYear;
        this.endYear = endYear;
        this.rounds = rounds;
        this.autoAddRounds = autoAddRounds;
    }

    @Override
    public Season execute() {

        if (getBrokerFactory().getLeagueBroker().findById(leagueId) == null) {
            throw new SportstatsServiceException("There are no league with id: " + leagueId);
        }
        Season season = getBrokerFactory().getSeasonBroker().create();
        season.setLeagueId(leagueId);
        season.setStartYear(startYear);
        season.setEndYear(endYear);
        season.setNbrOfRounds(rounds);
        season.save();

        if (autoAddRounds) {
            for (int i = 0; i < rounds; i++) {
                Round round = getBrokerFactory().getRoundBroker().create();
                round.setRound(i + 1);
                round.setSeasonId(season.getId());
                round.save();
            }
        }

        return season;
    }
}
