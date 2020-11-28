package com.example.sportstats.service;

import com.example.sportstats.domain.Round;
import org.springframework.http.HttpStatus;

/**
 * Service that edits an existing round.
 *
 * @author Claes Andersson
 */
public class EditRoundService extends BaseService<Round> {

    private final Integer id;
    private final Integer seasonId;
    private final Integer number;

    public EditRoundService(Integer id, Integer seasonId, Integer roundNumber) {

        if (roundNumber != null && roundNumber < 1) {
            throw new SportstatsServiceException("Round number can't be less than 1", HttpStatus.BAD_REQUEST);
        }
        this.id = id;
        this.seasonId = seasonId;
        this.number = roundNumber;
    }

    @Override
    public Round execute() {

        Round round = getBrokerFactory().getRoundBroker().findById(id);
        if (round == null) {
            throw new SportstatsServiceException("There are no round with id: " + id, HttpStatus.NOT_FOUND);
        }
        if (seasonId != null) {
            if (getBrokerFactory().getSeasonBroker().findById(seasonId) == null) {
                throw new SportstatsServiceException("There are no season with id: " + seasonId, HttpStatus.NOT_FOUND);
            } else {
                round.setSeasonId(seasonId);
            }
        }
        if (number != null) {
            round.setRound(number);
        }
        round.save();

        return round;
    }
}
