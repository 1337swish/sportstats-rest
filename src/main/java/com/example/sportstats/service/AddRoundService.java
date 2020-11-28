package com.example.sportstats.service;

import com.example.sportstats.domain.Round;
import org.springframework.http.HttpStatus;

/**
 * Service that adds a new round.
 *
 * @author Claes Andersson
 */
public class AddRoundService extends BaseService<Round> {

    private final Integer seasonId;
    private final Integer number;

    public AddRoundService(Integer seasonId, Integer number) {

        if (number < 1) {
            throw new SportstatsServiceException("Round number can't be less than 1", HttpStatus.BAD_REQUEST);
        }
        this.seasonId = seasonId;
        this.number = number;
    }

    @Override
    public Round execute() {

        if (getBrokerFactory().getRoundBroker().findBySeasonId(seasonId).stream().anyMatch(r -> r.getRound().equals(number))) {
            throw new SportstatsServiceException("Round " + number + " already exists in this season", HttpStatus.BAD_REQUEST);
        } else if (getBrokerFactory().getSeasonBroker().findById(seasonId) == null) {
            throw new SportstatsServiceException("There are no season with id: " + seasonId, HttpStatus.NOT_FOUND);
        } else if (number > getBrokerFactory().getSeasonBroker().findById(seasonId).getNbrOfRounds()) {
            throw new SportstatsServiceException("Round number can't be bigger than max rounds for this season", HttpStatus.BAD_REQUEST);
        }
        Round round = getBrokerFactory().getRoundBroker().create();
        round.setRound(number);
        round.setSeasonId(seasonId);
        round.save();

        return round;
    }
}
