package com.example.sportstats.service;

import com.example.sportstats.domain.Round;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Claes Andersson
 */
public class GetRoundsService extends BaseService<List<Round>> {

    private final Integer id;
    private final Integer seasonId;

    public GetRoundsService(Integer id, Integer seasonId) {

        this.id = id;
        this.seasonId = seasonId;
    }

    @Override
    public List<Round> execute() {

        List<Round> rounds = getBrokerFactory().getRoundBroker().findAll();

        if (id != null) {
            rounds = rounds.stream()
                    .filter(round -> round.getId().equals(id))
                    .collect(Collectors.toList());
        } else if (seasonId != null) {
            rounds = rounds.stream()
                    .filter(round -> round.getSeasonId().equals(seasonId))
                    .collect(Collectors.toList());
        }
        
        if (rounds.isEmpty()) {
            throw new SportstatsServiceException("There are no rounds", HttpStatus.NOT_FOUND);
        }

        return rounds;
    }
}
