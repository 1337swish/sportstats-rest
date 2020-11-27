package com.example.sportstats.service;

import com.example.sportstats.domain.Season;
import com.example.sportstats.domain.SeasonTeam;
import com.example.sportstats.domain.Team;

/**
 * Service that adds a new seasonTeam.
 *
 * @author Claes Andersson
 */
public class AddSeasonTeamService extends BaseService<SeasonTeam> {

    private final Integer teamId;
    private final Integer seasonId;

    public AddSeasonTeamService(Integer teamId, Integer seasonId) throws SportstatsServiceException {

        this.teamId = teamId;
        this.seasonId = seasonId;
    }

    @Override
    public SeasonTeam execute() {

        Team team = getBrokerFactory().getTeamBroker().findById(teamId);
        Season season = getBrokerFactory().getSeasonBroker().findById(seasonId);

        if (team == null) {
            throw new SportstatsServiceException("There are no team with id: " + teamId);
        } else if (season == null) {
            throw new SportstatsServiceException("There are no season with id: " + seasonId);
        } else if (getBrokerFactory().getSeasonsTeamsBroker().findBySeasonId(seasonId).stream().anyMatch(s -> s.getTeamId().equals(teamId))) {
            throw new SportstatsServiceException("This team is already connected to this season");
        }

        SeasonTeam seasonTeam = getBrokerFactory().getSeasonsTeamsBroker().create();
        seasonTeam.setTeamId(teamId);
        seasonTeam.setSeasonId(seasonId);
        seasonTeam.setTeamName(team.getName());
        if (season.getStartYear().equals(season.getEndYear())) {
            seasonTeam.setSeasonName(season.getStartYear().toString());
        } else {
            seasonTeam.setSeasonName(season.getStartYear() + "/" + season.getEndYear());
        }
        seasonTeam.save();

        return seasonTeam;
    }
}
