package com.example.sportstats.service;

import com.example.sportstats.domain.SeasonTeam;
import com.example.sportstats.domain.Team;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Claes Andersson
 */
public class GetTeamsService extends BaseService<List<Team>> {

    private final Integer id;
    private final Integer arenaId;
    private final Integer seasonId;
    private final Integer sportId;

    public GetTeamsService(Integer id, Integer arenaId, Integer seasonId, Integer sportId) {

        this.id = id;
        this.arenaId = arenaId;
        this.seasonId = seasonId;
        this.sportId = sportId;
    }

    @Override
    public List<Team> execute() {

        List<Team> teams = getBrokerFactory().getTeamBroker().findAll();

        if (id != null) {
            teams = teams.stream()
                    .filter(team -> team.getId().equals(id))
                    .collect(Collectors.toList());
        } else if (seasonId != null) {
            List<SeasonTeam> seasonTeams = getBrokerFactory().getSeasonsTeamsBroker().findBySeasonId(seasonId);
            teams = teams.stream()
                    .filter(team -> {
                        return seasonTeams.stream().anyMatch(s -> (team.getId().equals(s.getTeamId())));
                    })
                    .collect(Collectors.toList());
        } else if (sportId != null) {
            teams = teams.stream()
                    .filter(team -> team.getSportId().equals(sportId))
                    .collect(Collectors.toList());
        }
        if (arenaId != null) {
            teams = teams.stream()
                    .filter(team -> team.getArenaId().equals(arenaId))
                    .collect(Collectors.toList());
        }

        if (teams.isEmpty()) {
            throw new SportstatsServiceException("There are no teams", HttpStatus.NOT_FOUND);
        }

        teams.forEach(t -> {
            t.setSportName(getBrokerFactory().getSportBroker().findById(t.getSportId()).getName());
            t.setArenaName(getBrokerFactory().getArenaBroker().findById(t.getArenaId()).getName());
        });

        return teams;
    }
}
