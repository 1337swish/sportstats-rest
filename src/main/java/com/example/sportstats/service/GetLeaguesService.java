package com.example.sportstats.service;

import com.example.sportstats.domain.League;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Claes Andersson
 */
public class GetLeaguesService extends BaseService<List<League>> {

    private final Integer id;
    private final Integer sportId;

    public GetLeaguesService(Integer id, Integer sportId) {
        this.id = id;
        this.sportId = sportId;
    }

    @Override
    public List<League> execute() {

        List<League> leagues = getBrokerFactory().getLeagueBroker().findAll();

        if (id != null) {
            leagues = leagues.stream()
                    .filter(league -> league.getId().equals(id))
                    .collect(Collectors.toList());
        } else if (sportId != null) {
            leagues = leagues.stream()
                    .filter(league -> league.getSportId().equals(sportId))
                    .collect(Collectors.toList());
        }

        return leagues;
    }
}
