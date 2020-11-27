package com.example.sportstats.service;

import com.example.sportstats.domain.Season;
import com.example.sportstats.domain.SeasonTeam;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Claes Andersson
 */
public class GetSeasonTeamService extends BaseService<List<SeasonTeam>> {

    private final Integer teamId;
    private final Integer seasonId;

    public GetSeasonTeamService(Integer teamId, Integer seasonId) throws SportstatsServiceException {

        this.teamId = teamId;
        this.seasonId = seasonId;
    }

    @Override
    public List<SeasonTeam> execute() {

        List<SeasonTeam> seasonTeams = getBrokerFactory().getSeasonsTeamsBroker().findAll();

        if (teamId != null) {
            seasonTeams = seasonTeams.stream()
                    .filter(s -> s.getTeamId().equals(teamId))
                    .collect(Collectors.toList());
        }
        if (seasonId != null) {
            seasonTeams = seasonTeams.stream()
                    .filter(s -> s.getSeasonId().equals(seasonId))
                    .collect(Collectors.toList());
        }
        seasonTeams.forEach(s -> {
            Season season = getBrokerFactory().getSeasonBroker().findById(s.getSeasonId());
            if (season.getStartYear().equals(season.getEndYear())) {
                s.setSeasonName(season.getStartYear().toString());
            } else {
                s.setSeasonName(season.getStartYear() + "/" + season.getEndYear());
            }
            s.setTeamName(getBrokerFactory().getTeamBroker().findById(s.getTeamId()).getName());
        });

        return seasonTeams;
    }
}
