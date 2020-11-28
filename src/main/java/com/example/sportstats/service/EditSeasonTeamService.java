package com.example.sportstats.service;

import com.example.sportstats.domain.Season;
import com.example.sportstats.domain.SeasonTeam;
import com.example.sportstats.domain.Team;
import org.springframework.http.HttpStatus;

/**
 * Service that edits an existing seasonTeam.
 *
 * @author Claes Andersson
 */
public class EditSeasonTeamService extends BaseService<SeasonTeam> {

    private final Integer id;
    private final Integer teamId;
    private final Integer seasonId;

    public EditSeasonTeamService(Integer id, Integer teamId, Integer seasonId) {

        this.id = id;
        this.teamId = teamId;
        this.seasonId = seasonId;
    }

    @Override
    public SeasonTeam execute() {

        SeasonTeam seasonTeam = getBrokerFactory().getSeasonsTeamsBroker().findById(id);
        if (seasonTeam == null) {
            throw new SportstatsServiceException("There are no seasonTeam with id: " + id, HttpStatus.NOT_FOUND);
        }
        if (teamId != null) {
            Team team = getBrokerFactory().getTeamBroker().findById(teamId);
            if (team == null) {
                throw new SportstatsServiceException("There are no team with id: " + teamId, HttpStatus.NOT_FOUND);
            } else {
                seasonTeam.setTeamId(teamId);
                seasonTeam.setTeamName(team.getName());
            }
        }
        if (seasonId != null) {
            Season season = getBrokerFactory().getSeasonBroker().findById(seasonId);
            if (season == null) {
                throw new SportstatsServiceException("There are no season with id: " + seasonId, HttpStatus.NOT_FOUND);
            } else {
                seasonTeam.setSeasonId(seasonId);
                if (season.getStartYear().equals(season.getEndYear())) {
                    seasonTeam.setSeasonName(season.getStartYear().toString());
                } else {
                    seasonTeam.setSeasonName(season.getStartYear() + "/" + season.getEndYear());
                }
            }
        }
        seasonTeam.save();

        return seasonTeam;
    }
}
