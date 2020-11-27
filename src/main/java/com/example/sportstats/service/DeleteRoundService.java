package com.example.sportstats.service;

import com.example.sportstats.domain.Round;
import java.util.HashMap;
import java.util.Map;

/**
 * Service that deletes rounds.
 *
 * @author Claes Andersson
 */
public class DeleteRoundService extends BaseService<Map> {

    private final Integer id;

    public DeleteRoundService(Integer id) {

        this.id = id;
    }

    @Override
    public Map execute() {

        Round round = getBrokerFactory().getRoundBroker().findById(id);
        if (round == null) {
            throw new SportstatsServiceException("There are no round with id: " + id);
        }
        Map deletedRound = new HashMap();
        deletedRound.put("id", round.getId());
        deletedRound.put("round", round.getRound());
        deletedRound.put("seasonId", round.getSeasonId());
        round.delete();

        return deletedRound;
    }
}
