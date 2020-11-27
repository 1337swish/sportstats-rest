package com.example.sportstats.service;

import com.example.sportstats.domain.Season;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Claes Andersson
 */
public class GetSeasonsService extends BaseService<List<Season>> {

    private final Integer id;
    private final Integer leagueId;

    public GetSeasonsService(Integer id, Integer leagueId) {
        this.id = id;
        this.leagueId = leagueId;
    }

    @Override
    public List<Season> execute() {

        List<Season> seasons = getBrokerFactory().getSeasonBroker().findAll();

        if (id != null) {
            seasons = seasons.stream()
                    .filter(season -> season.getId().equals(id))
                    .collect(Collectors.toList());
        } else if (leagueId != null) {
            seasons = seasons.stream()
                    .filter(season -> season.getLeagueId().equals(leagueId))
                    .collect(Collectors.toList());
        }

        return seasons;
    }
}
